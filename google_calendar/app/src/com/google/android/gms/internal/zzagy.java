// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;

public class zzagy
    implements DriveResource
{

    public final DriveId zzaWH;

    public zzagy(DriveId driveid)
    {
        zzaWH = driveid;
    }

    public final PendingResult getMetadata(GoogleApiClient googleapiclient)
    {
        return googleapiclient.zza(new _cls1(googleapiclient, false));
    }

    private class _cls1 extends zzf
    {
        private class zzf extends zzagq
        {

            public final Result zzb(Status status)
            {
                return new zze(status, null);
            }

            zzf(GoogleApiClient googleapiclient)
            {
                super(googleapiclient);
            }

            private class zze
                implements com.google.android.gms.drive.DriveResource.MetadataResult
            {

                private final Status zzahG;
                private final Metadata zzbar;

                public final Metadata getMetadata()
                {
                    return zzbar;
                }

                public final Status getStatus()
                {
                    return zzahG;
                }

                public zze(Status status, Metadata metadata)
                {
                    zzahG = status;
                    zzbar = metadata;
                }
            }

        }


        private final boolean zzbag;
        private final zzagy zzbah;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb)
            throws RemoteException
        {
            ((zzahu)((zzagr)zzb).zzyP()).zza(new zzaho(zzbah.zzaWH, zzbag), new zzd(this));
        }

        _cls1(GoogleApiClient googleapiclient, boolean flag)
        {
            zzbah = zzagy.this;
            zzbag = flag;
            super(googleapiclient);
        }

        private class zzd extends zzafo
        {

            private final zzyq.zzb zzaNb;

            public final void onError(Status status)
                throws RemoteException
            {
                zzaNb.setResult(new zze(status, null));
            }

            public final void zza(zzaiv zzaiv1)
                throws RemoteException
            {
                zzaNb.setResult(new zze(Status.zzaJt, new zzagk(zzaiv1.zzaYE)));
            }

            public zzd(zzyq.zzb zzb)
            {
                zzaNb = zzb;
            }
        }

    }

}
