// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.overflow;

import android.content.Context;
import android.content.DialogInterface;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.SettableFuture;

// Referenced classes of package com.google.android.calendar.newapi.overflow:
//            GuestNotificationDialogUtils

final class arg._cls3
    implements android.content.cationDialogUtils..Lambda._cls4
{

    private final SettableFuture arg$1;
    private final Context arg$2;
    private final String arg$3;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface = arg$1;
        Context context = arg$2;
        String s = arg$3;
        dialoginterface.set(com.google.android.calendar.api.event.BLED);
        GuestNotificationDialogUtils.logUserDecision(context, s, com.google.android.calendar.api.event.BLED);
    }

    (SettableFuture settablefuture, Context context, String s)
    {
        arg$1 = settablefuture;
        arg$2 = context;
        arg$3 = s;
    }
}
