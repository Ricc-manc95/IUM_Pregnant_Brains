// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.store;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.util.Pair;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.commonsync.constants.ExtendedPropertiesConstants;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.apps.calendar.timely.contract.TimelyContract;
import com.google.android.apps.calendar.util.concurrent.SubscriptionManager;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.json.JsonUtils;
import com.google.android.syncadapters.calendar.timely.contract.TimelyEventData;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.json.GenericJson;
import com.google.api.services.calendar.model.ConferenceData;
import com.google.common.collect.ImmutableCollection;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package com.google.android.apps.calendar.timely.store:
//            AccountSettingsLogStore, AccountSettingsStore, TimelyStoreUtils, PreferredNotification

public final class TimelyStore
    implements ExtendedPropertiesConstants
{

    private static final Uri BASE_CONTENT_PROVIDER_URI;
    private static final String EVENT_TABLE_COLUMNS[] = {
        "_sync_id", "title"
    };
    private static final Object STORE_HOLDER_LOCK = new Object();
    private static TimelyStore store;
    public final AccountSettingsLogStore accountSettingsLogStore;
    public final AccountSettingsStore accountSettingsStore;
    public final SubscriptionManager conferenceSubscribers;
    public Context context;
    public SQLiteDatabase database;
    public final SubscriptionManager notificationSubscribers;
    public ConferenceTypeCache supportedConferenceByCalendarIdMap;

    TimelyStore()
    {
        supportedConferenceByCalendarIdMap = new ConferenceTypeCache(10000);
        notificationSubscribers = new SubscriptionManager();
        conferenceSubscribers = new SubscriptionManager();
        accountSettingsLogStore = null;
        accountSettingsStore = null;
    }

    private TimelyStore(Context context1)
    {
        supportedConferenceByCalendarIdMap = new ConferenceTypeCache(10000);
        notificationSubscribers = new SubscriptionManager();
        conferenceSubscribers = new SubscriptionManager();
        database = (new DatabaseHelper(context1, 37)).getWritableDatabase();
        context = context1.getApplicationContext();
        accountSettingsLogStore = new AccountSettingsLogStore(database);
        context1 = context;
        SQLiteDatabase sqlitedatabase = database;
        accountSettingsStore = new AccountSettingsStore(context1, sqlitedatabase, accountSettingsLogStore, AccountSettingsStore.createCache(sqlitedatabase));
    }

    public static TimelyStore acquire(Context context1)
    {
        synchronized (STORE_HOLDER_LOCK)
        {
            if (store == null)
            {
                store = new TimelyStore(context1);
            }
        }
        return store;
        context1;
        obj;
        JVM INSTR monitorexit ;
        throw context1;
    }

    private final Map getEventData(long l)
    {
        Cursor cursor;
        cursor = context.getContentResolver().query(ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, l), EVENT_TABLE_COLUMNS, null, null, null);
        if (cursor == null)
        {
            LogUtils.e("TimelyStore", "Null cursor while retrieving syncId and title", new Object[0]);
            return null;
        }
        if (cursor.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_78;
        }
        LogUtils.e("TimelyStore", "Unable to load event sync id and title for %d", new Object[] {
            Long.valueOf(l)
        });
        cursor.close();
        return null;
        HashMap hashmap;
        String as[];
        int j;
        hashmap = new HashMap();
        as = EVENT_TABLE_COLUMNS;
        j = as.length;
        int i = 0;
_L3:
        if (i >= j) goto _L2; else goto _L1
_L1:
        String s = as[i];
        String s1 = cursor.getString(cursor.getColumnIndex(s));
        if (s1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        hashmap.put(s, s1);
        i++;
          goto _L3
_L2:
        cursor.close();
        return hashmap;
        Exception exception;
        exception;
        cursor.close();
        throw exception;
    }

    static String parseConferenceType(Pair pair, String s)
    {
        Account account;
        account = (Account)pair.first;
        pair = (String)pair.second;
        if (TextUtils.isEmpty(s))
        {
            return null;
        }
        s = new JSONObject(s);
        if (s.isNull("allowedConferenceTypes"))
        {
            break MISSING_BLOCK_LABEL_105;
        }
        s = s.getJSONArray("allowedConferenceTypes");
        if (s.length() == 0)
        {
            return "";
        }
        s = (String)s.get(0);
        return s;
        s;
        LogUtils.wtf("TimelyStore", "Conference properties were not parsed for %s of %s.", new Object[] {
            LogUtils.sanitizeName("TimelyStore", pair), LogUtils.sanitizeAccountName("TimelyStore", account.name)
        });
        return null;
    }

    public final String getConferenceTypeForCalendar(String s, String s1, String s2)
    {
        if (s == null)
        {
            s = null;
        } else
        {
            s = Pair.create(new Account(s1, s2), s);
            s1 = (String)supportedConferenceByCalendarIdMap.get(s);
            s = s1;
            if (TextUtils.isEmpty(s1))
            {
                return null;
            }
        }
        return s;
    }

    public final Set getEventSyncIdsForCalendar(long l)
    {
        Object obj;
        Object obj1;
        obj = database;
        obj1 = (new StringBuilder(31)).append("calendarId=").append(l).toString();
        obj = ((SQLiteDatabase) (obj)).query("timelydata", new String[] {
            "syncId"
        }, ((String) (obj1)), null, null, null, null);
        obj1 = new HashSet();
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_110;
        }
        for (; ((Cursor) (obj)).moveToNext(); ((Set) (obj1)).add(((Cursor) (obj)).getString(0))) { }
        break MISSING_BLOCK_LABEL_104;
        obj1;
        ((Cursor) (obj)).close();
        throw obj1;
        ((Cursor) (obj)).close();
        return ((Set) (obj1));
    }

    public final ConferenceData getLastSyncedConferenceDataForEvent(long l, String s)
    {
        ConferenceData conferencedata = null;
        if (!TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        s = null;
_L4:
        return s;
_L2:
        Object obj = database.query("timelydata", new String[] {
            "conferenceData"
        }, "syncId = ? AND calendarId = ?", new String[] {
            s, String.valueOf(l)
        }, null, null, null);
        if (obj == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (!((Cursor) (obj)).moveToFirst())
        {
            break; /* Loop/switch isn't completed */
        }
        conferencedata = (ConferenceData)JsonUtils.fromString(((Cursor) (obj)).getString(0), com/google/api/services/calendar/model/ConferenceData, null);
        s = conferencedata;
        if (obj != null)
        {
            ((Cursor) (obj)).close();
            return conferencedata;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
_L5:
        return null;
        obj;
        obj = null;
_L8:
        LogUtils.wtf("TimelyStore", "Conference data for event %s in calendar %d could not be read from the database.", new Object[] {
            s, Long.valueOf(l)
        });
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
          goto _L5
        s;
        obj = conferencedata;
_L7:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        throw s;
        s;
        continue; /* Loop/switch isn't completed */
        s;
        if (true) goto _L7; else goto _L6
_L6:
        Exception exception;
        exception;
          goto _L8
    }

    public final TimelyEventData getTimelyEventData(long l, CalendarKey calendarkey)
    {
        Map map = getEventData(l);
        return getTimelyEventData((String)map.get("_sync_id"), (String)map.get("title"), calendarkey.getLocalId(), TimelyStoreUtils.loadExtendedProperties(context.getContentResolver(), l));
    }

    public final TimelyEventData getTimelyEventData(String s, String s1, long l, Map map)
    {
        Object obj = loadSyncedEventData(s, l);
        String s3 = (String)map.get("locationExtra");
        String s4 = (String)map.get("titleContactsExtra");
        String s5 = (String)map.get("attachmentsExtra");
        String s2 = (String)map.get("eventAttendeeList");
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        if (!TextUtils.isEmpty(s3))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!TextUtils.isEmpty(s4))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!TextUtils.isEmpty(s5))
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        flag4 = map.containsKey("participantStatusExtra");
        if (!TextUtils.isEmpty(s2))
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (flag || flag1 || flag2 || flag4 || flag3 || !TextUtils.isEmpty(FlairAllocatorFactory.getFlairUrlString(s1)))
        {
            s = ((String) (obj));
            if (obj == null)
            {
                s = new TimelyEventData();
            }
            s1 = new AndroidJsonFactory();
            if (flag)
            {
                s.structuredLocation = TimelyEventData.createStructuredLocation(s1, s3);
            }
            if (flag1)
            {
                s.titleContactAnnotations = TimelyEventData.createTitleContactAnnotations(s1, s4);
            }
            if (flag2)
            {
                s.attachments = TimelyEventData.createAttachments(s1, s5);
            }
            if (flag4)
            {
                s.participantStatus = (String)map.get("participantStatusExtra");
            }
            obj = s;
            if (flag3)
            {
                s.attendees = TimelyEventData.createAttendees(s1, s2);
                return s;
            }
        }
        return ((TimelyEventData) (obj));
    }

    public final Uri insertOrUpdateEventData(String s, long l, TimelyEventData timelyeventdata)
    {
        ContentValues contentvalues = new ContentValues();
        LogUtils.d("TimelyStore", "inserting data for event: %s calendar: %d", new Object[] {
            s, Long.valueOf(l)
        });
        LogUtils.v("TimelyStore", "Timely data: %s", new Object[] {
            timelyeventdata
        });
        contentvalues.put("syncId", s);
        contentvalues.put("calendarId", Long.valueOf(l));
        s = timelyeventdata.structuredLocation;
        if (s != null)
        {
            contentvalues.put("structuredLocation", s.toString());
        }
        s = timelyeventdata.smartMailInfo;
        if (s != null)
        {
            contentvalues.put("smartmail", s.toString());
        }
        s = timelyeventdata.backgroundImageUrl;
        if (!TextUtils.isEmpty(s))
        {
            contentvalues.put("eventBackgroundUrl", s);
        }
        s = timelyeventdata.titleContactAnnotationsAsString();
        if (!TextUtils.isEmpty(s))
        {
            contentvalues.put("titleContacts", s);
        }
        s = timelyeventdata.eventGadget;
        if (s != null)
        {
            contentvalues.put("eventGadget", s.toString());
        }
        s = timelyeventdata.eventSource;
        if (s != null)
        {
            contentvalues.put("eventSource", s.toString());
        }
        s = timelyeventdata.attachmentsAsString();
        if (!TextUtils.isEmpty(s))
        {
            contentvalues.put("attachments", s);
        }
        s = timelyeventdata.conferenceData;
        if (s != null)
        {
            contentvalues.put("conferenceData", s.toString());
        }
        s = timelyeventdata.responseSummary;
        if (s != null)
        {
            contentvalues.put("responseSummary", s.toString());
        }
        s = timelyeventdata.participantStatus;
        if (s != null)
        {
            contentvalues.put("participantStatus", s);
        }
        s = timelyeventdata.attendeesAsString();
        if (!TextUtils.isEmpty(s))
        {
            contentvalues.put("attendees", s);
        }
        l = database.insertWithOnConflict("timelydata", null, contentvalues, 5);
        if (l == -1L)
        {
            return null;
        } else
        {
            return ContentUris.withAppendedId(BASE_CONTENT_PROVIDER_URI, l);
        }
    }

    public final Map loadAllConferenceTypes()
    {
        Object obj1 = new HashMap();
        Object obj = database.query("calendar_settings", new String[] {
            "account_name", "account_type", "calendar_sync_id", "conference_properties"
        }, null, null, null, null, null);
_L5:
        if (obj == null) goto _L2; else goto _L1
_L1:
        if (!((Cursor) (obj)).moveToNext()) goto _L2; else goto _L3
_L3:
        Pair pair;
        String s;
        pair = Pair.create(new Account(((Cursor) (obj)).getString(0), ((Cursor) (obj)).getString(1)), ((Cursor) (obj)).getString(2));
        s = parseConferenceType(pair, ((Cursor) (obj)).getString(3));
        if (s == null) goto _L5; else goto _L4
_L4:
        ((Map) (obj1)).put(pair, s);
        supportedConferenceByCalendarIdMap.put(pair, s);
          goto _L5
        Exception exception;
        exception;
        obj1 = obj;
        obj = exception;
_L7:
        if (obj1 != null)
        {
            ((Cursor) (obj1)).close();
        }
        throw obj;
_L2:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        return ((Map) (obj1));
        obj;
        obj1 = null;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final Notifications loadAllNotifications(Map map, String s)
    {
        Object obj;
        Object obj1;
        obj1 = new HashMap();
        HashMap hashmap = new HashMap();
        s = new Notifications(((Map) (obj1)), hashmap);
        obj = new HashSet();
        String s1;
        Object obj3;
        for (map = map.entrySet().iterator(); map.hasNext(); hashmap.put(s1, new DefaultNotifications(((Account) (obj3)))))
        {
            obj3 = (java.util.Map.Entry)map.next();
            s1 = (String)((java.util.Map.Entry) (obj3)).getKey();
            obj3 = (Account)((java.util.Map.Entry) (obj3)).getValue();
            ((Set) (obj)).add(((Account) (obj3)).name);
            ((Set) (obj)).add(s1);
            if (!((Map) (obj1)).containsKey(((Account) (obj3)).name))
            {
                ((Map) (obj1)).put(((Account) (obj3)).name, new RecentNotifications());
            }
        }

        map = new StringBuilder();
        if (((Set) (obj)).size() != 0)
        {
            obj1 = map.append("lookupKey IN (");
            class .Lambda._cls0
                implements Function
            {

                public static final Function $instance = new .Lambda._cls0();

                public final Object apply(Object obj4)
                {
                    return DatabaseUtils.sqlEscapeString((String)obj4);
                }


            private .Lambda._cls0()
            {
            }
            }

            Function function = .Lambda._cls0..instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            if (function == null)
            {
                throw new NullPointerException();
            }
            ((StringBuilder) (obj1)).append(TextUtils.join(", ", new com.google.common.collect.Iterables._cls5(((Iterable) (obj)), function))).append(")");
        }
        if (!TextUtils.isEmpty(null))
        {
            if (map.length() > 0)
            {
                map.insert(0, "(").append(") AND ");
            }
            map.append(null);
        }
        map = database.query("preferrednotifications", TimelyContract.NOTIFICATIONS_PROJECTION, map.toString(), null, null, null, "category ASC, lookupKey ASC, allday ASC, timestamp DESC");
        if (map == null)
        {
            LogUtils.e("TimelyStore", "Null cursor for notifications.", new Object[0]);
            return s;
        }
        obj = ((Notifications) (s)).recentNotificationsMap;
        obj1 = ((Notifications) (s)).defaultNotificationsMap;
_L2:
        Object obj2;
        int i;
        int j;
        int k;
        int l;
        if (!map.moveToNext())
        {
            break; /* Loop/switch isn't completed */
        }
        obj2 = map.getString(map.getColumnIndexOrThrow("lookupKey"));
        i = map.getInt(map.getColumnIndexOrThrow("category"));
        j = map.getInt(map.getColumnIndexOrThrow("method"));
        k = map.getInt(map.getColumnIndexOrThrow("minutes"));
        l = map.getInt(map.getColumnIndexOrThrow("allday"));
        boolean flag;
        PreferredNotification preferrednotification;
        if (map.getInt(map.getColumnIndexOrThrow("noNotifications")) == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_546;
        }
        preferrednotification = new PreferredNotification(l, k, j);
        obj2 = (RecentNotifications)((Map) (obj)).get(obj2);
        if (obj2 == null)
        {
            break MISSING_BLOCK_LABEL_530;
        }
        ((RecentNotifications) (obj2)).notifications.add(preferrednotification);
        continue; /* Loop/switch isn't completed */
        s;
        map.close();
        throw s;
        LogUtils.w("TimelyStore", "Lookup for recent notifications failed, ignoring.", new Object[0]);
        continue; /* Loop/switch isn't completed */
        if (i != 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        DefaultNotifications defaultnotifications = (DefaultNotifications)((Map) (obj1)).get(obj2);
        if (defaultnotifications == null)
        {
            break MISSING_BLOCK_LABEL_671;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_608;
        }
        PreferredNotification preferrednotification1;
        if (l == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_599;
        }
        defaultnotifications.noAllDayNotifications = true;
        continue; /* Loop/switch isn't completed */
        defaultnotifications.noTimedNotifications = true;
        continue; /* Loop/switch isn't completed */
        preferrednotification1 = new PreferredNotification(l, k, j);
        if (preferrednotification1.allDay > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_655;
        }
        defaultnotifications.allDayNotifications.add(preferrednotification1);
        continue; /* Loop/switch isn't completed */
        defaultnotifications.timedNotifications.add(preferrednotification1);
        continue; /* Loop/switch isn't completed */
        LogUtils.w("TimelyStore", "Lookup for default notifications failed, ignoring.", new Object[0]);
        if (true) goto _L2; else goto _L1
_L1:
        map.close();
        return s;
    }

    public final PreferredNotification[] loadNotifications(String s, Account account, boolean flag, int i, String s1, String s2)
    {
        int j;
        int k;
        int l;
        int i1;
        Object obj = new StringBuilder("lookupKey = ? AND category = ? AND allday = ?");
        if (!TextUtils.isEmpty(s1))
        {
            ((StringBuilder) (obj)).append(" AND ").append(s1);
        }
        SQLiteDatabase sqlitedatabase = database;
        String as[] = TimelyContract.NOTIFICATIONS_PROJECTION;
        obj = ((StringBuilder) (obj)).toString();
        String s3 = Integer.toString(i);
        if (flag)
        {
            s1 = "1";
        } else
        {
            s1 = "0";
        }
        s = sqlitedatabase.query("preferrednotifications", as, ((String) (obj)), new String[] {
            s, s3, s1
        }, null, null, s2);
        s1 = new PreferredNotification[s.getCount()];
        s.moveToPosition(-1);
        j = 0;
_L2:
        if (!s.moveToNext())
        {
            break; /* Loop/switch isn't completed */
        }
        k = s.getInt(s.getColumnIndex("allday"));
        l = s.getInt(s.getColumnIndex("minutes"));
        i1 = s.getInt(s.getColumnIndex("method"));
        boolean flag1;
        if (s.getInt(s.getColumnIndexOrThrow("noNotifications")) == 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            s.close();
            return new PreferredNotification[0];
        }
        s1[j] = new PreferredNotification(k, l, i1);
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        if (i != 1)
        {
            break MISSING_BLOCK_LABEL_311;
        }
        account = account.type;
        if (!AccountUtil.EXCHANGE_TYPES.contains(account) || s1.length != 0)
        {
            break MISSING_BLOCK_LABEL_311;
        }
        account = DefaultNotifications.getExchangeInitialDefaultNotification(flag);
        s.close();
        return (new PreferredNotification[] {
            account
        });
        s.close();
        return s1;
        account;
        s.close();
        throw account;
    }

    public final PreferredNotification[] loadRecentlyUsedNotificationsForAccount(Account account, boolean flag)
    {
        return loadNotifications(account.name, account, flag, 0, null, "timestamp ASC");
    }

    public final TimelyEventData loadSyncedEventData(String s, long l)
    {
        if (TextUtils.isEmpty(s))
        {
            return null;
        }
        s = database.query("timelydata", TimelyContract.EVENT_EXTRAS_PROJECTION, "syncId = ? AND calendarId = ?", new String[] {
            s, Long.toString(l)
        }, null, null, null);
        boolean flag = s.moveToFirst();
        if (!flag)
        {
            s.close();
            return null;
        }
        Object obj;
        obj = s.getString(s.getColumnIndex("eventBackgroundUrl"));
        obj = new TimelyEventData(s.getString(s.getColumnIndex("structuredLocation")), s.getString(s.getColumnIndex("smartmail")), ((String) (obj)), s.getString(s.getColumnIndex("titleContacts")), s.getString(s.getColumnIndex("eventGadget")), s.getString(s.getColumnIndex("eventSource")), s.getString(s.getColumnIndex("attachments")), s.getString(s.getColumnIndex("conferenceData")), s.getString(s.getColumnIndex("responseSummary")), s.getString(s.getColumnIndex("participantStatus")), s.getString(s.getColumnIndex("attendees")));
        s.close();
        return ((TimelyEventData) (obj));
        Exception exception;
        exception;
        s.close();
        throw exception;
    }

    public final void updateDefaultNotifications(Context context1, String s, Account account, boolean flag, PreferredNotification apreferrednotification[])
    {
        updateNotifications(s, account, flag, apreferrednotification, loadNotifications(s, account, flag, 1, null, "timestamp ASC"), 1, context1);
    }

    public final void updateNotifications(String s, Account account, boolean flag, PreferredNotification apreferrednotification[], PreferredNotification apreferrednotification1[], int i, Context context1)
    {
        int j;
        int k;
        int l;
        int i1;
        String as[];
        boolean flag1;
        long l1;
        if (i == 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        database.beginTransaction();
        k = 0;
        as = new String[3];
        as[0] = s;
        as[1] = Integer.toString(i);
        Object obj;
        if (flag)
        {
            obj = "1";
        } else
        {
            obj = "0";
        }
        as[2] = ((String) (obj));
        if (!flag1) goto _L2; else goto _L1
_L1:
        if (apreferrednotification1 == null) goto _L4; else goto _L3
_L3:
        obj = apreferrednotification1;
        if (apreferrednotification1.length != 0) goto _L5; else goto _L4
_L4:
        apreferrednotification1 = TimelyStoreUtils.getRecentNotificationOptions(context1, account, flag);
        if (Clock.mockedTimestamp <= 0L) goto _L7; else goto _L6
_L6:
        l1 = Clock.mockedTimestamp;
          goto _L8
_L10:
        obj = apreferrednotification1;
        if (j >= apreferrednotification1.length) goto _L5; else goto _L9
_L9:
        context1 = apreferrednotification1[j].toContentValues();
        context1.put("lookupKey", s);
        context1.put("accountName", account.name);
        context1.put("category", Integer.toString(i));
        context1.put("timestamp", Long.valueOf((long)j + l1));
        database.insertWithOnConflict("preferrednotifications", null, context1, 5);
        j++;
          goto _L10
_L7:
        l1 = System.currentTimeMillis();
          goto _L8
_L23:
        if (k >= apreferrednotification.length) goto _L12; else goto _L11
_L11:
        i1 = 0;
_L24:
        l = j;
        if (i1 >= obj.length) goto _L14; else goto _L13
_L13:
        if (!obj[i1].equals(apreferrednotification[k])) goto _L16; else goto _L15
_L15:
        l = j + 1;
          goto _L14
_L12:
        if (Clock.mockedTimestamp <= 0L) goto _L18; else goto _L17
_L17:
        l1 = Clock.mockedTimestamp;
          goto _L19
_L22:
        if (k >= apreferrednotification.length) goto _L21; else goto _L20
_L20:
        apreferrednotification1 = apreferrednotification[k].toContentValues();
        apreferrednotification1.put("lookupKey", s);
        apreferrednotification1.put("accountName", account.name);
        apreferrednotification1.put("category", Integer.toString(i));
        apreferrednotification1.put("timestamp", Long.valueOf((long)(apreferrednotification.length - k) + l1));
        database.insertWithOnConflict("preferrednotifications", null, apreferrednotification1, 5);
        k++;
          goto _L22
_L2:
        database.delete("preferrednotifications", "lookupKey = ? AND category = ? AND allday = ?", as);
        apreferrednotification1 = account.type;
        if (!AccountUtil.EXCHANGE_TYPES.contains(apreferrednotification1) || apreferrednotification.length != 0)
        {
            break MISSING_BLOCK_LABEL_675;
        }
        apreferrednotification1 = new ContentValues();
        apreferrednotification1.put("lookupKey", s);
        apreferrednotification1.put("accountName", account.name);
        apreferrednotification1.put("category", Integer.valueOf(i));
        if (flag)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        apreferrednotification1.put("allday", Integer.valueOf(j));
        apreferrednotification1.put("noNotifications", Integer.valueOf(1));
        database.insert("preferrednotifications", null, apreferrednotification1);
        break MISSING_BLOCK_LABEL_675;
_L18:
        l1 = System.currentTimeMillis();
          goto _L19
_L21:
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_561;
        }
        i = apreferrednotification.length;
        s = (new StringBuilder(141)).append("_id in ( SELECT _id FROM preferrednotifications WHERE lookupKey = ? AND category = ? AND allday = ? ORDER BY timestamp ASC LIMIT ").append(i - j).append(")").toString();
        database.delete("preferrednotifications", s, as);
        database.setTransactionSuccessful();
        database.endTransaction();
        s = (Collection)notificationSubscribers.apply(null);
        return;
        s;
        database.endTransaction();
        account = (Collection)notificationSubscribers.apply(null);
        throw s;
_L8:
        j = 0;
          goto _L10
_L5:
        l = 0;
        j = k;
        k = l;
          goto _L23
_L14:
        k++;
        j = l;
          goto _L23
_L16:
        i1++;
          goto _L24
_L19:
        k = 0;
          goto _L22
        j = 0;
          goto _L12
    }

    public final boolean updateOrInsertOneEventField(String s, long l, String s1, String s2)
    {
        boolean flag;
        flag = true;
        if (TextUtils.isEmpty(s))
        {
            return false;
        }
        LogUtils.d("TimelyStore", "updating data for event: %s calendar: %d", new Object[] {
            s, Long.valueOf(l)
        });
        LogUtils.v("TimelyStore", "Timely %s: %s", new Object[] {
            s1, s2
        });
        database.beginTransaction();
        ContentValues contentvalues;
        contentvalues = new ContentValues(3);
        contentvalues.put(s1, s2);
        contentvalues.put("syncId", s);
        contentvalues.put("calendarId", Long.valueOf(l));
        if (database.update("timelydata", contentvalues, "syncId = ? AND calendarId = ?", new String[] {
    s, String.valueOf(l)
}) <= 0)
        {
            break MISSING_BLOCK_LABEL_155;
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        return true;
        l = database.insert("timelydata", null, contentvalues);
        database.setTransactionSuccessful();
        if (l == -1L)
        {
            flag = false;
        }
        database.endTransaction();
        return flag;
        s;
        database.endTransaction();
        throw s;
    }

    static 
    {
        BASE_CONTENT_PROVIDER_URI = TimelyContract.EVENT_EXTRAS_URI;
    }

    private class ConferenceTypeCache extends LruCache
    {

        private final TimelyStore this$0;

        private final String create(Pair pair)
        {
            Object obj;
            Object obj1;
            Object obj2;
            obj = null;
            obj2 = (Account)pair.first;
            obj1 = (String)pair.second;
            SQLiteDatabase sqlitedatabase = database;
            String s = ((Account) (obj2)).name;
            obj2 = ((Account) (obj2)).type;
            obj1 = sqlitedatabase.query("calendar_settings", new String[] {
                "conference_properties"
            }, "calendar_sync_id = ? AND account_name = ? AND account_type = ?", new String[] {
                obj1, s, obj2
            }, null, null, null);
            if (obj1 == null)
            {
                break MISSING_BLOCK_LABEL_124;
            }
            if (!((Cursor) (obj1)).moveToFirst())
            {
                break MISSING_BLOCK_LABEL_124;
            }
            obj = TimelyStore.this;
            pair = TimelyStore.parseConferenceType(pair, ((Cursor) (obj1)).getString(0));
            if (obj1 != null)
            {
                ((Cursor) (obj1)).close();
            }
            return pair;
            if (obj1 != null)
            {
                ((Cursor) (obj1)).close();
            }
            return null;
            pair;
_L2:
            if (obj != null)
            {
                ((Cursor) (obj)).close();
            }
            throw pair;
            pair;
            obj = obj1;
            if (true) goto _L2; else goto _L1
_L1:
        }

        protected final volatile Object create(Object obj)
        {
            return create((Pair)obj);
        }

        public ConferenceTypeCache(int i)
        {
            this$0 = TimelyStore.this;
            super(10000);
        }
    }


    private class DatabaseHelper extends SQLiteOpenHelper
    {

        private static void dropTable(SQLiteDatabase sqlitedatabase, String s)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "DROP TABLE IF EXISTS ".concat(s);
            } else
            {
                s = new String("DROP TABLE IF EXISTS ");
            }
            sqlitedatabase.execSQL(s);
        }

        public final void onCreate(SQLiteDatabase sqlitedatabase)
        {
            sqlitedatabase.execSQL("CREATE TABLE timelydata (_id INTEGER PRIMARY KEY,syncId TEXT,calendarId INTEGER,structuredLocation TEXT,smartmail TEXT,eventBackgroundUrl TEXT,attachments TEXT,associatedContacts TEXT,titleContacts TEXT,eventGadget TEXT,eventSource TEXT,conferenceData TEXT,responseSummary TEXT,participantStatus TEXT,attendees TEXT, UNIQUE (syncId, calendarId));");
            sqlitedatabase.execSQL("CREATE INDEX calendar_event_index ON timelydata (syncId, calendarId)");
            sqlitedatabase.execSQL("CREATE TABLE timelysettings (_id INTEGER PRIMARY KEY,accountName TEXT UNIQUE,smartMailDelivery TEXT DEFAULT CREATE_SECRET,tasksselected INTEGER DEFAULT 1,taskscolor TEXT DEFAULT \"4184f3\",defaultEventLength INTEGER DEFAULT 60,defaultNoEndTime INTEGER DEFAULT 0,settingBirthdayVisibility INTEGER DEFAULT 1,settingBirthdayIncludeGplus INTEGER DEFAULT 1,holidayscolor TEXT,autoAddHangouts INTEGER DEFAULT 0,timezone TEXT,qualityOfService TEXT DEFAULT NULL);");
            sqlitedatabase.execSQL("CREATE INDEX calendar_account_index ON timelysettings(accountName)");
            sqlitedatabase.execSQL("CREATE TABLE timelysettingslog (_id INTEGER PRIMARY KEY,accountName TEXT,id TEXT,value TEXT,flags TEXT);");
            sqlitedatabase.execSQL("CREATE INDEX settings_log_account_ordered_index ON timelysettingslog(accountName,_id)");
            sqlitedatabase.execSQL("CREATE TABLE preferrednotifications (_id INTEGER PRIMARY KEY,lookupKey TEXT,accountName TEXT,category INTEGER,allday INTEGER,minutes INTEGER,method INTEGER,timestamp INTEGER,noNotifications INTEGER DEFAULT 0, UNIQUE (lookupKey, accountName, category, allday, minutes, method));");
            sqlitedatabase.execSQL("CREATE INDEX notifications_index ON preferrednotifications(lookupKey, category)");
            sqlitedatabase.execSQL("CREATE TABLE calendar_settings (_id INTEGER PRIMARY KEY,calendar_sync_id TEXT,account_name TEXT,account_type TEXT,conference_properties TEXT, UNIQUE (calendar_sync_id, account_name, account_type));");
            sqlitedatabase.execSQL("CREATE INDEX calendar_settings_index ON calendar_settings(calendar_sync_id, account_name, account_type)");
            TimelyStoreUtils.triggerSyncAdapterRestoreTimelyData();
        }

        public final void onOpen(SQLiteDatabase sqlitedatabase)
        {
            sqlitedatabase.setMaxSqlCacheSize(25);
        }

        public final void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
        {
            i;
            JVM INSTR tableswitch 1 36: default 160
        //                       1 169
        //                       2 181
        //                       3 193
        //                       4 211
        //                       5 235
        //                       6 253
        //                       7 264
        //                       8 282
        //                       9 299
        //                       10 310
        //                       11 321
        //                       12 321
        //                       13 339
        //                       14 351
        //                       15 362
        //                       16 386
        //                       17 397
        //                       18 415
        //                       19 427
        //                       20 451
        //                       21 451
        //                       22 462
        //                       23 473
        //                       24 485
        //                       25 497
        //                       26 503
        //                       27 503
        //                       28 509
        //                       29 527
        //                       30 1266
        //                       31 1266
        //                       32 1266
        //                       33 1266
        //                       34 1260
        //                       35 1254
        //                       36 1248;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L20 _L21 _L22 _L23 _L24 _L25 _L25 _L26 _L27 _L28 _L28 _L28 _L28 _L29 _L30 _L31
_L28:
            break MISSING_BLOCK_LABEL_1266;
_L1:
            boolean flag1 = false;
_L33:
            SQLiteException sqliteexception12;
            boolean flag;
            if (!flag1)
            {
                return;
            } else
            {
                sqlitedatabase = new Bundle(3);
                sqlitedatabase.putBoolean("sync_extra_get_settings", true);
                sqlitedatabase.putBoolean("sync_extra_get_recent_notifications", true);
                sqlitedatabase.putBoolean("sync_extra_get_default_notifications", true);
                sqlitedatabase.putBoolean("metafeedonly", true);
                TimelyStoreUtils.triggerSyncAdapterSyncWithExtras(null, null, false, sqlitedatabase);
                return;
            }
_L2:
            sqlitedatabase.execSQL("CREATE TABLE timelysettings (_id INTEGER PRIMARY KEY,accountName TEXT UNIQUE,smartMailDelivery TEXT DEFAULT CREATE_SECRET,tasksselected INTEGER DEFAULT 1,taskscolor TEXT DEFAULT \"4184f3\",defaultEventLength INTEGER DEFAULT 60,defaultNoEndTime INTEGER DEFAULT 0,settingBirthdayVisibility INTEGER DEFAULT 1,settingBirthdayIncludeGplus INTEGER DEFAULT 1,holidayscolor TEXT,autoAddHangouts INTEGER DEFAULT 0,timezone TEXT,qualityOfService TEXT DEFAULT NULL);");
            sqlitedatabase.execSQL("CREATE INDEX calendar_account_index ON timelysettings(accountName)");
_L3:
            sqlitedatabase.execSQL("CREATE TABLE preferrednotifications (_id INTEGER PRIMARY KEY,lookupKey TEXT,accountName TEXT,category INTEGER,allday INTEGER,minutes INTEGER,method INTEGER,timestamp INTEGER,noNotifications INTEGER DEFAULT 0, UNIQUE (lookupKey, accountName, category, allday, minutes, method));");
            sqlitedatabase.execSQL("CREATE INDEX notifications_index ON preferrednotifications(lookupKey, category)");
_L4:
            dropTable(sqlitedatabase, "timelysettings");
            sqlitedatabase.execSQL("CREATE TABLE timelysettings (_id INTEGER PRIMARY KEY,accountName TEXT UNIQUE,smartMailDelivery TEXT DEFAULT CREATE_SECRET,tasksselected INTEGER DEFAULT 1,taskscolor TEXT DEFAULT \"4184f3\",defaultEventLength INTEGER DEFAULT 60,defaultNoEndTime INTEGER DEFAULT 0,settingBirthdayVisibility INTEGER DEFAULT 1,settingBirthdayIncludeGplus INTEGER DEFAULT 1,holidayscolor TEXT,autoAddHangouts INTEGER DEFAULT 0,timezone TEXT,qualityOfService TEXT DEFAULT NULL);");
            sqlitedatabase.execSQL("CREATE INDEX calendar_account_index ON timelysettings(accountName)");
_L5:
            dropTable(sqlitedatabase, "timelydata");
            dropTable(sqlitedatabase, "calendar_event_index");
            sqlitedatabase.execSQL("CREATE TABLE timelydata (_id INTEGER PRIMARY KEY,syncId TEXT,calendarId INTEGER,structuredLocation TEXT,smartmail TEXT,eventBackgroundUrl TEXT,attachments TEXT,associatedContacts TEXT,titleContacts TEXT,eventGadget TEXT,eventSource TEXT,conferenceData TEXT,responseSummary TEXT,participantStatus TEXT,attendees TEXT, UNIQUE (syncId, calendarId));");
            sqlitedatabase.execSQL("CREATE INDEX calendar_event_index ON timelydata (syncId, calendarId)");
_L6:
            dropTable(sqlitedatabase, "preferrednotifications");
            sqlitedatabase.execSQL("CREATE TABLE preferrednotifications (_id INTEGER PRIMARY KEY,lookupKey TEXT,accountName TEXT,category INTEGER,allday INTEGER,minutes INTEGER,method INTEGER,timestamp INTEGER,noNotifications INTEGER DEFAULT 0, UNIQUE (lookupKey, accountName, category, allday, minutes, method));");
            sqlitedatabase.execSQL("CREATE INDEX notifications_index ON preferrednotifications(lookupKey, category)");
_L7:
            if (i > 4)
            {
                try
                {
                    sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN associatedContacts TEXT");
                }
                catch (SQLiteException sqliteexception)
                {
                    LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                        "associatedContacts", Integer.valueOf(i), Integer.valueOf(j)
                    });
                }
            }
_L8:
            dropTable(sqlitedatabase, "preferrednotifications");
            sqlitedatabase.execSQL("CREATE TABLE preferrednotifications (_id INTEGER PRIMARY KEY,lookupKey TEXT,accountName TEXT,category INTEGER,allday INTEGER,minutes INTEGER,method INTEGER,timestamp INTEGER,noNotifications INTEGER DEFAULT 0, UNIQUE (lookupKey, accountName, category, allday, minutes, method));");
            sqlitedatabase.execSQL("CREATE INDEX notifications_index ON preferrednotifications(lookupKey, category)");
_L9:
            if (i > 3)
            {
                sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN tasksselected INTEGER DEFAULT 1");
                sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN taskscolor TEXT DEFAULT 4184f3");
            }
_L10:
            if (i > 4)
            {
                try
                {
                    sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN eventGadget TEXT");
                }
                catch (SQLiteException sqliteexception1)
                {
                    LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                        "eventGadget", Integer.valueOf(i), Integer.valueOf(j)
                    });
                }
            }
