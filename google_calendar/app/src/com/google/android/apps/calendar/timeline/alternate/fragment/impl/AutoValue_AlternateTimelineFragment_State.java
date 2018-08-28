// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import android.os.Parcel;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.fragment.impl:
//            $AutoValue_AlternateTimelineFragment_State

final class AutoValue_AlternateTimelineFragment_State extends $AutoValue_AlternateTimelineFragment_State
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_AlternateTimelineFragment_State(com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType viewtype, Integer integer)
    {
        super(viewtype, integer);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(viewType().name());
        parcel.writeInt(requestedJulianDay().intValue());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_AlternateTimelineFragment_State(com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType.valueOf(parcel.readString()), Integer.valueOf(parcel.readInt()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_AlternateTimelineFragment_State[i];
        }

        _cls1()
        {
        }
    }

}
