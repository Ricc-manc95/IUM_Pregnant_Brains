// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import com.google.android.calendar.api.color.CalendarColor;

// Referenced classes of package com.google.android.calendar.api.settings:
//            GoogleSettings, SettingsModifications

public interface GoogleSettingsModifications
    extends GoogleSettings, SettingsModifications
{

    public abstract GoogleSettings.SmartMailUpdateMode getSmartMailUpdateMode();

    public abstract boolean isBirthdayModeModified();

    public abstract boolean isDefaultEventDurationMillisModified();

    public abstract boolean isEndTimeUnspecifiedByDefaultModified();

    public abstract boolean isHolidayColorModified();

    public abstract boolean isSmartMailModeModified();

    public abstract boolean isTaskColorModified();

    public abstract boolean isTasksVisibleModified();

    public abstract boolean isTimezoneIdModified();

    public abstract GoogleSettingsModifications setAreTasksVisible(boolean flag);

    public abstract GoogleSettingsModifications setBirthdayMode(GoogleSettings.BirthdayMode birthdaymode);

    public abstract GoogleSettingsModifications setDefaultEventDurationMillis(long l);

    public abstract GoogleSettingsModifications setEndTimeUnspecifiedByDefault(boolean flag);

    public abstract GoogleSettingsModifications setHolidayColor(CalendarColor calendarcolor);

    public abstract GoogleSettingsModifications setSmartMailMode(GoogleSettings.SmartMailMode smartmailmode, GoogleSettings.SmartMailUpdateMode smartmailupdatemode);

    public abstract GoogleSettingsModifications setTaskColor(CalendarColor calendarcolor);

    public abstract GoogleSettingsModifications setTimezoneId(String s);
}
