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
        zzaKA.zzaKv = connectionresult;
        zzyv.zzb(zzaKA);
        zzaKA.zzaKy.unlock();
        return;
        connectionresult;
        zzaKA.zzaKy.unlock();
        throw connectionresult;
    }

    public final void zze(int i, boolean flag)
    {
        boolean flag1;
        flag1 = true;
        zzaKA.zzaKy.lock();
        if (zzaKA.zzaKx || zzaKA.zzaKw == null) goto _L2; else goto _L1
_L1:
        zzyv zzyv1;
        if (zzaKA.zzaKw.zzaEP != 0)
        {
            flag1 = false;
        }
          goto _L3
_L2:
        zzaKA.zzaKx = false;
        zzyv1 = zzaKA;
        zzyv1.zzaKo.zze(i, flag);
        zzyv1.zzaKw = null;
        zzyv1.zzaKv = null;
        zzaKA.zzaKy.unlock();
        return;
_L4:
        zzaKA.zzaKx = true;
        zzaKA.zzaKq.onConnectionSuspended(i);
        zzaKA.zzaKy.unlock();
        return;
        Exception exception;
        exception;
        zzaKA.zzaKy.unlock();
        throw exception;
_L3:
        if (flag1) goto _L4; else goto _L2
    }

    public final void zzu(Bundle bundle)
    {
        zzaKA.zzaKy.lock();
        zzyv zzyv1 = zzaKA;
        if (zzyv1.zzaKu != null) goto _L2; else goto _L1
_L1:
        zzyv1.zzaKu = bundle;
_L4:
        zzaKA.zzaKv = ConnectionResult.zzaIj;
        zzyv.zzb(zzaKA);
        zzaKA.zzaKy.unlock();
        return;
_L2:
        if (bundle == null) goto _L4; else goto _L3
_L3:
        zzyv1.zzaKu.putAll(bundle);
          goto _L4
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
