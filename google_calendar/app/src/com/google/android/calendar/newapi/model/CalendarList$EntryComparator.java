// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model;

import android.accounts.Account;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.utils.account.AccountUtil;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.newapi.model:
//            CalendarList

static final class 
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        byte byte0 = 2;
        boolean flag = true;
        obj = (CalendarListEntry)obj;
        obj1 = (CalendarListEntry)obj1;
        Account account = ((CalendarListEntry) (obj)).getDescriptor().account;
        Account account1 = ((CalendarListEntry) (obj1)).getDescriptor().account;
        int i;
        if (AccountUtil.isGoogleAccount(account1))
        {
            i = 2;
        } else
        if (AccountUtil.isExchangeAccount(account1))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (!AccountUtil.isGoogleAccount(account))
        {
            if (AccountUtil.isExchangeAccount(account))
            {
                byte0 = 1;
            } else
            {
                byte0 = 0;
            }
        }
        i -= byte0;
        if (i == 0)
        {
            i = account.name.compareToIgnoreCase(account1.name);
        }
        if (i == 0)
        {
            int j;
            if (((CalendarListEntry) (obj1)).isPrimary())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (((CalendarListEntry) (obj)).isPrimary())
            {
                j = ((flag) ? 1 : 0);
            } else
            {
                j = 0;
            }
            j = i - j;
            i = j;
            if (j == 0)
            {
                return ((CalendarListEntry) (obj)).getDisplayName().compareToIgnoreCase(((CalendarListEntry) (obj1)).getDisplayName());
            }
        }
        return i;
    }

    ()
    {
    }
}
