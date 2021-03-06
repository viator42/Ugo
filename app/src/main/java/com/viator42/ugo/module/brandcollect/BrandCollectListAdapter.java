package com.viator42.ugo.module.brandcollect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.viator42.ugo.R;
import com.viator42.ugo.model.AppBrandAll;
import com.viator42.ugo.model.AppBrandCollectItem;
import com.viator42.ugo.model.AppbrandId;
import com.viator42.ugo.model.AppgoodsId;
import com.viator42.ugo.model.Brand;
import com.viator42.ugo.module.branddetail.BrandDetailActivity;
import com.viator42.ugo.module.goods.GoodsActivity;
import com.viator42.ugo.utils.CommonUtils;
import com.viator42.ugo.utils.GlideApp;

import java.util.List;
import java.util.Map;

public class BrandCollectListAdapter extends RecyclerView.Adapter<BrandCollectListAdapter.ViewHolder>{
    List<Map<String,Object>> list;
    private Context context;

    public BrandCollectListAdapter(Context context, List<Map<String, Object>> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public BrandCollectListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.brand_collect_item, viewGroup,false);
        BrandCollectListAdapter.ViewHolder vh = new BrandCollectListAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BrandCollectListAdapter.ViewHolder holder, int position) {
        final AppbrandId brand = (AppbrandId) list.get(position).get("obj");
        GlideApp.with(context)
                .load(brand.logopic)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(holder.img);
        holder.name.setText(brand.brandName);
        holder.detail.setText(CommonUtils.omissionText(brand.detail, 100));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, BrandDetailActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putLong("id", (Long) list.get(position).get("id"));
//                bundle.putParcelable("obj", brand);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
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
        TextView detail;

        public ViewHolder(View convertView) {
            super(convertView);

            img = convertView.findViewById(R.id.img);
            name = convertView.findViewById(R.id.name);
            detail =  convertView.findViewById(R.id.detail);
        }
    }
}
