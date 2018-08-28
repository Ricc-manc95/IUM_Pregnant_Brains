// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap.drawable;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import com.android.bitmap.BitmapCache;

// Referenced classes of package com.android.bitmap.drawable:
//            ExtendedBitmapDrawable, BasicBitmapDrawable

public class StyledCornersBitmapDrawable extends ExtendedBitmapDrawable
{

    private static final String TAG = com/android/bitmap/drawable/StyledCornersBitmapDrawable.getSimpleName();
    private static final RectF rectF = new RectF();
    private final Paint borderPaint = new Paint();
    public int bottomEndCornerStyle;
    private int bottomLeftCornerStyle;
    private int bottomRightCornerStyle;
    public int bottomStartCornerStyle;
    private final Path clipPath = new Path();
    private final Paint compatibilityModeBackgroundPaint = new Paint();
    private final Path compatibilityModePath = new Path();
    private final float cornerFlapSide;
    private final float cornerRoundRadius;
    private boolean eatInvalidates;
    public final Paint flapPaint = new Paint();
    private int scrimColor;
    public int topEndCornerStyle;
    private int topLeftCornerStyle;
    private int topRightCornerStyle;
    public int topStartCornerStyle;

    public StyledCornersBitmapDrawable(Resources resources, BitmapCache bitmapcache, boolean flag, ExtendedBitmapDrawable.ExtendedOptions extendedoptions, float f, float f1)
    {
        super(resources, bitmapcache, flag, extendedoptions);
        topLeftCornerStyle = 0;
        topRightCornerStyle = 0;
        bottomRightCornerStyle = 0;
        bottomLeftCornerStyle = 0;
        topStartCornerStyle = 0;
        topEndCornerStyle = 0;
        bottomEndCornerStyle = 0;
        bottomStartCornerStyle = 0;
        cornerRoundRadius = f;
        cornerFlapSide = f1;
        flapPaint.setColor(0);
        flapPaint.setStyle(android.graphics.Paint.Style.FILL);
        flapPaint.setAntiAlias(true);
        borderPaint.setColor(0);
        borderPaint.setStyle(android.graphics.Paint.Style.STROKE);
        borderPaint.setStrokeWidth(0.0F);
        borderPaint.setAntiAlias(true);
        compatibilityModeBackgroundPaint.setColor(0);
        compatibilityModeBackgroundPaint.setStyle(android.graphics.Paint.Style.FILL);
        compatibilityModeBackgroundPaint.setAntiAlias(true);
        scrimColor = 0;
    }

    private final void recalculatePath()
    {
        float f;
        float f1;
        float f2;
        float f3;
        Object obj;
        obj = getBounds();
        if (((Rect) (obj)).isEmpty())
        {
            return;
        }
        f = (float)((Rect) (obj)).left + 0.0F;
        f1 = (float)((Rect) (obj)).top + 0.0F;
        f2 = ((Rect) (obj)).right;
        f3 = ((Rect) (obj)).bottom;
        obj = rectF;
        ((RectF) (obj)).set(0.0F, 0.0F, cornerRoundRadius * 2.0F, cornerRoundRadius * 2.0F);
        clipPath.rewind();
        topLeftCornerStyle;
        JVM INSTR tableswitch 0 2: default 108
    //                   0 212
    //                   1 224
    //                   2 247;
           goto _L1 _L2 _L3 _L4
_L1:
        topRightCornerStyle;
        JVM INSTR tableswitch 0 2: default 140
    //                   0 278
    //                   1 290
    //                   2 319;
           goto _L5 _L6 _L7 _L8
_L5:
        bottomRightCornerStyle;
        JVM INSTR tableswitch 0 2: default 172
    //                   0 350
    //                   1 363
    //                   2 398;
           goto _L9 _L10 _L11 _L12
_L9:
        bottomLeftCornerStyle;
        JVM INSTR tableswitch 0 2: default 204
    //                   0 431
    //                   1 444
    //                   2 474;
           goto _L13 _L14 _L15 _L16
_L13:
        clipPath.close();
        return;
_L2:
        clipPath.moveTo(f, f1);
          goto _L1
_L3:
        ((RectF) (obj)).offsetTo(f, f1);
        clipPath.arcTo(((RectF) (obj)), 180F, 90F);
          goto _L1
_L4:
        clipPath.moveTo(f, f1 - cornerFlapSide);
        clipPath.lineTo(cornerFlapSide + f, f1);
          goto _L1
_L6:
        clipPath.lineTo(f2, f1);
          goto _L5
_L7:
        ((RectF) (obj)).offsetTo(f2 - ((RectF) (obj)).width(), f1);
        clipPath.arcTo(((RectF) (obj)), 270F, 90F);
          goto _L5
_L8:
        clipPath.lineTo(f2 - cornerFlapSide, f1);
        clipPath.lineTo(f2, f1 + cornerFlapSide);
          goto _L5
_L10:
        clipPath.lineTo(f2, f3);
          goto _L9
_L11:
        ((RectF) (obj)).offsetTo(f2 - ((RectF) (obj)).width(), f3 - ((RectF) (obj)).height());
        clipPath.arcTo(((RectF) (obj)), 0.0F, 90F);
          goto _L9
_L12:
        clipPath.lineTo(f2, f3 - cornerFlapSide);
        clipPath.lineTo(f2 - cornerFlapSide, f3);
          goto _L9
_L14:
        clipPath.lineTo(f, f3);
          goto _L13
_L15:
        ((RectF) (obj)).offsetTo(f, f3 - ((RectF) (obj)).height());
        clipPath.arcTo(((RectF) (obj)), 90F, 90F);
          goto _L13
_L16:
        clipPath.lineTo(cornerFlapSide + f, f3);
        clipPath.lineTo(f, f3 - cornerFlapSide);
          goto _L13
    }

