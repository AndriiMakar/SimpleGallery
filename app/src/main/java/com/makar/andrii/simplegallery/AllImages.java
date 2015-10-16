package com.makar.andrii.simplegallery;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;


public class AllImages extends ImageView {                                                      //view used to show all images in gallery

    public AllImages(Context context) {
        super(context);
    }

    public AllImages(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AllImages(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();                                                         // calculating size and scalling images
        int height = getMeasuredHeight();

        if (width != height) {

            setMeasuredDimension(width, width);
            setScaleType(ScaleType.CENTER_CROP);

        }

    }


}