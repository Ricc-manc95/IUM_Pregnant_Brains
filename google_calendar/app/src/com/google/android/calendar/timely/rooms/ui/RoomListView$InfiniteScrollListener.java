// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.os.SystemClock;
import android.widget.AbsListView;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui:
//            RoomListView

final class lastNextPageRequest
    implements android.widget.tView.InfiniteScrollListener
{

    public boolean enabled;
    private long lastNextPageRequest;
    private final RoomListView this$0;

    public final void onScroll(AbsListView abslistview, int i, int j, int k)
    {
        long l;
        if (enabled && listener != null && k - i - j <= 3)
        {
            if ((l = SystemClock.uptimeMillis()) - lastNextPageRequest > 500L)
            {
                listener.ested();
                lastNextPageRequest = l;
                return;
            }
        }
    }

    public final void onScrollStateChanged(AbsListView abslistview, int i)
    {
    }

    ()
    {
        this$0 = RoomListView.this;
        super();
        enabled = false;
        lastNextPageRequest = 0L;
    }
}
