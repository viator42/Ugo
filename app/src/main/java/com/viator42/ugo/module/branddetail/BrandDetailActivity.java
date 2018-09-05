package com.viator42.ugo.module.branddetail;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.viator42.ugo.R;
import com.viator42.ugo.StaticValues;
import com.viator42.ugo.model.AppBrandAll;
import com.viator42.ugo.model.AppbrandId;
import com.viator42.ugo.model.AppgoodsId;
import com.viator42.ugo.module.branddetail.param.BrandGoodsParam;
import com.viator42.ugo.module.brands.BrandsContract;
import com.viator42.ugo.module.mainpage.HomeReAdapter;
import com.viator42.ugo.module.mainpage.result.HomeReItem;
import com.viator42.ugo.utils.EndlessGridRecyclerOnScrollListener;
import com.viator42.ugo.utils.EndlessParentScrollListener;

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

    private long brandId;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        bannerImageView = (ImageView) findViewById(R.id.banner);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
        Glide.with(BrandDetailActivity.this)
                .load(appBrandAll.showpic)
                .into(bannerImageView);

        brandDetailPresenter = new BrandDetailPresenter(BrandDetailActivity.this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        reload();
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
}
