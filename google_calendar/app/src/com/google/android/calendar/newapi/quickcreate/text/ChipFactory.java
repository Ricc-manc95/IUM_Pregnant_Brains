// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.text;

import android.content.res.Resources;
import android.text.TextPaint;
import com.google.android.calendar.newapi.common.ShinobiEditText;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.text:
//            ChipDrawableFactory

public final class ChipFactory
{

    public final int baseline;
    public final ChipDrawableFactory drawableFactory;
    public final ShinobiEditText editText;

    ChipFactory(ShinobiEditText shinobiedittext, ChipDrawableFactory chipdrawablefactory, int i)
    {
        editText = shinobiedittext;
        drawableFactory = chipdrawablefactory;
        baseline = i;
    }

    public static void setupLineHeightFor(ShinobiEditText shinobiedittext)
    {
        Resources resources = shinobiedittext.getResources();
        android.graphics.Paint.FontMetricsInt fontmetricsint = shinobiedittext.getPaint().getFontMetricsInt();
        int i = resources.getDimensionPixelSize(0x7f0e0338);
        shinobiedittext.addGlobalSpan(new ChipLineHeightSpan(i, (i - fontmetricsint.top - fontmetricsint.bottom) / 2));
    }

    private class ChipLineHeightSpan
        implements LineHeightSpan, UpdateLayout
    {

        public final int baseline;
        public final int height;

        public final void chooseHeight(CharSequence charsequence, int i, int j, int k, int l, android.graphics.Paint.FontMetricsInt fontmetricsint)
        {
            i = -baseline;
            j = height - baseline;
            fontmetricsint.top = i;
            fontmetricsint.bottom = j;
            fontmetricsint.ascent = i;
            fontmetricsint.descent = j;
        }

        ChipLineHeightSpan(int i, int j)
        {
            height = i;
            baseline = j;
        }
    }

}
