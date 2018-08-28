// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.DateTimeImpl;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.time.TimeZoneImpl;
import com.google.android.calendar.time.clock.Clock;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestion;
import com.google.personalization.assist.annotate.api.Annotation;
import com.google.personalization.assist.annotate.api.AnnotationFragment;
import com.google.personalization.assist.annotate.api.EventTime;
import com.google.personalization.assist.annotate.api.Time;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public final class TaskAssistUtils
{

    private static final ImmutableSet LOWER_CASE_LANGUAGES = ImmutableSet.construct(4, new Object[] {
        "cs", "nb", "ru", "uk"
    });

    public static AnnotationFragment getLastFragment(AnnotatedSuggestion annotatedsuggestion)
    {
        if (annotatedsuggestion.annotation_ == null)
        {
            annotatedsuggestion = Annotation.DEFAULT_INSTANCE;
        } else
        {
            annotatedsuggestion = annotatedsuggestion.annotation_;
        }
        annotatedsuggestion = ((Annotation) (annotatedsuggestion)).fragment_;
        if (annotatedsuggestion.isEmpty())
        {
            return null;
        } else
        {
            return (AnnotationFragment)annotatedsuggestion.get(annotatedsuggestion.size() - 1);
        }
    }

    public static String getTimeLabel(Context context, EventTime eventtime, boolean flag)
    {
        Object obj;
        if (eventtime.startTime_ == null)
        {
            obj = Time.DEFAULT_INSTANCE;
        } else
        {
            obj = eventtime.startTime_;
        }
        obj = Long.valueOf(((Time) (obj)).timeMs_);
        if (obj == null)
        {
            obj = null;
        } else
        {
            DateTimeService datetimeservice1 = DateTimeService.getInstance();
            obj = new DateTimeImpl(((Long) (obj)).longValue(), datetimeservice1.calendarTimeZone);
        }
        if (obj == null)
        {
            return null;
        }
        boolean flag1 = eventtime.allDay_;
        DateTimeService datetimeservice2 = DateTimeService.getInstance();
        TimeZoneImpl timezoneimpl = datetimeservice2.calendarTimeZone;
        long l3 = datetimeservice2.fromLocalTime(((com.google.calendar.v2.client.service.api.time.DateTime) (obj)), flag1, timezoneimpl);
        long l;
        if ((eventtime.bitField0_ & 2) == 2)
        {
            long l1;
            if (eventtime.endTime_ == null)
            {
                eventtime = Time.DEFAULT_INSTANCE;
            } else
            {
                eventtime = eventtime.endTime_;
            }
            eventtime = Long.valueOf(((Time) (eventtime)).timeMs_);
            if (eventtime == null)
            {
                eventtime = null;
            } else
            {
                DateTimeService datetimeservice = DateTimeService.getInstance();
                eventtime = new DateTimeImpl(eventtime.longValue(), datetimeservice.calendarTimeZone);
            }
            l1 = datetimeservice2.fromLocalTime(eventtime, flag1, timezoneimpl);
            l = l1;
            if (!flag1)
            {
                l = l1;
                if (flag)
                {
                    if (Clock.mockedTimestamp > 0L)
                    {
                        l = Clock.mockedTimestamp;
                    } else
                    {
                        l = System.currentTimeMillis();
                    }
                    return Utils.getDisplayedRangeForKnownContext(l3, l1, l, timezoneimpl.timeZone.getID(), context);
                }
            }
        } else
        {
            l = l3;
        }
        eventtime = context.getResources().getConfiguration().locale;
        long l2;
        if (eventtime == null || !LOWER_CASE_LANGUAGES.contains(eventtime.getLanguage()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (Clock.mockedTimestamp > 0L)
        {
            l2 = Clock.mockedTimestamp;
        } else
        {
            l2 = System.currentTimeMillis();
        }
        return Utils.getDisplayedDatetime(l3, l, l2, timezoneimpl.timeZone.getID(), flag1, flag, context);
    }

}
