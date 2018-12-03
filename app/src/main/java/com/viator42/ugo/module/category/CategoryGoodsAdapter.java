package com.viator42.ugo.module.category;

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
import com.viator42.ugo.model.Goods;
import com.viator42.ugo.module.goods.GoodsActivity;
import com.viator42.ugo.utils.GlideApp;

import java.util.List;
import java.util.Map;

public class CategoryGoodsAdapter extends RecyclerView.Adapter<CategoryGoodsAdapter.ViewHolder> {
    List<Map<String,Object>> list;
    private Context context;

    public CategoryGoodsAdapter(Context context, List<Map<String, Object>> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryGoodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.goods_item, viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Goods goods = (Goods) list.get(position).get("obj");

        GlideApp.with(context)
                .load(goods.logopicUrl)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(holder.img);

        holder.name.setText(goods.goodsName);
        holder.price.setText(String.valueOf(goods.goodsPrice));
        holder.price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线（删除线）
        holder.promotionPrice.setText(String.valueOf(goods.promotionPrice));
        holder.salesCount.setText(String.valueOf(goods.saleCount));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("goodsId", goods.id);
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
