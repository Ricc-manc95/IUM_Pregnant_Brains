package com.unica.pregnantbrains.ddgridmanager.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class TileView extends ViewGroup {

    public TileView(Context context) {
        super(context);
    }

    public void onLayout(boolean changed, int left, int top, int bottom, int right) {
        int width = right-left;

        int rowTop = 0;
        int rowBottom = 0;
        int currentLeft = width + 1; //Set up so new row created each time.
        for (int i = 0; i < this.getChildCount(); ++i) {
            View v= this.getChildAt(i);
            int DEBUG_width = v.getMeasuredWidth();
            int DEBUG_height = v.getMeasuredHeight();
            if (currentLeft > 0 && currentLeft + v.getMeasuredWidth() >= width) {
                rowTop = rowBottom;
                rowBottom = Integer.MIN_VALUE;
                currentLeft = 0;
            }

            int currentBottom = top + v.getMeasuredHeight();
            rowBottom = Math.max(rowBottom, currentBottom);
            int currentRight = currentLeft + v.getMeasuredWidth();
            v.layout(currentLeft, rowTop, currentRight, currentBottom);
            currentLeft = currentRight;
        }
    }

}