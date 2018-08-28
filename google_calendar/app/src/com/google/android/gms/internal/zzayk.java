// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.people.model.AutocompleteBuffer;
import com.google.android.gms.people.model.AutocompleteEntry;
import com.google.android.gms.people.model.AvatarReference;

public final class zzayk extends zzc
    implements AutocompleteEntry
{

    public zzayk(AutocompleteBuffer autocompletebuffer, DataHolder dataholder, int i, Bundle bundle)
    {
        super(dataholder, i);
    }

    public final long getAndroidContactDataId()
    {
        return getLong("cp2_data_id");
    }

    public final long getAndroidContactId()
    {
        return getLong("cp2_contact_id");
    }

    public final AvatarReference getAvatarReference()
    {
        String s = getString("avatar_location");
        if (TextUtils.isEmpty(s))
        {
            return null;
        } else
        {
            return new AvatarReference(getInteger("avatar_source"), s);
        }
    }

    public final long getItemCertificateExpirationMillis()
    {
        return getLong("item_certificate_expiration_millis");
    }

    public final String getItemCertificateStatus()
    {
        return getString("item_certificate_status");
    }

    public final String getItemValue()
    {
        return getString("value");
    }

    public final String getPersonDisplayName()
    {
        return getString("display_name");
    }

    public final String getPersonKey()
    {
        return getString("person_key");
    }
}
