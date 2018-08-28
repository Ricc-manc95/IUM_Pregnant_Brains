// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;


// Referenced classes of package com.google.android.calendar.api.event.conference:
//            CreateConferenceRequest

abstract class $AutoValue_CreateConferenceRequest extends CreateConferenceRequest
{

    private final CreateConferenceRequest.ConferenceRequestStatus conferenceRequestStatus;
    private final ConferenceSolution.Key conferenceSolutionKey;
    private final String requestId;

    $AutoValue_CreateConferenceRequest(String s, ConferenceSolution.Key key, CreateConferenceRequest.ConferenceRequestStatus conferencerequeststatus)
    {
        if (s == null)
        {
            throw new NullPointerException("Null requestId");
        } else
        {
            requestId = s;
            conferenceSolutionKey = key;
            conferenceRequestStatus = conferencerequeststatus;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof CreateConferenceRequest))
            {
                break MISSING_BLOCK_LABEL_96;
            }
            obj = (CreateConferenceRequest)obj;
            if (requestId.equals(((CreateConferenceRequest) (obj)).getRequestId()) && (conferenceSolutionKey != null ? conferenceSolutionKey.equals(((CreateConferenceRequest) (obj)).getConferenceSolutionKey()) : ((CreateConferenceRequest) (obj)).getConferenceSolutionKey() == null))
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (conferenceRequestStatus != null) goto _L4; else goto _L3
_L3:
        if (((CreateConferenceRequest) (obj)).getConferenceRequestStatus() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!conferenceRequestStatus.equals(((CreateConferenceRequest) (obj)).getConferenceRequestStatus())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final CreateConferenceRequest.ConferenceRequestStatus getConferenceRequestStatus()
    {
        return conferenceRequestStatus;
    }

    public final ConferenceSolution.Key getConferenceSolutionKey()
    {
        return conferenceSolutionKey;
    }

    public final String getRequestId()
    {
        return requestId;
    }

    public int hashCode()
    {
        int j = 0;
        int k = requestId.hashCode();
        int i;
        if (conferenceSolutionKey == null)
        {
            i = 0;
        } else
        {
            i = conferenceSolutionKey.hashCode();
        }
        if (conferenceRequestStatus != null)
        {
            j = conferenceRequestStatus.hashCode();
        }
        return (i ^ (k ^ 0xf4243) * 0xf4243) * 0xf4243 ^ j;
    }

    public String toString()
    {
        String s = requestId;
        String s1 = String.valueOf(conferenceSolutionKey);
        String s2 = String.valueOf(conferenceRequestStatus);
        return (new StringBuilder(String.valueOf(s).length() + 85 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("CreateConferenceRequest{requestId=").append(s).append(", conferenceSolutionKey=").append(s1).append(", conferenceRequestStatus=").append(s2).append("}").toString();
    }
}
