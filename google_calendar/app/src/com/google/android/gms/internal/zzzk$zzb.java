// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzr;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            zzzk, zzyn

final class zzaIT
    implements com.google.android.gms.common.internal., 
{

    public final zzyn zzaIT;
    public final com.google.android.gms.common.api.ult zzaKB;
    public zzr zzaLh;
    public final zzzk zzaMr;
    public boolean zzaMu;
    public Set zzalx;

    public final void zzb(zzr zzr, Set set)
    {
        if (zzr == null || set == null)
        {
            Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
            zzi(new ConnectionResult(4));
        } else
        {
            zzaLh = zzr;
            zzalx = set;
            if (zzaMu && zzaLh != null)
            {
                zzaKB.za(zzaLh, zzalx);
                return;
            }
        }
    }

    public final void zzg(ConnectionResult connectionresult)
    {
        class _cls1
            implements Runnable
        {

            private final ConnectionResult zzaMt;
            private final zzzk.zzb zzaMv;

            public final void run()
            {
                boolean flag;
                if (zzaMt.zzaEP == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    zzaMv.zzaMu = true;
                    if (zzaMv.zzaKB.zzpZ())
                    {
                        zzzk.zzb zzb1 = zzaMv;
                        if (zzb1.zzaMu && zzb1.zzaLh != null)
                        {
                            zzb1.zzaKB.zza(zzb1.zzaLh, zzb1.zzalx);
                        }
                        return;
                    } else
                    {
                        zzaMv.zzaKB.zza(null, Collections.emptySet());
                        return;
                    }
                } else
                {
                    ((zzzk.zza)zzzk.zzj(zzaMv.zzaMr).get(zzaMv.zzaIT)).onConnectionFailed(zzaMt);
                    return;
                }
            }

            _cls1(ConnectionResult connectionresult)
            {
                zzaMv = zzzk.zzb.this;
                zzaMt = connectionresult;
                super();
            }
        }

        zzzk.zza(zzaMr).post(new _cls1(connectionresult));
    }

    public final void zzi(ConnectionResult connectionresult)
    {
        esult esult = (esult)zzzk.zzj(zzaMr).get(zzaIT);
        Handler handler = zzzk.zza(esult.zzaMr);
        if (Looper.myLooper() != handler.getLooper())
        {
            throw new IllegalStateException("Must be called on the handler thread");
        } else
        {
            esult.zzaKB.isconnect();
            esult.onConnectionFailed(connectionresult);
            return;
        }
    }

    public (zzzk zzzk1, com.google.android.gms.common.api.ult ult, zzyn zzyn)
    {
        zzaMr = zzzk1;
        super();
        zzaLh = null;
        zzalx = null;
        zzaMu = false;
        zzaKB = ult;
        zzaIT = zzyn;
    }
}
