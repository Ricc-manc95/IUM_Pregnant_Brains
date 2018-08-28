// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.EventFactory;
import com.google.common.base.Absent;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Collections;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Content;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.DtStamp;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.Sequence;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventReader

final class arg._cls5
    implements Function
{

    private final ICalEventReader arg$1;
    private final CalendarListEntry arg$2;
    private final VEvent arg$3;
    private final String arg$4;
    private final String arg$5;

    public final Object apply(Object obj)
    {
        ICalEventReader icaleventreader = arg$1;
        CalendarListEntry calendarlistentry = arg$2;
        VEvent vevent = arg$3;
        String s1 = arg$4;
        String s = arg$5;
        Collection collection = (Collection)obj;
        obj = (DtStamp)((Component) (vevent)).properties.getProperty("DTSTAMP");
        String s2;
        int i;
        if (obj == null)
        {
            obj = Absent.INSTANCE;
        } else
        {
            obj = new Present(obj);
        }
        s2 = (String)((Optional) (obj)).transform(instance).orNull();
        obj = (Sequence)((Component) (vevent)).properties.getProperty("SEQUENCE");
        if (obj == null)
        {
            obj = Absent.INSTANCE;
        } else
        {
            obj = new Present(obj);
        }
        i = ((Integer)((Optional) (obj)).transform(instance).or(Integer.valueOf(0))).intValue();
        if ((Organizer)((Component) (vevent)).properties.getProperty("ORGANIZER") != null && !"PUBLISH".equals(s))
        {
            obj = ICalEventReader.getEmailWithoutScheme(((Organizer)((Component) (vevent)).properties.getProperty("ORGANIZER")).getValue());
        } else
        {
            obj = null;
        }
        if (collection.isEmpty())
        {
            if (s1 == null)
            {
                obj = CalendarApi.EventFactory.newEvent(calendarlistentry);
            } else
            {
                obj = CalendarApi.EventFactory.newIcsImport(calendarlistentry, ((String) (obj)), s1, i, s2);
            }
            return icaleventreader.createICalEventOperation(Collections.singletonList(obj), vevent, s);
        } else
        {
            return icaleventreader.createICalEventOperation(Lists.newArrayList(new com.google.common.collect.lection(collection, new init>(((String) (obj)), i, s2))), vevent, s);
        }
    }

    ry(ICalEventReader icaleventreader, CalendarListEntry calendarlistentry, VEvent vevent, String s, String s1)
    {
        arg$1 = icaleventreader;
        arg$2 = calendarlistentry;
        arg$3 = vevent;
        arg$4 = s;
        arg$5 = s1;
    }
}