_L11:
            if (i > 3)
            {
                sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN defaultEventLength INTEGER DEFAULT 60");
            }
_L12:
            dropTable(sqlitedatabase, "timelysettings");
            sqlitedatabase.execSQL("CREATE TABLE timelysettings (_id INTEGER PRIMARY KEY,accountName TEXT UNIQUE,smartMailDelivery TEXT DEFAULT CREATE_SECRET,tasksselected INTEGER DEFAULT 1,taskscolor TEXT DEFAULT \"4184f3\",defaultEventLength INTEGER DEFAULT 60,defaultNoEndTime INTEGER DEFAULT 0,settingBirthdayVisibility INTEGER DEFAULT 1,settingBirthdayIncludeGplus INTEGER DEFAULT 1,holidayscolor TEXT,autoAddHangouts INTEGER DEFAULT 0,timezone TEXT,qualityOfService TEXT DEFAULT NULL);");
            sqlitedatabase.execSQL("CREATE INDEX calendar_account_index ON timelysettings(accountName)");
_L13:
            if (i > 12)
            {
                sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN holidayscolor TEXT");
            }
_L14:
            if (i > 4)
            {
                try
                {
                    sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN titleContacts TEXT");
                }
                catch (SQLiteException sqliteexception2)
                {
                    LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                        "titleContacts", Integer.valueOf(i), Integer.valueOf(j)
                    });
                }
            }
