// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.material.Material;
import com.google.common.util.concurrent.ListenableFuture;

final class MonthBannerView extends View
{

    public int backgroundColor;
    private Bitmap bannerBitmap;
    public ListenableFuture bannerBitmapFuture;
    private final Matrix bannerMatrix = new Matrix();
    public final Paint bitmapPaint = new Paint();
    public ValueAnimator fadeInAnimator;
    private final ObservableReference isRtl;
    public String text;
    private final Paint textPaint = new Paint();
    private final float textSize;
    private final float textStartMargin;
    private final float textTopMargin;

    MonthBannerView(Context context, ObservableReference observablereference, ScheduleProvider scheduleprovider, ObservableReference observablereference1, DimensConverter dimensconverter)
    {
        super(context);
        isRtl = observablereference;
        textSize = dimensconverter.spToPx(21F);
        float f;
        boolean flag;
        if ((ScreenType)observablereference1.get() == ScreenType.PHONE)
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
            f = 100F;
        } else
        {
            f = 72F;
        }
        textStartMargin = dimensconverter.spToPx(f);
        if (scheduleprovider.shouldShowMonthImages())
        {
            f = 36F;
        } else
        {
            f = 55F;
        }
        textTopMargin = dimensconverter.dpToPx(f);
        textPaint.setStyle(android.graphics.Paint.Style.FILL);
        textPaint.setColor(0xff000000);
        textPaint.setTextSize(textSize);
        observablereference = textPaint;
        if (Material.robotoMedium != null)
        {
            context = Material.robotoMedium;
        } else
        {
            context = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = context;
        }
        observablereference.setTypeface(context);
        textPaint.setAntiAlias(true);
        textPaint.setStrokeWidth(dimensconverter.dpToPx(1.0F));
    }

    final void clean()
    {
        if (bannerBitmapFuture != null)
        {
            bannerBitmapFuture.cancel(false);
            bannerBitmapFuture = null;
        }
        if (fadeInAnimator != null)
        {
            fadeInAnimator.cancel();
            fadeInAnimator = null;
        }
        bannerBitmap = null;
    }

    final void onBitmapFutureComplete(boolean flag)
    {
        Exception exception1;
        class .Lambda._cls1
            implements android.animation.ValueAnimator.AnimatorUpdateListener
        {

            private final MonthBannerView arg$1;

            public final void onAnimationUpdate(ValueAnimator valueanimator)
            {
                MonthBannerView monthbannerview = arg$1;
                monthbannerview.bitmapPaint.setAlpha(((Integer)valueanimator.getAnimatedValue()).intValue());
                monthbannerview.invalidate();
            }

            .Lambda._cls1()
            {
                arg$1 = MonthBannerView.this;
            }
        }

        try
        {
            bannerBitmap = (Bitmap)bannerBitmapFuture.get();
        }
        catch (Exception exception)
        {
            bannerBitmapFuture = null;
            return;
        }
        finally
        {
            bannerBitmapFuture = null;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        fadeInAnimator = ValueAnimator.ofInt(new int[] {
            0, 255
        });
        fadeInAnimator.addUpdateListener(new .Lambda._cls1());
        fadeInAnimator.addListener(new _cls1());
        fadeInAnimator.start();
        invalidate();
        bannerBitmapFuture = null;
        return;
        throw exception1;
    }

    protected final void onDraw(Canvas canvas)
    {
        canvas.clipRect(0, 0, getWidth(), getHeight());
        if (bannerBitmap == null || fadeInAnimator != null)
        {
            canvas.drawColor(backgroundColor);
        }
        if (bannerBitmap != null)
        {
            View view = (View)getParent();
            Matrix matrix = bannerMatrix;
            int i = bannerBitmap.getWidth();
            int j = bannerBitmap.getHeight();
            int k = getHeight();
            float f1 = getY();
            int l = view.getWidth();
            int i1 = view.getHeight();
            boolean flag = ((Boolean)isRtl.get()).booleanValue();
            if (f1 < (float)(-k) || f1 > (float)i1)
            {
                i = 0;
            } else
            {
                float f = (float)l / (float)i;
                matrix.setScale(f, f);
                if (flag)
                {
                    matrix.postScale(-1F, 1.0F, ((float)i * f) / 2.0F, ((float)j * f) / 2.0F);
                }
                f1 = ((float)k + f1) / (float)(i1 + k);
                float f2 = j;
                float f3 = k;
                matrix.postTranslate(0.0F, -f1 * (f * f2 - f3));
                i = 1;
            }
            if (i != 0)
            {
                canvas.drawBitmap(bannerBitmap, bannerMatrix, bitmapPaint);
            }
        }
        if (((Boolean)isRtl.get()).booleanValue())
        {
            textPaint.setTextAlign(android.graphics.Paint.Align.RIGHT);
            canvas.drawText(text, (float)getWidth() - textStartMargin, textTopMargin + textSize, textPaint);
            return;
        } else
        {
            textPaint.setTextAlign(android.graphics.Paint.Align.LEFT);
            canvas.drawText(text, textStartMargin, textTopMargin + textSize, textPaint);
            return;
        }
    }

    private class _cls1 extends AnimatorListenerAdapter
    {

        private final MonthBannerView this$0;

        public final void onAnimationEnd(Animator animator)
        {
            bitmapPaint.setAlpha(255);
            fadeInAnimator = null;
            invalidate();
        }

        _cls1()
        {
            this$0 = MonthBannerView.this;
            super();
        }
    }

}
