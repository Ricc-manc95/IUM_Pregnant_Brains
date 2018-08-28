// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference.thirdparty.utils;

import com.google.android.calendar.api.event.conference.ConferenceData;
import com.google.android.calendar.api.event.conference.ConferenceSolution;
import com.google.common.collect.ImmutableList;

public final class ThirdPartyConferenceUtils
{

    public static final ImmutableList NO_DEFAULT_CONFERENCES_FOR_NEW_ALL_DAY_EVENTS_FOR_TYPES = ImmutableList.of("hangoutsMeet");

    public static boolean isThirdPartyConferenceData(ConferenceData conferencedata)
    {
        return conferencedata.getConferenceSolution() != null && "addOn".equals(conferencedata.getConferenceSolution().getKey().getType());
    }

    public static boolean showThirdPartyConferenceSegment(boolean flag, ConferenceData conferencedata)
    {
        if (flag)
        {
            boolean flag1;
            if (conferencedata.getConferenceSolution() != null && "addOn".equals(conferencedata.getConferenceSolution().getKey().getType()))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                return true;
            }
        }
        return false;
    }

}
