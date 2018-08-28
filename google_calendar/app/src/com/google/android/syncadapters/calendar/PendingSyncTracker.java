// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.util.Base64;

public final class PendingSyncTracker
{

    public static String getPerAccountPrefKey(String s, Account account)
    {
        account = Base64.encodeToString(account.name.getBytes(), 8);
        s = String.valueOf(s);
        account = String.valueOf(account);
        if (account.length() != 0)
        {
            return s.concat(account);
        } else
        {
            return new String(s);
        }
    }
}
