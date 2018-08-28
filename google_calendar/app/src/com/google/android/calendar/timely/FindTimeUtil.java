// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.time.DateTimeImpl;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.time.DateTimeUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.calendar.v2.client.service.api.time.DateTime;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeScenarioDasher, FindTimeScenarioExchange, FindTimeAttendee, AttendeeExplanation, 
//            FindTimeScenario, TimelineSuggestion, TimelineAttendeeEvent, TimelineEvent

public final class FindTimeUtil
{

    private static final ImmutableList FIND_TIME_GOOGLE_SCENARIOS;
    public static final ImmutableList FIND_TIME_SCENARIOS;
    private static FindTimeUtil instance;
    public Context context;

    private FindTimeUtil(Context context1)
    {
        context = context1.getApplicationContext();
    }

    public static boolean containsRooms(List list)
    {
        for (list = list.iterator(); list.hasNext();)
        {
            if (((FindTimeAttendee)list.next()).email.endsWith("resource.calendar.google.com"))
            {
                return true;
            }
        }

        return false;
    }

    public static ImmutableList convertToExplanationForExchange(Collection collection)
    {
        if (collection == null)
        {
            return null;
        }
        ArrayList arraylist = new ArrayList(collection.size());
        for (collection = collection.iterator(); collection.hasNext(); arraylist.add(new AttendeeExplanation(1, (FindTimeAttendee)collection.next(), null))) { }
        return ImmutableList.copyOf(arraylist);
    }

    private static String getDomain(String s)
    {
        s = s.split("@");
        if (s.length != 2)
        {
            return null;
        } else
        {
            return s[1];
        }
    }

    public static FindTimeUtil getInstance(Context context1)
    {
        com/google/android/calendar/timely/FindTimeUtil;
        JVM INSTR monitorenter ;
        if (instance == null)
        {
            instance = new FindTimeUtil(context1);
        }
        context1 = instance;
        com/google/android/calendar/timely/FindTimeUtil;
        JVM INSTR monitorexit ;
        return context1;
        context1;
        throw context1;
    }

    public static Calendar getMinDateForDatePicker(Context context1)
    {
        DateTime datetime = DateTimeUtils.getNowDateTime(context1);
        Calendar calendar;
        if (datetime == null)
        {
            datetime = null;
        } else
        {
            datetime = datetime.withMillisOfDay(0);
        }
        calendar = Calendar.getInstance();
        calendar.setTimeZone(Utils.getTimeZone(context1));
        calendar.setTimeInMillis(datetime.getMillis());
        context1 = Calendar.getInstance();
        context1.set(1, calendar.get(1));
        context1.set(2, calendar.get(2));
        context1.set(5, calendar.get(5));
        return context1;
    }

    private static int getRoomCount(ImmutableList immutablelist)
    {
        int i = 0;
        int j;
        int k;
        for (j = 0; i < immutablelist.size(); j = k)
        {
            k = j;
            if (((AttendeeExplanation)immutablelist.get(i)).attendee.email.endsWith("resource.calendar.google.com"))
            {
                k = j + 1;
            }
            i++;
        }

        return j;
    }

    private static String getSafeDisplayName(AttendeeExplanation attendeeexplanation)
    {
        String s = attendeeexplanation.attendee.displayName;
        if (!TextUtils.isEmpty(s))
        {
            return s;
        }
        if (!TextUtils.isEmpty(attendeeexplanation.attendee.email))
        {
            return attendeeexplanation.attendee.email;
        } else
        {
            return "";
        }
    }

