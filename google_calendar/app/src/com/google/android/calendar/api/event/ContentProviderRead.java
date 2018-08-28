// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.util.Pair;
import com.google.android.apps.calendar.util.database.Cursors;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListApiStoreImpl;
import java.io.IOException;

// Referenced classes of package com.google.android.calendar.api.event:
//            CpEventKey, LoadDetailsConstants, Event

final class ContentProviderRead
{

    private final CalendarListApiStoreImpl calendarListStore = new CalendarListApiStoreImpl();

    ContentProviderRead()
    {
    }

    final Event readEvent(CpEventKey cpeventkey)
        throws IOException
    {
        class .Lambda._cls0
            implements com.google.android.apps.calendar.util.database.Cursors.Extractor
        {

            private final ContentProviderRead arg$1;
            private final Pair arg$2;

            public final Object extract(Cursor cursor)
            {
                ContentProviderRead contentproviderread = arg$1;
                String as[] = (String[])arg$2.second;
                CpEventDescriptor cpeventdescriptor = EventStoreUtils.cursorToEventDescriptor(cursor, as);
                com.google.android.calendar.api.calendarlist.CalendarDescriptor calendardescriptor = EventStoreUtils.cursorToCalendarDescriptor(cursor);
                return EventStoreUtils.cursorToEntity(cpeventdescriptor, calendardescriptor, CalendarListApiStoreImpl.read(calendardescriptor), cursor, as);
            }

            .Lambda._cls0(Pair pair)
            {
                arg$1 = ContentProviderRead.this;
                arg$2 = pair;
            }
        }

        if (cpeventkey.hasStartMillis())
        {
            ContentResolver contentresolver = CalendarApi.getApiContentResolver();
            if (!cpeventkey.hasStartMillis())
            {
                throw new IllegalStateException();
            }
            android.net.Uri.Builder builder = android.provider.CalendarContract.Instances.CONTENT_URI.buildUpon();
            ContentUris.appendId(builder, cpeventkey.startMillis());
            ContentUris.appendId(builder, cpeventkey.startMillis());
            cpeventkey = new Pair(contentresolver.query(builder.build(), LoadDetailsConstants.INSTANCE_PROJECTION, "event_id = ? AND begin = ?", new String[] {
                String.valueOf(cpeventkey.localId()), String.valueOf(cpeventkey.startMillis())
            }, null), LoadDetailsConstants.INSTANCE_PROJECTION);
        } else
        {
            cpeventkey = new Pair(CalendarApi.getApiContentResolver().query(ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, cpeventkey.localId()), LoadDetailsConstants.EVENT_PROJECTION, null, null, null), LoadDetailsConstants.EVENT_PROJECTION);
        }
        return (Event)Cursors.extractSingleEntry((Cursor)((Pair) (cpeventkey)).first, new .Lambda._cls0(cpeventkey), "Event");
    }
}
