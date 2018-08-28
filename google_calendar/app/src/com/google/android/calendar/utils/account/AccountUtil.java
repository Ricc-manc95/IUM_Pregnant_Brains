// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.account;

import android.accounts.Account;
import android.text.TextUtils;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;

public final class AccountUtil
{

    public static final ImmutableSet EXCHANGE_TYPES = ImmutableSet.construct(3, new Object[] {
        "com.android.exchange", "com.google.android.gm.exchange", "com.google.android.gm.exchange.lite"
    });

    public static boolean isAccountInfoValid(String s, String s1)
    {
        return !TextUtils.isEmpty(s) && !TextUtils.isEmpty(s1);
    }

    public static boolean isExchangeAccount(Account account)
    {
        account = account.type;
        return EXCHANGE_TYPES.contains(account);
    }

    public static boolean isGoogleAccount(Account account)
    {
        return "com.google".equals(account.type);
    }

    public static boolean isGoogleCorpAccount(Account account)
    {
        return "com.google".equals(account.type) && account.name.endsWith("@google.com");
    }

    public static boolean isGoogleExchangeAccount(Account account)
    {
        return "com.google.android.gm.exchange".equals(account.type);
    }

    public static boolean isGoogleExchangeGoAccount(Account account)
    {
        return "com.google.android.gm.exchange.lite".equals(account.type);
    }

    public static boolean isGoogleExchangeGoAccount(String s)
    {
        return "com.google.android.gm.exchange.lite".equals(s);
    }

    public static boolean isGoogleExchangeType(String s)
    {
        return "com.google.android.gm.exchange".equals(s);
    }

    public static boolean isGoogleType(String s)
    {
        return "com.google".equals(s);
    }

    public static Optional newAccount(String s, String s1)
    {
        boolean flag;
        if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            s = new Account(s, s1);
            if (s == null)
            {
                throw new NullPointerException();
            } else
            {
                return new Present(s);
            }
        } else
        {
            return Absent.INSTANCE;
        }
    }

    public static Account newGoogleAccount(String s)
    {
        return new Account(s, "com.google");
    }

}
