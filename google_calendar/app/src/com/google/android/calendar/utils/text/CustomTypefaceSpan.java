// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.text;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

public final class CustomTypefaceSpan extends TypefaceSpan
{

    private final Typeface typeface;

    public CustomTypefaceSpan(String s, Typeface typeface1)
    {
        super(s);
        typeface = typeface1;
    }

    public final void updateDrawState(TextPaint textpaint)
    {
        textpaint.setTypeface(typeface);
    }

    public final void updateMeasureState(TextPaint textpaint)
    {
        textpaint.setTypeface(typeface);
    }
}
