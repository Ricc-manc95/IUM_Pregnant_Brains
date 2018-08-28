// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.dynamite;

import android.content.Context;

final class a
    implements b
{

    public final b.zzb zza(Context context, String s, b.zza zza1)
        throws a
    {
        b.zzb zzb = new b.zzb();
        zzb.zzbgP = zza1.zzB(context, s);
        if (zzb.zzbgP != 0)
        {
            zzb.zzbgQ = zza1.zzb(context, s, false);
        } else
        {
            zzb.zzbgQ = zza1.zzb(context, s, true);
        }
        if (zzb.zzbgP == 0 && zzb.zzbgQ == 0)
        {
            zzb.zzbgR = 0;
            return zzb;
        }
        if (zzb.zzbgQ >= zzb.zzbgP)
        {
            zzb.zzbgR = 1;
            return zzb;
        } else
        {
            zzb.zzbgR = -1;
            return zzb;
        }
    }

    a()
    {
    }
}
