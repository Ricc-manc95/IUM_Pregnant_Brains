// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.flags;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.libraries.performance.primes.PrimesLog;
import com.google.android.libraries.performance.primes.Shutdown;
import com.google.android.libraries.performance.primes.Supplier;
import java.util.concurrent.ScheduledExecutorService;

final class executorServiceSupplier extends BroadcastReceiver
{

    private final Supplier executorServiceSupplier;
    public final Shutdown shutdown;
    public final Supplier shutdownFlag;

    public final void onReceive(Context context, Intent intent)
    {
        PrimesLog.log(3, "PrimesShutdown", "BroadcastReceiver: action = %s", new Object[] {
            intent.getAction()
        });
        if (shutdown.shutdown)
        {
            context.unregisterReceiver(this);
        } else
        if ("com.google.gservices.intent.action.GSERVICES_CHANGED".equals(intent.getAction()) && executorServiceSupplier != null)
        {
            context = (ScheduledExecutorService)executorServiceSupplier.get();
            if (context != null)
            {
                class _cls1
                    implements Runnable
                {

                    private final PrimesShutdown.GServicesBroadcastReceiver this$0;

                    public final void run()
                    {
                        shutdown.updateShutdownFlag(shutdownFlag);
                    }

            _cls1()
            {
                this$0 = PrimesShutdown.GServicesBroadcastReceiver.this;
                super();
            }
                }

                context.submit(new _cls1());
                return;
            }
        }
    }

    _cls1(Shutdown shutdown1, Supplier supplier, Supplier supplier1)
    {
        shutdown = shutdown1;
        shutdownFlag = supplier;
        executorServiceSupplier = supplier1;
    }
}
