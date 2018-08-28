// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.pseudonymous.PseudonymousIdToken;

// Referenced classes of package com.google.android.gms.internal:
//            zzys

final class nit> extends nit>
{

    private final mousIdToken zzcfA;

    public final void zza(Status status, PseudonymousIdToken pseudonymousidtoken)
    {
        zzcfA.zzb(new nit>(status, pseudonymousidtoken));
    }

    mousIdToken(mousIdToken mousidtoken)
    {
        zzcfA = mousidtoken;
        super();
    }
}
