// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ex.chips;

import android.net.Uri;
import android.provider.ContactsContract;

public final class 
{

    public static final String PROJECTION[] = {
        "_id", "accountName", "accountType", "displayName", "packageName", "typeResourceId"
    };
    public static final Uri URI;

    static 
    {
        URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "directories");
    }
}
