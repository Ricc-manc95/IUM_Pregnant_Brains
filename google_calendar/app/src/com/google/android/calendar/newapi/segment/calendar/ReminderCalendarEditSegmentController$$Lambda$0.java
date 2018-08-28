// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import android.accounts.Account;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.color.NamedCalendarColor;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.newapi.model.SettingsMap;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.model.edit.SettingsMapHolder;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.common.base.Function;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.segment.calendar:
//            ReminderCalendarEditSegmentController, AutoValue_UiCalendarUtils_UiReminderCalendar

final class arg._cls1
    implements Function
{

    private final ReminderCalendarEditSegmentController arg$1;

    public final Object apply(Object obj)
    {
        Object obj1 = arg$1;
        obj = (Account)obj;
        String s = ((ReminderCalendarEditSegmentController) (obj1)).calendarName;
        obj1 = (GoogleSettings)(Settings)((SettingsMapHolder)(EditScreenModel)((SegmentController) (obj1)).model).getSettingsMap().settingsMap.get(obj);
        int i;
        if (obj1 == null)
        {
            i = CalendarApi.getColorFactory().defaultTaskColor().getValue();
        } else
        {
            i = ((GoogleSettings) (obj1)).getTaskColor().getValue();
        }
        return new AutoValue_UiCalendarUtils_UiReminderCalendar(s, ((Account) (obj)).name, i, ((Account) (obj)));
    }

    i(ReminderCalendarEditSegmentController remindercalendareditsegmentcontroller)
    {
        arg$1 = remindercalendareditsegmentcontroller;
    }
}
