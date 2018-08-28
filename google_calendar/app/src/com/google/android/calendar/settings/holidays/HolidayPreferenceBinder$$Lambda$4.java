// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.holidays;

import com.google.common.base.Function;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.settings.holidays:
//            HolidayPreferenceBinder, HolidayViewModel

final class arg._cls1
    implements Function
{

    private final HolidayPreferenceBinder arg$1;

    public final Object apply(Object obj)
    {
        Object obj1 = arg$1;
        obj = (String)obj;
        obj1 = ((HolidayPreferenceBinder) (obj1)).viewModel;
        arg._cls1 _lcls1 = (arg._cls1)((HolidayViewModel) (obj1)).countryHolidaysById.get(obj);
        if (_lcls1 != null)
        {
            return _lcls1;
        } else
        {
            return (Id)((HolidayViewModel) (obj1)).religiousHolidaysById.get(obj);
        }
    }

    (HolidayPreferenceBinder holidaypreferencebinder)
    {
        arg$1 = holidaypreferencebinder;
    }
}
