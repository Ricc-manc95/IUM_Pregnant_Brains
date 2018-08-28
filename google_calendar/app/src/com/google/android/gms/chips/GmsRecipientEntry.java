// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.chips;

import com.android.ex.chips.RecipientEntry;
import com.google.android.gms.people.model.AutocompleteEntry;
import com.google.android.gms.people.model.AvatarReference;

public final class GmsRecipientEntry extends RecipientEntry
{

    public final AvatarReference mAvatarReference;
    private final long mCertificateExpirationMillis;

    public GmsRecipientEntry(int i, AutocompleteEntry autocompleteentry, int j)
    {
        super(0, autocompleteentry.getPersonDisplayName(), autocompleteentry.getItemValue(), -1, null, autocompleteentry.getAndroidContactId(), null, autocompleteentry.getAndroidContactDataId(), null, true, true, null, null);
        autocompleteentry.getPersonKey();
        mAvatarReference = autocompleteentry.getAvatarReference();
        mCertificateExpirationMillis = autocompleteentry.getItemCertificateExpirationMillis();
        if (mCertificateExpirationMillis == 0L || mCertificateExpirationMillis > System.currentTimeMillis())
        {
            CertificateStatus.Converter.toCertificateStatus(autocompleteentry.getItemCertificateStatus());
        }
    }
}
