// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;

// Referenced classes of package com.google.android.gms.internal:
//            zzze, zzzi

final class zzaKm
    implements com.google.android.gms.common.internal.t
{

    private final Api zzaGo;
    public final int zzaKm;
    private final WeakReference zzaLm;

    public final void zzg(ConnectionResult connectionresult)
    {
        zzze zzze1;
        boolean flag2;
        flag2 = true;
        zzze1 = (zzze)zzaLm.get();
        if (zzze1 == null)
        {
            return;
        }
        boolean flag;
        if (Looper.myLooper() == zzze1.zzaKV.zzaKo.getLooper())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("onReportServiceBinding must be called on the GoogleApiClient handler thread"));
        }
        zzze1.zzaKy.lock();
        boolean flag3 = zzze1.zzfG(0);
        if (!flag3)
        {
            zzze1.zzaKy.unlock();
            return;
        }
        boolean flag1;
        if (connectionresult.zzaEP == 0)
        {
            flag1 = flag2;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_117;
        }
        zzze1.zzb(connectionresult, zzaGo, zzaKm);
        if (zzze1.zzxn())
        {
            zzze1.zzxo();
        }
        zzze1.zzaKy.unlock();
        return;
        connectionresult;
        zzze1.zzaKy.unlock();
        throw connectionresult;
    }

    public esult(zzze zzze1, Api api, int i)
    {
        zzaLm = new WeakReference(zzze1);
        zzaGo = api;
        zzaKm = i;
    }
}
