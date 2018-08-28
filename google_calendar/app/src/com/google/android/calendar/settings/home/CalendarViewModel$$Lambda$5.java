// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.calendarlist.CalendarListEntryModifications;
import java.util.List;

// Referenced classes of package com.google.android.calendar.settings.home:
//            CalendarViewModel

final class arg._cls2
    implements Consumer
{

    private final int arg$1;
    private final List arg$2;

    public final void accept(Object obj)
    {
        CalendarViewModel.lambda$updateStore$3$CalendarViewModel(arg$1, arg$2, (CalendarListEntryModifications)obj);
    }

    tions(int i, List list)
    {
        arg$1 = i;
        arg$2 = list;
    }
}
