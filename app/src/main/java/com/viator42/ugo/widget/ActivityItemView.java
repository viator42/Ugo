package com.viator42.ugo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.viator42.ugo.R;
import com.viator42.ugo.model.Activity;

public class ActivityItemView extends FrameLayout {
    private ImageView imageView;

    public ActivityItemView(@NonNull Context context, @Nullable AttributeSet attrs, Activity activity) {
        super(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.activity_item, this, true);
        imageView = view.findViewById(R.id.img);

        Glide.with(context)
                .load(activity.img)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView);

    }
}
