// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentUris;
import com.google.android.apps.calendar.util.database.Cursors;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.LoadDetailsConstants;
import java.io.IOException;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarDescriptor, CalendarKey, CalendarListEntry

public final class CalendarListApiStoreImpl
{

    public final boolean shouldFilterOutGoogleAccounts = false;

    public CalendarListApiStoreImpl()
    {
    }

    public static CalendarListEntry read(CalendarDescriptor calendardescriptor)
        throws IOException
    {
        class .Lambda._cls0
            implements com.google.android.apps.calendar.util.database.Cursors.Extractor
        {

            private final CalendarDescriptor arg$1;

            public final Object extract(Cursor cursor)
            {
                return CalendarListStoreUtils.cursorToEntity(arg$1, cursor, null, null);
            }

            .Lambda._cls0(CalendarDescriptor calendardescriptor)
            {
                arg$1 = calendardescriptor;
            }
        }

        android.net.Uri uri;
        String s;
        String as[];
        if (calendardescriptor.calendarKey == CalendarKey.NONE)
        {
            uri = android.provider.CalendarContract.Calendars.CONTENT_URI;
            s = LoadDetailsConstants.SINGLE_CALENDAR_SELECTION;
            as = new String[3];
            as[0] = calendardescriptor.calendarId;
            as[1] = calendardescriptor.account.name;
            as[2] = calendardescriptor.account.type;
        } else
        {
            uri = ContentUris.withAppendedId(android.provider.CalendarContract.Calendars.CONTENT_URI, calendardescriptor.calendarKey.getLocalId());
            as = null;
            s = null;
        }
        return (CalendarListEntry)Cursors.extractSingleEntry(CalendarApi.getApiContentResolver().query(uri, LoadDetailsConstants.CALENDARS_PROJECTION, s, as, null), new .Lambda._cls0(calendardescriptor), "CalendarListEntry");
    }
}
