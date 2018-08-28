// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.people.model;


// Referenced classes of package com.google.android.gms.people.model:
//            AvatarReference

public interface AutocompleteEntry
{

    public abstract long getAndroidContactDataId();

    public abstract long getAndroidContactId();

    public abstract AvatarReference getAvatarReference();

    public abstract long getItemCertificateExpirationMillis();

    public abstract String getItemCertificateStatus();

    public abstract String getItemValue();

    public abstract String getPersonDisplayName();

    public abstract String getPersonKey();
}
