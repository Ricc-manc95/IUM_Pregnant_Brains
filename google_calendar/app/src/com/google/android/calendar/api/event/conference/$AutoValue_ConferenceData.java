// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            ConferenceData, ConferenceSolution, CreateConferenceRequest

abstract class $AutoValue_ConferenceData extends ConferenceData
{

    private final String conferenceId;
    private final ConferenceSolution conferenceSolution;
    private final ImmutableList conferences;
    private final CreateConferenceRequest createConferenceRequest;
    private final String notes;
    private final String signature;

    $AutoValue_ConferenceData(ConferenceSolution conferencesolution, ImmutableList immutablelist, String s, String s1, String s2, CreateConferenceRequest createconferencerequest)
    {
        conferenceSolution = conferencesolution;
        if (immutablelist == null)
        {
            throw new NullPointerException("Null conferences");
        }
        conferences = immutablelist;
        if (s == null)
        {
            throw new NullPointerException("Null conferenceId");
        }
        conferenceId = s;
        if (s1 == null)
        {
            throw new NullPointerException("Null notes");
        }
        notes = s1;
        if (s2 == null)
        {
            throw new NullPointerException("Null signature");
        } else
        {
            signature = s2;
            createConferenceRequest = createconferencerequest;
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
            if (!(obj instanceof ConferenceData))
            {
                break MISSING_BLOCK_LABEL_138;
            }
            obj = (ConferenceData)obj;
            if ((conferenceSolution != null ? conferenceSolution.equals(((ConferenceData) (obj)).getConferenceSolution()) : ((ConferenceData) (obj)).getConferenceSolution() == null) && (conferences.equals(((ConferenceData) (obj)).getConferences()) && conferenceId.equals(((ConferenceData) (obj)).getConferenceId()) && notes.equals(((ConferenceData) (obj)).getNotes()) && signature.equals(((ConferenceData) (obj)).getSignature())))
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (createConferenceRequest != null) goto _L4; else goto _L3
_L3:
        if (((ConferenceData) (obj)).getCreateConferenceRequest() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!createConferenceRequest.equals(((ConferenceData) (obj)).getCreateConferenceRequest())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final String getConferenceId()
    {
        return conferenceId;
    }

    public final ConferenceSolution getConferenceSolution()
    {
        return conferenceSolution;
    }

    public final ImmutableList getConferences()
    {
        return conferences;
    }

    public final CreateConferenceRequest getCreateConferenceRequest()
    {
        return createConferenceRequest;
    }

    public final String getNotes()
    {
        return notes;
    }

    public final String getSignature()
    {
        return signature;
    }

    public int hashCode()
    {
        int j = 0;
        int i;
        int k;
        int l;
        int i1;
        int j1;
        if (conferenceSolution == null)
        {
            i = 0;
        } else
        {
            i = conferenceSolution.hashCode();
        }
        k = conferences.hashCode();
        l = conferenceId.hashCode();
        i1 = notes.hashCode();
        j1 = signature.hashCode();
        if (createConferenceRequest != null)
        {
            j = createConferenceRequest.hashCode();
        }
        return (((((i ^ 0xf4243) * 0xf4243 ^ k) * 0xf4243 ^ l) * 0xf4243 ^ i1) * 0xf4243 ^ j1) * 0xf4243 ^ j;
    }

    public String toString()
    {
        String s = String.valueOf(conferenceSolution);
        String s1 = String.valueOf(conferences);
        String s2 = conferenceId;
        String s3 = notes;
        String s4 = signature;
        String s5 = String.valueOf(createConferenceRequest);
        return (new StringBuilder(String.valueOf(s).length() + 110 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length())).append("ConferenceData{conferenceSolution=").append(s).append(", conferences=").append(s1).append(", conferenceId=").append(s2).append(", notes=").append(s3).append(", signature=").append(s4).append(", createConferenceRequest=").append(s5).append("}").toString();
    }
}
