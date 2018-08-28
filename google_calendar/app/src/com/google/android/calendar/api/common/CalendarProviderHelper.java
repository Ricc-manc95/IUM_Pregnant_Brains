// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.common;

import android.accounts.Account;
import android.net.Uri;

public final class CalendarProviderHelper
{

    public static Uri withAppendedSyncAdapterFlags(Uri uri, Account account)
    {
        return uri.buildUpon().appendQueryParameter("caller_is_syncadapter", "true").appendQueryParameter("account_name", account.name).appendQueryParameter("account_type", account.type).build();
    }
}
