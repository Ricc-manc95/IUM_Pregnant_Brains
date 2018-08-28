// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.os.Parcel;
import android.os.Parcelable;

public final class CalendarCategory
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public static final CalendarCategory FAMILY = new CalendarCategory(0);
    public static final CalendarCategory MIGRATED = new CalendarCategory(1);
    private final int category;

    private CalendarCategory(int i)
    {
        category = i;
    }

    static CalendarCategory categoryToCalendarCategory(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Unknown category");

        case 0: // '\0'
            return FAMILY;

        case 1: // '\001'
            return MIGRATED;
        }
    }

    static int checkCategory(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Unknown category");

        case 0: // '\0'
        case 1: // '\001'
            return i;
        }
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (CalendarCategory)obj;
            if (category != ((CalendarCategory) (obj)).category)
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return category;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeByte((byte)category);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return CalendarCategory.categoryToCalendarCategory(CalendarCategory.checkCategory(parcel.readByte()));
        }

        public final Object[] newArray(int i)
        {
            return new CalendarCategory[0];
        }

        _cls1()
        {
        }
    }

}
