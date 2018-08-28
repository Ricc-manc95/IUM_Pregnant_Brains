// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.View;

// Referenced classes of package com.google.android.calendar.timely:
//            DayViewPagerAdapter

final class this._cls0
    implements android.view..AllDayLayoutListener
{

    private final DayViewPagerAdapter this$0;

    public final void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1)
    {
        i = l - j;
        j = l1 - j1;
        if (i != j)
        {
            view = scrollManager;
            view.ollPositionFromBottom = (i - j) + ((ollPositionFromBottom) (view)).ollPositionFromBottom;
            view.ners(null);
        }
    }

    Q()
    {
        this$0 = DayViewPagerAdapter.this;
        super();
    }
}
