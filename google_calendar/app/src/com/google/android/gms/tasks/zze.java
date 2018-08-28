// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.gms.tasks:
//            zzf, Task, OnSuccessListener

final class zze
    implements zzf
{

    private final Executor zzahT;
    public OnSuccessListener zzcAh;
    public final Object zzrY = new Object();

    public zze(Executor executor, OnSuccessListener onsuccesslistener)
    {
        zzahT = executor;
        zzcAh = onsuccesslistener;
    }

    public final void onComplete(Task task)
    {
label0:
        {
            if (!task.isSuccessful())
            {
                break MISSING_BLOCK_LABEL_50;
            }
            synchronized (zzrY)
            {
                if (zzcAh != null)
                {
                    break label0;
                }
            }
            return;
        }
        obj;
        JVM INSTR monitorexit ;
        zzahT.execute(new _cls1(task));
        return;
        task;
        obj;
        JVM INSTR monitorexit ;
        throw task;
    }

    private class _cls1
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

        _cls1(Task task)
        {
            zzcAi = zze.this;
            zzcAa = task;
            super();
        }
    }

}
