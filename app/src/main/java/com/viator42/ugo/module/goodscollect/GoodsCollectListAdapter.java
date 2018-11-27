package com.viator42.ugo.module.goodscollect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.viator42.ugo.R;
import com.viator42.ugo.model.AppgoodsId;
import com.viator42.ugo.model.Goods;
import com.viator42.ugo.module.goods.GoodsActivity;
import com.viator42.ugo.utils.GlideApp;

import java.util.List;
import java.util.Map;

public class GoodsCollectListAdapter extends RecyclerView.Adapter<GoodsCollectListAdapter.ViewHolder>{
    List<Map<String,Object>> list;
    private Context context;

    public GoodsCollectListAdapter(Context context, List<Map<String, Object>> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public GoodsCollectListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.goods_item, viewGroup,false);
        GoodsCollectListAdapter.ViewHolder vh = new GoodsCollectListAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsCollectListAdapter.ViewHolder holder, int position) {
        final AppgoodsId appgoodsId = (AppgoodsId) list.get(position).get("obj");

        GlideApp.with(context)
                .load(appgoodsId.logopicUrl)
                .centerCrop()
                .into(holder.img);

        holder.name.setText(appgoodsId.goodsName);
        holder.price.setText(String.valueOf(appgoodsId.goodsPrice));
        holder.price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线（删除线）
        holder.promotionPrice.setText(String.valueOf(appgoodsId.promotionPrice));
        holder.salesCount.setText(String.valueOf(appgoodsId.saleCount));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("goodsId", appgoodsId.id);
                Intent intent = new Intent(context, GoodsActivity.class);
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return (Long)(list.get(position).get("id"));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView price;
        TextView promotionPrice;
        TextView salesCount;

        public ViewHolder(View convertView) {
            super(convertView);

            img = convertView.findViewById(R.id.img);
            name = convertView.findViewById(R.id.name);
            price =  convertView.findViewById(R.id.price);
            promotionPrice = convertView.findViewById(R.id.promotion_price);
            salesCount = convertView.findViewById(R.id.sales_count);
        }
    }
}
