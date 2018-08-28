// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class zzaIv extends zzaIv
{

    private static final WeakReference zzaIv = new WeakReference(null);
    private WeakReference zzaIu;

    final byte[] getBytes()
    {
        this;
        JVM INSTR monitorenter ;
        byte abyte1[] = (byte[])zzaIu.get();
        byte abyte0[];
        abyte0 = abyte1;
        if (abyte1 != null)
        {
            break MISSING_BLOCK_LABEL_36;
        }
        abyte0 = zzwo();
        zzaIu = new WeakReference(abyte0);
        this;
        JVM INSTR monitorexit ;
        return abyte0;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected abstract byte[] zzwo();


    (byte abyte0[])
    {
        super(abyte0);
        zzaIu = zzaIv;
    }
}
