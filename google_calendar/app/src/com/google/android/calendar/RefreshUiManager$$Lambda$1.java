// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

// Referenced classes of package com.google.android.calendar:
//            RefreshUiManager

final class arg._cls2
    implements Runnable
{

    private final RefreshUiManager arg$1;
    private final boolean arg$2;

    public final void run()
    {
        RefreshUiManager refreshuimanager = arg$1;
        boolean flag = arg$2;
        refreshuimanager.swipeRefreshLayout.announceForAccessibility(refreshuimanager.finishRefreshLabel);
        refreshuimanager.swipeRefreshLayout.setRefreshing(false);
        if (refreshuimanager.swipeRefreshLayout == null || refreshuimanager.swipeRefreshLayout.getContext() == null)
        {
            return;
        }
        int i;
        if (flag)
        {
            i = 0x7f130192;
        } else
        {
            i = 0x7f130190;
        }
        Toast.makeText(refreshuimanager.swipeRefreshLayout.getContext(), i, 0).show();
    }

    (RefreshUiManager refreshuimanager, boolean flag)
    {
        arg$1 = refreshuimanager;
        arg$2 = flag;
    }
}
