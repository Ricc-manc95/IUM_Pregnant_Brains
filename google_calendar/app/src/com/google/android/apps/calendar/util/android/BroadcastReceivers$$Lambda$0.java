// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import com.google.android.apps.calendar.util.concurrent.Subscription;

// Referenced classes of package com.google.android.apps.calendar.util.android:
//            BroadcastReceivers

final class arg._cls2
    implements Subscription
{

    private final Context arg$1;
    private final BroadcastReceiver arg$2;

    public final void cancel(boolean flag)
    {
        BroadcastReceivers.lambda$subscribeToBroadcast$0$BroadcastReceivers$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FCDNMST35DPQ2UGJIDTGM8OR1EDQ54PB3CLKNCPBI7DD2ILG_0(arg$1, arg$2);
    }

    (Context context, BroadcastReceiver broadcastreceiver)
    {
        arg$1 = context;
        arg$2 = broadcastreceiver;
    }
}
