// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.RandomAccess;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.component.VEvent;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventReader

final class arg._cls2
    implements oader
{

    private final ICalEventReader arg$1;
    private final Calendar arg$2;

    public final ListenableFuture loadEvents(CalendarListEntry calendarlistentry)
    {
        ICalEventReader icaleventreader = arg$1;
        Calendar calendar = arg$2;
        Object obj = calendar.components.getComponents("VEVENT");
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (net/fortuna/ical4j/model/component/VEvent == null)
        {
            throw new NullPointerException();
        }
        com.google.common.base.cate cate = new com.google.common.base.cate(net/fortuna/ical4j/model/component/VEvent);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (cate == null)
        {
            throw new NullPointerException();
        }
        obj = new com.google.common.collect.e(((Iterable) (obj)), cate);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        obj = Lists.newArrayList(new com.google.common.collect.e(((Iterable) (obj)), 200));
        calendarlistentry = new <init>(icaleventreader, calendar, calendarlistentry);
        if (obj instanceof RandomAccess)
        {
            calendarlistentry = new com.google.common.collect.cessList(((java.util.List) (obj)), calendarlistentry);
        } else
        {
            calendarlistentry = new com.google.common.collect.alList(((java.util.List) (obj)), calendarlistentry);
        }
        return new com.google.common.util.concurrent.e(ImmutableList.copyOf(calendarlistentry), true);
    }

    try(ICalEventReader icaleventreader, Calendar calendar)
    {
        arg$1 = icaleventreader;
        arg$2 = calendar;
    }
}
