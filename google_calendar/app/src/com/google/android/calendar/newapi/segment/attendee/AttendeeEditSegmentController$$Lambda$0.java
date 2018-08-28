// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee;

import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.newapi.segment.attendee:
//            AttendeeEditSegmentController

final class arg._cls1
    implements Predicate
{

    private final Event arg$1;

    public final boolean apply(Object obj)
    {
        return AttendeeEditSegmentController.lambda$getAttendeeFilter$0$AttendeeEditSegmentController(arg$1, (Attendee)obj);
    }

    a(Event event)
    {
        arg$1 = event;
    }
}
