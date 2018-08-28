// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.PagedScrollView;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridViewFrame, GridDrawable

public final class val.scrollToTime
    implements Runnable
{

    private final GridViewFrame this$0;
    private final Time val$scrollToTime;

    public final void run()
    {
        float f = getTimeLinePosition(val$scrollToTime);
        GridDrawable griddrawable = gridDrawable;
        int i = griddrawable.intervalHeight;
        i = (int)(f - (float)(griddrawable.gridLineHeight + i));
        ((PagedScrollView)getParent()).scrollTo(0, i);
    }

    public ()
    {
        this$0 = final_gridviewframe;
        val$scrollToTime = Time.this;
        super();
    }
}
