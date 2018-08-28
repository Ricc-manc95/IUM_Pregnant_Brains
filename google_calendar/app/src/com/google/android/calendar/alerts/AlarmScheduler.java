// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;
import android.util.SparseArray;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.alerts:
//            AlertReceiver, AlarmManagerInterface, AlertUtils

public final class AlarmScheduler
{

    private static final String INSTANCES_PROJECTION[] = {
        "event_id", "begin", "allDay"
    };
    private static final String REMINDERS_PROJECTION[] = {
        "event_id", "minutes", "method"
    };

    private static void queryNextReminderAndSchedule(Cursor cursor, Context context, ContentResolver contentresolver, AlarmManagerInterface alarmmanagerinterface, int i, long l)
    {
        SparseArray sparsearray;
        Time time;
        Object obj3;
        int j;
        long l2;
        j = cursor.getCount();
        Object obj;
        List list;
        int k;
        int j1;
        long l3;
        if (j == 0)
        {
            LogUtils.d("AlarmScheduler", "No events found starting within %d weeks.", new Object[] {
                Long.valueOf(4L)
            });
        } else
        {
            LogUtils.d("AlarmScheduler", "Query result count for events starting within %d weeks: %d", new Object[] {
                Long.valueOf(4L), Integer.valueOf(j)
            });
        }
        sparsearray = new SparseArray();
        time = new Time();
        cursor.moveToPosition(-1);
        j = 0;
        l2 = 0x7fffffffffffffffL;
_L3:
        if (cursor.isAfterLast()) goto _L2; else goto _L1
_L1:
        k = 0;
        sparsearray.clear();
        obj3 = new StringBuilder();
        ((StringBuilder) (obj3)).append('(');
        while (k < i && cursor.moveToNext()) 
        {
            j1 = cursor.getInt(0);
            l3 = cursor.getLong(1);
            boolean flag;
            boolean flag3;
            if (cursor.getInt(2) != 0)
            {
                flag3 = true;
            } else
            {
                flag3 = false;
            }
            if (flag3)
            {
                l3 = Utils.convertAlldayUtcToLocal(time, l3, android.text.format.Time.getCurrentTimezone());
            }
            list = (List)sparsearray.get(j1);
            obj = list;
            if (list == null)
            {
                obj = new ArrayList();
                sparsearray.put(j1, obj);
                ((StringBuilder) (obj3)).append(j1);
                ((StringBuilder) (obj3)).append(",");
            }
            ((List) (obj)).add(Long.valueOf(l3));
            if (LogUtils.maxEnabledLogLevel > 3)
            {
                flag = false;
            } else
            if (Log.isLoggable("AlarmScheduler", 3))
            {
                flag = true;
            } else
            {
                flag = Log.isLoggable("AlarmScheduler", 3);
            }
            if (flag)
            {
                time.impl.timezone = time.timezone;
                time.impl.set(l3);
                time.impl.toMillis(true);
                time.copyFieldsFromImpl();
                time.writeFieldsToImpl();
                LogUtils.d("AlarmScheduler", "Events cursor result -- eventId:%d, allDay:%b, start:%d (%s)", new Object[] {
                    Integer.valueOf(j1), Boolean.valueOf(flag3), Long.valueOf(l3), (new android.text.format.Time(time.impl)).format("%a, %b %d, %Y %I:%M%P")
                });
            }
            k++;
        }
        if (((StringBuilder) (obj3)).charAt(((StringBuilder) (obj3)).length() - 1) == ',')
        {
            ((StringBuilder) (obj3)).deleteCharAt(((StringBuilder) (obj3)).length() - 1);
        }
        ((StringBuilder) (obj3)).append(')');
        Object obj1;
        obj1 = android.provider.CalendarContract.Reminders.CONTENT_URI;
        String as[] = REMINDERS_PROJECTION;
        obj3 = String.valueOf(obj3);
        obj1 = contentresolver.query(((Uri) (obj1)), as, (new StringBuilder(String.valueOf(obj3).length() + 25)).append("method=1 AND event_id IN ").append(((String) (obj3))).toString(), null, null);
label0:
        {
            if (obj1 != null)
            {
                break label0;
            }
            if (obj1 != null)
            {
                ((Cursor) (obj1)).close();
            }
        }
          goto _L3
        ((Cursor) (obj1)).moveToPosition(-1);
_L7:
        if (!((Cursor) (obj1)).moveToNext()) goto _L5; else goto _L4
_L4:
        Object obj2;
        int k1;
        int l1;
        k1 = ((Cursor) (obj1)).getInt(0);
        l1 = ((Cursor) (obj1)).getInt(1);
        obj2 = (List)sparsearray.get(k1);
        if (obj2 == null) goto _L7; else goto _L6
_L6:
        obj2 = ((List) (obj2)).iterator();
        int i1;
        long l4;
        l4 = l2;
        i1 = j;
_L14:
        j = i1;
        l2 = l4;
        if (!((Iterator) (obj2)).hasNext()) goto _L7; else goto _L8
_L8:
        long l5;
        obj3 = (Long)((Iterator) (obj2)).next();
        l5 = ((Long) (obj3)).longValue() - (long)l1 * 60000L;
        boolean flag1;
        if (l5 > l && l5 < l4)
        {
            j = k1;
            l2 = l5;
        } else
        {
            l2 = l4;
            j = i1;
        }
        if (LogUtils.maxEnabledLogLevel <= 3) goto _L10; else goto _L9
_L9:
        flag1 = false;
_L12:
        if (!flag1)
        {
            break; /* Loop/switch isn't completed */
        }
        time.impl.timezone = time.timezone;
        time.impl.set(l5);
        time.impl.toMillis(true);
        time.copyFieldsFromImpl();
        time.writeFieldsToImpl();
        LogUtils.d("AlarmScheduler", "Reminders cursor result -- eventId:%d, startTime:%d, minutes:%d, alarmTime:%d (%s)", new Object[] {
            Integer.valueOf(k1), obj3, Integer.valueOf(l1), Long.valueOf(l5), (new android.text.format.Time(time.impl)).format("%a, %b %d, %Y %I:%M%P")
        });
        break; /* Loop/switch isn't completed */
_L10:
        if (Log.isLoggable("AlarmScheduler", 3))
        {
            flag1 = true;
            continue; /* Loop/switch isn't completed */
        }
        flag1 = Log.isLoggable("AlarmScheduler", 3);
        if (true) goto _L12; else goto _L11
_L5:
        if (obj1 != null)
        {
            ((Cursor) (obj1)).close();
        }
          goto _L3
        cursor;
        context = null;
_L13:
        if (context != null)
        {
            context.close();
        }
        throw cursor;
_L2:
        if (l2 >= 0x7fffffffffffffffL)
        {
            break MISSING_BLOCK_LABEL_1065;
        }
        long l6 = j;
        l4 = 0x5265c00L + l;
        l = l2;
        if (l2 > l4)
        {
            l = l4;
        }
        l += 1000L;
        boolean flag2;
        if (LogUtils.maxEnabledLogLevel > 3)
        {
            flag2 = false;
        } else
        if (Log.isLoggable("AlarmScheduler", 3))
        {
            flag2 = true;
        } else
        {
            flag2 = Log.isLoggable("AlarmScheduler", 3);
        }
        if (flag2)
        {
            cursor = new Time();
            ((Time) (cursor)).impl.timezone = ((Time) (cursor)).timezone;
            ((Time) (cursor)).impl.set(l);
            ((Time) (cursor)).impl.toMillis(true);
            cursor.copyFieldsFromImpl();
            cursor.writeFieldsToImpl();
            LogUtils.d("AlarmScheduler", "Scheduling alarm for EVENT_REMINDER_APP broadcast for event %d at %d (%s)", new Object[] {
                Long.valueOf(l6), Long.valueOf(l), (new android.text.format.Time(((Time) (cursor)).impl)).format("%a, %b %d, %Y %I:%M%P")
            });
        }
        cursor = new Intent("com.google.android.calendar.EVENT_REMINDER_APP");
        cursor.setClass(context, com/google/android/calendar/alerts/AlertReceiver);
        cursor.putExtra("alarmTime", l);
        cursor = PendingIntent.getBroadcast(context, 0, cursor, 0);
        alarmmanagerinterface.setExactAndAllowWhileIdle(0, l, cursor);
        return;
        cursor;
        LogUtils.e("AlarmScheduler", cursor, "Failed to schedule a wake up.", new Object[0]);
        return;
        cursor;
        context = ((Context) (obj1));
        if (true) goto _L13; else goto _L11
_L11:
        i1 = j;
        l4 = l2;
          goto _L14
    }

