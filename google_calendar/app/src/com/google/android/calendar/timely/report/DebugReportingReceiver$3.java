// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.report;

import android.content.Context;
import android.content.Intent;
import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.calendar.timely.report:
//            DebugReportingReceiver

final class val.fileName
    implements ResultCallback
{

    private final DebugReportingReceiver this$0;
    private final Context val$context;
    private final String val$fileName;

    public final void onResult(Result result)
    {
        result = (Status)result;
        apiClient.disconnect();
        boolean flag;
        if (((Status) (result)).zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            val$context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
            LogUtils.v(DebugReportingReceiver.TAG, "Sending Feedback: Success", new Object[0]);
            result = DebugReportingReceiver.this;
            DebugReportingReceiver.deleteReportFile(val$context, val$fileName);
            return;
        } else
        {
            LogUtils.w(DebugReportingReceiver.TAG, "Sending Feedback: %s", new Object[] {
                ((Status) (result)).zzaIk
            });
            return;
        }
    }

    ()
    {
        this$0 = final_debugreportingreceiver;
        val$context = context1;
        val$fileName = String.this;
        super();
    }
}
