// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.prefs;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.GoogleSettingsModifications;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.api.settings.SettingsFactory;
import com.google.common.collect.ImmutableMap;

// Referenced classes of package com.google.android.calendar.prefs:
//            PrefService

final class arg._cls2
    implements Consumer
{

    private final PrefService arg$1;
    private final CalendarColor arg$2;

    public final void accept(Object obj)
    {
        PrefService prefservice = arg$1;
        CalendarColor calendarcolor = arg$2;
        obj = prefservice.getPrimarySettings((ImmutableMap)obj);
        if (obj instanceof GoogleSettings)
        {
            obj = CalendarApi.SettingsFactory.modifySettings(((com.google.android.calendar.api.settings.Settings) (obj)));
            if (obj instanceof GoogleSettingsModifications)
            {
                ((GoogleSettingsModifications)obj).setHolidayColor(calendarcolor);
                CalendarApi.Settings.update(((com.google.android.calendar.api.settings.SettingsModifications) (obj)));
            }
        }
    }

    odifications(PrefService prefservice, CalendarColor calendarcolor)
    {
        arg$1 = prefservice;
        arg$2 = calendarcolor;
    }
}
