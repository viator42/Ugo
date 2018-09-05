package com.viator42.ugo.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.viator42.ugo.R;
import com.viator42.ugo.model.Recommend;

public class RecommendItemView extends FrameLayout{
    private Recommend recommend;
    private ImageView imgView;
    private TextView introTextView;

    public RecommendItemView(final Context context, AttributeSet attrs, final Recommend recommend) {
        super(context, attrs);
        this.recommend = recommend;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recommend_item, this, true);

        imgView = view.findViewById(R.id.img);
        introTextView = view.findViewById(R.id.intro);

        introTextView.setText(recommend.intro);
        Glide.with(context)
                .load(recommend.img)
                .apply(RequestOptions.centerCropTransform())
                .into(imgView);

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AppgoodsId appgoodsId = recommend.getAppgoodsId();
//                if(appgoodsId != null)
//                {
//                    Intent intent = new Intent(context, GoodsActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putLong("goodsId", appgoodsId.getId());
//                    bundle.putLong("boughtType", StaticValues.BOUTHT_TYPE_NORMAL);
//                    intent.putExtras(bundle);
//                    appContext.setBundleObj(bundle);
//                    context.startActivity(intent);
//
//                }
            }
        });

    }
}
