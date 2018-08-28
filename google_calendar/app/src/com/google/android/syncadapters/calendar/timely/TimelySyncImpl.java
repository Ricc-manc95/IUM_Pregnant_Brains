// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.content.Entity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apiary.ParseException;
import com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.syncadapters.timely.sql.ColumnConstants;
import com.google.android.apps.calendar.syncadapters.timely.sql.SQLiteDatabaseUtils;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.apps.calendar.timely.contract.TimelyContract;
import com.google.android.apps.calendar.timely.store.AccountSettingsLogStore;
import com.google.android.apps.calendar.timely.store.AccountSettingsStore;
import com.google.android.apps.calendar.timely.store.BirthdaySetting;
import com.google.android.apps.calendar.timely.store.PreferredNotification;
import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.android.apps.calendar.util.concurrent.SubscriptionManager;
import com.google.android.calendar.utils.json.JsonUtils;
import com.google.android.calendar.utils.timely.TimelyUtils;
import com.google.android.syncadapters.calendar.AnalyticsLoggerExtensionFactory;
import com.google.android.syncadapters.calendar.CalendarRequestExecutorBase;
import com.google.android.syncadapters.calendar.CalendarSyncInfo;
import com.google.android.syncadapters.calendar.CalendarSyncState;
import com.google.android.syncadapters.calendar.CalendarSyncStateUtils;
import com.google.android.syncadapters.calendar.FeedState;
import com.google.android.syncadapters.calendar.ProviderHelper;
import com.google.android.syncadapters.calendar.SyncAnalyticsLoggerExtension;
import com.google.android.syncadapters.calendar.SyncLog;
import com.google.android.syncadapters.calendar.SyncUtil;
import com.google.android.syncadapters.calendar.SyncUtils;
import com.google.android.syncadapters.calendar.Utilities;
import com.google.android.syncadapters.calendar.timely.contract.TimelyEventData;
import com.google.android.syncadapters.calendar.timely.contract.TimelySync;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.MethodOverride;
import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GZipEncoding;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.UriTemplate;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.ObjectParser;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Annotations;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.ConferenceData;
import com.google.api.services.calendar.model.CreateConferenceRequest;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.PrivateEventData;
import com.google.api.services.calendar.model.Setting;
import com.google.api.services.calendar.model.Settings;
import com.google.calendar.v2a.android.util.metric.MetricUtils;
import com.google.calendar.v2a.android.util.metric.SyncOperation;
import com.google.common.base.Platform;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import org.json.JSONObject;

public class TimelySyncImpl
    implements TimelySync
{

    public static final ConferenceData NULL_CONFERENCE_DATA = (ConferenceData)Data.nullOf(com/google/api/services/calendar/model/ConferenceData);
    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/timely/TimelySyncImpl);
    private final SyncAnalyticsLoggerExtension analyticsLogger;
    private final Calendar client;
    private final Context context;
    private final CalendarRequestExecutorBase requestExecutor;
    public final TimelyStore timelyStore;

    public TimelySyncImpl(Context context1, Calendar calendar, CalendarRequestExecutorBase calendarrequestexecutorbase)
    {
        context = context1;
        timelyStore = TimelyStore.acquire(context);
        requestExecutor = calendarrequestexecutorbase;
        if (AnalyticsLoggerExtensionFactory.analyticsLogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLoggerExtensionFactory#initialize() must be called first"));
        } else
        {
            analyticsLogger = AnalyticsLoggerExtensionFactory.analyticsLogger;
            client = calendar;
            return;
        }
    }

    private final com.google.api.services.calendar.Calendar.Settings.List createSettingsListRequest()
        throws IOException
    {
        com.google.api.services.calendar.Calendar.Settings settings = new com.google.api.services.calendar.Calendar.Settings(client);
        com.google.api.services.calendar.Calendar.Settings.List list = new com.google.api.services.calendar.Calendar.Settings.List(settings);
        settings.this$0.initialize(list);
        list.namespace = Arrays.asList(new String[] {
            "goocal"
        });
        list.maxResults = Integer.valueOf(100);
        return list;
    }

    private final void deserializeRemindersList(Account account, boolean flag, String s)
    {
        String as[];
        int i;
        int j;
        as = TextUtils.split(s, ",");
        i = as.length;
        if (i != 8)
        {
            LogUtils.w(TAG, "Found an inappropriate number of recent notifications for account %s (%d): %s", new Object[] {
                LogUtils.sanitizeAccountName(TAG, account.name), Integer.valueOf(as.length), s
            });
            i = Math.min(i, 8);
        }
        s = new ArrayList(i);
        j = 0;
_L2:
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_268;
        }
        String as1[];
        as1 = TextUtils.split(as[j], ";");
        if (as1.length != 2)
        {
            LogUtils.e(TAG, "Found a malformed notification for account %s: %s", new Object[] {
                LogUtils.sanitizeAccountName(TAG, account.name), as[j]
            });
            return;
        }
        Integer integer;
        int l;
        l = Integer.parseInt(as1[0]);
        integer = (Integer)TimelyContract.METHOD_LABELS_TO_VALUES.get(as1[1]);
        if (integer != null)
        {
            TimelyStore timelystore;
            Context context1;
            PreferredNotification apreferrednotification[];
            int k;
            if (flag)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            try
            {
                s.add(new PreferredNotification(k, l, integer.intValue()));
            }
            catch (NumberFormatException numberformatexception)
            {
                LogUtils.e(TAG, "Malformed minutes in notification for account %s: %s", new Object[] {
                    LogUtils.sanitizeAccountName(TAG, account.name), as[j]
                });
            }
            break MISSING_BLOCK_LABEL_339;
        }
        LogUtils.e(TAG, "Unable to find method for %s", new Object[] {
            as1[1]
        });
        break MISSING_BLOCK_LABEL_339;
        SyncLog.start("DB: notifications.update");
        timelystore = timelyStore;
        context1 = context;
        s = (PreferredNotification[])s.toArray(new PreferredNotification[s.size()]);
        apreferrednotification = timelystore.loadRecentlyUsedNotificationsForAccount(account, flag);
        timelystore.updateNotifications(account.name, account, flag, s, apreferrednotification, 0, context1);
        SyncLog.stop("DB: notifications.update");
        return;
        j++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private static String getRestoreTimelyDataKey(Account account, String s)
    {
        int i = account.hashCode();
        return (new StringBuilder(String.valueOf(s).length() + 30)).append("restoreTimelyData_").append(i).append("_").append(s).toString();
    }

    private final void getTimelySettingsInternal(Account account)
        throws IOException
    {
        SyncLog.start("Get Timely Settings");
        ContentValues contentvalues = new ContentValues();
        com.google.api.services.calendar.Calendar.Settings.List list = createSettingsListRequest();
        Object obj = (Settings)requestExecutor.execute("API: Get Calendar Settings", list);
        do
        {
            String s1 = ((Settings) (obj)).nextPageToken;
            LogUtils.d(TAG, "settingsList.nextPageToken: %s", new Object[] {
                s1
            });
            if (s1 != null)
            {
                list.pageToken = s1;
                requestExecutor.prefetch("API: Get Calendar Settings", list);
            }
            obj = ((Settings) (obj)).items;
            if (obj != null)
            {
                for (obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); processApiSetting(account, (Setting)((Iterator) (obj)).next(), contentvalues)) { }
            }
            if (s1 == null)
            {
                break;
            }
            obj = (Settings)requestExecutor.execute("API: Get Calendar Settings", list);
        } while (true);
        if (contentvalues.size() > 0)
        {
            if (contentvalues.containsKey("smartMailDelivery"))
            {
                String s = TimelyUtils.getGoogleClientVersionPrefKey(account);
                if (TimelyUtils.getVersionSharedPreferences(context).getString(s, "0").equals("0"))
                {
                    contentvalues.remove("smartMailDelivery");
                }
            }
            SyncLog.start("DB: settings.update");
            timelyStore.accountSettingsStore.updateFromSync(account, contentvalues);
            SyncLog.stop("DB: settings.update");
        }
        SyncLog.stop("Get Timely Settings");
    }

    private final List loadEventReminders(Account account, String s, boolean flag)
    {
        ArrayList arraylist = new ArrayList();
        SyncLog.start("DB: notifications.query");
        account = timelyStore.loadNotifications(s, account, flag, 1, null, "timestamp ASC");
        SyncLog.stop("DB: notifications.query");
        int j = account.length;
        for (int i = 0; i < j; i++)
        {
            s = account[i];
            EventReminder eventreminder = new EventReminder();
            eventreminder.method = (String)TimelyContract.DEFAULT_METHOD_VALUES_TO_LABELS.get(Integer.valueOf(((PreferredNotification) (s)).method));
            eventreminder.minutes = Integer.valueOf(((PreferredNotification) (s)).minutes);
            arraylist.add(eventreminder);
        }

        return arraylist;
    }

    private final void processApiSetting(Account account, Setting setting, ContentValues contentvalues)
        throws IOException
    {
        Object obj = setting.id;
        if (obj != null) goto _L2; else goto _L1
_L1:
        LogUtils.w(TAG, "Found setting with no id: %s", new Object[] {
            setting.toPrettyString()
        });
_L12:
        return;
_L2:
        setting = setting.value;
        LogUtils.v(TAG, "Retrieved setting: %s = %s", new Object[] {
            obj, setting
        });
        ((String) (obj)).hashCode();
        JVM INSTR lookupswitch 3: default 100
    //                   -325354433: 187
    //                   390244681: 170
    //                   416836820: 153;
           goto _L3 _L4 _L5 _L6
_L3:
        int i = -1;
_L14:
        i;
        JVM INSTR tableswitch 0 2: default 132
    //                   0 204
    //                   1 234
    //                   2 634;
           goto _L7 _L8 _L9 _L10
_L7:
        account = TimelyContract.apiaryToLocalSettingField(((String) (obj)));
        if (account == null) goto _L12; else goto _L11
_L11:
        contentvalues.put(account, TimelyContract.apiaryToLocalSettingValue(account, setting));
        return;
_L6:
        if (!((String) (obj)).equals("googleClientVersion")) goto _L3; else goto _L13
_L13:
        i = 0;
          goto _L14
_L5:
        if (!((String) (obj)).equals("smartMailAck")) goto _L3; else goto _L15
_L15:
        i = 1;
          goto _L14
_L4:
        if (!((String) (obj)).equals("goocal.recentreminders")) goto _L3; else goto _L16
_L16:
        i = 2;
          goto _L14
_L8:
        account = TimelyUtils.getGoogleClientVersionPrefKey(account);
        TimelyUtils.getVersionSharedPreferences(context).edit().putString(account, setting).apply();
        return;
_L9:
        android.content.SharedPreferences.Editor editor;
        if (setting == null)
        {
            LogUtils.wtf(TAG, "Ack preference has no value", new Object[0]);
            return;
        }
        contentvalues = Features.instance;
        if (contentvalues == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)contentvalues).uss();
        contentvalues = TimelyUtils.getSmartmailAckPrefKey(account);
        obj = TimelyUtils.getVersionSharedPreferences(context);
        editor = ((SharedPreferences) (obj)).edit();
        setting.hashCode();
        JVM INSTR lookupswitch 3: default 348
    //                   -482654623: 445
    //                   175259132: 429
    //                   950753608: 461;
           goto _L17 _L18 _L19 _L20
