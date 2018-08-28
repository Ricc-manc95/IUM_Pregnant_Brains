// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.contract;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.calendar.api.habit.HabitContract;

final class FrequencyChoice
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public int interval;
    public int numInstances;

    FrequencyChoice(int i, int j)
    {
        interval = i;
        numInstances = j;
    }

    protected FrequencyChoice(Parcel parcel)
    {
        interval = parcel.readInt();
        numInstances = parcel.readInt();
    }

    FrequencyChoice(HabitContract habitcontract)
    {
        this(habitcontract.getInterval(), habitcontract.getNumInstancesPerInterval());
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
            obj = (FrequencyChoice)obj;
            if (interval != ((FrequencyChoice) (obj)).interval)
            {
                return false;
            }
            if (numInstances != ((FrequencyChoice) (obj)).numInstances)
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return interval * 31 + numInstances;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(interval);
        parcel.writeInt(numInstances);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new FrequencyChoice(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new FrequencyChoice[i];
        }

        _cls1()
        {
        }
    }

}
