package com.viator42.ugo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.viator42.ugo.R;

public class HeadimgPreference extends Preference {
    private static final String TAG = "HeadimgPreference";
    private Context context;
    private int img;
    private ImageView imageView;

    public HeadimgPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeadimgPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        this.context = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.HeadimgPreference);
        img = typedArray.getResourceId(R.styleable.HeadimgPreference_img, R.drawable.ic_headimg);

    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.preference_headimg, null);

        return view;
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);

        imageView = view.findViewById(R.id.img);
        imageView.setImageResource(img);
    }

    public void setHeadimg() {

    }

}
