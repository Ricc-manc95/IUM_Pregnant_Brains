// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.concurrent.locks.Lock;

// Referenced classes of package com.google.android.gms.internal:
//            zzze, zzzi

abstract class zzaLl
    implements Runnable
{

    private final zzze zzaLl;

    public void run()
    {
        zzaLl.zzaKy.lock();
        boolean flag = Thread.interrupted();
        if (flag)
        {
            zzaLl.zzaKy.unlock();
            return;
        }
        zzxm();
        zzaLl.zzaKy.unlock();
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        zzzi zzzi1 = zzaLl.zzaKV;
        android.os.Message message = zzzi1.zzaLQ.obtainMessage(2, runtimeexception);
        zzzi1.zzaLQ.sendMessage(message);
        zzaLl.zzaKy.unlock();
        return;
        Exception exception;
        exception;
        zzaLl.zzaKy.unlock();
        throw exception;
    }

    protected abstract void zzxm();

    (zzze zzze1)
    {
        zzaLl = zzze1;
        super();
    }
}
