// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.report;

import android.content.Context;

// Referenced classes of package com.google.android.calendar.timely.report:
//            DebugReportingReceiver

final class arg._cls2
    implements Runnable
{

    private final Context arg$1;
    private final String arg$2;

    public final void run()
    {
        DebugReportingReceiver.lambda$notifyUserAboutStatus$1$DebugReportingReceiver(arg$1, arg$2);
    }

    (Context context, String s)
    {
        arg$1 = context;
        arg$2 = s;
    }
}
