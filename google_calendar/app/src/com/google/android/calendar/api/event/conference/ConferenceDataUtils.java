// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Present;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            ConferenceTypeStoreUtils, ConferenceData, ConferenceDataModifications, ConferenceSolution, 
//            CreateConferenceRequest, Conference

public final class ConferenceDataUtils
{

    public static final ImmutableCollection PRIMARY_CONFERENCE_TYPES = ImmutableList.of(Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(3));

    public static int convertConferenceSolutionTypeToConferenceType(String s)
    {
        byte byte0;
        int i;
        i = 1;
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 3: default 44
    //                   -972730403: 90
    //                   774960958: 104
    //                   1601152418: 76;
           goto _L1 _L2 _L3 _L4
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_104;
_L5:
        switch (byte0)
        {
        default:
            i = 0;
            // fall through

        case 0: // '\0'
            return i;

        case 1: // '\001'
            return 2;

        case 2: // '\002'
            return 3;
        }
_L4:
        if (s.equals("eventHangout"))
        {
            byte0 = 0;
        }
          goto _L5
_L2:
        if (s.equals("eventNamedHangout"))
        {
            byte0 = 1;
        }
          goto _L5
        if (s.equals("hangoutsMeet"))
        {
            byte0 = 2;
        }
          goto _L5
    }

    public static String convertConferenceTypeToConferenceSolutionType(int i)
    {
        switch (i)
        {
        default:
            return "unknownConferenceSolution";

        case 1: // '\001'
            return "eventHangout";

        case 2: // '\002'
            return "eventNamedHangout";

        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
            return "hangoutsMeet";
        }
    }

    public static ConferenceData createDefaultConferenceData(CalendarDescriptor calendardescriptor)
    {
        return ConferenceData.createConferenceRequest(convertConferenceTypeToConferenceSolutionType(ConferenceTypeStoreUtils.getDefaultConferenceType(calendardescriptor, null)));
    }

    public static void createNewConference(ConferenceDataModifications conferencedatamodifications, int i)
    {
        conferencedatamodifications.createNewConference(i);
    }

    public static int getConferenceType(ConferenceData conferencedata)
    {
        Object obj = conferencedata.getConferenceSolution();
        if (obj != null)
        {
            return convertConferenceSolutionTypeToConferenceType(((ConferenceSolution) (obj)).getKey().getType());
        }
        obj = conferencedata.getCreateConferenceRequest();
        if (obj != null)
        {
            return convertConferenceSolutionTypeToConferenceType(((CreateConferenceRequest) (obj)).getConferenceSolutionKey().getType());
        }
        obj = conferencedata.getConferences();
        class .Lambda._cls0
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls0();

            public final boolean apply(Object obj2)
            {
                obj2 = (Conference)obj2;
                return ConferenceDataUtils.PRIMARY_CONFERENCE_TYPES.contains(Integer.valueOf(((Conference) (obj2)).getType()));
            }


            private .Lambda._cls0()
            {
            }
        }

        conferencedata = .Lambda._cls0..instance;
        obj = ((Iterable) (obj)).iterator();
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (conferencedata == null)
        {
            throw new NullPointerException();
        }
        Object obj1;
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break MISSING_BLOCK_LABEL_144;
            }
            obj1 = ((Iterator) (obj)).next();
        } while (!conferencedata.apply(obj1));
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        conferencedata = new Present(obj1);
_L1:
        conferencedata = (Conference)conferencedata.orNull();
        if (conferencedata != null)
        {
            return conferencedata.getType();
        } else
        {
            return 0;
        }
        conferencedata = Absent.INSTANCE;
          goto _L1
    }

    public static boolean isAddOn(ConferenceData conferencedata)
    {
        conferencedata = conferencedata.getConferenceSolution();
        return conferencedata != null && conferencedata.getKey().getType().equals("addOn");
    }

    public static boolean isCreationPending(ConferenceData conferencedata)
    {
label0:
        {
            conferencedata = conferencedata.getCreateConferenceRequest();
            if (conferencedata == null)
            {
                break label0;
            }
            if (conferencedata.getConferenceRequestStatus() != null)
            {
                boolean flag;
                if (conferencedata.getConferenceRequestStatus() != null && conferencedata.getConferenceRequestStatus().getStatusCode() == 1)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break label0;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isCreationSuccessful(ConferenceData conferencedata)
    {
        conferencedata = conferencedata.getCreateConferenceRequest();
        if (conferencedata != null)
        {
            boolean flag;
            if (conferencedata.getConferenceRequestStatus() != null && conferencedata.getConferenceRequestStatus().getStatusCode() == 2)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmptyConference(ConferenceData conferencedata)
    {
        return conferencedata.getConferences().isEmpty() && conferencedata.getCreateConferenceRequest() == null;
    }

}
