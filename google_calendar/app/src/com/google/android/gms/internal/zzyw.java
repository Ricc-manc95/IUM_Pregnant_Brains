// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.zzd;
import com.google.android.gms.common.internal.zzg;

// Referenced classes of package com.google.android.gms.internal:
//            zzzk, zzyt, zzaah

public final class zzyw extends zzd
{

    private final com.google.android.gms.common.api.Api.zza zzaJk;
    private final com.google.android.gms.common.api.Api.zze zzaKB;
    private final zzyt zzaKC;
    private final zzg zzaKD;

    public zzyw(Context context, Api api, Looper looper, com.google.android.gms.common.api.Api.zze zze, zzyt zzyt1, zzg zzg, com.google.android.gms.common.api.Api.zza zza)
    {
        super(context, api, looper);
        zzaKB = zze;
        zzaKC = zzyt1;
        zzaKD = zzg;
        zzaJk = zza;
        context = zzaIW;
        ((zzzk) (context)).mHandler.sendMessage(((zzzk) (context)).mHandler.obtainMessage(5, this));
    }

    public final com.google.android.gms.common.api.Api.zze buildApiClient(Looper looper, zzzk.zza zza)
    {
        zzaKC.zzaKn = zza;
        return zzaKB;
    }

    public final zzaah createSignInCoordinator(Context context, Handler handler)
    {
        return new zzaah(context, handler, zzaKD, zzaJk);
    }
}
