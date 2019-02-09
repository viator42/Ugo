package com.viator42.ugo.module.goods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.viator42.ugo.R;
import com.viator42.ugo.model.Goods;
import com.viator42.ugo.utils.CommonUtils;
import com.viator42.ugo.utils.GlideApp;
import com.viator42.ugo.widget.FloatingView;

import java.util.ArrayList;
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
    final ArrayList<RadioButton> colorViewItems;
    final ArrayList<RadioButton> sizeViewItems;

    private ImageView imgView;
    private TextView nameTextView;
    private TextView priceTextView;
    private TextView attributesTextView;
    private FloatingView colorsContainer;
    private FloatingView sizesContainer;
    private Button confirmBtn;
    private String sizeSelected;
    private String colorselected;

    public GoodsAttributePopup(final Context context, AttributeSet attrs, Goods goods, HashMap<String, String[]> attributes) {
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
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.isValueEmpty(colorselected)) {
                    CommonUtils.makeToast(context, R.string.choose_color);
                    return;
                }
                if (CommonUtils.isValueEmpty(sizeSelected)) {
                    CommonUtils.makeToast(context, R.string.choose_size);
                    return;
                }

                GoodsAttributePopup.this.dismiss();
            }
        });

        //set values
        GlideApp.with(context)
                .load(goods.logopicUrl)
                .fitCenter()
                .into(imgView);
        nameTextView.setText(goods.goodsName);
        priceTextView.setText(String.valueOf(goods.promotionPrice));

        Set<String> colorSet = new HashSet();
        Set<String> sizeSet = new HashSet();

        colorViewItems = new ArrayList<RadioButton>();
        sizeViewItems = new ArrayList<RadioButton>();

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
            String text = (String) sizeIterator.next();
            final RadioButton sizeItemView = new RadioButton(context);
            sizeItemView.setText(text);
            FloatingView.LayoutParams params = new FloatingView.LayoutParams(FloatingView.LayoutParams.WRAP_CONTENT,
                    FloatingView.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 8, 8, 8);
            sizeItemView.setLayoutParams(params);
            sizeItemView.setPadding(16, 8, 16, 8);
            sizeItemView.setButtonDrawable(null);
            sizeItemView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    setAttributeChecked(buttonView, isChecked);
                }
            });

            sizeItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(RadioButton item: sizeViewItems) {
                        item.setChecked(false);
                    }
                    sizeItemView.setChecked(true);

                    //设置尺寸
                    sizeSelected = sizeItemView.getText().toString();
                    updateSelectedAttribute();
                }
            });

            sizeViewItems.add(sizeItemView);
            sizesContainer.addView(sizeItemView);
        }

        Iterator colorIterator = colorSet.iterator();
        while (colorIterator.hasNext()) {
            String text = (String) colorIterator.next();
            final RadioButton colorItemView = new RadioButton(context);
            colorItemView.setText(text);
            FloatingView.LayoutParams params = new FloatingView.LayoutParams(FloatingView.LayoutParams.WRAP_CONTENT,
                    FloatingView.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 8, 8, 8);
            colorItemView.setLayoutParams(params);
            colorItemView.setPadding(16, 8, 16, 8);
            colorItemView.setButtonDrawable(null);
            colorItemView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    setAttributeChecked(buttonView, isChecked);
                }
            });

            colorItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(RadioButton item: colorViewItems) {
                        item.setChecked(false);
                    }
                    colorItemView.setChecked(true);

                    //设置颜色
                    colorselected = colorItemView.getText().toString();
                    updateSelectedAttribute();
                }
            });

            colorViewItems.add(colorItemView);
            colorsContainer.addView(colorItemView);
        }

        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                returnSelectedAttribute();
            }
        });

        this.setContentView(view);
    }

    private void setAttributeChecked(View view, boolean checked) {
        if (checked) {
            view.setBackground(context.getResources().getDrawable(R.drawable.rect_attribute_checked));
        }
        else {
            view.setBackground(context.getResources().getDrawable(R.drawable.rect_attribute_unchecked));
        }
    }

    public void setSelectedAttribute(String sizeSelected, String colorselected) {
        this.colorselected = colorselected;
        this.sizeSelected = sizeSelected;

        updateSelectedAttribute();
    }

    private void updateSelectedAttribute() {
        if (CommonUtils.isValueEmpty(sizeSelected)) {
            sizeSelected = "";
        }
        if (CommonUtils.isValueEmpty(colorselected)) {
            colorselected = "";
        }

        attributesTextView.setText("颜色:"+colorselected+" 尺码:"+sizeSelected);
    }

    private void returnSelectedAttribute() {
        GoodsActivity goodsActivity = (GoodsActivity) context;
        goodsActivity.setAttributeSelected(sizeSelected, colorselected);
    }

}

