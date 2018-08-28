// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.people.model.AvatarReference;

// Referenced classes of package com.google.android.gms.internal:
//            zzayx, zzys

final class ApiClient extends a
{

    private final AvatarReference zzcac;
    private final com.google.android.gms.people.oadImageOptions zzcad;

    protected final void zza(com.google.android.gms.common.api.iClient iclient)
        throws RemoteException
    {
        com.google.android.gms.common.internal.zzs zzs = ((zzayx)iclient).zza(this, zzcac, zzcad);
        synchronized (super.zzaJZ)
        {
            super.zzaKh = zzs;
        }
        return;
        exception;
        iclient;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ApiClient(GoogleApiClient googleapiclient, AvatarReference avatarreference, com.google.android.gms.people.oadImageOptions oadimageoptions)
    {
        zzcac = avatarreference;
        zzcad = oadimageoptions;
        super(googleapiclient);
    }
}
