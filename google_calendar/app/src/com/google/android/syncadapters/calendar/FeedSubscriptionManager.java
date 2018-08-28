// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apiary.ParseException;
import com.google.android.apps.calendar.syncadapters.timely.sql.ColumnConstants;
import com.google.android.apps.calendar.timely.contract.TimelyContract;
import com.google.android.gsf.Gservices;
import com.google.android.gsf.SubscribedFeeds;
import com.google.android.syncadapters.calendar.timely.contract.SyncHooks;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            ProviderHelper

public class FeedSubscriptionManager
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/FeedSubscriptionManager);

    public FeedSubscriptionManager()
    {
    }

    private static String generateDefaultFeedUrl(String s)
    {
        s = Uri.encode(s);
        return (new StringBuilder(String.valueOf(s).length() + 50)).append("http://www.google.com/calendar/feeds/").append(s).append("/private/full").toString();
    }

    private static HashSet getExpectedFeeds(ContentResolver contentresolver, Account account, ContentProviderClient contentproviderclient, SyncHooks asynchooks[])
        throws RemoteException, ParseException
    {
        HashSet hashset;
        Object obj;
        hashset = new HashSet();
        obj = ProviderHelper.asClient();
        Uri uri = android.provider.CalendarContract.Calendars.CONTENT_URI;
        String s2 = ColumnConstants.WHERE_ACCOUNT_AND_SYNC;
        String s3 = account.name;
        String s4 = account.type;
        contentproviderclient = ((ProviderHelper) (obj)).query(contentproviderclient, uri, new String[] {
            "cal_sync1", "ownerAccount", "COALESCE(isPrimary,account_name=ownerAccount) AS isPrimary"
        }, s2, new String[] {
            s3, s4, "1"
        }, null);
        if (contentproviderclient == null)
        {
            throw new RemoteException();
        }
        obj = new ContentValues();
_L4:
        String s;
        if (!contentproviderclient.moveToNext())
        {
            break MISSING_BLOCK_LABEL_258;
        }
        s = contentproviderclient.getString(0);
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_179;
        }
        s = contentproviderclient.getString(1);
        s = LogUtils.sanitizeAccountName(TAG, s);
        LogUtils.w(TAG, "Attempt to subscribe to feed %s with null id.", new Object[] {
            s
        });
        continue; /* Loop/switch isn't completed */
        contentresolver;
        contentproviderclient.close();
        throw contentresolver;
        int j;
        hashset.add(generateDefaultFeedUrl(s));
        if (asynchooks.length == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        ((ContentValues) (obj)).clear();
        DatabaseUtils.cursorRowToContentValues(contentproviderclient, ((ContentValues) (obj)));
        j = asynchooks.length;
        int i = 0;
_L2:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        String s1 = asynchooks[i].generateSubscriptionUrl(((ContentValues) (obj)));
        if (!TextUtils.isEmpty(s1))
        {
            hashset.add(s1);
        }
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        if (true) goto _L4; else goto _L3
_L3:
        contentproviderclient.close();
        if (Gservices.getBoolean(contentresolver, "google_calendar_virtual_feed_subscription_sync", false))
        {
            contentproviderclient = TimelyContract.VIRTUAL_FEED_SUFFIXES.iterator();
            while (contentproviderclient.hasNext()) 
            {
                asynchooks = (String)contentproviderclient.next();
                contentresolver = String.valueOf(account.name);
                asynchooks = String.valueOf(asynchooks);
                if (asynchooks.length() != 0)
                {
                    contentresolver = contentresolver.concat(asynchooks);
                } else
                {
                    contentresolver = new String(contentresolver);
                }
                hashset.add(generateDefaultFeedUrl(contentresolver));
            }
        }
        return hashset;
    }

    public static void updateSubscriptions(ContentResolver contentresolver, Account account, ContentProviderClient contentproviderclient, SyncHooks asynchooks[])
        throws RemoteException, ParseException
    {
        SubscribedFeeds.manageSubscriptions(contentresolver, account, "com.android.calendar", "cl", getExpectedFeeds(contentresolver, account, contentproviderclient, asynchooks));
    }

}
