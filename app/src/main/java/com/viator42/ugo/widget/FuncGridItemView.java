package com.viator42.ugo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.viator42.ugo.R;
import com.viator42.ugo.model.FuncGridItem;
@Deprecated
public class FuncGridItemView extends RelativeLayout {
    private ImageView imgView;
    private TextView titleTextView;

    public FuncGridItemView(Context context, AttributeSet attrs, FuncGridItem gridFuncItem) {
        super(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.func_grid_item, this, true);

        imgView = view.findViewById(R.id.img);
        titleTextView = view.findViewById(R.id.title);

        if(gridFuncItem.img == null || gridFuncItem.img.isEmpty()) {
            imgView.setImageResource(gridFuncItem.imgRes);
        }
        else {
            Glide.with(context)
                .load(gridFuncItem.img)
                .apply(RequestOptions.centerCropTransform())
                .into(imgView);
        }

        titleTextView.setText(gridFuncItem.title);


    }

}
