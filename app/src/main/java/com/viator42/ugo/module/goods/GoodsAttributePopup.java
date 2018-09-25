package com.viator42.ugo.module.goods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.viator42.ugo.R;
import com.viator42.ugo.model.Goods;
import com.viator42.ugo.utils.GlideApp;
import com.viator42.ugo.widget.FloatingView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2015/11/20.
 * 商品属性选择弹窗
 *
 */
public class GoodsAttributePopup extends PopupWindow
{
    private Context context;
    private Goods goods;
    HashMap<String, String[]> attributes;

    private ImageView imgView;
    private TextView nameTextView;
    private TextView priceTextView;
    private TextView attributesTextView;
    private FloatingView colorsContainer;
    private FloatingView sizesContainer;
    private Button confirmBtn;
    private String sizeSelected;
    private String colorselected;

    public GoodsAttributePopup(Context context, AttributeSet attrs, Goods goods, HashMap<String, String[]> attributes) {
        super(context, attrs);
        this.context = context;
        this.goods = goods;
        this.attributes = attributes;

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.attribute_popup, null);

        imgView = view.findViewById(R.id.img);
        nameTextView = view.findViewById(R.id.goods_name);
        priceTextView = view.findViewById(R.id.price);
        attributesTextView = view.findViewById(R.id.attribute);
        colorsContainer = view.findViewById(R.id.colors);
        sizesContainer = view.findViewById(R.id.sizes);
        confirmBtn = view.findViewById(R.id.confirm);

        //set values
        GlideApp.with(context)
                .load(goods.logopicUrl)
                .fitCenter()
                .into(imgView);
        nameTextView.setText(goods.goodsName);
        priceTextView.setText(String.valueOf(goods.promotionPrice));

        Set<String> colorSet = new HashSet();
        Set<String> sizeSet = new HashSet();

        //attributes set
        Iterator attributesIterator = attributes.entrySet().iterator();
        while (attributesIterator.hasNext()) {
            Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) attributesIterator.next();
            String size = entry.getKey();
            String[] colors = entry.getValue();

            sizeSet.add(size);
            for(int a=0; a<colors.length; a++) {
                colorSet.add(colors[a]);
            }
        }

        Iterator sizeIterator = sizeSet.iterator();
        while (sizeIterator.hasNext()) {
            final TextView textView = new TextView(context);
            textView.setText((String) sizeIterator.next());
            FloatingView.LayoutParams params = new FloatingView.LayoutParams(FloatingView.LayoutParams.WRAP_CONTENT,
                    FloatingView.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 8, 8, 8);
            textView.setLayoutParams(params);
            textView.setPadding(8, 8, 8, 8);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //设置尺寸
                    sizeSelected = textView.getText().toString();
                    setSelectedAttribute();
                }
            });
            sizesContainer.addView(textView);
        }

        Iterator colorIterator = colorSet.iterator();
        while (colorIterator.hasNext()) {
            final TextView textView = new TextView(context);
            textView.setText((String) colorIterator.next());
            FloatingView.LayoutParams params = new FloatingView.LayoutParams(FloatingView.LayoutParams.WRAP_CONTENT,
                    FloatingView.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 8, 8, 8);
            textView.setLayoutParams(params);
            textView.setPadding(8, 8, 8, 8);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //设置颜色
                    colorselected = textView.getText().toString();
                    setSelectedAttribute();
                }
            });
            colorsContainer.addView(textView);
        }

        this.setContentView(view);
    }

    private void setSelectedAttribute() {
        GoodsActivity goodsActivity = (GoodsActivity) context;
        goodsActivity.setAttributeSelected(sizeSelected, colorselected);
    }

}

