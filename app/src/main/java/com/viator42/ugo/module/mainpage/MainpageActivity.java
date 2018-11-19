package com.viator42.ugo.module.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viator42.ugo.R;
import com.viator42.ugo.module.brands.BrandsFragment;
import com.viator42.ugo.module.dev.DevActivity;
import com.viator42.ugo.module.mine.MineFragment;
import com.viator42.ugo.module.theme.ThemeFragment;
import com.viator42.ugo.utils.BottomNavigationViewHelper;

public class MainpageActivity extends AppCompatActivity {
    private MainpageFragment mainpageFragment;
    private BrandsFragment brandsFragment;
    private ThemeFragment themeFragment;
    private MineFragment mineFragment;
    private Toolbar toolbar;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RelativeLayout contentViewGroup;
    private Fragment currentFragment;

    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            navSelected(item.getItemId());
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

//        toolbar.setNavigationIcon(R.mipmap.ic_arrow_left_white_24dp);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        contentViewGroup = findViewById(R.id.content);

        manager = getSupportFragmentManager();

        navSelected(R.id.nav_home);

    }

    /**
     * 切换tab
     */
    private void navSelected(int itemId) {
        transaction = manager.beginTransaction();

        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }

        switch (itemId) {
            case R.id.nav_home:
                if(mainpageFragment == null) {
                    mainpageFragment = new MainpageFragment();
                    transaction.add(R.id.content, mainpageFragment);
                }
                else {
                    transaction.show(mainpageFragment);
                }
                currentFragment = mainpageFragment;
                break;

            case R.id.nav_brand:
                if(brandsFragment == null) {
                    brandsFragment = new BrandsFragment();
                    transaction.add(R.id.content, brandsFragment);
                }
                else {
                    transaction.show(brandsFragment);
                }
                currentFragment = brandsFragment;
                break;

            case R.id.nav_theme:
                if(themeFragment == null) {
                    themeFragment = new ThemeFragment();
                    transaction.add(R.id.content, themeFragment);
                }
                else {
                    transaction.show(themeFragment);
                }
                currentFragment = themeFragment;
                break;

            case R.id.nav_mine:
                if(mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.content, mineFragment);
                }
                else {
                    transaction.show(mineFragment);
                }
                currentFragment = mineFragment;
                break;

        }

        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_mine, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_dev:
                startActivity(new Intent(MainpageActivity.this, DevActivity.class));
                break;
            case R.id.menu_item_settings:

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