_L17:
        i = -1;
_L27:
        i;
        JVM INSTR tableswitch 0 2: default 380
    //                   0 477
    //                   1 493
    //                   2 618;
           goto _L21 _L22 _L23 _L24
_L21:
        LogUtils.wtf(TAG, "Unexpected ack value from the server: %s", new Object[] {
            setting
        });
        i = 0;
_L30:
        editor.apply();
        if (i == 0) goto _L12; else goto _L25
_L25:
        TimelyUtils.triggerSyncAdapterSyncWithExtras(account, "sync_extra_update_client_status", false, new Bundle());
        return;
_L19:
        if (!setting.equals("INELIGIBLE")) goto _L17; else goto _L26
_L26:
        i = 0;
          goto _L27
_L18:
        if (!setting.equals("UNACKNOWLEDGED")) goto _L17; else goto _L28
_L28:
        i = 1;
          goto _L27
_L20:
        if (!setting.equals("ACKNOWLEDGED")) goto _L17; else goto _L29
_L29:
        i = 2;
          goto _L27
_L22:
        editor.putInt(contentvalues, 1);
        i = 0;
          goto _L30
_L23:
        i = ((SharedPreferences) (obj)).getInt(contentvalues, 2);
        switch (i)
        {
        default:
            LogUtils.wtf(TAG, "Unexpected pref value: %s", new Object[] {
                Integer.valueOf(i)
            });
            i = 0;
            break;

        case 4: // '\004'
            LogUtils.w(TAG, "Inconsistent SM ack state: invalid server ack recorded.", new Object[0]);
            // fall through

        case 1: // '\001'
        case 2: // '\002'
        case 5: // '\005'
            editor.putInt(contentvalues, 2);
            i = 0;
            break;

        case 6: // '\006'
            editor.putInt(contentvalues, 3);
            // fall through

        case 3: // '\003'
            i = 1;
            break;
        }
        if (true) goto _L30; else goto _L31
_L31:
_L24:
        editor.putInt(contentvalues, 4);
        i = 0;
          goto _L30
