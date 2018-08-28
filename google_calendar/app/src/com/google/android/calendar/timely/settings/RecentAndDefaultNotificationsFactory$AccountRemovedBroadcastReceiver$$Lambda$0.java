// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.settings;

import android.accounts.Account;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.apps.calendar.api.util.account.CalendarAccountsUtil;
import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.android.apps.calendar.util.concurrent.SubscriptionManager;
import java.util.Collection;

final class arg._cls1
    implements Runnable
{

    private final Context arg$1;

    public final void run()
    {
        Object obj1 = arg$1;
        Account aaccount[] = CalendarAccountsUtil.getSyncableAccounts(((Context) (obj1)));
        Object obj;
        String s;
        if (aaccount.length > 0)
        {
            StringBuilder stringbuilder = new StringBuilder("(accountName!=?");
            obj = new String[aaccount.length];
            obj[0] = aaccount[0].name;
            for (int i = 1; i < aaccount.length; i++)
            {
                stringbuilder.append(" AND accountName!=?");
                obj[i] = aaccount[i].name;
            }

            stringbuilder.append(")");
            s = stringbuilder.toString();
        } else
        {
            obj = null;
            s = null;
        }
        obj1 = TimelyStore.acquire(((Context) (obj1)).getApplicationContext());
        ((TimelyStore) (obj1)).database.delete("preferrednotifications", s, ((String []) (obj)));
        obj = (Collection)((TimelyStore) (obj1)).notificationSubscribers.apply(null);
    }

    (Context context)
    {
        arg$1 = context;
    }
}
