// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.gms.tasks:
//            zzf, Task, OnFailureListener

final class zzd
    implements zzf
{

    private final Executor zzahT;
    public OnFailureListener zzcAf;
    public final Object zzrY = new Object();

    public zzd(Executor executor, OnFailureListener onfailurelistener)
    {
        zzahT = executor;
        zzcAf = onfailurelistener;
    }

    public final void onComplete(Task task)
    {
label0:
        {
            if (task.isSuccessful())
            {
                break MISSING_BLOCK_LABEL_50;
            }
            synchronized (zzrY)
            {
                if (zzcAf != null)
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

        _cls1(Task task)
        {
            zzcAg = zzd.this;
            zzcAa = task;
            super();
        }
    }

}
