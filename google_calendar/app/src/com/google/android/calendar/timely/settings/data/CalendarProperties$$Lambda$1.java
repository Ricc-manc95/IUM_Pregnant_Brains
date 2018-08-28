// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.settings.data;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.utils.ColorUtils;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.timely.settings.data:
//            CalendarProperties, DefaultCalendarHelper

final class arg._cls1
    implements Consumer
{

    private final CalendarProperties arg$1;

    public final void accept(Object obj)
    {
        CalendarProperties calendarproperties;
        calendarproperties = arg$1;
        calendarproperties.setCalendars((CalendarListEntry[])obj);
        obj = calendarproperties.context.getSharedPreferences("com.google.android.calendar_preferences", 0).getString("preference_defaultTaskAccount", null);
        if (obj == null) goto _L2; else goto _L1
_L1:
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)((ImmutableCollection)calendarproperties.calendars.values()).iterator();
_L5:
        if (!unmodifiableiterator.hasNext()) goto _L2; else goto _L3
_L3:
        if (!TextUtils.equals(((CharSequence) (obj)), ((CalendarListEntry)unmodifiableiterator.next()).getDescriptor().account.name)) goto _L5; else goto _L4
_L4:
        obj = (CalendarDescriptor)DefaultCalendarHelper.readDefaultCalendarDescriptorToSharedPrefs((ImmutableSet)calendarproperties.calendars.keySet(), calendarproperties.context).orNull();
        if (obj == null)
        {
            calendarproperties.computeNewDefaultCalendar();
            return;
        }
        break; /* Loop/switch isn't completed */
_L2:
        calendarproperties.computeNewDefaultTaskAccount();
        if (true) goto _L4; else goto _L6
_L6:
        obj = (CalendarListEntry)calendarproperties.calendars.get(obj);
        if (obj == null || !((CalendarListEntry) (obj)).isPrimary() && !((CalendarListEntry) (obj)).isVisible())
        {
            calendarproperties.computeNewDefaultCalendar();
            return;
        } else
        {
            calendarproperties.setPropertyValue(3, Integer.valueOf(calendarproperties.getDefaultEventDuration(((CalendarListEntry) (obj)).getDescriptor().account)));
            calendarproperties.setPropertyValue(4, Integer.valueOf(ColorUtils.getDisplayColorFromColor(((CalendarListEntry) (obj)).getColor().getValue())));
            return;
        }
    }

    (CalendarProperties calendarproperties)
    {
        arg$1 = calendarproperties;
    }
}
