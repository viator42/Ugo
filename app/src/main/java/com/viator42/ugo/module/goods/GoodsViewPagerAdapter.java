package com.viator42.ugo.module.goods;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class GoodsViewPagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<ImageView> imageViews;

    public GoodsViewPagerAdapter(Context context, ArrayList<ImageView> imageViews) {
        this.context = context;
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public ImageView instantiateItem(ViewGroup container, int position) {
        ImageView imageView = imageViews.get(position);
        container.removeView(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
