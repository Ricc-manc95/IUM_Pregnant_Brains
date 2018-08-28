// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import android.os.Parcelable;

public abstract class RoomListingParams
    implements Parcelable
{
    public static abstract class Builder
    {

        public abstract RoomListingParams build();

        public abstract Builder setPageToken(String s);

        public abstract Builder setPreferHierarchy(boolean flag);

        public abstract Builder setShowUnavailable(boolean flag);

        public Builder()
        {
        }
    }


    public RoomListingParams()
    {
    }

    public abstract Integer getMaxPageSize();

    public abstract String getPageToken();

    public abstract boolean isPreferHierarchy();

    public abstract boolean isShowUnavailable();

    public abstract Builder toBuilder();
}
