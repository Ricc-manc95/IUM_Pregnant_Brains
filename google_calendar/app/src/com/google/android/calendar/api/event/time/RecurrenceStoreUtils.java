// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import com.google.android.apps.calendar.util.database.Cursors;
import com.google.android.calendar.api.CalendarApi;
import java.io.IOException;

// Referenced classes of package com.google.android.calendar.api.event.time:
//            RecurrenceParser, EventKindUtils, Recurrence

public final class RecurrenceStoreUtils
{

    private static final String EXCEPTION_PARENT_PROJECTION[] = {
        "rrule", "rdate", "exrule", "exdate", "deleted"
    };

    private static Recurrence cursorToRecurrence(Cursor cursor, int i, int j, int k, int l)
        throws IOException
    {
        String s = cursor.getString(i);
        String s1 = cursor.getString(j);
        String s2 = cursor.getString(k);
        cursor = cursor.getString(l);
        try
        {
            cursor = RecurrenceParser.parseFromStoreStrings(s, s1, s2, cursor);
        }
        // Misplaced declaration of an exception variable
        catch (Cursor cursor)
        {
            throw new IOException("Error parsing recurrence properties from the store", cursor);
        }
        return cursor;
    }

    static Recurrence extractRecurrence(Cursor cursor)
        throws IOException
    {
        long l;
        switch (EventKindUtils.getEventKind(cursor))
        {
        default:
            return null;

        case 1: // '\001'
            return cursorToRecurrence(cursor, 10, 11, 12, 13);

        case 2: // '\002'
            l = cursor.getLong(23);
            break;
        }
        cursor = CalendarApi.getApiContentResolver().query(ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, l), EXCEPTION_PARENT_PROJECTION, null, null, null);
        class .Lambda._cls0
            implements com.google.android.apps.calendar.util.database.Cursors.Extractor
        {

            private final Cursor arg$1;

            public final Object extract(Cursor cursor1)
            {
                return RecurrenceStoreUtils.lambda$loadExceptionRecurrence$0$RecurrenceStoreUtils(arg$1, cursor1);
            }

            .Lambda._cls0(Cursor cursor)
            {
                arg$1 = cursor;
            }
        }

        return (Recurrence)Cursors.extractSingleEntry(cursor, new .Lambda._cls0(cursor), "Recurrence parent");
    }

    static final Recurrence lambda$loadExceptionRecurrence$0$RecurrenceStoreUtils(Cursor cursor, Cursor cursor1)
        throws IOException
    {
        if (cursor1.getInt(4) == 1)
        {
            return null;
        } else
        {
            return cursorToRecurrence(cursor, 0, 1, 2, 3);
        }
    }

}
