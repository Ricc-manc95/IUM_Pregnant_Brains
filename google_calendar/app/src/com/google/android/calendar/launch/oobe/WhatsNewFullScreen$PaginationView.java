// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.oobe;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.calendar.utils.rtl.RtlUtils;

// Referenced classes of package com.google.android.calendar.launch.oobe:
//            WhatsNewFullScreen

final class _cls1 extends View
{

    private int currentPage;
    private final int dotColor;
    public final int dotMargin;
    public final int dotRadius;
    public ShapeDrawable dots[];
    public float fadeValue;
    private int lastPage;
    private final WhatsNewFullScreen this$0;

    protected final void onDraw(Canvas canvas)
    {
        if (dots != null)
        {
            int j = 0;
            while (j < dots.length) 
            {
                ShapeDrawable shapedrawable = dots[j];
                int i;
                if (j == lastPage)
                {
                    i = 255 - (int)(fadeValue * 204F);
                } else
                if (j == currentPage)
                {
                    i = (int)(fadeValue * 204F) + 51;
                } else
                {
                    i = 51;
                }
                shapedrawable.getPaint().setColor(dotColor);
                shapedrawable.getPaint().setAlpha(i);
                shapedrawable.draw(canvas);
                j++;
            }
        }
    }

    public final void setCurrentPage(int i)
    {
        clearAnimation();
        int j = i;
        if (RtlUtils.isLayoutDirectionRtl(getContext()))
        {
            j = 3 - i;
        }
        lastPage = currentPage;
        currentPage = j;
        ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
            1.0F - fadeValue, 1.0F
        });
        class _cls2
            implements android.animation.ValueAnimator.AnimatorUpdateListener
        {

            private final WhatsNewFullScreen.PaginationView this$1;

            public final void onAnimationUpdate(ValueAnimator valueanimator1)
            {
                fadeValue = ((Float)valueanimator1.getAnimatedValue()).floatValue();
                invalidate();
            }

            _cls2()
            {
                this$1 = WhatsNewFullScreen.PaginationView.this;
                super();
            }
        }

        valueanimator.addUpdateListener(new _cls2());
        valueanimator.start();
    }

    _cls1.this._cls1(Context context)
    {
        this$0 = WhatsNewFullScreen.this;
        super(context);
        context = context.getResources();
        dotRadius = context.getDimensionPixelSize(0x7f0e0304);
        dotMargin = context.getDimensionPixelSize(0x7f0e0303);
        dotColor = context.getColor(0x7f0d0169);
        lastPage = -1;
        currentPage = 0;
        fadeValue = 1.0F;
        class _cls1
            implements android.view.ViewTreeObserver.OnPreDrawListener
        {

            private final WhatsNewFullScreen.PaginationView this$1;

            public final boolean onPreDraw()
            {
                WhatsNewFullScreen.PaginationView paginationview = WhatsNewFullScreen.PaginationView.this;
                int i = paginationview.getWidth();
                if (i == 0)
                {
                    i = 0;
                } else
                {
                    int j = paginationview.getHeight() / 2;
                    paginationview.dots = new ShapeDrawable[4];
                    int k = i / 2;
                    int l = paginationview.dotMargin / 2;
                    int i1 = paginationview.dotRadius;
                    int j1 = paginationview.dotRadius;
                    int k1 = paginationview.dotMargin;
                    for (i = 0; i < 4; i++)
                    {
                        ShapeDrawable shapedrawable = new ShapeDrawable(new OvalShape());
                        int l1 = (paginationview.dotRadius * 2 + paginationview.dotMargin) * i + (k - l - i1 - (j1 * 2 + k1) * 1);
                        shapedrawable.setBounds(l1 - paginationview.dotRadius, j - paginationview.dotRadius, l1 + paginationview.dotRadius, paginationview.dotRadius + j);
                        paginationview.dots[i] = shapedrawable;
                    }

                    i = 1;
                }
                if (i == 0)
                {
                    requestLayout();
                    return false;
                } else
                {
                    getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            }

            _cls1(WhatsNewFullScreen whatsnewfullscreen)
            {
                this$1 = WhatsNewFullScreen.PaginationView.this;
                super();
            }
        }

        getViewTreeObserver().addOnPreDrawListener(new _cls1(WhatsNewFullScreen.this));
    }
}
