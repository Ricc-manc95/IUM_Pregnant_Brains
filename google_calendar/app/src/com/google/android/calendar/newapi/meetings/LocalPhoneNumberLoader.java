// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.meetings;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.meetings.MeetingsPstnFinder;
import com.google.android.calendar.api.event.conference.Conference;
import com.google.android.calendar.event.conference.PhoneNumberDetails;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;

public final class LocalPhoneNumberLoader extends AsyncTaskLoader
{

    private final Conference defaultPhoneConference;
    private final MeetingsPstnFinder meetingsPstnFinder;
    private final Conference universalConference;

    public LocalPhoneNumberLoader(Conference conference, Conference conference1, MeetingsPstnFinder meetingspstnfinder)
    {
        if (conference == null)
        {
            throw new NullPointerException();
        }
        defaultPhoneConference = (Conference)conference;
        if (conference1 == null)
        {
            throw new NullPointerException();
        } else
        {
            universalConference = (Conference)conference1;
            meetingsPstnFinder = meetingspstnfinder;
            return;
        }
    }

    private final transient PhoneNumberDetails runInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UPBMCLN78BR3DTN6CPBICLN66P9FA1K6URJ59PQMQOJ5E926AT31D5M76EO_0()
    {
        Object obj = meetingsPstnFinder.findLocalPhoneNumber(defaultPhoneConference.getRegionCode(), universalConference.getPassCode());
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        if (((ListenableFuture) (obj)).get() == null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        obj = (PhoneNumberDetails)((ListenableFuture) (obj)).get();
        return ((PhoneNumberDetails) (obj));
        return null;
        Object obj1;
        obj1;
_L2:
        LogUtils.e("LocalPhoneNumberLoader", ((Throwable) (obj1)), "MeetingsPstnFinder failed", new Object[0]);
        return null;
        obj1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    protected final volatile Object runInBackground(Object aobj[])
    {
        return runInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UPBMCLN78BR3DTN6CPBICLN66P9FA1K6URJ59PQMQOJ5E926AT31D5M76EO_0();
    }
}
