// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers;

import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.common.ConnectionResult;

// Referenced classes of package com.google.android.apps.calendar.loggers:
//            SilentFeedbackReceiver

final class arg._cls1
    implements com.google.android.gms.common.api.dListener
{

    private final Runnable arg$1;

    public final void onConnectionFailed(ConnectionResult connectionresult)
    {
        Runnable runnable = arg$1;
        LogUtils.e(SilentFeedbackReceiver.TAG, "GoogleApiClient silent feedback connection failed with result: %d", new Object[] {
            Integer.valueOf(connectionresult.zzaEP)
        });
        runnable.run();
    }

    er(Runnable runnable)
    {
        arg$1 = runnable;
    }
}
