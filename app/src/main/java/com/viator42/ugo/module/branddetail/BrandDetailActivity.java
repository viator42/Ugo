package com.viator42.ugo.module.branddetail;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.StaticValues;
import com.viator42.ugo.model.AppBrandAll;
import com.viator42.ugo.model.AppgoodsId;
import com.viator42.ugo.model.User;
import com.viator42.ugo.module.branddetail.param.BrandGoodsParam;
import com.viator42.ugo.module.brands.BrandsContract;
import com.viator42.ugo.module.user.LoginActivity;
import com.viator42.ugo.utils.CommonUtils;
import com.viator42.ugo.utils.EndlessParentScrollListener;
import com.viator42.ugo.utils.GlideApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrandDetailActivity extends AppCompatActivity implements BrandDetailContract.View {
    private AppBrandAll appBrandAll;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView bannerImageView;
    private BrandDetailPresenter brandDetailPresenter;
    private RecyclerView brandGoodsListView;
    private GridLayoutManager gridLayoutManager;
    private BrandGoodsAdapter brandGoodsAdapter;
    private List<Map<String,Object>> brandGoodsList;
//    private SwipeRefreshLayout swipeRefreshLayout;
    private NestedScrollView nestedScrollView;
    private FloatingActionButton favBtn;
    private AppContext appContext;

    private User user;
    private long brandId;
    private int currentPage;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appContext = (AppContext) getApplicationContext();

        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        bannerImageView = (ImageView) findViewById(R.id.banner);

        favBtn = (FloatingActionButton) findViewById(R.id.fav);

        nestedScrollView = findViewById(R.id.scroll_view);
        brandGoodsListView = findViewById(R.id.brand_goods_list);
        gridLayoutManager = new GridLayoutManager(BrandDetailActivity.this, 2);
        brandGoodsListView.setLayoutManager(gridLayoutManager);

        nestedScrollView.setOnScrollChangeListener(new EndlessParentScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                load();
            }
        });

//        brandGoodsListView.addOnScrollListener(new EndlessGridRecyclerOnScrollListener(gridLayoutManager) {
//            @Override
//            public void onLoadMore(int currentPage) {
//                load();
//            }
//        });

        Bundle bundle = getIntent().getExtras();
        appBrandAll = bundle.getParcelable("obj");
        brandId = appBrandAll.id;

        collapsingToolbarLayout.setTitle(appBrandAll.brandName);
        GlideApp.with(BrandDetailActivity.this)
                .load(appBrandAll.showpic)
                .placeholder(R.drawable.placeholder)
                .into(bannerImageView);

        brandDetailPresenter = new BrandDetailPresenter(BrandDetailActivity.this);

        switch (appBrandAll.flag) {
            case StaticValues.GOODS_FAVOURITE_ON:
                favBtn.setImageResource(R.drawable.ic_heart_white_24dp);
                favBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (user == null) {
                            needsLogin();
                        }
                        else {
                            CommonUtils.makeToast(BrandDetailActivity.this, getResources().getString(R.string.has_favourited));
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
                            //关注品牌
                        }
                    }
                });
                break;
        }

        appContext.localBroadcastManager.registerReceiver(broadcastReceiver, new IntentFilter(StaticValues.BROADCAST_EXIT));
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = appContext.user;

        reload();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appContext.localBroadcastManager.unregisterReceiver(broadcastReceiver);
    }

    private void reload() {
        currentPage = 0;
        brandGoodsList = null;
        brandGoodsAdapter = null;

        load();
    }

    private void load() {
        BrandGoodsParam brandGoodsParam = new BrandGoodsParam();
        brandGoodsParam.android_version = "5.6";
        brandGoodsParam.model = false;
        brandGoodsParam.appbrandId = brandId;
        brandGoodsParam.min = currentPage * StaticValues.PAGE_COUNT;
        brandGoodsParam.max = brandGoodsParam.min + StaticValues.PAGE_COUNT;

        brandDetailPresenter.loadAll(brandGoodsParam);
    }

    @Override
    public void setPresenter(BrandsContract.Presenter presenter) {

    }

    @Override
    public void listAll(ArrayList<AppgoodsId> appgoodsIds) {
        if(brandGoodsList == null) {
            brandGoodsList = new ArrayList<Map<String,Object>>();
        }

        for (AppgoodsId appgoodsId: appgoodsIds) {
            Map<String,Object> item = new HashMap();
            item.put("id", appgoodsId.id);
            item.put("obj", appgoodsId);

            brandGoodsList.add(item);
        }

        if(brandGoodsAdapter == null) {
            brandGoodsAdapter = new BrandGoodsAdapter(brandGoodsList, BrandDetailActivity.this);
            brandGoodsListView.setAdapter(brandGoodsAdapter);
        }
        else {
            brandGoodsAdapter.notifyDataSetChanged();
        }

        currentPage += 1;

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

                Intent intent = new Intent(BrandDetailActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        builder.setNegativeButton(getResources().getString(R.string.cancel), null);
        builder.create().show();
    }

}
