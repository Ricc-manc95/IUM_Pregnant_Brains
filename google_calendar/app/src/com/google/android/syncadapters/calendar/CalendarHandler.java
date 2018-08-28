// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Entity;
import android.content.EntityIterator;
import android.content.SyncResult;
import android.database.Cursor;
import android.net.TrafficStats;
import android.os.RemoteException;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apiary.ItemAndEntityHandler;
import com.google.android.apiary.ParseException;
import com.google.android.calendar.time.clock.Clock;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.common.base.Platform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            AnalyticsLoggerExtensionFactory, CalendarSyncAdapterApiary, CalendarRequestExecutor, ProviderHelper, 
//            Utils, SyncLog, SyncAnalyticsLoggerExtension

public final class CalendarHandler
    implements ItemAndEntityHandler
{

    private final Account account;
    private final SyncAnalyticsLoggerExtension analyticsLogger;
    private final com.google.api.services.calendar.Calendar client;
    private final ContentProviderClient provider;
    private final CalendarRequestExecutor requestExecutor;

    public CalendarHandler(com.google.api.services.calendar.Calendar calendar, ContentProviderClient contentproviderclient, Account account1, CalendarRequestExecutor calendarrequestexecutor)
    {
        client = calendar;
        provider = contentproviderclient;
        account = account1;
        if (AnalyticsLoggerExtensionFactory.analyticsLogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLoggerExtensionFactory#initialize() must be called first"));
        } else
        {
            analyticsLogger = AnalyticsLoggerExtensionFactory.analyticsLogger;
            requestExecutor = calendarrequestexecutor;
            return;
        }
    }

    public static int apiaryAccessLevelToProviderAccessLevel(String s)
    {
        byte byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 4: default 48
    //                   -934979389: 110
    //                   -779574157: 96
    //                   -153005144: 124
    //                   106164915: 82;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        break; /* Loop/switch isn't completed */
_L4:
        break MISSING_BLOCK_LABEL_124;
_L6:
        switch (byte0)
        {
        default:
            return 0;

        case 0: // '\0'
            return 700;

        case 1: // '\001'
            return 600;

        case 2: // '\002'
            return 200;

        case 3: // '\003'
            return 100;
        }
_L5:
        if (s.equals("owner"))
        {
            byte0 = 0;
        }
          goto _L6
_L3:
        if (s.equals("writer"))
        {
            byte0 = 1;
        }
          goto _L6
_L2:
        if (s.equals("reader"))
        {
            byte0 = 2;
        }
          goto _L6
        if (s.equals("freeBusyReader"))
        {
            byte0 = 3;
        }
          goto _L6
    }

    public static ContentValues calendarEntryToContentValues(CalendarListEntry calendarlistentry, Boolean boolean1)
    {
        ContentValues contentvalues = new ContentValues();
        Object obj = calendarlistentry.id;
        contentvalues.put("cal_sync1", ((String) (obj)));
        contentvalues.put("ownerAccount", ((String) (obj)));
        obj = calendarlistentry.summary;
        contentvalues.put("name", ((String) (obj)));
        Object obj1 = calendarlistentry.summaryOverride;
        int i;
        long l;
        boolean flag;
        if (obj1 != null)
        {
            contentvalues.put("calendar_displayName", ((String) (obj1)));
        } else
        {
            contentvalues.put("calendar_displayName", ((String) (obj)));
        }
        contentvalues.put("calendar_timezone", calendarlistentry.timeZone);
        obj = calendarlistentry.colorId;
        i = 0xff000000 | Integer.parseInt(calendarlistentry.backgroundColor.substring(1), 16);
        obj1 = (Integer)CalendarSyncAdapterApiary.calendarColors.get(obj);
        contentvalues.put("calendar_color", Integer.valueOf(i));
        if (i != ((Integer) (obj1)).intValue())
        {
            obj = "";
        }
        contentvalues.put("calendar_color_index", ((String) (obj)));
        if (calendarlistentry.primary == null || calendarlistentry.primary == Data.NULL_BOOLEAN)
        {
            flag = false;
        } else
        {
            flag = calendarlistentry.primary.booleanValue();
        }
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        contentvalues.put("isPrimary", Integer.valueOf(i));
        obj = calendarlistentry.selected;
        if (obj == null)
        {
            flag = false;
        } else
        {
            flag = ((Boolean) (obj)).booleanValue();
        }
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        contentvalues.put("cal_sync4", Integer.valueOf(i));
        obj = calendarlistentry.hidden;
        if (obj == null)
        {
            flag = false;
        } else
        {
            flag = ((Boolean) (obj)).booleanValue();
        }
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        contentvalues.put("cal_sync5", Integer.valueOf(i));
        contentvalues.put("calendar_access_level", Integer.valueOf(apiaryAccessLevelToProviderAccessLevel(calendarlistentry.accessRole)));
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        contentvalues.put("cal_sync8", Long.valueOf(l));
        if (boolean1 != null)
        {
            if (boolean1.booleanValue())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            contentvalues.put("cal_sync9", Integer.valueOf(i));
        }
        calendarlistentry = calendarlistentry.categories;
        if (calendarlistentry == null || calendarlistentry.isEmpty())
        {
            calendarlistentry = null;
        } else
        if (calendarlistentry.size() == 1)
        {
            calendarlistentry = (String)calendarlistentry.get(0);
        } else
        {
            calendarlistentry = new ArrayList(calendarlistentry);
            Collections.sort(calendarlistentry);
            calendarlistentry = TextUtils.join(",", calendarlistentry);
        }
        contentvalues.put("cal_sync7", calendarlistentry);
        return contentvalues;
    }

    public static boolean containsCategory(ContentValues contentvalues, String s)
    {
        if (TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException("Empty category");
        }
        String s1;
        for (s1 = contentvalues.getAsString("cal_sync7"); TextUtils.isEmpty(s1) || s1.length() < s.length();)
        {
            return false;
        }

        if (s1.equals(s))
        {
            return true;
        }
        contentvalues = String.valueOf(s);
        String s2 = String.valueOf(",");
        if (s2.length() != 0)
        {
            contentvalues = contentvalues.concat(s2);
        } else
        {
            contentvalues = new String(contentvalues);
        }
        if (s1.startsWith(contentvalues))
        {
            return true;
        }
        contentvalues = String.valueOf(",");
        s2 = String.valueOf(s);
        if (s2.length() != 0)
        {
            contentvalues = contentvalues.concat(s2);
        } else
        {
            contentvalues = new String(contentvalues);
        }
        if (s1.endsWith(contentvalues))
        {
            return true;
        } else
        {
            return s1.contains((new StringBuilder(String.valueOf(s).length() + 2)).append(",").append(s).append(",").toString());
        }
    }

    private final CalendarListEntry contentValuesToCalendarEntry(String s, ContentValues contentvalues, String s1)
        throws RemoteException, IOException, ParseException
    {
        CalendarListEntry calendarlistentry;
        calendarlistentry = new CalendarListEntry();
        Object obj = contentvalues.getAsString("calendar_displayName");
        if (contentvalues.getAsInteger("calendar_access_level").intValue() >= 700)
        {
            Calendar calendar = new Calendar();
            calendar.summary = ((String) (obj));
            obj = requestExecutor;
            com.google.api.services.calendar.Calendar.Calendars calendars = new com.google.api.services.calendar.Calendar.Calendars(client);
            s = new com.google.api.services.calendar.Calendar.Calendars.Patch(calendars, s, calendar);
            calendars.this$0.initialize(s);
            ((CalendarRequestExecutor) (obj)).execute("API: Patch Calendar", (com.google.api.services.calendar.Calendar.Calendars.Patch)s.set("userAgentPackage", s1));
        } else
        {
            s1 = ((String) (obj));
            if (TextUtils.equals(((CharSequence) (obj)), s))
            {
                s1 = null;
            }
            calendarlistentry.summaryOverride = s1;
        }
        calendarlistentry.timeZone = contentvalues.getAsString("calendar_timezone");
        if (contentvalues.containsKey("calendar_color_index"))
        {
            break MISSING_BLOCK_LABEL_217;
        }
        LogUtils.v("CalendarSyncAdapter", "Missing CALENDAR_COLOR_KEY, fetching from provider", new Object[0]);
        s = ProviderHelper.asClient().query(provider, ContentUris.withAppendedId(android.provider.CalendarContract.Calendars.CONTENT_URI, contentvalues.getAsLong("_id").longValue()), new String[] {
            "calendar_color_index"
        }, null, null, null);
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_217;
        }
        if (s.moveToFirst())
        {
            contentvalues.put("calendar_color_index", Long.valueOf(s.getLong(0)));
        }
        s.close();
        s = contentvalues.getAsString("calendar_color_index");
        if (TextUtils.isEmpty(s))
        {
            calendarlistentry.backgroundColor = String.format("#%06x", new Object[] {
                Integer.valueOf(contentvalues.getAsInteger("calendar_color").intValue() & 0xffffff)
            });
            return calendarlistentry;
        } else
        {
            calendarlistentry.colorId = s;
            return calendarlistentry;
        }
        contentvalues;
        s.close();
        throw contentvalues;
    }

    private final CalendarListEntry insertOrSubscribeCalendar(String s, ContentValues contentvalues, ArrayList arraylist, String s1)
        throws IOException
    {
        String s2;
        if (!Utils.containsSyncFlag(contentvalues, "subscribe"))
        {
            LogUtils.e("CalendarSyncAdapter", "Calendars do not support insert", new Object[0]);
            return null;
        }
        s2 = contentvalues.getAsString("ownerAccount");
        if (Platform.stringIsNullOrEmpty(s2))
        {
            LogUtils.e("CalendarSyncAdapter", "Undefined calendar to subscribe to", new Object[0]);
            return null;
        }
        Object obj;
        Object obj1;
        obj1 = new CalendarListEntry();
        obj1.id = s2;
        obj1.selected = Boolean.valueOf(true);
        obj1.hidden = Boolean.valueOf(false);
        obj = new com.google.api.services.calendar.Calendar.CalendarList(client);
        obj1 = new com.google.api.services.calendar.Calendar.CalendarList.Insert(((com.google.api.services.calendar.Calendar.CalendarList) (obj)), ((CalendarListEntry) (obj1)));
        ((com.google.api.services.calendar.Calendar.CalendarList) (obj)).this$0.initialize(((com.google.api.client.googleapis.services.AbstractGoogleClientRequest) (obj1)));
        obj = (com.google.api.services.calendar.Calendar.CalendarList.Insert)((GenericData) (obj1)).set("userAgentPackage", s1);
        obj = (CalendarListEntry)requestExecutor.execute("API: Insert Calendar", ((com.google.api.services.calendar.CalendarRequest) (obj1)));
        obj1 = new ContentValues(0);
        ((ContentValues) (obj1)).put("cal_sync2", contentvalues.getAsString("cal_sync2"));
        GoogleJsonResponseException googlejsonresponseexception;
        int i;
        if (((ContentValues) (obj1)).containsKey("cal_sync2"))
        {
            try
            {
                Object obj2 = ((ContentValues) (obj1)).getAsString("cal_sync2");
                if (((String) (obj2)).length() >= "subscribe".length())
                {
                    obj2 = new HashSet(Arrays.asList(TextUtils.split(((String) (obj2)), "|")));
                    if (((Set) (obj2)).remove("subscribe"))
                    {
                        ((ContentValues) (obj1)).put("cal_sync2", TextUtils.join("|", ((Iterable) (obj2))));
                    }
                }
            }
            // Misplaced declaration of an exception variable
            catch (GoogleJsonResponseException googlejsonresponseexception)
            {
                i = ((HttpResponseException) (googlejsonresponseexception)).statusCode;
                analyticsLogger.logCalendarHttpException("CalendarHandler::SubscribeCalendar", googlejsonresponseexception, s1, String.format(null, "Can't subscribe to calendar %s: %d error", new Object[] {
                    LogUtils.sanitizeName(SyncLog.TAG, s), Integer.valueOf(i)
                }));
                if (i >= 400 && i < 500)
                {
                    LogUtils.e("CalendarSyncAdapter", "Calendar subscribe error, deleting locally: %s", new Object[] {
                        LogUtils.sanitizeName("CalendarSyncAdapter", s2)
                    });
                    CalendarSyncAdapterApiary.addAsSyncAdapterCalendarDeleteOperation(arraylist, account, contentvalues.getAsLong("_id"), false);
                }
                return null;
            }
        }
        CalendarSyncAdapterApiary.addAsSyncAdapterUpdateOperation(arraylist, android.provider.CalendarContract.Calendars.CONTENT_URI, account, ((ContentValues) (obj1)), contentvalues.getAsLong("_id"), null, false);
        return ((CalendarListEntry) (obj));
    }

    private final void revertUnsupportedDelete(long l, ArrayList arraylist)
    {
        ContentValues contentvalues = new ContentValues(1);
        contentvalues.put("deleted", Integer.valueOf(0));
        CalendarSyncAdapterApiary.addAsSyncAdapterUpdateOperation(arraylist, android.provider.CalendarContract.Calendars.CONTENT_URI, account, contentvalues, Long.valueOf(l), null, true);
    }

    public final String getEntitySelection()
    {
        return "dirty = 1";
    }

    public final String itemToSourceId(Object obj)
    {
        return ((CalendarListEntry)obj).id;
    }

    public final EntityIterator newEntityIterator(String s, String as[], int i)
        throws RemoteException, ParseException
    {
        String s1;
        if (i <= 0)
        {
            s1 = null;
        } else
        {
            s1 = (new StringBuilder(21)).append("_id LIMIT ").append(i).toString();
        }
        return android.provider.CalendarContract.CalendarEntity.newEntityIterator(ProviderHelper.asSyncAdapter(account).query(provider, android.provider.CalendarContract.Calendars.CONTENT_URI, null, s, as, s1));
    }

    public final ArrayList sendEntityToServer(Entity entity, SyncResult syncresult)
        throws ParseException, RemoteException, IOException
    {
        Object obj;
        ArrayList arraylist;
        Object obj1;
        int i;
        int j;
        int k;
        SyncLog.start("Send calendar to server");
        obj = entity.getEntityValues();
        obj1 = ((ContentValues) (obj)).getAsString("cal_sync1");
        if (((ContentValues) (obj)).getAsInteger("deleted").intValue() != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        k = TrafficStats.getThreadStatsTag();
        j = 0;
        arraylist = new ArrayList();
        if (((ContentValues) (obj)).containsKey("mutators"))
        {
            syncresult = ((ContentValues) (obj)).getAsString("mutators");
        } else
        {
            syncresult = null;
        }
        if (i == 0) goto _L2; else goto _L1
_L1:
        if (!Platform.stringIsNullOrEmpty(((String) (obj1)))) goto _L4; else goto _L3
_L3:
        LogUtils.e("CalendarSyncAdapter", "Unexpected empty calendar ID, can't unsubscribe", new Object[0]);
        revertUnsupportedDelete(((ContentValues) (obj)).getAsLong("_id").longValue(), arraylist);
        syncresult = null;
        i = j;
        break MISSING_BLOCK_LABEL_119;
_L4:
        if (Utils.containsSyncFlag(((ContentValues) (obj)), "unsubscribe"))
        {
            break MISSING_BLOCK_LABEL_270;
        }
        LogUtils.e("CalendarSyncAdapter", "Calendars do not support delete", new Object[0]);
        revertUnsupportedDelete(((ContentValues) (obj)).getAsLong("_id").longValue(), arraylist);
        syncresult = null;
        i = j;
        continue; /* Loop/switch isn't completed */
        Object obj3 = new com.google.api.services.calendar.Calendar.CalendarList(client);
        com.google.api.services.calendar.Calendar.CalendarList.Delete delete = new com.google.api.services.calendar.Calendar.CalendarList.Delete(((com.google.api.services.calendar.Calendar.CalendarList) (obj3)), ((String) (obj1)));
        ((com.google.api.services.calendar.Calendar.CalendarList) (obj3)).this$0.initialize(delete);
        obj3 = (com.google.api.services.calendar.Calendar.CalendarList.Delete)delete.set("userAgentPackage", syncresult);
        requestExecutor.execute("API: Delete Calendar", delete);
_L5:
        CalendarSyncAdapterApiary.addAsSyncAdapterCalendarDeleteOperation(arraylist, account, ((ContentValues) (obj)).getAsLong("_id"), false);
        break; /* Loop/switch isn't completed */
        googlejsonresponseexception1;
        i = ((HttpResponseException) (googlejsonresponseexception1)).statusCode;
        analyticsLogger.logCalendarHttpException("CalendarHandler::UnsubscribeCalendar", googlejsonresponseexception1, syncresult, String.format(null, "Unsubscribing calendar %s: %d error", new Object[] {
            LogUtils.sanitizeName(SyncLog.TAG, ((String) (obj1))), Integer.valueOf(i)
        }));
        if (i > 500) goto _L6; else goto _L5
_L2:
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_439;
        }
        syncresult = insertOrSubscribeCalendar(((String) (obj1)), ((ContentValues) (obj)), arraylist, syncresult);
        i = j;
        continue; /* Loop/switch isn't completed */
        i = k | 2;
        TrafficStats.setThreadStatsTag(i);
        if (((ContentValues) (obj)).containsKey("calendar_color_index"))
        {
            break MISSING_BLOCK_LABEL_552;
        }
        LogUtils.v("CalendarSyncAdapter", "Missing CALENDAR_COLOR_KEY, fetching from provider", new Object[0]);
        obj2 = ProviderHelper.asClient().query(provider, ContentUris.withAppendedId(android.provider.CalendarContract.Calendars.CONTENT_URI, ((ContentValues) (obj)).getAsLong("_id").longValue()), new String[] {
            "calendar_color_index"
        }, null, null, null);
        if (obj2 == null)
        {
            break MISSING_BLOCK_LABEL_552;
        }
        if (((Cursor) (obj2)).moveToFirst())
        {
            ((ContentValues) (obj)).put("calendar_color_index", Long.valueOf(((Cursor) (obj2)).getLong(0)));
        }
        ((Cursor) (obj2)).close();
        obj2 = contentValuesToCalendarEntry(((String) (obj1)), entity.getEntityValues(), syncresult);
        obj = new com.google.api.services.calendar.Calendar.CalendarList(client);
        obj2 = new com.google.api.services.calendar.Calendar.CalendarList.Patch(((com.google.api.services.calendar.Calendar.CalendarList) (obj)), ((String) (obj1)), ((CalendarListEntry) (obj2)));
        ((com.google.api.services.calendar.Calendar.CalendarList) (obj)).this$0.initialize(((com.google.api.client.googleapis.services.AbstractGoogleClientRequest) (obj2)));
        obj = (com.google.api.services.calendar.Calendar.CalendarList.Patch)((GenericData) (obj2)).set("userAgentPackage", syncresult);
        obj = (CalendarListEntry)requestExecutor.execute("API: Patch Calendar", ((com.google.api.services.calendar.CalendarRequest) (obj2)));
        syncresult = ((SyncResult) (obj));
        continue; /* Loop/switch isn't completed */
        entity;
        ((Cursor) (obj2)).close();
        throw entity;
        entity;
_L9:
        if (i != 0)
        {
            TrafficStats.incrementOperationCount(i, 1);
        }
        TrafficStats.setThreadStatsTag(k);
        throw entity;
        googlejsonresponseexception;
        j = ((HttpResponseException) (googlejsonresponseexception)).statusCode;
        analyticsLogger.logCalendarHttpException("CalendarHandler::UpdateCalendar", googlejsonresponseexception, syncresult, null);
        j;
        JVM INSTR tableswitch 403 403: default 909
    //                   403 752;
           goto _L7 _L8
_L7:
        SyncLog.logError(googlejsonresponseexception, "Can't update calendar %s: %d error", new Object[] {
            LogUtils.sanitizeName(SyncLog.TAG, ((String) (obj1))), Integer.valueOf(j)
        });
        if (i != 0)
        {
            TrafficStats.incrementOperationCount(i, 1);
        }
        TrafficStats.setThreadStatsTag(k);
        return null;
_L8:
        SyncLog.logError(googlejsonresponseexception, "Can't update calendar %s: Forbidden", new Object[] {
            LogUtils.sanitizeName(SyncLog.TAG, ((String) (obj1)))
        });
        syncresult = requestExecutor;
        com.google.api.services.calendar.Calendar.CalendarList calendarlist = new com.google.api.services.calendar.Calendar.CalendarList(client);
        obj1 = new com.google.api.services.calendar.Calendar.CalendarList.Get(calendarlist, ((String) (obj1)));
        calendarlist.this$0.initialize(((com.google.api.client.googleapis.services.AbstractGoogleClientRequest) (obj1)));
        syncresult = (CalendarListEntry)syncresult.execute("API: Get Calendar", ((com.google.api.services.calendar.CalendarRequest) (obj1)));
        continue; /* Loop/switch isn't completed */
_L11:
        if (i != 0)
        {
            TrafficStats.incrementOperationCount(i, 1);
        }
        TrafficStats.setThreadStatsTag(k);
        if (syncresult != null)
        {
            SyncLog.start("Convert calendar to provider operations");
            LogUtils.v("CalendarSyncAdapter", "============= applyItemToEntity =============", new Object[0]);
            LogUtils.v("CalendarSyncAdapter", "item is %s", new Object[] {
                syncresult
            });
            LogUtils.v("CalendarSyncAdapter", "entity is %s", new Object[] {
                entity
            });
            GoogleJsonResponseException googlejsonresponseexception;
            GoogleJsonResponseException googlejsonresponseexception1;
            Object obj2;
            if (entity == null)
            {
                throw new ParseException("Inserts not supported. Entity should not be null");
            }
            syncresult = calendarEntryToContentValues(syncresult, null);
            syncresult.put("dirty", Integer.valueOf(0));
            syncresult.remove("isPrimary");
            entity = entity.getEntityValues().getAsLong("_id");
            CalendarSyncAdapterApiary.addAsSyncAdapterUpdateOperation(arraylist, android.provider.CalendarContract.Calendars.CONTENT_URI, account, syncresult, entity, null, true);
            SyncLog.stop("Convert calendar to provider operations");
        }
        SyncLog.stop("Send calendar to server");
        return arraylist;
        entity;
        i = 0;
        if (true) goto _L9; else goto _L6
_L6:
        syncresult = null;
        i = j;
        if (true) goto _L11; else goto _L10
_L10:
    }
}
