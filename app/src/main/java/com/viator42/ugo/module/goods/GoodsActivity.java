 package com.viator42.ugo.module.goods;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.StaticValues;
import com.viator42.ugo.base.BaseActivity;
import com.viator42.ugo.model.AppgoodsId;
import com.viator42.ugo.model.AppuserId;
import com.viator42.ugo.model.Goods;
import com.viator42.ugo.model.User;
import com.viator42.ugo.module.branddetail.BrandDetailActivity;
import com.viator42.ugo.module.goods.param.GoodsDetailParam;
import com.viator42.ugo.module.goods.param.SaveAppGoodsCollectParam;
import com.viator42.ugo.module.goods.result.GoodsDetailResult;
import com.viator42.ugo.module.order.OrderConfirmActivity;
import com.viator42.ugo.module.user.LoginActivity;
import com.viator42.ugo.utils.CommonUtils;
import com.viator42.ugo.utils.GlideApp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;

 public class GoodsActivity extends BaseActivity implements GoodsContract.View{
    private GoodsPresenter goodsPresenter;
    private ViewPager sliderView;
    private AppContext appContext;
    private User user;
    private long goodsId;
    private GoodsViewPagerAdapter goodsViewPagerAdapter;
    private ViewGroup rootView;
    private TextView goodsNameTextView;
    private TextView salesPriceTextView;
    private TextView originalPriceTextView;
    private TextView goodsIntroTextView;
    private TextView attributesTextView;
    private Button addToCartBtn;
    HashMap<String, String[]> attributes; //key:尺码 value:颜色
    private GoodsAttributePopup goodsAttributePopup;
    private Goods goods;
     private String sizeSelected = null;
     private String colorSelected = null;
     private String attributeSelected = null;
    private TextView attributeTextView;
    private FloatingActionButton favBtn;
     private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
         @Override
         public void onReceive(Context context, Intent intent) {
             finish();
         }
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        goodsPresenter = new GoodsPresenter(this);

        favBtn = (FloatingActionButton) findViewById(R.id.fab);

        sliderView = findViewById(R.id.slider);
        goodsNameTextView = findViewById(R.id.goods_name);
        salesPriceTextView = findViewById(R.id.sales_price);
        originalPriceTextView = findViewById(R.id.original_price);
//        goodsIntroTextView = findViewById(R.id.goods_intro);
//        attributesTextView = findViewById(R.id.attributes);

        rootView = findViewById(android.R.id.content);

        attributeTextView = findViewById(R.id.attribute);
        attributeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAttributePopup();
            }
        });

        appContext = (AppContext) getApplicationContext();

