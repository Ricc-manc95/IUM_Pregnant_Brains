// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import com.google.android.calendar.newevent.CreateNewEventView;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridDayView

final class arg._cls2
    implements Runnable
{

    private final arg._cls2 arg$1;
    private final int arg$2;

    public final void run()
    {
        arg._cls2 _lcls2 = arg$1;
        int i = arg$2;
        CreateNewEventView.setSelectedTime(_lcls2._fld2.getContext(), _lcls2._fld2.julianDay, i);
    }

    ( , int i)
    {
        arg$1 = ;
        arg$2 = i;
    }
}