    public static void scheduleNextAlarm(Context context)
    {
        Cursor cursor;
        Object obj;
        AlarmManagerInterface alarmmanagerinterface = AlertUtils.createAlarmManager(context);
        Object obj1;
        long l;
        long l1;
        long l2;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        cursor = null;
        obj = context.getContentResolver();
        obj1 = new Time();
        ((Time) (obj1)).writeFieldsToImpl();
        ((Time) (obj1)).impl.normalize(false);
        ((Time) (obj1)).copyFieldsFromImpl();
        l2 = ((Time) (obj1)).gmtoff;
        l1 = l + 0x90321000L;
        l2 = l - l2 * 1000L;
        obj1 = android.provider.CalendarContract.Instances.CONTENT_URI.buildUpon();
        ContentUris.appendId(((android.net.Uri.Builder) (obj1)), l - 0x5265c00L);
        ContentUris.appendId(((android.net.Uri.Builder) (obj1)), 0x5265c00L + l1);
        obj = ((ContentResolver) (obj)).query(((android.net.Uri.Builder) (obj1)).build(), INSTANCES_PROJECTION, "(visible=1 AND begin>=? AND begin<=? AND allDay=?) OR (visible=1 AND begin>=? AND begin<=? AND allDay=?)", new String[] {
            String.valueOf(l2), String.valueOf(l2 + 0x90321000L), "1", String.valueOf(l), String.valueOf(l1), "0"
        }, null);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_202;
        }
        cursor = ((Cursor) (obj));
        queryNextReminderAndSchedule(((Cursor) (obj)), context, context.getContentResolver(), alarmmanagerinterface, 50, l);
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
_L2:
        return;
        context;
        obj = null;
_L5:
        cursor = ((Cursor) (obj));
        LogUtils.e("AlarmScheduler", context, "Next alarm scheduling failed.", new Object[0]);
        if (obj == null) goto _L2; else goto _L1
_L1:
        ((Cursor) (obj)).close();
        return;
        context;
_L4:
        if (cursor != null)
        {
            cursor.close();
        }
        throw context;
        context;
        if (true) goto _L4; else goto _L3
_L3:
        context;
          goto _L5
    }

}
