package com.viator42.ugo.module.order;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
//import android.support.v7.app.AlertController;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.model.User;
import com.viator42.ugo.module.order.param.LoadParam;
import com.viator42.ugo.module.order.result.LoadResult;

public class OrderListActivity extends AppCompatActivity implements OrderListContract.View {
//    private AlertController.RecycleListView recycleListView;
    private OrderListPresenter presenter;
    private AppContext appContext;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        presenter = new OrderListPresenter(OrderListActivity.this);

        appContext = (AppContext) getApplicationContext();
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

        presenter.load(loadParam);
    }

    @Override
    public void list(LoadResult loadResult) {

    }

    @Override
    public void setPresenter(OrderListContract.Presenter presenter) {
    }
}
