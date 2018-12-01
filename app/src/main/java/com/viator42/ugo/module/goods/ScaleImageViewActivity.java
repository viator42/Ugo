package com.viator42.ugo.module.goods;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.viator42.ugo.R;
import com.viator42.ugo.utils.GlideApp;
import com.viator42.ugo.widget.ScaleImageView;

public class ScaleImageViewActivity extends AppCompatActivity {
    private ImageView closeBtn;
    private ScaleImageView imgView;

    private String imgSrc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scale_image_view);

        closeBtn = findViewById(R.id.close);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleImageViewActivity.this.finish();
            }
        });

        imgView = findViewById(R.id.img);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Bundle bundle = getIntent().getExtras();
        imgSrc = bundle.getString("src");

        imgView.setImageResource(R.drawable.ic_logo);

        Glide.with(this)
                .load(imgSrc)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imgView.setImageDrawable(resource);
                    }
                });
    }
}
