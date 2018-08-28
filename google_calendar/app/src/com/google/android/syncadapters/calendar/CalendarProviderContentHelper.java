// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.database.Cursor;
import android.os.RemoteException;
import com.google.android.apiary.ParseException;
import com.google.android.apps.calendar.syncadapters.timely.sql.ColumnConstants;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            ProviderHelper

public final class CalendarProviderContentHelper
{

    public static Map getStoredCalendarsForAccount(ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, ParseException
    {
        HashMap hashmap;
        hashmap = new HashMap();
        ProviderHelper providerhelper = ProviderHelper.asClient();
        android.net.Uri uri = android.provider.CalendarContract.Calendars.CONTENT_URI;
        String s = ColumnConstants.WHERE_ACCOUNT_AND_TYPE;
        String s1 = account.name;
        account = account.type;
        contentproviderclient = providerhelper.query(contentproviderclient, uri, new String[] {
            "_id", "cal_sync1"
        }, s, new String[] {
            s1, account
        }, null);
        if (contentproviderclient == null)
        {
            break MISSING_BLOCK_LABEL_126;
        }
        for (; contentproviderclient.moveToNext(); hashmap.put(Long.valueOf(contentproviderclient.getLong(0)), contentproviderclient.getString(1))) { }
        break MISSING_BLOCK_LABEL_120;
        account;
        contentproviderclient.close();
        throw account;
        contentproviderclient.close();
        return hashmap;
    }
}
