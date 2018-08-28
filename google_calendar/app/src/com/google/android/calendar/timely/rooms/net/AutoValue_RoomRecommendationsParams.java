// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import android.os.Parcel;
import com.google.android.calendar.utils.datatypes.ImmutableListAdapter;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.net:
//            $AutoValue_RoomRecommendationsParams, RoomRecommendationsParams

public final class AutoValue_RoomRecommendationsParams extends $AutoValue_RoomRecommendationsParams
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public static final ImmutableListAdapter IMMUTABLE_LIST_ADAPTER = new ImmutableListAdapter();

    public AutoValue_RoomRecommendationsParams(int i, boolean flag, boolean flag1, ImmutableList immutablelist)
    {
        super(i, flag, flag1, immutablelist);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(getMaxSuggestions());
        if (showUnavailable())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (preferLocationBasedSuggestions())
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        parcel.writeList(getRoomCriteria());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            boolean flag1 = true;
            int i = parcel.readInt();
            boolean flag;
            if (parcel.readInt() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (parcel.readInt() != 1)
            {
                flag1 = false;
            }
            return new AutoValue_RoomRecommendationsParams(i, flag, flag1, ImmutableList.copyOf(parcel.readArrayList(com/google/android/calendar/utils/datatypes/ImmutableListAdapter.getClassLoader())));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_RoomRecommendationsParams[i];
        }

        _cls1()
        {
        }
    }

}
