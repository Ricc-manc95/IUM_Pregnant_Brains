// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.os.Parcelable;
import com.google.android.calendar.api.color.CalendarColor;

// Referenced classes of package com.google.android.calendar.api.settings:
//            Settings

public interface GoogleSettings
    extends Parcelable, Settings
{
    public static final class BirthdayMode extends Enum
    {

        private static final BirthdayMode $VALUES[];
        public static final BirthdayMode CONTACTS;
        public static final BirthdayMode GPLUS_AND_CONTACTS;
        public static final BirthdayMode NONE;

        public static BirthdayMode[] values()
        {
            return (BirthdayMode[])$VALUES.clone();
        }

        static 
        {
            GPLUS_AND_CONTACTS = new BirthdayMode("GPLUS_AND_CONTACTS", 0);
            CONTACTS = new BirthdayMode("CONTACTS", 1);
            NONE = new BirthdayMode("NONE", 2);
            $VALUES = (new BirthdayMode[] {
                GPLUS_AND_CONTACTS, CONTACTS, NONE
            });
        }

        private BirthdayMode(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class SmartMailMode extends Enum
    {

        private static final SmartMailMode $VALUES[];
        public static final SmartMailMode CREATE;
        public static final SmartMailMode CREATE_PRIVATE;
        public static final SmartMailMode CREATE_SECRET;
        public static final SmartMailMode IGNORE;

        public static SmartMailMode valueOf(String s)
        {
            return (SmartMailMode)Enum.valueOf(com/google/android/calendar/api/settings/GoogleSettings$SmartMailMode, s);
        }

        public static SmartMailMode[] values()
        {
            return (SmartMailMode[])$VALUES.clone();
        }

        static 
        {
            CREATE = new SmartMailMode("CREATE", 0);
            CREATE_PRIVATE = new SmartMailMode("CREATE_PRIVATE", 1);
            CREATE_SECRET = new SmartMailMode("CREATE_SECRET", 2);
            IGNORE = new SmartMailMode("IGNORE", 3);
            $VALUES = (new SmartMailMode[] {
                CREATE, CREATE_PRIVATE, CREATE_SECRET, IGNORE
            });
        }

        private SmartMailMode(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class SmartMailUpdateMode extends Enum
    {

        private static final SmartMailUpdateMode $VALUES[];
        public static final SmartMailUpdateMode ALL;
        public static final SmartMailUpdateMode NEW;
        public static final SmartMailUpdateMode UPCOMING;

        public static SmartMailUpdateMode[] values()
        {
            return (SmartMailUpdateMode[])$VALUES.clone();
        }

        static 
        {
            ALL = new SmartMailUpdateMode("ALL", 0);
            NEW = new SmartMailUpdateMode("NEW", 1);
            UPCOMING = new SmartMailUpdateMode("UPCOMING", 2);
            $VALUES = (new SmartMailUpdateMode[] {
                ALL, NEW, UPCOMING
            });
        }

        private SmartMailUpdateMode(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract boolean areTasksVisible();

    public abstract boolean autoAddHangoutsEnabled();

    public abstract BirthdayMode getBirthdayMode();

    public abstract long getDefaultEventDurationMillis();

    public abstract CalendarColor getHolidayColor();

    public abstract int getQualityOfService();

    public abstract SmartMailMode getSmartMailMode();

    public abstract CalendarColor getTaskColor();

    public abstract String getTimezoneId();

    public abstract boolean isEndTimeUnspecifiedByDefault();
}
