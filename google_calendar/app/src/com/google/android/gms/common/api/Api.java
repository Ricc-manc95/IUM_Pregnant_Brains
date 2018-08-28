// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.api;


public final class Api
{

    public final String mName;
    private final zza zzaIG;
    public final zzf zzaII;

    public Api(String s, zza zza, zzf zzf)
    {
        if (zza == null)
        {
            throw new NullPointerException(String.valueOf("Cannot construct an Api with a null ClientBuilder"));
        }
        if (zzf == null)
        {
            throw new NullPointerException(String.valueOf("Cannot construct an Api with a null ClientKey"));
        } else
        {
            mName = s;
            zzaIG = zza;
            zzaII = zzf;
            return;
        }
    }

    public final zza zzwr()
    {
        boolean flag;
        if (zzaIG != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder"));
        } else
        {
            return zzaIG;
        }
    }
}
