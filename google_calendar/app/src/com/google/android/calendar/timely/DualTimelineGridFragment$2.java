// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v4.view.ViewPager;

// Referenced classes of package com.google.android.calendar.timely:
//            DualTimelineGridFragment, TimelineRecyclerView

final class this._cls0
    implements android.support.v4.view.ner
{

    private final DualTimelineGridFragment this$0;

    public final void onPageScrollStateChanged(int i)
    {
        if (i == 0)
        {
            i = dayPager.getCurrentItem();
            onNewDayOnTop(DualTimelineGridFragment.getJulianDayFromPosition(i));
        }
    }

    public final void onPageScrolled(int i, float f, int j)
    {
    }

    public final void onPageSelected(int i)
    {
        if (inGridMode())
        {
            list.setForceShowPosition(i);
        }
    }

    ()
    {
        this$0 = DualTimelineGridFragment.this;
        super();
    }
}
