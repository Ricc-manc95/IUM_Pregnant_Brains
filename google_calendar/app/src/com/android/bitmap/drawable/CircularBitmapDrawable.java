// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import com.android.bitmap.BitmapCache;
import com.android.bitmap.ReusableBitmap;

// Referenced classes of package com.android.bitmap.drawable:
//            ExtendedBitmapDrawable, BasicBitmapDrawable, TileDrawable

public class CircularBitmapDrawable extends ExtendedBitmapDrawable
{

    private final Paint bitmapPaint;
    private final Paint borderPaint;
    private final Matrix matrix;
    private final Rect rect;
    private Bitmap shaderBitmap;

    public CircularBitmapDrawable(Resources resources, BitmapCache bitmapcache, boolean flag)
    {
        this(resources, bitmapcache, flag, null);
    }

    private CircularBitmapDrawable(Resources resources, BitmapCache bitmapcache, boolean flag, ExtendedBitmapDrawable.ExtendedOptions extendedoptions)
    {
        super(resources, bitmapcache, flag, null);
        bitmapPaint = new Paint();
        borderPaint = new Paint();
        rect = new Rect();
        matrix = new Matrix();
        bitmapPaint.setAntiAlias(true);
        bitmapPaint.setFilterBitmap(true);
        bitmapPaint.setDither(true);
        borderPaint.setColor(0);
        borderPaint.setStyle(android.graphics.Paint.Style.STROKE);
        borderPaint.setStrokeWidth(0.0F);
        borderPaint.setAntiAlias(true);
    }

    protected final void onDrawBitmap(Canvas canvas, Rect rect1, Rect rect2)
    {
        onDrawCircularBitmap(getBitmap().bmp, canvas, rect1, rect2, 1.0F);
    }

    public final void onDrawCircularBitmap(Bitmap bitmap, Canvas canvas, Rect rect1, Rect rect2, float f)
    {
        BitmapShader bitmapshader = (BitmapShader)bitmapPaint.getShader();
        if (bitmapshader == null || shaderBitmap != bitmap)
        {
            bitmapshader = new BitmapShader(bitmap, android.graphics.Shader.TileMode.CLAMP, android.graphics.Shader.TileMode.CLAMP);
            shaderBitmap = bitmap;
        }
        matrix.reset();
        float f1 = Math.max((float)rect2.width() / (float)rect1.width(), (float)rect2.height() / (float)rect1.height());
        matrix.postScale(f1, f1);
        matrix.postTranslate(rect2.left, rect2.top);
        bitmapshader.setLocalMatrix(matrix);
        bitmapPaint.setShader(bitmapshader);
        int i = bitmapPaint.getAlpha();
        bitmapPaint.setAlpha((int)((float)i * f));
        canvas.drawCircle(rect2.centerX(), rect2.centerY(), rect2.width() / 2, bitmapPaint);
        bitmapPaint.setAlpha(i);
        canvas.drawCircle(rect2.centerX(), rect2.centerY(), (float)rect2.width() / 2.0F, borderPaint);
    }

    protected final void onDrawPlaceholderOrProgress(Canvas canvas, TileDrawable tiledrawable)
    {
        Rect rect1 = getBounds();
        if (tiledrawable.inner instanceof BitmapDrawable)
        {
            tiledrawable = (BitmapDrawable)tiledrawable.inner;
            Bitmap bitmap = tiledrawable.getBitmap();
            float f = (float)tiledrawable.getPaint().getAlpha() / 255F;
            rect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            onDrawCircularBitmap(bitmap, canvas, rect, rect1, f);
        } else
        {
            super.onDrawPlaceholderOrProgress(canvas, tiledrawable);
        }
        canvas.drawCircle(rect1.centerX(), rect1.centerY(), (float)rect1.width() / 2.0F, borderPaint);
    }

    public void setAlpha(int i)
    {
        super.setAlpha(i);
        int j = bitmapPaint.getAlpha();
        bitmapPaint.setAlpha(i);
        if (i != j)
        {
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        super.setColorFilter(colorfilter);
        mPaint.setColorFilter(colorfilter);
    }
}
