// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.pseudonymous.PseudonymousIdApi;

public final class zzbbe
    implements PseudonymousIdApi
{

    public zzbbe()
    {
    }

    public final PendingResult getToken(GoogleApiClient googleapiclient)
    {
        return googleapiclient.zza(new _cls2(googleapiclient));
    }

    private class _cls2 extends zzc
    {
        private class zzc extends zzb
        {
            private class zzb extends zzyq.zza
            {

                public zzb(GoogleApiClient googleapiclient)
                {
                    super(PseudonymousId.API, googleapiclient);
                }
            }


            public zzbbc zzcfz;

            protected final Result zzb(Status status)
            {
                return new zzd(status, null);
            }

            zzc(GoogleApiClient googleapiclient)
            {
                super(googleapiclient);
                class _cls1 extends zza
                {
                    private class zza extends zzbbc.zza
                    {

                        public void zza(Status status, PseudonymousIdToken pseudonymousidtoken)
                        {
                            throw new UnsupportedOperationException();
                        }

                        public final void zzdC(Status status)
                        {
                            throw new UnsupportedOperationException();
                        }

                        zza()
                        {
                        }
                    }


                    private final zzc zzcfA;

                    public final void zza(Status status, PseudonymousIdToken pseudonymousidtoken)
                    {
                        zzcfA.zzb(new zzd(status, pseudonymousidtoken));
                    }

                    _cls1()
                    {
                        zzcfA = zzc.this;
                        super();
                    }

                    private class zzd
                        implements com.google.android.gms.pseudonymous.PseudonymousIdApi.PseudonymousIdTokenResult
                    {

                        private final Status zzahG;
                        private final PseudonymousIdToken zzcfB;

                        public final Status getStatus()
                        {
                            return zzahG;
                        }

                        public final PseudonymousIdToken getToken()
                        {
                            return zzcfB;
                        }

                        public zzd(Status status, PseudonymousIdToken pseudonymousidtoken)
                        {
                            zzahG = status;
                            zzcfB = pseudonymousidtoken;
                        }
                    }

                }

                zzcfz = new _cls1();
            }
        }


        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb)
            throws RemoteException
        {
            zzb = (zzbbf)zzb;
            zzbbc zzbbc = zzcfz;
            ((zzbbd)zzb.zzyP()).zza(zzbbc);
        }

        _cls2(GoogleApiClient googleapiclient)
        {
            super(googleapiclient);
        }
    }

}