    public void draw(Canvas canvas)
    {
        Object obj = getBounds();
        if (((Rect) (obj)).isEmpty())
        {
            return;
        }
        eatInvalidates = true;
        canvas.save();
        canvas.clipPath(clipPath);
        super.draw(canvas);
        canvas.drawColor(0);
        float f = (float)((Rect) (obj)).left + 0.0F;
        float f1 = (float)((Rect) (obj)).top + 0.0F;
        float f2 = ((Rect) (obj)).right;
        float f3 = ((Rect) (obj)).bottom;
        obj = rectF;
        ((RectF) (obj)).set(0.0F, 0.0F, cornerFlapSide + cornerRoundRadius, cornerFlapSide + cornerRoundRadius);
        if (topLeftCornerStyle == 2)
        {
            ((RectF) (obj)).offsetTo(f, f1);
            canvas.drawRoundRect(((RectF) (obj)), cornerRoundRadius, cornerRoundRadius, flapPaint);
        }
        if (topRightCornerStyle == 2)
        {
            ((RectF) (obj)).offsetTo(f2 - cornerFlapSide, f1);
            canvas.drawRoundRect(((RectF) (obj)), cornerRoundRadius, cornerRoundRadius, flapPaint);
        }
        if (bottomRightCornerStyle == 2)
        {
            ((RectF) (obj)).offsetTo(f2 - cornerFlapSide, f3 - cornerFlapSide);
            canvas.drawRoundRect(((RectF) (obj)), cornerRoundRadius, cornerRoundRadius, flapPaint);
        }
        if (bottomLeftCornerStyle == 2)
        {
            ((RectF) (obj)).offsetTo(f, f3 - cornerFlapSide);
            canvas.drawRoundRect(((RectF) (obj)), cornerRoundRadius, cornerRoundRadius, flapPaint);
        }
        canvas.restore();
        canvas.drawPath(clipPath, borderPaint);
        eatInvalidates = false;
    }

    public void invalidateSelf()
    {
        if (!eatInvalidates)
        {
            super.invalidateSelf();
        }
    }

    protected void onBoundsChange(Rect rect)
    {
        super.onBoundsChange(rect);
        recalculatePath();
    }

    public final void resolveCornerStyles()
    {
        boolean flag = false;
        int i;
        int j;
        int k;
        int l;
        if (super.layoutDirection == 0)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k != 0)
        {
            i = topStartCornerStyle;
        } else
        {
            i = topEndCornerStyle;
        }
        if (k != 0)
        {
            j = topEndCornerStyle;
        } else
        {
            j = topStartCornerStyle;
        }
        if (k != 0)
        {
            l = bottomEndCornerStyle;
        } else
        {
            l = bottomStartCornerStyle;
        }
        if (k != 0)
        {
            k = bottomStartCornerStyle;
        } else
        {
            k = bottomEndCornerStyle;
        }
        if (topLeftCornerStyle != i || topRightCornerStyle != j || bottomRightCornerStyle != l || bottomLeftCornerStyle != k)
        {
            flag = true;
        }
        topLeftCornerStyle = i;
        topRightCornerStyle = j;
        bottomRightCornerStyle = l;
        bottomLeftCornerStyle = k;
        if (flag)
        {
            recalculatePath();
        }
    }

}
