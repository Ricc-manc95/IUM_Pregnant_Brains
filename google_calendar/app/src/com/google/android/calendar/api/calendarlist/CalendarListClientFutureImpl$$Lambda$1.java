// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.color.CustomCalendarColor;
import com.google.android.calendar.api.color.NamedCalendarColor;
import com.google.android.calendar.api.event.notification.NotificationsTimelyStoreUtils;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarListEntryModifications, CalendarListClientFutureImpl, CalendarDescriptor, CalendarKey, 
//            CalendarListApiStoreImpl

final class arg._cls2
    implements Callable
{

    private final CalendarListClientFutureImpl arg$1;
    private final CalendarListEntryModifications arg$2;

    public final Object call()
    {
        CalendarListEntryModifications calendarlistentrymodifications;
        boolean flag;
        flag = true;
        Object obj = arg$1;
        calendarlistentrymodifications = arg$2;
        if (calendarlistentrymodifications.getOriginal() == null)
        {
            throw new NullPointerException();
        }
        obj = ((CalendarListClientFutureImpl) (obj)).api;
        if (!calendarlistentrymodifications.isModified()) goto _L2; else goto _L1
_L1:
        ContentValues contentvalues;
        Object obj1;
        int i;
        contentvalues = new ContentValues();
        if (calendarlistentrymodifications.isVisibleModified())
        {
            if (calendarlistentrymodifications.isVisible())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            contentvalues.put("visible", Integer.valueOf(i));
        }
        if (calendarlistentrymodifications.isSyncEnabledModified())
        {
            if (calendarlistentrymodifications.isSyncEnabled())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            contentvalues.put("sync_events", Integer.valueOf(i));
        }
        if (calendarlistentrymodifications.isDisplayNameModified())
        {
            contentvalues.put("calendar_displayName", calendarlistentrymodifications.getDisplayName());
        }
        if (!calendarlistentrymodifications.isColorModified()) goto _L4; else goto _L3
_L3:
        obj1 = calendarlistentrymodifications.getColor();
        if (!(obj1 instanceof NamedCalendarColor)) goto _L6; else goto _L5
_L5:
        contentvalues.put("calendar_color_index", CalendarApi.getColorFactory().getCalendarColorKey((NamedCalendarColor)obj1));
        contentvalues.put("calendar_color", Integer.valueOf(((CalendarColor) (obj1)).getValue()));
_L4:
        if (contentvalues.size() > 0)
        {
            obj1 = ContentUris.withAppendedId(android.provider.alue, calendarlistentrymodifications.getDescriptor().calendarKey.getLocalId());
            CalendarApi.getApiContentResolver().update(((android.net.Uri) (obj1)), contentvalues, null, null);
        }
_L2:
        if (calendarlistentrymodifications.areDefaultNotificationsModified(1))
        {
            NotificationsTimelyStoreUtils.storeDefaultNotifications(calendarlistentrymodifications.getDefaultNotifications(1), calendarlistentrymodifications.getDescriptor(), false);
        }
        if (calendarlistentrymodifications.areDefaultNotificationsModified(2))
        {
            NotificationsTimelyStoreUtils.storeDefaultNotifications(calendarlistentrymodifications.getDefaultNotifications(2), calendarlistentrymodifications.getDescriptor(), true);
        }
        if (CalendarListApiStoreImpl.read(calendarlistentrymodifications.getDescriptor()) != null)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        return Integer.valueOf(i);
_L6:
        if (obj1 instanceof CustomCalendarColor)
        {
            contentvalues.put("calendar_color_index", "");
            contentvalues.put("calendar_color", Integer.valueOf(((CustomCalendarColor)obj1).getOriginalValue()));
        }
        if (true) goto _L4; else goto _L7
_L7:
    }

    Y(CalendarListClientFutureImpl calendarlistclientfutureimpl, CalendarListEntryModifications calendarlistentrymodifications)
    {
        arg$1 = calendarlistclientfutureimpl;
        arg$2 = calendarlistentrymodifications;
    }
}
