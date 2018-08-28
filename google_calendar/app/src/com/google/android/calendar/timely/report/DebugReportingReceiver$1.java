// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.report;

import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.common.ConnectionResult;

// Referenced classes of package com.google.android.calendar.timely.report:
//            DebugReportingReceiver

final class dListener
    implements com.google.android.gms.common.api.ionFailedListener
{

    public final void onConnectionFailed(ConnectionResult connectionresult)
    {
        LogUtils.e(DebugReportingReceiver.TAG, "Feedback API client failed to connect: %d", new Object[] {
            Integer.valueOf(connectionresult.zzaEP)
        });
    }

    dListener()
    {
    }
}
