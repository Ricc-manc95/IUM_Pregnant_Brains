// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.api.util.attendee;

import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.common.base.Predicate;

public final class arg._cls1
    implements Predicate
{

    private final String arg$1;

    public final boolean apply(Object obj)
    {
        String s = arg$1;
        obj = ((Attendee)obj).attendeeDescriptor.email;
        return obj != null && ((String) (obj)).equalsIgnoreCase(s);
    }

    public (String s)
    {
        arg$1 = s;
    }
}
