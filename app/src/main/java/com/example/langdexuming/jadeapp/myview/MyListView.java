package com.example.langdexuming.jadeapp.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by 地球人 on 2015/9/27.
 */
public class MyListView extends ListView{

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);//my set
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }



}
