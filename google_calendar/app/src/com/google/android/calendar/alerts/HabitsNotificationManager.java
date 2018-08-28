// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timely.store.GrooveStore;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.event.CpEventKey;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitReminders;
import com.google.android.calendar.belong.BelongUtils;
import com.google.android.calendar.time.TimeUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.habit.HabitInstancesUtil;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.common.collect.Iterators;
import java.util.Arrays;
import java.util.Random;

// Referenced classes of package com.google.android.calendar.alerts:
//            HabitsAlerts, HabitsIntentReceiver, AlarmManagerInterface

public class HabitsNotificationManager
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/alerts/HabitsNotificationManager);

    public HabitsNotificationManager()
    {
    }

    static final boolean bridge$lambda$0$HabitsNotificationManager(Entity entity)
    {
        return entity.getEntityValues().getAsInteger("displayState").equals(Integer.valueOf(1));
    }

    static void dismissExpiredNotifications(Context context)
    {
        GrooveStore groovestore;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        groovestore = GrooveStore.store;
        if (groovestore == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        Entity aentity[] = ((GrooveStore)groovestore).queryHabitNotifications("displayState=1 AND triggerTimeMs<?", new String[] {
            String.valueOf(l - 0x5265c00L)
        }, null, "");
        if (aentity != null)
        {
            int i = 0;
            while (i < aentity.length) 
            {
                setDisplayState(aentity[i], 3);
                HabitsAlerts.cancelLegacy(context, aentity[i].getEntityValues().getAsString("habitParentSyncId"));
                i++;
            }
        }
    }

    static Entity getFollowupNotificationForInstanceId(CpEventKey cpeventkey)
    {
        GrooveStore groovestore = GrooveStore.store;
        if (groovestore == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        cpeventkey = ((GrooveStore)groovestore).queryHabitNotifications("displayState=2 AND type=3 AND eventId=?", new String[] {
            String.valueOf(cpeventkey.localId())
        }, null, "");
        if (cpeventkey == null || cpeventkey.length == 0)
        {
            return null;
        } else
        {
            return cpeventkey[0];
        }
    }

    static Entity[] getNotificationsAtTriggerTime(long l)
    {
        GrooveStore groovestore = GrooveStore.store;
        if (groovestore == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        } else
        {
            return ((GrooveStore)groovestore).queryHabitNotifications("displayState=2 AND triggerTimeMs=?", new String[] {
                String.valueOf(l)
            }, null, "");
        }
    }

    static boolean isInactive(int i)
    {
label0:
        {
            boolean flag1 = false;
            boolean flag;
            if ((0x100 & i) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                if ((0x80 & i) != 0)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        return flag1;
    }

    static void markPastNotificationsAsDismissed(Entity entity)
    {
        markPastNotificationsAsDismissed(entity.getEntityValues().getAsString("habitParentSyncId"), entity.getEntityValues().getAsLong("triggerTimeMs"));
    }

    public static void markPastNotificationsAsDismissed(String s, Long long1)
    {
        GrooveStore groovestore = GrooveStore.store;
        if (groovestore == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        s = ((GrooveStore)groovestore).queryHabitNotifications("displayState<>3 AND habitParentSyncId=? AND triggerTimeMs<?", new String[] {
            s, String.valueOf(long1)
        }, null, "");
        if (s != null)
        {
            for (int i = 0; i < s.length; i++)
            {
                setDisplayState(s[i], 3);
            }

        }
    }

    static boolean processFollowupNotificationTriggerLegacy(Context context, Habit habit, EventKey eventkey, int i, boolean flag)
    {
        if (HabitsAlerts.areNewHabitNotificationsEnabled())
        {
            LogUtils.i(TAG, "Processing follow-up notification is ignored.", new Object[0]);
        } else
        {
            if (!flag && habit.getFitIntegrationStatus() != 10)
            {
                if (habit.getReminders().enableFollowup)
                {
                    BelongUtils.startActivityCheckForFollowup(context, eventkey);
                    return true;
                } else
                {
                    BelongUtils.startActivityCheck(context, true);
                    return false;
                }
            }
            if (habit.getReminders().enableFollowup)
            {
                context = context.getApplicationContext();
                if (HabitsAlerts.areNewHabitNotificationsEnabled())
                {
                    LogUtils.i(HabitsAlerts.TAG, "Showing follow-up notification is ignored.", new Object[0]);
                    return false;
                } else
                {
                    HabitsAlerts.showFollowupNotification(context, habit, eventkey, null, i);
                    return false;
                }
            }
        }
        return false;
    }

    static void processRecommitNotificationTriggerLegacy(Context context, Habit habit, EventKey eventkey, long l, long l1, int i)
    {
label0:
        {
            if (habit.getReminders().enableRecommit)
            {
                context = context.getApplicationContext();
                if (!HabitsAlerts.areNewHabitNotificationsEnabled())
                {
                    break label0;
                }
                LogUtils.i(HabitsAlerts.TAG, "Showing re-commit notification is ignored.", new Object[0]);
            }
            return;
        }
        HabitsAlerts.showRecommitNotification(context, habit, eventkey, l, l1, null, i);
    }

    static void scheduleAlarmForNextNotificationTriggerTime(Context context, AlarmManagerInterface alarmmanagerinterface, long l)
    {
        com/google/android/calendar/alerts/HabitsNotificationManager;
        JVM INSTR monitorenter ;
        Object obj = GrooveStore.store;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        throw new NullPointerException(String.valueOf("Not initialized"));
        context;
        com/google/android/calendar/alerts/HabitsNotificationManager;
        JVM INSTR monitorexit ;
        throw context;
        Entity aentity[] = ((GrooveStore)obj).queryHabitNotifications("displayState=2 AND triggerTimeMs>?", new String[] {
            String.valueOf(l)
        }, "triggerTimeMs ASC", "1");
        if (aentity == null)
        {
            break MISSING_BLOCK_LABEL_158;
        }
        if (aentity.length <= 0)
        {
            break MISSING_BLOCK_LABEL_158;
        }
        aentity = aentity[0];
        Intent intent = new Intent();
        intent.setAction("com.google.android.calendar.intent.action.HABITS_NOTIFICATION");
        intent.setClass(context, com/google/android/calendar/alerts/HabitsIntentReceiver);
        intent.putExtra("habitNotificationTriggerTime", aentity.getEntityValues().getAsLong("triggerTimeMs"));
        context = PendingIntent.getBroadcast(context, 0, intent, 0x8000000);
        alarmmanagerinterface.cancel(context);
        alarmmanagerinterface.setExactAndAllowWhileIdle(0, aentity.getEntityValues().getAsLong("triggerTimeMs").longValue(), context);
        com/google/android/calendar/alerts/HabitsNotificationManager;
        JVM INSTR monitorexit ;
    }

    static void setDisplayState(Entity entity, int i)
    {
        if (entity == null)
        {
            return;
        }
        entity.getEntityValues().put("displayState", Integer.valueOf(i));
        GrooveStore groovestore = GrooveStore.store;
        if (groovestore == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        } else
        {
            ((GrooveStore)groovestore).database.update("habitnotifications", entity.getEntityValues(), "_id=?", new String[] {
                entity.getEntityValues().getAsString("_id")
            });
            return;
        }
    }

    private static void updateNotification(Entity entity, Long long1)
    {
        if (long1 == null)
        {
            return;
        }
        entity.getEntityValues().put("triggerTimeMs", long1);
        long l1 = long1.longValue();
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        if (l1 > l)
        {
            entity.getEntityValues().put("displayState", Integer.valueOf(2));
        }
        long1 = GrooveStore.store;
        if (long1 == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        } else
        {
            ((GrooveStore)long1).database.insertWithOnConflict("habitnotifications", null, entity.getEntityValues(), 5);
            return;
        }
    }

    static void updateNotificationsForHabit(Context context, HabitDescriptor habitdescriptor, int i)
    {
        com.google.android.calendar.api.habit.HabitClient.ReadResult readresult;
        readresult = (com.google.android.calendar.api.habit.HabitClient.ReadResult)CalendarApi.Habits.read(habitdescriptor).await();
        boolean flag;
        if (readresult.getStatus().zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && readresult.getHabit() != null && readresult.getHabit().getReminders() != null) goto _L2; else goto _L1
_L1:
        context = GrooveStore.store;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        context = (GrooveStore)context;
        habitdescriptor = habitdescriptor.habitId;
        long l = ((GrooveStore) (context)).database.delete("habitnotifications", "habitParentSyncId=?", new String[] {
            habitdescriptor
        });
_L40:
        return;
_L2:
        Entity aentity[];
        HabitReminders habitreminders;
        Object obj = habitdescriptor.habitId;
        String s = String.format(null, "CAST(%s as INTEGER)", new Object[] {
            "eventId"
        });
        GrooveStore groovestore = GrooveStore.store;
        if (groovestore == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        aentity = ((GrooveStore)groovestore).queryHabitNotifications("habitParentSyncId=?", new String[] {
            obj
        }, s, null);
        obj = GrooveStore.store;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        obj = (GrooveStore)obj;
        s = habitdescriptor.habitId;
        long l1 = ((GrooveStore) (obj)).database.delete("habitnotifications", "habitParentSyncId=?", new String[] {
            s
        });
        habitreminders = readresult.getHabit().getReminders();
        Cursor cursor;
        ContentResolver contentresolver = context.getContentResolver();
        android.net.Uri uri = android.provider.CalendarContract.Events.CONTENT_URI;
        String as[] = HabitInstancesUtil.getSelectionArgs(habitdescriptor.habitId);
        cursor = contentresolver.query(uri, new String[] {
            "_id", "dtstart", "dtend", "sync_data9"
        }, "(sync_data8=? OR sync_data8 LIKE ?)", as, "_id");
        if (cursor == null) goto _L4; else goto _L3
_L3:
        if (cursor.getCount() == 0) goto _L4; else goto _L5
_L5:
        Random random;
        cursor.moveToFirst();
        random = new Random();
        int j = 0;
_L38:
        int k;
        long l2;
        long l3;
        long l4;
        l2 = cursor.getLong(0);
        l3 = cursor.getLong(1);
        l4 = cursor.getLong(2);
        k = cursor.getInt(3);
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        boolean flag1;
        boolean flag2;
        if ((0x100 & k) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
          goto _L6
_L45:
        if (flag1) goto _L8; else goto _L7
_L7:
        if (!habitdescriptor.calendar.account.name.equals(habitdescriptor.calendar.calendarId))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L10; else goto _L9
_L9:
        if (!habitreminders.useDefaultReminders) goto _L12; else goto _L11
_L11:
        obj3 = Long.valueOf(l3 - (long)(60000 * i));
_L19:
        obj1 = readresult.getHabit();
        obj2 = Utils.getTimeZone(context);
        obj4 = ((Habit) (obj1)).getDescriptor();
        if (!((HabitDescriptor) (obj4)).calendar.account.name.equals(((HabitDescriptor) (obj4)).calendar.calendarId))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L14; else goto _L13
_L13:
        if (!((Habit) (obj1)).getReminders().enableRecommit) goto _L14; else goto _L15
_L15:
        obj2 = Long.valueOf(TimeUtils.eightPmPreviousDay(l3, ((java.util.TimeZone) (obj2))));
_L48:
        obj1 = readresult.getHabit();
        if (!habitdescriptor.calendar.account.name.equals(habitdescriptor.calendar.calendarId))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
          goto _L16
_L20:
        for (; j < aentity.length && (long)aentity[j].getEntityValues().getAsInteger("eventId").intValue() < l2; j++) { }
          goto _L17
_L12:
        if (habitreminders.overrideMinutes == null) goto _L10; else goto _L18
_L18:
        obj3 = Long.valueOf(l3 - (long)(habitreminders.overrideMinutes.intValue() * 60000));
          goto _L19
_L47:
        if (((Habit) (obj1)).isFitIntegrationEnabled() || habitreminders.enableFollowup)
        {
            break MISSING_BLOCK_LABEL_689;
        }
        obj1 = null;
          goto _L20
        obj1 = Long.valueOf((long)random.nextInt(0xdbba0) + l4);
          goto _L20
_L24:
        updateNotification(((Entity) (obj4)), ((Long) (obj3)));
        obj3 = null;
        break MISSING_BLOCK_LABEL_1301;
_L17:
        if (j >= aentity.length || (long)aentity[j].getEntityValues().getAsInteger("eventId").intValue() != l2) goto _L22; else goto _L21
_L21:
        obj4 = aentity[j];
        ((Entity) (obj4)).getEntityValues().getAsInteger("type").intValue();
        JVM INSTR tableswitch 1 3: default 1310
    //                   1 708
    //                   2 817
    //                   3 830;
           goto _L23 _L24 _L25 _L26
_L23:
        LogUtils.wtf(TAG, "Unknown habit notification type.", new Object[0]);
        break MISSING_BLOCK_LABEL_1301;
_L25:
        updateNotification(((Entity) (obj4)), ((Long) (obj2)));
        obj2 = null;
        break MISSING_BLOCK_LABEL_1301;
_L26:
        updateNotification(((Entity) (obj4)), ((Long) (obj1)));
        obj1 = null;
        break MISSING_BLOCK_LABEL_1301;
_L22:
        obj5 = habitdescriptor.habitId;
        obj4 = new ContentValues(5);
        ((ContentValues) (obj4)).put("eventId", Long.valueOf(l2));
        ((ContentValues) (obj4)).put("habitParentSyncId", ((String) (obj5)));
        ((ContentValues) (obj4)).put("displayState", Integer.valueOf(2));
        obj5 = new Entity(((ContentValues) (obj4)));
        if (obj3 == null) goto _L28; else goto _L27
_L27:
        ((ContentValues) (obj4)).put("type", Integer.valueOf(1));
        ((ContentValues) (obj4)).put("triggerTimeMs", ((Long) (obj3)));
        obj3 = GrooveStore.store;
        if (obj3 != null) goto _L30; else goto _L29
_L29:
        throw new NullPointerException(String.valueOf("Not initialized"));
        context;
        habitdescriptor = cursor;
_L41:
        if (habitdescriptor != null)
        {
            habitdescriptor.close();
        }
        throw context;
_L30:
        ((GrooveStore)obj3).database.insertWithOnConflict("habitnotifications", null, ((Entity) (obj5)).getEntityValues(), 5);
_L28:
        if (obj2 == null) goto _L32; else goto _L31
_L31:
        ((ContentValues) (obj4)).put("type", Integer.valueOf(2));
        ((ContentValues) (obj4)).put("triggerTimeMs", ((Long) (obj2)));
        obj2 = GrooveStore.store;
        if (obj2 != null) goto _L34; else goto _L33
_L33:
        throw new NullPointerException(String.valueOf("Not initialized"));
_L34:
        ((GrooveStore)obj2).database.insertWithOnConflict("habitnotifications", null, ((Entity) (obj5)).getEntityValues(), 5);
_L32:
        if (obj1 == null) goto _L8; else goto _L35
_L35:
        ((ContentValues) (obj4)).put("type", Integer.valueOf(3));
        ((ContentValues) (obj4)).put("triggerTimeMs", ((Long) (obj1)));
        obj1 = GrooveStore.store;
        if (obj1 != null) goto _L37; else goto _L36
_L36:
        throw new NullPointerException(String.valueOf("Not initialized"));
_L37:
        ((GrooveStore)obj1).database.insertWithOnConflict("habitnotifications", null, ((Entity) (obj5)).getEntityValues(), 5);
_L8:
        flag2 = cursor.moveToNext();
        if (flag2) goto _L38; else goto _L4
_L4:
        if (cursor != null)
        {
            cursor.close();
        }
        obj1 = Arrays.asList(aentity);
        class .Lambda._cls0
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls0();

            public final boolean apply(Object obj6)
            {
                return HabitsNotificationManager.bridge$lambda$0$HabitsNotificationManager((Entity)obj6);
            }


            private .Lambda._cls0()
            {
            }
        }

        obj2 = .Lambda._cls0..instance;
        if (Iterators.indexOf(((Iterable) (obj1)).iterator(), ((Predicate) (obj2))) != -1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L40; else goto _L39
_L39:
        HabitsAlerts.cancelLegacy(context, habitdescriptor.habitId);
        return;
        context;
        habitdescriptor = null;
          goto _L41
_L6:
        if (flag1) goto _L43; else goto _L42
_L42:
        if ((0x80 & k) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1) goto _L44; else goto _L43
_L43:
        flag1 = true;
          goto _L45
_L16:
        if (!flag1) goto _L47; else goto _L46
_L46:
        obj1 = null;
          goto _L20
_L44:
        flag1 = false;
          goto _L45
_L10:
        obj3 = null;
          goto _L19
_L14:
        obj2 = null;
          goto _L48
        j++;
          goto _L17
    }

}
