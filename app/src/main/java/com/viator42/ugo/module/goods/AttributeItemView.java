package com.viator42.ugo.module.goods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viator42.ugo.R;

public class AttributeItemView extends RelativeLayout {
    private TextView textView;

    public AttributeItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.attribute_item, this, true);

        textView = view.findViewById(R.id.text);

    }

}
