// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.userstatus.UserStatus;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventPermissionsFactory, Event, ReadOnlyEventPermissionsImpl, EventPermissionUtils, 
//            SmartMailEventPermissionsImpl, HabitInstanceEventPermissionsImpl, OutOfOfficeEventPermissionsImpl, AttendeeCopyEventPermissionsImpl, 
//            MutableEventPermissionsImpl, EventPermissions

public final class EventPermissionsFactoryImpl
    implements EventPermissionsFactory
{

    public EventPermissionsFactoryImpl()
    {
    }

    public final EventPermissions create(Event event)
    {
label0:
        {
            Object obj = event.getCalendarListEntry();
            if (obj != null)
            {
                obj = ((CalendarListEntry) (obj)).getAccessLevel();
                CalendarAccessLevel calendaraccesslevel = CalendarAccessLevel.READER;
                if (calendaraccesslevel == null)
                {
                    throw new NullPointerException();
                }
                boolean flag;
                if (((CalendarAccessLevel) (obj)).level - calendaraccesslevel.level <= 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break label0;
                }
            }
            return new ReadOnlyEventPermissionsImpl();
        }
        if (EventPermissionUtils.isGoogleEvent(event))
        {
            if (event.isSmartMailEvent())
            {
                return new SmartMailEventPermissionsImpl(event);
            }
            if (event.isHabitInstance())
            {
                return new HabitInstanceEventPermissionsImpl();
            }
            FeatureConfig featureconfig = Features.instance;
            if (featureconfig == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            if (((FeatureConfig)featureconfig).ooo() && event.getParticipantStatus() != null && event.getParticipantStatus().getOutOfOffice() != null)
            {
                return new OutOfOfficeEventPermissionsImpl(event);
            }
        }
        if (!EventPermissionUtils.attendeeEmailMatchesCalendar(event.getOrganizer(), event))
        {
            return new AttendeeCopyEventPermissionsImpl(event);
        }
        if (EventPermissionUtils.isGoogleEvent(event))
        {
            event.isSocial();
        }
        return new MutableEventPermissionsImpl(event);
    }
}
