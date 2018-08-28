// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.android.calendarcommon2.LogUtils;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar:
//            Rescheduler

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        obj = (Exception)obj;
        LogUtils.wtf(Rescheduler.TAG, ((Throwable) (obj)), "Failed to reschedule the event.", new Object[0]);
        return Boolean.valueOf(false);
    }


    private ()
    {
    }
}
