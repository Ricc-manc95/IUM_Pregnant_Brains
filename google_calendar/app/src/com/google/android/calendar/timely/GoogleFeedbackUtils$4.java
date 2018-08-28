// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Bundle;
import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar.timely:
//            GoogleFeedbackUtils

final class ionCallbacks
    implements com.google.android.gms.common.api.tionCallbacks
{

    public final void onConnected(Bundle bundle)
    {
        LogUtils.i(GoogleFeedbackUtils.TAG, "Feedback API client connected", new Object[0]);
    }

    public final void onConnectionSuspended(int i)
    {
        LogUtils.i(GoogleFeedbackUtils.TAG, "Feedback API client disconnected: %d", new Object[] {
            Integer.valueOf(i)
        });
    }

    ionCallbacks()
    {
    }
}
