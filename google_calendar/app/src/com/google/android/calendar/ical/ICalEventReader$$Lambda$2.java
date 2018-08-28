// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.EventClient;
import com.google.common.base.Absent;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.util.concurrent.AbstractTransformFuture;
import java.util.Collections;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.RecurrenceId;
import net.fortuna.ical4j.model.property.Uid;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventReader

final class arg._cls3
    implements Function
{

    private final ICalEventReader arg$1;
    private final Calendar arg$2;
    private final CalendarListEntry arg$3;

    public final Object apply(Object obj)
    {
        ICalEventReader icaleventreader = arg$1;
        Object obj1 = arg$2;
        CalendarListEntry calendarlistentry = arg$3;
        VEvent vevent = (VEvent)obj;
        obj = (Method)((Calendar) (obj1)).properties.getProperty("METHOD");
        String s;
        if (obj == null)
        {
            obj = Absent.INSTANCE;
        } else
        {
            obj = new Present(obj);
        }
        obj1 = (String)((Optional) (obj)).transform(..instance).or("PUBLISH");
        obj = (Uid)((Component) (vevent)).properties.getProperty("UID");
        if (obj == null)
        {
            obj = Absent.INSTANCE;
        } else
        {
            obj = new Present(obj);
        }
        s = (String)((Optional) (obj)).transform(..instance).orNull();
        obj = (RecurrenceId)((Component) (vevent)).properties.getProperty("RECURRENCE-ID");
        if (obj == null)
        {
            obj = Absent.INSTANCE;
        } else
        {
            obj = new Present(obj);
        }
        obj = ((Optional) (obj)).transform(..instance).transform(..instance);
        if (s == null)
        {
            obj = Collections.emptyList();
            if (obj == null)
            {
                obj = com.google.common.util.concurrent.uccessfulFuture.NULL;
            } else
            {
                obj = new com.google.common.util.concurrent.uccessfulFuture(obj);
            }
        } else
        {
            obj = AbstractTransformFuture.create(icaleventreader.eventClient.icsList(Collections.singleton(s)), new (icaleventreader, ((Optional) (obj))), CalendarExecutor.BACKGROUND);
        }
        return AbstractTransformFuture.create(((com.google.common.util.concurrent.ListenableFuture) (obj)), new (icaleventreader, calendarlistentry, vevent, s, ((String) (obj1))), com.google.common.util.concurrent.or.INSTANCE);
    }

    try(ICalEventReader icaleventreader, Calendar calendar, CalendarListEntry calendarlistentry)
    {
        arg$1 = icaleventreader;
        arg$2 = calendar;
        arg$3 = calendarlistentry;
    }
}
