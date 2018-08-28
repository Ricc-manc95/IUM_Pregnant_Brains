// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.textfield;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

final class CutoutDrawable extends GradientDrawable
{

    public final RectF cutoutBounds = new RectF();
    private final Paint cutoutPaint = new Paint(1);
    private int savedLayer;

    CutoutDrawable()
    {
        cutoutPaint.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
        cutoutPaint.setColor(-1);
        cutoutPaint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_OUT));
    }

    public final void draw(Canvas canvas)
    {
        android.graphics.drawable.Drawable.Callback callback = getCallback();
        if (callback instanceof View)
        {
            ((View)callback).setLayerType(2, null);
        } else
        {
            savedLayer = canvas.saveLayer(0.0F, 0.0F, canvas.getWidth(), canvas.getHeight(), null);
        }
        super.draw(canvas);
        canvas.drawRect(cutoutBounds, cutoutPaint);
        if (!(getCallback() instanceof View))
        {
            canvas.restoreToCount(savedLayer);
        }
    }

    final void setCutout(float f, float f1, float f2, float f3)
    {
        if (f != cutoutBounds.left || f1 != cutoutBounds.top || f2 != cutoutBounds.right || f3 != cutoutBounds.bottom)
        {
            cutoutBounds.set(f, f1, f2, f3);
            invalidateSelf();
        }
    }
}
