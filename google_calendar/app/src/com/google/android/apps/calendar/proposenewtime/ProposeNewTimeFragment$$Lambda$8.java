// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime;

import android.view.View;
import com.google.android.calendar.timely.PagedScrollView;
import com.google.android.calendar.timely.gridviews.GridDayView;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime:
//            ProposeNewTimeFragment

final class arg._cls1
    implements Runnable
{

    private final ProposeNewTimeFragment arg$1;

    public final void run()
    {
        ProposeNewTimeFragment proposenewtimefragment = arg$1;
        com.google.android.apps.calendar.proposenewtime.grid.views.ProposeNewTimeGridDayView proposenewtimegriddayview = proposenewtimefragment.proposalGrid;
        PagedScrollView pagedscrollview = proposenewtimefragment.mainGridScrollView;
        int i = Math.max(0, proposenewtimegriddayview.getFirstChipCenterYCoordinate(-1) - pagedscrollview.getHeight() / 2);
        proposenewtimefragment.mainGridScrollView.scrollTo(0, i);
    }

    (ProposeNewTimeFragment proposenewtimefragment)
    {
        arg$1 = proposenewtimefragment;
    }
}
