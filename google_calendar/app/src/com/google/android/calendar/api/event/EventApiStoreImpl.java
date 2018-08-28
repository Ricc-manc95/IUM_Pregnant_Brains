// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.common.ApiOperation;
import com.google.android.calendar.api.event.time.RecurrenceStartShifter;
import com.google.calendar.v2a.android.util.metric.MetricUtils;
import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.event:
//            ContentProviderRead, ContentProviderList, EventModifications, Event

public final class EventApiStoreImpl
{

    public final boolean filterOutGoogleEvents = false;
    public final ContentProviderList list = new ContentProviderList();
    public final ContentProviderRead read = new ContentProviderRead();

    public EventApiStoreImpl(boolean flag)
    {
    }

    static void adjustTimesToFirstPhantom(EventModifications eventmodifications)
    {
        if (!eventmodifications.isRecurring())
        {
            throw new IllegalArgumentException(String.valueOf("Event is not a recurring phantom."));
        }
        long l = eventmodifications.getRecurringFirstStartMillis();
        Object obj;
        for (obj = eventmodifications; ((EventModifications) (obj)).getOriginal() instanceof EventModifications; obj = (EventModifications)((EventModifications) (obj)).getOriginal()) { }
        Event event = ((EventModifications) (obj)).getOriginal();
        if (event == null)
        {
            throw new NullPointerException();
        }
        long l1 = event.getStartMillis();
        Object obj1 = event.getTimeZoneId();
        obj = obj1;
        if (obj1 == null)
        {
            obj = "Etc/GMT";
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(((String) (obj))));
        calendar.setTimeInMillis(l1);
        l1 = eventmodifications.getStartMillis();
        obj1 = eventmodifications.getTimeZoneId();
        obj = obj1;
        if (obj1 == null)
        {
            obj = "Etc/GMT";
        }
        Calendar calendar1 = Calendar.getInstance(TimeZone.getTimeZone(((String) (obj))));
        calendar1.setTimeInMillis(l1);
        obj1 = event.getTimeZoneId();
        obj = obj1;
        if (obj1 == null)
        {
            obj = "Etc/GMT";
        }
        obj = Calendar.getInstance(TimeZone.getTimeZone(((String) (obj))));
        ((Calendar) (obj)).setTimeInMillis(l);
        int i = RecurrenceStartShifter.getJulianDay(calendar1);
        int j = RecurrenceStartShifter.getJulianDay(calendar);
        obj1 = (Calendar)calendar1.clone();
        ((Calendar) (obj1)).set(6, ((Calendar) (obj)).get(6));
        ((Calendar) (obj1)).set(1, ((Calendar) (obj)).get(1));
        ((Calendar) (obj1)).add(6, i - j);
        l = ((Calendar) (obj1)).getTimeInMillis();
        l1 = eventmodifications.getEndMillis();
        long l2 = eventmodifications.getStartMillis();
        eventmodifications.setStartMillis(l);
        eventmodifications.setEndMillis((l1 + l) - l2);
    }

    static Object callWithMetrics(Callable callable, ApiOperation apioperation)
        throws IOException
    {
        callable = ((Callable) (MetricUtils.withMetrics(com.google.common.base.Predicates.ObjectPredicate.ALWAYS_TRUE, callable, apioperation).call()));
        return callable;
        callable;
_L2:
        throw callable;
        callable;
        throw new IOException(callable);
        callable;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static void notifyWidgetAndForceUpsync()
    {
        CalendarApi.getApiContentResolver().notifyChange(CalendarContract.CONTENT_URI, null, true);
        Object obj = String.valueOf(CalendarApi.getApiAppContext().getPackageName()).concat(".APPWIDGET_CALLER_IS_SYNCADAPTER");
        Context context = CalendarApi.getApiAppContext();
        obj = (Intent)(new Intent(((String) (obj)))).clone();
        ((Intent) (obj)).setPackage(context.getPackageName());
        context.sendBroadcast(((Intent) (obj)));
    }
}
