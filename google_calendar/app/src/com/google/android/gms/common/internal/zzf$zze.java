// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzf

public abstract class zzaPI
{

    public Object mListener;
    private final zzf zzaPH;
    public boolean zzaPI;

    public final void unregister()
    {
        this;
        JVM INSTR monitorenter ;
        mListener = null;
        this;
        JVM INSTR monitorexit ;
        synchronized (zzf.zzc(zzaPH))
        {
            zzf.zzc(zzaPH).remove(this);
        }
        return;
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected abstract void zzA(Object obj);

    protected abstract void zzyT();

    public (zzf zzf1, Object obj)
    {
        zzaPH = zzf1;
        super();
        mListener = obj;
        zzaPI = false;
    }
}
