// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.internal:
//            zzafo, zzagk, zzaiv

final class zzaNb extends zzafo
{

    private final E zzaNb;

    public final void onError(Status status)
        throws RemoteException
    {
        zzaNb.etResult(new <init>(status, null));
    }

    public final void zza(zzaiv zzaiv1)
        throws RemoteException
    {
        zzaNb.etResult(new <init>(Status.zzaJt, new zzagk(zzaiv1.zzaYE)));
    }

    public ( )
    {
        zzaNb = ;
    }
}
