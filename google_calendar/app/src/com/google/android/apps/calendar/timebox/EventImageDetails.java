// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.smartmail.SmartMailAddress;

public abstract class EventImageDetails
{

    public EventImageDetails()
    {
    }

    public abstract EventLocation getEventLocation();

    public abstract String getGooglePlusImageUrl();

    public abstract SmartMailAddress getSmartMailAddress();

    public abstract String getSmartMailImageUrl();

    public abstract SmartMailType getType();
}
