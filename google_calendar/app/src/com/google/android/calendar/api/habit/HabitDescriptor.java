// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.utils.account.AccountUtil;

public class HabitDescriptor
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final CalendarDescriptor calendar;
    public final String habitId;

    HabitDescriptor(Parcel parcel)
    {
        this((CalendarDescriptor)parcel.readParcelable(com/google/android/calendar/api/calendarlist/CalendarDescriptor.getClassLoader()), parcel.readString());
    }

    public HabitDescriptor(CalendarDescriptor calendardescriptor, String s)
    {
        if (calendardescriptor == null)
        {
            throw new NullPointerException();
        }
        calendar = (CalendarDescriptor)calendardescriptor;
        if (!AccountUtil.isGoogleAccount(calendardescriptor.account))
        {
            throw new IllegalArgumentException();
        }
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            habitId = (String)s;
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof HabitDescriptor)
        {
            obj = (HabitDescriptor)obj;
            CalendarDescriptor calendardescriptor = calendar;
            CalendarDescriptor calendardescriptor1 = ((HabitDescriptor) (obj)).calendar;
            boolean flag;
            if (calendardescriptor == calendardescriptor1 || calendardescriptor != null && calendardescriptor.equals(calendardescriptor1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                String s = habitId;
                obj = ((HabitDescriptor) (obj)).habitId;
                if (s == obj || s != null && s.equals(obj))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode()
    {
        return (calendar.hashCode() + 527) * 31 + habitId.hashCode();
    }

    public String toString()
    {
        String s = String.valueOf(calendar);
        String s1 = habitId;
        return (new StringBuilder(String.valueOf(s).length() + 18 + String.valueOf(s1).length())).append("HabitDescriptor{").append(s).append(",").append(s1).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(calendar, i);
        parcel.writeString(habitId);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new HabitDescriptor(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new HabitDescriptor[i];
        }

        _cls1()
        {
        }
    }

}
