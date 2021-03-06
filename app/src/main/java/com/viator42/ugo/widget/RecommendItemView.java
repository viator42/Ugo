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
import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.model.AppgoodsId;
import com.viator42.ugo.model.Recommend;
import com.viator42.ugo.module.goods.GoodsActivity;
import com.viator42.ugo.utils.GlideApp;

public class RecommendItemView extends FrameLayout{
    private Recommend recommend;
    private ImageView imgView;
    private TextView introTextView;
    private Context context;
    private AppContext appContext;

    public RecommendItemView(final Context context, AttributeSet attrs, final Recommend recommend) {
        super(context, attrs);
        this.recommend = recommend;
        this.context = context;
        this.appContext = (AppContext) context.getApplicationContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recommend_item, this, true);

        imgView = view.findViewById(R.id.img);
        introTextView = view.findViewById(R.id.intro);

        introTextView.setText(recommend.intro);
        GlideApp.with(context)
                .load(recommend.img)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(imgView);

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppgoodsId appgoodsId = recommend.appgoodsId;
                if(appgoodsId != null)
                {
//                    Intent intent = new Intent(context, GoodsActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putLong("goodsId", appgoodsId.id);
//                    intent.putExtras(bundle);
                    appContext.eventBus.postSticky(appgoodsId);
                    context.startActivity(new Intent(context, GoodsActivity.class));

                }
            }
        });

    }
}
