// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.TimeChangeProposal;
import com.google.common.base.Predicate;

final class 
    implements Predicate
{

    public static final Predicate $instance = new <init>();

    public final boolean apply(Object obj)
    {
        obj = (EventAttendee)obj;
        return ((EventAttendee) (obj)).timeChangeProposal != null && ((EventAttendee) (obj)).timeChangeProposal.startTimeMillis != null && ((EventAttendee) (obj)).timeChangeProposal.endTimeMillis != null;
    }


    private ()
    {
    }
}