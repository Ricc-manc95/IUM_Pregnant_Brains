// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.slab.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.calendar.utils.viewpager.LayoutDirectionAwareViewPager;

public class ResizableViewPager extends LayoutDirectionAwareViewPager
{

    public ResizableViewPager(Context context)
    {
        super(context);
    }

    public ResizableViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        addOnPageChangeListener(new _cls1());
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        View view = findViewWithTag(Integer.valueOf(getCurrentItem()));
        if (view != null)
        {
            view.measure(i, android.view.View.MeasureSpec.makeMeasureSpec(0, 0));
            i = android.view.View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 0x40000000);
            setMeasuredDimension(getMeasuredWidth(), i);
        }
    }

    private class _cls1 extends android.support.v4.view.ViewPager.SimpleOnPageChangeListener
    {

        private final ResizableViewPager this$0;

        public final void onPageSelected(int i)
        {
            requestLayout();
        }

        _cls1()
        {
            this$0 = ResizableViewPager.this;
            super();
        }
    }

}
