// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tasks;

import java.util.ArrayDeque;
import java.util.Queue;

// Referenced classes of package com.google.android.gms.tasks:
//            zzf, Task

final class zzg
{

    private Queue zzcAj;
    private boolean zzcAk;
    private final Object zzrY = new Object();

    zzg()
    {
    }

    public final void zza(Task task)
    {
label0:
        {
            synchronized (zzrY)
            {
                if (zzcAj != null && !zzcAk)
                {
                    break label0;
                }
            }
            return;
        }
        zzcAk = true;
        obj;
        JVM INSTR monitorexit ;
_L2:
        obj = zzrY;
        obj;
        JVM INSTR monitorenter ;
        zzf zzf1 = (zzf)zzcAj.poll();
        if (zzf1 != null)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        zzcAk = false;
        obj;
        JVM INSTR monitorexit ;
        return;
        task;
        obj;
        JVM INSTR monitorexit ;
        throw task;
        task;
        obj;
        JVM INSTR monitorexit ;
        throw task;
        obj;
        JVM INSTR monitorexit ;
        zzf1.onComplete(task);
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final void zza(zzf zzf1)
    {
        synchronized (zzrY)
        {
            if (zzcAj == null)
            {
                zzcAj = new ArrayDeque();
            }
            zzcAj.add(zzf1);
        }
        return;
        zzf1;
        obj;
        JVM INSTR monitorexit ;
        throw zzf1;
    }
}
