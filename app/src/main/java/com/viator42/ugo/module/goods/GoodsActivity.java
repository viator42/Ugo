 package com.viator42.ugo.module.goods;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.model.Goods;
import com.viator42.ugo.model.User;
import com.viator42.ugo.module.goods.param.GoodsDetailParam;
import com.viator42.ugo.module.goods.result.GoodsDetailResult;
import com.viator42.ugo.utils.GlideApp;

import java.util.ArrayList;
import java.util.HashMap;

 public class GoodsActivity extends AppCompatActivity implements GoodsContract.View{
    private GoodsPresenter goodsPresenter;
    private ViewPager sliderView;
    private AppContext appContext;
    private User user;
    private long goodsId;
    private GoodsViewPagerAdapter goodsViewPagerAdapter;
    private TextView goodsNameTextView;
    private TextView salesPriceTextView;
    private TextView originalPriceTextView;
    private TextView goodsIntroTextView;
    private TextView attributesTextView;
    private Button buyBtn;
    private Button addToCartBtn;
    HashMap<String, String[]> attribute; //key:尺码 value:颜色
    private String sizeSelected = null;
    private String colorSelected = null;
    private GoodsAttributePopup goodsAttributePopup;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        sliderView = findViewById(R.id.slider);
        goodsNameTextView = findViewById(R.id.goods_name);
        salesPriceTextView = findViewById(R.id.sales_price);
        originalPriceTextView = findViewById(R.id.original_price);
//        goodsIntroTextView = findViewById(R.id.goods_intro);
//        attributesTextView = findViewById(R.id.attributes);

        appContext = (AppContext) getApplicationContext();

        Bundle bundle = getIntent().getExtras();
        goodsId = bundle.getLong("goodsId");

    }

     @Override
     protected void onStart() {
         super.onStart();
         user = appContext.user;
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
        Goods goods = goodsDetailResult.data.goods;

        if(goods.images != null && !goods.images.isEmpty()) {
            final String[] imgList = goods.images.split(";");
            ArrayList<ImageView> imageViewList = new ArrayList<ImageView>();

            for(int a=0; a<imgList.length; a++) {
                ImageView imgView = new ImageView(this);
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
                        .centerCrop()
                        .into(imgView);
                imageViewList.add(imgView);
            }

            goodsViewPagerAdapter = new GoodsViewPagerAdapter(this, imageViewList);
            sliderView.setAdapter(goodsViewPagerAdapter);
        }

        salesPriceTextView.setText(String.valueOf(goods.goodsPrice));
        originalPriceTextView.setText(String.valueOf(goods.originalPrice));

        attribute = goodsDetailResult.data.attribute;
     }

     @Override
     public void setPresenter(GoodsContract.Presenter presenter) {

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
