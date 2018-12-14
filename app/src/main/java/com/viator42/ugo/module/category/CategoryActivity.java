package com.viator42.ugo.module.category;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.StaticValues;
import com.viator42.ugo.base.BaseActivity;
import com.viator42.ugo.model.Category;
import com.viator42.ugo.model.Goods;
import com.viator42.ugo.model.User;
import com.viator42.ugo.module.category.param.CategoryGoodsParam;
import com.viator42.ugo.utils.CommonUtils;
import com.viator42.ugo.utils.EndlessGridRecyclerOnScrollListener;
import com.viator42.ugo.utils.TimeoutbleProgressDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryActivity extends BaseActivity implements CategoryContract.View {
    private CategoryPresenter presenter;
    private RecyclerView goodsListView;
    private User user;
    private int currentGoodsCount;
    private long categoryId;
    private String categoryName;
    private List<Map<String,Object>> goodsListData;
    private CategoryGoodsAdapter categoryGoodsAdapter;
    private GridLayoutManager categoryLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TimeoutbleProgressDialog loadingDialog;
    private Toolbar toolbar;
    private AppContext appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        toolbar = findViewById(R.id.toolbar);

        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                reload();
            }
        });

        loadingDialog = TimeoutbleProgressDialog.createProgressDialog(CategoryActivity.this, StaticValues.CONNECTION_TIMEOUT, new TimeoutbleProgressDialog.OnTimeOutListener() {
            @Override
            public void onTimeOut(TimeoutbleProgressDialog dialog) {
                loadingDialog.dismiss();
                CommonUtils.makeToast(CategoryActivity.this, "加载内容失败");
            }
        });

        goodsListView = findViewById(R.id.goods_list);
        categoryLayoutManager = new GridLayoutManager(CategoryActivity.this, 2);
        goodsListView.setLayoutManager(categoryLayoutManager);
        goodsListView.addOnScrollListener(new EndlessGridRecyclerOnScrollListener(categoryLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                load();
            }
        });

        presenter = new CategoryPresenter(CategoryActivity.this);

//        Bundle bundle = getIntent().getExtras();
//        categoryId = bundle.getLong("id");
//        categoryName = bundle.getString("name");
//
//        toolbar.setTitle(categoryName);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        appContext = (AppContext) getApplicationContext();

        appContext.eventBus.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appContext.eventBus.unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        reload();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onCategoryEvent(@NonNull Category category) {
        categoryName = category.name;
        categoryId = category.id;

        toolbar.setTitle(categoryName);

        reload();
    }

    private void reload() {
        goodsListData = null;
        categoryGoodsAdapter = null;
        currentGoodsCount = 0;
        load();
    }

    private void load() {
        CategoryGoodsParam categoryGoodsParam = new CategoryGoodsParam();
        if (user != null) {
            categoryGoodsParam.userId = user.userId;
            categoryGoodsParam.sessionid = user.sessionid;
        }

        categoryGoodsParam.min = currentGoodsCount;
        categoryGoodsParam.max = StaticValues.PAGE_COUNT;
        categoryGoodsParam.area = "370105";
        categoryGoodsParam.appcategoryId = categoryId;

        presenter.load(categoryGoodsParam);
    }

    @Override
    public void list(ArrayList<Goods> goodsList) {
        if (goodsListData == null) {
            goodsListData = new ArrayList<Map<String,Object>>();
        }
        for (Goods goods : goodsList) {
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("id", goods.id);
            item.put("obj", goods);

            goodsListData.add(item);
        }
        currentGoodsCount += goodsList.size();

        if (categoryGoodsAdapter == null) {
            categoryGoodsAdapter = new CategoryGoodsAdapter(CategoryActivity.this, goodsListData);
            goodsListView.setAdapter(categoryGoodsAdapter);

        }
        else {
            categoryGoodsAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void loadDone() {
        swipeRefreshLayout.setRefreshing(false);
        loadingDialog.dismiss();
    }

    @Override
    public void loadFailed(String msg) {
        if(!CommonUtils.isValueEmpty(msg)) {
            CommonUtils.makeToast(CategoryActivity.this, msg);
        }
        else {
            CommonUtils.makeToast(CategoryActivity.this, R.string.load_category_failed);
        }
    }

    @Override
    public void loadStart() {
        loadingDialog.show();
    }
}
