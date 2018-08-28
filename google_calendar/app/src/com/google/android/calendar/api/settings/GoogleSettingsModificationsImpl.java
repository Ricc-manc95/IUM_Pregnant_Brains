// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.common.FieldModification;
import com.google.android.calendar.api.common.TimeZoneHelper;

// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsModificationsImpl, GoogleSettingsModifications, GoogleSettingsImpl, GoogleSettings, 
//            Settings

public final class GoogleSettingsModificationsImpl extends SettingsModificationsImpl
    implements Parcelable, GoogleSettingsModifications
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private FieldModification autoAddHangouts;
    private FieldModification birthdayMode;
    private FieldModification defaultEventDurationMillis;
    private FieldModification holidayColor;
    private FieldModification isEndTimeUnspecifiedByDefault;
    private final GoogleSettings original;
    private FieldModification smartMailMode;
    private FieldModification smartMailUpdateMode;
    private FieldModification taskColor;
    private FieldModification tasksVisible;
    private FieldModification timezone;

    GoogleSettingsModificationsImpl(Parcel parcel)
    {
        super(parcel);
        autoAddHangouts = new FieldModification();
        timezone = new FieldModification();
        tasksVisible = new FieldModification();
        holidayColor = new FieldModification();
        taskColor = new FieldModification();
        birthdayMode = new FieldModification();
        smartMailMode = new FieldModification();
        smartMailUpdateMode = new FieldModification();
        defaultEventDurationMillis = new FieldModification();
        isEndTimeUnspecifiedByDefault = new FieldModification();
        original = (GoogleSettings)parcel.readParcelable(com/google/android/calendar/api/settings/GoogleSettingsImpl.getClassLoader());
        autoAddHangouts = FieldModification.readFromParcel(parcel, java/lang/Boolean.getClassLoader());
        timezone = FieldModification.readFromParcel(parcel, java/lang/String.getClassLoader());
        tasksVisible = FieldModification.readFromParcel(parcel, java/lang/Boolean.getClassLoader());
        holidayColor = FieldModification.readFromParcel(parcel, com/google/android/calendar/api/color/CalendarColor.getClassLoader());
        taskColor = FieldModification.readFromParcel(parcel, com/google/android/calendar/api/color/CalendarColor.getClassLoader());
        birthdayMode = FieldModification.readFromParcel(parcel, java/lang/Integer.getClassLoader());
        smartMailMode = FieldModification.readFromParcel(parcel, com/google/android/calendar/api/settings/GoogleSettings$SmartMailMode.getClassLoader());
        smartMailUpdateMode = FieldModification.readFromParcel(parcel, com/google/android/calendar/api/settings/GoogleSettings$SmartMailUpdateMode.getClassLoader());
        defaultEventDurationMillis = FieldModification.readFromParcel(parcel, java/lang/Long.getClassLoader());
        isEndTimeUnspecifiedByDefault = FieldModification.readFromParcel(parcel, java/lang/Boolean.getClassLoader());
    }

    GoogleSettingsModificationsImpl(GoogleSettings googlesettings)
    {
        super(googlesettings);
        autoAddHangouts = new FieldModification();
        timezone = new FieldModification();
        tasksVisible = new FieldModification();
        holidayColor = new FieldModification();
        taskColor = new FieldModification();
        birthdayMode = new FieldModification();
        smartMailMode = new FieldModification();
        smartMailUpdateMode = new FieldModification();
        defaultEventDurationMillis = new FieldModification();
        isEndTimeUnspecifiedByDefault = new FieldModification();
        if (googlesettings == null)
        {
            throw new NullPointerException();
        } else
        {
            original = (GoogleSettings)googlesettings;
            return;
        }
    }

    public final boolean areTasksVisible()
    {
        if (tasksVisible.shouldModify())
        {
            return ((Boolean)tasksVisible.getModificationValue()).booleanValue();
        }
        return original != null && original.areTasksVisible();
    }

    public final boolean autoAddHangoutsEnabled()
    {
        if (autoAddHangouts.shouldModify())
        {
            return ((Boolean)autoAddHangouts.getModificationValue()).booleanValue();
        } else
        {
            return original.autoAddHangoutsEnabled();
        }
    }

    public final int describeContents()
    {
        return 0;
    }

    public final GoogleSettings.BirthdayMode getBirthdayMode()
    {
        if (birthdayMode.shouldModify())
        {
            return (GoogleSettings.BirthdayMode)birthdayMode.getModificationValue();
        } else
        {
            return original.getBirthdayMode();
        }
    }

    public final long getDefaultEventDurationMillis()
    {
        if (defaultEventDurationMillis.shouldModify())
        {
            return ((Long)defaultEventDurationMillis.getModificationValue()).longValue();
        } else
        {
            return original.getDefaultEventDurationMillis();
        }
    }

    public final CalendarColor getHolidayColor()
    {
        if (holidayColor.shouldModify())
        {
            return (CalendarColor)holidayColor.getModificationValue();
        } else
        {
            return original.getHolidayColor();
        }
    }

    public final Settings getOriginal()
    {
        return original;
    }

    public final int getQualityOfService()
    {
        return original.getQualityOfService();
    }

    public final GoogleSettings.SmartMailMode getSmartMailMode()
    {
        if (smartMailMode.shouldModify())
        {
            return (GoogleSettings.SmartMailMode)smartMailMode.getModificationValue();
        } else
        {
            return original.getSmartMailMode();
        }
    }

    public final GoogleSettings.SmartMailUpdateMode getSmartMailUpdateMode()
    {
        if (!smartMailMode.shouldModify())
        {
            throw new IllegalStateException();
        }
        if (!smartMailUpdateMode.shouldModify())
        {
            throw new IllegalStateException();
        } else
        {
            return (GoogleSettings.SmartMailUpdateMode)smartMailUpdateMode.getModificationValue();
        }
    }

    public final CalendarColor getTaskColor()
    {
        if (taskColor.shouldModify())
        {
            return (CalendarColor)taskColor.getModificationValue();
        } else
        {
            return original.getTaskColor();
        }
    }

    public final String getTimezoneId()
    {
        String s;
        if (timezone.shouldModify())
        {
            s = (String)timezone.getModificationValue();
        } else
        if (original != null)
        {
            s = original.getTimezoneId();
        } else
        {
            s = "";
        }
        if (s == null)
        {
            throw new NullPointerException();
        }
        boolean flag;
        if (s.isEmpty())
        {
            flag = true;
        } else
        {
            flag = TimeZoneHelper.isValidTimeZoneId(s);
        }
        if (flag)
        {
            return s;
        } else
        {
            return "";
        }
    }

    public final boolean isBirthdayModeModified()
    {
        return birthdayMode.shouldModify();
    }

    public final boolean isDefaultEventDurationMillisModified()
    {
        return defaultEventDurationMillis.shouldModify();
    }

    public final boolean isEndTimeUnspecifiedByDefault()
    {
        if (isEndTimeUnspecifiedByDefault.shouldModify())
        {
            return ((Boolean)isEndTimeUnspecifiedByDefault.getModificationValue()).booleanValue();
        } else
        {
            return original.isEndTimeUnspecifiedByDefault();
        }
    }

    public final boolean isEndTimeUnspecifiedByDefaultModified()
    {
        return isEndTimeUnspecifiedByDefault.shouldModify();
    }

    public final boolean isHolidayColorModified()
    {
        return holidayColor.shouldModify();
    }

    public final boolean isModified()
    {
        return timezone.shouldModify() || tasksVisible.shouldModify() || holidayColor.shouldModify() || taskColor.shouldModify() || birthdayMode.shouldModify() || smartMailMode.shouldModify() || defaultEventDurationMillis.shouldModify() || isEndTimeUnspecifiedByDefault() || super.isModified();
    }

    public final boolean isSmartMailModeModified()
    {
        return smartMailMode.shouldModify();
    }

    public final boolean isTaskColorModified()
    {
        return taskColor.shouldModify();
    }

    public final boolean isTasksVisibleModified()
    {
        return tasksVisible.shouldModify();
    }

    public final boolean isTimezoneIdModified()
    {
        return timezone.shouldModify();
    }

    public final GoogleSettingsModifications setAreTasksVisible(boolean flag)
    {
        tasksVisible = new com.google.android.calendar.api.common.FieldModification._cls1(Boolean.valueOf(flag));
        return this;
    }

    public final GoogleSettingsModifications setBirthdayMode(GoogleSettings.BirthdayMode birthdaymode)
    {
        birthdayMode = new com.google.android.calendar.api.common.FieldModification._cls1(birthdaymode);
        return this;
    }

    public final GoogleSettingsModifications setDefaultEventDurationMillis(long l)
    {
        defaultEventDurationMillis = new com.google.android.calendar.api.common.FieldModification._cls1(Long.valueOf(l));
        return this;
    }

    public final GoogleSettingsModifications setEndTimeUnspecifiedByDefault(boolean flag)
    {
        isEndTimeUnspecifiedByDefault = new com.google.android.calendar.api.common.FieldModification._cls1(Boolean.valueOf(flag));
        return this;
    }

    public final GoogleSettingsModifications setHolidayColor(CalendarColor calendarcolor)
    {
        holidayColor = new com.google.android.calendar.api.common.FieldModification._cls1(calendarcolor);
        return this;
    }

    public final GoogleSettingsModifications setSmartMailMode(GoogleSettings.SmartMailMode smartmailmode, GoogleSettings.SmartMailUpdateMode smartmailupdatemode)
    {
        smartMailMode = new com.google.android.calendar.api.common.FieldModification._cls1(smartmailmode);
        smartMailUpdateMode = new com.google.android.calendar.api.common.FieldModification._cls1(smartmailupdatemode);
        return this;
    }

    public final GoogleSettingsModifications setTaskColor(CalendarColor calendarcolor)
    {
        taskColor = new com.google.android.calendar.api.common.FieldModification._cls1(calendarcolor);
        return this;
    }

    public final GoogleSettingsModifications setTimezoneId(String s)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        boolean flag;
        if (s.isEmpty())
        {
            flag = true;
        } else
        {
            flag = TimeZoneHelper.isValidTimeZoneId(s);
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            timezone = new com.google.android.calendar.api.common.FieldModification._cls1(s);
            return this;
        }
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(original, i);
        FieldModification fieldmodification = autoAddHangouts;
        boolean flag = fieldmodification.shouldModify();
        parcel.writeValue(Boolean.valueOf(flag));
        if (flag)
        {
            parcel.writeValue(fieldmodification.getModificationValue());
        }
        fieldmodification = timezone;
        flag = fieldmodification.shouldModify();
        parcel.writeValue(Boolean.valueOf(flag));
        if (flag)
        {
            parcel.writeValue(fieldmodification.getModificationValue());
        }
        fieldmodification = tasksVisible;
        flag = fieldmodification.shouldModify();
        parcel.writeValue(Boolean.valueOf(flag));
        if (flag)
        {
            parcel.writeValue(fieldmodification.getModificationValue());
        }
        fieldmodification = holidayColor;
        flag = fieldmodification.shouldModify();
        parcel.writeValue(Boolean.valueOf(flag));
        if (flag)
        {
            parcel.writeValue(fieldmodification.getModificationValue());
        }
        fieldmodification = taskColor;
        flag = fieldmodification.shouldModify();
        parcel.writeValue(Boolean.valueOf(flag));
        if (flag)
        {
            parcel.writeValue(fieldmodification.getModificationValue());
        }
        fieldmodification = birthdayMode;
        flag = fieldmodification.shouldModify();
        parcel.writeValue(Boolean.valueOf(flag));
        if (flag)
        {
            parcel.writeValue(fieldmodification.getModificationValue());
        }
        fieldmodification = smartMailMode;
        flag = fieldmodification.shouldModify();
        parcel.writeValue(Boolean.valueOf(flag));
        if (flag)
        {
            parcel.writeValue(fieldmodification.getModificationValue());
        }
        fieldmodification = smartMailUpdateMode;
        flag = fieldmodification.shouldModify();
        parcel.writeValue(Boolean.valueOf(flag));
        if (flag)
        {
            parcel.writeValue(fieldmodification.getModificationValue());
        }
        fieldmodification = defaultEventDurationMillis;
        flag = fieldmodification.shouldModify();
        parcel.writeValue(Boolean.valueOf(flag));
        if (flag)
        {
            parcel.writeValue(fieldmodification.getModificationValue());
        }
        fieldmodification = isEndTimeUnspecifiedByDefault;
        flag = fieldmodification.shouldModify();
        parcel.writeValue(Boolean.valueOf(flag));
        if (flag)
        {
            parcel.writeValue(fieldmodification.getModificationValue());
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new GoogleSettingsModificationsImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new GoogleSettingsModificationsImpl[i];
        }

        _cls1()
        {
        }
    }

}
