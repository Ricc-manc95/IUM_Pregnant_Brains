// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;


// Referenced classes of package com.google.android.apps.calendar.timebox:
//            Calendar

public abstract class esponseStatus
{

    public abstract int getAccessLevel();

    public abstract Calendar getCalendar();

    public abstract Integer getColor();

    public abstract boolean getEndTimeUnspecified();

    public abstract boolean getEveryoneDeclined();

    public abstract boolean getHasImageData();

    public abstract boolean getHasSmartMail();

    public abstract boolean getHasTimeProposals();

    public abstract String getLocation();

    public abstract String getOrganizer();

    public abstract boolean getOutOfOffice();

    public abstract com.google.android.calendar.api.event.attendee.eStatus getSelfAttendeeStatus();

    public abstract String getTitle();

    public abstract boolean isInstanceModifiable();

    public esponseStatus()
    {
    }
}
