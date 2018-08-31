package com.unica.pregnantbrains.ddgridmanager.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class GridCanvas extends View {

    Paint paint;
    Rect rect;

    public GridCanvas(Context context) {
        super(context);
        paint = new Paint();
        rect= new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
