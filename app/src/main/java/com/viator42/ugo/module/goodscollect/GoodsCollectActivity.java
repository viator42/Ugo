package com.viator42.ugo.module.goodscollect;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.base.BaseActivity;
import com.viator42.ugo.model.AppGoodsCollectItem;
import com.viator42.ugo.model.AppgoodsId;
import com.viator42.ugo.model.Goods;
import com.viator42.ugo.model.User;
import com.viator42.ugo.module.category.CategoryActivity;
import com.viator42.ugo.module.category.CategoryGoodsAdapter;
import com.viator42.ugo.module.goodscollect.param.LoadParam;
import com.viator42.ugo.module.goodscollect.result.LoadResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsCollectActivity extends BaseActivity implements GoodsCollectContract.View {
    private GoodsCollectPresenter presenter;
    private User user;
    private AppContext appContext;

    private List<Map<String,Object>> goodsListData;
    private GoodsCollectListAdapter adapter;
    private RecyclerView goodsCollectListView;
    private GridLayoutManager categoryLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_collect);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appContext = (AppContext) getApplicationContext();

        goodsCollectListView = findViewById(R.id.goods_list);
        categoryLayoutManager = new GridLayoutManager(GoodsCollectActivity.this, 2);
        goodsCollectListView.setLayoutManager(categoryLayoutManager);

        presenter = new GoodsCollectPresenter(GoodsCollectActivity.this);

        user = appContext.user;

    }

    @Override
    protected void onStart() {
        super.onStart();
        reload();
    }

    private void reload() {
        load();
    }

    private void load() {
        LoadParam loadParam = new LoadParam();
        loadParam.userId = user.userId;
        loadParam.sessionid = user.sessionid;
        loadParam.newUserId = user.newUserId;
        loadParam.area = "370105";

        presenter.load(loadParam);
    }

    @Override
    public void list(ArrayList<AppGoodsCollectItem> appGoodsCollectItems) {
        if (goodsListData == null) {
            goodsListData = new ArrayList<Map<String,Object>>();
        }
        for (AppGoodsCollectItem appGoodsCollectItem : appGoodsCollectItems) {
            AppgoodsId appgoodsId = appGoodsCollectItem.appgoodsId;

            Map<String,Object> item = new HashMap<String,Object>();
            item.put("id", appgoodsId.id);
            item.put("obj", appgoodsId);

            goodsListData.add(item);
        }
//        currentGoodsCount += goodsList.size();

        if (adapter == null) {
            adapter = new GoodsCollectListAdapter(GoodsCollectActivity.this, goodsListData);
            goodsCollectListView.setAdapter(adapter);

        }
        else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadSuccess() {

    }

    @Override
    public void loadFailed(String msg) {

    }
}
