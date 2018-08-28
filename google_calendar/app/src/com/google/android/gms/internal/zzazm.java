// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.people.Images;
import com.google.android.gms.people.model.AvatarReference;

public final class zzazm
    implements Images
{

    public zzazm()
    {
    }

    public final PendingResult loadByReference(GoogleApiClient googleapiclient, AvatarReference avatarreference, com.google.android.gms.people.Images.LoadImageOptions loadimageoptions)
    {
        return googleapiclient.zza(new _cls7(googleapiclient, avatarreference, loadimageoptions));
    }

    public final PendingResult loadOwnerAvatar(GoogleApiClient googleapiclient, String s, String s1, int i, int j)
    {
        return googleapiclient.zza(new _cls5(googleapiclient, s, s1, i, 1));
    }

    private class _cls7 extends zza
    {
        private class zza extends com.google.android.gms.people.People.zza
        {

            public final Result zzb(Status status)
            {
                class _cls1
                    implements com.google.android.gms.people.Images.LoadImageResult
                {

                    private final Status zzavV;

                    public final ParcelFileDescriptor getParcelFileDescriptor()
                    {
                        return null;
                    }

                    public final Status getStatus()
                    {
                        return zzavV;
                    }

                    public final void release()
                    {
                    }

                    _cls1(Status status)
                    {
                        zzavV = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            zza(GoogleApiClient googleapiclient)
            {
                super(googleapiclient);
            }
        }


        private final AvatarReference zzcac;
        private final com.google.android.gms.people.Images.LoadImageOptions zzcad;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb)
            throws RemoteException
        {
            com.google.android.gms.common.internal.zzs zzs = ((zzayx)zzb).zza(this, zzcac, zzcad);
            synchronized (super.zzaJZ)
            {
                super.zzaKh = zzs;
            }
            return;
            exception;
            zzb;
            JVM INSTR monitorexit ;
            throw exception;
        }

        _cls7(GoogleApiClient googleapiclient, AvatarReference avatarreference, com.google.android.gms.people.Images.LoadImageOptions loadimageoptions)
        {
            zzcac = avatarreference;
            zzcad = loadimageoptions;
            super(googleapiclient);
        }
    }


    private class _cls5 extends zza
    {

        private final String zzbZB;
        private final int zzbZY;
        private final String zzbZv;
        private final int zzcab;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb)
            throws RemoteException
        {
            com.google.android.gms.common.internal.zzs zzs = ((zzayx)zzb).zzb(this, zzbZv, zzbZB, zzbZY, zzcab);
            synchronized (super.zzaJZ)
            {
                super.zzaKh = zzs;
            }
            return;
            exception;
            zzb;
            JVM INSTR monitorexit ;
            throw exception;
        }

        _cls5(GoogleApiClient googleapiclient, String s, String s1, int i, int j)
        {
            zzbZv = s;
            zzbZB = s1;
            zzbZY = i;
            zzcab = j;
            super(googleapiclient);
        }
    }

}
