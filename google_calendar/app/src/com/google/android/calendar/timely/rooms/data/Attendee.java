// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcelable;

public abstract class Attendee
    implements Parcelable
{

    public Attendee()
    {
    }

    public abstract String getDisplayName();

    public abstract String getEmail();

    public abstract com.google.android.calendar.api.event.attendee.Response.ResponseStatus getResponseStatus();

    public abstract boolean isOrganizer();
}
