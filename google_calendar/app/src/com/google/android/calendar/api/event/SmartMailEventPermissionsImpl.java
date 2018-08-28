// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.notification.MutableNotificationPermissionsImpl;
import com.google.android.calendar.api.event.notification.NotificationPermissions;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event:
//            ReadOnlyEventPermissionsImpl, Event, EventPermissionUtils

public final class SmartMailEventPermissionsImpl extends ReadOnlyEventPermissionsImpl
{

    private final Event event;

    public SmartMailEventPermissionsImpl(Event event1)
    {
        if (event1 == null)
        {
            throw new NullPointerException();
        }
        event = (Event)event1;
        if (!EventPermissionUtils.isGoogleEvent(event1))
        {
            throw new IllegalStateException();
        } else
        {
            return;
        }
    }

    public final boolean canDelete()
    {
        return true;
    }

    public final boolean canModifyColorOverride()
    {
        return true;
    }

    public final List getAllowedAvailabilityValues()
    {
        CalendarListEntry calendarlistentry = event.getCalendarListEntry();
        if (calendarlistentry == null)
        {
            return Collections.emptyList();
        } else
        {
            return calendarlistentry.getAllowedAvailabilityValues();
        }
    }

    public final List getAllowedModificationScopes()
    {
        return Collections.singletonList(Integer.valueOf(0));
    }

    public final List getAllowedVisibilityValues()
    {
        return Collections.unmodifiableList(Arrays.asList(new Integer[] {
            Integer.valueOf(0), Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(3)
        }));
    }

    public final NotificationPermissions getNotificationPermissions()
    {
        return new MutableNotificationPermissionsImpl(event);
    }

    public final boolean isReadOnly()
    {
        return false;
    }
}
