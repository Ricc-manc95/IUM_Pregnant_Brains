// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.availability;

import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.userstatus.UserStatus;
import com.google.android.calendar.newapi.utils.SmartMailUtils;

public final class AvailabilityUtils
{

    public static int getDefaultAvailability(Event event)
    {
        boolean flag;
        if (event != null && event.getParticipantStatus() != null && event.getParticipantStatus().getOutOfOffice() != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return flag || !SmartMailUtils.isSupportedSmartMailType(event.getSmartMailInfo()) && !event.isAllDayEvent() ? 0 : 1;
    }
}
