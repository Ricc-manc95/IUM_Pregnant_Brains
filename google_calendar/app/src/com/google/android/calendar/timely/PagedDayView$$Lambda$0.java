// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            PagedDayView, MonthData

final class arg._cls4
    implements Runnable
{

    private final PagedDayView arg$1;
    private final int arg$2;
    private final MonthData arg$3;
    private final edListener arg$4;

    public final void run()
    {
        PagedDayView pageddayview = arg$1;
        int i = arg$2;
        MonthData monthdata = arg$3;
        edListener edlistener = arg$4;
        if (i - 0x253d8c == pageddayview.position)
        {
            pageddayview.onUpdate(monthdata, i);
        }
        edlistener.notifyUpdateFinished();
    }

    edListener(PagedDayView pageddayview, int i, MonthData monthdata, edListener edlistener)
    {
        arg$1 = pageddayview;
        arg$2 = i;
        arg$3 = monthdata;
        arg$4 = edlistener;
    }
}
