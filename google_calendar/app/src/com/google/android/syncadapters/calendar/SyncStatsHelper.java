// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.database.Cursor;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            ProviderHelper, SyncLog, AnalyticsLoggerExtensionFactory, SyncAnalyticsLoggerExtension

public class SyncStatsHelper
{
    public static final class CalendarDeletionStats
    {

        public final int deletedEvents;
        public final int totalEvents;

        public CalendarDeletionStats(long l, int i, int j)
        {
            deletedEvents = i;
            totalEvents = j;
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/SyncStatsHelper);

    public SyncStatsHelper()
    {
    }

    public static CalendarDeletionStats getCalendarWithMostDeletions(ContentProviderClient contentproviderclient, Account account, int i)
    {
        contentproviderclient = getStatsPerCalendar(contentproviderclient, account);
        int j = 0;
        float f = 0.0F;
        int k = -1;
        while (j < contentproviderclient.size()) 
        {
label0:
            {
                account = (CalendarDeletionStats)contentproviderclient.get(j);
                float f1 = f;
                int l = k;
                if (((CalendarDeletionStats) (account)).totalEvents <= 0)
                {
                    break label0;
                }
                f1 = f;
                l = k;
                if (((CalendarDeletionStats) (account)).deletedEvents < i)
                {
                    break label0;
                }
                float f2 = (float)((CalendarDeletionStats) (account)).deletedEvents / (float)((CalendarDeletionStats) (account)).totalEvents;
                if (k != -1)
                {
                    boolean flag;
                    if (f2 - f > 0.005F)
                    {
                        l = 1;
                    } else
                    {
                        l = 0;
                    }
                    if (Math.abs(f2 - f) <= 0.005F && ((CalendarDeletionStats) (account)).deletedEvents > ((CalendarDeletionStats)contentproviderclient.get(k)).deletedEvents)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (l == 0)
                    {
                        f1 = f;
                        l = k;
                        if (!flag)
                        {
                            break label0;
                        }
                    }
                }
                f1 = f2;
                l = j;
            }
            j++;
            f = f1;
            k = l;
        }
        if (k != -1)
        {
            return (CalendarDeletionStats)contentproviderclient.get(k);
        } else
        {
            return null;
        }
    }

    public static int getDeletedEventsCountInEditableCalendars(ContentProviderClient contentproviderclient, Account account)
    {
        return getEventsCount(contentproviderclient, "account_name=? AND account_type=? AND calendar_access_level>=500 AND deleted != 0", new String[] {
            account.name, account.type
        });
    }

