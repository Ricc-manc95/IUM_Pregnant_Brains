// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.Context;
import android.content.Intent;

// Referenced classes of package com.google.android.calendar.alerts:
//            AlertReceiver

final class arg._cls3
    implements Runnable
{

    private final Context arg$1;
    private final Intent arg$2;
    private final android.content.gResult arg$3;

    public final void run()
    {
        AlertReceiver.lambda$onReceive$0$AlertReceiver(arg$1, arg$2, arg$3);
    }

    (Context context, Intent intent, android.content.gResult gresult)
    {
        arg$1 = context;
        arg$2 = intent;
        arg$3 = gresult;
    }
}
