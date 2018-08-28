// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.notification;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.database.Cursor;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.database.Cursors;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.common.ContentProviderOperator;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.LoadDetailsConstants;
import com.google.calendar.v2a.android.util.provider.Batch;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.api.event.notification:
//            Notification, NotificationsTimelyStoreUtils

public class NotificationsStoreUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/api/event/notification/NotificationsStoreUtils);

    public NotificationsStoreUtils()
    {
    }

    public static void addDeleteNotificationOperations(long l, ContentProviderOperator contentprovideroperator)
    {
        if (contentprovideroperator == null)
        {
            throw new NullPointerException();
        } else
        {
            ContentProviderOperation contentprovideroperation = ContentProviderOperation.newDelete(android.provider.CalendarContract.Reminders.CONTENT_URI).withSelection("event_id=?", new String[] {
                String.valueOf(l)
            }).build();
            contentprovideroperator = contentprovideroperator.batch;
            ((Batch) (contentprovideroperator)).operations.add(contentprovideroperation);
            ((Batch) (contentprovideroperator)).operations.size();
            return;
        }
    }

    public static void addInsertNotificationOperationsForEventCreate(int i, EventModifications eventmodifications, ContentProviderOperator contentprovideroperator)
    {
        if (contentprovideroperator == null)
        {
            throw new NullPointerException();
        }
        if (eventmodifications == null)
        {
            throw new NullPointerException();
        }
        Batch batch;
        for (eventmodifications = getNotificationsToStore(eventmodifications).iterator(); eventmodifications.hasNext(); batch.operations.size())
        {
            Object obj = (Notification)eventmodifications.next();
            obj = ContentProviderOperation.newInsert(android.provider.CalendarContract.Reminders.CONTENT_URI).withValueBackReference("event_id", i).withValue("method", Integer.valueOf(apiMethodToStoreMethod(((Notification) (obj)).method))).withValue("minutes", Integer.valueOf(((Notification) (obj)).minutesBefore)).build();
            batch = contentprovideroperator.batch;
            batch.operations.add(obj);
        }

    }

    public static void addInsertNotificationOperationsForEventUpdate(long l, EventModifications eventmodifications, ContentProviderOperator contentprovideroperator)
    {
        if (contentprovideroperator == null)
        {
            throw new NullPointerException();
        }
        if (eventmodifications == null)
        {
            throw new NullPointerException();
        }
        Batch batch;
        for (eventmodifications = getNotificationsToStore(eventmodifications).iterator(); eventmodifications.hasNext(); batch.operations.size())
        {
            Object obj = (Notification)eventmodifications.next();
            obj = ContentProviderOperation.newInsert(android.provider.CalendarContract.Reminders.CONTENT_URI).withValue("event_id", Long.valueOf(l)).withValue("method", Integer.valueOf(apiMethodToStoreMethod(((Notification) (obj)).method))).withValue("minutes", Integer.valueOf(((Notification) (obj)).minutesBefore)).build();
            batch = contentprovideroperator.batch;
            batch.operations.add(obj);
        }

    }

    public static int apiMethodToStoreMethod(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException((new StringBuilder(45)).append("Invalid notification method value ").append(i).toString());

        case 1: // '\001'
            return 1;

        case 2: // '\002'
            return 2;

        case 3: // '\003'
            return 3;

        case 4: // '\004'
            return 4;
        }
    }

    private static List getNotificationsToStore(EventModifications eventmodifications)
    {
        if (eventmodifications.getNotifications() != null)
        {
            return eventmodifications.getNotifications();
        } else
        {
            return Arrays.asList(NotificationsTimelyStoreUtils.loadDefaultNotifications(eventmodifications.getCalendar(), eventmodifications.isAllDayEvent()));
        }
    }

    static final Notification lambda$loadEventNotifications$0$NotificationsStoreUtils(Cursor cursor)
        throws IOException
    {
        return new Notification(storeMethodToApiMethod(cursor.getInt(2)), cursor.getInt(1));
    }

    public static Notification[] loadEventNotifications(long l)
        throws IOException
    {
        class .Lambda._cls0
            implements com.google.android.apps.calendar.util.database.Cursors.Extractor
        {

            public static final com.google.android.apps.calendar.util.database.Cursors.Extractor $instance = new .Lambda._cls0();

            public final Object extract(Cursor cursor)
            {
                return NotificationsStoreUtils.lambda$loadEventNotifications$0$NotificationsStoreUtils(cursor);
            }


            private .Lambda._cls0()
            {
            }
        }

        return (Notification[])ImmutableSet.copyOf(Cursors.apply(CalendarApi.getApiContentResolver().query(android.provider.CalendarContract.Reminders.CONTENT_URI, LoadDetailsConstants.REMINDERS_PROJECTION, "event_id=?", new String[] {
            String.valueOf(l)
        }, "minutes ASC, method ASC"), .Lambda._cls0..instance, "Notification")).toArray(new Notification[0]);
    }

    public static int storeMethodToApiMethod(int i)
    {
        try
        {
            i = storeMethodToApiMethodOrThrow(i);
        }
        catch (IllegalStateException illegalstateexception)
        {
            LogUtils.w(TAG, illegalstateexception, "Defaulted to alert for invalid reminder method", new Object[0]);
            return 1;
        }
        return i;
    }

    private static int storeMethodToApiMethodOrThrow(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException((new StringBuilder(45)).append("Invalid notification method value ").append(i).toString());

        case 0: // '\0'
        case 1: // '\001'
            return 1;

        case 2: // '\002'
            return 2;

        case 3: // '\003'
            return 3;

        case 4: // '\004'
            return 4;
        }
    }

    public static List storeMethodsToApiMethods(int ai[])
    {
        HashSet hashset = new HashSet(ai.length);
        int i = 0;
        while (i < ai.length) 
        {
            try
            {
                hashset.add(Integer.valueOf(storeMethodToApiMethodOrThrow(ai[i])));
            }
            catch (IllegalStateException illegalstateexception)
            {
                LogUtils.w(TAG, illegalstateexception, "Skipped invalid reminder method", new Object[0]);
            }
            i++;
        }
        return new ArrayList(hashset);
    }

}
