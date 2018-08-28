// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tasks;


// Referenced classes of package com.google.android.gms.tasks:
//            zze, Task, OnSuccessListener

final class zzcAa
    implements Runnable
{

    private final Task zzcAa;
    private final zze zzcAi;

    public final void run()
    {
        synchronized (zzcAi.zzrY)
        {
            if (zzcAi.zzcAh != null)
            {
                zzcAi.zzcAh.onSuccess(zzcAa.getResult());
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    essListener(zze zze1, Task task)
    {
        zzcAi = zze1;
        zzcAa = task;
        super();
    }
}