_L15:
            if (i > 12)
            {
                dropTable(sqlitedatabase, "timelysettings");
                sqlitedatabase.execSQL("CREATE TABLE timelysettings (_id INTEGER PRIMARY KEY,accountName TEXT UNIQUE,smartMailDelivery TEXT DEFAULT CREATE_SECRET,tasksselected INTEGER DEFAULT 1,taskscolor TEXT DEFAULT \"4184f3\",defaultEventLength INTEGER DEFAULT 60,defaultNoEndTime INTEGER DEFAULT 0,settingBirthdayVisibility INTEGER DEFAULT 1,settingBirthdayIncludeGplus INTEGER DEFAULT 1,holidayscolor TEXT,autoAddHangouts INTEGER DEFAULT 0,timezone TEXT,qualityOfService TEXT DEFAULT NULL);");
                sqlitedatabase.execSQL("CREATE INDEX calendar_account_index ON timelysettings(accountName)");
            }
_L16:
            if (i > 4)
            {
                try
                {
                    sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN eventSource TEXT");
                }
                catch (SQLiteException sqliteexception3)
                {
                    LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                        "eventSource", Integer.valueOf(i), Integer.valueOf(j)
                    });
                }
            }
_L17:
            dropTable(sqlitedatabase, "preferrednotifications");
            sqlitedatabase.execSQL("CREATE TABLE preferrednotifications (_id INTEGER PRIMARY KEY,lookupKey TEXT,accountName TEXT,category INTEGER,allday INTEGER,minutes INTEGER,method INTEGER,timestamp INTEGER,noNotifications INTEGER DEFAULT 0, UNIQUE (lookupKey, accountName, category, allday, minutes, method));");
            sqlitedatabase.execSQL("CREATE INDEX notifications_index ON preferrednotifications(lookupKey, category)");
