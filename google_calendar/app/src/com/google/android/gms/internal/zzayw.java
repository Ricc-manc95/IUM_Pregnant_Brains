// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.regex.Pattern;

public final class zzayw
{

    public static final zzayw zzbXn = new zzayw();
    private Pattern zzbXo[];

    private zzayw()
    {
        zzbXo = new Pattern[0];
    }

    public final void zza(String as[], String as1[])
    {
        boolean flag1 = false;
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        if (as.length == as1.length)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_36;
        }
        throw new IllegalArgumentException();
        as;
        this;
        JVM INSTR monitorexit ;
        throw as;
        zzbXo = new Pattern[as.length];
        int i = ((flag1) ? 1 : 0);
_L2:
        if (i >= as.length)
        {
            break; /* Loop/switch isn't completed */
        }
        zzbXo[i] = Pattern.compile(as[i]);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
    }

}
