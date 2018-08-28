// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.os.Parcel;
import android.os.Parcelable;

public class HabitReminders
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final boolean enableFollowup;
    public final boolean enableRecommit;
    public final Integer overrideMinutes;
    public final boolean useDefaultReminders;

    HabitReminders()
    {
        this(true, null, true, true);
    }

    HabitReminders(Parcel parcel)
    {
        boolean aflag[] = new boolean[4];
        parcel.readBooleanArray(aflag);
        useDefaultReminders = aflag[0];
        enableRecommit = aflag[1];
        enableFollowup = aflag[2];
        if (aflag[3])
        {
            overrideMinutes = Integer.valueOf(parcel.readInt());
            return;
        } else
        {
            overrideMinutes = null;
            return;
        }
    }

    public HabitReminders(boolean flag, Integer integer, boolean flag1, boolean flag2)
    {
        useDefaultReminders = flag;
        overrideMinutes = integer;
        enableRecommit = flag1;
        enableFollowup = flag2;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof HabitReminders)
        {
            if (useDefaultReminders == ((HabitReminders) (obj = (HabitReminders)obj)).useDefaultReminders)
            {
                Integer integer = overrideMinutes;
                Integer integer1 = ((HabitReminders) (obj)).overrideMinutes;
                boolean flag;
                if (integer == integer1 || integer != null && integer.equals(integer1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && enableRecommit == ((HabitReminders) (obj)).enableRecommit && enableFollowup == ((HabitReminders) (obj)).enableFollowup)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode()
    {
        int l = 1;
        int i;
        int j;
        int k;
        if (useDefaultReminders)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (overrideMinutes == null)
        {
            j = 0;
        } else
        {
            j = overrideMinutes.hashCode();
        }
        if (enableRecommit)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (!enableFollowup)
        {
            l = 0;
        }
        return (k + (j + (i + 527) * 31) * 31) * 31 + l;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        boolean flag1 = useDefaultReminders;
        boolean flag2 = enableRecommit;
        boolean flag3 = enableFollowup;
        if (overrideMinutes == null)
        {
            flag = false;
        }
        parcel.writeBooleanArray(new boolean[] {
            flag1, flag2, flag3, flag
        });
        if (overrideMinutes != null)
        {
            parcel.writeInt(overrideMinutes.intValue());
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new HabitReminders(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new HabitReminders[i];
        }

        _cls1()
        {
        }
    }

}
