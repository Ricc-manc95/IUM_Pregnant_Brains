// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;


// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            Attendee

abstract class $AutoValue_Attendee extends Attendee
{

    private final String displayName;
    private final String email;
    private final boolean organizer;
    private final com.google.android.calendar.api.event.attendee.Response.ResponseStatus responseStatus;

    $AutoValue_Attendee(String s, String s1, boolean flag, com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus)
    {
        if (s == null)
        {
            throw new NullPointerException("Null email");
        }
        email = s;
        displayName = s1;
        organizer = flag;
        if (responsestatus == null)
        {
            throw new NullPointerException("Null responseStatus");
        } else
        {
            responseStatus = responsestatus;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof Attendee)
            {
                if (!email.equals(((Attendee) (obj = (Attendee)obj)).getEmail()) || (displayName != null ? !displayName.equals(((Attendee) (obj)).getDisplayName()) : ((Attendee) (obj)).getDisplayName() != null) || (organizer != ((Attendee) (obj)).isOrganizer() || !responseStatus.equals(((Attendee) (obj)).getResponseStatus())))
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

    public final String getDisplayName()
    {
        return displayName;
    }

    public final String getEmail()
    {
        return email;
    }

    public final com.google.android.calendar.api.event.attendee.Response.ResponseStatus getResponseStatus()
    {
        return responseStatus;
    }

    public int hashCode()
    {
        int j = email.hashCode();
        int i;
        char c;
        if (displayName == null)
        {
            i = 0;
        } else
        {
            i = displayName.hashCode();
        }
        if (organizer)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return (c ^ (i ^ (j ^ 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243 ^ responseStatus.hashCode();
    }

    public final boolean isOrganizer()
    {
        return organizer;
    }

    public String toString()
    {
        String s = email;
        String s1 = displayName;
        boolean flag = organizer;
        String s2 = String.valueOf(responseStatus);
        return (new StringBuilder(String.valueOf(s).length() + 64 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("Attendee{email=").append(s).append(", displayName=").append(s1).append(", organizer=").append(flag).append(", responseStatus=").append(s2).append("}").toString();
    }
}