_L18:
            sqlitedatabase.execSQL("CREATE TABLE timelysettingslog (_id INTEGER PRIMARY KEY,accountName TEXT,id TEXT,value TEXT,flags TEXT);");
            sqlitedatabase.execSQL("CREATE INDEX settings_log_account_ordered_index ON timelysettingslog(accountName,_id)");
_L19:
            if (i > 12)
            {
                dropTable(sqlitedatabase, "timelysettings");
                sqlitedatabase.execSQL("CREATE TABLE timelysettings (_id INTEGER PRIMARY KEY,accountName TEXT UNIQUE,smartMailDelivery TEXT DEFAULT CREATE_SECRET,tasksselected INTEGER DEFAULT 1,taskscolor TEXT DEFAULT \"4184f3\",defaultEventLength INTEGER DEFAULT 60,defaultNoEndTime INTEGER DEFAULT 0,settingBirthdayVisibility INTEGER DEFAULT 1,settingBirthdayIncludeGplus INTEGER DEFAULT 1,holidayscolor TEXT,autoAddHangouts INTEGER DEFAULT 0,timezone TEXT,qualityOfService TEXT DEFAULT NULL);");
                sqlitedatabase.execSQL("CREATE INDEX calendar_account_index ON timelysettings(accountName)");
            }
