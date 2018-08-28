// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.report;

import android.os.Bundle;
import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar.timely.report:
//            DebugReportingReceiver

final class ks
    implements com.google.android.gms.common.api.nCallbacks
{

    public final void onConnected(Bundle bundle)
    {
        LogUtils.v(DebugReportingReceiver.TAG, "Feedback API client connected", new Object[0]);
    }

    public final void onConnectionSuspended(int i)
    {
        LogUtils.v(DebugReportingReceiver.TAG, "Feedback API client disconnected: %d", new Object[] {
            Integer.valueOf(i)
        });
    }

    ks()
    {
    }
}
