// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee;

import java.util.Collection;

public final class AttendeesResult
{

    public final Collection attendees;
    public final boolean canAttendeesAddAttendees;

    public AttendeesResult(Collection collection, boolean flag)
    {
        attendees = collection;
        canAttendeesAddAttendees = flag;
    }
}
