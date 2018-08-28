// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.people;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzawr;
import com.google.android.gms.internal.zzazh;
import com.google.android.gms.internal.zzazi;
import com.google.android.gms.internal.zzazj;
import com.google.android.gms.internal.zzazk;
import com.google.android.gms.internal.zzazl;
import com.google.android.gms.internal.zzazm;
import com.google.android.gms.internal.zzazn;
import com.google.android.gms.internal.zzazo;
import com.google.android.gms.internal.zzazp;
import com.google.android.gms.internal.zzazq;

// Referenced classes of package com.google.android.gms.people:
//            Autocomplete, Images

public final class People
{

    public static final Api API_1P;
    public static final Autocomplete AutocompleteApi = new zzazh();
    public static final Images ImageApi = new zzazm();
    private static final com.google.android.gms.common.api.Api.zzf zzbTm;
    private static final com.google.android.gms.common.api.Api.zza zzbmK;

    static 
    {
        zzbTm = new com.google.android.gms.common.api.Api.zzf();
        zzbmK = new _cls1();
        API_1P = new Api("People.API_1P", zzbmK, zzbTm);
        new zzawr();
        new zzazk();
        new zzazl();
        new zzazq();
        new zzazn();
        new zzazo();
        new zzazj();
        new zzazi();
        new zzazp();
    }

    private class _cls1 extends com.google.android.gms.common.api.Api.zza
    {

        public final com.google.android.gms.common.api.Api.zze zza(Context context, Looper looper, zzg zzg, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            obj = (PeopleOptions1p)obj;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("Must provide valid PeopleOptions!"));
            } else
            {
                return new zzayx(context, looper, connectioncallbacks, onconnectionfailedlistener, String.valueOf(((PeopleOptions1p) (obj)).zzbTn), zzg);
            }
        }

        _cls1()
        {
        }

        private class PeopleOptions1p
            implements com.google.android.gms.common.api.Api.ApiOptions.HasOptions
        {

            public final int zzbTn;

            public PeopleOptions1p(Builder builder)
            {
                class Builder
                {

                    public int zzbTn;

                    public Builder()
                    {
                        zzbTn = -1;
                    }
                }

                zzbTn = builder.zzbTn;
            }
        }

    }

}
