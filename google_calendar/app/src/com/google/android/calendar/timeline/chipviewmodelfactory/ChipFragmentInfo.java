// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;

import android.os.Parcel;
import android.os.Parcelable;

public class ChipFragmentInfo
    implements Parcelable
{
    public static final class Builder
    {

        public String contentDescriptionPrefix;
        public String contentDescriptionSuffix;
        public boolean shouldShowImages;
        public boolean showMultidayAnnotations;
        public ChipViewModelFactory.SwipeConfigProvider swipeConfigProvider;
        public Integer viewType;

        public final ChipFragmentInfo build()
        {
            if (viewType == null)
            {
                throw new IllegalStateException("setViewType must be called before build!");
            } else
            {
                return new ChipFragmentInfo(showMultidayAnnotations, shouldShowImages, false, 0, viewType.intValue(), contentDescriptionPrefix, contentDescriptionSuffix, swipeConfigProvider, 0);
            }
        }

        public Builder()
        {
            contentDescriptionPrefix = "";
            contentDescriptionSuffix = "";
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final String contentDescriptionPrefix;
    public final String contentDescriptionSuffix;
    public final int extraTimeFlags;
    public final int forceCompatibilityBackgroundColorForStyledCorners;
    public final boolean forceCompatibilityModeForStyledCorners;
    public final boolean shouldShowImages;
    public final boolean showMultiDayAnnotations;
    public final ChipViewModelFactory.SwipeConfigProvider swipeConfigProvider;
    public final int viewType;

    ChipFragmentInfo(Parcel parcel)
    {
        boolean flag1 = true;
        super();
        boolean flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        showMultiDayAnnotations = flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        shouldShowImages = flag;
        if (parcel.readInt() == 1)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        forceCompatibilityModeForStyledCorners = flag;
        forceCompatibilityBackgroundColorForStyledCorners = parcel.readInt();
        viewType = parcel.readInt();
        contentDescriptionPrefix = parcel.readString();
        contentDescriptionSuffix = parcel.readString();
        swipeConfigProvider = null;
        extraTimeFlags = parcel.readInt();
    }

    ChipFragmentInfo(boolean flag, boolean flag1, boolean flag2, int i, int j, String s, String s1, 
            ChipViewModelFactory.SwipeConfigProvider swipeconfigprovider, int k)
    {
        showMultiDayAnnotations = flag;
        shouldShowImages = flag1;
        forceCompatibilityModeForStyledCorners = flag2;
        forceCompatibilityBackgroundColorForStyledCorners = i;
        viewType = j;
        contentDescriptionPrefix = s;
        contentDescriptionSuffix = s1;
        swipeConfigProvider = swipeconfigprovider;
        extraTimeFlags = k;
    }

    public static boolean shouldForceFirstDay(int i)
    {
        return i == -1;
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        boolean flag = showMultiDayAnnotations;
        boolean flag1 = shouldShowImages;
        boolean flag2 = forceCompatibilityModeForStyledCorners;
        int i = forceCompatibilityBackgroundColorForStyledCorners;
        int j = viewType;
        String s = contentDescriptionPrefix;
        String s1 = contentDescriptionSuffix;
        String s2 = String.valueOf(swipeConfigProvider);
        return (new StringBuilder(String.valueOf(s).length() + 282 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("ChipFragmentInfo{showMultiDayAnnotations=").append(flag).append(", shouldShowImages=").append(flag1).append(", forceCompatibilityModeForStyledCorners=").append(flag2).append(", forceCompatibilityBackgroundColorForStyledCorners=").append(i).append(", viewType=").append(j).append(", contentDescriptionPrefix='").append(s).append('\'').append(", contentDescriptionSuffix='").append(s1).append('\'').append(", swipeConfigProvider=").append(s2).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if (showMultiDayAnnotations)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (shouldShowImages)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (forceCompatibilityModeForStyledCorners)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        parcel.writeInt(forceCompatibilityBackgroundColorForStyledCorners);
        parcel.writeInt(viewType);
        parcel.writeString(contentDescriptionPrefix);
        parcel.writeString(contentDescriptionSuffix);
        parcel.writeInt(extraTimeFlags);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new ChipFragmentInfo(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new ChipFragmentInfo[i];
        }

        _cls1()
        {
        }
    }

}
