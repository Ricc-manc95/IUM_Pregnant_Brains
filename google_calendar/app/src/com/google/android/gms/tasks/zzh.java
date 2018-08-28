// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.gms.tasks:
//            Task, zzg, zzd, zze, 
//            RuntimeExecutionException, OnFailureListener, OnSuccessListener

public final class zzh extends Task
{

    public Object zzbxl;
    public final zzg zzcAn = new zzg();
    public boolean zzcAo;
    public Exception zzcAp;
    public final Object zzrY = new Object();

    zzh()
    {
    }

    private final void zzYT()
    {
label0:
        {
            synchronized (zzrY)
            {
                if (zzcAo)
                {
                    break label0;
                }
            }
            return;
        }
        obj;
        JVM INSTR monitorexit ;
        zzcAn.zza(this);
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final Task addOnFailureListener(Executor executor, OnFailureListener onfailurelistener)
    {
        zzcAn.zza(new zzd(executor, onfailurelistener));
        zzYT();
        return this;
    }

    public final Task addOnSuccessListener(Executor executor, OnSuccessListener onsuccesslistener)
    {
        zzcAn.zza(new zze(executor, onsuccesslistener));
        zzYT();
        return this;
    }

    public final Exception getException()
    {
        Exception exception;
        synchronized (zzrY)
        {
            exception = zzcAp;
        }
        return exception;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public final Object getResult()
    {
        Object obj = zzrY;
        obj;
        JVM INSTR monitorenter ;
        if (!zzcAo)
        {
            throw new IllegalStateException(String.valueOf("Task is not yet complete"));
        }
        break MISSING_BLOCK_LABEL_32;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        Object obj1;
        if (zzcAp != null)
        {
            throw new RuntimeExecutionException(zzcAp);
        }
        obj1 = zzbxl;
        obj;
        JVM INSTR monitorexit ;
        return obj1;
    }

    public final boolean isSuccessful()
    {
        Object obj = zzrY;
        obj;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag;
        if (zzcAo && zzcAp == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj;
        JVM INSTR monitorexit ;
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void setException(Exception exception)
    {
        boolean flag;
        flag = true;
        if (exception == null)
        {
            throw new NullPointerException(String.valueOf("Exception must not be null"));
        }
        Object obj = zzrY;
        obj;
        JVM INSTR monitorenter ;
        if (zzcAo)
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        throw new IllegalStateException(String.valueOf("Task is already complete"));
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        zzcAo = true;
        zzcAp = exception;
        obj;
        JVM INSTR monitorexit ;
        zzcAn.zza(this);
        return;
    }

    public final void setResult(Object obj)
    {
        boolean flag = true;
        Object obj1 = zzrY;
        obj1;
        JVM INSTR monitorenter ;
        if (zzcAo)
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_43;
        }
        throw new IllegalStateException(String.valueOf("Task is already complete"));
        obj;
        obj1;
        JVM INSTR monitorexit ;
        throw obj;
        zzcAo = true;
        zzbxl = obj;
        obj1;
        JVM INSTR monitorexit ;
        zzcAn.zza(this);
        return;
    }

    public final boolean trySetException(Exception exception)
    {
label0:
        {
            if (exception == null)
            {
                throw new NullPointerException(String.valueOf("Exception must not be null"));
            }
            synchronized (zzrY)
            {
                if (!zzcAo)
                {
                    break label0;
                }
            }
            return false;
        }
        zzcAo = true;
        zzcAp = exception;
        obj;
        JVM INSTR monitorexit ;
        zzcAn.zza(this);
        return true;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
