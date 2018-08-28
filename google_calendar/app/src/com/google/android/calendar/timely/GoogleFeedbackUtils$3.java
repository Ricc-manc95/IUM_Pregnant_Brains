// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.common.ConnectionResult;

// Referenced classes of package com.google.android.calendar.timely:
//            GoogleFeedbackUtils

final class ctionFailedListener
    implements com.google.android.gms.common.api.ectionFailedListener
{

    public final void onConnectionFailed(ConnectionResult connectionresult)
    {
        LogUtils.e(GoogleFeedbackUtils.TAG, "Feedback API client failed to connect: %d", new Object[] {
            Integer.valueOf(connectionresult.zzaEP)
        });
    }

    ctionFailedListener()
    {
    }
}
