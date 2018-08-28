// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import android.accounts.Account;
import android.util.Pair;
import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import java.util.Map;

public final class ConferenceTypeStoreUtils
{

    public static int getDefaultConferenceType(CalendarDescriptor calendardescriptor, Map map)
    {
        if (map != null)
        {
            calendardescriptor = (String)map.get(Pair.create(calendardescriptor.account, calendardescriptor.calendarId));
        } else
        {
            calendardescriptor = TimelyStore.acquire(CalendarApi.getApiAppContext()).getConferenceTypeForCalendar(calendardescriptor.calendarId, calendardescriptor.account.name, calendardescriptor.account.type);
        }
        return toApiType(calendardescriptor);
    }

    static int toApiType(String s)
    {
        byte byte0;
        if (s == null)
        {
            return 0;
        }
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 5: default 64
    //                   -1876495076: 146
    //                   -972730403: 118
    //                   -517304463: 160
    //                   942033467: 132
    //                   1601152418: 104;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        break; /* Loop/switch isn't completed */
_L4:
        break MISSING_BLOCK_LABEL_160;
_L7:
        switch (byte0)
        {
        default:
            return 0;

        case 0: // '\0'
            return 1;

        case 1: // '\001'
            return 2;

        case 2: // '\002'
            return 3;

        case 3: // '\003'
            return 4;

        case 4: // '\004'
            return 5;
        }
_L6:
        if (s.equals("eventHangout"))
        {
            byte0 = 0;
        }
          goto _L7
_L3:
        if (s.equals("eventNamedHangout"))
        {
            byte0 = 1;
        }
          goto _L7
_L5:
        if (s.equals("meeting"))
        {
            byte0 = 2;
        }
          goto _L7
_L2:
        if (s.equals("meetingPhoneNumber"))
        {
            byte0 = 3;
        }
          goto _L7
        if (s.equals("meetingPhoneNumbersLink"))
        {
            byte0 = 4;
        }
          goto _L7
    }
}
