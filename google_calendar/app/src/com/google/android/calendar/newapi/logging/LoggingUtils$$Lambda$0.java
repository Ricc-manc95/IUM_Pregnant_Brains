// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.logging;

import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.common.base.Predicate;

final class 
    implements Predicate
{

    public static final Predicate $instance = new <init>();

    public final boolean apply(Object obj)
    {
        obj = ((Attendee)obj).attendeeDescriptor.email;
        boolean flag;
        if (obj != null && ((String) (obj)).endsWith("@resource.calendar.google.com"))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return !flag;
    }


    private ()
    {
    }
}
