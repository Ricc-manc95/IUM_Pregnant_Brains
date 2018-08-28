// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.View;
import android.view.ViewTreeObserver;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineRecyclerView, TimelyDayView, TimelyDayHeaderView

final class this._cls0
    implements android.view.rawListener
{

    private final TimelineRecyclerView this$0;

    public final boolean onPreDraw()
    {
        ViewTreeObserver viewtreeobserver = getViewTreeObserver();
        if (!viewtreeobserver.isAlive())
        {
            return true;
        }
        View view = getChildAt(0);
        if (view instanceof TimelyDayView)
        {
            TimelyDayView timelydayview = (TimelyDayView)view;
            if (timelydayview.dayHeaderView.isToday)
            {
                boolean flag;
                if (!timelydayview.hasItems && !timelydayview.mDataLoaded)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag && view.getTop() == 0 && agendaScrollOffsetNow != 0)
                {
                    return false;
                }
            }
        }
        viewtreeobserver.removeOnPreDrawListener(this);
        return true;
    }

    ()
    {
        this$0 = TimelineRecyclerView.this;
        super();
    }
}
