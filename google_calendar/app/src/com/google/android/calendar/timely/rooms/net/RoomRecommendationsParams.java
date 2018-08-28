// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import android.os.Parcelable;
import com.google.common.collect.ImmutableList;

public abstract class RoomRecommendationsParams
    implements Parcelable
{

    public RoomRecommendationsParams()
    {
    }

    public abstract int getMaxSuggestions();

    public abstract ImmutableList getRoomCriteria();

    public abstract boolean preferLocationBasedSuggestions();

    public abstract boolean showUnavailable();
}
