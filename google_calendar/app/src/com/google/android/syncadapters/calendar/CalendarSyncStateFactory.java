// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Entity;
import android.content.EntityIterator;
import android.content.SyncResult;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Pair;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apiary.ParseException;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.syncadapters.timely.sql.ColumnConstants;
import com.google.android.apps.calendar.syncadapters.timely.sql.SQLiteDatabaseUtils;
import com.google.android.calendar.api.habit.HabitIdTypeUtil;
import com.google.android.calendar.api.habit.HabitSyncUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.gsf.Gservices;
import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.http.HttpResponseException;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Habit;
import com.google.api.services.calendar.model.HabitData;
import com.google.api.services.calendar.model.Habits;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            ProviderHelper, Utilities, CalendarSyncAdapterApiary, CalendarSyncState, 
//            CalendarSyncStateUtils, HtcMailIssueResyncTrigger, EventHandler, CalendarRequestExecutor, 
//            SyncLog, FeedState

public class CalendarSyncStateFactory
{

    private static final String QUERY_PROJECTION[] = {
        "_id", "sync_events", "cal_sync1", "cal_sync4", "cal_sync5"
    };
    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/CalendarSyncStateFactory);

    public CalendarSyncStateFactory()
    {
    }

    private static void addTimelyDataToExistingEvents(CalendarSyncAdapterApiary calendarsyncadapterapiary, ContentProviderClient contentproviderclient, Account account, CalendarSyncState calendarsyncstate)
        throws RemoteException, IOException, ParseException
    {
        Cursor cursor;
        cursor = ProviderHelper.asSyncAdapter(account).query(contentproviderclient, android.provider.CalendarContract.Calendars.CONTENT_URI, new String[] {
            "_id", "ownerAccount"
        }, "sync_events=?", new String[] {
            "1"
        }, null);
        String as[] = new String[2];
        String s;
        TimeRange timerange;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        as[0] = String.valueOf(l);
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_178;
        }
        do
        {
            if (!cursor.moveToNext())
            {
                break MISSING_BLOCK_LABEL_171;
            }
            l = cursor.getLong(0);
            s = cursor.getString(1);
            as[1] = String.valueOf(l);
            timerange = Utilities.getEventsRange(contentproviderclient, ColumnConstants.WHERE_DT_START_GT_AND_EVENTS_CALENDAR_ID, as);
        } while (timerange == null);
        if (!calendarsyncadapterapiary.saveTimelyDataForEventRange(contentproviderclient, account, s, l, timerange, calendarsyncstate))
        {
            throw new IOException("The upgrade was cancelled.");
        } else
        {
            break MISSING_BLOCK_LABEL_72;
        }
        calendarsyncadapterapiary;
        cursor.close();
        throw calendarsyncadapterapiary;
        cursor.close();
    }

    private static CalendarSyncState create(ContentProviderClient contentproviderclient, Account account, Context context)
        throws RemoteException, ParseException
    {
        ContentValues contentvalues = new ContentValues();
        CalendarSyncState calendarsyncstate = new CalendarSyncState(15);
        context = context.getApplicationContext().getPackageName();
        try
        {
            calendarsyncstate.data.put("package", context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            LogUtils.e("CalendarSyncAdapter", context, "Failed to set syncing package.", new Object[0]);
        }
        contentvalues.put("data", calendarsyncstate.data.toString().getBytes());
        contentvalues.put("account_name", account.name);
        contentvalues.put("account_type", account.type);
        return new CalendarSyncState(ProviderHelper.asSyncAdapter(account).insert(contentproviderclient, android.provider.CalendarContract.SyncState.CONTENT_URI, contentvalues), calendarsyncstate);
    }

    private static Method findUpgradeMethod(int i)
    {
        Method method;
        try
        {
            method = com/google/android/syncadapters/calendar/CalendarSyncStateFactory.getMethod((new StringBuilder(22)).append("upgradeFrom").append(i).toString(), new Class[] {
                com/google/android/syncadapters/calendar/CalendarSyncAdapterApiary, com/google/android/syncadapters/calendar/CalendarSyncState, android/content/Context, android/content/ContentProviderClient, android/accounts/Account
            });
        }
        catch (NoSuchMethodException nosuchmethodexception)
        {
            throw new IllegalStateException((new StringBuilder(41)).append("Missing upgrade from version: ").append(i).toString(), nosuchmethodexception);
        }
        return method;
    }

    public static CalendarSyncState getOrCreate(CalendarSyncAdapterApiary calendarsyncadapterapiary, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException, ParseException
    {
        Object obj = android.provider.SyncStateContract.Helpers.getWithUri(contentproviderclient, android.provider.CalendarContract.SyncState.CONTENT_URI, account);
        if (obj == null)
        {
            return create(contentproviderclient, account, context);
        }
        obj = CalendarSyncStateUtils.fromBytes(ProviderHelper.toAsSyncAdapterUri((Uri)((Pair) (obj)).first, account), (byte[])((Pair) (obj)).second, contentproviderclient, account);
        if (obj == null)
        {
            LogUtils.w(TAG, "Can't upgrade, wipe and resync", new Object[0]);
            LogUtils.i(TAG, "Upgrading to Apiary Sync Adapter", new Object[0]);
            wipeEventsAndCalendars(context, contentproviderclient, account);
            LogUtils.d(TAG, "Requesting full sync in CalendarSyncState#getSyncStateForWipeAndResync", new Object[0]);
            calendarsyncadapterapiary = new Bundle();
            obj = Features.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            } else
            {
                ((FeatureConfig)obj).fishfood_debug();
                ContentResolver.requestSync(account, "com.android.calendar", calendarsyncadapterapiary);
                return create(contentproviderclient, account, context);
            }
        }
        obj.originalVersion = ((CalendarSyncState) (obj)).getVersion();
        int i;
        for (i = ((CalendarSyncState) (obj)).originalVersion; i < 15; i = upgradeFrom(i, calendarsyncadapterapiary, ((CalendarSyncState) (obj)), context, contentproviderclient, account)) { }
        if (i > 15)
        {
            LogUtils.w(TAG, "Wipe Data on Downgrade from %d to %d", new Object[] {
                Integer.valueOf(i), Integer.valueOf(15)
            });
            wipeEventsAndCalendars(context, contentproviderclient, account);
            ((CalendarSyncState) (obj)).reset();
            LogUtils.d(TAG, "Requesting full sync in CalendarSyncState#forceFullSync", new Object[0]);
            Bundle bundle = new Bundle();
            FeatureConfig featureconfig = Features.instance;
            if (featureconfig == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)featureconfig).fishfood_debug();
            ContentResolver.requestSync(account, "com.android.calendar", bundle);
            ((CalendarSyncState) (obj)).setVersion(15);
            android.provider.SyncStateContract.Helpers.update(contentproviderclient, ((CalendarSyncState) (obj)).uri, ((CalendarSyncState) (obj)).data.toString().getBytes());
        }
        if (!((CalendarSyncState) (obj)).isJellyBean())
        {
            CalendarSyncStateUtils.transformSyncIds(contentproviderclient, account, "%\n%", new _cls1());
            ((CalendarSyncState) (obj)).setJellyBean(true);
            android.provider.SyncStateContract.Helpers.update(contentproviderclient, ((CalendarSyncState) (obj)).uri, ((CalendarSyncState) (obj)).data.toString().getBytes());
        }
        String s = ((CalendarSyncState) (obj)).getSyncingPackage();
        String s1 = context.getApplicationContext().getPackageName();
        if (!s1.equals(s))
        {
            if ("com.google.android.syncadapters.calendar".equals(s) && "com.google.android.calendar".equals(s1))
            {
                try
                {
                    addTimelyDataToExistingEvents(calendarsyncadapterapiary, contentproviderclient, account, ((CalendarSyncState) (obj)));
                }
                // Misplaced declaration of an exception variable
                catch (CalendarSyncAdapterApiary calendarsyncadapterapiary)
                {
                    throw new IllegalStateException("Failed to upgrade package", calendarsyncadapterapiary);
                }
            }
            ((CalendarSyncState) (obj)).setSyncingPackage(s1);
            android.provider.SyncStateContract.Helpers.update(contentproviderclient, ((CalendarSyncState) (obj)).uri, ((CalendarSyncState) (obj)).data.toString().getBytes());
        }
        HtcMailIssueResyncTrigger.process(((CalendarSyncState) (obj)), context, contentproviderclient, account);
        return ((CalendarSyncState) (obj));
    }

    private static int upgradeFrom(int i, CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException
    {
        Method method = findUpgradeMethod(i);
        int j = ((Integer)method.invoke(new CalendarSyncStateFactory(), new Object[] {
            calendarsyncadapterapiary, calendarsyncstate, context, contentproviderclient, account
        })).intValue();
        if (j >= 0) goto _L2; else goto _L1
_L1:
        int k = i + 1;
_L6:
        calendarsyncstate.data.put("version", k);
_L3:
        android.provider.SyncStateContract.Helpers.update(contentproviderclient, calendarsyncstate.uri, calendarsyncstate.data.toString().getBytes());
        LogUtils.i(TAG, "Completed upgrade from version %d to %d", new Object[] {
            Integer.valueOf(i), Integer.valueOf(k)
        });
        return k;
_L5:
        try
        {
            calendarsyncadapterapiary = method.getName();
            throw new IllegalStateException((new StringBuilder(String.valueOf(calendarsyncadapterapiary).length() + 80)).append("Upgrade method ").append(calendarsyncadapterapiary).append(" returned invalid new version: ").append(j).append(" <= ").append(i).append(" or > 15").toString());
        }
        // Misplaced declaration of an exception variable
        catch (CalendarSyncAdapterApiary calendarsyncadapterapiary)
        {
            calendarsyncstate = calendarsyncadapterapiary.getCause();
        }
        // Misplaced declaration of an exception variable
        catch (CalendarSyncAdapterApiary calendarsyncadapterapiary)
        {
            throw new IllegalStateException("Failed to invoke upgrade Method", calendarsyncadapterapiary);
        }
        if (calendarsyncstate == null)
        {
            throw new IllegalStateException("Invocation failed with null cause.", calendarsyncadapterapiary);
        }
        break MISSING_BLOCK_LABEL_250;
        calendarsyncadapterapiary;
        LogUtils.e("CalendarSyncAdapter", calendarsyncadapterapiary, "Failed to set version.", new Object[0]);
          goto _L3
        if (calendarsyncstate instanceof RuntimeException)
        {
            throw (RuntimeException)calendarsyncstate;
        }
        if (calendarsyncstate instanceof RemoteException)
        {
            throw (RemoteException)calendarsyncstate;
        }
        if (calendarsyncstate instanceof IOException)
        {
            throw (IOException)calendarsyncstate;
        } else
        {
            throw new IllegalStateException("Failed to invoke upgrade Method", calendarsyncstate);
        }
_L2:
        if (j <= i) goto _L5; else goto _L4
_L4:
        k = j;
        if (j <= 15) goto _L6; else goto _L5
    }

    private static void wipeEventsAndCalendars(Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, ParseException
    {
        Cursor cursor = ProviderHelper.asSyncAdapter(account).query(contentproviderclient, android.provider.CalendarContract.Calendars.CONTENT_URI, new String[] {
            "calendar_displayName", "sync_events", "visible"
        }, null, null, null);
        if (cursor == null) goto _L2; else goto _L1
_L1:
        Object obj = String.valueOf(account.name);
        if (((String) (obj)).length() == 0) goto _L4; else goto _L3
_L3:
        obj = "saved-calendar-settings-".concat(((String) (obj)));
_L5:
        context = new PrintWriter(context.openFileOutput(((String) (obj)), 0));
        for (; cursor.moveToNext(); context.printf("%d:%d:%s\n", new Object[] {
    Integer.valueOf(cursor.getInt(1)), Integer.valueOf(cursor.getInt(2)), obj
}))
        {
            obj = cursor.getString(0);
        }

        break MISSING_BLOCK_LABEL_285;
        obj;
        try
        {
            context.close();
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch (Context context) { }
        finally
        {
            cursor.close();
        }
        LogUtils.e(TAG, context, "Failed to create save file: %s%s", new Object[] {
            "saved-calendar-settings-", LogUtils.sanitizeAccountName(TAG, account.name)
        });
_L6:
        cursor.close();
_L2:
        ProviderHelper.asSyncAdapter(account).delete(contentproviderclient, android.provider.CalendarContract.Calendars.CONTENT_URI, "account_name=? AND account_type=?", new String[] {
            account.name, account.type
        });
        ProviderHelper.asSyncAdapter(account).delete(contentproviderclient, android.provider.CalendarContract.Events.CONTENT_URI, "account_name=? AND account_type=?", new String[] {
            account.name, account.type
        });
        return;
_L4:
        obj = new String("saved-calendar-settings-");
          goto _L5
        throw context;
        context.close();
          goto _L6
    }

    public int upgradeFrom0(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException, ParseException
    {
        wipeEventsAndCalendars(context, contentproviderclient, account);
        calendarsyncstate.reset();
        LogUtils.d(TAG, "Requesting full sync in CalendarSyncState#forceFullSync", new Object[0]);
        calendarsyncadapterapiary = new Bundle();
        calendarsyncstate = Features.instance;
        if (calendarsyncstate == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)calendarsyncstate).fishfood_debug();
            ContentResolver.requestSync(account, "com.android.calendar", calendarsyncadapterapiary);
            return -1;
        }
    }

    public int upgradeFrom1(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException, ParseException
    {
        calendarsyncadapterapiary = new ContentValues();
        calendarsyncadapterapiary.put("allowedReminders", "0,1,2");
        ProviderHelper.asSyncAdapter(account).update(contentproviderclient, android.provider.CalendarContract.Calendars.CONTENT_URI, calendarsyncadapterapiary, "account_name=? AND account_type=?", new String[] {
            account.name, account.type
        });
        return -1;
    }

    public int upgradeFrom10(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException, ParseException
    {
        return calendarsyncadapterapiary.restoreGrooveDataForEvents(contentproviderclient, account);
    }

    public int upgradeFrom11(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
    {
        return -1;
    }

    public int upgradeFrom12(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
    {
        return -1;
    }

    public int upgradeFrom13(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException, ParseException
    {
        calendarsyncstate = new com.google.api.services.calendar.Calendar.Habits(calendarsyncadapterapiary.client);
        com.google.api.services.calendar.Calendar.Habits.List list = new com.google.api.services.calendar.Calendar.Habits.List(calendarsyncstate, account.name);
        ((com.google.api.services.calendar.Calendar.Habits) (calendarsyncstate)).this$0.initialize(list);
        String as[] = new String[1];
        ContentValues contentvalues = new ContentValues();
        do
        {
            Habits habits = calendarsyncadapterapiary.getHabitsListLogged(context, contentproviderclient, list, account);
            List list1 = habits.items;
            int i = 0;
            while (i < list1.size()) 
            {
                Habit habit = (Habit)list1.get(i);
                calendarsyncstate = habit.id;
                String s = habit.habitData.type;
                int j;
                if (s != null)
                {
                    calendarsyncstate = HabitIdTypeUtil.createHabitIdTypeStringFromApiType(calendarsyncstate, HabitSyncUtils.serverTypeToApiType(s));
                }
                contentvalues.clear();
                contentvalues.put("sync_data8", calendarsyncstate);
                as[0] = habit.id;
                j = ProviderHelper.asSyncAdapter(account).update(contentproviderclient, android.provider.CalendarContract.Events.CONTENT_URI, contentvalues, "sync_data8=?", as);
                LogUtils.v("CalendarSyncAdapter", "Account: %s, Habit: %s, Value: %s, Updated: %d", new Object[] {
                    account.name, habit.habitData.summary, calendarsyncstate, Integer.valueOf(j)
                });
                if (j > 0)
                {
                    context.getContentResolver().notifyChange(ProviderHelper.toAsSyncAdapterUri(android.provider.CalendarContract.Events.CONTENT_URI, account), null, true);
                }
                i++;
            }
            list.pageToken = habits.nextPageToken;
        } while (list.pageToken != null);
        return -1;
    }

    public int upgradeFrom14(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException, ParseException
    {
        return -1;
    }

    public int upgradeFrom15(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException, ParseException
    {
        calendarsyncadapterapiary = new ContentValues(1);
        calendarsyncadapterapiary.put("allowedReminders", "0,1,2,4");
        ProviderHelper.asSyncAdapter(account).update(contentproviderclient, android.provider.CalendarContract.Calendars.CONTENT_URI, calendarsyncadapterapiary, "account_name=? AND account_type=?", new String[] {
            account.name, account.type
        });
        return -1;
    }

    public int upgradeFrom2(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException, ParseException
    {
        context = calendarsyncadapterapiary.getContext().getContentResolver();
        calendarsyncstate = ProviderHelper.asSyncAdapter(account).query(contentproviderclient, android.provider.CalendarContract.Calendars.CONTENT_URI, new String[] {
            "cal_sync1"
        }, "sync_events=1", null, null);
        if (calendarsyncstate == null)
        {
            break MISSING_BLOCK_LABEL_452;
        }
        int i = Gservices.getInt(context, "google_calendar_sync_max_attendees", 50);
_L3:
        String s;
        EventHandler eventhandler;
        if (!calendarsyncstate.moveToNext())
        {
            break MISSING_BLOCK_LABEL_446;
        }
        s = calendarsyncstate.getString(0);
        eventhandler = new EventHandler(calendarsyncadapterapiary.client, account, contentproviderclient, context, s, calendarsyncadapterapiary.syncHooks, calendarsyncadapterapiary.requestExecutor, calendarsyncadapterapiary.timelySync);
        Object obj = eventhandler.newEntityIterator(SQLiteDatabaseUtils.makeWhere(new String[] {
            "cal_sync1=?", "dirty=0", "lastSynced=0", "_sync_id IS NOT NULL", "(guestsCanInviteOthers=0 OR guestsCanSeeGuests=0)"
        }), new String[] {
            s
        }, -1);
        SyncResult syncresult;
        ArrayList arraylist;
        syncresult = new SyncResult();
        arraylist = new ArrayList();
_L2:
        Entity entity;
        Object obj1;
        if (!((EntityIterator) (obj)).hasNext())
        {
            break MISSING_BLOCK_LABEL_391;
        }
        entity = (Entity)((EntityIterator) (obj)).next();
        obj1 = entity.getEntityValues().getAsString("_sync_id");
        CalendarRequestExecutor calendarrequestexecutor = calendarsyncadapterapiary.requestExecutor;
        com.google.api.services.calendar.Calendar.Events events = new com.google.api.services.calendar.Calendar.Events(calendarsyncadapterapiary.client);
        obj1 = new com.google.api.services.calendar.Calendar.Events.Get(events, s, ((String) (obj1)));
        events.this$0.initialize(((com.google.api.client.googleapis.services.AbstractGoogleClientRequest) (obj1)));
        obj1.maxAttendees = Integer.valueOf(i);
        eventhandler.applyItemToEntity(arraylist, (Event)calendarrequestexecutor.execute("API: Get Event", ((com.google.api.services.calendar.CalendarRequest) (obj1))), entity, false, syncresult, null);
        int j = arraylist.size();
        if (j <= 20) goto _L2; else goto _L1
_L1:
        LogUtils.i("CalendarSyncAdapter", "Repairing %d events", new Object[] {
            Integer.valueOf(j)
        });
        Utilities.applyOperationsAsSyncAdapter(contentproviderclient, account, arraylist);
          goto _L2
        Exception exception;
        exception;
        try
        {
            ((EntityIterator) (obj)).close();
            throw exception;
        }
        // Misplaced declaration of an exception variable
        catch (Object obj) { }
        finally
        {
            calendarsyncstate.close();
        }
        LogUtils.wtf("CalendarSyncAdapter", ((Throwable) (obj)), "Failed to repair events on upgrade.", new Object[0]);
          goto _L3
        throw calendarsyncadapterapiary;
        HttpResponseException httpresponseexception;
        httpresponseexception;
        SyncLog.logErrorType(httpresponseexception, entity, "Failed to resync event");
          goto _L2
        int k = arraylist.size();
        if (arraylist.size() > 0)
        {
            LogUtils.i("CalendarSyncAdapter", "Repairing %d events", new Object[] {
                Integer.valueOf(k)
            });
            Utilities.applyOperationsAsSyncAdapter(contentproviderclient, account, arraylist);
        }
        ((EntityIterator) (obj)).close();
          goto _L3
        calendarsyncstate.close();
        return -1;
    }

    public int upgradeFrom3(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException, ParseException
    {
        return -1;
    }

    public int upgradeFrom4(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException, ParseException
    {
        calendarsyncstate = ProviderHelper.asClient().query(contentproviderclient, android.provider.CalendarContract.Events.CONTENT_URI, null, "rrule LIKE '%;UNTIL=%'", null, null);
        if (calendarsyncstate == null) goto _L2; else goto _L1
_L1:
        context = new ArrayList();
_L6:
        if (!calendarsyncstate.moveToNext()) goto _L4; else goto _L3
_L3:
        Object obj;
        obj = calendarsyncstate.getString(calendarsyncstate.getColumnIndexOrThrow("rrule"));
        calendarsyncadapterapiary = Utilities.sanitizeRecurrence(((String) (obj)));
        if (calendarsyncadapterapiary.equals(obj)) goto _L6; else goto _L5
_L5:
        ProviderHelper providerhelper;
        obj = new ContentValues();
        long l = calendarsyncstate.getLong(calendarsyncstate.getColumnIndexOrThrow("_id"));
        ((ContentValues) (obj)).put("_id", Long.valueOf(l));
        ((ContentValues) (obj)).put("rrule", calendarsyncadapterapiary);
        DatabaseUtils.cursorIntToContentValuesIfPresent(calendarsyncstate, ((ContentValues) (obj)), "eventStatus");
        DatabaseUtils.cursorLongToContentValuesIfPresent(calendarsyncstate, ((ContentValues) (obj)), "dtstart");
        DatabaseUtils.cursorLongToContentValuesIfPresent(calendarsyncstate, ((ContentValues) (obj)), "dtend");
        DatabaseUtils.cursorStringToContentValuesIfPresent(calendarsyncstate, ((ContentValues) (obj)), "duration");
        DatabaseUtils.cursorStringToContentValuesIfPresent(calendarsyncstate, ((ContentValues) (obj)), "eventTimezone");
        DatabaseUtils.cursorStringToContentValuesIfPresent(calendarsyncstate, ((ContentValues) (obj)), "allDay");
        DatabaseUtils.cursorStringToContentValuesIfPresent(calendarsyncstate, ((ContentValues) (obj)), "rdate");
        DatabaseUtils.cursorStringToContentValuesIfPresent(calendarsyncstate, ((ContentValues) (obj)), "exrule");
        DatabaseUtils.cursorStringToContentValuesIfPresent(calendarsyncstate, ((ContentValues) (obj)), "exdate");
        providerhelper = ProviderHelper.asSyncAdapter(account);
        calendarsyncadapterapiary = ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, l);
        int i;
        if (providerhelper.account != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L7
_L8:
        context.add(ContentProviderOperation.newUpdate(calendarsyncadapterapiary).withValues(((ContentValues) (obj))).build());
          goto _L6
        calendarsyncadapterapiary;
        calendarsyncstate.close();
        throw calendarsyncadapterapiary;
_L9:
        calendarsyncadapterapiary = ProviderHelper.toAsSyncAdapterUri(calendarsyncadapterapiary, providerhelper.account);
        break; /* Loop/switch isn't completed */
_L4:
        calendarsyncstate.close();
        calendarsyncadapterapiary = new ArrayList();
        calendarsyncstate = (ArrayList)context;
        int k = calendarsyncstate.size();
        i = 0;
        do
        {
            if (i >= k)
            {
                break;
            }
            context = ((Context) (calendarsyncstate.get(i)));
            int j = i + 1;
            calendarsyncadapterapiary.add((ContentProviderOperation)context);
            i = j;
            if (calendarsyncadapterapiary.size() > 100)
            {
                CalendarSyncStateUtils.applyOperationsAsSyncAdapter(contentproviderclient, account, calendarsyncadapterapiary);
                calendarsyncadapterapiary.clear();
                i = j;
            }
        } while (true);
        if (calendarsyncadapterapiary.size() > 0)
        {
            CalendarSyncStateUtils.applyOperationsAsSyncAdapter(contentproviderclient, account, calendarsyncadapterapiary);
        }
_L2:
        return -1;
_L7:
        if (i != 0) goto _L9; else goto _L8
    }

    public int upgradeFrom5(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException, ParseException
    {
        Uri uri;
        calendarsyncadapterapiary = context.getPackageManager().getInstalledPackages(128).iterator();
        boolean flag = false;
        do
        {
            if (!calendarsyncadapterapiary.hasNext())
            {
                break;
            }
            if ("com.google.android.syncadapters.calendar".equals(((PackageInfo)calendarsyncadapterapiary.next()).applicationInfo.packageName))
            {
                flag = true;
            }
        } while (true);
        if (!flag)
        {
            return -1;
        }
        uri = android.provider.CalendarContract.Calendars.CONTENT_URI;
        calendarsyncadapterapiary = ProviderHelper.asClient().query(contentproviderclient, uri, QUERY_PROJECTION, "account_name=? AND account_type=?", new String[] {
            account.name, account.type
        }, null);
        if (calendarsyncadapterapiary == null)
        {
            break MISSING_BLOCK_LABEL_434;
        }
        if (!calendarsyncadapterapiary.moveToFirst()) goto _L2; else goto _L1
_L1:
        context = new ArrayList();
_L4:
        int i = calendarsyncadapterapiary.getInt(calendarsyncadapterapiary.getColumnIndex("sync_events"));
        if (i == 1)
        {
            calendarsyncadapterapiary.close();
            return -1;
        }
        context.add(calendarsyncadapterapiary.getString(calendarsyncadapterapiary.getColumnIndex("cal_sync1")));
        if (calendarsyncadapterapiary.moveToNext()) goto _L4; else goto _L3
_L3:
        int j;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("sync_events", Integer.valueOf(1));
        contentvalues.put("visible", Integer.valueOf(1));
        ProviderHelper.asClient().update(contentproviderclient, uri, contentvalues, "account_name=? AND account_type=? AND cal_sync4=? AND cal_sync5=?", new String[] {
            account.name, account.type, "1", "0"
        });
        context = (ArrayList)context;
        j = context.size();
        i = 0;
_L6:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        account = ((Account) (context.get(i)));
        i++;
        account = calendarsyncstate.getFeedState((String)account);
        if (account == null)
        {
            break MISSING_BLOCK_LABEL_322;
        }
        account.clear();
        android.provider.SyncStateContract.Helpers.update(contentproviderclient, calendarsyncstate.uri, calendarsyncstate.data.toString().getBytes());
        if (true) goto _L6; else goto _L5
        calendarsyncstate;
        calendarsyncadapterapiary.close();
        throw calendarsyncstate;
_L2:
        wipeEventsAndCalendars(context, contentproviderclient, account);
        calendarsyncstate.reset();
        LogUtils.d(TAG, "Requesting full sync in CalendarSyncState#forceFullSync", new Object[0]);
        calendarsyncstate = new Bundle();
        context = Features.instance;
        if (context != null)
        {
            break MISSING_BLOCK_LABEL_409;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)context).fishfood_debug();
        ContentResolver.requestSync(account, "com.android.calendar", calendarsyncstate);
_L5:
        calendarsyncadapterapiary.close();
        return -1;
    }

    public int upgradeFrom6(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException
    {
        return -1;
    }

    public int upgradeFrom7(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException, ParseException
    {
        addTimelyDataToExistingEvents(calendarsyncadapterapiary, contentproviderclient, account, calendarsyncstate);
        return -1;
    }

    public int upgradeFrom8(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException, ParseException
    {
        context = ProviderHelper.asSyncAdapter(account).query(contentproviderclient, android.provider.CalendarContract.Events.CONTENT_URI, new String[] {
            "_id", "sync_data9", "sync_data10"
        }, "sync_data9='true' OR sync_data9='false'", null, null);
        if (context == null) goto _L2; else goto _L1
_L1:
        if (!context.moveToFirst()) goto _L4; else goto _L3
_L3:
        ArrayList arraylist;
        int i;
        int j;
        i = context.getColumnIndex("sync_data9");
        j = context.getColumnIndex("sync_data10");
        arraylist = new ArrayList();
_L13:
        long l = context.getLong(context.getColumnIndexOrThrow("_id"));
        if (i == -1) goto _L6; else goto _L5
_L5:
        if (context.isNull(i)) goto _L6; else goto _L7
_L7:
        calendarsyncadapterapiary = context.getString(i);
_L14:
        if (j == -1) goto _L9; else goto _L8
_L8:
        if (context.isNull(j)) goto _L9; else goto _L10
_L10:
        calendarsyncstate = context.getString(j);
_L15:
        Object obj;
        obj = EventHandler.upgradeTimelyExtrasFlags(calendarsyncadapterapiary, calendarsyncstate);
        LogUtils.i(TAG, "Packing SYNC_DATA9, SYNC_DATA10 for event %d  From: %s, %s  To: %s", new Object[] {
            Long.valueOf(l), calendarsyncadapterapiary, calendarsyncstate, obj
        });
        calendarsyncstate = new ContentValues(3);
        calendarsyncstate.put("_id", Long.valueOf(l));
        calendarsyncstate.put("sync_data9", ((String) (obj)));
        calendarsyncstate.put("sync_data10", "");
        calendarsyncadapterapiary = ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, l);
        obj = ProviderHelper.asSyncAdapter(account);
        boolean flag;
        if (((ProviderHelper) (obj)).account != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L11
_L16:
        arraylist.add(ContentProviderOperation.newUpdate(calendarsyncadapterapiary).withYieldAllowed(true).withValues(calendarsyncstate).build());
        if (arraylist.size() > 100)
        {
            CalendarSyncStateUtils.applyOperationsAsSyncAdapter(contentproviderclient, account, arraylist);
            arraylist.clear();
        }
        if (context.moveToNext()) goto _L13; else goto _L12
_L12:
        if (!arraylist.isEmpty())
        {
            CalendarSyncStateUtils.applyOperationsAsSyncAdapter(contentproviderclient, account, arraylist);
        }
_L4:
        context.close();
_L2:
        return -1;
_L6:
        calendarsyncadapterapiary = null;
          goto _L14
_L9:
        calendarsyncstate = null;
          goto _L15
_L17:
        calendarsyncadapterapiary = ProviderHelper.toAsSyncAdapterUri(calendarsyncadapterapiary, ((ProviderHelper) (obj)).account);
        break; /* Loop/switch isn't completed */
        calendarsyncadapterapiary;
        context.close();
        throw calendarsyncadapterapiary;
_L11:
        if (flag) goto _L17; else goto _L16
    }

    public int upgradeFrom9(CalendarSyncAdapterApiary calendarsyncadapterapiary, CalendarSyncState calendarsyncstate, Context context, ContentProviderClient contentproviderclient, Account account)
        throws RemoteException, IOException, ParseException
    {
        if (calendarsyncstate.originalVersion > 7)
        {
            addTimelyDataToExistingEvents(calendarsyncadapterapiary, contentproviderclient, account, calendarsyncstate);
        }
        return -1;
    }


    private class _cls1
        implements CalendarSyncStateUtils.IdTransformer
    {

        public final String transform$5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFADQ74QBECSTIIJ3AC5R62BRCC5N6EBQJEHP6IRJ77C______0(String s)
        {
            String s1;
            if (s == null)
            {
                s1 = null;
            } else
            {
                int i = s.lastIndexOf("\n");
                s1 = s;
                if (i >= 0)
                {
                    return s.substring(i + 1);
                }
            }
            return s1;
        }

        _cls1()
        {
        }
    }

}
