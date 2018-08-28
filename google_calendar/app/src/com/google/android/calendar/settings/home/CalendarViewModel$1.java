// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import com.android.calendarcommon2.LogUtils;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.settings.home:
//            CalendarViewModel

final class 
    implements FutureCallback
{

    public final void onFailure(Throwable throwable)
    {
        LogUtils.wtf(CalendarViewModel.TAG, throwable, "Unable to update calendar", new Object[0]);
    }

    public final volatile void onSuccess(Object obj)
    {
    }

    ()
    {
    }
}
