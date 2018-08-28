// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;

public final class zzaqe extends zzapr.zza
{

    private final zzyq.zzb zzaNb;

    public zzaqe(zzyq.zzb zzb)
    {
        zzaNb = zzb;
    }

    public final void zzx(Status status)
    {
        zzaNb.setResult(status);
    }
}
