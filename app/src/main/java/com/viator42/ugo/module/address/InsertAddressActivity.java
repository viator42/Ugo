package com.viator42.ugo.module.address;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.module.address.result.InsertAddressResult;

public class InsertAddressActivity extends AppCompatActivity implements InsertAddressContract.View {
    public EditText consigneeEditText;
    public EditText deaddressEditText;
    public EditText mobileEditText;
    public Button confirmBtn;
    public Button cancelBtn;
    public AppContext appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_address);

        consigneeEditText = findViewById(R.id.consignee);
        deaddressEditText = findViewById(R.id.deaddress);
        mobileEditText = findViewById(R.id.mobile);
        confirmBtn = findViewById(R.id.confirm);
        cancelBtn = findViewById(R.id.cancel);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        appContext = (AppContext) getApplicationContext();

    }

    @Override
    public void insertSuccess(InsertAddressResult insertAddressResult) {

    }
}
