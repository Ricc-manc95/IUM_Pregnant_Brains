// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.allday;

import com.google.android.calendar.timely.MonthData;

// Referenced classes of package com.google.android.calendar.timely.gridviews.allday:
//            AllDayHeaderView

public final class arg._cls4
    implements Runnable
{

    private final AllDayHeaderView arg$1;
    private final MonthData arg$2;
    private final int arg$3;
    private final com.google.android.calendar.timely.stener arg$4;

    public final void run()
    {
        AllDayHeaderView alldayheaderview = arg$1;
        MonthData monthdata = arg$2;
        int i = arg$3;
        com.google.android.calendar.timely.stener stener = arg$4;
        alldayheaderview.onUpdate(monthdata, i);
        stener.notifyUpdateFinished();
    }

    public (AllDayHeaderView alldayheaderview, MonthData monthdata, int i, com.google.android.calendar.timely.stener stener)
    {
        arg$1 = alldayheaderview;
        arg$2 = monthdata;
        arg$3 = i;
        arg$4 = stener;
    }
}
