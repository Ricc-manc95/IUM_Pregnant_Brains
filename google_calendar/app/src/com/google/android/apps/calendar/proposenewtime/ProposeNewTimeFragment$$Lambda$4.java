// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime;

import android.view.ViewGroup;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderArrow;
import com.google.android.calendar.timely.gridviews.attendees.AttendeeInfoLayout;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime:
//            ProposeNewTimeFragment

final class arg._cls3
    implements com.google.android.calendar.timely.gridviews.allday.ementChangedListener
{

    private final AllDayHeaderArrow arg$1;
    private final AttendeeInfoLayout arg$2;
    private final ViewGroup arg$3;

    public final void onMeasurementChanged(int i, int j)
    {
        ProposeNewTimeFragment.lambda$onCreateView$3$ProposeNewTimeFragment(arg$1, arg$2, arg$3, i, j);
    }

    asurementChangedListener(AllDayHeaderArrow alldayheaderarrow, AttendeeInfoLayout attendeeinfolayout, ViewGroup viewgroup)
    {
        arg$1 = alldayheaderarrow;
        arg$2 = attendeeinfolayout;
        arg$3 = viewgroup;
    }
}
