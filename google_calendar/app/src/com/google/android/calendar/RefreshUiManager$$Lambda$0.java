// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.support.v4.widget.SwipeRefreshLayout;

// Referenced classes of package com.google.android.calendar:
//            RefreshUiManager

final class arg._cls1
    implements Runnable
{

    private final RefreshUiManager arg$1;

    public final void run()
    {
        RefreshUiManager refreshuimanager = arg$1;
        refreshuimanager.swipeRefreshLayout.announceForAccessibility(refreshuimanager.startRefreshLabel);
        refreshuimanager.swipeRefreshLayout.setRefreshing(true);
    }

    (RefreshUiManager refreshuimanager)
    {
        arg$1 = refreshuimanager;
    }
}
