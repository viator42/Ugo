package com.viator42.ugo.module.dev;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.module.tclocation.TcLocation;
import com.viator42.ugo.utils.CommonUtils;

import javax.inject.Inject;

public class DevActivity extends AppCompatActivity implements DevContract.View {
    private AppContext appContext;
    Button bindingTestBtn;
    Button bindingListTestBtn;

//    @Inject
//    Computer computer;
    @Inject
    DevPresenter devPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev);

        appContext = (AppContext) getApplicationContext();

        DaggerDevActivityComponent.builder().presenterModule(new PresenterModule(this)).appComponent(appContext.appComponent).build().inject(this);
//        DaggerMainComponent.builder().minePresenterModule(new MinePresenterModule(mineFragment)).appComponent(SampleApplication.getInstance().getAppComponent()).build().inject(this);
//        DaggerDevActivityComponent.create().inject(this);

        bindingTestBtn = findViewById(R.id.binding_test);
        bindingTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DevActivity.this, BindingTesterActivity.class));
            }
        });

        bindingListTestBtn = findViewById(R.id.binding_list_test);
        bindingListTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DevActivity.this, BindingListTesterActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        CommonUtils.makeToast(this, devPresenter.iamHere());
//        CommonUtils.makeToast(this, computer.display());

    }
}
