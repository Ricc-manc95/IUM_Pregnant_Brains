// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcelable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public abstract class ExpandedMeetingLocation
    implements Parcelable
{

    public ExpandedMeetingLocation()
    {
    }

    public abstract ImmutableSet getAddedRoomEmails();

    public abstract ImmutableList getAttendees();

    public abstract String getBuildingId();

    public abstract String getBuildingName();

    public abstract ImmutableList getRoomSuggestions();
}
