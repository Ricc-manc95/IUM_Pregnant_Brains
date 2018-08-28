// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventFactory;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.GooglePrivateDataModification;
import com.google.android.calendar.newapi.logging.LoggingUtils;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.alerts:
//            NotificationActionTrampoline

final class arg._cls1
    implements Function
{

    private final NotificationActionTrampoline arg$1;

    public final Object apply(Object obj)
    {
        NotificationActionTrampoline notificationactiontrampoline = arg$1;
        Event event = (Event)obj;
        if (AttendeeUtils.isOrganizerAndListed(event))
        {
            obj = "email_guests_organizer";
        } else
        {
            obj = "email_guests";
        }
        LoggingUtils.logEveryoneDeclined(notificationactiontrampoline, ((String) (obj)), false, event.getAttendees());
        obj = CalendarApi.EventFactory.modifyEvent(event);
        ((EventModifications) (obj)).getGooglePrivateDataModification().setIsEveryoneDeclinedDismissed(true);
        return obj;
    }

    (NotificationActionTrampoline notificationactiontrampoline)
    {
        arg$1 = notificationactiontrampoline;
    }
}
