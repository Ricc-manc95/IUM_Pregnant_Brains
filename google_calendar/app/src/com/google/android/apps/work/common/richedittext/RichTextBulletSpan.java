// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.work.common.richedittext;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.util.DisplayMetrics;

public final class RichTextBulletSpan extends BulletSpan
{

    private int bulletRadius;
    public int index;
    private final int level;
    private int marginAfter;
    private int marginBefore;
    private final int type;

    public RichTextBulletSpan()
    {
        this(0, 1);
    }

    public RichTextBulletSpan(int i, int j)
    {
        this(i, j, 2, 26, 10);
    }

    private RichTextBulletSpan(int i, int j, int k, int l, int i1)
    {
        type = i;
        level = j;
        bulletRadius = 2;
        marginBefore = 26;
        marginAfter = 10;
    }

    public final void drawLeadingMargin(Canvas canvas, Paint paint, int i, int j, int k, int l, int i1, 
            CharSequence charsequence, int j1, int k1, boolean flag, Layout layout)
    {
        float f2;
        int l1;
label0:
        {
            if (((Spanned)charsequence).getSpanStart(this) == j1)
            {
                f2 = Resources.getSystem().getDisplayMetrics().scaledDensity;
                l1 = Math.round((float)marginBefore * f2);
                boolean flag1 = false;
                j1 = ((flag1) ? 1 : 0);
                if (level > 0)
                {
                    k1 = getLeadingMargin(true) * (level - 1);
                    float f;
                    float f3;
                    if (j <= 0)
                    {
                        k1 = layout.getWidth() - k1;
                    }
                    j1 = ((flag1) ? 1 : 0);
                    if (i != k1)
                    {
                        j1 = k1 - i;
                    }
                }
                if (type != 1)
                {
                    break label0;
                }
                charsequence = String.valueOf(String.valueOf(index + 1)).concat(".");
                f = Math.round(f2 * (float)marginAfter);
                f2 = j1;
                f3 = j;
                canvas.drawText(charsequence, f2 + ((f + (float)l1) - paint.measureText(charsequence)) * f3 + (float)i, l, paint);
            }
            return;
        }
        layout = paint.getStyle();
        float f1 = paint.getStrokeWidth();
        paint.setStrokeWidth(Math.round((float)1 * f2));
        l = Math.round((float)bulletRadius * f2);
        float f4 = j1 + (l1 + l) * j;
        f2 = (float)(k + i1) / 2.0F;
        if (level <= 2)
        {
            if (level == 1)
            {
                charsequence = android.graphics.Paint.Style.FILL;
            } else
            {
                charsequence = android.graphics.Paint.Style.STROKE;
            }
            paint.setStyle(charsequence);
            if (canvas.isHardwareAccelerated())
            {
                charsequence = new Path();
                charsequence.addCircle(0.0F, 0.0F, (float)l * 1.2F, android.graphics.Path.Direction.CW);
                canvas.save();
                canvas.translate(f4 + (float)i, f2);
                canvas.drawPath(charsequence, paint);
                canvas.restore();
            } else
            {
                canvas.drawCircle((float)i + f4, f2, l, paint);
            }
        } else
        {
            paint.setStyle(android.graphics.Paint.Style.FILL);
            f4 += i;
            float f5 = 2.4F * (float)l;
            canvas.drawRect(f4, f2 - f5 / 2.0F, f4 + f5, f2 + f5 / 2.0F, paint);
        }
        paint.setStyle(layout);
        paint.setStrokeWidth(f1);
    }

    public final int getLeadingMargin(boolean flag)
    {
        return Math.round(Resources.getSystem().getDisplayMetrics().scaledDensity * (float)(marginBefore + (bulletRadius << 1) + marginAfter));
    }
}
