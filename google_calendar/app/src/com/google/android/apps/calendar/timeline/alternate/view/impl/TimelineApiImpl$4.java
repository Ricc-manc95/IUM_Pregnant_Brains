// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.support.v7.widget.RecyclerView;
import com.android.calendarcommon2.LogUtils;
import java.util.Set;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            TimelineApiImpl

final class this._cls0
    implements Runnable
{

    private final TimelineApiImpl this$0;

    public final void run()
    {
        if (!pendingDisableClipRequests.remove(this))
        {
            LogUtils.wtf(TimelineApiImpl.TAG, "Clip request cancelled multipled times", new Object[0]);
        }
        if (pendingDisableClipRequests.isEmpty())
        {
            recyclerView.setClipChildren(true);
        }
    }

    ()
    {
        this$0 = TimelineApiImpl.this;
        super();
    }
}
