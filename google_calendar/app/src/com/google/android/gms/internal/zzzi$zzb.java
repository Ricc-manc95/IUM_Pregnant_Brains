// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.concurrent.locks.Lock;

// Referenced classes of package com.google.android.gms.internal:
//            zzzi

final class zzaLX extends Handler
{

    private final zzzi zzaLX;

    public final void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 1 2: default 28
    //                   1 63
    //                   2 138;
           goto _L1 _L2 _L3
_L1:
        int i = message.what;
        Log.w("GACStateManager", (new StringBuilder(31)).append("Unknown message id: ").append(i).toString());
        return;
_L2:
        zzaLX zzalx;
        zzalx = (zzaLX)message.obj;
        message = zzaLX;
        ((zzzi) (message)).zzaKy.lock();
        zzzh zzzh;
        zzzh zzzh1;
        zzzh = ((zzzi) (message)).zzaLS;
        zzzh1 = zzalx.zzaLW;
        if (zzzh != zzzh1)
        {
            ((zzzi) (message)).zzaKy.unlock();
            return;
        }
        zzalx.zzxm();
        ((zzzi) (message)).zzaKy.unlock();
        return;
        Exception exception;
        exception;
        ((zzzi) (message)).zzaKy.unlock();
        throw exception;
_L3:
        throw (RuntimeException)message.obj;
    }

    (zzzi zzzi1, Looper looper)
    {
        zzaLX = zzzi1;
        super(looper);
    }
}
