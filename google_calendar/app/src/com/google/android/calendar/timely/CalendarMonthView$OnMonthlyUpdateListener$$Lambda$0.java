// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar.timely:
//            CalendarMonthView, MonthData

final class arg._cls4
    implements Runnable
{

    private final ed arg$1;
    private final MonthData arg$2;
    private final int arg$3;
    private final ed arg$4;

    public final void run()
    {
        arg._cls4 _lcls4 = arg$1;
        MonthData monthdata = arg$2;
        int i = arg$3;
        arg._cls4 _lcls4_1 = arg$4;
        if (_lcls4._fld4)
        {
            LogUtils.e(CalendarMonthView.TAG, "onUpdate called after unregistering", new Object[0]);
        } else
        {
            _lcls4._fld4.onUpdate(monthdata, i);
            if (_lcls4.rTagType() == 2)
            {
                _lcls4_1.ed();
                return;
            }
        }
    }

    ( , MonthData monthdata, int i,  1)
    {
        arg$1 = ;
        arg$2 = monthdata;
        arg$3 = i;
        arg$4 = 1;
    }
}
