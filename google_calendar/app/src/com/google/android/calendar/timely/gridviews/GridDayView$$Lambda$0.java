// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import com.google.android.calendar.timely.MonthData;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridDayView

final class arg._cls4
    implements Runnable
{

    private final GridDayView arg$1;
    private final MonthData arg$2;
    private final int arg$3;
    private final com.google.android.calendar.timely.hedListener arg$4;

    public final void run()
    {
        GridDayView griddayview = arg$1;
        MonthData monthdata = arg$2;
        int i = arg$3;
        com.google.android.calendar.timely.hedListener hedlistener = arg$4;
        griddayview.onUpdate(monthdata, i);
        if (hedlistener != null)
        {
            hedlistener.notifyUpdateFinished();
        }
    }

    r(GridDayView griddayview, MonthData monthdata, int i, com.google.android.calendar.timely.hedListener hedlistener)
    {
        arg$1 = griddayview;
        arg$2 = monthdata;
        arg$3 = i;
        arg$4 = hedlistener;
    }
}
