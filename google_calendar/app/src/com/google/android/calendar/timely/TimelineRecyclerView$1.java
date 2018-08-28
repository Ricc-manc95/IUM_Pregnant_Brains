// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            TimelineRecyclerView, TimelineAdapter

final class this._cls0
    implements Runnable
{

    private final TimelineRecyclerView this$0;

    public final void run()
    {
        aderViewDescriptor aderviewdescriptor = timelineAdapter.getHeaderView(currentItemPosition);
        if (aderviewdescriptor != null)
        {
            announceForAccessibility(aderviewdescriptor.getAnnounceableDescription());
        }
    }

    aderViewDescriptor()
    {
        this$0 = TimelineRecyclerView.this;
        super();
    }
}
