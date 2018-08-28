// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.util.concurrent.FluentFuture;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyColorMonthView, MonthData

final class this._cls0
    implements ener
{

    public final TimelyColorMonthView this$0;

    public final int getListenerTag()
    {
        return listenerTag;
    }

    public final int getListenerTagType()
    {
        return 7;
    }

    public final void postUpdate$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NKQRREEHK48OBKC4TKIMICCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UT39DLIMOU9FA9GMSPR5CH262T314HAN0P31EHIKCQBED5PMGPB49HKN6T35DPIN4EP9AO______0(MonthData monthdata, int i, edListener edlistener)
    {
        class .Lambda._cls0
            implements Runnable
        {

            private final TimelyColorMonthView._cls1 arg$1;
            private final int arg$2;
            private final MonthData arg$3;
            private final RangedData.UpdateFinishedListener arg$4;

            public final void run()
            {
                TimelyColorMonthView._cls1 _lcls1 = arg$1;
                int j = arg$2;
                MonthData monthdata1 = arg$3;
                RangedData.UpdateFinishedListener updatefinishedlistener = arg$4;
                if (j == _lcls1.this$0.mFirstJulianDay)
                {
                    _lcls1.this$0.onUpdateData(monthdata1);
                    _lcls1.this$0.invalidate();
                }
                updatefinishedlistener.notifyUpdateFinished();
            }

            .Lambda._cls0(int i, MonthData monthdata, RangedData.UpdateFinishedListener updatefinishedlistener)
            {
                arg$1 = TimelyColorMonthView._cls1.this;
                arg$2 = i;
                arg$3 = monthdata;
                arg$4 = updatefinishedlistener;
            }
        }

        monthdata = (FluentFuture)CalendarExecutor.MAIN.submit(new .Lambda._cls0(i, monthdata, edlistener));
    }

    public final void setListenerTag(int i)
    {
        listenerTag = i;
    }

    edListener()
    {
        this$0 = TimelyColorMonthView.this;
        super();
    }
}
