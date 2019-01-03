package com.viator42.ugo.module.dev;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.viator42.ugo.R;
import com.viator42.ugo.databinding.ActivityBindingTesterBinding;
import com.viator42.ugo.utils.CommonUtils;

public class BindingTesterActivity extends AppCompatActivity {
    ActivityBindingTesterBinding binding;
    Swordman swordman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_tester);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_binding_tester);

        swordman = new Swordman();
        swordman.setName("John");
        swordman.setLevel("36");

        binding.setSwordman(swordman);

        binding.fetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.makeToast(BindingTesterActivity.this, "button clicked");
                swordman.setName("harry");
                swordman.setLevel("19");
//                binding.notifyChange();
//                binding.setSwordman(swordman);
            }
        });

        binding.setGoodsName("商品名称");

    }

    @Override
    protected void onStart() {
        super.onStart();
    }




}
