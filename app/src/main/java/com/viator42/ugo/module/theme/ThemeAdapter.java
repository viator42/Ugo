package com.viator42.ugo.module.theme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.viator42.ugo.R;
import com.viator42.ugo.model.Theme;
import com.viator42.ugo.module.webview.WebviewActivity;
import com.viator42.ugo.utils.CommonUtils;
import com.viator42.ugo.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
    List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
    private Context context;

    public ThemeAdapter(List<Map<String, Object>> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_item, parent,false);
        ThemeAdapter.ViewHolder vh = new ThemeAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Theme theme = (Theme) list.get(position).get("obj");

        GlideApp.with(context)
                .load(theme.img)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CommonUtils.isValueEmpty(theme.imgUrl)) {
                    Intent intent = new Intent(context, WebviewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url", theme.imgUrl);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ViewHolder(View convertView) {
            super(convertView);

            img = convertView.findViewById(R.id.img);

        }
    }
}
