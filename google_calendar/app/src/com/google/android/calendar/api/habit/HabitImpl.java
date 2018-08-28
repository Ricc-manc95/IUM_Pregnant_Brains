// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.os.Parcel;
import com.google.android.calendar.api.color.EventColor;

// Referenced classes of package com.google.android.calendar.api.habit:
//            Habit, HabitDescriptor, HabitContract, HabitReminders

public final class HabitImpl
    implements Habit
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final EventColor colorOverride;
    private final HabitContract contract;
    private final HabitDescriptor descriptor;
    private final String fingerprint;
    private final int fitIntegrationStatus;
    private final HabitReminders reminders;
    private final String summary;
    private final int type;
    private final int visibility;

    protected HabitImpl(HabitDescriptor habitdescriptor, String s, String s1, EventColor eventcolor, HabitContract habitcontract, HabitReminders habitreminders, int i, 
            int j, int k)
    {
        if (habitdescriptor == null)
        {
            throw new NullPointerException();
        }
        descriptor = (HabitDescriptor)habitdescriptor;
        fingerprint = s;
        summary = s1;
        colorOverride = eventcolor;
        if (habitcontract == null)
        {
            throw new NullPointerException();
        } else
        {
            contract = (HabitContract)habitcontract;
            reminders = habitreminders;
            type = i;
            visibility = j;
            fitIntegrationStatus = k;
            return;
        }
    }

    public final int describeContents()
    {
        return 0;
    }

    public final EventColor getColorOverride()
    {
        return colorOverride;
    }

    public final HabitContract getContract()
    {
        return contract;
    }

    public final HabitDescriptor getDescriptor()
    {
        return descriptor;
    }

    public final String getFingerprint()
    {
        return fingerprint;
    }

    public final int getFitIntegrationStatus()
    {
        return fitIntegrationStatus;
    }

    public final HabitReminders getReminders()
    {
        return reminders;
    }

    public final String getSummary()
    {
        return summary;
    }

    public final int getType()
    {
        return type;
    }

    public final int getVisibility()
    {
        return visibility;
    }

    public final boolean isFitIntegrationEnabled()
    {
        return getFitIntegrationStatus() == 20;
    }

    public final String toString()
    {
        String s = String.valueOf(descriptor);
        String s1 = fingerprint;
        String s2 = summary;
        String s3 = String.valueOf(colorOverride);
        String s4 = String.valueOf(contract);
        String s5 = String.valueOf(reminders);
        int i = type;
        int j = visibility;
        int k = fitIntegrationStatus;
        return (new StringBuilder(String.valueOf(s).length() + 174 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length())).append("HabitImpl{mDescriptor=").append(s).append(", mFingerprint='").append(s1).append('\'').append(", mSummary='").append(s2).append('\'').append(", mColorOverride=").append(s3).append(", mContract=").append(s4).append(", mReminders=").append(s5).append(", mType=").append(i).append(", mVisibility=").append(j).append(", mFitIntegrationStatus=").append(k).append('}').toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(descriptor, i);
        parcel.writeString(fingerprint);
        parcel.writeString(summary);
        parcel.writeParcelable(colorOverride, i);
        parcel.writeParcelable(contract, i);
        parcel.writeParcelable(reminders, i);
        parcel.writeInt(type);
        parcel.writeInt(visibility);
        parcel.writeInt(fitIntegrationStatus);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new HabitImpl((HabitDescriptor)parcel.readParcelable(com/google/android/calendar/api/habit/HabitDescriptor.getClassLoader()), parcel.readString(), parcel.readString(), (EventColor)parcel.readParcelable(com/google/android/calendar/api/color/EventColor.getClassLoader()), (HabitContract)parcel.readParcelable(com/google/android/calendar/api/habit/HabitContract.getClassLoader()), (HabitReminders)parcel.readParcelable(com/google/android/calendar/api/habit/HabitReminders.getClassLoader()), HabitUtil.checkType(parcel.readInt()), HabitUtil.checkVisibility(parcel.readInt()), HabitUtil.checkFitIntegrationStatus(parcel.readInt()));
        }

        public final Object[] newArray(int i)
        {
            return new HabitImpl[i];
        }

        _cls1()
        {
        }
    }

}
