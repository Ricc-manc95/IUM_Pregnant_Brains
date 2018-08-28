// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.concurrent.ScheduledExecutorService;

// Referenced classes of package com.firebase.jobdispatcher:
//            GooglePlayReceiver, GooglePlayMessengerCallback, ExecutionDelegator, JobCoder

final class GooglePlayMessageHandler extends Handler
{

    private final GooglePlayReceiver googlePlayReceiver;

    public GooglePlayMessageHandler(Looper looper, GooglePlayReceiver googleplayreceiver)
    {
        super(looper);
        googlePlayReceiver = googleplayreceiver;
    }

    public final void handleMessage(Message message)
    {
        if (message != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        AppOpsManager appopsmanager = (AppOpsManager)googlePlayReceiver.getApplicationContext().getSystemService("appops");
        try
        {
            appopsmanager.checkPackage(message.sendingUid, "com.google.android.gms");
        }
        // Misplaced declaration of an exception variable
        catch (Message message)
        {
            Log.e("FJD.GooglePlayReceiver", "Message was not sent from GCM.");
            return;
        }
        switch (message.what)
        {
        case 3: // '\003'
        default:
            message = String.valueOf(message);
            Log.e("FJD.GooglePlayReceiver", (new StringBuilder(String.valueOf(message).length() + 31)).append("Unrecognized message received: ").append(message).toString());
            return;

        case 4: // '\004'
            break;

        case 1: // '\001'
            Object obj = message.getData();
            message = message.replyTo;
            String s = ((Bundle) (obj)).getString("tag");
            if (message != null && s != null)
            {
                message = GooglePlayReceiver.prepareJob(new GooglePlayMessengerCallback(message, s), ((Bundle) (obj)));
                obj = googlePlayReceiver.getExecutionDelegator();
                if (message != null)
                {
                    ((ExecutionDelegator) (obj)).executorService.execute(new ExecutionDelegator._cls2(((ExecutionDelegator) (obj)), message));
                    return;
                }
            }
            break;

        case 2: // '\002'
            message = GooglePlayReceiver.prefixedCoder.decode(message.getData());
            continue; /* Loop/switch isn't completed */
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (message == null) goto _L1; else goto _L4
_L4:
        ExecutionDelegator.stopJob(message.build(), true);
        return;
    }
}
