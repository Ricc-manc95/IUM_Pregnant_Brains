// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers;

import android.content.Intent;
import android.os.Bundle;
import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.feedback.Feedback;

// Referenced classes of package com.google.android.apps.calendar.loggers:
//            SilentFeedbackReceiver

final class val.afterAction
    implements com.google.android.gms.common.api.nCallbacks
{

    private final Runnable val$afterAction;
    private final GoogleApiClient val$googleApiClient;
    private final Intent val$intent;

    public final void onConnected(Bundle bundle)
    {
        LogUtils.d(SilentFeedbackReceiver.TAG, "Sending silent feedback now.", new Object[0]);
        class .Lambda._cls0
            implements ResultCallback
        {

            private final GoogleApiClient arg$1;
            private final Runnable arg$2;

            public final void onResult(Result result)
            {
                result = arg$1;
                Runnable runnable = arg$2;
                if (result.isConnected())
                {
                    result.disconnect();
                }
                runnable.run();
            }

            .Lambda._cls0(GoogleApiClient googleapiclient, Runnable runnable)
            {
                arg$1 = googleapiclient;
                arg$2 = runnable;
            }
        }

        Feedback.silentSendFeedback(val$googleApiClient, SilentFeedbackReceiver.buildCrashOptions(val$intent)).setResultCallback(new .Lambda._cls0(val$googleApiClient, val$afterAction));
    }

    public final void onConnectionSuspended(int i)
    {
    }

    .Lambda._cls0()
    {
        val$googleApiClient = googleapiclient;
        val$intent = intent1;
        val$afterAction = runnable;
        super();
    }
}
