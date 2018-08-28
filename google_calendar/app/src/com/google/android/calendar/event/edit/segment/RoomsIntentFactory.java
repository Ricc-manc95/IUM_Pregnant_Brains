// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.edit.segment;

import android.text.TextUtils;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.newapi.segment.room.RoomUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class RoomsIntentFactory
{

    public static ArrayList getSelectedApiRoomEmails(Event event)
    {
        ArrayList arraylist = new ArrayList();
        event = RoomUtils.getRooms(event).iterator();
        do
        {
            if (!event.hasNext())
            {
                break;
            }
            String s = ((Attendee)event.next()).attendeeDescriptor.email;
            if (!TextUtils.isEmpty(s))
            {
                arraylist.add(s);
            }
        } while (true);
        return arraylist;
    }
}
