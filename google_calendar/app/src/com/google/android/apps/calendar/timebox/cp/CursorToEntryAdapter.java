// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.cp;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseArray;
import com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags;
import com.google.android.apps.calendar.goals.common.GoalStoreUtils;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.apps.calendar.timebox.AutoValue_Calendar;
import com.google.android.apps.calendar.timebox.AutoValue_TimeRangeEntry;
import com.google.android.apps.calendar.timebox.Calendar;
import com.google.android.apps.calendar.timebox.EventItem;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.converters.CalendarAccessLevelConverter;
import com.google.android.calendar.api.converters.EventAccessLevelConverter;
import com.google.android.calendar.api.converters.ResponseStatusConverter;
import com.google.android.calendar.api.event.CpEventDescriptor;
import com.google.android.calendar.api.event.CpEventKey;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.habit.HabitUtil;
import com.google.common.base.Function;
import com.google.common.base.Platform;
import java.util.TimeZone;

public class CursorToEntryAdapter
    implements Function
{

    private static final String TAG = com/google/android/apps/calendar/timebox/cp/CursorToEntryAdapter.getSimpleName();
    private int applyCount;
    private final LongSparseArray calendarListEntries;
    private final SparseArray calendarsById = new SparseArray();
    private final LongSparseArray eventsById = new LongSparseArray();
    private final SparseArray integerValues = new SparseArray();
    private final TimeZone timeZone;

    CursorToEntryAdapter(TimeZone timezone, CalendarListEntry acalendarlistentry[])
    {
        timeZone = timezone;
        timezone = new LongSparseArray(acalendarlistentry.length);
        int j = acalendarlistentry.length;
        for (int i = 0; i < j; i++)
        {
            CalendarListEntry calendarlistentry = acalendarlistentry[i];
            timezone.put(calendarlistentry.getDescriptor().calendarKey.getLocalId(), calendarlistentry);
        }

        calendarListEntries = timezone;
    }

    private static CpEventDescriptor createDescriptor(Cursor cursor)
    {
        if (cursor.getLong(EventsQueryInfo.Column.ORIGINAL_ID.ordinal()) != 0L)
        {
            long l = cursor.getLong(EventsQueryInfo.Column.EVENT_ID.ordinal());
            long l1 = cursor.getLong(EventsQueryInfo.Column.ORIGINAL_ID.ordinal());
            long l2 = cursor.getLong(EventsQueryInfo.Column.ORIGINAL_INSTANCE_TIME.ordinal());
            return new CpEventDescriptor(CpEventKey.newInstance(l), CpEventKey.newInstance(l1, l2));
        }
        if (cursor.isNull(EventsQueryInfo.Column.RRULE.ordinal()))
        {
            return new CpEventDescriptor(CpEventKey.newInstance(cursor.getLong(EventsQueryInfo.Column.EVENT_ID.ordinal())));
        } else
        {
            return new CpEventDescriptor(CpEventKey.newInstance(cursor.getLong(EventsQueryInfo.Column.EVENT_ID.ordinal()), cursor.getLong(EventsQueryInfo.Column.BEGIN.ordinal())));
        }
    }

    private final Calendar getCalendar(Cursor cursor)
    {
        int i = cursor.getInt(EventsQueryInfo.Column.CALENDAR_ID.ordinal());
        Calendar calendar = (Calendar)calendarsById.get(i);
        Object obj = calendar;
        if (calendar == null)
        {
            CalendarKey calendarkey = CalendarKey.newInstance(i);
            com.google.android.calendar.api.calendarlist.CalendarAccessLevel calendaraccesslevel = CalendarAccessLevelConverter.providerToApi(Integer.valueOf(cursor.getInt(EventsQueryInfo.Column.CALENDAR_ACCESS_LEVEL.ordinal())));
            Object obj1 = EventsQueryInfo.Column.OWNER_ACCOUNT;
            String s = cursor.getString(((EventsQueryInfo.Column) (obj1)).ordinal());
            obj = s;
            if (s != null)
            {
                obj = s;
                if (((EventsQueryInfo.Column) (obj1)).internStrings)
                {
                    obj = s.intern();
                }
            }
            EventsQueryInfo.Column column = EventsQueryInfo.Column.ACCOUNT_TYPE;
            obj1 = cursor.getString(column.ordinal());
            s = ((String) (obj1));
            if (obj1 != null)
            {
                s = ((String) (obj1));
                if (column.internStrings)
                {
                    s = ((String) (obj1)).intern();
                }
            }
            column = EventsQueryInfo.Column.ACCOUNT_NAME;
            obj1 = cursor.getString(column.ordinal());
            cursor = ((Cursor) (obj1));
            if (obj1 != null)
            {
                cursor = ((Cursor) (obj1));
                if (column.internStrings)
                {
                    cursor = ((String) (obj1)).intern();
                }
            }
            obj = new AutoValue_Calendar(calendarkey, calendaraccesslevel, ((String) (obj)), s, cursor);
            calendarsById.put(i, obj);
        }
        return ((Calendar) (obj));
    }

    private final com.google.android.apps.calendar.timebox.EventItem.Event getEvent(long l, Cursor cursor, Calendar calendar, EventExtrasFlags eventextrasflags)
    {
        Object obj;
        com.google.android.apps.calendar.timebox.EventItem.Event event;
        boolean flag1;
        flag1 = true;
        event = (com.google.android.apps.calendar.timebox.EventItem.Event)eventsById.get(l);
        obj = event;
        if (event != null) goto _L2; else goto _L1
_L1:
        boolean flag;
        Object obj2 = new com.google.android.apps.calendar.timebox.AutoValue_EventItem_Event.Builder();
        EventsQueryInfo.Column column = EventsQueryInfo.Column.TITLE;
        Object obj1 = cursor.getString(column.ordinal());
        obj = obj1;
        if (obj1 != null)
        {
            obj = obj1;
            if (column.internStrings)
            {
                obj = ((String) (obj1)).intern();
            }
        }
        obj2 = ((com.google.android.apps.calendar.timebox.EventItem.Event.Builder) (obj2)).setTitle(Platform.nullToEmpty(((String) (obj))));
        int i;
        if (cursor.isNull(EventsQueryInfo.Column.DISPLAY_COLOR.ordinal()))
        {
            obj = null;
        } else
        {
            i = cursor.getInt(EventsQueryInfo.Column.DISPLAY_COLOR.ordinal());
            Integer integer = (Integer)integerValues.get(i);
            obj = integer;
            if (integer == null)
            {
                obj = Integer.valueOf(i);
                integerValues.put(i, obj);
            }
        }
        obj2 = ((com.google.android.apps.calendar.timebox.EventItem.Event.Builder) (obj2)).setColor(((Integer) (obj))).setCalendar(calendar);
        column = EventsQueryInfo.Column.EVENT_LOCATION;
        obj1 = cursor.getString(column.ordinal());
        obj = obj1;
        if (obj1 != null)
        {
            obj = obj1;
            if (column.internStrings)
            {
                obj = ((String) (obj1)).intern();
            }
        }
        obj2 = ((com.google.android.apps.calendar.timebox.EventItem.Event.Builder) (obj2)).setLocation(((String) (obj)));
        column = EventsQueryInfo.Column.ORGANIZER;
        obj1 = cursor.getString(column.ordinal());
        obj = obj1;
        if (obj1 != null)
        {
            obj = obj1;
            if (column.internStrings)
            {
                obj = ((String) (obj1)).intern();
            }
        }
        obj2 = ((com.google.android.apps.calendar.timebox.EventItem.Event.Builder) (obj2)).setOrganizer(((String) (obj))).setSelfAttendeeStatus(ResponseStatusConverter.providerToApi(cursor.getInt(EventsQueryInfo.Column.SELF_ATTENDEE_STATUS.ordinal())));
        i = cursor.getInt(EventsQueryInfo.Column.ACCESS_LEVEL.ordinal());
        obj1 = (Integer)integerValues.get(i);
        obj = obj1;
        if (obj1 == null)
        {
            obj = Integer.valueOf(i);
            integerValues.put(i, obj);
        }
        obj = ((com.google.android.apps.calendar.timebox.EventItem.Event.Builder) (obj2)).setAccessLevel(EventAccessLevelConverter.providerToApi(((Integer) (obj)).intValue()));
        if (cursor.isNull(EventsQueryInfo.Column.RRULE.ordinal()) || !cursor.isNull(EventsQueryInfo.Column.SYNC_ID.ordinal()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = ((com.google.android.apps.calendar.timebox.EventItem.Event.Builder) (obj)).setInstanceModifiable(flag);
        if ((eventextrasflags.flags & 1) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = ((com.google.android.apps.calendar.timebox.EventItem.Event.Builder) (obj)).setHasSmartMail(flag);
        if ((eventextrasflags.flags & 2) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = ((com.google.android.apps.calendar.timebox.EventItem.Event.Builder) (obj)).setHasImageData(flag);
        if ((eventextrasflags.flags & 4) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = ((com.google.android.apps.calendar.timebox.EventItem.Event.Builder) (obj)).setEndTimeUnspecified(flag);
        if ((eventextrasflags.flags & 0x800) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_700;
        }
        if ((eventextrasflags.flags & 0x2000) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0 || cursor.getLong(EventsQueryInfo.Column.DIRTY.ordinal()) != 0L)
        {
            break MISSING_BLOCK_LABEL_700;
        }
        cursor = (CalendarListEntry)calendarListEntries.get(calendar.getKey().getLocalId());
        if (cursor != null && cursor.isPrimary())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_700;
        }
        flag = true;
_L3:
        cursor = ((com.google.android.apps.calendar.timebox.EventItem.Event.Builder) (obj)).setEveryoneDeclined(flag);
        if ((eventextrasflags.flags & 0x1000) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        cursor = cursor.setOutOfOffice(flag);
        if ((eventextrasflags.flags & 0x4000) != 0)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        obj = cursor.setHasTimeProposals(flag).build();
        eventsById.put(l, obj);
_L2:
        return ((com.google.android.apps.calendar.timebox.EventItem.Event) (obj));
        flag = false;
          goto _L3
    }

    private final int getEventExtrasFlags(Cursor cursor)
    {
        if (!"com.google".equals(getCalendar(cursor).getAccountType()))
        {
            break MISSING_BLOCK_LABEL_97;
        }
        EventsQueryInfo.Column column = EventsQueryInfo.Column.EVENT_EXTRAS_FLAGS;
        String s = cursor.getString(column.ordinal());
        cursor = s;
        if (s != null)
        {
            cursor = s;
            if (column.internStrings)
            {
                cursor = s.intern();
            }
        }
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_97;
        }
        int i = Integer.parseInt(cursor);
        return i;
        NumberFormatException numberformatexception;
        numberformatexception;
        String s1 = TAG;
        cursor = String.valueOf(cursor);
        if (cursor.length() != 0)
        {
            cursor = "Unable to parse extras: ".concat(cursor);
        } else
        {
            cursor = new String("Unable to parse extras: ");
        }
        Log.e(s1, cursor, numberformatexception);
        return 0;
    }

    private static com.google.android.apps.calendar.timebox.GoalItem.Goal getGoal(Cursor cursor, Calendar calendar, EventExtrasFlags eventextrasflags)
    {
        EventsQueryInfo.Column column = EventsQueryInfo.Column.HABIT_ID_AND_TYPE;
        String s = cursor.getString(column.ordinal());
        cursor = s;
        if (s != null)
        {
            cursor = s;
            if (column.internStrings)
            {
                cursor = s.intern();
            }
        }
        if (!TextUtils.isEmpty(cursor) && "com.google".equals(calendar.getAccountType()))
        {
            calendar = new com.google.android.apps.calendar.timebox.AutoValue_GoalItem_Goal.Builder();
            cursor = cursor.split(",");
            calendar.setHabitId(cursor[0]);
            boolean flag;
            if (cursor.length > 1)
            {
                try
                {
                    calendar.setType(Integer.valueOf(HabitUtil.checkType(GoalStoreUtils.protoTypeToApiType(Integer.parseInt(cursor[1])))));
                }
                // Misplaced declaration of an exception variable
                catch (Cursor cursor)
                {
                    Log.e(TAG, "Number format exception parsing habit type", cursor);
                }
            }
            if ((eventextrasflags.flags & 0x80) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            calendar.setDone(flag);
            return calendar.build();
        } else
        {
            return null;
        }
    }

    public final Object apply(Object obj)
    {
        Object obj1 = (Cursor)obj;
        applyCount = applyCount + 1;
        long l = ((Cursor) (obj1)).getLong(EventsQueryInfo.Column.EVENT_ID.ordinal());
        obj = getCalendar(((Cursor) (obj1)));
        Object obj2 = EventExtrasFlags.fromExisting(getEventExtrasFlags(((Cursor) (obj1))));
        com.google.android.apps.calendar.timebox.GoalItem.Goal goal = getGoal(((Cursor) (obj1)), ((Calendar) (obj)), ((EventExtrasFlags) (obj2)));
        boolean flag;
        if (goal != null)
        {
            obj = (new com.google.android.apps.calendar.timebox..AutoValue_GoalImpl.Builder()).setEventDescriptor(createDescriptor(((Cursor) (obj1)))).setEvent(getEvent(l, ((Cursor) (obj1)), ((Calendar) (obj)), ((EventExtrasFlags) (obj2)))).setGoal(goal).build();
        } else
        {
            obj2 = (new com.google.android.apps.calendar.timebox.AutoValue_EventImpl.Builder()).setEventDescriptor(createDescriptor(((Cursor) (obj1)))).setEvent(getEvent(l, ((Cursor) (obj1)), ((Calendar) (obj)), ((EventExtrasFlags) (obj2))));
            if (CalendarType.isHolidayCalendar(((Calendar) (obj)).getOwnerAccount()))
            {
                obj = com.google.android.apps.calendar.timebox.Item.SortType.HOLIDAY;
            } else
            {
                obj = com.google.android.apps.calendar.timebox.Item.SortType.EVENT;
            }
            obj = ((com.google.android.apps.calendar.timebox.EventImpl.Builder) (obj2)).setSortType(((com.google.android.apps.calendar.timebox.Item.SortType) (obj))).build();
        }
        obj2 = timeZone;
        if (((Cursor) (obj1)).getInt(EventsQueryInfo.Column.ALL_DAY.ordinal()) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj1 = TimeRange.newInstanceUnsafe(((TimeZone) (obj2)), flag, ((Cursor) (obj1)).getLong(EventsQueryInfo.Column.BEGIN.ordinal()), ((Cursor) (obj1)).getInt(EventsQueryInfo.Column.START_DAY.ordinal()), ((Cursor) (obj1)).getInt(EventsQueryInfo.Column.START_MINUTE.ordinal()), ((Cursor) (obj1)).getLong(EventsQueryInfo.Column.END.ordinal()), ((Cursor) (obj1)).getInt(EventsQueryInfo.Column.END_DAY.ordinal()), ((Cursor) (obj1)).getInt(EventsQueryInfo.Column.END_MINUTE.ordinal()));
        return new AutoValue_TimeRangeEntry(((EventItem) (obj)).getEventDescriptor().getKey(), obj, ((TimeRange) (obj1)));
    }

}
