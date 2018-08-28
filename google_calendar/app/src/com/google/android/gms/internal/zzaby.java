// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;

// Referenced classes of package com.google.android.gms.internal:
//            zzabx

public final class zzaby
{

    public static zzaby zzaTJ = new zzaby();
    private zzabx zzaTI;

    public zzaby()
    {
        zzaTI = null;
    }

    public final zzabx zzaS(Context context)
    {
        this;
        JVM INSTR monitorenter ;
        if (zzaTI != null) goto _L2; else goto _L1
_L1:
        if (context.getApplicationContext() != null)
        {
            break MISSING_BLOCK_LABEL_37;
        }
_L3:
        zzaTI = new zzabx(context);
_L2:
        context = zzaTI;
        this;
        JVM INSTR monitorexit ;
        return context;
        context = context.getApplicationContext();
          goto _L3
        context;
        throw context;
    }

}
