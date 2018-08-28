// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.common.base.Predicate;

final class 
    implements Predicate
{

    public static final Predicate $instance = new <init>();

    public final boolean apply(Object obj)
    {
        obj = (Attendee)obj;
        return ((Attendee) (obj)).response != null && ((Attendee) (obj)).response.proposedStartTimeMillis != null && ((Attendee) (obj)).response.proposedEndTimeMillis != null;
    }


    private ()
    {
    }
}
