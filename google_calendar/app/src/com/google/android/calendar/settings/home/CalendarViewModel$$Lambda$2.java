// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.calendarlist.CalendarListEntryModifications;

// Referenced classes of package com.google.android.calendar.settings.home:
//            CalendarViewModel

public final class arg._cls1
    implements Consumer
{

    private final CalendarViewModel arg$1;

    public final void accept(Object obj)
    {
        CalendarViewModel calendarviewmodel = arg$1;
        ((CalendarListEntryModifications)obj).setDisplayName(calendarviewmodel.displayName);
    }

    public tions(CalendarViewModel calendarviewmodel)
    {
        arg$1 = calendarviewmodel;
    }
}
