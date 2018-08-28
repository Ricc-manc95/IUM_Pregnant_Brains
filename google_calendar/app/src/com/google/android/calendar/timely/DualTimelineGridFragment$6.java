// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            DualTimelineGridFragment, TimelineRecyclerView

final class this._cls0
    implements linePositionChangedListener
{

    private final DualTimelineGridFragment this$0;

    public final void onTimelinePositionChanged(int i)
    {
        if (list != null)
        {
            list.updateCurrentHeaderPosition(i);
        }
    }

    linePositionChangedListener()
    {
        this$0 = DualTimelineGridFragment.this;
        super();
    }
}
