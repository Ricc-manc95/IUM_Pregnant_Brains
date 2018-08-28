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

final class piClient extends 
{

    private final String zzcbo;

    protected final void zza(com.google.android.gms.common.api.Client client)
        throws RemoteException
    {
        client = (zzazu)client;
        class _cls1 extends zzazt.zza
        {

            private final zzazt._cls12 zzcbA;

            public final void zzdg(Status status)
            {
                zzcbA.zzb(status);
            }

            _cls1()
            {
                zzcbA = zzazt._cls12.this;
                super();
            }
        }

        _cls1 _lcls1 = new _cls1();
        ((zzazs)client.zzyP()).zzb(_lcls1, zzcbo);
    }

    public final Result zzb(Status status)
    {
        return status;
    }

    piClient(GoogleApiClient googleapiclient, String s)
    {
        zzcbo = s;
        super(googleapiclient);
    }
}
