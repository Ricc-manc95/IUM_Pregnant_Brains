// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.timely.FindTimeUtil;
import com.google.common.collect.Iterators;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

// Referenced classes of package com.google.android.calendar.alerts:
//            EventNotificationInfo

class EveryoneDeclinedAlertBuilderHelper
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/alerts/EveryoneDeclinedAlertBuilderHelper);
    public List attendees;
    public final Context context;
    public final EventNotificationInfo info;
    public boolean isOrganizer;
    public final int notificationId;
    public boolean showEveryoneDeclined;
    public boolean showFindTime;

    EveryoneDeclinedAlertBuilderHelper(Context context1, EventNotificationInfo eventnotificationinfo, int i)
    {
        boolean flag;
        flag = true;
        super();
        showFindTime = false;
        showEveryoneDeclined = false;
        isOrganizer = false;
        attendees = Collections.emptyList();
        context = context1;
        info = eventnotificationinfo;
        notificationId = i;
        if (!RemoteFeatureConfig.EVERYONE_DECLINED.enabled() || !eventnotificationinfo.everyoneDeclinedAndNotDismissed) goto _L2; else goto _L1
_L1:
        Object obj;
        com.google.common.collect.ImmutableList immutablelist;
        eventnotificationinfo = (Event)CalendarApi.Events.read(eventnotificationinfo.eventKey).get();
        attendees = eventnotificationinfo.getAttendees();
        obj = eventnotificationinfo.getCalendar();
        obj = (CalendarListEntry)CalendarApi.CalendarList.read(((com.google.android.calendar.api.calendarlist.CalendarDescriptor) (obj))).get();
        immutablelist = FindTimeUtil.FIND_TIME_SCENARIOS;
        context1 = new com.google.android.calendar.timely.FindTimeUtil..Lambda._cls0(context1, ((CalendarListEntry) (obj)));
        if (Iterators.indexOf(immutablelist.iterator(), context1) != -1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L4; else goto _L3
_L3:
        if (!eventnotificationinfo.isAllDayEvent()) goto _L6; else goto _L5
_L5:
        i = 0;
          goto _L7
_L6:
        if (!eventnotificationinfo.isEndTimeUnspecified()) goto _L9; else goto _L8
_L8:
        i = 0;
          goto _L7
_L9:
        l = eventnotificationinfo.getEndMillis();
        l1 = eventnotificationinfo.getStartMillis();
        l -= l1;
        if (l > 0x5265c00L)
        {
            i = 0;
        } else
        if (l == 0L)
        {
            i = 0;
        } else
        {
            i = 1;
        }
          goto _L7
_L4:
        flag = false;
        break; /* Loop/switch isn't completed */
_L10:
        long l;
        long l1;
        try
        {
            showFindTime = flag;
            showEveryoneDeclined = ((CalendarListEntry) (obj)).isPrimary();
            isOrganizer = AttendeeUtils.isOrganizerAndListed(eventnotificationinfo);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Context context1) { }
        // Misplaced declaration of an exception variable
        catch (Context context1) { }
        LogUtils.w(TAG, context1, "Failed to load event.", new Object[0]);
        return;
_L7:
        if (i == 0) goto _L4; else goto _L10
_L2:
    }

}
