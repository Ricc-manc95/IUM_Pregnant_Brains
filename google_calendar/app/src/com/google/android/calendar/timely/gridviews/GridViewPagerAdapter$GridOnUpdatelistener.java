// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.timely.MonthData;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderView;
import com.google.common.util.concurrent.FluentFuture;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridDayView

public final class allDayView
    implements com.google.android.calendar.timely.elistener
{

    private final AllDayHeaderView allDayView;
    private final GridDayView dayView;
    private int tag;

    public final int getListenerTag()
    {
        return tag;
    }

    public final int getListenerTagType()
    {
        return 5;
    }

    public final void postUpdate$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NKQRREEHK48OBKC4TKIMICCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UT39DLIMOU9FA9GMSPR5CH262T314HAN0P31EHIKCQBED5PMGPB49HKN6T35DPIN4EP9AO______0(MonthData monthdata, int i, final com.google.android.calendar.timely.elistener updateFinishedListener)
    {
        class _cls1
            implements com.google.android.calendar.timely.RangedData.UpdateFinishedListener
        {

            private int counter;
            private final com.google.android.calendar.timely.RangedData.UpdateFinishedListener val$updateFinishedListener;

            public final void notifyUpdateFinished()
            {
                counter = counter - 1;
                if (counter == 0)
                {
                    updateFinishedListener.notifyUpdateFinished();
                }
            }

            _cls1()
            {
                updateFinishedListener = updatefinishedlistener;
                super();
                counter = 2;
            }
        }

        updateFinishedListener = new _cls1();
        if (dayView != null)
        {
            dayView.postUpdate(monthdata, i, updateFinishedListener);
        }
        if (allDayView != null)
        {
            AllDayHeaderView alldayheaderview = allDayView;
            monthdata = (FluentFuture)CalendarExecutor.MAIN.submit(new com.google.android.calendar.timely.gridviews.allday.iew(alldayheaderview, monthdata, i, updateFinishedListener));
        }
    }

    public final void setListenerTag(int i)
    {
        tag = i;
    }

    public _cls1(int i, GridDayView griddayview, AllDayHeaderView alldayheaderview)
    {
        tag = i;
        dayView = griddayview;
        allDayView = alldayheaderview;
    }
}
