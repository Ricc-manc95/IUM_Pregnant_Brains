// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alternatecalendar;

import android.util.SparseArray;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.alternatecalendar:
//            AlternateCalendarUtils

static final class ner
    implements OnPropertyChangedListener
{

    public static fullDateString instance;
    public final SparseArray fullDateString = new SparseArray();
    public Locale locale;

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        fullDateString.clear();
    }

    ()
    {
        locale = Locale.getDefault();
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            ((CalendarProperties)calendarproperties).registerListener(13, this);
            return;
        }
    }
}
