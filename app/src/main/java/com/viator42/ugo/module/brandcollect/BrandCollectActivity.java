package com.viator42.ugo.module.brandcollect;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.base.BaseActivity;
import com.viator42.ugo.model.AppBrandAll;
import com.viator42.ugo.model.AppBrandCollectItem;
import com.viator42.ugo.model.AppGoodsCollectItem;
import com.viator42.ugo.model.AppbrandId;
import com.viator42.ugo.model.Brand;
import com.viator42.ugo.model.User;
import com.viator42.ugo.module.brandcollect.param.LoadParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrandCollectActivity extends BaseActivity implements BrandCollectContract.View {
    private AppContext appContext;
    private User user;
    private RecyclerView brandCollectListView;
    private BrandCollectListAdapter brandCollectListAdapter;
    private LinearLayoutManager layoutManager;
    private List<Map<String,Object>> brandListData;
    private BrandCollectPresenter brandCollectPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_collect);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        brandCollectListView = findViewById(R.id.brand_collect_list);
        layoutManager = new LinearLayoutManager(BrandCollectActivity.this);
        brandCollectListView.setLayoutManager(layoutManager);

        appContext = (AppContext) getApplicationContext();
        user = appContext.user;

        brandCollectPresenter = new BrandCollectPresenter(BrandCollectActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        load();
    }

    private void load() {
        LoadParam loadParam = new LoadParam();
        loadParam.userId = user.userId;
        loadParam.sessionid = user.sessionid;
        loadParam.newUserId = user.newUserId;

        brandCollectPresenter.load(loadParam);
    }

    @Override
    public void list(ArrayList<AppBrandCollectItem> appBrandCollectItems) {
        if (brandListData == null) {
            brandListData = new ArrayList<Map<String,Object>>();
        }

        for (AppBrandCollectItem appBrandCollectItem : appBrandCollectItems) {
            Brand brand = appBrandCollectItem.appbrandId;
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("id", brand.id);
            item.put("obj", brand);

            brandListData.add(item);
        }

        if (brandCollectListAdapter == null) {
            brandCollectListAdapter = new BrandCollectListAdapter(BrandCollectActivity.this, brandListData);
            brandCollectListView.setAdapter(brandCollectListAdapter);
        }
        else {
            brandCollectListAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void loadSuccess() {

    }

    @Override
    public void loadFailed(String msg) {

    }
}
