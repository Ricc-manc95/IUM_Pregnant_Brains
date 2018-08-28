// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;

final class bottomPadding extends Drawable
{

    private final Bitmap arrow;
    private final int bottomPadding;
    public Animator lastAnimator;
    private final Matrix matrix = new Matrix();
    private final Paint paint = new Paint();
    public int rotation;

    public final void draw(Canvas canvas)
    {
        int i = (getBounds().width() - arrow.getWidth()) / 2;
        int j = getBounds().height();
        int k = arrow.getHeight();
        int l = bottomPadding;
        matrix.setRotate(rotation, arrow.getWidth() / 2, arrow.getHeight() / 2);
        matrix.postTranslate(i, j - k - l);
        canvas.drawBitmap(arrow, matrix, paint);
    }

    public final int getOpacity()
    {
        return -3;
    }

    public final void setAlpha(int i)
    {
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
    }

    (Context context, DimensConverter dimensconverter, ObservableReference observablereference)
    {
        rotation = 0;
        lastAnimator = null;
        paint.setAntiAlias(true);
        arrow = BitmapFactory.decodeResource(context.getResources(), 0x7f0201f6);
        float f;
        boolean flag;
        if ((ScreenType)observablereference.get() == ScreenType.PHONE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            f = 1.0F;
        } else
        {
            f = 4F;
        }
        bottomPadding = dimensconverter.getPixelOffset(f);
    }
}
