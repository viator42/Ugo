package com.viator42.ugo.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.viator42.ugo.AppContext;

public class BaseActivity extends AppCompatActivity {
    protected AppContext appContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        appContext = (AppContext) getApplicationContext();

    }
}
