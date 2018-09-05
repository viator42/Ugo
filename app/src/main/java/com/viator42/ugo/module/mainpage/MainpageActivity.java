package com.viator42.ugo.module.mainpage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viator42.ugo.R;
import com.viator42.ugo.module.brands.BrandsFragment;
import com.viator42.ugo.module.mine.MineFragment;
import com.viator42.ugo.module.theme.ThemeFragment;
import com.viator42.ugo.utils.BottomNavigationViewHelper;

public class MainpageActivity extends AppCompatActivity {
    private MainpageFragment mainpageFragment;
    private BrandsFragment brandsFragment;
    private ThemeFragment themeFragment;
    private MineFragment mineFragment;

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RelativeLayout contentViewGroup;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    if(mainpageFragment == null)
                    {
                        mainpageFragment = new MainpageFragment();
                    }
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.content, mainpageFragment, "mainpageFragment");
                    transaction.commit();

                    return true;

                case R.id.nav_brand:
                    if(brandsFragment == null)
                    {
                        brandsFragment = new BrandsFragment();
                    }
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.content, brandsFragment, "brandsFragment");
                    transaction.commit();

                    return true;

                case R.id.nav_theme:
                    if(themeFragment == null)
                    {
                        themeFragment = new ThemeFragment();
                    }
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.content, themeFragment, "themeFragment");
                    transaction.commit();

                    return true;

                case R.id.nav_mine:
                    if(mineFragment == null)
                    {
                        mineFragment = new MineFragment();
                    }
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.content, mineFragment, "themeFragment");
                    transaction.commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        contentViewGroup = findViewById(R.id.content);

        manager = getSupportFragmentManager();

        if(mainpageFragment == null)
        {
            mainpageFragment = new MainpageFragment();
        }
        transaction = manager.beginTransaction();
        transaction.replace(R.id.content, mainpageFragment, "mainpageFragment");
        transaction.commit();

    }

}
