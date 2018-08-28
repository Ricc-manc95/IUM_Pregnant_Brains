// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.os.Bundle;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.AttendeeModifications;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocationModifications;
import com.google.android.calendar.api.event.notification.NotificationModifications;
import com.google.android.calendar.api.event.time.RecurrenceParser;
import com.google.android.calendar.api.event.userstatus.AutoValue_UserStatus;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.newapi.screen.event.AutoValue_EventDuration;
import com.google.android.calendar.newapi.screen.event.EventDuration;
import com.google.android.calendar.newapi.segment.time.TimeUtils;
import com.google.android.calendar.newapi.utils.LegacyUtils;
import java.util.Calendar;
import java.util.Collections;

class NewEventPopulator
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/screen/NewEventPopulator);

    NewEventPopulator()
    {
    }

    static void updateEventModifications(EventModifications eventmodifications, Bundle bundle, long l, Settings settings, String s)
    {
        Bundle bundle1;
        int i;
        bundle1 = bundle;
        if (bundle == null)
        {
            bundle1 = new Bundle();
        }
        long l1;
        long l3;
        boolean flag;
        if (settings instanceof GoogleSettings)
        {
            bundle = (GoogleSettings)settings;
        } else
        {
            bundle = null;
        }
        if (bundle == null)
        {
            bundle = new AutoValue_EventDuration(0x36ee80L, false);
        } else
        {
            bundle = new AutoValue_EventDuration(bundle.getDefaultEventDurationMillis(), bundle.isEndTimeUnspecifiedByDefault());
        }
        l3 = bundle1.getLong("beginTime", l);
        l1 = bundle.getMillis() + l3;
        l = l1;
        if (bundle1.containsKey("endTime"))
        {
            l = Math.max(l3, bundle1.getLong("endTime", l1));
        }
        if (bundle1.getBoolean("allDay", false))
        {
            settings = Calendar.getInstance();
            settings.setTimeInMillis(l3);
            long l2 = TimeUtils.toMidnight(settings, false);
            settings = Calendar.getInstance();
            settings.setTimeInMillis(l);
            eventmodifications.setToAllDayWithDates(l2, TimeUtils.toMidnight(settings, true));
        } else
        {
            eventmodifications.setToTimedWithTimes(l3, l);
        }
        if (!bundle1.containsKey("endTime") && bundle.isEndTimeUnspecified())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        eventmodifications.setEndTimeUnspecified(flag);
        if (bundle1.containsKey("title"))
        {
            eventmodifications.setSummary(bundle1.getString("title"));
        }
        if (bundle1.containsKey("eventLocation"))
        {
            bundle = bundle1.getString("eventLocation");
            if (!TextUtils.isEmpty(bundle))
            {
                settings = eventmodifications.getLocationModification();
                com.google.android.calendar.api.event.location.EventLocation.Builder builder = new com.google.android.calendar.api.event.location.EventLocation.Builder();
                if (bundle == null)
                {
                    throw new NullPointerException();
                }
                builder.name = (String)bundle;
                settings.addEventLocation(new EventLocation(builder));
            }
        }
        if (bundle1.containsKey("description"))
        {
            eventmodifications.setDescription(bundle1.getString("description"));
        }
        if (bundle1.containsKey("rrule"))
        {
            bundle = bundle1.getString("rrule");
            AttendeeModifications attendeemodifications;
            AttendeeDescriptor attendeedescriptor;
            int j;
            try
            {
                eventmodifications.setRecurrence(RecurrenceParser.parseFromStoreStrings(bundle, null, null, null));
            }
            // Misplaced declaration of an exception variable
            catch (Settings settings)
            {
                LogUtils.e(TAG, settings, "Dropped invalid RRULE: %s", new Object[] {
                    bundle
                });
            }
        }
        if (!bundle1.containsKey("availability")) goto _L2; else goto _L1
_L1:
        bundle1.getInt("availability", 0);
        JVM INSTR tableswitch 1 2: default 424
    //                   1 672
    //                   2 666;
           goto _L3 _L4 _L5
_L3:
        i = 0;
_L7:
        eventmodifications.setAvailability(i);
_L2:
        if (bundle1.containsKey("accessLevel"))
        {
            eventmodifications.setVisibility(LegacyUtils.convertVisibility(bundle1.getInt("accessLevel", 0)));
        }
        if (bundle1.containsKey("android.intent.extra.EMAIL"))
        {
            bundle = bundle1.getStringArray("android.intent.extra.EMAIL");
            if (bundle == null)
            {
                settings = bundle1.getString("android.intent.extra.EMAIL");
                if (!TextUtils.isEmpty(settings))
                {
                    bundle = settings.split("[ ,;]");
                }
            }
            if (bundle != null)
            {
                j = bundle.length;
                i = 0;
                while (i < j) 
                {
                    settings = bundle[i];
                    if (!TextUtils.isEmpty(settings) && settings.contains("@"))
                    {
                        attendeemodifications = eventmodifications.getAttendeeModifications();
                        attendeedescriptor = new AttendeeDescriptor(settings);
                        byte byte0;
                        if (settings != null && settings.endsWith("@resource.calendar.google.com"))
                        {
                            byte0 = 1;
                        } else
                        {
                            byte0 = 0;
                        }
                        if (byte0 != 0)
                        {
                            byte0 = 3;
                        } else
                        {
                            byte0 = 1;
                        }
                        attendeemodifications.addAttendee(new Attendee(attendeedescriptor, settings, 1, byte0, new Response(new com.google.android.calendar.api.event.attendee.Response.Builder())));
                    }
                    i++;
                }
            }
        }
        break; /* Loop/switch isn't completed */
_L5:
        i = 2;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 1;
        if (true) goto _L7; else goto _L6
_L6:
        if (bundle1.containsKey("eventTimezone"))
        {
            bundle = bundle1.getString("eventTimezone");
            try
            {
                eventmodifications.setTimeZoneId(bundle);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Settings settings)
            {
                LogUtils.e(TAG, settings, "Dropped invalid time zone: %s", new Object[] {
                    bundle
                });
            }
            eventmodifications.setTimeZoneId(s);
            return;
        } else
        {
            eventmodifications.setTimeZoneId(s);
            return;
        }
    }

    static void updateOooEventModifications(EventModifications eventmodifications, Bundle bundle, long l, String s, String s1)
    {
        boolean flag;
        if (bundle != null && bundle.getBoolean("out_of_office_event", false))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            l = bundle.getLong("beginTime", l);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(l);
            long l1 = TimeUtils.toMidnight(calendar, false);
            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(l);
            eventmodifications.setToAllDayWithDates(l1, TimeUtils.toMidnight(calendar, true));
            eventmodifications.setTimeZoneId(s1);
            eventmodifications.getNotificationModifications().setNotifications(Collections.emptyList());
            eventmodifications.setAvailability(0);
            eventmodifications.setParticipantStatus(new AutoValue_UserStatus((new com.google.android.calendar.api.event.userstatus..AutoValue_OutOfOffice.Builder()).setAutoDeclineEnabled(false).setAutoDeclineEnabled(true).setCalendarDeclineMessage(s).build()));
            eventmodifications.setSummary(bundle.getString("title"));
            eventmodifications.setVisibility(LegacyUtils.convertVisibility(0));
            return;
        }
    }

}
