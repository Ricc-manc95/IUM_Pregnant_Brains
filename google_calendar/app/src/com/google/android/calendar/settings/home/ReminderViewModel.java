// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import android.content.res.Resources;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.settings.GoogleSettings;

// Referenced classes of package com.google.android.calendar.settings.home:
//            CalendarListItemViewModel

public final class ReminderViewModel
    implements CalendarListItemViewModel
{

    public CalendarColor color;
    public final GoogleSettings settings;

    public ReminderViewModel(GoogleSettings googlesettings)
    {
        settings = googlesettings;
        color = googlesettings.getTaskColor();
    }

    public final CalendarColor getColor()
    {
        return color;
    }

    public final CharSequence getDisplayName(Resources resources)
    {
        return resources.getString(0x7f1303e2);
    }
}