    public static boolean isCalendarTypeSupported(CalendarListEntry calendarlistentry)
    {
        String s = null;
        Account account = calendarlistentry.getDescriptor().account;
        String s1 = calendarlistentry.getDescriptor().calendarId;
        boolean flag = calendarlistentry.isPrimary();
        if (account == null || CalendarType.calculateType(s1) != 6)
        {
            return false;
        }
        if (!flag)
        {
            calendarlistentry = s1.split("@");
            if (calendarlistentry.length != 2)
            {
                calendarlistentry = s;
            } else
            {
                calendarlistentry = calendarlistentry[0];
            }
            s = getDomain(s1);
            if (calendarlistentry == null || s == null)
            {
                return false;
            }
            if (!s.equalsIgnoreCase(getDomain(account.name)))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean isToday(long l)
    {
        DateTimeService datetimeservice = DateTimeService.getInstance();
        long l1;
        if (Clock.mockedTimestamp > 0L)
        {
            l1 = Clock.mockedTimestamp;
        } else
        {
            l1 = System.currentTimeMillis();
        }
        return (new DateTimeImpl(l1, datetimeservice.calendarTimeZone)).getJulianDay() == (new DateTimeImpl(l, datetimeservice.calendarTimeZone)).getJulianDay();
    }

    static final boolean lambda$isFindTimeFeatureSupported$0$FindTimeUtil(Context context1, CalendarListEntry calendarlistentry, FindTimeScenario findtimescenario)
    {
        return findtimescenario.isEnabled(context1, calendarlistentry);
    }

    public final String getDescription(TimelineSuggestion timelinesuggestion, String s, String s1)
    {
        boolean flag1;
        flag1 = false;
        if (timelinesuggestion.isCustom)
        {
            return "";
        }
        if (!AccountUtil.isGoogleExchangeType(s1) && !AccountUtil.isGoogleExchangeGoAccount(s1)) goto _L2; else goto _L1
_L1:
        s1 = timelinesuggestion.attendeeExplanations;
        timelinesuggestion = timelinesuggestion.attendees;
        if (s1 == null || s1.isEmpty())
        {
            return context.getString(0x7f13009f);
        }
        if (s1.size() != 1) goto _L4; else goto _L3
_L3:
        AttendeeExplanation attendeeexplanation;
        boolean flag2;
        boolean flag3;
        attendeeexplanation = (AttendeeExplanation)s1.get(0);
        flag2 = s.equalsIgnoreCase(((FindTimeAttendee)timelinesuggestion.get(0)).email);
        flag3 = ((FindTimeAttendee)timelinesuggestion.get(0)).email.equals(attendeeexplanation.attendee.email);
        attendeeexplanation.conflictType;
        JVM INSTR tableswitch 0 1: default 148
    //                   0 475
    //                   1 151;
           goto _L5 _L6 _L7
_L5:
        return "";
_L7:
        if (!attendeeexplanation.attendee.email.endsWith("resource.calendar.google.com")) goto _L9; else goto _L8
_L8:
        timelinesuggestion = context.getResources().getString(0x7f130215, new Object[] {
            getSafeDisplayName(attendeeexplanation)
        });
_L22:
        return timelinesuggestion;
_L9:
        int i;
        if (flag3 && flag2)
        {
            s = context.getResources().getString(0x7f130220);
        } else
        {
            s = context.getResources().getString(0x7f13020f, new Object[] {
                getSafeDisplayName(attendeeexplanation)
            });
        }
        if (attendeeexplanation.conflictingEvents == null)
        {
            i = 0;
        } else
        {
            i = attendeeexplanation.conflictingEvents.size();
        }
        timelinesuggestion = s;
        if (i != 0)
        {
            timelinesuggestion = ((TimelineEvent) ((TimelineAttendeeEvent)attendeeexplanation.conflictingEvents.get(0))).title;
            s1 = s;
            if (!TextUtils.isEmpty(timelinesuggestion))
            {
                if (Utils.isPortrait(context))
                {
                    i = 0x7f13021e;
                } else
                {
                    i = 0x7f13021f;
                }
                s1 = context.getResources().getString(i, new Object[] {
                    s, timelinesuggestion
                });
            }
            if (attendeeexplanation.conflictingEvents == null)
            {
                i = 0;
            } else
            {
                i = attendeeexplanation.conflictingEvents.size();
            }
            timelinesuggestion = s1;
            if (i >= 2)
            {
                timelinesuggestion = context.getResources();
                if (attendeeexplanation.conflictingEvents == null)
                {
                    i = 0;
                } else
                {
                    i = attendeeexplanation.conflictingEvents.size();
                }
                timelinesuggestion = timelinesuggestion.getString(0x7f130213, new Object[] {
                    Integer.valueOf(i - 1)
                });
                timelinesuggestion = context.getResources().getString(0x7f13021d, new Object[] {
                    s1, timelinesuggestion
                });
            }
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (flag3 && flag2)
        {
            return context.getResources().getString(0x7f130221);
        }
        timelinesuggestion = context.getResources().getString(0x7f13020e, new Object[] {
            getSafeDisplayName(attendeeexplanation)
        });
        continue; /* Loop/switch isn't completed */
_L4:
        timelinesuggestion = s1.iterator();
_L13:
        if (!timelinesuggestion.hasNext()) goto _L11; else goto _L10
_L10:
        if (((AttendeeExplanation)timelinesuggestion.next()).conflictType == 1) goto _L13; else goto _L12
_L12:
        boolean flag = false;
_L15:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_634;
        }
        if (getRoomCount(s1) == s1.size())
        {
            return context.getResources().getString(0x7f130219);
        }
        break; /* Loop/switch isn't completed */
_L11:
        flag = true;
        if (true) goto _L15; else goto _L14
_L14:
        if (getRoomCount(s1) == 0)
        {
            return context.getResources().getString(0x7f130216);
        } else
        {
            return context.getResources().getString(0x7f130218);
        }
        timelinesuggestion = s1.iterator();
_L19:
        if (!timelinesuggestion.hasNext()) goto _L17; else goto _L16
_L16:
        if (((AttendeeExplanation)timelinesuggestion.next()).conflictType == 0) goto _L19; else goto _L18
_L18:
        flag = flag1;
_L21:
        if (flag)
        {
            return context.getResources().getString(0x7f130217);
        }
        break; /* Loop/switch isn't completed */
_L17:
        flag = true;
        if (true) goto _L21; else goto _L20
_L20:
        if (getRoomCount(s1) > 0)
        {
            return context.getResources().getString(0x7f130218);
        }
        timelinesuggestion = context.getResources().getString(0x7f130212);
        if (true) goto _L22; else goto _L2
_L2:
        return Platform.nullToEmpty(timelinesuggestion.explanationMessage);
    }

    public final void getDisplayedDateTime(StringBuilder stringbuilder, StringBuilder stringbuilder1, TimelineSuggestion timelinesuggestion, String s)
    {
        long l1 = ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcStartMillis();
        long l2 = ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcEndMillis();
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        Utils.getDisplayedDateTimesInTimezone(l1, l2, l, s, timelinesuggestion.isAllDay(), 16, context, stringbuilder, stringbuilder1);
    }

    static 
    {
        FIND_TIME_GOOGLE_SCENARIOS = ImmutableList.of(new FindTimeScenarioDasher());
        com.google.common.collect.ImmutableList.Builder builder = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)ImmutableList.builder().addAll(FIND_TIME_GOOGLE_SCENARIOS)).add(new FindTimeScenarioExchange());
        builder.forceCopy = true;
        FIND_TIME_SCENARIOS = ImmutableList.asImmutableList(builder.contents, builder.size);
    }
}
