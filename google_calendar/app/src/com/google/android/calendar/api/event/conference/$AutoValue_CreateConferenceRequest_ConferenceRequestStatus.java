// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;


abstract class $AutoValue_CreateConferenceRequest_ConferenceRequestStatus extends CreateConferenceRequest.ConferenceRequestStatus
{

    private final int statusCode;

    $AutoValue_CreateConferenceRequest_ConferenceRequestStatus(int i)
    {
        statusCode = i;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof CreateConferenceRequest.ConferenceRequestStatus)
            {
                if (statusCode != ((CreateConferenceRequest.ConferenceRequestStatus) (obj = (CreateConferenceRequest.ConferenceRequestStatus)obj)).getStatusCode())
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final int getStatusCode()
    {
        return statusCode;
    }

    public int hashCode()
    {
        return 0xf4243 ^ statusCode;
    }

    public String toString()
    {
        int i = statusCode;
        return (new StringBuilder(47)).append("ConferenceRequestStatus{statusCode=").append(i).append("}").toString();
    }
}
