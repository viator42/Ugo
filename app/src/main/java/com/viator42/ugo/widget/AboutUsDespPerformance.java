package com.viator42.ugo.widget;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.viator42.ugo.R;

public class AboutUsDespPerformance extends Preference {
    private static final String TAG = "HeadimgPreference";
    private Context context;
    private int img;
    private ImageView imageView;

    public AboutUsDespPerformance(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AboutUsDespPerformance(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        this.context = context;

    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pref_item_about_desp, null);

        return view;
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
    }

}
