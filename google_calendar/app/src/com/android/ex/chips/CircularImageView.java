// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ex.chips;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircularImageView extends ImageView
{

    private static float circularImageBorder = 1.0F;
    private final Paint bitmapPaint;
    private final Paint borderPaint;
    private final RectF destination;
    private final Matrix matrix;
    private final RectF source;

    public CircularImageView(Context context)
    {
        this(context, null, 0);
    }

    public CircularImageView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public CircularImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        matrix = new Matrix();
        source = new RectF();
        destination = new RectF();
        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
        bitmapPaint.setFilterBitmap(true);
        bitmapPaint.setDither(true);
        borderPaint = new Paint();
        borderPaint.setColor(0);
        borderPaint.setStyle(android.graphics.Paint.Style.STROKE);
        borderPaint.setStrokeWidth(circularImageBorder);
        borderPaint.setAntiAlias(true);
    }

    protected void onDraw(Canvas canvas)
    {
        Object obj = getDrawable();
        Object obj1;
        if (obj instanceof StateListDrawable)
        {
            if (((StateListDrawable)obj).getCurrent() != null)
            {
                obj = (BitmapDrawable)((Drawable) (obj)).getCurrent();
            } else
            {
                obj = null;
            }
        } else
        {
            obj = (BitmapDrawable)obj;
        }
        if (obj != null)
        {
            if ((obj1 = ((BitmapDrawable) (obj)).getBitmap()) != null)
            {
                source.set(0.0F, 0.0F, ((Bitmap) (obj1)).getWidth(), ((Bitmap) (obj1)).getHeight());
                destination.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
                obj = source;
                RectF rectf = destination;
                obj1 = new BitmapShader(((Bitmap) (obj1)), android.graphics.Shader.TileMode.CLAMP, android.graphics.Shader.TileMode.CLAMP);
                matrix.reset();
                matrix.setRectToRect(((RectF) (obj)), rectf, android.graphics.Matrix.ScaleToFit.FILL);
                ((BitmapShader) (obj1)).setLocalMatrix(matrix);
                bitmapPaint.setShader(((android.graphics.Shader) (obj1)));
                canvas.drawCircle(rectf.centerX(), rectf.centerY(), rectf.width() / 2.0F, bitmapPaint);
                canvas.drawCircle(rectf.centerX(), rectf.centerY(), rectf.width() / 2.0F - circularImageBorder / 2.0F, borderPaint);
                return;
            }
        }
    }

}
