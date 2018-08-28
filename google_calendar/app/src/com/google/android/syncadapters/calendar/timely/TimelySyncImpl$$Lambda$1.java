// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely;

import android.accounts.Account;
import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.api.services.calendar.model.Conference;
import com.google.api.services.calendar.model.ConferenceData;
import com.google.common.base.Supplier;
import java.util.Arrays;

// Referenced classes of package com.google.android.syncadapters.calendar.timely:
//            TimelySyncImpl

final class arg._cls3
    implements Supplier
{

    private final TimelySyncImpl arg$1;
    private final String arg$2;
    private final Account arg$3;

    public final Object get()
    {
        Object obj;
        obj = arg$1;
        String s = arg$2;
        Account account = arg$3;
        obj = ((TimelySyncImpl) (obj)).timelyStore.getConferenceTypeForCalendar(s, account.name, account.type);
        if (obj == null) goto _L2; else goto _L1
_L1:
        byte byte0 = -1;
        ((String) (obj)).hashCode();
        JVM INSTR lookupswitch 5: default 92
    //                   -1876495076: 185
    //                   -972730403: 155
    //                   -517304463: 200
    //                   942033467: 170
    //                   1601152418: 140;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        byte0;
        JVM INSTR tableswitch 0 4: default 128
    //                   0 215
    //                   1 215
    //                   2 215
    //                   3 215
    //                   4 215;
           goto _L2 _L9 _L9 _L9 _L9 _L9
_L2:
        byte0 = 0;
_L10:
        if (byte0 == 0)
        {
            return TimelySyncImpl.NULL_CONFERENCE_DATA;
        } else
        {
            ConferenceData conferencedata = new ConferenceData();
            Conference conference = new Conference();
            conference.type = ((String) (obj));
            conferencedata.conferences = Arrays.asList(new Conference[] {
                conference
            });
            return conferencedata;
        }
_L8:
        if (((String) (obj)).equals("eventHangout"))
        {
            byte0 = 0;
        }
          goto _L3
_L5:
        if (((String) (obj)).equals("eventNamedHangout"))
        {
            byte0 = 1;
        }
          goto _L3
_L7:
        if (((String) (obj)).equals("meeting"))
        {
            byte0 = 2;
        }
          goto _L3
_L4:
        if (((String) (obj)).equals("meetingPhoneNumber"))
        {
            byte0 = 3;
        }
          goto _L3
_L6:
        if (((String) (obj)).equals("meetingPhoneNumbersLink"))
        {
            byte0 = 4;
        }
          goto _L3
_L9:
        byte0 = 1;
          goto _L10
    }

    (TimelySyncImpl timelysyncimpl, String s, Account account)
    {
        arg$1 = timelysyncimpl;
        arg$2 = s;
        arg$3 = account;
    }
}
