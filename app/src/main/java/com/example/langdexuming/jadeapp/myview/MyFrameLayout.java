package com.example.langdexuming.jadeapp.myview;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by langdexuming on 2015/10/23 23:00
 * Email:langdexuming_x@163.com.
 */
public class MyFrameLayout extends FrameLayout {
    public MyFrameLayout(Context context) {
        super(context);
    }

    public MyFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);// my set
      //  heightMeasureSpec=widthMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
