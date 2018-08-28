// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.util.concurrent.FluentFuture;

// Referenced classes of package com.google.android.calendar.timely:
//            CalendarMonthView, MonthData

public abstract class this._cls0
    implements this._cls0
{

    private int dataListenerTag;
    public boolean disabled;
    public final CalendarMonthView this$0;

    public final int getListenerTag()
    {
        return dataListenerTag;
    }

    public final void postUpdate$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NKQRREEHK48OBKC4TKIMICCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UT39DLIMOU9FA9GMSPR5CH262T314HAN0P31EHIKCQBED5PMGPB49HKN6T35DPIN4EP9AO______0(MonthData monthdata, int i, dataListenerTag datalistenertag)
    {
        if (getListenerTagType() != 2)
        {
            datalistenertag.dateFinished();
        }
        class .Lambda._cls0
            implements Runnable
        {

            private final CalendarMonthView.OnMonthlyUpdateListener arg$1;
            private final MonthData arg$2;
            private final int arg$3;
            private final RangedData.UpdateFinishedListener arg$4;

            public final void run()
            {
                CalendarMonthView.OnMonthlyUpdateListener onmonthlyupdatelistener = arg$1;
                MonthData monthdata1 = arg$2;
                int j = arg$3;
                RangedData.UpdateFinishedListener updatefinishedlistener = arg$4;
                if (onmonthlyupdatelistener.disabled)
                {
                    LogUtils.e(CalendarMonthView.TAG, "onUpdate called after unregistering", new Object[0]);
                } else
                {
                    onmonthlyupdatelistener.this$0.onUpdate(monthdata1, j);
                    if (onmonthlyupdatelistener.getListenerTagType() == 2)
                    {
                        updatefinishedlistener.notifyUpdateFinished();
                        return;
                    }
                }
            }

            .Lambda._cls0(MonthData monthdata, int i, RangedData.UpdateFinishedListener updatefinishedlistener)
            {
                arg$1 = CalendarMonthView.OnMonthlyUpdateListener.this;
                arg$2 = monthdata;
                arg$3 = i;
                arg$4 = updatefinishedlistener;
            }
        }

        monthdata = (FluentFuture)CalendarExecutor.MAIN.submit(new .Lambda._cls0(monthdata, i, datalistenertag));
    }

    public void setListenerTag(int i)
    {
        dataListenerTag = i;
    }

    protected .Lambda._cls0()
    {
        this$0 = CalendarMonthView.this;
        super();
    }
}
