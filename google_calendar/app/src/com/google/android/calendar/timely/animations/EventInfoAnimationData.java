// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.animations;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import com.google.android.calendar.Utils;
import com.google.android.calendar.timeline.chip.ChipViewModel;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipViewModelFactory;
import com.google.android.calendar.timely.TimelineItem;

public final class EventInfoAnimationData
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final ChipFragmentInfo chipFragmentInfo;
    private ChipViewModel chipViewModel;
    private final int currentJulianDay;
    public final int orientation;
    public final Rect position;
    private boolean regenerateChipViewModel;

    EventInfoAnimationData(Rect rect, int i, ChipFragmentInfo chipfragmentinfo, int j, ChipViewModel chipviewmodel, boolean flag)
    {
        position = rect;
        orientation = i;
        chipFragmentInfo = chipfragmentinfo;
        currentJulianDay = j;
        chipViewModel = chipviewmodel;
        regenerateChipViewModel = flag;
    }

    public EventInfoAnimationData(View view, ChipViewModel chipviewmodel, ChipFragmentInfo chipfragmentinfo, int i)
    {
        this(Utils.getLocationInWindow(view), view.getResources().getConfiguration().orientation, chipfragmentinfo, i, chipviewmodel, false);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final ChipViewModel getChipViewModel(Context context, TimelineItem timelineitem)
    {
        if (regenerateChipViewModel)
        {
            chipViewModel = (new ChipViewModelFactory(context)).buildViewModel(timelineitem, chipFragmentInfo, currentJulianDay);
            regenerateChipViewModel = false;
        }
        return chipViewModel;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(position, i);
        parcel.writeInt(orientation);
        parcel.writeParcelable(chipFragmentInfo, i);
        parcel.writeInt(currentJulianDay);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new EventInfoAnimationData((Rect)parcel.readParcelable(null), parcel.readInt(), (ChipFragmentInfo)parcel.readParcelable(com/google/android/calendar/timeline/chipviewmodelfactory/ChipFragmentInfo.getClassLoader()), parcel.readInt(), null, true);
        }

        public final Object[] newArray(int i)
        {
            return new EventInfoAnimationData[i];
        }

        _cls1()
        {
        }
    }

}
