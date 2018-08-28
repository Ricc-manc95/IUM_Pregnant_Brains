// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.os.Parcel;
import android.os.Parcelable;

public class CalendarAccessLevel
    implements Parcelable, Comparable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public static final CalendarAccessLevel FREEBUSY = new CalendarAccessLevel(100);
    public static final CalendarAccessLevel NONE = new CalendarAccessLevel(0);
    public static final CalendarAccessLevel OWNER = new CalendarAccessLevel(700);
    public static final CalendarAccessLevel READER = new CalendarAccessLevel(200);
    public static final CalendarAccessLevel WRITER = new CalendarAccessLevel(600);
    public final int level;

    private CalendarAccessLevel(int i)
    {
        level = i;
    }

    static CalendarAccessLevel accessLevelIntToCalendarAccessLevel(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException("Unknown access level");

        case 0: // '\0'
            return NONE;

        case 100: // 'd'
            return FREEBUSY;

        case 200: 
            return READER;

        case 600: 
            return WRITER;

        case 700: 
            return OWNER;
        }
    }

    static int checkLevel(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException("Unknown access level");

        case 0: // '\0'
        case 100: // 'd'
        case 200: 
        case 600: 
        case 700: 
            return i;
        }
    }

    public int compareTo(Object obj)
    {
        obj = (CalendarAccessLevel)obj;
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            return level - ((CalendarAccessLevel) (obj)).level;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (CalendarAccessLevel)obj;
            if (level != ((CalendarAccessLevel) (obj)).level)
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        return level;
    }

    public final boolean isGreaterOrEqual(CalendarAccessLevel calendaraccesslevel)
    {
        if (calendaraccesslevel == null)
        {
            throw new NullPointerException();
        }
        return level - calendaraccesslevel.level >= 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(level);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return CalendarAccessLevel.accessLevelIntToCalendarAccessLevel(CalendarAccessLevel.checkLevel(parcel.readInt()));
        }

        public final Object[] newArray(int i)
        {
            return new CalendarAccessLevel[i];
        }

        _cls1()
        {
        }
    }

}
