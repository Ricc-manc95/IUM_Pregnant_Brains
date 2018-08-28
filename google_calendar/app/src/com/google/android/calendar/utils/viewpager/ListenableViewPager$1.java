// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.viewpager;

import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.utils.viewpager:
//            ListenableViewPager

final class this._cls0
    implements android.support.v4.view.Listener
{

    private final ListenableViewPager this$0;

    public final void onPageScrollStateChanged(int i)
    {
        for (Iterator iterator = listeners.iterator(); iterator.hasNext(); ((android.support.v4.view.Listener)iterator.next()).onPageScrollStateChanged(i)) { }
    }

    public final void onPageScrolled(int i, float f, int j)
    {
        for (Iterator iterator = listeners.iterator(); iterator.hasNext(); ((android.support.v4.view.Listener)iterator.next()).onPageScrolled(i, f, j)) { }
    }

    public final void onPageSelected(int i)
    {
        for (Iterator iterator = listeners.iterator(); iterator.hasNext(); ((android.support.v4.view.Listener)iterator.next()).onPageSelected(i)) { }
    }

    ()
    {
        this$0 = ListenableViewPager.this;
        super();
    }
}
