package com.viator42.ugo.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viator42.ugo.R;
import com.viator42.ugo.model.Category;

/**
 * Created by Administrator on 2016/1/26.
 */
public class CategoryItemView extends LinearLayout {
    private TextView nameTextView;

    public CategoryItemView(final Context context, AttributeSet attrs, final Category category) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.category_item, this, true);

        nameTextView = (TextView) view.findViewById(R.id.name);
        nameTextView.setText(category.name);

    }


}
