// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.calendar.alerts.HabitsIntentServiceHelper;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar:
//            Rescheduler

final class arg._cls2
    implements Function
{

    private final tionsIntent arg$1;
    private final TimelineGroove arg$2;

    public final Object apply(Object obj)
    {
        arg._cls2 _lcls2 = arg$1;
        TimelineGroove timelinegroove = arg$2;
        if (!((Boolean)obj).booleanValue())
        {
            return _lcls2.cMessage(false);
        } else
        {
            _lcls2.cMessage.context.startService(HabitsIntentServiceHelper.createRefreshNotificationsIntent(_lcls2.tionsIntent.context, timelinegroove.descriptor));
            return new tionsIntent(true, _lcls2.tionsIntent.context.getResources().getString(0x7f130254));
        }
    }

    ( , TimelineGroove timelinegroove)
    {
        arg$1 = ;
        arg$2 = timelinegroove;
    }
}
