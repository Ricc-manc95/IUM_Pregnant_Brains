// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Lock;

// Referenced classes of package com.google.android.gms.internal:
//            zzyv, zzzg, zzzi

final class zzaKA
    implements zzaKA
{

    private final zzyv zzaKA;

    public final void zzc(ConnectionResult connectionresult)
    {
        zzaKA.zzaKy.lock();
        zzaKA.zzaKw = connectionresult;
        zzyv.zzb(zzaKA);
        zzaKA.zzaKy.unlock();
        return;
        connectionresult;
        zzaKA.zzaKy.unlock();
        throw connectionresult;
    }

    public final void zze(int i, boolean flag)
    {
        zzaKA.zzaKy.lock();
        if (!zzaKA.zzaKx)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        zzaKA.zzaKx = false;
        zzyv zzyv1 = zzaKA;
        zzyv1.zzaKo.zze(i, flag);
        zzyv1.zzaKw = null;
        zzyv1.zzaKv = null;
        zzaKA.zzaKy.unlock();
        return;
        zzaKA.zzaKx = true;
        zzaKA.zzaKp.onConnectionSuspended(i);
        zzaKA.zzaKy.unlock();
        return;
        Exception exception;
        exception;
        zzaKA.zzaKy.unlock();
        throw exception;
    }

    public final void zzu(Bundle bundle)
    {
        zzaKA.zzaKy.lock();
        zzaKA.zzaKw = ConnectionResult.zzaIj;
        zzyv.zzb(zzaKA);
        zzaKA.zzaKy.unlock();
        return;
        bundle;
        zzaKA.zzaKy.unlock();
        throw bundle;
    }

    esult(zzyv zzyv1)
    {
        zzaKA = zzyv1;
        super();
    }
}
