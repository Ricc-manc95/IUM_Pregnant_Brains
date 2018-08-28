// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.calendar.timely:
//            GoogleFeedbackUtils

final class 
    implements ResultCallback
{

    public final void onResult(Result result)
    {
        result = (Status)result;
        GoogleFeedbackUtils.apiClient.disconnect();
        boolean flag;
        if (((Status) (result)).zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            LogUtils.e(GoogleFeedbackUtils.TAG, "Failed to execute feedback request in Google Play Services: %s", new Object[] {
                result
            });
        }
    }

    ()
    {
    }
}
