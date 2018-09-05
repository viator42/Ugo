package com.viator42.ugo.module.mainpage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.model.AppgoodsId;
import com.viator42.ugo.module.goods.GoodsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/13.
 */
public class HomeReAdapter extends RecyclerView.Adapter<HomeReAdapter.ViewHolder>
{
    List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
    private Context context;

    //构造器
    public HomeReAdapter(List<Map<String,Object>> list, Context context){
        this.context=context;
        this.list=list;
    }

    @Override
    public HomeReAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_re_item, viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final AppgoodsId appgoodsId = (AppgoodsId) list.get(position).get("obj");

        Glide.with(context)
            .load(appgoodsId.logopicUrl)
            .into(holder.img);

        holder.name.setText(appgoodsId.goodsName);
        holder.price.setText(String.valueOf(appgoodsId.goodsPrice));
        holder.price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线（删除线）
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
