// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.util.android:
//            BroadcastReceivers

final class val.consumer extends BroadcastReceiver
{

    private final Consumer val$consumer;
    private final IntentFilter val$filter;

    public final void onReceive(Context context, Intent intent)
    {
        if (!val$filter.matchAction(intent.getAction()))
        {
            LogUtils.wtf(BroadcastReceivers.TAG, "Received an action that is not matched by the filter.", new Object[0]);
            return;
        } else
        {
            val$consumer.accept(intent);
            return;
        }
    }

    ()
    {
        val$filter = intentfilter;
        val$consumer = consumer1;
        super();
    }
}
