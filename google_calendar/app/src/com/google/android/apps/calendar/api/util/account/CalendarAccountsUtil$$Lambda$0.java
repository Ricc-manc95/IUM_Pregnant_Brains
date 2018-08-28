// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.api.util.account;

import android.accounts.Account;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        CalendarListEntry acalendarlistentry[] = (CalendarListEntry[])obj;
        obj = new HashSet();
        int j = acalendarlistentry.length;
        for (int i = 0; i < j; i++)
        {
            Account account = acalendarlistentry[i].getDescriptor().account;
            if (!AccountUtil.isGoogleAccount(account) && AccountUtil.isAccountInfoValid(account.name, account.type))
            {
                ((Set) (obj)).add(account);
            }
        }

        acalendarlistentry = ((CalendarListEntry []) ((Object[])Array.newInstance(android/accounts/Account, 0)));
        if (obj instanceof Collection)
        {
            obj = (Collection)obj;
        } else
        {
            java.util.Iterator iterator = ((Iterable) (obj)).iterator();
            obj = new ArrayList();
            Iterators.addAll(((Collection) (obj)), iterator);
        }
        return (Account[])((Collection) (obj)).toArray(acalendarlistentry);
    }


    private ()
    {
    }
}
