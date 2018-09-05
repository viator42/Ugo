package com.viator42.ugo.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * URLConnection 网络连接类
 *
 */
public class URLConnectionUtil
{
    public static HashMap<String, String> post(String path, Map<String, Object> params) throws Exception
    {
        HashMap<String, String> result = new HashMap<String, String>();
        Log.v("params", path + " ---- " + params.toString());
        URL url = new URL(path);

        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        StringBuffer responseResult = null;
        StringBuffer paramsStringBuffer = new StringBuffer();

        // 组织请求参数
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry element = (Map.Entry) it.next();
            paramsStringBuffer.append(element.getKey());
            paramsStringBuffer.append("=");
            paramsStringBuffer.append(URLEncoder.encode((String) element.getValue(), "UTF-8"));
            paramsStringBuffer.append("&");
        }

        String paramsString = paramsStringBuffer.toString();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);

        // 设置通用的请求属性
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        conn.setRequestProperty("Content-Length", String.valueOf(paramsString.length()));
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setRequestProperty("contentType", "utf-8");

        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(true);

        // 获取URLConnection对象对应的输出流
        printWriter = new PrintWriter(conn.getOutputStream());
        // 发送请求参数
        printWriter.write(paramsString);



        // flush输出流的缓冲
        printWriter.flush();
        int responseCode = conn.getResponseCode();
        try {
            String cookie = conn.getHeaderField("Set-Cookie");
            result.put("cookie", cookie);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        if (responseCode == HttpURLConnection.HTTP_OK)
        {
            // 定义BufferedReader输入流来读取URL的ResponseData
            bufferedReader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line;
            responseResult = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                responseResult.append(line);
            }

        }

        if (printWriter != null) {
            printWriter.close();
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        conn.disconnect();

        if(responseResult != null)
        {
            Log.v("resultString", path + " ---- " + responseResult.toString());
            result.put("resultString", responseResult.toString());
            return result;
        }
        else
        {
            return null;
        }
    }

    public static String get(String path)
    {
        try {
            URL url = new URL(path.trim());
            StringBuffer responseResult = null;

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 设置通用的请求属性
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");

            conn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line;
            responseResult = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                responseResult.append(line);
            }
            reader.close();
            conn.disconnect();

            if(responseResult != null)
            {
                Log.v("resultString", path + " ---- " + responseResult.toString());
                return responseResult.toString();
            }
            else
            {
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
