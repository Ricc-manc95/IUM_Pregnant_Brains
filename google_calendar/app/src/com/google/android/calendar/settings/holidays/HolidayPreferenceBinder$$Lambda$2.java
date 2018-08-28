// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.holidays;

import com.google.android.calendar.prefs.PrefService;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.calendar.settings.holidays:
//            HolidayViewModel

final class arg._cls1
    implements Supplier
{

    private final HolidayViewModel arg$1;

    public final Object get()
    {
        return PrefService.getInstance(arg$1.context).holidaysColor;
    }

    (HolidayViewModel holidayviewmodel)
    {
        arg$1 = holidayviewmodel;
    }
}
