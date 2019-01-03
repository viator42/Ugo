package com.viator42.ugo.module.dev;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.viator42.ugo.R;
import com.viator42.ugo.databinding.ActivityBindingListTesterBinding;

import java.util.ArrayList;
import java.util.List;

public class BindingListTesterActivity extends AppCompatActivity {
    private ActivityBindingListTesterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_list_tester);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_binding_list_tester);

        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.empolyeeList.setLayoutManager(layoutManager);
        BindingListAdapter adapter = new BindingListAdapter(initData());
        binding.empolyeeList.setAdapter(adapter);
    }

    private List<Employee> initData() {
        List<Employee> list = new ArrayList<>();
        for(int a=0; a<10; a++) {
            list.add(new Employee("/haedimg.jpg", "John", 20+a));
        }

        return list;
    }

}
