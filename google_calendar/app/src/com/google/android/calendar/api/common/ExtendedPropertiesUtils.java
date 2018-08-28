// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.common;

import android.accounts.Account;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.LoadDetailsConstants;
import com.google.common.collect.ImmutableSet;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExtendedPropertiesUtils
{

    public static final ImmutableSet EXTENDED_PROPERTIES_BLACK_LIST = ImmutableSet.of("organizer", "iCalUid", "sequenceNumber", "hangoutLink", "includeHangout", "conferenceData", new String[] {
        "secretEvent", "endTimeUnspecified", "locationExtra", "participantStatusExtra", "associatedContactsExtra", "titleContactsExtra", "attachmentsExtra", "clearDefaultReminders", "shouldCreateEvent", "cc:mark"
    });
    public static final ImmutableSet EXTENDED_PROPERTIES_WRITE_LIST = ImmutableSet.of("organizer", "iCalUid", "sequenceNumber", "private:iCalDtStamp", "includeHangout", "conferenceData", new String[] {
        "secretEvent", "endTimeUnspecified", "locationExtra", "participantStatusExtra", "associatedContactsExtra", "titleContactsExtra", "attachmentsExtra", "clearDefaultReminders", "shouldCreateEvent"
    });
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/api/common/ExtendedPropertiesUtils);

    private ExtendedPropertiesUtils()
    {
    }

    public static Uri getExtendedPropertyUri(Account account)
    {
        return android.provider.CalendarContract.ExtendedProperties.CONTENT_URI.buildUpon().appendQueryParameter("caller_is_syncadapter", "true").appendQueryParameter("account_name", account.name).appendQueryParameter("account_type", account.type).build();
    }

    public static Map loadExtendedProperties(long l)
    {
        Object obj1;
        Cursor cursor;
        obj1 = null;
        cursor = CalendarApi.getApiContentResolver().query(android.provider.CalendarContract.ExtendedProperties.CONTENT_URI, LoadDetailsConstants.EXTENDED_PROPERTIES_PROJECTION, "event_id=?", new String[] {
            Long.toString(l)
        }, null);
        if (cursor != null) goto _L2; else goto _L1
_L1:
        Object obj = Collections.emptyMap();
        obj1 = obj;
        obj = obj1;
        if (cursor != null)
        {
            cursor.close();
            obj = obj1;
        }
_L5:
        return ((Map) (obj));
_L2:
        HashMap hashmap;
        hashmap = new HashMap();
        for (; cursor.moveToNext(); hashmap.put(cursor.getString(0), cursor.getString(1))) { }
          goto _L3
        obj1;
        throw obj1;
        obj;
_L6:
        if (cursor != null)
        {
            if (obj1 != null)
            {
                try
                {
                    cursor.close();
                }
                catch (Throwable throwable)
                {
                    ThrowableExtension.STRATEGY.addSuppressed(((Throwable) (obj1)), throwable);
                }
            } else
            {
                cursor.close();
            }
        }
        throw obj;
_L3:
        obj = hashmap;
        if (cursor == null) goto _L5; else goto _L4
_L4:
        cursor.close();
        return hashmap;
        obj;
          goto _L6
    }

}
