package com.viator42.ugo.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.viator42.ugo.R;
import com.viator42.ugo.model.Activity;
import com.viator42.ugo.module.webview.WebviewActivity;
import com.viator42.ugo.utils.CommonUtils;
import com.viator42.ugo.utils.GlideApp;

public class ActivityItemView extends FrameLayout {
    private ImageView imageView;

    public ActivityItemView(@NonNull final Context context, @Nullable AttributeSet attrs, final Activity activity) {
        super(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.activity_item, this, true);
        imageView = view.findViewById(R.id.img);

        GlideApp.with(context)
                .load(activity.img)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(imageView);

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CommonUtils.isValueEmpty(activity.imgUrl)) {
                    Intent intent = new Intent(context, WebviewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url", activity.imgUrl);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            }
        });

    }
}
