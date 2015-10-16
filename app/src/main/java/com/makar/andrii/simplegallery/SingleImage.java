package com.makar.andrii.simplegallery;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SingleImage extends ImageView {                                                //class which show single image
    public SingleImage(Context context) {
        super(context);
    }

    public SingleImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SingleImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {                 //method that calculate size of image that have to be shown
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        setMeasuredDimension(width, height);
        setScaleType(ScaleType.CENTER_INSIDE);


    }
}
