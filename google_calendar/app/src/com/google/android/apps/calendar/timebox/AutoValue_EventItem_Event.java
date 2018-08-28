// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;


// Referenced classes of package com.google.android.apps.calendar.timebox:
//            Calendar

final class AutoValue_EventItem_Event extends EventItem.Event
{

    private final int accessLevel;
    private final Calendar calendar;
    private final Integer color;
    private final boolean endTimeUnspecified;
    private final boolean everyoneDeclined;
    private final boolean hasImageData;
    private final boolean hasSmartMail;
    private final boolean hasTimeProposals;
    private final boolean instanceModifiable;
    private final String location;
    private final String organizer;
    private final boolean outOfOffice;
    private final com.google.android.calendar.api.event.attendee.Response.ResponseStatus selfAttendeeStatus;
    private final String title;

    AutoValue_EventItem_Event(String s, Integer integer, Calendar calendar1, String s1, String s2, int i, boolean flag, 
            com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus, boolean flag1, boolean flag2, boolean flag3, boolean flag4, boolean flag5, boolean flag6)
    {
        title = s;
        color = integer;
        calendar = calendar1;
        location = s1;
        organizer = s2;
        accessLevel = i;
        instanceModifiable = flag;
        selfAttendeeStatus = responsestatus;
        hasSmartMail = flag1;
        hasImageData = flag2;
        endTimeUnspecified = flag3;
        everyoneDeclined = flag4;
        outOfOffice = flag5;
        hasTimeProposals = flag6;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof EventItem.Event)
            {
                obj = (EventItem.Event)obj;
                if ((title != null ? !title.equals(((EventItem.Event) (obj)).getTitle()) : ((EventItem.Event) (obj)).getTitle() != null) || (color != null ? !color.equals(((EventItem.Event) (obj)).getColor()) : ((EventItem.Event) (obj)).getColor() != null) || !calendar.equals(((EventItem.Event) (obj)).getCalendar()) || (location != null ? !location.equals(((EventItem.Event) (obj)).getLocation()) : ((EventItem.Event) (obj)).getLocation() != null) || (organizer != null ? !organizer.equals(((EventItem.Event) (obj)).getOrganizer()) : ((EventItem.Event) (obj)).getOrganizer() != null) || (accessLevel != ((EventItem.Event) (obj)).getAccessLevel() || instanceModifiable != ((EventItem.Event) (obj)).isInstanceModifiable() || !selfAttendeeStatus.equals(((EventItem.Event) (obj)).getSelfAttendeeStatus()) || hasSmartMail != ((EventItem.Event) (obj)).getHasSmartMail() || hasImageData != ((EventItem.Event) (obj)).getHasImageData() || endTimeUnspecified != ((EventItem.Event) (obj)).getEndTimeUnspecified() || everyoneDeclined != ((EventItem.Event) (obj)).getEveryoneDeclined() || outOfOffice != ((EventItem.Event) (obj)).getOutOfOffice() || hasTimeProposals != ((EventItem.Event) (obj)).getHasTimeProposals()))
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

    public final int getAccessLevel()
    {
        return accessLevel;
    }

    public final Calendar getCalendar()
    {
        return calendar;
    }

    public final Integer getColor()
    {
        return color;
    }

    public final boolean getEndTimeUnspecified()
    {
        return endTimeUnspecified;
    }

    public final boolean getEveryoneDeclined()
    {
        return everyoneDeclined;
    }

    public final boolean getHasImageData()
    {
        return hasImageData;
    }

    public final boolean getHasSmartMail()
    {
        return hasSmartMail;
    }

    public final boolean getHasTimeProposals()
    {
        return hasTimeProposals;
    }

    public final String getLocation()
    {
        return location;
    }

    public final String getOrganizer()
    {
        return organizer;
    }

    public final boolean getOutOfOffice()
    {
        return outOfOffice;
    }

    public final com.google.android.calendar.api.event.attendee.Response.ResponseStatus getSelfAttendeeStatus()
    {
        return selfAttendeeStatus;
    }

    public final String getTitle()
    {
        return title;
    }

    public final int hashCode()
    {
        int l = 0;
        char c6 = '\u04CF';
        int i;
        int j;
        int k;
        char c;
        char c1;
        char c2;
        char c3;
        char c4;
        char c5;
        int i1;
        int j1;
        int k1;
        if (title == null)
        {
            i = 0;
        } else
        {
            i = title.hashCode();
        }
        if (color == null)
        {
            j = 0;
        } else
        {
            j = color.hashCode();
        }
        i1 = calendar.hashCode();
        if (location == null)
        {
            k = 0;
        } else
        {
            k = location.hashCode();
        }
        if (organizer != null)
        {
            l = organizer.hashCode();
        }
        j1 = accessLevel;
        if (instanceModifiable)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        k1 = selfAttendeeStatus.hashCode();
        if (hasSmartMail)
        {
            c1 = '\u04CF';
        } else
        {
            c1 = '\u04D5';
        }
        if (hasImageData)
        {
            c2 = '\u04CF';
        } else
        {
            c2 = '\u04D5';
        }
        if (endTimeUnspecified)
        {
            c3 = '\u04CF';
        } else
        {
            c3 = '\u04D5';
        }
        if (everyoneDeclined)
        {
            c4 = '\u04CF';
        } else
        {
            c4 = '\u04D5';
        }
        if (outOfOffice)
        {
            c5 = '\u04CF';
        } else
        {
            c5 = '\u04D5';
        }
        if (!hasTimeProposals)
        {
            c6 = '\u04D5';
        }
        return (c5 ^ (c4 ^ (c3 ^ (c2 ^ (c1 ^ ((c ^ (((k ^ ((j ^ (i ^ 0xf4243) * 0xf4243) * 0xf4243 ^ i1) * 0xf4243) * 0xf4243 ^ l) * 0xf4243 ^ j1) * 0xf4243) * 0xf4243 ^ k1) * 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243 ^ c6;
    }

    public final boolean isInstanceModifiable()
    {
        return instanceModifiable;
    }

    public final String toString()
    {
        String s = title;
        String s1 = String.valueOf(color);
        String s2 = String.valueOf(calendar);
        String s3 = location;
        String s4 = organizer;
        int i = accessLevel;
        boolean flag = instanceModifiable;
        String s5 = String.valueOf(selfAttendeeStatus);
        boolean flag1 = hasSmartMail;
        boolean flag2 = hasImageData;
        boolean flag3 = endTimeUnspecified;
        boolean flag4 = everyoneDeclined;
        boolean flag5 = outOfOffice;
        boolean flag6 = hasTimeProposals;
        return (new StringBuilder(String.valueOf(s).length() + 260 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length())).append("Event{title=").append(s).append(", color=").append(s1).append(", calendar=").append(s2).append(", location=").append(s3).append(", organizer=").append(s4).append(", accessLevel=").append(i).append(", instanceModifiable=").append(flag).append(", selfAttendeeStatus=").append(s5).append(", hasSmartMail=").append(flag1).append(", hasImageData=").append(flag2).append(", endTimeUnspecified=").append(flag3).append(", everyoneDeclined=").append(flag4).append(", outOfOffice=").append(flag5).append(", hasTimeProposals=").append(flag6).append("}").toString();
    }
}
