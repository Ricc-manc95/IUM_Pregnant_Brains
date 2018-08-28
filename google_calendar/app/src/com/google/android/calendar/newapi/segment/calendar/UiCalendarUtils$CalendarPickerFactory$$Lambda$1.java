// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import android.accounts.Account;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.newapi.model.SettingsMap;
import com.google.common.base.Function;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.segment.calendar:
//            AutoValue_UiCalendarUtils_UiReminderCalendar

final class arg._cls2
    implements Function
{

    private final String arg$1;
    private final SettingsMap arg$2;

    public final Object apply(Object obj)
    {
        String s = arg$1;
        SettingsMap settingsmap = arg$2;
        Account account = (Account)obj;
        obj = (GoogleSettings)(Settings)settingsmap.settingsMap.get(account);
        int i;
        if (obj == null)
        {
            obj = CalendarApi.getColorFactory().defaultTaskColor();
        } else
        {
            obj = ((GoogleSettings) (obj)).getTaskColor();
        }
        i = ((EntityColor) (obj)).getValue();
        return new AutoValue_UiCalendarUtils_UiReminderCalendar(s, account.name, i, account);
    }

    i(String s, SettingsMap settingsmap)
    {
        arg$1 = s;
        arg$2 = settingsmap;
    }
}
