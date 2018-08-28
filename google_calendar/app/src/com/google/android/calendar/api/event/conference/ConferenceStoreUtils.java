// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import com.google.api.services.calendar.model.Conference;
import com.google.api.services.calendar.model.ConferenceData;
import com.google.api.services.calendar.model.ConferenceRequestStatus;
import com.google.api.services.calendar.model.ConferenceSolution;
import com.google.api.services.calendar.model.ConferenceSolutionAddOnId;
import com.google.api.services.calendar.model.ConferenceSolutionKey;
import com.google.api.services.calendar.model.CreateConferenceRequest;
import com.google.common.base.Platform;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            ConferenceData, ConferenceTypeStoreUtils, Conference, AutoValue_CreateConferenceRequest, 
//            AutoValue_ConferenceSolution_Key_AddOnId, AutoValue_CreateConferenceRequest_ConferenceRequestStatus, AutoValue_ConferenceSolution

public final class ConferenceStoreUtils
{

    public static com.google.android.calendar.api.event.conference.ConferenceData toApiConferenceData(ConferenceData conferencedata)
    {
        Object obj;
        Object obj1;
        Object obj2;
        byte byte0;
        byte0 = 3;
        obj2 = null;
        if (conferencedata == null)
        {
            return ConferenceData.EMPTY;
        }
        obj1 = conferencedata.createRequest;
        Iterator iterator;
        Conference.Builder builder;
        int i;
        if (obj1 == null)
        {
            obj = null;
            break MISSING_BLOCK_LABEL_24;
        }
        obj3 = ((CreateConferenceRequest) (obj1)).requestId;
        obj = ((CreateConferenceRequest) (obj1)).conferenceSolutionKey;
        if (obj == null || ((ConferenceSolutionKey) (obj)).type == null)
        {
            obj = ConferenceSolution.Key.create("unknownConferenceSolution");
        } else
        {
            String s = ((ConferenceSolutionKey) (obj)).type;
            obj4 = ((ConferenceSolutionKey) (obj)).addOnId;
            if (obj4 == null)
            {
                obj = null;
            } else
            {
                obj = ((ConferenceSolutionAddOnId) (obj4)).deploymentId;
                obj4 = ((ConferenceSolutionAddOnId) (obj4)).solutionId;
                obj = new AutoValue_ConferenceSolution_Key_AddOnId(Platform.nullToEmpty(((String) (obj))), Platform.nullToEmpty(((String) (obj4))));
            }
            obj = ConferenceSolution.Key.create(s, ((ConferenceSolution.Key.AddOnId) (obj)));
        }
        obj1 = ((CreateConferenceRequest) (obj1)).status;
        if (obj1 != null && ((ConferenceRequestStatus) (obj1)).statusCode != null) goto _L2; else goto _L1
_L1:
        obj1 = null;
_L12:
        obj = new AutoValue_CreateConferenceRequest(Platform.nullToEmpty(((String) (obj3))), ((ConferenceSolution.Key) (obj)), ((CreateConferenceRequest.ConferenceRequestStatus) (obj1)));
        continue;
_L2:
        obj1 = ((ConferenceRequestStatus) (obj1)).statusCode;
        j = -1;
        ((String) (obj1)).hashCode();
        JVM INSTR lookupswitch 4: default 468
    //                   -1867169789: 527
    //                   -1086574198: 542
    //                   -682587753: 512
    //                   -284840886: 557;
           goto _L3 _L4 _L5 _L6 _L7
_L3:
        j;
        JVM INSTR tableswitch 0 2: default 496
    //                   0 572
    //                   1 578
    //                   2 499;
           goto _L8 _L9 _L10 _L11
_L11:
        break; /* Loop/switch isn't completed */
_L8:
        byte0 = 0;
_L13:
        obj1 = new AutoValue_CreateConferenceRequest_ConferenceRequestStatus(byte0);
          goto _L12
_L6:
        if (((String) (obj1)).equals("pending"))
        {
            j = 0;
        }
          goto _L3
_L4:
        if (((String) (obj1)).equals("success"))
        {
            j = 1;
        }
          goto _L3
_L5:
        if (((String) (obj1)).equals("failure"))
        {
            j = 2;
        }
          goto _L3
_L7:
        if (((String) (obj1)).equals("unknown"))
        {
            j = 3;
        }
          goto _L3
_L9:
        byte0 = 1;
          goto _L13
_L10:
        byte0 = 2;
          goto _L13
        do
        {
            Object obj3 = conferencedata.conferenceSolution;
            if (obj3 == null)
            {
                obj1 = null;
            } else
            {
                obj1 = ((ConferenceSolution) (obj3)).key;
                if (obj1 == null || ((ConferenceSolutionKey) (obj1)).type == null)
                {
                    obj1 = ConferenceSolution.Key.create("unknownConferenceSolution");
                } else
                {
                    String s1 = ((ConferenceSolutionKey) (obj1)).type;
                    obj1 = ((ConferenceSolutionKey) (obj1)).addOnId;
                    if (obj1 == null)
                    {
                        obj1 = obj2;
                    } else
                    {
                        obj2 = ((ConferenceSolutionAddOnId) (obj1)).deploymentId;
                        obj1 = ((ConferenceSolutionAddOnId) (obj1)).solutionId;
                        obj1 = new AutoValue_ConferenceSolution_Key_AddOnId(Platform.nullToEmpty(((String) (obj2))), Platform.nullToEmpty(((String) (obj1))));
                    }
                    obj1 = ConferenceSolution.Key.create(s1, ((ConferenceSolution.Key.AddOnId) (obj1)));
                }
                obj2 = ((ConferenceSolution) (obj3)).name;
                obj3 = ((ConferenceSolution) (obj3)).iconUri;
                obj1 = new AutoValue_ConferenceSolution(((ConferenceSolution.Key) (obj1)), Platform.nullToEmpty(((String) (obj2))), Platform.nullToEmpty(((String) (obj3))));
            }
            obj3 = new ArrayList();
            if (conferencedata.conferences != null)
            {
                iterator = conferencedata.conferences.iterator();
                while (iterator.hasNext()) 
                {
                    Object obj4 = (Conference)iterator.next();
                    i = ConferenceTypeStoreUtils.toApiType(((Conference) (obj4)).type);
                    builder = Conference.builder().setType(i).setUri(Platform.nullToEmpty(((Conference) (obj4)).uri)).setName(Platform.nullToEmpty(((Conference) (obj4)).name)).setPassCode(Platform.nullToEmpty(((Conference) (obj4)).passCode)).setRegionCode(Platform.nullToEmpty(((Conference) (obj4)).regionCode)).setAccessCode(Platform.nullToEmpty(((Conference) (obj4)).accessCode));
                    int j;
                    if (((Conference) (obj4)).entryPointType == null)
                    {
                        obj2 = "unknown";
                    } else
                    {
                        obj2 = ((Conference) (obj4)).entryPointType;
                    }
                    obj2 = builder.setEntryPointType(((String) (obj2))).setLabel(Platform.nullToEmpty(((Conference) (obj4)).label)).setMeetingCode(Platform.nullToEmpty(((Conference) (obj4)).meetingCode)).setPasscode(Platform.nullToEmpty(((Conference) (obj4)).passcode)).setPassword(Platform.nullToEmpty(((Conference) (obj4)).password)).setPin(Platform.nullToEmpty(((Conference) (obj4)).pin));
                    obj4 = ((Conference) (obj4)).gatewayAccessEnabled;
                    if (obj4 == null)
                    {
                        j = 0;
                    } else
                    if (((Boolean) (obj4)).booleanValue())
                    {
                        j = 1;
                    } else
                    {
                        j = 2;
                    }
                    obj2 = ((Conference.Builder) (obj2)).setGatewayAccess(j);
                    ((Conference.Builder) (obj2)).setRegionCode(((Conference.Builder) (obj2)).getRegionCode().toUpperCase(Locale.ENGLISH));
                    ((List) (obj3)).add(((Conference.Builder) (obj2)).autoBuild());
                }
            }
            return ConferenceData.create(((com.google.android.calendar.api.event.conference.ConferenceSolution) (obj1)), ((List) (obj3)), Platform.nullToEmpty(conferencedata.conferenceId), Platform.nullToEmpty(conferencedata.notes), Platform.nullToEmpty(conferencedata.signature), ((com.google.android.calendar.api.event.conference.CreateConferenceRequest) (obj)));
        } while (true);
          goto _L12
    }

    public static ConferenceSolutionKey toStoreConferenceSolutionKey(ConferenceSolution.Key key)
    {
        ConferenceSolutionKey conferencesolutionkey = new ConferenceSolutionKey();
        conferencesolutionkey.type = key.getType();
        if (key.getAddOnId() != null)
        {
            key = key.getAddOnId();
            ConferenceSolutionAddOnId conferencesolutionaddonid = new ConferenceSolutionAddOnId();
            if (!Platform.stringIsNullOrEmpty(key.getSolutionId()))
            {
                conferencesolutionaddonid.solutionId = key.getSolutionId();
            }
            if (!Platform.stringIsNullOrEmpty(key.getDeploymentId()))
            {
                conferencesolutionaddonid.deploymentId = key.getDeploymentId();
            }
            conferencesolutionkey.addOnId = conferencesolutionaddonid;
        }
        return conferencesolutionkey;
    }
}
