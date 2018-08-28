// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            TimelyColorMonthView, MonthData

final class arg._cls4
    implements Runnable
{

    private final .notifyUpdateFinished arg$1;
    private final int arg$2;
    private final MonthData arg$3;
    private final  arg$4;

    public final void run()
    {
        arg._cls4 _lcls4 = arg$1;
        int i = arg$2;
        MonthData monthdata = arg$3;
          = arg$4;
        if (i == _lcls4._fld4.mFirstJulianDay)
        {
            _lcls4.Day.onUpdateData(monthdata);
            _lcls4..invalidate();
        }
        .notifyUpdateFinished();
    }

    ( , int i, MonthData monthdata,  1)
    {
        arg$1 = ;
        arg$2 = i;
        arg$3 = monthdata;
        arg$4 = 1;
    }
}
