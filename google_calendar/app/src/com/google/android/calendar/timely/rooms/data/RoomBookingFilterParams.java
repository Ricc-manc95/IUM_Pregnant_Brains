// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcelable;

public abstract class RoomBookingFilterParams
    implements Parcelable
{

    public RoomBookingFilterParams()
    {
    }

    public abstract Integer getRecurrenceOption();

    public abstract boolean showOnlyAvailable();
}