    private static int getEventsCount(ContentProviderClient contentproviderclient, String s, String as[])
    {
        s = ProviderHelper.asClient().query(contentproviderclient, android.provider.CalendarContract.Events.CONTENT_URI, new String[] {
            "_count"
        }, s, as, null);
        if (s == null) goto _L2; else goto _L1
_L1:
        contentproviderclient = s;
        s.moveToLast();
        contentproviderclient = s;
        int i = s.getInt(0);
_L7:
        if (s != null)
        {
            s.close();
        }
        return i;
        as;
        s = null;
_L5:
        contentproviderclient = s;
        LogUtils.e(TAG, as, "Failure when obtaining number of events.", new Object[0]);
        if (s != null)
        {
            s.close();
            return 0;
        } else
        {
            return 0;
        }
        s;
        contentproviderclient = null;
_L4:
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
        throw s;
        s;
        if (true) goto _L4; else goto _L3
_L3:
        as;
        if (true) goto _L5; else goto _L2
_L2:
        i = 0;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public static int getEventsCountInEditableCalendars(ContentProviderClient contentproviderclient, Account account)
    {
        return getEventsCount(contentproviderclient, "account_name=? AND account_type=? AND calendar_access_level>=500", new String[] {
            account.name, account.type
        });
    }

    public static String getMutatorType(String s)
    {
        if (s == null)
        {
            return "null";
        }
        if (TextUtils.isEmpty(s))
        {
            return "empty";
        }
        if (s.contains("com.google.android.calendar"))
        {
            return "calendar";
        }
        if (s.contains("com.google.android.syncadapters.calendar"))
        {
            return "syncAdapter";
        }
        if (s.contains("com.android.providers.calendar"))
        {
            return "calendarProvider";
        } else
        {
            return "other";
        }
    }

    private static List getStatsPerCalendar(ContentProviderClient contentproviderclient, Account account)
    {
        ArrayList arraylist = new ArrayList();
        Object obj;
        obj = ProviderHelper.asClient();
        android.net.Uri uri = android.provider.CalendarContract.Calendars.CONTENT_URI;
        String s1 = account.name;
        String s3 = account.type;
        obj = ((ProviderHelper) (obj)).query(contentproviderclient, uri, new String[] {
            "_id"
        }, "account_name=? AND account_type=? AND calendar_access_level>=500", new String[] {
            s1, s3
        }, null);
        if (obj == null)
        {
            if (obj != null)
            {
                ((Cursor) (obj)).close();
            }
            return arraylist;
        }
        ArrayList arraylist1;
        ArrayList arraylist2;
        ArrayList arraylist3;
        arraylist1 = new ArrayList();
        arraylist2 = new ArrayList();
        arraylist3 = new ArrayList();
_L5:
        if (!((Cursor) (obj)).moveToNext()) goto _L2; else goto _L1
_L1:
        int i;
        long l = ((Cursor) (obj)).getLong(0);
        arraylist1.add(Long.valueOf(l));
        String s = String.format(null, "COUNT(CASE WHEN (calendar_id=%1$d) THEN 1 ELSE NULL END) AS calendar_%1$d_total_events", new Object[] {
            Long.valueOf(l)
        });
        String s2 = String.format(null, "COUNT(CASE WHEN (calendar_id=%1$d AND deleted != 0) THEN 1 ELSE NULL END) AS calendar_%1$d_deleted_events", new Object[] {
            Long.valueOf(l)
        });
        arraylist2.add(s);
        arraylist3.add(s2);
        i = arraylist2.size() + arraylist3.size();
        if (i >= 50) goto _L4; else goto _L3
_L3:
        if (!((Cursor) (obj)).isLast()) goto _L5; else goto _L4
_L4:
        String as[] = new String[i];
        i = 0;
_L7:
        if (i >= arraylist1.size())
        {
            break; /* Loop/switch isn't completed */
        }
        as[i * 2] = (String)arraylist2.get(i);
        as[i * 2 + 1] = (String)arraylist3.get(i);
        i++;
        if (true) goto _L7; else goto _L6
_L6:
        Object obj1;
        Object obj2;
        obj1 = null;
        obj2 = null;
        Cursor cursor = ProviderHelper.asClient().query(contentproviderclient, android.provider.CalendarContract.Events.CONTENT_URI, as, "account_name=? AND account_type=? AND calendar_access_level>=500", new String[] {
            account.name, account.type
        }, null);
        if (cursor == null) goto _L9; else goto _L8
_L8:
        if (!cursor.moveToFirst()) goto _L9; else goto _L10
_L10:
        i = 0;
_L11:
        if (i >= arraylist1.size())
        {
            break; /* Loop/switch isn't completed */
        }
        int j = cursor.getInt(i * 2);
        int k = cursor.getInt(i * 2 + 1);
        arraylist.add(new CalendarDeletionStats(((Long)arraylist1.get(i)).longValue(), k, j));
        i++;
        if (true) goto _L11; else goto _L9
_L9:
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_430;
        }
        cursor.close();
_L15:
        arraylist1.clear();
        arraylist2.clear();
        arraylist3.clear();
          goto _L5
        account;
        contentproviderclient = ((ContentProviderClient) (obj));
_L18:
        LogUtils.e(TAG, account, "Failure when obtaining number of total and deleted events per calendar.", new Object[0]);
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
        }
_L16:
        return arraylist;
        obj1;
        cursor = ((Cursor) (obj2));
        obj2 = obj1;
_L20:
        obj1 = cursor;
        SyncLog.logError(((Throwable) (obj2)), "Failure when obtaining stats for calendar(s).");
        obj1 = cursor;
        if (AnalyticsLoggerExtensionFactory.analyticsLogger != null) goto _L13; else goto _L12
_L12:
        obj1 = cursor;
        throw new NullPointerException(String.valueOf("AnalyticsLoggerExtensionFactory#initialize() must be called first"));
        contentproviderclient;
_L19:
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_532;
        }
        ((Cursor) (obj1)).close();
        throw contentproviderclient;
        contentproviderclient;
_L17:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        throw contentproviderclient;
_L13:
        obj1 = cursor;
        obj2 = AnalyticsLoggerExtensionFactory.analyticsLogger;
        obj1 = cursor;
        long l1 = arraylist1.size();
        obj1 = cursor;
        ((SyncAnalyticsLoggerExtension) (obj2)).addSyncSpecificCustomDimensions();
        obj1 = cursor;
        ((SyncAnalyticsLoggerExtension) (obj2)).trackEvent("Sync", "getStatsPerCalendar", "", l1, null);
        if (cursor == null) goto _L15; else goto _L14
_L14:
        cursor.close();
          goto _L15
_L2:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
          goto _L16
        contentproviderclient;
        obj = null;
          goto _L17
        account;
        obj = contentproviderclient;
        contentproviderclient = account;
          goto _L17
        account;
        contentproviderclient = null;
          goto _L18
        contentproviderclient;
        obj1 = cursor;
          goto _L19
        obj2;
          goto _L20
    }

    public static String getTopMutatorForDeletedEvents(ContentProviderClient contentproviderclient, Account account)
    {
        Object obj = null;
        String s = String.format(null, "COUNT(CASE WHEN (mutators='%s') THEN 1 ELSE NULL END) AS COLUMN%d", new Object[] {
            "com.google.android.calendar", Integer.valueOf(1)
        });
        String s1 = String.format(null, "COUNT(CASE WHEN (mutators='%s') THEN 1 ELSE NULL END) AS COLUMN%d", new Object[] {
            "com.google.android.syncadapters.calendar", Integer.valueOf(2)
        });
        String s2 = String.format(null, "COUNT(CASE WHEN (mutators='%s') THEN 1 ELSE NULL END) AS COLUMN%d", new Object[] {
            "com.android.providers.calendar", Integer.valueOf(3)
        });
        String s3 = String.format(null, "COUNT(CASE WHEN (mutators IS NOT NULL AND mutators!='') THEN 1 ELSE NULL END) AS COLUMN%d", new Object[] {
            Integer.valueOf(4)
        });
        String s4 = String.format(null, "COUNT(CASE WHEN (mutators='%s') THEN 1 ELSE NULL END) AS COLUMN%d", new Object[] {
            "", Integer.valueOf(5)
        });
        String s5 = String.format(null, "COUNT(CASE WHEN (mutators IS NULL) THEN 1 ELSE NULL END) AS COLUMN%d", new Object[] {
            Integer.valueOf(6)
        });
        ProviderHelper providerhelper = ProviderHelper.asClient();
        android.net.Uri uri = android.provider.CalendarContract.Events.CONTENT_URI;
        String s6 = account.name;
        account = account.type;
        account = providerhelper.query(contentproviderclient, uri, new String[] {
            s, s1, s2, s3, s4, s5
        }, "account_name=? AND account_type=? AND calendar_access_level>=500 AND deleted != 0", new String[] {
            s6, account
        }, null);
        if (account == null) goto _L2; else goto _L1
_L1:
        contentproviderclient = account;
        if (account.moveToFirst()) goto _L3; else goto _L2
_L2:
        obj = "none";
        contentproviderclient = ((ContentProviderClient) (obj));
        if (account != null)
        {
            account.close();
            contentproviderclient = ((ContentProviderClient) (obj));
        }
_L5:
        return contentproviderclient;
_L3:
        contentproviderclient = account;
        int k = account.getInt(0);
        contentproviderclient = account;
        int l = account.getInt(1);
        contentproviderclient = account;
        int i1 = account.getInt(2);
        contentproviderclient = account;
        int j1 = account.getInt(3) - k - l - i1;
        contentproviderclient = account;
        int k1 = account.getInt(4);
        contentproviderclient = account;
        int i = account.getInt(5);
        if (k + l + i1 + j1 + k1 + i != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        contentproviderclient = "none";
        if (account != null)
        {
            account.close();
            return "none";
        }
        if (true) goto _L5; else goto _L4
_L4:
        contentproviderclient = account;
        obj = new int[6];
        obj[0] = k;
        obj[1] = l;
        obj[2] = i1;
        obj[3] = j1;
        obj[4] = k1;
        obj[5] = i;
        contentproviderclient = account;
        int l1 = obj.length;
        int j;
        j = 0x80000000;
        i = 0;
_L7:
        if (i >= l1)
        {
            break; /* Loop/switch isn't completed */
        }
        contentproviderclient = account;
        j = Math.max(j, obj[i]);
        i++;
        if (true) goto _L7; else goto _L6
_L6:
        if (j != k)
        {
            break; /* Loop/switch isn't completed */
        }
        contentproviderclient = "calendar";
        if (account != null)
        {
            account.close();
            return "calendar";
        }
        if (true) goto _L5; else goto _L8
_L8:
        if (j != l)
        {
            break; /* Loop/switch isn't completed */
        }
        contentproviderclient = "syncAdapter";
        if (account != null)
        {
            account.close();
            return "syncAdapter";
        }
        if (true) goto _L5; else goto _L9
_L9:
        if (j != i1)
        {
            break; /* Loop/switch isn't completed */
        }
        contentproviderclient = "calendarProvider";
        if (account != null)
        {
            account.close();
            return "calendarProvider";
        }
        if (true) goto _L5; else goto _L10
_L10:
        if (j != j1)
        {
            break; /* Loop/switch isn't completed */
        }
        contentproviderclient = "other";
        if (account != null)
        {
            account.close();
            return "other";
        }
        if (true) goto _L5; else goto _L11
_L11:
        if (j != k1)
        {
            break; /* Loop/switch isn't completed */
        }
        contentproviderclient = "empty";
        if (account != null)
        {
            account.close();
            return "empty";
        }
        if (true) goto _L5; else goto _L12
_L12:
        contentproviderclient = "null";
        if (account == null) goto _L5; else goto _L13
_L13:
        account.close();
        return "null";
        obj;
        account = null;
_L17:
        contentproviderclient = account;
        LogUtils.e(TAG, ((Throwable) (obj)), "Failure when obtaining value of top mutator.", new Object[0]);
        if (account != null)
        {
            account.close();
        }
        return "none";
        contentproviderclient;
        account = ((Account) (obj));
_L15:
        if (account != null)
        {
            account.close();
        }
        throw contentproviderclient;
        obj;
        account = contentproviderclient;
        contentproviderclient = ((ContentProviderClient) (obj));
        if (true) goto _L15; else goto _L14
_L14:
        obj;
        if (true) goto _L17; else goto _L16
_L16:
    }

}
