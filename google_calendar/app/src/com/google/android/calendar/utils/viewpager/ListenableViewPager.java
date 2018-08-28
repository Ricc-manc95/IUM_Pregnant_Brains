// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.utils.viewpager:
//            LayoutDirectionAwareViewPager

public class ListenableViewPager extends LayoutDirectionAwareViewPager
{

    public List listeners;

    public ListenableViewPager(Context context)
    {
        super(context);
    }

    public ListenableViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public final void addOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        if (listeners == null)
        {
            listeners = new ArrayList();
            super.setOnPageChangeListener(new _cls1());
        }
        listeners.add(onpagechangelistener);
    }

    public final void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        throw new UnsupportedOperationException("ListenableViewPager supports addOnPageChangeListener instead of setOnPageChangeListener");
    }

    private class _cls1
        implements android.support.v4.view.ViewPager.OnPageChangeListener
    {

        private final ListenableViewPager this$0;

        public final void onPageScrollStateChanged(int i)
        {
            for (Iterator iterator = listeners.iterator(); iterator.hasNext(); ((android.support.v4.view.ViewPager.OnPageChangeListener)iterator.next()).onPageScrollStateChanged(i)) { }
        }

        public final void onPageScrolled(int i, float f, int j)
        {
            for (Iterator iterator = listeners.iterator(); iterator.hasNext(); ((android.support.v4.view.ViewPager.OnPageChangeListener)iterator.next()).onPageScrolled(i, f, j)) { }
        }

        public final void onPageSelected(int i)
        {
            for (Iterator iterator = listeners.iterator(); iterator.hasNext(); ((android.support.v4.view.ViewPager.OnPageChangeListener)iterator.next()).onPageSelected(i)) { }
        }

        _cls1()
        {
            this$0 = ListenableViewPager.this;
            super();
        }
    }

}
