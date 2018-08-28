// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

// Referenced classes of package com.google.android.gms.internal:
//            zzzg

final class zzaLK extends Handler
{

    private final zzzg zzaLK;

    public final void handleMessage(Message message)
    {
        switch (message.what)
        {
        default:
            int i = message.what;
            Log.w("GoogleApiClientImpl", (new StringBuilder(31)).append("Unknown message id: ").append(i).toString());
            return;

        case 1: // '\001'
            zzzg.zzb(zzaLK);
            return;

        case 2: // '\002'
            zzzg.zza(zzaLK);
            break;
        }
    }

    (zzzg zzzg1, Looper looper)
    {
        zzaLK = zzzg1;
        super(looper);
    }
}
