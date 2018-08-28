// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            DualTimelineGridFragment, TimelineRecyclerView, TimelyDayViewPager, OnTimelineModeChangedListener

final class this._cls0
    implements Runnable
{

    private final DualTimelineGridFragment this$0;

    public final void run()
    {
        list.setEnabled(true);
        dayPager.setEnabled(true);
        if (pendingModeChangePosition != -1)
        {
            int i = pendingModeChangePosition;
            pendingModeChangePosition = -1;
            timelineModeChangedListener.onModeChanged(i);
        }
    }

    er()
    {
        this$0 = DualTimelineGridFragment.this;
        super();
    }
}
