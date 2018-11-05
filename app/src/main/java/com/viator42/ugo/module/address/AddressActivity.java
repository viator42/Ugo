package com.viator42.ugo.module.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.model.Address;
import com.viator42.ugo.model.User;
import com.viator42.ugo.module.address.param.LoadParam;
import com.viator42.ugo.module.dev.DevActivity;
import com.viator42.ugo.module.mainpage.MainpageActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressActivity extends AppCompatActivity implements AddressContract.View{
    private ListView addressListView;
    private AddressPresenter presenter;
    private AppContext appContext;
    private User user;
    private ArrayList<Address> addresses;
    private AddressAdapter adapter;
    private List listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        addressListView = findViewById(R.id.address_list);

        presenter = new AddressPresenter(AddressActivity.this);
        appContext = (AppContext) getApplicationContext();
    }

    @Override
    protected void onStart() {
        super.onStart();
        load();
    }

    @Override
    public void list(ArrayList<Address> addresses) {
        //UI更新
        listData = new ArrayList<Map<String, Object>>();
        for (Address address : addresses) {
            Map line = new HashMap();

            line.put("id", address.id);
            line.put("consignee", address.consignee);
            line.put("mobile", address.mobile);
            line.put("area", address.area);
            line.put("deaddress", address.deaddress);
            line.put("zipcode", address.zipcode);

            line.put("userId", user.userId);
            line.put("sessionid", user.sessionid);

            listData.add(line);
        }

        if(adapter == null) {
            adapter = new AddressAdapter(AddressActivity.this, listData);
            addressListView.setAdapter(adapter);
        }
        else {
            adapter.notifyDataSetChanged();
        }

    }

    private void load() {
        user = appContext.user;
        if (user != null) {
            LoadParam loadParam = new LoadParam();
            loadParam.userId = user.userId;
            loadParam.sessionid = user.sessionid;
            loadParam.android_version = "5.6";
            loadParam.newUserId = user.newUserId;

            presenter.load(loadParam);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_address, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_insert_address:
                startActivity(new Intent(AddressActivity.this, InsertAddressActivity.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }

}
