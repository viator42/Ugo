package com.viator42.ugo.module.address;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.viator42.ugo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddressAdapter extends BaseAdapter {
    private List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
    private LayoutInflater inflater =null;
    private Context context;

    //构造器
    public AddressAdapter(Context context, List<Map<String,Object>> list){
        this.context = context;
        this.list=list;

        inflater=LayoutInflater.from(context);
    }

    private int width;
    private int height;
    private ViewHolder holder;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (Long)(list.get(position).get("id"));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            holder = new ViewHolder();
            convertView =inflater.inflate(R.layout.address_item, null);

            holder.consignee=(TextView)convertView.findViewById(R.id.consignee);
            holder.mobile=(TextView)convertView.findViewById(R.id.mobile);
            holder.area=(TextView)convertView.findViewById(R.id.area);
            holder.deaddress=(TextView)convertView.findViewById(R.id.deaddress);
            holder.zipcode=(TextView)convertView.findViewById(R.id.zipcode);

            //为view设置标签
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        holder.consignee.setText("联系人:" + list.get(position).get("consignee").toString());
        holder.mobile.setText("电话:" + list.get(position).get("mobile").toString());
        holder.area.setText(list.get(position).get("area").toString());
        holder.deaddress.setText("地址:" + list.get(position).get("deaddress").toString());

        width =View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        height =View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);

        convertView.measure(width,height);

        height=convertView.getMeasuredHeight();
        width=convertView.getMeasuredWidth();

        return convertView;
    }

    static class ViewHolder {
        TextView consignee;
        TextView mobile;
        TextView area;
        TextView deaddress;
        TextView zipcode;
    }

}
