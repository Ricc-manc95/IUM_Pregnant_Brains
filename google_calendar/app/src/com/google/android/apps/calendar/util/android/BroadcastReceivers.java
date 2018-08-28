// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.apps.calendar.util.function.Consumer;

public class BroadcastReceivers
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/util/android/BroadcastReceivers);

    public BroadcastReceivers()
    {
    }

    static final void lambda$subscribeToBroadcast$0$BroadcastReceivers$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FCDNMST35DPQ2UGJIDTGM8OR1EDQ54PB3CLKNCPBI7DD2ILG_0(Context context, BroadcastReceiver broadcastreceiver)
    {
        context.unregisterReceiver(broadcastreceiver);
    }

    public static Subscription subscribeToBroadcast(Context context, final IntentFilter filter, final Consumer consumer)
    {
        consumer = new _cls1();
        context.registerReceiver(consumer, filter);
        class .Lambda._cls0
            implements Subscription
        {

            private final Context arg$1;
            private final BroadcastReceiver arg$2;

            public final void cancel(boolean flag)
            {
                BroadcastReceivers.lambda$subscribeToBroadcast$0$BroadcastReceivers$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FCDNMST35DPQ2UGJIDTGM8OR1EDQ54PB3CLKNCPBI7DD2ILG_0(arg$1, arg$2);
            }

            .Lambda._cls0(Context context, BroadcastReceiver broadcastreceiver)
            {
                arg$1 = context;
                arg$2 = broadcastreceiver;
            }
        }

        return new .Lambda._cls0(context, consumer);
    }


    private class _cls1 extends BroadcastReceiver
    {

        private final Consumer val$consumer;
        private final IntentFilter val$filter;

        public final void onReceive(Context context, Intent intent)
        {
            if (!filter.matchAction(intent.getAction()))
            {
                LogUtils.wtf(BroadcastReceivers.TAG, "Received an action that is not matched by the filter.", new Object[0]);
                return;
            } else
            {
                consumer.accept(intent);
                return;
            }
        }

        _cls1()
        {
            filter = intentfilter;
            consumer = consumer1;
            super();
        }
    }

}