//        Bundle bundle = getIntent().getExtras();
//        goodsId = bundle.getLong("goodsId");

        appContext.eventBus.register(this);

        appContext.localBroadcastManager.registerReceiver(broadcastReceiver, new IntentFilter(StaticValues.BROADCAST_EXIT));
    }

     @Override
     protected void onStart() {
         super.onStart();
         user = appContext.user;
     }

     @Override
     protected void onDestroy() {
         super.onDestroy();
         appContext.eventBus.unregister(this);
         appContext.localBroadcastManager.unregisterReceiver(broadcastReceiver);
     }

     @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
     public void onAppgoodsIdEvent(AppgoodsId appgoodsId) {
         goodsId = appgoodsId.id;
         loadDetail();
     }

     @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
     public void onGoodsEvent(Goods goods) {
        goodsId = goods.id;
        loadDetail();
     }

     private void loadDetail() {
        GoodsDetailParam goodsDetailParam = new GoodsDetailParam();
        if(user != null) {
            goodsDetailParam.userId = user.userId;
            goodsDetailParam.sessionid = user.sessionid;
            goodsDetailParam.newUserId = user.newUserId;
        }
        goodsDetailParam.goodsId = goodsId;
        goodsDetailParam.android_version = "5.6";
        goodsDetailParam.area = "370105";

        goodsPresenter.loadDetails(goodsDetailParam);
     }

     @Override
     public void showDetails(GoodsDetailResult goodsDetailResult) {
        goods = goodsDetailResult.data.goods;

        if(goods.images != null && !goods.images.isEmpty()) {
            final String[] imgList = goods.images.split(";");
            ArrayList<ImageView> imageViewList = new ArrayList<ImageView>();

            for(int a=0; a<imgList.length; a++) {
                final ImageView imgView = new ImageView(this);
                final String imgSrc = imgList[a];
                imgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("src", imgSrc);
                        Intent intent = new Intent(GoodsActivity.this, ScaleImageViewActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                GlideApp.with(this)
                        .load(imgSrc)
                        .placeholder(R.drawable.placeholder)
                        .fitCenter()
                        .into(imgView);

                imageViewList.add(imgView);
            }

            goodsViewPagerAdapter = new GoodsViewPagerAdapter(this, imageViewList);
            sliderView.setAdapter(goodsViewPagerAdapter);
        }

         switch (goods.flag) {
            case StaticValues.GOODS_FAVOURITE_ON:
                favBtn.setImageResource(R.drawable.ic_heart_white_24dp);
                favBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (user == null) {
                            needsLogin();
                        }
                        else {
                            CommonUtils.makeToast(GoodsActivity.this, getResources().getString(R.string.has_favourited));
                        }
                    }
                });
                break;
            case StaticValues.GOODS_FAVOURITE_OFF:
                favBtn.setImageResource(R.drawable.ic_heart_outline_white_24dp);
                favBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (user == null) {
                            needsLogin();
                        }
                        else {
                            // 添加收藏
                            saveAppGoodsCollect();
                        }
                    }
                });
                break;
         }

        goodsNameTextView.setText(goods.goodsName);
        salesPriceTextView.setText(String.valueOf(goods.goodsPrice));
        originalPriceTextView.setText(String.valueOf(goods.originalPrice));

        attributes = goodsDetailResult.data.attribute;
     }

     @Override
     public void addToCartDone() {

     }

     /**
      * 添加收藏
      */
     private void saveAppGoodsCollect() {
         if (user != null) {
             AppuserId appuserId = new AppuserId();
             appuserId.userId = user.userId;
             appuserId.newUserId = user.newUserId;
             appuserId.sessionid = user.sessionid;

             AppgoodsId appgoodsId = new AppgoodsId();
             appgoodsId.id = goodsId;

             SaveAppGoodsCollectParam saveAppGoodsCollectParam = new SaveAppGoodsCollectParam();
             saveAppGoodsCollectParam.appuserId = appuserId;
             saveAppGoodsCollectParam.appgoodsId = appgoodsId;

             goodsPresenter.saveAppGoodsCollect(saveAppGoodsCollectParam);
         }
         else {
             CommonUtils.makeToast(GoodsActivity.this, getResources().getString(R.string.needs_login));
         }

     }

     @Override
     public void saveAppGoodsCollectSuccess() {
        CommonUtils.makeToast(GoodsActivity.this, getResources().getString(R.string.add_collection_success));
        goods.flag = StaticValues.GOODS_FAVOURITE_ON;
        favBtn.setImageResource(R.drawable.ic_heart_white_24dp);
     }

     @Override
     public void saveAppGoodsCollectFailed(String msg) {
        if(CommonUtils.isValueEmpty(msg)) {
            CommonUtils.makeToast(GoodsActivity.this, getResources().getString(R.string.add_collection_failed));
        }
        else {
            CommonUtils.makeToast(GoodsActivity.this, msg);
        }
     }

     @Override
     public void setPresenter(GoodsContract.Presenter presenter) {

     }

     /**
      * 唤起属性选择
      */
     private void callAttributePopup()
     {
         if(goodsAttributePopup == null)
         {
             goodsAttributePopup = new GoodsAttributePopup(GoodsActivity.this, null, goods, attributes);
             goodsAttributePopup.setOutsideTouchable(true);
         }
         goodsAttributePopup.showAtLocation(rootView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
     }

     public void setAttributeSelected(String sizeSelected, String colorSelected) {
         this.sizeSelected = sizeSelected;
         this.colorSelected = colorSelected;

         this.attributeSelected = "颜色:"+colorSelected+" 尺码:"+sizeSelected;
         attributeTextView.setText(this.attributeSelected);
     }

     private void buyit() {
         if(CommonUtils.isValueEmpty(sizeSelected)) {
             CommonUtils.makeToast(GoodsActivity.this, getResources().getString(R.string.choose_size));
             return;
         }
         if (CommonUtils.isValueEmpty(colorSelected)) {
             CommonUtils.makeToast(GoodsActivity.this, getResources().getString(R.string.choose_color));
             return;
         }

         goods.attribute = attributeSelected;

         Intent intent = new Intent(GoodsActivity.this, OrderConfirmActivity.class);

         ArrayList<Goods> goodsList = new ArrayList<Goods>();
         goodsList.add(goods);

         Bundle bundle = new Bundle();
         bundle.putParcelableArrayList("goods", goodsList);

         intent.putExtras(bundle);
         startActivity(intent);
     }

     /**
      * 需要登录窗口
      */
     public void needsLogin() {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setMessage(R.string.needs_login);
         builder.setTitle(R.string.tip);
         builder.setPositiveButton(getResources().getString(R.string.login), new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 dialogInterface.dismiss();

                 Intent intent = new Intent(GoodsActivity.this, LoginActivity.class);
                 startActivity(intent);

             }
         });
         builder.setNegativeButton(getResources().getString(R.string.cancel), null);
         builder.create().show();
     }

//     private void callAttributePopup(int type)
//     {
//         if(goodsAttributePopup == null)
//         {
//             goodsAttributePopup = new GoodsAttributePopup(GoodsActivity.this, goodsInfo, sizeSelected, colorSelected, type);
//             goodsAttributePopup.setOutsideTouchable(true);
//         }
//         goodsAttributePopup.showAtLocation(rootView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//     }
 }
