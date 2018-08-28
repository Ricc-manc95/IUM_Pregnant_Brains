// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.ics;

import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.EventModifications;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.newapi.screen.ics:
//            IcsImporter

final class arg._cls3
    implements Callable
{

    private final IcsImporter arg$1;
    private final CalendarListEntry arg$2;
    private final EventModifications arg$3;

    public final Object call()
    {
        return arg$1.importOrUpdateSingleEvent(arg$2, arg$3);
    }

    I(IcsImporter icsimporter, CalendarListEntry calendarlistentry, EventModifications eventmodifications)
    {
        arg$1 = icsimporter;
        arg$2 = calendarlistentry;
        arg$3 = eventmodifications;
    }
}
