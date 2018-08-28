// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import com.google.android.calendar.api.event.location.EventLocation;

public abstract class EventResult
{

    public EventResult()
    {
    }

    public abstract EventLocation getLocation();

    public abstract Timespan getTimespan();

    public abstract String getTitle();

    public abstract boolean wasConnectorUsed();

    public abstract boolean wasSuggestionReceived();
}
