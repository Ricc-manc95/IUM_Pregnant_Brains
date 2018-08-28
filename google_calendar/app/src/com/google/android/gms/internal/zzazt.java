// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.phenotype.PhenotypeApi;

public final class zzazt
    implements PhenotypeApi
{

    public zzazt()
    {
    }

    public final PendingResult commitToConfiguration(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.zza(new _cls12(googleapiclient, s));
    }

    public final PendingResult getConfigurationSnapshot(GoogleApiClient googleapiclient, String s, String s1)
    {
        return googleapiclient.zza(new _cls11(googleapiclient, s, s1, null));
    }

    public final PendingResult getConfigurationSnapshot(GoogleApiClient googleapiclient, String s, String s1, String s2)
    {
        return googleapiclient.zza(new _cls11(googleapiclient, s, s1, s2));
    }

    public final PendingResult register(GoogleApiClient googleapiclient, String s, int i, String as[], byte abyte0[])
    {
        return googleapiclient.zza(new _cls1(googleapiclient, s, i, as, abyte0));
    }

    private class _cls12 extends zzb
    {
        private class zzb extends zzyq.zza
        {

            public zzb(GoogleApiClient googleapiclient)
            {
                super(Phenotype.API, googleapiclient);
            }
        }


        private final String zzcbo;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
            throws RemoteException
        {
            zzb1 = (zzazu)zzb1;
            class _cls1 extends zza
            {
                private class zza extends zzazr.zza
                {

                    public void zza(Status status, Configurations configurations)
                    {
                        throw new UnsupportedOperationException();
                    }

                    public final void zza(Status status, DogfoodsToken dogfoodstoken)
                    {
                        throw new UnsupportedOperationException();
                    }

                    public final void zza(Status status, ExperimentTokens experimenttokens)
                    {
                        throw new UnsupportedOperationException();
                    }

                    public final void zza(Status status, Flag flag)
                    {
                        throw new UnsupportedOperationException();
                    }

                    public final void zza(Status status, FlagOverrides flagoverrides)
                    {
                        throw new UnsupportedOperationException();
                    }

                    public final void zzb(Status status, Configurations configurations)
                    {
                        throw new UnsupportedOperationException();
                    }

                    public void zzdd(Status status)
                    {
                        throw new UnsupportedOperationException();
                    }

                    public final void zzde(Status status)
                    {
                        throw new UnsupportedOperationException();
                    }

                    public final void zzdf(Status status)
                    {
                        throw new UnsupportedOperationException();
                    }

                    public void zzdg(Status status)
                    {
                        throw new UnsupportedOperationException();
                    }

                    public final void zzdh(Status status)
                    {
                        throw new UnsupportedOperationException();
                    }

                    public final void zzdi(Status status)
                    {
                        throw new UnsupportedOperationException();
                    }

                    public final void zzdj(Status status)
                    {
                        throw new UnsupportedOperationException();
                    }

                    public zza()
                    {
                    }
                }


                private final _cls12 zzcbA;

                public final void zzdg(Status status)
                {
                    zzcbA.zzb(status);
                }

                _cls1()
                {
                    zzcbA = _cls12.this;
                    super();
                }
            }

            _cls1 _lcls1 = new _cls1();
            ((zzazs)zzb1.zzyP()).zzb(_lcls1, zzcbo);
        }

        public final Result zzb(Status status)
        {
            return status;
        }

        _cls12(GoogleApiClient googleapiclient, String s)
        {
            zzcbo = s;
            super(googleapiclient);
        }
    }


    private class _cls11 extends zzb
    {

        private final String zzcaW;
        private final String zzcbd;
        private final String zzcbo;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
            throws RemoteException
        {
            zzb1 = (zzazu)zzb1;
            class _cls1 extends zza
            {

                private final _cls11 zzcbz;

                public final void zza(Status status, Configurations configurations)
                {
                    zzcbz.zzb(new zzc(status, configurations));
                }

                _cls1()
                {
                    zzcbz = _cls11.this;
                    super();
                }

                private class zzc
                    implements com.google.android.gms.phenotype.PhenotypeApi.ConfigurationsResult
                {

                    private final Status zzahG;
                    private final Configurations zzcbH;

                    public final Configurations getConfigurations()
                    {
                        return zzcbH;
                    }

                    public final Status getStatus()
                    {
                        return zzahG;
                    }

                    public zzc(Status status, Configurations configurations)
                    {
                        zzahG = status;
                        zzcbH = configurations;
                    }
                }

            }

            _cls1 _lcls1 = new _cls1();
            ((zzazs)zzb1.zzyP()).zza(_lcls1, zzcbd, zzcaW, zzcbo);
        }

        public final Result zzb(Status status)
        {
            return new zzc(status, null);
        }

        _cls11(GoogleApiClient googleapiclient, String s, String s1, String s2)
        {
            zzcbd = s;
            zzcaW = s1;
            zzcbo = s2;
            super(googleapiclient);
        }
    }


    private class _cls1 extends zzb
    {

        private final String zzcbd;
        private final int zzcbe;
        private final String zzcbf[];
        private final byte zzcbg[];

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
            throws RemoteException
        {
            zzb1 = (zzazu)zzb1;
            class _cls1 extends zza
            {

                private final _cls1 zzcbh;

                public final void zzdd(Status status)
                {
                    zzcbh.zzb(status);
                }

                _cls1()
                {
                    zzcbh = _cls1.this;
                    super();
                }
            }

            _cls1 _lcls1 = new _cls1();
            ((zzazs)zzb1.zzyP()).zza(_lcls1, zzcbd, zzcbe, zzcbf, zzcbg);
        }

        public final Result zzb(Status status)
        {
            return status;
        }

        _cls1(GoogleApiClient googleapiclient, String s, int i, String as[], byte abyte0[])
        {
            zzcbd = s;
            zzcbe = i;
            zzcbf = as;
            zzcbg = abyte0;
            super(googleapiclient);
        }
    }

}
