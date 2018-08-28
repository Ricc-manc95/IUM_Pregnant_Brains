// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import com.android.calendarcommon2.LogUtils;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.alerts:
//            TasksBroadcastReceiver

final class val.action
    implements FutureCallback
{

    private final String val$action;
    private final android.content.Result val$result;

    public final void onFailure(Throwable throwable)
    {
        val$result.finish();
        LogUtils.e(TasksBroadcastReceiver.TAG, throwable, "Failed to handle %s.", new Object[] {
            val$action
        });
    }

    public final void onSuccess(Object obj)
    {
        val$result.finish();
    }

    A()
    {
        val$result = result1;
        val$action = s;
        super();
    }
}
