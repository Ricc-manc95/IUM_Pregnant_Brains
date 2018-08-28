// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Referenced classes of package com.google.android.gms.internal:
//            zzzh, zzzi, zzzg, zzze

public final class zzzf
    implements zzzh
{

    private final zzzi zzaKV;

    public zzzf(zzzi zzzi1)
    {
        zzaKV = zzzi1;
    }

    public final void begin()
    {
        for (Iterator iterator = zzaKV.zzaLC.values().iterator(); iterator.hasNext(); ((com.google.android.gms.common.api.Api.zze)iterator.next()).disconnect()) { }
        zzaKV.zzaKo.zzaLD = Collections.emptySet();
    }

    public final void connect()
    {
        zzzi zzzi1;
        zzzi1 = zzaKV;
        zzzi1.zzaKy.lock();
        zzzi1.zzaLS = new zzze(zzzi1, zzzi1.zzaKD, zzzi1.zzaKF, zzzi1.zzaKH, zzzi1.zzaJk, zzzi1.zzaKy, zzzi1.mContext);
        zzzi1.zzaLS.begin();
        zzzi1.zzaLP.signalAll();
        zzzi1.zzaKy.unlock();
        return;
        Exception exception;
        exception;
        zzzi1.zzaKy.unlock();
        throw exception;
    }

    public final boolean disconnect()
    {
        return true;
    }

    public final void onConnected(Bundle bundle)
    {
    }

    public final void onConnectionSuspended(int i)
    {
    }

    public final zzyq.zza zza(zzyq.zza zza1)
    {
        zzaKV.zzaKo.zzaLw.add(zza1);
        return zza1;
    }

    public final void zza(ConnectionResult connectionresult, Api api, int i)
    {
    }

    public final zzyq.zza zzb(zzyq.zza zza1)
    {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
