// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.people;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.people.model.AvatarReference;

public interface Images
{

    public abstract PendingResult loadByReference(GoogleApiClient googleapiclient, AvatarReference avatarreference, LoadImageOptions loadimageoptions);

    public abstract PendingResult loadOwnerAvatar(GoogleApiClient googleapiclient, String s, String s1, int i, int j);
}
