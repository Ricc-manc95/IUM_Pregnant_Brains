// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

// Referenced classes of package com.google.android.gms.reminders:
//            RemindersListenerService

final class zzcgd extends Handler
{

    private final RemindersListenerService zzcgd;

    public final void handleMessage(Message message)
    {
        zzcgd.onHandleIntentInternal((Intent)message.obj);
        zzcgd.stopSelf(message.arg1);
    }

    public (RemindersListenerService reminderslistenerservice, Looper looper)
    {
        zzcgd = reminderslistenerservice;
        super(looper);
    }
}
