// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.common.ParcelHelper;
import com.google.android.calendar.api.common.TimeZoneHelper;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.common.base.Platform;

// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsImpl, GoogleSettings

public class GoogleSettingsImpl extends SettingsImpl
    implements GoogleSettings
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public static final GoogleSettings.BirthdayMode DEFAULT_BIRTHDAY_MODE = null;
    public static final Long DEFAULT_EVENT_DURATION = Long.valueOf(0x36ee80L);
    public static final GoogleSettings.SmartMailMode DEFAULT_SMART_MAIL_MODE = null;
    private static final String DEFAULT_TIMEZONE = null;
    private final boolean autoAddHangouts;
    private final GoogleSettings.BirthdayMode birthdayMode;
    private final long defaultDurationMillis;
    private final boolean endTimeUnspecified;
    private final CalendarColor holidaysColor;
    private final int qualityOfService;
    private final GoogleSettings.SmartMailMode smartMailMode;
    private final CalendarColor tasksColor;
    private final boolean tasksVisible;
    private final String timezoneId;

    GoogleSettingsImpl(Account account, Notification anotification[], Notification anotification1[], boolean flag, int i, String s, long l, boolean flag1, boolean flag2, CalendarColor calendarcolor, CalendarColor calendarcolor1, GoogleSettings.SmartMailMode smartmailmode, GoogleSettings.BirthdayMode birthdaymode)
    {
        super(account, anotification, anotification1);
        autoAddHangouts = flag;
        qualityOfService = i;
        timezoneId = Platform.nullToEmpty(s);
        defaultDurationMillis = l;
        endTimeUnspecified = flag1;
        tasksVisible = flag2;
        tasksColor = calendarcolor;
        holidaysColor = calendarcolor1;
        smartMailMode = smartmailmode;
        birthdayMode = birthdaymode;
    }

    GoogleSettingsImpl(Parcel parcel)
    {
        boolean flag1 = true;
        super(parcel);
        int i;
        boolean flag;
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        autoAddHangouts = flag;
        i = parcel.readInt();
        switch (i)
        {
        default:
            throw new IllegalStateException((new StringBuilder(39)).append("Invalid quality of service: ").append(i).toString());

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
            qualityOfService = i;
            break;
        }
        timezoneId = parcel.readString();
        defaultDurationMillis = parcel.readLong();
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        endTimeUnspecified = flag;
        if (parcel.readByte() != 0)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        tasksVisible = flag;
        tasksColor = (CalendarColor)parcel.readParcelable(com/google/android/calendar/api/color/CalendarColor.getClassLoader());
        holidaysColor = (CalendarColor)parcel.readParcelable(com/google/android/calendar/api/color/CalendarColor.getClassLoader());
        smartMailMode = (GoogleSettings.SmartMailMode)ParcelHelper.readFromParcel(parcel, com/google/android/calendar/api/settings/GoogleSettings$SmartMailMode);
        birthdayMode = (GoogleSettings.BirthdayMode)ParcelHelper.readFromParcel(parcel, com/google/android/calendar/api/settings/GoogleSettings$BirthdayMode);
    }

    static GoogleSettings createDefault(Account account, Notification anotification[], Notification anotification1[])
    {
        return new GoogleSettingsImpl(account, anotification, anotification1, false, 0, null, DEFAULT_EVENT_DURATION.longValue(), false, true, CalendarApi.getColorFactory().defaultTaskColor(), CalendarApi.getColorFactory().defaultHolidayColor(), null, null);
    }

    public final boolean areTasksVisible()
    {
        return tasksVisible;
    }

    public final boolean autoAddHangoutsEnabled()
    {
        return autoAddHangouts;
    }

    public int describeContents()
    {
        return 0;
    }

    public final GoogleSettings.BirthdayMode getBirthdayMode()
    {
        return birthdayMode;
    }

    public final long getDefaultEventDurationMillis()
    {
        return defaultDurationMillis;
    }

    public final CalendarColor getHolidayColor()
    {
        return holidaysColor;
    }

    public final int getQualityOfService()
    {
        return qualityOfService;
    }

    public final GoogleSettings.SmartMailMode getSmartMailMode()
    {
        return smartMailMode;
    }

    public final CalendarColor getTaskColor()
    {
        return tasksColor;
    }

    public final String getTimezoneId()
    {
        String s = timezoneId;
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

    public final boolean isEndTimeUnspecifiedByDefault()
    {
        return endTimeUnspecified;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        byte byte0 = -1;
        boolean flag = true;
        super.writeToParcel(parcel, i);
        Object obj;
        int j;
        if (autoAddHangouts)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeByte((byte)j);
        parcel.writeInt(qualityOfService);
        parcel.writeString(timezoneId);
        parcel.writeLong(defaultDurationMillis);
        if (endTimeUnspecified)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeByte((byte)j);
        if (tasksVisible)
        {
            j = ((flag) ? 1 : 0);
        } else
        {
            j = 0;
        }
        parcel.writeByte((byte)j);
        parcel.writeParcelable(tasksColor, i);
        parcel.writeParcelable(holidaysColor, i);
        obj = smartMailMode;
        if (obj == null)
        {
            i = -1;
        } else
        {
            i = ((Enum) (obj)).ordinal();
        }
        parcel.writeInt(i);
        obj = birthdayMode;
        if (obj == null)
        {
            i = byte0;
        } else
        {
            i = ((Enum) (obj)).ordinal();
        }
        parcel.writeInt(i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new GoogleSettingsImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new GoogleSettingsImpl[i];
        }

        _cls1()
        {
        }
    }

}
