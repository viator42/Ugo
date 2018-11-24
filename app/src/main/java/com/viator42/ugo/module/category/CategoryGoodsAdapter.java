package com.viator42.ugo.module.category;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.viator42.ugo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryGoodsAdapter extends RecyclerView.Adapter<CategoryGoodsAdapter.ViewHolder> {
    List<Map<String,Object>> list;
    private Context context;

    @NonNull
    @Override
    public CategoryGoodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryGoodsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
