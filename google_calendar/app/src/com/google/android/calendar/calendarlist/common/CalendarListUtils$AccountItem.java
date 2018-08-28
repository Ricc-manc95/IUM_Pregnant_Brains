// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist.common;

import android.accounts.Account;

public class account
    implements ItemInfo
{

    public Account account;
    public int priority;

    public final int getOrder()
    {
        return 0;
    }

    public int getType()
    {
        return 0;
    }

    public int getViewType()
    {
        return 1;
    }

    public ItemInfo()
    {
    }

    public ItemInfo(Account account1)
    {
        priority = 0;
        account = account1;
    }
}
