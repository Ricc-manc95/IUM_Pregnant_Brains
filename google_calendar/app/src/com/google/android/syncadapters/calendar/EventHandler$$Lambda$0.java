// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import com.google.api.services.calendar.model.EventAttendee;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            EventHandler

final class arg._cls1
    implements Predicate
{

    private final EventAttendee arg$1;

    public final boolean apply(Object obj)
    {
        return EventHandler.lambda$getEventAttendeeFromList$0$EventHandler(arg$1, (EventAttendee)obj);
    }

    (EventAttendee eventattendee)
    {
        arg$1 = eventattendee;
    }
}
