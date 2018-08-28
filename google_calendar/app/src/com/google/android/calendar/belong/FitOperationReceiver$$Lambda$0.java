// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.belong;

import android.content.Context;
import com.google.android.calendar.api.event.EventKey;

// Referenced classes of package com.google.android.calendar.belong:
//            FitOperationReceiver

final class arg._cls4
    implements Runnable
{

    private final Context arg$1;
    private final String arg$2;
    private final int arg$3;
    private final EventKey arg$4;

    public final void run()
    {
        FitOperationReceiver.lambda$onReceive$0$FitOperationReceiver(arg$1, arg$2, arg$3, arg$4);
    }

    (Context context, String s, int i, EventKey eventkey)
    {
        arg$1 = context;
        arg$2 = s;
        arg$3 = i;
        arg$4 = eventkey;
    }
}
