// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import com.google.android.calendar.newevent.CreateNewEventView;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridDayView

final class arg._cls1
    implements Runnable
{

    private final GridDayView arg$1;

    public final void run()
    {
        GridDayView griddayview = arg$1;
        if (griddayview.createNewEventView.getParent() == griddayview)
        {
            griddayview.createNewEventView.requestFocus();
        }
    }

    (GridDayView griddayview)
    {
        arg$1 = griddayview;
    }
}
