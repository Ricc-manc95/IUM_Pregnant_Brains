// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v4.view.PagerAdapter;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyMonthPagerAdapter

final class arg._cls1
    implements Runnable
{

    private final TimelyMonthPagerAdapter arg$1;

    public final void run()
    {
        arg$1.notifyDataSetChanged();
    }

    (TimelyMonthPagerAdapter timelymonthpageradapter)
    {
        arg$1 = timelymonthpageradapter;
    }
}
