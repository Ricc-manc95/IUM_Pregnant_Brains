// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.logging;

import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.userstatus.UserStatus;

public final class AnalyticsViewType
{

    public static String fromEvent(Event event)
    {
        boolean flag;
        if (event != null && event.getParticipantStatus() != null && event.getParticipantStatus().getOutOfOffice() != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return "ooo";
        }
        if (event != null && (!AttendeeUtils.isOrganizerCopy(event) || AttendeeUtils.hasGuests(event)))
        {
            return "invitation";
        } else
        {
            return "event";
        }
    }
}