_L10:
        saveRecentNotifications(account, setting);
        return;
          goto _L27
    }

    private static void removeFromCalendarList(Account account, ContentProviderClient contentproviderclient, CalendarRequestExecutorBase calendarrequestexecutorbase, Calendar calendar, String s)
        throws IOException, RemoteException
    {
        account = CalendarSyncStateUtils.getIfAvailable(contentproviderclient, account);
        if (account != null)
        {
            FeedState feedstate = account.getFeedState(s);
            if (feedstate != null)
            {
                feedstate.clear();
                android.provider.SyncStateContract.Helpers.update(contentproviderclient, ((CalendarSyncState) (account)).uri, ((CalendarSyncState) (account)).data.toString().getBytes());
            }
        }
        account = new com.google.api.services.calendar.Calendar.CalendarList(calendar);
        contentproviderclient = new com.google.api.services.calendar.Calendar.CalendarList.Delete(account, s);
        ((com.google.api.services.calendar.Calendar.CalendarList) (account)).this$0.initialize(contentproviderclient);
        calendarrequestexecutorbase.execute("API: Delete Calendar", contentproviderclient);
_L1:
        return;
        account;
        if (((HttpResponseException) (account)).statusCode != 404)
        {
            throw account;
        }
          goto _L1
    }

    private final void saveRecentNotifications(Account account, String s)
    {
        String as[] = TextUtils.split(s, "#");
        if (as.length != 2)
        {
            LogUtils.e(TAG, "The pref string for recent notifications for account %s is malformed: %s", new Object[] {
                LogUtils.sanitizeAccountName(TAG, account.name), s
            });
            return;
        } else
        {
            deserializeRemindersList(account, true, as[0]);
            deserializeRemindersList(account, false, as[1]);
            return;
        }
    }

    private static void serializeReminderList(StringBuilder stringbuilder, PreferredNotification apreferrednotification[])
    {
        if (apreferrednotification != null && apreferrednotification.length != 0)
        {
            int j = apreferrednotification.length;
            int i = 0;
            boolean flag = true;
            while (i < j) 
            {
                PreferredNotification preferrednotification = apreferrednotification[i];
                String s;
                String s1;
                if (flag)
                {
                    flag = false;
                } else
                {
                    stringbuilder.append(",");
                }
                stringbuilder.append(preferrednotification.minutes);
                stringbuilder.append(";");
                s1 = (String)TimelyContract.METHOD_VALUES_TO_LABELS.get(Integer.valueOf(preferrednotification.method));
                s = s1;
                if (s1 == null)
                {
                    LogUtils.w(TAG, "Will substitute method %d with ALERT", new Object[] {
                        Integer.valueOf(preferrednotification.method)
                    });
                    s = "ALERT";
                }
                stringbuilder.append(s);
                i++;
            }
        }
    }

    private final Setting updateOrInsertNamespacedSetting(String s, String s1, String s2, String s3)
        throws IOException
    {
        String s4 = (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append(".").append(s1).toString();
        Setting setting = new Setting();
        setting.id = s4;
        setting.namespace = s;
        setting.name = s1;
        setting.value = s2;
        try
        {
            s = requestExecutor;
            s1 = new com.google.api.services.calendar.Calendar.Settings(client);
            s2 = new com.google.api.services.calendar.Calendar.Settings.Update(s1, s4, setting);
            ((com.google.api.services.calendar.Calendar.Settings) (s1)).this$0.initialize(s2);
            s = (Setting)s.executeWithFlags("API: Update Calendar Setting", s2, s3);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            if (((HttpResponseException) (s)).statusCode == 404)
            {
                s = requestExecutor;
                s1 = new com.google.api.services.calendar.Calendar.Settings(client);
                s2 = new com.google.api.services.calendar.Calendar.Settings.Insert(s1, setting);
                ((com.google.api.services.calendar.Calendar.Settings) (s1)).this$0.initialize(s2);
                return (Setting)s.executeWithFlags("API: Insert Calendar Setting", s2, s3);
            } else
            {
                throw s;
            }
        }
        return s;
    }

    private final Setting updateSingleSetting(Account account, String s, String s1, String s2, ContentProviderClient contentproviderclient)
        throws IOException, RemoteException
    {
        if (Platform.stringIsNullOrEmpty(s1))
        {
            LogUtils.w(TAG, "Won't set empty setting: %s", new Object[] {
                s
            });
            return null;
        }
        String s3 = TimelyContract.localSettingValueToApiary(s, s1);
        if ("smartMailDelivery".equals(s) || "defaultNoEndTime".equals(s) || "defaultEventLength".equals(s) || "timezone".equals(s))
        {
            account = requestExecutor;
            s1 = new com.google.api.services.calendar.Calendar.Settings(client);
            contentproviderclient = new Setting();
            contentproviderclient.value = s3;
            s = new com.google.api.services.calendar.Calendar.Settings.Update(s1, s, contentproviderclient);
            ((com.google.api.services.calendar.Calendar.Settings) (s1)).this$0.initialize(s);
            return (Setting)account.executeWithFlags("API: Update Calendar Setting", s, s2);
        }
        if ("holidayscolor".equals(s) || "taskscolor".equals(s))
        {
            return updateOrInsertNamespacedSetting("goocal", s, s3, s2);
        }
        if ("settingBirthdayVisibility".equals(s) && "0".equals(s1))
        {
            removeFromCalendarList(account, contentproviderclient, requestExecutor, client, CalendarType.getBirthdayCalendarId(true));
            removeFromCalendarList(account, contentproviderclient, requestExecutor, client, CalendarType.getBirthdayCalendarId(false));
            Utilities.requestMetaFeedSync(account);
            return null;
        }
        if (!"settingBirthdayIncludeGplus".equals(s)) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        Object obj = null;
        s2 = null;
        boolean flag1 = "1".equals(s1);
        String s4;
        Uri uri;
        String s5;
        String s6;
        boolean flag;
        if (!flag1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        s4 = CalendarType.getBirthdayCalendarId(flag);
        s = obj;
        s1 = ProviderHelper.asClient();
        s = obj;
        uri = android.provider.CalendarContract.Calendars.CONTENT_URI;
        s = obj;
        s5 = SQLiteDatabaseUtils.makeWhere(new String[] {
            "ownerAccount=?", "account_name=?"
        });
        s = obj;
        s6 = account.name;
        s = obj;
        s1 = s1.query(contentproviderclient, uri, new String[] {
            "calendar_color_index"
        }, s5, new String[] {
            s4, s6
        }, null);
        s = s1;
        if (s == null) goto _L4; else goto _L3
_L3:
        s1 = s;
        if (!s.moveToFirst()) goto _L4; else goto _L5
_L5:
        s1 = s;
        j = s.getInt(0);
_L14:
        i = j;
        if (s != null)
        {
            s.close();
            i = j;
        }
_L12:
        removeFromCalendarList(account, contentproviderclient, requestExecutor, client, s4);
        s = new Bundle();
        if (i != -1)
        {
            s.putInt("calendar_color_index", i);
        }
        TimelyUtils.subscribeBirthdayCalendar(context, account, flag1, s);
        return null;
        s2;
        s = null;
_L11:
        s1 = s;
        ThrowableExtension.STRATEGY.printStackTrace(s2);
        if (s != null)
        {
            s.close();
            i = -1;
            continue; /* Loop/switch isn't completed */
        }
        break; /* Loop/switch isn't completed */
        s;
        s1 = s2;
        s2 = s;
_L9:
        s = s1;
        ThrowableExtension.STRATEGY.printStackTrace(s2);
        if (s1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        s1.close();
        i = -1;
        continue; /* Loop/switch isn't completed */
        account;
_L7:
        if (s != null)
        {
            s.close();
        }
        throw account;
_L2:
        LogUtils.w(TAG, "Unhandled setting: %s", new Object[] {
            s
        });
        return null;
        account;
        s = s1;
        if (true) goto _L7; else goto _L6
_L6:
        s2;
        s1 = s;
        if (true) goto _L9; else goto _L8
_L8:
        break; /* Loop/switch isn't completed */
        s2;
        if (true) goto _L11; else goto _L10
_L10:
        i = -1;
        if (true) goto _L12; else goto _L4
_L4:
        j = -1;
        if (true) goto _L14; else goto _L13
_L13:
    }

    private final void updateTimelySettings(Account account, ContentProviderClient contentproviderclient)
        throws RemoteException
    {
        Object obj;
        Object obj1;
        obj1 = SyncOperation.SETTINGS_SYNC_UP;
        obj = MetricUtils.backends;
        obj1 = new com.google.calendar.v2a.android.util.metric.MetricUtils..Lambda._cls2(((com.google.calendar.v2a.android.util.metric.MetricUtils.Operation) (obj1)));
        if (obj instanceof RandomAccess)
        {
            obj = new com.google.common.collect.Lists.TransformingRandomAccessList(((List) (obj)), ((com.google.common.base.Function) (obj1)));
        } else
        {
            obj = new com.google.common.collect.Lists.TransformingSequentialList(((List) (obj)), ((com.google.common.base.Function) (obj1)));
        }
        obj = new com.google.calendar.v2a.android.util.metric.MetricUtils..Lambda._cls3(ImmutableList.copyOf(((Collection) (obj))));
        SyncLog.start("DB: settings.query");
        obj1 = timelyStore.accountSettingsLogStore.database.query("timelysettingslog", null, "accountName = ?", new String[] {
            account.name
        }, null, null, "_id", "20");
        SyncLog.stop("DB: settings.query");
        if (((Cursor) (obj1)).moveToFirst()) goto _L2; else goto _L1
_L1:
        LogUtils.i(TAG, "Found no pending settings", new Object[0]);
        ((Cursor) (obj1)).close();
_L5:
        ((com.google.calendar.v2a.android.util.metric.MetricUtils.MetricContext) (obj)).finish(true);
        return;
_L2:
        Object obj2;
        HashSet hashset;
        ArrayList arraylist;
        String s;
        boolean flag;
        boolean flag1;
        long l1;
        Object obj3;
        String s1;
        int i;
        int j;
        int k;
        int l;
        boolean flag2;
        try
        {
            i = ((Cursor) (obj1)).getColumnIndexOrThrow("_id");
            j = ((Cursor) (obj1)).getColumnIndexOrThrow("id");
            k = ((Cursor) (obj1)).getColumnIndexOrThrow("value");
            l = ((Cursor) (obj1)).getColumnIndexOrThrow("flags");
            obj2 = new HashMap();
            hashset = new HashSet();
        }
        // Misplaced declaration of an exception variable
        catch (Account account)
        {
            ((com.google.calendar.v2a.android.util.metric.MetricUtils.MetricContext) (obj)).finish(false);
            throw account;
        }
        flag1 = false;
        arraylist = new ArrayList();
_L4:
        l1 = ((Cursor) (obj1)).getLong(i);
        s = ((Cursor) (obj1)).getString(j);
        obj3 = ((Cursor) (obj1)).getString(k);
        s1 = ((Cursor) (obj1)).getString(l);
        flag2 = hashset.contains(s);
        flag = flag1;
        if (flag2)
        {
            break MISSING_BLOCK_LABEL_346;
        }
        obj3 = updateSingleSetting(account, s, ((String) (obj3)), s1, contentproviderclient);
        if (obj3 == null)
        {
            break MISSING_BLOCK_LABEL_329;
        }
        ((Map) (obj2)).put(s, obj3);
        arraylist.add(Long.valueOf(l1));
        flag = flag1;
_L6:
        flag2 = ((Cursor) (obj1)).moveToNext();
        flag1 = flag;
        if (flag2) goto _L4; else goto _L3
_L3:
        ((Cursor) (obj1)).close();
        SyncLog.start("DB: settings.update");
        SQLiteDatabaseUtils.deleteAll(timelyStore.accountSettingsLogStore.database, "timelysettingslog", null, null, "_id", arraylist);
        SyncLog.stop("DB: settings.update");
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_540;
        }
        flag2 = hashset.isEmpty();
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_540;
        }
        getTimelySettingsInternal(account);
          goto _L5
        account;
        ThrowableExtension.STRATEGY.printStackTrace(account);
          goto _L5
        HttpResponseException httpresponseexception;
        httpresponseexception;
        if (!Utilities.isPermanentException(httpresponseexception))
        {
            break MISSING_BLOCK_LABEL_484;
        }
        arraylist.add(Long.valueOf(l1));
        flag = true;
          goto _L6
        hashset.add(s);
        flag = flag1;
          goto _L6
        account;
        ((Cursor) (obj1)).close();
        throw account;
        IOException ioexception1;
        ioexception1;
        SyncLog.logError(ioexception1, "Error updating setting");
        arraylist.add(Long.valueOf(l1));
        flag = true;
          goto _L6
        contentproviderclient = new ContentValues();
        obj1 = ((Map) (obj2)).values().iterator();
_L9:
        if (!((Iterator) (obj1)).hasNext()) goto _L8; else goto _L7
_L7:
        obj2 = (Setting)((Iterator) (obj1)).next();
        processApiSetting(account, ((Setting) (obj2)), contentproviderclient);
          goto _L9
        IOException ioexception;
        ioexception;
        LogUtils.e(TAG, "Error parsing back setting: %s", new Object[] {
            ioexception
        });
          goto _L9
_L8:
        if (contentproviderclient.size() <= 0) goto _L5; else goto _L10
_L10:
        SyncLog.start("DB: settings.update");
        timelyStore.accountSettingsStore.updateFromSync(account, contentproviderclient);
        SyncLog.stop("DB: settings.update");
          goto _L5
    }

    public final void addAttachmentsToEntry(HashMap hashmap, String s, Long long1, Event event)
    {
        Object obj = null;
        String s1 = (String)hashmap.get("attachmentsExtra");
        hashmap = ((HashMap) (obj));
        if (!TextUtils.isEmpty(s1))
        {
            hashmap = TimelyEventData.createAttachments(new AndroidJsonFactory(), s1);
            if (long1 != null)
            {
                SyncLog.start("DB: timelyData.update");
                timelyStore.updateOrInsertOneEventField(s, long1.longValue(), "attachments", s1);
                SyncLog.stop("DB: timelyData.update");
            }
        }
        obj = hashmap;
        if (hashmap == null)
        {
            obj = hashmap;
            if (s != null)
            {
                obj = hashmap;
                if (long1 != null)
                {
                    SyncLog.start("DB: timelyData.query");
                    s = timelyStore.loadSyncedEventData(s, long1.longValue());
                    SyncLog.stop("DB: timelyData.query");
                    obj = hashmap;
                    if (s != null)
                    {
                        obj = ((TimelyEventData) (s)).attachments;
                    }
                }
            }
        }
        event.attachments = ((List) (obj));
    }

    public final void addConferenceDetailsToEvent(Entity entity, HashMap hashmap, boolean flag, Event event, String s, Account account)
    {
        .Lambda._cls0 _lcls0;
        class .Lambda._cls0
            implements Supplier
        {

            private final TimelySyncImpl arg$1;
            private final long arg$2;
            private final String arg$3;

            public final Object get()
            {
                TimelySyncImpl timelysyncimpl = arg$1;
                long l = arg$2;
                String s1 = arg$3;
                return timelysyncimpl.timelyStore.getLastSyncedConferenceDataForEvent(l, s1);
            }

            .Lambda._cls0(long l, String s)
            {
                arg$1 = TimelySyncImpl.this;
                arg$2 = l;
                arg$3 = s;
            }
        }

        _lcls0 = new .Lambda._cls0(entity.getEntityValues().getAsLong("calendar_id").longValue(), entity.getEntityValues().getAsString("_sync_id"));
        class .Lambda._cls1
            implements Supplier
        {

            private final TimelySyncImpl arg$1;
            private final String arg$2;
            private final Account arg$3;

            public final Object get()
            {
                Object obj;
                obj = arg$1;
                String s1 = arg$2;
                Account account1 = arg$3;
                obj = ((TimelySyncImpl) (obj)).timelyStore.getConferenceTypeForCalendar(s1, account1.name, account1.type);
                if (obj == null) goto _L2; else goto _L1
_L1:
                byte byte0 = -1;
                ((String) (obj)).hashCode();
                JVM INSTR lookupswitch 5: default 92
            //                           -1876495076: 185
            //                           -972730403: 155
            //                           -517304463: 200
            //                           942033467: 170
            //                           1601152418: 140;
                   goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
                byte0;
                JVM INSTR tableswitch 0 4: default 128
            //                           0 215
            //                           1 215
            //                           2 215
            //                           3 215
            //                           4 215;
                   goto _L2 _L9 _L9 _L9 _L9 _L9
_L2:
                byte0 = 0;
_L10:
                if (byte0 == 0)
                {
                    return TimelySyncImpl.NULL_CONFERENCE_DATA;
                } else
                {
                    ConferenceData conferencedata = new ConferenceData();
                    Conference conference = new Conference();
                    conference.type = ((String) (obj));
                    conferencedata.conferences = Arrays.asList(new Conference[] {
                        conference
                    });
                    return conferencedata;
                }
_L8:
                if (((String) (obj)).equals("eventHangout"))
                {
                    byte0 = 0;
                }
                  goto _L3
_L5:
                if (((String) (obj)).equals("eventNamedHangout"))
                {
                    byte0 = 1;
                }
                  goto _L3
_L7:
                if (((String) (obj)).equals("meeting"))
                {
                    byte0 = 2;
                }
                  goto _L3
_L4:
                if (((String) (obj)).equals("meetingPhoneNumber"))
                {
                    byte0 = 3;
                }
                  goto _L3
_L6:
                if (((String) (obj)).equals("meetingPhoneNumbersLink"))
                {
                    byte0 = 4;
                }
                  goto _L3
_L9:
                byte0 = 1;
                  goto _L10
            }

            .Lambda._cls1(String s, Account account)
            {
                arg$1 = TimelySyncImpl.this;
                arg$2 = s;
                arg$3 = account;
            }
        }

        s = new .Lambda._cls1(s, account);
        if (!hashmap.containsKey("includeHangout")) goto _L2; else goto _L1
_L1:
        account = Utilities.getValueAsBoolean("includeHangout", hashmap.get("includeHangout"));
        if (account != null) goto _L4; else goto _L3
_L3:
        LogUtils.w(TAG, "Null value for includeHangout extended property.", new Object[0]);
_L2:
        return;
_L4:
        entity = (ConferenceData)JsonUtils.fromString((String)hashmap.get("conferenceData"), com/google/api/services/calendar/model/ConferenceData, null);
        hashmap = (ConferenceData)_lcls0.get();
        if (hashmap == null) goto _L6; else goto _L5
_L5:
        boolean flag1;
        boolean flag2;
        if (((ConferenceData) (hashmap)).conferences != null && !((ConferenceData) (hashmap)).conferences.isEmpty())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L8; else goto _L7
_L7:
        if (((ConferenceData) (hashmap)).createRequest != null && ((ConferenceData) (hashmap)).createRequest.requestId != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1) goto _L6; else goto _L8
_L8:
        flag2 = true;
_L9:
        if (!flag || account.booleanValue() != flag2 || account.booleanValue() && entity != null)
        {
            if (account.booleanValue())
            {
                if (entity == null)
                {
                    entity = (ConferenceData)s.get();
                }
                event.conferenceData = entity;
                return;
            } else
            {
                event.conferenceData = NULL_CONFERENCE_DATA;
                return;
            }
        }
        if (true) goto _L2; else goto _L6
_L6:
        flag2 = false;
          goto _L9
    }

    public final void addParticipantStatusToEntry(HashMap hashmap, String s, Long long1, Event event)
    {
        if (hashmap.containsKey("participantStatusExtra"))
        {
            event.participantStatusSerialized = (String)hashmap.get("participantStatusExtra");
        } else
        if (s != null && long1 != null)
        {
            SyncLog.start("DB: timelyData.query");
            hashmap = timelyStore.loadSyncedEventData(s, long1.longValue());
            SyncLog.stop("DB: timelyData.query");
            if (hashmap != null && ((TimelyEventData) (hashmap)).participantStatus != null)
            {
                event.participantStatusSerialized = ((TimelyEventData) (hashmap)).participantStatus;
                return;
            }
        }
    }

    public final void addTitleContactAnnotationsToEntry(HashMap hashmap, String s, Long long1, Event event)
    {
        Object obj = null;
        String s1 = (String)hashmap.get("titleContactsExtra");
        hashmap = obj;
        if (!TextUtils.isEmpty(s1))
        {
            hashmap = TimelyEventData.createTitleContactAnnotations(new AndroidJsonFactory(), s1);
            if (long1 != null)
            {
                SyncLog.start("DB: timelyData.update");
                timelyStore.updateOrInsertOneEventField(s, long1.longValue(), "titleContacts", s1);
                SyncLog.stop("DB: timelyData.update");
            }
        }
        if (hashmap == null && s != null && long1 != null)
        {
            SyncLog.start("DB: timelyData.query");
            s = timelyStore.loadSyncedEventData(s, long1.longValue());
            SyncLog.stop("DB: timelyData.query");
            if (s != null)
            {
                hashmap = ((TimelyEventData) (s)).titleContactAnnotations;
            }
        }
        if (hashmap != null)
        {
            long1 = event.privateEventData;
            s = long1;
            if (long1 == null)
            {
                s = new PrivateEventData();
            }
            Annotations annotations = ((PrivateEventData) (s)).annotations;
            long1 = annotations;
            if (annotations == null)
            {
                long1 = new Annotations();
                s.annotations = long1;
            }
            long1.titleContactAnnotations = hashmap;
            event.privateEventData = s;
        }
    }

    public final void apiaryEventToTimelyExtras(Event event, TimelyEventData timelyeventdata)
    {
        Object obj;
        PrivateEventData privateeventdata;
        if (timelyeventdata.hasTimelyData())
        {
            obj = TAG;
            boolean flag;
            if (LogUtils.maxEnabledLogLevel > 5)
            {
                flag = false;
            } else
            if (Log.isLoggable(((String) (obj)), 5))
            {
                flag = true;
            } else
            {
                flag = Log.isLoggable(((String) (obj)), 5);
            }
            if (flag)
            {
                LogUtils.w(TAG, "Expected empty extras, got %s", new Object[] {
                    timelyeventdata
                });
            }
        }
        timelyeventdata.structuredLocation = event.structuredLocation;
        timelyeventdata.conferenceData = event.conferenceData;
        privateeventdata = event.privateEventData;
        if (privateeventdata == null)
        {
            obj = null;
        } else
        {
            obj = privateeventdata.smartMailInfo;
        }
        timelyeventdata.smartMailInfo = ((com.google.api.services.calendar.model.SmartMailInfo) (obj));
        timelyeventdata.eventSource = event.source;
        timelyeventdata.backgroundImageUrl = event.backgroundImageUrl;
        if (privateeventdata == null || privateeventdata.annotations == null)
        {
            obj = null;
        } else
        {
            obj = privateeventdata.annotations.titleContactAnnotations;
        }
        timelyeventdata.titleContactAnnotations = ((List) (obj));
        timelyeventdata.attachments = event.attachments;
        obj = event.organizer;
        if (obj != null && CalendarType.isBirthdayCalendar(((com.google.api.services.calendar.model.Event.Organizer) (obj)).email))
        {
            timelyeventdata.eventGadget = event.gadget;
        }
        timelyeventdata.responseSummary = event.responseSummary;
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).ooo_sync();
        timelyeventdata.participantStatus = event.participantStatusSerialized;
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).response_comments();
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).propose_new_time();
        event = event.attendees;
        if (event == null)
        {
            timelyeventdata.attendees = null;
            return;
        } else
        {
            timelyeventdata.attendees = event;
            return;
        }
    }

    public final void fillSyncInfo(CalendarSyncInfo calendarsyncinfo, String s)
    {
        calendarsyncinfo.defaultAllDayReminders = loadEventReminders(calendarsyncinfo.account, s, true);
        calendarsyncinfo.defaultReminders = loadEventReminders(calendarsyncinfo.account, s, false);
    }

    public final List getEventAttendees(String s, long l)
    {
        if (s != null)
        {
            SyncLog.start("DB: timelyData.query");
            s = timelyStore.loadSyncedEventData(s, l);
            SyncLog.stop("DB: timelyData.query");
            if (s != null)
            {
                s = ((TimelyEventData) (s)).attendees;
                if (s != null)
                {
                    return s;
                } else
                {
                    return Collections.emptyList();
                }
            }
        }
        return Collections.emptyList();
    }

    public final void getTimelySettings(Account account)
        throws IOException
    {
        Object obj1 = SyncOperation.SETTINGS_SYNC_DOWN;
        Object obj = MetricUtils.backends;
        obj1 = new com.google.calendar.v2a.android.util.metric.MetricUtils..Lambda._cls2(((com.google.calendar.v2a.android.util.metric.MetricUtils.Operation) (obj1)));
        if (obj instanceof RandomAccess)
        {
            obj = new com.google.common.collect.Lists.TransformingRandomAccessList(((List) (obj)), ((com.google.common.base.Function) (obj1)));
        } else
        {
            obj = new com.google.common.collect.Lists.TransformingSequentialList(((List) (obj)), ((com.google.common.base.Function) (obj1)));
        }
        obj = new com.google.calendar.v2a.android.util.metric.MetricUtils..Lambda._cls3(ImmutableList.copyOf(((Collection) (obj))));
        try
        {
            getTimelySettingsInternal(account);
            ((com.google.calendar.v2a.android.util.metric.MetricUtils.MetricContext) (obj)).finish(true);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Account account)
        {
            ((com.google.calendar.v2a.android.util.metric.MetricUtils.MetricContext) (obj)).finish(false);
        }
        throw account;
    }

    public final Uri insertOrUpdateEventData(String s, long l, TimelyEventData timelyeventdata)
    {
        return timelyStore.insertOrUpdateEventData(s, l, timelyeventdata);
    }

    public final void onAfterUpsync(Account account, ContentProviderClient contentproviderclient)
        throws RemoteException
    {
        LogUtils.d(TAG, "Local Settings changes", new Object[0]);
        updateTimelySettings(account, contentproviderclient);
    }

    public final void onInitializeSync(Account account)
    {
        Bundle bundle = new Bundle(5);
        bundle.putBoolean("sync_extra_get_settings", true);
        bundle.putBoolean("sync_extra_get_default_notifications", true);
        bundle.putBoolean("sync_extra_get_recent_notifications", true);
        bundle.putBoolean("force", true);
        bundle.putBoolean("upload", true);
        SyncUtil.requestSync(account, "com.android.calendar", bundle);
    }

    public final void parseDefaultNotifications(CalendarListEntry calendarlistentry, long l, Account account, boolean flag)
    {
        PreferredNotification apreferrednotification[];
        int i;
        int j;
        if (flag)
        {
            calendarlistentry = calendarlistentry.defaultAllDayReminders;
        } else
        {
            calendarlistentry = calendarlistentry.defaultReminders;
        }
        if (calendarlistentry == null)
        {
            i = 0;
        } else
        {
            i = calendarlistentry.size();
        }
        apreferrednotification = new PreferredNotification[i];
        j = 0;
        while (j < i) 
        {
            EventReminder eventreminder = (EventReminder)calendarlistentry.get(j);
            int i1 = eventreminder.minutes.intValue();
            int j1 = ((Integer)TimelyContract.DEFAULT_METHOD_LABELS_TO_VALUES.get(eventreminder.method)).intValue();
            int k;
            if (flag)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            apreferrednotification[j] = new PreferredNotification(k, i1, j1);
            j++;
        }
        SyncLog.start("DB: notifications.update");
        timelyStore.updateDefaultNotifications(context, String.valueOf(l), account, flag, apreferrednotification);
        SyncLog.stop("DB: notifications.update");
    }

    public final void prefetchTimelyTopLevelSyncRequests(Bundle bundle)
        throws IOException
    {
        if (bundle.getBoolean("sync_extra_get_settings"))
        {
            requestExecutor.prefetch("API: Get Calendar Settings", createSettingsListRequest());
        }
    }

    public final void processNonUpdateFlags(Account account, Bundle bundle, ContentProviderClient contentproviderclient)
        throws RemoteException, IOException, ParseException
    {
        if (bundle != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (bundle.getBoolean("sync_extra_get_settings", false))
        {
            getTimelySettings(account);
            bundle.remove("sync_extra_get_settings");
            bundle.putBoolean("just_synced_settings", true);
            bundle.remove("sync_extra_get_recent_notifications");
        }
        if (!bundle.getBoolean("sync_extra_get_recent_notifications", false))
        {
            continue; /* Loop/switch isn't completed */
        }
        SyncLog.start("Get Recent Notifications");
        Object obj;
        obj = requestExecutor;
        com.google.api.services.calendar.Calendar.Settings settings = new com.google.api.services.calendar.Calendar.Settings(client);
        com.google.api.services.calendar.Calendar.Settings.Get get = new com.google.api.services.calendar.Calendar.Settings.Get(settings, "goocal.recentreminders");
        settings.this$0.initialize(get);
        obj = ((Setting)((CalendarRequestExecutorBase) (obj)).execute("API: Get Recent Notifications", get)).value;
        saveRecentNotifications(account, ((String) (obj)));
        SyncLog.stop("Get Recent Notifications");
_L4:
        bundle.remove("sync_extra_get_recent_notifications");
        if (!bundle.getBoolean("sync_extra_get_default_notifications", false) || !bundle.containsKey("feed") && !bundle.containsKey("feed_internal") && !bundle.getBoolean("upload", false)) goto _L1; else goto _L3
_L3:
        com.google.api.services.calendar.Calendar.CalendarList.List list;
        HashMap hashmap;
        Object obj1;
        SyncLog.start("Get Default Notifications");
        obj = new com.google.api.services.calendar.Calendar.CalendarList(client);
        list = new com.google.api.services.calendar.Calendar.CalendarList.List(((com.google.api.services.calendar.Calendar.CalendarList) (obj)));
        ((com.google.api.services.calendar.Calendar.CalendarList) (obj)).this$0.initialize(list);
        list.supportsAllDayReminders = Boolean.valueOf(true);
        list.maxResults = Integer.valueOf(100);
        obj = (CalendarList)requestExecutor.execute("API: Get Calendars List", list);
        hashmap = new HashMap();
        obj1 = ProviderHelper.asClient();
        Uri uri = android.provider.CalendarContract.Calendars.CONTENT_URI;
        String s1 = ColumnConstants.WHERE_ACCOUNT_AND_TYPE;
        String s2 = account.name;
        String s3 = account.type;
        obj1 = ((ProviderHelper) (obj1)).query(contentproviderclient, uri, new String[] {
            "_id", "ownerAccount"
        }, s1, new String[] {
            s2, s3
        }, null);
        contentproviderclient = ((ContentProviderClient) (obj));
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_436;
        }
        long l;
        for (; ((Cursor) (obj1)).moveToNext(); hashmap.put(((Cursor) (obj1)).getString(1), Long.valueOf(l)))
        {
            l = ((Cursor) (obj1)).getLong(0);
        }

        break MISSING_BLOCK_LABEL_426;
        account;
        ((Cursor) (obj1)).close();
        throw account;
        obj;
        SyncLog.stop("Get Recent Notifications");
        if (((HttpResponseException) (obj)).statusCode != 404)
        {
            throw obj;
        }
          goto _L4
        ((Cursor) (obj1)).close();
        contentproviderclient = ((ContentProviderClient) (obj));
        do
        {
            String s = ((CalendarList) (contentproviderclient)).nextPageToken;
            LogUtils.d(TAG, "calendarList.nextPageToken: %s", new Object[] {
                s
            });
            if (s != null)
            {
                list.pageToken = s;
                requestExecutor.prefetch("API: Get Calendars List", list);
            }
            contentproviderclient = ((CalendarList) (contentproviderclient)).items;
            if (contentproviderclient != null)
            {
                for (contentproviderclient = contentproviderclient.iterator(); contentproviderclient.hasNext();)
                {
                    CalendarListEntry calendarlistentry = (CalendarListEntry)contentproviderclient.next();
                    Long long1 = (Long)hashmap.get(calendarlistentry.id);
                    if (long1 == null)
                    {
                        LogUtils.e(TAG, "Unable to find calendar %s", new Object[] {
                            LogUtils.sanitizeName(TAG, calendarlistentry.id)
                        });
                    } else
                    {
                        parseDefaultNotifications(calendarlistentry, long1.longValue(), account, true);
                        parseDefaultNotifications(calendarlistentry, long1.longValue(), account, false);
                    }
                }

            }
            if (s != null)
            {
                contentproviderclient = (CalendarList)requestExecutor.execute("API: Get Calendars List", list);
            } else
            {
                SyncLog.stop("Get Default Notifications");
                bundle.remove("sync_extra_get_default_notifications");
                return;
            }
        } while (true);
    }

    public final boolean processUpdateFlags(Account account, Bundle bundle, ContentProviderClient contentproviderclient)
        throws IOException, RemoteException, ParseException
    {
        if (bundle == null)
        {
            return false;
        }
        if (!bundle.getBoolean("sync_extra_sync_timely_data", false))
        {
            break MISSING_BLOCK_LABEL_475;
        }
        bundle = ProviderHelper.asSyncAdapter(account).query(contentproviderclient, android.provider.CalendarContract.Calendars.CONTENT_URI, new String[] {
            "_id", "ownerAccount"
        }, "sync_events=?", new String[] {
            "1"
        }, null);
        if (bundle == null)
        {
            break MISSING_BLOCK_LABEL_473;
        }
_L3:
        Object obj;
        Object obj2;
        String s;
        long l;
        do
        {
            if (!bundle.moveToNext() || Thread.currentThread().isInterrupted())
            {
                break MISSING_BLOCK_LABEL_467;
            }
            l = bundle.getLong(0);
            obj2 = String.valueOf(l);
            s = bundle.getString(1);
        } while (TimelyUtils.getVersionSharedPreferences(context).getBoolean(getRestoreTimelyDataKey(account, s), false));
        obj = ProviderHelper.asSyncAdapter(account).query(contentproviderclient, android.provider.CalendarContract.Events.CONTENT_URI, new String[] {
            "_sync_id"
        }, "((sync_data9 & 1075) != 0) AND calendar_id=? AND lastSynced=0", new String[] {
            obj2
        }, null);
        if (obj == null) goto _L2; else goto _L1
_L1:
        boolean flag = ((Cursor) (obj)).moveToFirst();
        if (flag)
        {
            break MISSING_BLOCK_LABEL_202;
        }
        ((Cursor) (obj)).close();
          goto _L3
        account;
        bundle.close();
        throw account;
        Set set;
        ArrayList arraylist;
        set = timelyStore.getEventSyncIdsForCalendar(l);
        arraylist = new ArrayList();
_L8:
        Object obj3 = ((Cursor) (obj)).getString(0);
        if (TextUtils.isEmpty(((CharSequence) (obj3)))) goto _L5; else goto _L4
_L4:
        flag = set.contains(obj3);
        if (flag) goto _L5; else goto _L6
_L6:
        CalendarRequestExecutorBase calendarrequestexecutorbase = requestExecutor;
        com.google.api.services.calendar.Calendar.Events events = new com.google.api.services.calendar.Calendar.Events(client);
        obj3 = new com.google.api.services.calendar.Calendar.Events.Get(events, s, ((String) (obj3)));
        events.this$0.initialize(((AbstractGoogleClientRequest) (obj3)));
        saveTimelyDataForEvent(arraylist, (Event)calendarrequestexecutorbase.execute("API: Get Event", ((com.google.api.services.calendar.CalendarRequest) (obj3))), account, contentproviderclient, l, ((String) (obj2)));
_L5:
        if (((Cursor) (obj)).moveToNext() && !Thread.currentThread().isInterrupted()) goto _L8; else goto _L7
_L7:
        if (!Thread.currentThread().isInterrupted() && !arraylist.isEmpty())
        {
            Utilities.applyOperationsAsSyncAdapter(contentproviderclient, account, arraylist);
        }
        ((Cursor) (obj)).close();
_L2:
        if (!Thread.currentThread().isInterrupted())
        {
            TimelyUtils.getVersionSharedPreferences(context).edit().putBoolean(getRestoreTimelyDataKey(account, s), true).apply();
        }
          goto _L3
        HttpResponseException httpresponseexception;
        httpresponseexception;
        if (!Utilities.isNotFoundException(httpresponseexception))
        {
            break MISSING_BLOCK_LABEL_464;
        }
        analyticsLogger.logSyncError("restoreTimelyEventData", httpresponseexception.statusCode);
        SyncLog.logError(httpresponseexception, "Cannot restore timely data");
          goto _L5
        account;
        ((Cursor) (obj)).close();
        throw account;
        throw httpresponseexception;
        bundle.close();
        return true;
        if (!bundle.getBoolean("sync_extra_update_client_status", false)) goto _L10; else goto _L9
_L9:
        LogUtils.d(TAG, "updateGoogleClientStatus(%s)", new Object[] {
            account
        });
        bundle = TimelyUtils.getVersionSharedPreferences(context);
        int i;
        try
        {
            obj2 = new Setting();
            obj2.value = "1";
            contentproviderclient = requestExecutor;
            obj = new com.google.api.services.calendar.Calendar.Settings(client);
            obj2 = new com.google.api.services.calendar.Calendar.Settings.Update(((com.google.api.services.calendar.Calendar.Settings) (obj)), "googleClientVersion", ((Setting) (obj2)));
            ((com.google.api.services.calendar.Calendar.Settings) (obj)).this$0.initialize(((AbstractGoogleClientRequest) (obj2)));
            contentproviderclient.execute("API: Update Calendar Setting", ((com.google.api.services.calendar.CalendarRequest) (obj2)));
            contentproviderclient = TimelyUtils.getGoogleClientVersionPrefKey(account);
            bundle.edit().putString(contentproviderclient, "1").apply();
        }
        // Misplaced declaration of an exception variable
        catch (ContentProviderClient contentproviderclient)
        {
            analyticsLogger.logSyncError("updateGoogleClientVersion", ((HttpResponseException) (contentproviderclient)).statusCode);
            LogUtils.d(TAG, contentproviderclient, "HttpResponseException while writing googleClientVersion for account %s", new Object[] {
                account
            });
        }
        i = bundle.getInt(TimelyUtils.getSmartmailAckPrefKey(account), 5);
        LogUtils.d(TAG, "Smartmail ack = %s", new Object[] {
            Integer.valueOf(i)
        });
        i;
        JVM INSTR tableswitch 1 6: default 684
    //                   1 706
    //                   2 706
    //                   3 745
    //                   4 706
    //                   5 1242
    //                   6 1242;
           goto _L11 _L12 _L12 _L13 _L12 _L14 _L14
_L12:
        break; /* Loop/switch isn't completed */
_L11:
        LogUtils.wtf(TAG, "Unexpected ack value in prefs: %s", new Object[] {
            Integer.valueOf(i)
        });
_L22:
        return true;
_L13:
        bundle = new Setting();
        bundle.value = "ACKNOWLEDGED";
        account = new com.google.api.services.calendar.Calendar.Settings(client);
        bundle = new com.google.api.services.calendar.Calendar.Settings.Update(account, "smartMailAck", bundle);
        ((com.google.api.services.calendar.Calendar.Settings) (account)).this$0.initialize(bundle);
        contentproviderclient = ((AbstractGoogleClientRequest) (bundle)).requestMethod;
        obj = bundle.getAbstractGoogleClient().requestFactory;
        obj2 = ((AbstractGoogleClientRequest) (bundle)).abstractGoogleClient;
        account = String.valueOf(((AbstractGoogleClient) (obj2)).rootUrl);
        obj2 = String.valueOf(((AbstractGoogleClient) (obj2)).servicePath);
        if (((String) (obj2)).length() == 0) goto _L16; else goto _L15
_L15:
        account = account.concat(((String) (obj2)));
_L28:
        int j;
        account = ((HttpRequestFactory) (obj)).buildRequest(contentproviderclient, new GenericUrl(UriTemplate.expand(account, ((AbstractGoogleClientRequest) (bundle)).uriTemplate, bundle, true)), ((AbstractGoogleClientRequest) (bundle)).httpContent);
        (new MethodOverride()).intercept(account);
        account.objectParser = bundle.getAbstractGoogleClient().getObjectParser();
        if (((AbstractGoogleClientRequest) (bundle)).httpContent == null && (((AbstractGoogleClientRequest) (bundle)).requestMethod.equals("POST") || ((AbstractGoogleClientRequest) (bundle)).requestMethod.equals("PUT") || ((AbstractGoogleClientRequest) (bundle)).requestMethod.equals("PATCH")))
        {
            account.content = new EmptyContent();
        }
        ((HttpRequest) (account)).headers.putAll(((AbstractGoogleClientRequest) (bundle)).requestHeaders);
        account.encoding = new GZipEncoding();
        account.responseInterceptor = new com.google.api.client.googleapis.services.AbstractGoogleClientRequest._cls1(bundle, ((HttpRequest) (account)).responseInterceptor, account);
        obj2 = account.execute();
        bundle = ((AbstractGoogleClientRequest) (bundle)).responseClass;
        j = ((HttpResponse) (obj2)).statusCode;
        if (!((HttpResponse) (obj2)).request.requestMethod.equals("HEAD") && j / 100 != 1 && j != 204 && j != 304) goto _L18; else goto _L17
_L17:
        account = ((HttpResponse) (obj2)).getContent();
        if (account == null) goto _L20; else goto _L19
_L19:
        account.close();
          goto _L20
_L30:
        if (!j) goto _L22; else goto _L21
_L21:
        contentproviderclient = ((HttpResponse) (obj2)).request.objectParser;
        obj = ((HttpResponse) (obj2)).getContent();
        if (((HttpResponse) (obj2)).mediaType == null) goto _L24; else goto _L23
_L23:
        account = (String)((HttpResponse) (obj2)).mediaType.parameters.get("charset".toLowerCase());
        if (account != null) goto _L26; else goto _L25
_L25:
        account = null;
          goto _L27
_L24:
        account = Charsets.ISO_8859_1;
_L29:
        contentproviderclient.parseAndClose(((InputStream) (obj)), account, bundle);
          goto _L22
        account;
        analyticsLogger.logSyncError("updateSmartmailAck", ((HttpResponseException) (account)).statusCode);
        LogUtils.d(TAG, account, "Failed to update SM ack value", new Object[0]);
          goto _L22
_L16:
        account = new String(account);
          goto _L28
_L26:
        account = Charset.forName(account);
          goto _L27
_L31:
        account = (String)((HttpResponse) (obj2)).mediaType.parameters.get("charset".toLowerCase());
label0:
        {
            if (account != null)
            {
                break label0;
            }
            account = null;
        }
          goto _L29
        account = Charset.forName(account);
          goto _L29
_L14:
        TimelyUtils.triggerSyncAdapterSyncWithExtras(account, "sync_extra_get_settings", false, new Bundle());
          goto _L22
_L10:
        if (bundle.getBoolean("sync_extra_update_settings", false))
        {
            updateTimelySettings(account, contentproviderclient);
            return true;
        }
        if (bundle.getBoolean("sync_extra_update_recent_notifications", false))
        {
            SyncLog.start("DB: notifications.query");
            bundle = timelyStore.loadRecentlyUsedNotificationsForAccount(account, true);
            account = timelyStore.loadRecentlyUsedNotificationsForAccount(account, false);
            SyncLog.stop("DB: notifications.query");
            contentproviderclient = new StringBuilder();
            serializeReminderList(contentproviderclient, bundle);
            contentproviderclient.append("#");
            serializeReminderList(contentproviderclient, account);
            updateOrInsertNamespacedSetting("goocal", "recentreminders", contentproviderclient.toString(), null);
            return true;
        }
        if (bundle.getBoolean("sync_extra_update_default_notifications", false))
        {
            Object obj1 = bundle.getString("sync_extra_local_calendar_id");
            contentproviderclient = bundle.getString("sync_extra_server_calendar_id");
            boolean flag2 = bundle.getBoolean("sync_extra_all_day");
            List list = loadEventReminders(account, ((String) (obj1)), flag2);
            bundle = new CalendarListEntry();
            if (flag2)
            {
                boolean flag1;
                if (!flag2)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                bundle.defaultReminders = loadEventReminders(account, ((String) (obj1)), flag1);
                bundle.defaultAllDayReminders = list;
            } else
            {
                bundle.defaultReminders = list;
            }
            account = requestExecutor;
            obj1 = new com.google.api.services.calendar.Calendar.CalendarList(client);
            bundle = new com.google.api.services.calendar.Calendar.CalendarList.Update(((com.google.api.services.calendar.Calendar.CalendarList) (obj1)), contentproviderclient, bundle);
            ((com.google.api.services.calendar.Calendar.CalendarList) (obj1)).this$0.initialize(bundle);
            bundle.supportsAllDayReminders = Boolean.valueOf(flag2);
            account.execute("API: Update Calendar", bundle);
            return true;
        } else
        {
            return false;
        }
_L20:
        j = 0;
          goto _L30
_L27:
        if (account != null) goto _L31; else goto _L24
_L18:
        j = 1;
          goto _L30
    }

    public final boolean removeTimelyEventData(String s, long l)
    {
        TimelyStore timelystore = timelyStore;
        LogUtils.d("TimelyStore", "deleting data for event: %s calendar: %d", new Object[] {
            s, Long.valueOf(l)
        });
        return timelystore.database.delete("timelydata", "syncId = ? AND calendarId = ?", new String[] {
            s, Long.toString(l)
        }) > 0;
    }

    public final void saveTimelyDataForEvent(ArrayList arraylist, Event event, Account account, ContentProviderClient contentproviderclient, long l, String s)
        throws ParseException, RemoteException
    {
        TimelyEventData timelyeventdata = new TimelyEventData();
        apiaryEventToTimelyExtras(event, timelyeventdata);
        if (timelyeventdata.hasTimelyData())
        {
            SyncLog.start("DB: timelyData.update");
            timelyStore.insertOrUpdateEventData(event.id, l, timelyeventdata);
            SyncLog.stop("DB: timelyData.update");
            android.content.ContentProviderOperation.Builder builder = ProviderHelper.asSyncAdapter(account).newUpdate(android.provider.CalendarContract.Events.CONTENT_URI);
            ContentValues contentvalues = new ContentValues(1);
            contentvalues.put("sync_data9", Integer.valueOf(SyncUtils.getEventExtrasFlagsValue(event, timelyeventdata).flags).toString());
            arraylist.add(builder.withValues(contentvalues).withSelection(SQLiteDatabaseUtils.makeWhere(new String[] {
                "_sync_id=?", "calendar_id=?"
            }), new String[] {
                event.id, s
            }).build());
            if (arraylist.size() > 100)
            {
                Utilities.applyOperationsAsSyncAdapter(contentproviderclient, account, arraylist);
                arraylist.clear();
            }
        }
    }

    public final void setLocalBirthdaySettings(Account account, ContentProviderClient contentproviderclient)
    {
        Object obj;
        Object obj1;
        Object obj2;
        boolean flag;
        obj2 = null;
        obj1 = null;
        flag = true;
        obj = obj2;
        ProviderHelper providerhelper = ProviderHelper.asClient();
        obj = obj2;
        Uri uri = android.provider.CalendarContract.Calendars.CONTENT_URI;
        obj = obj2;
        String s = SQLiteDatabaseUtils.makeWhere(new String[] {
            ColumnConstants.WHERE_ACCOUNT_AND_TYPE, "((ownerAccount = '#contacts@group.v.calendar.google.com') OR (ownerAccount = 'addressbook#contacts@group.v.calendar.google.com'))"
        });
        obj = obj2;
        String s1 = account.name;
        obj = obj2;
        String s2 = account.type;
        obj = obj2;
        contentproviderclient = providerhelper.query(contentproviderclient, uri, new String[] {
            "ownerAccount"
        }, s, new String[] {
            s1, s2
        }, null);
        if (contentproviderclient == null) goto _L2; else goto _L1
_L1:
        obj = contentproviderclient;
        if (!contentproviderclient.moveToFirst()) goto _L2; else goto _L3
_L3:
        obj = contentproviderclient;
        boolean flag1 = CalendarType.isBirthdayCalendar(contentproviderclient.getString(0), false);
        int i;
        int j;
        if (flag1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
_L12:
        j = i;
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
            j = i;
        }
_L10:
        contentproviderclient = new BirthdaySetting(j);
        obj = new ContentValues(2);
        if (((BirthdaySetting) (contentproviderclient)).visibility)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        ((ContentValues) (obj)).put("settingBirthdayVisibility", Integer.valueOf(i));
        if (((BirthdaySetting) (contentproviderclient)).includeGplusBirthday)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        ((ContentValues) (obj)).put("settingBirthdayIncludeGplus", Integer.valueOf(i));
        SyncLog.start("DB: settings.update");
        timelyStore.accountSettingsStore.updateFromSync(account, ((ContentValues) (obj)));
        SyncLog.stop("DB: settings.update");
        return;
        obj1;
        contentproviderclient = null;
_L9:
        obj = contentproviderclient;
        ThrowableExtension.STRATEGY.printStackTrace(((Throwable) (obj1)));
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
            j = 2;
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_360;
        obj;
        contentproviderclient = ((ContentProviderClient) (obj1));
        obj1 = obj;
_L7:
        obj = contentproviderclient;
        ThrowableExtension.STRATEGY.printStackTrace(((Throwable) (obj1)));
        if (contentproviderclient != null)
        {
            contentproviderclient.close();
            j = 2;
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_360;
        account;
_L5:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        throw account;
        account;
        if (true) goto _L5; else goto _L4
_L4:
        obj1;
        if (true) goto _L7; else goto _L6
_L6:
        obj1;
        if (true) goto _L9; else goto _L8
_L8:
        j = 2;
        if (true) goto _L10; else goto _L2
_L2:
        i = 2;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public final void updateCalendarsSettings(Account account, List list)
    {
        TimelyStore timelystore = timelyStore;
        int i = 0;
        while (i < list.size()) 
        {
            CalendarListEntry calendarlistentry = (CalendarListEntry)list.get(i);
            Object obj = calendarlistentry.conferenceProperties;
            if (obj == null)
            {
                timelystore.database.delete("calendar_settings", "calendar_sync_id = ? AND account_name = ? AND account_type = ?", new String[] {
                    calendarlistentry.id, account.name, account.type
                });
                timelystore.supportedConferenceByCalendarIdMap.remove(Pair.create(account, calendarlistentry.id));
            } else
            {
                ContentValues contentvalues;
                try
                {
                    Object obj1 = com.google.api.client.extensions.android.json.AndroidJsonFactory.InstanceHolder.INSTANCE;
                    ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                    obj1 = ((JsonFactory) (obj1)).createJsonGenerator(bytearrayoutputstream, Charsets.UTF_8);
                    ((JsonGenerator) (obj1)).serialize(false, obj);
                    ((JsonGenerator) (obj1)).flush();
                    obj = bytearrayoutputstream.toString("UTF-8");
                }
                catch (IOException ioexception)
                {
                    LogUtils.wtf("TimelyStore", "Failed to serialize ConferenceProperties to JSON for %s and account %s.", new Object[] {
                        LogUtils.sanitizeName("TimelyStore", calendarlistentry.id), LogUtils.sanitizeAccountName("TimelyStore", account.name)
                    });
                    ioexception = null;
                }
                contentvalues = new ContentValues();
                contentvalues.put("calendar_sync_id", calendarlistentry.id);
                contentvalues.put("account_name", account.name);
                contentvalues.put("account_type", account.type);
                contentvalues.put("conference_properties", ((String) (obj)));
                timelystore.database.insertWithOnConflict("calendar_settings", null, contentvalues, 5);
                timelystore.supportedConferenceByCalendarIdMap.remove(Pair.create(account, calendarlistentry.id));
                obj = (Collection)timelystore.conferenceSubscribers.apply(null);
            }
            i++;
        }
    }

}
