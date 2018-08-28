// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.tracking;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitContract;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.newapi.segment.tracking:
//            TrackingIntervalData

public class GrooveTrackingData
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public int interval;
    public ArrayList intervalDataList;
    public int numInstancesPerInterval;
    public boolean shouldAnimateUpdate;

    private GrooveTrackingData(int i, int j)
    {
        interval = i;
        numInstancesPerInterval = j;
        intervalDataList = new ArrayList();
    }

    protected GrooveTrackingData(Parcel parcel)
    {
        boolean flag = true;
        super();
        interval = parcel.readInt();
        numInstancesPerInterval = parcel.readInt();
        if (parcel.readInt() != 1)
        {
            flag = false;
        }
        shouldAnimateUpdate = flag;
        intervalDataList = parcel.createTypedArrayList(TrackingIntervalData.CREATOR);
    }

    public GrooveTrackingData(Habit habit)
    {
        this(habit.getContract().getInterval(), habit.getContract().getNumInstancesPerInterval());
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(interval);
        parcel.writeInt(numInstancesPerInterval);
        if (shouldAnimateUpdate)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        parcel.writeTypedList(intervalDataList);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new GrooveTrackingData(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new GrooveTrackingData[i];
        }

        _cls1()
        {
        }
    }

}
