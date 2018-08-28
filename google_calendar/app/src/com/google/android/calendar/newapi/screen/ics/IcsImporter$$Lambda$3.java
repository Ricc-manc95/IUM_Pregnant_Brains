// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.ics;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.EventModifications;
import com.google.common.base.Function;
import com.google.common.util.concurrent.FluentFuture;

// Referenced classes of package com.google.android.calendar.newapi.screen.ics:
//            IcsImporter

final class arg._cls2
    implements Function
{

    private final IcsImporter arg$1;
    private final CalendarListEntry arg$2;

    public final Object apply(Object obj)
    {
        IcsImporter icsimporter = arg$1;
        CalendarListEntry calendarlistentry = arg$2;
        obj = (EventModifications)obj;
        return (FluentFuture)CalendarExecutor.DISK.submit(new <init>(icsimporter, calendarlistentry, ((EventModifications) (obj))));
    }

    I(IcsImporter icsimporter, CalendarListEntry calendarlistentry)
    {
        arg$1 = icsimporter;
        arg$2 = calendarlistentry;
    }
}
