package com.viator42.ugo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Floating Layout by viator42
 */
public class FloatingView extends ViewGroup {
    private int screenWidth;
    private int screenHeight;

    public FloatingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = windowManager.getDefaultDisplay().getWidth();
        screenHeight = windowManager.getDefaultDisplay().getHeight();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        int finalWidth = 0;
        int finalHeight = 0;

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        switch (hMode) {
            case MeasureSpec.AT_MOST:
                finalWidth = measureWidth;

                int lineWidth = 0;
                int lineHeight = 0;
                int additionHeight = 0;

                for(int a=0; a<getChildCount(); a++) {
                    View v = getChildAt(a);

                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();

                    int childWidth = v.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                    int childHeight = v.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;

                    if((lineWidth + childWidth) < measureWidth) {
                        //不换行
                        lineWidth += childWidth;
                        if(lineHeight < childHeight) {
                            lineHeight = childHeight;
                        }
                    }
                    else {
                        //超过最大宽度,换行
                        additionHeight += lineHeight;

                        lineWidth = 0;
                        lineHeight = childHeight;
                    }
                }
                finalHeight = additionHeight + lineHeight;

                break;

            case MeasureSpec.EXACTLY:
                finalWidth = measureWidth;
                finalHeight = measureHeight;

                break;

            case MeasureSpec.UNSPECIFIED:
                finalWidth = measureWidth;
                finalHeight = measureHeight;

                break;
        }

        finalWidth = finalWidth + getPaddingLeft() + getPaddingRight();
        finalHeight = finalHeight + getPaddingTop() + getPaddingBottom();

        int resultWidthMeasureSpec = MeasureSpec.makeMeasureSpec(finalWidth, wMode);
        int resultHeightMeasureSpec = MeasureSpec.makeMeasureSpec(finalHeight, hMode);

        super.onMeasure(resultWidthMeasureSpec, resultHeightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int height = getHeight();

        int lastBaseL = 0, lastBaseT = 0;   //上次的基准位置,不包括marginLeft,marginTop
        int lineMaxinumHeight = 0; //每一行的最大高度

        for(int a=0; a<getChildCount(); a++) {
            View childView = getChildAt(a);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childView.getLayoutParams();

            int cWidth = childView.getMeasuredWidth();
            int cHeight = childView.getMeasuredHeight();

            int cl = 0, ct = 0, cr = 0, cb = 0;

            //最大的元素高度
            if(cHeight > lineMaxinumHeight) {
                lineMaxinumHeight = cHeight;
            }

            //超出宽度,换行
            if((lastBaseL + cWidth) > width) {
                lastBaseL = 0;
                lastBaseT += lineMaxinumHeight;
            }

            cl = lastBaseL + marginLayoutParams.leftMargin;
            ct = lastBaseT + marginLayoutParams.topMargin;

            lastBaseL += cWidth + marginLayoutParams.leftMargin;

            cr = cl + cWidth;
            cb = ct + cHeight;

            childView.layout(cl, ct, cr, cb);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }


    // 继承自margin，支持子视图android:layout_margin属性
    public static class LayoutParams extends MarginLayoutParams {


        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }


        public LayoutParams(int width, int height) {
            super(width, height);
        }


        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }


        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }
    }

}
