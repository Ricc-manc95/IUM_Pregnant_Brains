// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitInstance, HabitDescriptor, HabitUtil

public final class HabitInstanceImpl
    implements HabitInstance
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final HabitDescriptor parentDescriptor;
    private final int parentType;
    private final int status;
    private final boolean statusInferred;

    HabitInstanceImpl(Parcel parcel)
    {
        this((HabitDescriptor)parcel.readParcelable(com/google/android/calendar/api/habit/HabitDescriptor.getClassLoader()), HabitUtil.checkType(parcel.readInt()), HabitUtil.intToHabitInstanceStatus(parcel.readInt()), ((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue());
    }

    public HabitInstanceImpl(HabitDescriptor habitdescriptor, int i, int j, boolean flag)
    {
        if (habitdescriptor == null)
        {
            throw new NullPointerException();
        } else
        {
            parentDescriptor = (HabitDescriptor)habitdescriptor;
            parentType = i;
            status = j;
            statusInferred = flag;
            return;
        }
    }

    public final int describeContents()
    {
        return 0;
    }

    public final HabitDescriptor getHabitParentDescriptor()
    {
        return parentDescriptor;
    }

    public final int getParentType()
    {
        return parentType;
    }

    public final int getStatus()
    {
        return status;
    }

    public final boolean getStatusInferred()
    {
        return statusInferred;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(parentDescriptor, i);
        parcel.writeInt(parentType);
        parcel.writeInt(status);
        parcel.writeValue(Boolean.valueOf(statusInferred));
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new HabitInstanceImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new HabitInstanceImpl[i];
        }

        _cls1()
        {
        }
    }

}
