// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.pseudonymous.PseudonymousIdToken;

// Referenced classes of package com.google.android.gms.internal:
//            zzbbc, zzys

abstract class _cls1 extends _cls1
{

    public zzbbc zzcfz;

    protected final Result zzb(Status status)
    {
        return new <init>(status, null);
    }

    iClient(GoogleApiClient googleapiclient)
    {
        super(googleapiclient);
        class _cls1 extends zzbbe.zza
        {

            private final zzbbe.zzc zzcfA;

            public final void zza(Status status, PseudonymousIdToken pseudonymousidtoken)
            {
                zzcfA.zzb(new zzbbe.zzd(status, pseudonymousidtoken));
            }

            _cls1()
            {
                zzcfA = zzbbe.zzc.this;
                super();
            }
        }

        zzcfz = new _cls1();
    }
}