_L20:
            if (i > 4)
            {
                try
                {
                    sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN attachments TEXT");
                }
                catch (SQLiteException sqliteexception4)
                {
                    LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                        "attachments", Integer.valueOf(i), Integer.valueOf(j)
                    });
                }
            }
_L21:
            if (i > 4)
            {
                try
                {
                    sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN conferenceData TEXT");
                }
                catch (SQLiteException sqliteexception5)
                {
                    LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                        "conferenceData", Integer.valueOf(i), Integer.valueOf(j)
                    });
                }
            }
_L22:
            if (i > 19)
            {
                try
                {
                    sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN autoAddHangouts TEXT");
                }
                catch (SQLiteException sqliteexception6)
                {
                    LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                        "autoAddHangouts", Integer.valueOf(i), Integer.valueOf(j)
                    });
                }
            }
_L23:
            if (i > 19)
            {
                try
                {
                    sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN settingBirthdayIncludeGplus INTEGER DEFAULT 1");
                }
                catch (SQLiteException sqliteexception7)
                {
                    LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                        "settingBirthdayIncludeGplus", Integer.valueOf(i), Integer.valueOf(j)
                    });
                }
            }
_L24:
            try
            {
                sqlitedatabase.execSQL("ALTER TABLE preferrednotifications ADD COLUMN noNotifications INTEGER DEFAULT 0");
            }
            catch (SQLiteException sqliteexception8)
            {
                LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                    "preferrednotifications", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
_L25:
            try
            {
                sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN timezone TEXT");
            }
            catch (SQLiteException sqliteexception9)
            {
                LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                    "timezone", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
_L26:
            if (i >= 27)
            {
                try
                {
                    sqlitedatabase.execSQL("ALTER TABLE timelysettings DROP COLUMN isGoogleDomainUser");
                }
                catch (SQLiteException sqliteexception10)
                {
                    LogUtils.e("TimelyStore", "Can not drop column isGoogleDomainUser on upgrade from %d to %d", new Object[] {
                        Integer.valueOf(i), Integer.valueOf(j)
                    });
                }
            }
            try
            {
                sqlitedatabase.execSQL("ALTER TABLE timelysettings ADD COLUMN qualityOfService TEXT DEFAULT NULL");
            }
            catch (SQLiteException sqliteexception11)
            {
                LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                    "qualityOfService", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
_L27:
            sqlitedatabase.execSQL("CREATE TABLE calendar_settings (_id INTEGER PRIMARY KEY,calendar_sync_id TEXT,account_name TEXT,account_type TEXT,conference_properties TEXT, UNIQUE (calendar_sync_id, account_name, account_type));");
            sqlitedatabase.execSQL("CREATE INDEX calendar_settings_index ON calendar_settings(calendar_sync_id, account_name, account_type)");
            flag1 = true;
_L37:
            flag = flag1;
            if (i <= 4)
            {
                break MISSING_BLOCK_LABEL_561;
            }
            sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN responseSummary TEXT");
            flag = flag1;
_L34:
            flag1 = flag;
            if (i <= 4)
            {
                break MISSING_BLOCK_LABEL_580;
            }
            sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN participantStatus TEXT");
            flag1 = flag;
_L35:
            flag = flag1;
            if (i <= 30)
            {
                break MISSING_BLOCK_LABEL_600;
            }
            sqlitedatabase.execSQL("ALTER TABLE timelydata DROP COLUMN deepLinkData");
            flag = flag1;
_L36:
            flag1 = flag;
            if (i <= 4) goto _L33; else goto _L32
_L32:
            sqlitedatabase.execSQL("ALTER TABLE timelydata ADD COLUMN attendees TEXT");
            flag1 = flag;
              goto _L33
            sqlitedatabase;
            LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                "attendees", Integer.valueOf(i), Integer.valueOf(j)
            });
            flag1 = flag;
              goto _L33
            sqliteexception12;
            LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                "responseSummary", Integer.valueOf(i), Integer.valueOf(j)
            });
            flag = flag1;
              goto _L34
            sqliteexception12;
            LogUtils.e("TimelyStore", "Can not add column %s on upgrade from %d to %d", new Object[] {
                "participantStatus", Integer.valueOf(i), Integer.valueOf(j)
            });
            flag1 = flag;
              goto _L35
            sqliteexception12;
            LogUtils.e("TimelyStore", "Can not drop column deepLinkData on upgrade from %d to %d", new Object[] {
                Integer.valueOf(i), Integer.valueOf(j)
            });
            flag = flag1;
              goto _L36
