package com.viator42.ugo.module.brands;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.viator42.ugo.R;
import com.viator42.ugo.model.AppBrandAll;
import com.viator42.ugo.module.branddetail.BrandDetailActivity;
import com.viator42.ugo.utils.CommonUtils;
import com.viator42.ugo.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.ViewHolder> {

    List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
    private Context context;

    //构造器
    public BrandsAdapter(List<Map<String,Object>> list, Context context){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_item, parent,false);
        BrandsAdapter.ViewHolder vh = new BrandsAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        AppBrandAll appBrandAll = (AppBrandAll) list.get(position).get("obj");

        GlideApp.with(context)
                .load(appBrandAll.logopic)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(holder.img);

        holder.name.setText(appBrandAll.brandName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CommonUtils.makeToast(context, String.valueOf(list.get(position).get("id")));
                Intent intent = new Intent(context, BrandDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("id", (Long) list.get(position).get("id"));
                bundle.putParcelable("obj", (Parcelable) list.get(position).get("obj"));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;

        public ViewHolder(View convertView) {
            super(convertView);

            img = convertView.findViewById(R.id.img);
            name = convertView.findViewById(R.id.name);

        }
    }
}
