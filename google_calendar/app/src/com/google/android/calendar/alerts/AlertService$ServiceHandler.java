// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar.alerts:
//            AlertService, AlertReceiver, AlertServiceHelper

final class this._cls0 extends Handler
{

    private final AlertService this$0;

    public final void handleMessage(Message message)
    {
        if (message.obj instanceof Intent)
        {
            break MISSING_BLOCK_LABEL_56;
        }
        LogUtils.d("AlertService", "Empty or unsupported message received, ignoring.", new Object[0]);
        if (message.obj instanceof Intent)
        {
            AlertReceiver.completeWakefulIntent((Intent)message.obj);
        }
        stopSelfResult(message.arg1);
        return;
        AlertServiceHelper.processRequest(AlertService.this, (Intent)message.obj);
        if (message.obj instanceof Intent)
        {
            AlertReceiver.completeWakefulIntent((Intent)message.obj);
        }
        stopSelfResult(message.arg1);
        return;
        Exception exception;
        exception;
        if (message.obj instanceof Intent)
        {
            AlertReceiver.completeWakefulIntent((Intent)message.obj);
        }
        stopSelfResult(message.arg1);
        throw exception;
    }

    public (Looper looper)
    {
        this$0 = AlertService.this;
        super(looper);
    }
}