_L31:
            flag = false;
              goto _L36
_L30:
            flag1 = false;
              goto _L35
_L29:
            flag = false;
              goto _L34
            flag1 = false;
              goto _L37
        }

        public DatabaseHelper(Context context1, int i)
        {
            super(context1, "timelydata.db", null, 37, new DefaultDatabaseErrorHandler());
            setWriteAheadLoggingEnabled(true);
        }
    }


    private class Notifications
    {

        public final Map defaultNotificationsMap;
        public final Map recentNotificationsMap;

        public Notifications(Map map, Map map1)
        {
            recentNotificationsMap = map;
            defaultNotificationsMap = map1;
        }
    }


    private class RecentNotifications
    {

        public final List notifications = new ArrayList();

        public RecentNotifications()
        {
        }
    }


    private class DefaultNotifications
    {

        public static final PreferredNotification EXCHANGE_DEFAULT_ALL_DAY_NOTIFICATION = new PreferredNotification(1, 900, 1);
        public static final PreferredNotification EXCHANGE_DEFAULT_TIMED_NOTIFICATION = new PreferredNotification(0, 10, 1);
        public final List allDayNotifications = new ArrayList();
        public final boolean isExchange;
        public boolean noAllDayNotifications;
        public boolean noTimedNotifications;
        public final List timedNotifications = new ArrayList();

        public static PreferredNotification getExchangeInitialDefaultNotification(boolean flag)
        {
            if (flag)
            {
                return EXCHANGE_DEFAULT_ALL_DAY_NOTIFICATION;
            } else
            {
                return EXCHANGE_DEFAULT_TIMED_NOTIFICATION;
            }
        }


        public DefaultNotifications(Account account)
        {
            noTimedNotifications = false;
            noAllDayNotifications = false;
            account = account.type;
            isExchange = AccountUtil.EXCHANGE_TYPES.contains(account);
        }
    }

}
