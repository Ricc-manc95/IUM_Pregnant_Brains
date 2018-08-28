// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcelable;
import com.google.common.collect.ImmutableList;

public abstract class Room
    implements Parcelable
{
    public static abstract class Builder
    {

        public abstract Room build();

        public abstract Builder setAvailability(int i);

        public abstract Builder setBuildingName(String s);

        public abstract Builder setCapacity(Integer integer);

        public abstract Builder setCategory(int i);

        public abstract Builder setDescription(String s);

        public abstract Builder setEmail(String s);

        public abstract Builder setFeatures(ImmutableList immutablelist);

        public abstract Builder setFloorName(String s);

        public abstract Builder setFloorSectionName(String s);

        public abstract Builder setHierarchyNodeId(String s);

        public abstract Builder setName(String s);

        public abstract Builder setShortName(String s);

        public Builder()
        {
        }
    }


    public Room()
    {
    }

    public abstract int getAvailability();

    public abstract String getBuildingName();

    public abstract Integer getCapacity();

    public abstract int getCategory();

    public abstract String getDescription();

    public abstract String getEmail();

    public abstract ImmutableList getFeatures();

    public abstract String getFloorName();

    public abstract String getFloorSectionName();

    public abstract String getHierarchyNodeId();

    public abstract String getName();

    public final Iterable getProminentFeatures()
    {
        ImmutableList immutablelist = getFeatures();
        class .Lambda._cls0
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls0();

            public final boolean apply(Object obj)
            {
                return ((RoomFeature)obj).getDisplayProminence() == 1;
            }


            private .Lambda._cls0()
            {
            }
        }

        Predicate predicate = .Lambda._cls0..instance;
        if (immutablelist == null)
        {
            throw new NullPointerException();
        }
        if (predicate == null)
        {
            throw new NullPointerException();
        } else
        {
            return new com.google.common.collect.Iterables._cls4(immutablelist, predicate);
        }
    }

    public abstract String getShortName();
}
