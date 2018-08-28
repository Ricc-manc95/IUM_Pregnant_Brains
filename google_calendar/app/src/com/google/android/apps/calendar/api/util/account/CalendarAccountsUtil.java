// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.api.util.account;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Looper;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public final class CalendarAccountsUtil
{

    private static Account[] getNonGoogleAccounts$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7CKLMJ31DPI74RR9CGNM2OR3DTQMST3J5T0M6ORFELN78EO_0()
    {
        if (Looper.getMainLooper().getThread() == Thread.currentThread())
        {
            LogUtils.w("CalendarAccountsUtil", "Getting accounts from the provider should NOT be done on the UI thread", new Object[0]);
        }
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj1)
            {
                CalendarListEntry acalendarlistentry[] = (CalendarListEntry[])obj1;
                obj1 = new HashSet();
                int j = acalendarlistentry.length;
                for (int i = 0; i < j; i++)
                {
                    Account account = acalendarlistentry[i].getDescriptor().account;
                    if (!AccountUtil.isGoogleAccount(account) && AccountUtil.isAccountInfoValid(account.name, account.type))
                    {
                        ((Set) (obj1)).add(account);
                    }
                }

                acalendarlistentry = ((CalendarListEntry []) ((Object[])Array.newInstance(android/accounts/Account, 0)));
                if (obj1 instanceof Collection)
                {
                    obj1 = (Collection)obj1;
                } else
                {
                    java.util.Iterator iterator = ((Iterable) (obj1)).iterator();
                    obj1 = new ArrayList();
                    Iterators.addAll(((Collection) (obj1)), iterator);
                }
                return (Account[])((Collection) (obj1)).toArray(acalendarlistentry);
            }


            private .Lambda._cls0()
            {
            }
        }

        Account aaccount[] = (Account[])AbstractTransformFuture.create(CalendarApi.CalendarList.list(null), .Lambda._cls0..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE).get();
        return aaccount;
        Object obj;
        obj;
_L2:
        LogUtils.wtf("CalendarAccountsUtil", ((Throwable) (obj)), "Unable to list calendars", new Object[0]);
        return new Account[0];
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static Account[] getSyncableAccounts(Context context)
    {
        boolean flag = false;
        Account aaccount[] = AccountsUtil.getGoogleAccounts(context);
        context = getNonGoogleAccounts$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7CKLMJ31DPI74RR9CGNM2OR3DTQMST3J5T0M6ORFELN78EO_0();
        ArrayList arraylist = new ArrayList();
        int k = aaccount.length;
        for (int i = 0; i < k; i++)
        {
            Account account1 = aaccount[i];
            if (ContentResolver.getIsSyncable(account1, "com.android.calendar") > 0)
            {
                arraylist.add(account1);
            }
        }

        k = context.length;
        for (int j = ((flag) ? 1 : 0); j < k; j++)
        {
            Account account = context[j];
            if (ContentResolver.getIsSyncable(account, "com.android.calendar") > 0)
            {
                arraylist.add(account);
            }
        }

        return (Account[])arraylist.toArray(new Account[arraylist.size()]);
    }
}
