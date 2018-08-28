// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.viewpager;


// Referenced classes of package com.google.android.calendar.utils.viewpager:
//            LayoutDirectionAwareViewPager

final class directionUnawareListener
    implements android.support.v4.view.nPageChangeListener
{

    private final android.support.v4.view.rePosition directionUnawareListener;
    private final LayoutDirectionAwareViewPager this$0;

    public final void onPageScrollStateChanged(int i)
    {
        directionUnawareListener.directionUnawareListener(i);
    }

    public final void onPageScrolled(int i, float f, int j)
    {
        android.support.v4.view.nPageChangeListener npagechangelistener = directionUnawareListener;
        int k = getDirectionAwarePosition(i);
        float f1 = f;
        if (usesRtl)
        {
            f1 = -f;
        }
        i = j;
        if (usesRtl)
        {
            i = -j;
        }
        npagechangelistener.rePosition(k, f1, i);
    }

    public final void onPageSelected(int i)
    {
        directionUnawareListener.directionUnawareListener(getDirectionAwarePosition(i));
    }

    public (android.support.v4.view.nPageChangeListener npagechangelistener)
    {
        this$0 = LayoutDirectionAwareViewPager.this;
        super();
        directionUnawareListener = npagechangelistener;
    }
}
