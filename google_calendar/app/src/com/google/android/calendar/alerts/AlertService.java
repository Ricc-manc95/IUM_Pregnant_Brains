// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar.alerts:
//            AlertUtils, AlertReceiver, AlertServiceHelper

public class AlertService extends Service
{
    final class ServiceHandler extends Handler
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

        public ServiceHandler(Looper looper)
        {
            this$0 = AlertService.this;
            super(looper);
        }
    }


    private volatile ServiceHandler serviceHandler;
    private volatile Looper serviceLooper;

    public AlertService()
    {
    }

    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void onCreate()
    {
        HandlerThread handlerthread = new HandlerThread("AlertService", 10);
        handlerthread.start();
        serviceLooper = handlerthread.getLooper();
        serviceHandler = new ServiceHandler(serviceLooper);
        AlertUtils.flushOldAlertsFromInternalStorage(getApplication());
    }

    public void onDestroy()
    {
        serviceLooper.quit();
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        Message message = serviceHandler.obtainMessage();
        message.arg1 = j;
        message.obj = intent;
        serviceHandler.sendMessage(message);
        return 3;
    }
}
