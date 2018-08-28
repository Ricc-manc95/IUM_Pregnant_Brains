// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ReplacementSpan;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.text:
//            ChipDrawableFactory

public class Chip extends ReplacementSpan
    implements com.google.android.calendar.newapi.common.ShinobiEditText.OnWidthChangeListener
{

    private final int baseline;
    private Drawable drawable;
    private final ChipDrawableFactory drawableFactory;
    public final String text;

    Chip(ChipDrawableFactory chipdrawablefactory, String s, int i, int j)
    {
        drawableFactory = chipdrawablefactory;
        text = s;
        baseline = i;
        drawable = drawableFactory.createDrawable(s, j);
    }

    public void draw(Canvas canvas, CharSequence charsequence, int i, int j, float f, int k, int l, 
            int i1, Paint paint)
    {
        i = baseline;
        canvas.save();
        canvas.translate(f, l - i);
        drawable.draw(canvas);
        canvas.restore();
    }

    public int getSize(Paint paint, CharSequence charsequence, int i, int j, android.graphics.Paint.FontMetricsInt fontmetricsint)
    {
        return drawable.getIntrinsicWidth();
    }

    public final void onWidthChanged(int i)
    {
        drawable = drawableFactory.createDrawable(text, i);
    }
}
