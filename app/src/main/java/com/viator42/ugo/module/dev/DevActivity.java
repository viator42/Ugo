package com.viator42.ugo.module.dev;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.viator42.ugo.R;
import com.viator42.ugo.module.tclocation.TcLocation;

public class DevActivity extends AppCompatActivity {
    private Button locationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev);

        locationList = findViewById(R.id.location_list);
        locationList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new TcLocation().listAll();
//                int i = ContextCompat.checkSelfPermission(DevActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//                if(i == PackageManager.PERMISSION_GRANTED) {
//                    new TcLocation().listAll();
//                }

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            new TcLocation().listAll();
        }

    }
}
