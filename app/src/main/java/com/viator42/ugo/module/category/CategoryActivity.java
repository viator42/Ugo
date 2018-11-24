package com.viator42.ugo.module.category;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.viator42.ugo.R;
import com.viator42.ugo.StaticValues;
import com.viator42.ugo.base.BaseActivity;
import com.viator42.ugo.model.User;
import com.viator42.ugo.module.category.param.CategoryGoodsParam;

public class CategoryActivity extends BaseActivity implements CategoryContract.View {
    private CategoryPresenter presenter;
    private RecyclerView goodsListView;
    private User user;
    private int currentGoodsCount;
    private long categoryId;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        goodsListView = findViewById(R.id.category_goods_list);

        presenter = new CategoryPresenter(CategoryActivity.this);

        Bundle bundle = getIntent().getExtras();
        categoryId = bundle.getLong("id");
        categoryName = bundle.getString("name");

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
    public void list() {

    }
}
