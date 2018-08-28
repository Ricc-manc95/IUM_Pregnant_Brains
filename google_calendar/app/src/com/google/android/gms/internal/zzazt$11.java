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

    private final String zzcaW;
    private final String zzcbd;
    private final String zzcbo;

    protected final void zza(com.google.android.gms.common.api.Client client)
        throws RemoteException
    {
        client = (zzazu)client;
        class _cls1 extends zzazt.zza
        {

            private final zzazt._cls11 zzcbz;

            public final void zza(Status status, Configurations configurations)
            {
                zzcbz.zzb(new zzazt.zzc(status, configurations));
            }

            _cls1()
            {
                zzcbz = zzazt._cls11.this;
                super();
            }
        }

        _cls1 _lcls1 = new _cls1();
        ((zzazs)client.zzyP()).zza(_lcls1, zzcbd, zzcaW, zzcbo);
    }

    public final Result zzb(Status status)
    {
        return new (status, null);
    }

    piClient(GoogleApiClient googleapiclient, String s, String s1, String s2)
    {
        zzcbd = s;
        zzcaW = s1;
        zzcbo = s2;
        super(googleapiclient);
    }
}
