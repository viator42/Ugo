package com.viator42.ugo.module.dev;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jakewharton.disklrucache.DiskLruCache;
import com.viator42.ugo.R;
import com.viator42.ugo.utils.CommonUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;

public class LruCacheActivity extends AppCompatActivity {
    String url = "http://sdqqsntd.com/static/img/bg.jpg";
    LruCache<String, Bitmap> imgCache;
    EditText urlEditText;
    Button reloadBtn;
    ImageView imgView;
    Handler imgDisplayHandler;
    final static int MSG_DISPLAY_IMG = 1000;
    int maxMemory = 0;
    int cacheSize = 0;
    Bitmap bitmap = null;
    DiskLruCache diskLruCache;

    BitmapFactory.Options options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lru_cache);

        urlEditText = findViewById(R.id.url);
        imgView = findViewById(R.id.img);
        reloadBtn = findViewById(R.id.reload);
        reloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload();
            }
        });

        imgDisplayHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what) {
                    case MSG_DISPLAY_IMG:
                        byte[] data = (byte[]) msg.obj;

                        try {
                            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
                        }catch (OutOfMemoryError e)
                        {
                            System.gc();
                            System.runFinalization();
                        }

                        if(bitmap != null)
                        {
                            imgView.setImageBitmap(bitmap);
                            CommonUtils.makeToast(LruCacheActivity.this, "图片从网络加载");

                            imgCache.put(getMD5(url), bitmap);

                        }

                        //磁盘缓存
                        try {
                            DiskLruCache.Editor editor = diskLruCache.edit(getMD5(url));
                            if(editor != null) {
                                OutputStream outputStream = editor.newOutputStream(0);
                                outputStream.write(data);
                                outputStream.flush();
                                editor.commit();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        break;
                }

            }
        };

        maxMemory = (int) Runtime.getRuntime().maxMemory();
        CommonUtils.log("maxMemory" + String.valueOf(maxMemory));
        if (maxMemory != 0) {
            cacheSize = maxMemory / 8;
        }

        imgCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int size = value.getRowBytes() * value.getHeight();
                CommonUtils.log("Cache size: " + String.valueOf(size));
                return size;
            }
        };

        try {
            diskLruCache = DiskLruCache.open(getExternalCacheDir(), 1, 1, cacheSize);

        } catch (IOException e) {
            e.printStackTrace();
        }

        options = new BitmapFactory.Options();
//        options.outWidth = 800;
//        options.outHeight = 600
        options.inJustDecodeBounds = false;
        options.inSampleSize = 2;
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        options.inScaled = true;

    }

    @Override
    protected void onStart() {
        super.onStart();

        reload();

    }

    private void reload() {
        bitmap = imgCache.get(getMD5(url));
        if(bitmap != null) {
            CommonUtils.makeToast(LruCacheActivity.this, "图片从缓存中加载");
            imgView.setImageBitmap(bitmap);
            return;
        }

        bitmap = getFromDiskCache();
        if(bitmap != null) {
            CommonUtils.makeToast(LruCacheActivity.this, "图片从磁盘缓存中加载");
            imgView.setImageBitmap(bitmap);
            return;
        }

        new DownloadImageHtread(url).start();
    }

    private Bitmap getFromDiskCache() {
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(getMD5(url));
            if(snapshot!=null){
                Bitmap bitmap = BitmapFactory.decodeStream(snapshot.getInputStream(0), null, options);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    class DownloadImageHtread extends Thread {
        private String path;

        public DownloadImageHtread(String path) {
            super();
            this.path = path;
        }

        @Override
        public void run() {
            super.run();

            URL url = null;
            try {
                url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5 * 1000);
                conn.setRequestMethod("GET");
                InputStream inStream = conn.getInputStream();
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    Message msg = new Message();
                    msg.what = MSG_DISPLAY_IMG;
                    msg.obj = readStream(inStream);

                    imgDisplayHandler.sendMessage(msg);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        private byte[] readStream(InputStream inStream) throws Exception{
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while( (len=inStream.read(buffer)) != -1){
                outStream.write(buffer, 0, len);
            }
            outStream.close();
            inStream.close();
            return outStream.toByteArray();
        }

    }

    /**
     * 对字符串md5加密(小写+字母)
     *
     * @param str 传入要加密的字符串
     * @return  MD5加密后的字符串
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        finish();
    }
}
