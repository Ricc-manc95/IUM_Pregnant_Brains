// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attendee;

import android.database.Cursor;
import com.google.android.calendar.api.converters.ResponseStatusConverter;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.TimeChangeProposal;
import com.google.common.base.Platform;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.api.event.attendee:
//            AttendeeStoreUtils, Attendee, Response

public final class arg._cls1
    implements com.google.android.apps.calendar.util.database.arg._cls1
{

    private final Map arg$1;

    public final Object extract(Cursor cursor)
    {
        byte byte1 = 3;
        byte byte0 = 2;
        Object obj = arg$1;
        AttendeeDescriptor attendeedescriptor = AttendeeStoreUtils.toApiAttendeeDescriptor(Platform.nullToEmpty(cursor.getString(1)), Platform.nullToEmpty(cursor.getString(4)), Platform.nullToEmpty(cursor.getString(5)));
        String s = cursor.getString(0);
        int j = cursor.getInt(6);
        int i = cursor.getInt(2);
        int k = cursor.getInt(3);
        cursor = new deeDescriptor();
        cursor.deeDescriptor = ResponseStatusConverter.providerToApi(k);
        obj = (EventAttendee)((Map) (obj)).get(attendeedescriptor);
        if (obj != null)
        {
            cursor.oApi = Platform.nullToEmpty(((EventAttendee) (obj)).comment);
            if (((EventAttendee) (obj)).timeChangeProposal != null)
            {
                cursor.ime(((EventAttendee) (obj)).timeChangeProposal.startTimeMillis, ((EventAttendee) (obj)).timeChangeProposal.endTimeMillis);
            }
        }
        if (j != 2)
        {
            byte0 = 1;
        }
        if (j != 3)
        {
            byte1 = 1;
        }
        return new Attendee(attendeedescriptor, s, byte0, byte1, i, new Response(cursor));
    }

    public Q(Map map)
    {
        arg$1 = map;
    }
}
