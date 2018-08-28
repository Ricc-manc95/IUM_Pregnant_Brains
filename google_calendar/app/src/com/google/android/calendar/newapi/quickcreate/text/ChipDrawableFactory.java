// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.text;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import com.google.android.calendar.utils.rtl.RtlUtils;

final class ChipDrawableFactory
{

    private final Drawable background;
    private final int baseline;
    private final int height;
    private final boolean isRtl;
    private final Resources resources;
    private final int textInset;
    private final TextPaint textPaint;

    ChipDrawableFactory(Resources resources1, boolean flag, TextPaint textpaint, Drawable drawable, int i, int j, int k)
    {
        resources = resources1;
        isRtl = flag;
        textPaint = textpaint;
        background = drawable;
        height = i;
        baseline = j;
        textInset = k;
    }

    final Drawable createDrawable(String s, int i)
    {
        int j = textInset;
        Object obj = RtlUtils.forceTextAlignment(TextUtils.ellipsize(s, textPaint, i - j * 2, android.text.TextUtils.TruncateAt.END), isRtl);
        s = Bitmap.createBitmap(textInset * 2 + (int)textPaint.measureText(((CharSequence) (obj)), 0, ((CharSequence) (obj)).length()), height, android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(s);
        background.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        background.draw(canvas);
        if (isRtl)
        {
            i = canvas.getWidth() - textInset;
        } else
        {
            i = textInset;
        }
        j = baseline;
        canvas.drawText(((CharSequence) (obj)), 0, ((CharSequence) (obj)).length(), i, j, textPaint);
        obj = new BitmapDrawable(resources, s);
        ((Drawable) (obj)).setBounds(0, 0, s.getWidth(), s.getHeight());
        return ((Drawable) (obj));
    }
}
