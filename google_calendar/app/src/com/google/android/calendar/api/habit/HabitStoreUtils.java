// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Entity;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.apps.calendar.goals.common.GoalStoreUtils;
import com.google.android.apps.calendar.util.database.Cursors;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.color.EventColor;
import com.google.android.calendar.api.event.LoadDetailsConstants;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

// Referenced classes of package com.google.android.calendar.api.habit:
//            FitIntegrationStore, HabitUtil, HabitContractModifications, HabitReminders, 
//            HabitImpl, HabitDescriptor, HabitModifications, Habit

final class HabitStoreUtils
{

    public static Habit entityToHabit(Entity entity)
        throws IOException
    {
        Object obj2;
        Object obj3;
        String s3;
        String s4;
        HabitContractImpl.Modification modification;
        int j;
        int k;
        entity = entity.getEntityValues();
        s3 = entity.getAsString("_sync_id");
        String s = entity.getAsString("account");
        obj2 = AccountUtil.newGoogleAccount(s);
        String s1 = entity.getAsString("calendar");
        obj3 = (Long)Cursors.extractSingleEntry(CalendarApi.getApiContentResolver().query(android.provider.CalendarContract.Calendars.CONTENT_URI, LoadDetailsConstants.CALENDAR_LOCAL_ID_PROJECTION, LoadDetailsConstants.SINGLE_CALENDAR_SELECTION, new String[] {
            s1, ((Account) (obj2)).name, ((Account) (obj2)).type
        }, null), com.google.android.calendar.api.calendarlist.CalendarListStoreUtils..Lambda._cls2.$instance, "CalendarId");
        byte abyte0[];
        if (obj3 != null)
        {
            obj2 = new CalendarDescriptor(((Account) (obj2)), s1, CalendarKey.newInstance(((Long) (obj3)).longValue()));
        } else
        {
            obj2 = CalendarDescriptor.createWithoutLocalId(((Account) (obj2)), s1);
        }
        s4 = entity.getAsString("fingerprint");
        k = HabitUtil.checkFitIntegrationStatus((new FitIntegrationStore(CalendarApi.getApiAppContext())).sharedPreferences.getInt(FitIntegrationStore.toKey(s, s1, s3), 0));
        abyte0 = entity.getAsByteArray("data");
        modification = new HabitContractImpl.Modification();
        entity = null;
        j = 0;
        if (abyte0 == null || abyte0.length <= 0) goto _L2; else goto _L1
_L1:
        obj3 = (com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData)MessageNano.mergeFrom(new com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData(), abyte0, 0, abyte0.length);
        if (((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).contract == null) goto _L4; else goto _L3
_L3:
        ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).contract.interval;
        JVM INSTR tableswitch 0 3: default 240
    //                   0 259
    //                   1 523
    //                   2 529
    //                   3 535;
           goto _L5 _L6 _L7 _L8 _L9
_L5:
        throw new IllegalArgumentException("Unknown interval");
_L6:
        int i = 0;
_L14:
        modification.setInterval(i).setDurationMinutes(((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).contract.durationMinutes).setNumInstancesPerInterval(((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).contract.numInstancesPerInterval).setMorningPreferable(((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).contract.timePattern.dailyPattern.morning).setAfternoonPreferable(((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).contract.timePattern.dailyPattern.afternoon).setEveningPreferable(((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).contract.timePattern.dailyPattern.evening).setUntilMillisUtc(((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).contract.untilMillisUtc);
        if (((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).contract.timePattern.dailyPattern.any)
        {
            modification.setAnyDayTimeAcceptable();
        }
_L4:
        Object obj;
        String s2;
        if (((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).reminders != null)
        {
            if (((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).reminders.reminderOverride.length == 0)
            {
                entity = null;
            } else
            {
                entity = Integer.valueOf(((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).reminders.reminderOverride[0].minutes);
            }
            entity = new HabitReminders(((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).reminders.useDefaultReminders, entity, ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).reminders.enableRecommit, ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).reminders.enableFollowup);
        }
        obj = ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).summary;
        s2 = ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).customName;
        if (obj == null)
        {
            if (s2 != null)
            {
                obj = s2;
            } else
            {
                throw new NullPointerException("Both parameters are null");
            }
        }
        obj = (String)obj;
        j = GoalStoreUtils.protoTypeToApiType(((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).type);
        ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).visibility;
        JVM INSTR tableswitch 0 2: default 512
    //                   0 581
    //                   1 654
    //                   2 660;
           goto _L10 _L11 _L12 _L13
_L10:
        throw new IllegalArgumentException("Unknown visibility");
_L7:
        i = 1;
          goto _L14
_L8:
        i = 2;
          goto _L14
_L9:
        i = 3;
          goto _L14
_L11:
        i = 0;
_L15:
        Object obj1;
        if (TextUtils.isEmpty(((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).color))
        {
            obj1 = null;
        } else
        {
            obj1 = ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj3)).color;
        }
        if (obj1 != null)
        {
            obj3 = CalendarApi.getColorFactory().createGoogleEventColor(((String) (obj1)));
            Object obj5 = obj;
            obj1 = entity;
            obj = obj3;
            entity = ((Entity) (obj5));
        } else
        {
            Object obj4 = null;
            obj1 = entity;
            entity = ((Entity) (obj));
            obj = obj4;
        }
_L16:
        return new HabitImpl(new HabitDescriptor(((CalendarDescriptor) (obj2)), s3), s4, entity, ((EventColor) (obj)), modification, ((HabitReminders) (obj1)), j, i, k);
_L12:
        i = 1;
          goto _L15
_L13:
        i = 2;
          goto _L15
_L2:
        i = 0;
        obj = null;
        obj1 = null;
        entity = null;
          goto _L16
    }

    static Entity modificationToEntity(HabitModifications habitmodifications, com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData habitdata)
        throws IOException
    {
        Object obj;
        boolean flag;
        byte byte0;
        byte0 = 2;
        flag = true;
        obj = habitdata;
        if (habitdata == null)
        {
            obj = new com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData();
        }
        if (habitmodifications.isSummaryModified())
        {
            if (habitmodifications.getSummary() != null)
            {
                habitdata = habitmodifications.getSummary();
            } else
            {
                habitdata = "";
            }
            obj.summary = habitdata;
        }
        if (habitmodifications.isTypeModified())
        {
            obj.type = GoalStoreUtils.apiTypeToProtoType(habitmodifications.getType());
        }
        if (habitmodifications.isColorOverrideModified())
        {
            habitdata = habitmodifications.getColorOverride();
            if (habitdata == null)
            {
                habitdata = "";
            } else
            {
                habitdata = habitdata.getKey();
            }
            obj.color = habitdata;
        }
        if (!habitmodifications.isVisibilityModified()) goto _L2; else goto _L1
_L1:
        habitmodifications.getVisibility();
        JVM INSTR tableswitch 0 2: default 144
    //                   0 170
    //                   1 366
    //                   2 372;
           goto _L3 _L4 _L5 _L6
_L3:
        throw new IllegalArgumentException("Unknown visibility");
_L4:
        int i = 0;
_L14:
        obj.visibility = i;
_L2:
        Object obj1;
        if (habitmodifications.isRemindersModified())
        {
            habitdata = habitmodifications.getReminders();
            obj1 = new com.google.calendar.intention.habit.client.nano.ClientHabitProto.Reminders();
            obj1.useDefaultReminders = ((HabitReminders) (habitdata)).useDefaultReminders;
            if (!((HabitReminders) (habitdata)).useDefaultReminders)
            {
                if (((HabitReminders) (habitdata)).overrideMinutes == null)
                {
                    obj1.reminderOverride = new com.google.calendar.intention.habit.client.nano.ClientHabitProto.ReminderInstance[0];
                } else
                {
                    com.google.calendar.intention.habit.client.nano.ClientHabitProto.ReminderInstance reminderinstance = new com.google.calendar.intention.habit.client.nano.ClientHabitProto.ReminderInstance();
                    reminderinstance.method = 2;
                    reminderinstance.minutes = ((HabitReminders) (habitdata)).overrideMinutes.intValue();
                    obj1.reminderOverride = (new com.google.calendar.intention.habit.client.nano.ClientHabitProto.ReminderInstance[] {
                        reminderinstance
                    });
                }
            }
            obj1.enableFollowup = ((HabitReminders) (habitdata)).enableFollowup;
            obj1.enableRecommit = ((HabitReminders) (habitdata)).enableRecommit;
            obj.reminders = ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.Reminders) (obj1));
        }
        habitdata = habitmodifications.getContractModifications();
        if (((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj)).contract == null)
        {
            obj.contract = new com.google.calendar.intention.habit.client.nano.ClientHabitProto.Contract();
        }
        if (habitdata.isDurationMinutesModified())
        {
            ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj)).contract.durationMinutes = habitdata.getDurationMinutes();
        }
        if (!habitdata.isIntervalModified()) goto _L8; else goto _L7
_L7:
        obj1 = ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj)).contract;
        i = byte0;
        habitdata.getInterval();
        JVM INSTR tableswitch 0 3: default 356
    //                   0 421
    //                   1 805
    //                   2 424
    //                   3 811;
           goto _L9 _L10 _L11 _L12 _L13
_L9:
        throw new IllegalArgumentException("Unknown interval");
_L5:
        i = 1;
          goto _L14
_L6:
        i = 2;
          goto _L14
_L10:
        i = 0;
_L12:
        obj1.interval = i;
_L8:
        if (habitdata.isNumInstancesPerIntervalModified())
        {
            ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj)).contract.numInstancesPerInterval = habitdata.getNumInstancesPerInterval();
        }
        if (habitdata.isDailyPatternModified())
        {
            ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj)).contract.timePattern = new com.google.calendar.intention.habit.client.nano.ClientHabitProto.TimePattern();
            ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj)).contract.timePattern.dailyPattern = new com.google.calendar.intention.habit.client.nano.ClientHabitProto.DailyPattern();
            ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj)).contract.timePattern.dailyPattern.any = habitdata.isAnyDayTimeAcceptable();
            ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj)).contract.timePattern.dailyPattern.morning = habitdata.isMorningPreferable();
            ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj)).contract.timePattern.dailyPattern.afternoon = habitdata.isAfternoonPreferable();
            ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj)).contract.timePattern.dailyPattern.evening = habitdata.isEveningPreferable();
        }
        if (habitdata.isUntilMillisUtcModified())
        {
            ((com.google.calendar.intention.habit.client.nano.ClientHabitProto.HabitData) (obj)).contract.untilMillisUtc = habitdata.getUntilMillisUtc();
        }
        i = ((MessageNano) (obj)).computeSerializedSize();
        obj.cachedSize = i;
        habitdata = new byte[i];
        ((MessageNano) (obj)).writeTo(new CodedOutputByteBufferNano(habitdata, 0, habitdata.length));
        if (habitmodifications.isFingerprintModified())
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        obj = new ContentValues(i + 4);
        ((ContentValues) (obj)).put("_sync_id", habitmodifications.getDescriptor().habitId);
        ((ContentValues) (obj)).put("account", habitmodifications.getDescriptor().calendar.account.name);
        ((ContentValues) (obj)).put("calendar", habitmodifications.getDescriptor().calendar.calendarId);
        if (habitmodifications.isFingerprintModified())
        {
            ((ContentValues) (obj)).put("fingerprint", habitmodifications.getFingerprint());
        }
        if (habitmodifications.isFitIntegrationStatusModified())
        {
            (new FitIntegrationStore(CalendarApi.getApiAppContext())).setIntegration(habitmodifications);
        }
        ((ContentValues) (obj)).put("data", habitdata);
        if (habitmodifications.getContractModifications() != null && habitmodifications.getContractModifications().isUntilMillisUtcModified())
        {
            ((ContentValues) (obj)).put("untilMillisUtc", Long.valueOf(habitmodifications.getContractModifications().getUntilMillisUtc()));
        }
        return new Entity(((ContentValues) (obj)));
_L11:
        i = 1;
        continue; /* Loop/switch isn't completed */
_L13:
        i = 3;
        if (true) goto _L12; else goto _L15
_L15:
    }
}
