// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v4.view.ViewPager;

// Referenced classes of package com.google.android.calendar.timely:
//            ViewPagerFragment

final class arg._cls1
    implements Runnable
{

    private final ViewPagerFragment arg$1;

    public final void run()
    {
        ViewPagerFragment viewpagerfragment = arg$1;
        ViewPager viewpager = viewpagerfragment.getViewPager();
        for (int i = 0; i < viewpager.getChildCount(); i++)
        {
            viewpagerfragment.updatePage(viewpager.getChildAt(i));
        }

    }

    (ViewPagerFragment viewpagerfragment)
    {
        arg$1 = viewpagerfragment;
    }
}
