// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tasks;


// Referenced classes of package com.google.android.gms.tasks:
//            zzd, Task, OnFailureListener

final class zzcAa
    implements Runnable
{

    private final Task zzcAa;
    private final zzd zzcAg;

    public final void run()
    {
        synchronized (zzcAg.zzrY)
        {
            if (zzcAg.zzcAf != null)
            {
                zzcAg.zzcAf.onFailure(zzcAa.getException());
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ureListener(zzd zzd1, Task task)
    {
        zzcAg = zzd1;
        zzcAa = task;
        super();
    }
}
