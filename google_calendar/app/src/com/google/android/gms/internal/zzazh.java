// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.people.Autocomplete;

public final class zzazh
    implements Autocomplete
{

    public zzazh()
    {
    }

    public final PendingResult loadAutocompleteList(GoogleApiClient googleapiclient, String s, com.google.android.gms.people.Autocomplete.AutocompleteOptions autocompleteoptions)
    {
        if (autocompleteoptions == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            return googleapiclient.zza(new _cls1(googleapiclient, s, autocompleteoptions));
        }
    }

    private class _cls1 extends com.google.android.gms.people.People.zza
    {

        private final com.google.android.gms.people.Autocomplete.AutocompleteOptions zzbZu;
        private final String zzbqu;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
            throws RemoteException
        {
            ((zzayx)zzb1).zza(this, zzbqu, zzbZu);
        }

        protected final Result zzb(Status status)
        {
            class _cls1
                implements com.google.android.gms.people.Autocomplete.AutocompleteResult
            {

                private final Status zzavV;

                public final AutocompleteBuffer getAutocompleteEntries()
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

        _cls1(GoogleApiClient googleapiclient, String s, com.google.android.gms.people.Autocomplete.AutocompleteOptions autocompleteoptions)
        {
            zzbqu = s;
            zzbZu = autocompleteoptions;
            super(googleapiclient);
        }
    }

}
