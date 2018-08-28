// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.ReflectedParcelable;

// Referenced classes of package com.google.android.gms.reminders.model:
//            ReminderEvent, Task

public class ReminderEventEntity
    implements ReflectedParcelable, ReminderEvent
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final int zzagZ;
    private final String zzajr;
    public final Task zzcjb;

    ReminderEventEntity(Parcel parcel)
    {
        zzagZ = parcel.readInt();
        zzcjb = (Task)parcel.readParcelable(com/google/android/gms/reminders/model/Task.getClassLoader());
        zzajr = parcel.readString();
    }

    public ReminderEventEntity(ReminderEvent reminderevent)
    {
        zzagZ = reminderevent.getType();
        zzcjb = (Task)reminderevent.getTask().freeze();
        zzajr = reminderevent.getAccountName();
    }

    public int describeContents()
    {
        return 0;
    }

    public final Object freeze()
    {
        if (this == null)
        {
            throw null;
        } else
        {
            return this;
        }
    }

    public final String getAccountName()
    {
        return zzajr;
    }

    public final Task getTask()
    {
        return zzcjb;
    }

    public final int getType()
    {
        return zzagZ;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(zzagZ);
        parcel.writeParcelable(zzcjb, i);
        parcel.writeString(zzajr);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new ReminderEventEntity(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new ReminderEventEntity[i];
        }

        _cls1()
        {
        }
    }

}
