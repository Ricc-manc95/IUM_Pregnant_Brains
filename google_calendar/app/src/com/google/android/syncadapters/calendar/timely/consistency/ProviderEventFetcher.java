// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.consistency;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.android.calendarcommon2.RecurrenceSet;
import com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags;
import com.google.android.calendar.utils.permission.PermissionsUtil;
import com.google.android.calendar.utils.version.MncUtil;
import com.google.android.syncadapters.calendar.EventHandler;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.GenericData;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.Callable;

public class ProviderEventFetcher
    implements Callable
{

    private static final String EVENT_PROJECTION[] = {
        "_sync_id", "eventStatus", "event_id", "organizer", "eventLocation", "allDay", "rrule", "rdate", "exrule", "exdate", 
        "eventTimezone", "originalInstanceTime", "title", "description", "begin", "end", "originalAllDay", "original_sync_id", "sync_data4 as sync_data4", "dirty as dirty", 
        "sync_data5 as sync_data5", "sync_data9 as sync_data9", "sync_data8 as sync_data8"
    };
    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/timely/consistency/ProviderEventFetcher);
    private final String calendarId;
    private final Context context;
    private final long endTimeMs;
    public ImmutableList executedRequests;
    private final boolean noLimit;
    private final long startTimeMs;

    public ProviderEventFetcher(Context context1, String s, long l, long l1, boolean flag)
    {
        context = context1;
        calendarId = s;
        startTimeMs = l;
        endTimeMs = l1;
        noLimit = flag;
    }

    private final List call()
    {
        Object obj;
        Object obj1;
        String as[];
        ContentProviderClient contentproviderclient;
        com.google.common.collect.ImmutableList.Builder builder;
        obj = context;
        boolean flag;
        if (!MncUtil.isMnc())
        {
            flag = true;
        } else
        if (PermissionsUtil.checkSelfPermission(((Context) (obj)), "android.permission.READ_CALENDAR") == 0 && PermissionsUtil.checkSelfPermission(((Context) (obj)), "android.permission.WRITE_CALENDAR") == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            LogUtils.wtf(TAG, "Insufficient permissions", new Object[0]);
            return null;
        }
        contentproviderclient = context.getContentResolver().acquireContentProviderClient("com.android.calendar");
        if (contentproviderclient == null)
        {
            LogUtils.e(TAG, "Failed to acquire content provider client", new Object[0]);
            return null;
        }
        as = new String[1];
        as[0] = calendarId;
        builder = new com.google.common.collect.ImmutableList.Builder();
        obj = null;
        obj1 = obj;
        Object obj3 = android.provider.CalendarContract.Instances.CONTENT_URI.buildUpon();
        obj1 = obj;
        ContentUris.appendId(((android.net.Uri.Builder) (obj3)), startTimeMs);
        obj1 = obj;
        ContentUris.appendId(((android.net.Uri.Builder) (obj3)), endTimeMs - 1L);
        obj1 = obj;
        obj = contentproviderclient.query(((android.net.Uri.Builder) (obj3)).build(), EVENT_PROJECTION, "ownerAccount = ?", as, null);
        obj1 = obj;
        obj3 = new GenericData();
        obj1 = obj;
        Object obj5 = android.provider.CalendarContract.Instances.CONTENT_URI.buildUpon();
        obj1 = obj;
        ContentUris.appendId(((android.net.Uri.Builder) (obj5)), startTimeMs);
        obj1 = obj;
        ContentUris.appendId(((android.net.Uri.Builder) (obj5)), endTimeMs - 1L);
        obj1 = obj;
        ((GenericData) (obj3)).set("uri", ((android.net.Uri.Builder) (obj5)).build().toString());
        obj1 = obj;
        ((GenericData) (obj3)).set("projection", EVENT_PROJECTION);
        obj1 = obj;
        ((GenericData) (obj3)).set("selection", "ownerAccount = ?");
        obj1 = obj;
        ((GenericData) (obj3)).set("selectionArgs", as);
        obj1 = obj;
        Object obj2 = (com.google.common.collect.ImmutableList.Builder)builder.add(obj3);
        if (obj != null) goto _L2; else goto _L1
_L1:
        obj1 = null;
_L9:
        if (obj != null && obj1 != null) goto _L4; else goto _L3
_L3:
        LogUtils.e(TAG, "Null cursor while fetching events for %s", new Object[] {
            LogUtils.sanitizeName(TAG, calendarId)
        });
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        if (obj1 != null)
        {
            ((Cursor) (obj1)).close();
        }
        contentproviderclient.release();
        builder.forceCopy = true;
        executedRequests = ImmutableList.asImmutableList(builder.contents, builder.size);
        return null;
_L2:
        obj1 = obj;
        obj3 = new ArrayList();
        obj1 = obj;
        ((Cursor) (obj)).moveToPosition(-1);
_L6:
        obj1 = obj;
        if (!((Cursor) (obj)).moveToNext())
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = obj;
        ((List) (obj3)).add(((Cursor) (obj)).getString(2));
        if (true) goto _L6; else goto _L5
        obj2;
        obj3 = null;
        obj1 = obj;
        obj = obj3;
_L37:
        LogUtils.e(TAG, ((Throwable) (obj2)), "Exception while fetching events for %s", new Object[] {
            LogUtils.sanitizeName(TAG, calendarId)
        });
        if (obj1 != null)
        {
            ((Cursor) (obj1)).close();
        }
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        contentproviderclient.release();
        builder.forceCopy = true;
        executedRequests = ImmutableList.asImmutableList(builder.contents, builder.size);
        return null;
_L5:
        obj1 = obj;
        obj5 = new StringBuilder();
        obj1 = obj;
        ((StringBuilder) (obj5)).append("?");
        int i = 1;
_L8:
        obj1 = obj;
        if (i >= ((List) (obj3)).size())
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = obj;
        ((StringBuilder) (obj5)).append(", ?");
        i++;
        if (true) goto _L8; else goto _L7
_L7:
        obj1 = obj;
        ((List) (obj3)).add("cc:mark");
        obj1 = obj;
        obj2 = android.provider.CalendarContract.ExtendedProperties.CONTENT_URI;
        obj1 = obj;
        obj5 = ((StringBuilder) (obj5)).toString();
        obj1 = obj;
        obj5 = (new StringBuilder(String.valueOf(obj5).length() + 27)).append("event_id IN (").append(((String) (obj5))).append(") AND name").append(" = ?").toString();
        obj1 = obj;
        String as1[] = (String[])((List) (obj3)).toArray(new String[((List) (obj3)).size()]);
        obj1 = obj;
        obj2 = contentproviderclient.query(((Uri) (obj2)), new String[] {
            "event_id"
        }, ((String) (obj5)), as1, null);
        obj1 = obj2;
          goto _L9
_L4:
        as1 = new ArrayList();
        ((Cursor) (obj1)).moveToPosition(-1);
        obj5 = new HashSet();
        for (; ((Cursor) (obj1)).moveToNext(); ((Set) (obj5)).add(((Cursor) (obj1)).getString(0))) { }
        ((Cursor) (obj)).moveToPosition(-1);
        int j = 0;
_L14:
        if (!((Cursor) (obj)).moveToNext()) goto _L11; else goto _L10
_L10:
        if (!((Cursor) (obj)).isNull(0)) goto _L13; else goto _L12
_L12:
        LogUtils.w(TAG, "Fetched object with null id from the server.", new Object[0]);
          goto _L14
        obj5;
        as1 = ((String []) (obj));
        obj2 = obj1;
        obj = obj5;
_L36:
        if (as1 != null)
        {
            as1.close();
        }
        if (obj2 != null)
        {
            ((Cursor) (obj2)).close();
        }
        contentproviderclient.release();
        builder.forceCopy = true;
        executedRequests = ImmutableList.asImmutableList(builder.contents, builder.size);
        throw obj;
_L13:
        if (!((Cursor) (obj)).isNull(14) && !((Cursor) (obj)).isNull(15)) goto _L16; else goto _L15
_L33:
        boolean flag1;
        if (flag1) goto _L14; else goto _L17
_L17:
        Event event;
        event = new Event();
        obj2 = (Event)event.set("event_id", ((Cursor) (obj)).getString(2));
        event.id = ((Cursor) (obj)).getString(0);
        if (((Cursor) (obj)).isNull(1)) goto _L19; else goto _L18
_L18:
        obj2 = Integer.valueOf(((Cursor) (obj)).getInt(1));
        ((Integer) (obj2)).intValue();
        JVM INSTR tableswitch 0 2: default 1812
    //                   0 1860
    //                   1 1853
    //                   2 1846;
           goto _L20 _L21 _L22 _L23
_L20:
        LogUtils.e(TAG, "Unexpected value for status: %d; using tentative.", new Object[] {
            obj2
        });
        obj2 = "tentative";
_L40:
        event.status = ((String) (obj2));
_L19:
        if (!((Cursor) (obj)).isNull(12))
        {
            event.summary = ((Cursor) (obj)).getString(12);
        }
        if (!((Cursor) (obj)).isNull(13))
        {
            event.description = ((Cursor) (obj)).getString(13);
        }
        if (((Cursor) (obj)).isNull(21)) goto _L25; else goto _L24
_L24:
        Exception exception;
        boolean flag2;
        long l;
        long l1;
        boolean flag3;
        boolean flag4;
        if ((EventExtrasFlags.fromCursor(((Cursor) (obj)), 21).flags & 1) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1) goto _L25; else goto _L26
_L26:
        event.description = null;
_L25:
        if (!((Cursor) (obj)).isNull(22) && !((Cursor) (obj)).getString(22).isEmpty())
        {
            event.description = null;
        }
        if (!((Cursor) (obj)).isNull(18))
        {
            event.etag = ((Cursor) (obj)).getString(18);
        }
        if (!((Cursor) (obj)).isNull(3))
        {
            obj2 = new com.google.api.services.calendar.model.Event.Organizer();
            obj2.email = ((Cursor) (obj)).getString(3);
            event.organizer = ((com.google.api.services.calendar.model.Event.Organizer) (obj2));
        }
        if (!((Cursor) (obj)).isNull(4))
        {
            event.location = ((Cursor) (obj)).getString(4);
        }
        if (((Cursor) (obj)).getInt(5) == 1)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        obj2 = new com.android.calendarcommon2.ICalendar.Component("DUMMY", null);
        RecurrenceSet.addPropertiesForRuleStr(((com.android.calendarcommon2.ICalendar.Component) (obj2)), "RRULE", ((Cursor) (obj)).getString(6));
        RecurrenceSet.addPropertyForDateStr(((com.android.calendarcommon2.ICalendar.Component) (obj2)), "RDATE", ((Cursor) (obj)).getString(7));
        RecurrenceSet.addPropertiesForRuleStr(((com.android.calendarcommon2.ICalendar.Component) (obj2)), "EXRULE", ((Cursor) (obj)).getString(8));
        RecurrenceSet.addPropertyForDateStr(((com.android.calendarcommon2.ICalendar.Component) (obj2)), "EXDATE", ((Cursor) (obj)).getString(9));
        if (((com.android.calendarcommon2.ICalendar.Component) (obj2)).propsMap.keySet().size() <= 0) goto _L28; else goto _L27
_L27:
        EventHandler.addRecurrenceToEntry(((com.android.calendarcommon2.ICalendar.Component) (obj2)), event);
        flag1 = true;
_L41:
        obj2 = ((Cursor) (obj)).getString(10);
        if (!TextUtils.isEmpty(((CharSequence) (obj2))))
        {
            break MISSING_BLOCK_LABEL_1885;
        }
        obj2 = TimeZone.getDefault().getID();
        flag4 = false;
_L42:
        event.start = extractTimeFromCursor(((Cursor) (obj)), 14, flag4, ((String) (obj2)), flag3);
        event.end = extractTimeFromCursor(((Cursor) (obj)), 15, flag4, ((String) (obj2)), flag3);
        l = -1L;
        if (!((Cursor) (obj)).isNull(11))
        {
            l = ((Cursor) (obj)).getLong(11);
        }
          goto _L29
_L38:
        event.recurringEventId = ((Cursor) (obj)).getString(17);
        if (event.recurringEventId == null)
        {
            event.recurringEventId = event.id;
        }
_L39:
        if (!flag2) goto _L31; else goto _L30
_L30:
        if (((Cursor) (obj)).getInt(16) == 1)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        event.originalStartTime = createEventDateTime(l, flag3);
_L35:
        obj2 = (Event)event.set("dirty", Integer.valueOf(((Cursor) (obj)).getInt(19)));
        if (!((Cursor) (obj)).isNull(20))
        {
            event.updated = DateTime.parseRfc3339(((Cursor) (obj)).getString(20));
        }
        obj2 = (Event)event.set("cc:mark", Boolean.valueOf(((Set) (obj5)).contains((String)event.get("event_id"))));
        as1.add(event);
        j++;
        flag3 = noLimit;
        if (flag3 || j < 400) goto _L14; else goto _L32
_L32:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        if (obj1 != null)
        {
            ((Cursor) (obj1)).close();
        }
        contentproviderclient.release();
        builder.forceCopy = true;
        executedRequests = ImmutableList.asImmutableList(builder.contents, builder.size);
        return null;
_L16:
        l = ((Cursor) (obj)).getLong(14);
        l1 = ((Cursor) (obj)).getLong(15);
        if (l1 == startTimeMs && l != l1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
          goto _L33
_L31:
        if (!flag1) goto _L35; else goto _L34
_L34:
        event.originalStartTime = event.start;
          goto _L35
_L11:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        if (obj1 != null)
        {
            ((Cursor) (obj1)).close();
        }
        contentproviderclient.release();
        builder.forceCopy = true;
        executedRequests = ImmutableList.asImmutableList(builder.contents, builder.size);
        return as1;
        obj;
        obj2 = null;
        as1 = ((String []) (obj1));
          goto _L36
        exception;
        obj2 = obj;
        obj = exception;
        exception = ((Exception) (obj1));
          goto _L36
        obj2;
        obj = null;
        obj1 = null;
          goto _L37
        obj2;
        Object obj4 = obj;
        obj = obj1;
        obj1 = obj4;
          goto _L37
_L15:
        flag1 = false;
          goto _L33
_L29:
        if (l != -1L)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!flag2 && !flag1) goto _L39; else goto _L38
_L23:
        obj2 = "cancelled";
          goto _L40
_L22:
        obj2 = "confirmed";
          goto _L40
_L21:
        obj2 = "tentative";
          goto _L40
_L28:
        flag1 = false;
          goto _L41
        flag4 = true;
          goto _L42
    }

    private static EventDateTime createEventDateTime(long l, boolean flag)
    {
        EventDateTime eventdatetime = new EventDateTime();
        if (flag)
        {
            eventdatetime.date = new DateTime(true, l, null);
            return eventdatetime;
        } else
        {
            eventdatetime.dateTime = new DateTime(l, 0);
            return eventdatetime;
        }
    }

    private static EventDateTime extractTimeFromCursor(Cursor cursor, int i, boolean flag, String s, boolean flag1)
    {
        if (!cursor.isNull(i))
        {
            cursor = createEventDateTime(cursor.getLong(i), flag1);
            if (flag)
            {
                cursor.timeZone = s;
            }
            return cursor;
        } else
        {
            return null;
        }
    }

    public volatile Object call()
        throws Exception
    {
        return call();
    }

}
