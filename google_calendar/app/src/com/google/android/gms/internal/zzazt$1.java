// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzf;

// Referenced classes of package com.google.android.gms.internal:
//            zzazu, zzazs

final class ApiClient extends b
{

    private final String zzcbd;
    private final int zzcbe;
    private final String zzcbf[];
    private final byte zzcbg[];

    protected final void zza(com.google.android.gms.common.api.iClient iclient)
        throws RemoteException
    {
        iclient = (zzazu)iclient;
        class _cls1 extends zzazt.zza
        {

            private final zzazt._cls1 zzcbh;

            public final void zzdd(Status status)
            {
                zzcbh.zzb(status);
            }

            _cls1()
            {
                zzcbh = zzazt._cls1.this;
                super();
            }
        }

        _cls1 _lcls1 = new _cls1();
        ((zzazs)iclient.zzyP()).zza(_lcls1, zzcbd, zzcbe, zzcbf, zzcbg);
    }

    public final Result zzb(Status status)
    {
        return status;
    }

    ApiClient(GoogleApiClient googleapiclient, String s, int i, String as[], byte abyte0[])
    {
        zzcbd = s;
        zzcbe = i;
        zzcbf = as;
        zzcbg = abyte0;
        super(googleapiclient);
    }
}
