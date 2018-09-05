package com.viator42.ugo.module.branddetail;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.*;
import com.bumptech.glide.request.RequestOptions;
import com.viator42.ugo.R;
import com.viator42.ugo.model.AppgoodsId;
import com.viator42.ugo.module.mainpage.HomeReAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrandGoodsAdapter extends RecyclerView.Adapter<BrandGoodsAdapter.ViewHolder> {
    List<Map<String,Object>> list;
    private Context context;

    public BrandGoodsAdapter(List<Map<String, Object>> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_item, parent,false);
        BrandGoodsAdapter.ViewHolder vh = new BrandGoodsAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppgoodsId appgoodsId = (AppgoodsId) list.get(position).get("obj");

        Glide.with(context)
                .load(appgoodsId.logopicUrl)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.img);

        holder.name.setText(appgoodsId.goodsName);
        holder.price.setText(String.valueOf(appgoodsId.goodsPrice));
        holder.price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线（删除线）
        holder.salesCount.setText(String.valueOf(appgoodsId.saleCount));
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView price;
        TextView salesCount;

        public ViewHolder(View convertView) {
            super(convertView);

            img = convertView.findViewById(R.id.img);
            name = convertView.findViewById(R.id.name);
            price =  convertView.findViewById(R.id.price);
            salesCount = convertView.findViewById(R.id.sales_count);

        }
    }
}
