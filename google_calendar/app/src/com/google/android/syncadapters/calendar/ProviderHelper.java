// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apiary.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            SyncLog

public class ProviderHelper
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/ProviderHelper);
    private static final ThreadLocal clientInstance = new ThreadLocal();
    private static final ThreadLocal syncAdapterMap = new ThreadLocal();
    public final Account account;

    private ProviderHelper(Account account1)
    {
        account = account1;
    }

    public static ProviderHelper asClient()
    {
        if (clientInstance.get() == null)
        {
            clientInstance.set(new ProviderHelper(null));
        }
        return (ProviderHelper)clientInstance.get();
    }

    public static ProviderHelper asSyncAdapter(Account account1)
    {
        if (account1 == null)
        {
            throw new IllegalArgumentException("Null account is not allowed is asSyncAdapter().");
        }
        Object obj = (Map)syncAdapterMap.get();
        if (obj == null)
        {
            obj = new HashMap();
            syncAdapterMap.set(obj);
        }
        ProviderHelper providerhelper1 = (ProviderHelper)((Map) (obj)).get(account1);
        ProviderHelper providerhelper = providerhelper1;
        if (providerhelper1 == null)
        {
            providerhelper = new ProviderHelper(account1);
            ((Map) (obj)).put(account1, providerhelper);
        }
        return providerhelper;
    }

    private static int getCollection(Uri uri)
    {
        uri = uri.toString();
        if (uri.startsWith(android.provider.CalendarContract.Events.CONTENT_URI.toString()) || uri.startsWith(android.provider.CalendarContract.EventsEntity.CONTENT_URI.toString()))
        {
            return 2;
        }
        if (uri.startsWith(android.provider.CalendarContract.Calendars.CONTENT_URI.toString()) || uri.startsWith(android.provider.CalendarContract.CalendarEntity.CONTENT_URI.toString()))
        {
            return 1;
        }
        return !uri.startsWith(android.provider.CalendarContract.Colors.CONTENT_URI.toString()) ? 0 : 3;
    }

    private static String getSyncLogTag(int i, int j)
    {
        switch (i)
        {
        default:
            switch (j)
            {
            default:
                return "DB: query";

            case 1: // '\001'
                return "DB: insert";

            case 2: // '\002'
                return "DB: update";

            case 5: // '\005'
                return "DB: delete";

            case 3: // '\003'
                return "DB: applyBatch";

            case 4: // '\004'
                return "DB: bulkInsert";
            }

        case 1: // '\001'
            switch (j)
            {
            default:
                return "DB: calendars.query";

            case 1: // '\001'
                return "DB: calendars.insert";

            case 2: // '\002'
                return "DB: calendars.update";

            case 5: // '\005'
                return "DB: calendars.delete";

            case 3: // '\003'
                return "DB: calendars.applyBatch";

            case 4: // '\004'
                return "DB: calendars.bulkInsert";
            }

        case 2: // '\002'
            switch (j)
            {
            default:
                return "DB: events.query";

            case 1: // '\001'
                return "DB: events.insert";

            case 2: // '\002'
                return "DB: events.update";

            case 5: // '\005'
                return "DB: events.delete";

            case 3: // '\003'
                return "DB: events.applyBatch";

            case 4: // '\004'
                return "DB: events.bulkInsert";
            }

        case 3: // '\003'
            switch (j)
            {
            default:
                return "DB: colors.query";

            case 1: // '\001'
                return "DB: colors.insert";

            case 2: // '\002'
                return "DB: colors.update";

            case 5: // '\005'
                return "DB: colors.delete";

            case 3: // '\003'
                return "DB: colors.applyBatch";

            case 4: // '\004'
                return "DB: colors.bulkInsert";
            }
        }
    }

    public static Uri toAsSyncAdapterUri(Uri uri, Account account1)
    {
        uri = uri.buildUpon().appendQueryParameter("caller_is_syncadapter", "true");
        if (account1 != null)
        {
            uri.appendQueryParameter("account_name", account1.name).appendQueryParameter("account_type", account1.type);
        } else
        {
            LogUtils.w(TAG, "Null account in toAsSyncAdapterUri", new Object[0]);
        }
        return uri.build();
    }

    public final ContentProviderResult[] applyBatch(ContentProviderClient contentproviderclient, ArrayList arraylist)
        throws RemoteException, OperationApplicationException, ParseException
    {
        String s;
        int i;
label0:
        {
            if (account != null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                ArrayList arraylist1 = (ArrayList)arraylist;
                int l = arraylist1.size();
                i = 0;
                do
                {
                    if (i >= l)
                    {
                        break;
                    }
                    Object obj = arraylist1.get(i);
                    int j = i + 1;
                    obj = (ContentProviderOperation)obj;
                    i = j;
                    if (!((ContentProviderOperation) (obj)).getUri().getBooleanQueryParameter("caller_is_syncadapter", false))
                    {
                        SyncLog.logError(new Throwable(), "One of batch queries not performed as a sync adapter");
                        LogUtils.v(TAG, "%s", new Object[] {
                            ((ContentProviderOperation) (obj)).toString()
                        });
                        i = j;
                    }
                } while (true);
            }
            ArrayList arraylist2 = (ArrayList)arraylist;
            int j1 = arraylist2.size();
            int k = 0;
            int i1;
label1:
            do
            {
                for (i = 0; k < j1; i = i1)
                {
                    Object obj1 = arraylist2.get(k);
                    k++;
                    i1 = getCollection(((ContentProviderOperation)obj1).getUri());
                    if (i != 0)
                    {
                        continue label1;
                    }
                }

                break label0;
            } while (i == i1);
            i = 0;
        }
        s = getSyncLogTag(i, 3);
        SyncLog.start(s);
        contentproviderclient = com.google.android.apiary.ProviderHelper.applyBatchProvider(contentproviderclient, arraylist);
        SyncLog.stop(s);
        return contentproviderclient;
        contentproviderclient;
        SyncLog.stop(s);
        throw contentproviderclient;
    }

    public final int delete(ContentProviderClient contentproviderclient, Uri uri, String s, String as[])
        throws RemoteException, ParseException
    {
        String s1;
        int i;
        if (account != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            uri = toAsSyncAdapterUri(uri, account);
        }
        s1 = getSyncLogTag(getCollection(uri), 5);
        SyncLog.start(s1);
        i = contentproviderclient.delete(uri, s, as);
        SyncLog.stop(s1);
        return i;
        contentproviderclient;
        throw new ParseException(contentproviderclient);
        contentproviderclient;
        SyncLog.stop(s1);
        throw contentproviderclient;
    }

    public final Uri insert(ContentProviderClient contentproviderclient, Uri uri, ContentValues contentvalues)
        throws RemoteException, ParseException
    {
        String s;
        boolean flag;
        if (account != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            uri = toAsSyncAdapterUri(uri, account);
        }
        s = getSyncLogTag(getCollection(uri), 1);
        SyncLog.start(s);
        contentproviderclient = com.google.android.apiary.ProviderHelper.insertProvider(contentproviderclient, uri, contentvalues);
        SyncLog.stop(s);
        return contentproviderclient;
        contentproviderclient;
        SyncLog.stop(s);
        throw contentproviderclient;
    }

    public final android.content.ContentProviderOperation.Builder newInsert(Uri uri)
    {
        boolean flag;
        if (account != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            uri = toAsSyncAdapterUri(uri, account);
        }
        return ContentProviderOperation.newInsert(uri);
    }

    public final android.content.ContentProviderOperation.Builder newUpdate(Uri uri)
    {
        boolean flag;
        if (account != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            uri = toAsSyncAdapterUri(uri, account);
        }
        return ContentProviderOperation.newUpdate(uri);
    }

    public final Cursor query(ContentProviderClient contentproviderclient, Uri uri, String as[], String s, String as1[], String s1)
        throws RemoteException, ParseException
    {
        String s2;
        boolean flag;
        if (account != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            uri = toAsSyncAdapterUri(uri, account);
        }
        s2 = getSyncLogTag(getCollection(uri), 0);
        SyncLog.start(s2);
        contentproviderclient = com.google.android.apiary.ProviderHelper.queryProvider(contentproviderclient, uri, as, s, as1, s1);
        SyncLog.stop(s2);
        return contentproviderclient;
        contentproviderclient;
        SyncLog.stop(s2);
        throw contentproviderclient;
    }

    public final int update(ContentProviderClient contentproviderclient, Uri uri, ContentValues contentvalues, String s, String as[])
        throws RemoteException, ParseException
    {
        String s1;
        int i;
        if (account != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            uri = toAsSyncAdapterUri(uri, account);
        }
        s1 = getSyncLogTag(getCollection(uri), 2);
        SyncLog.start(s1);
        i = com.google.android.apiary.ProviderHelper.updateProvider(contentproviderclient, uri, contentvalues, s, as);
        SyncLog.stop(s1);
        return i;
        contentproviderclient;
        SyncLog.stop(s1);
        throw contentproviderclient;
    }

}
