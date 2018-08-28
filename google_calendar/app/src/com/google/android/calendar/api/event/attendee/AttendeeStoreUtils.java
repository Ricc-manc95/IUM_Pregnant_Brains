// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attendee;

import android.content.ContentProviderOperation;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.common.ContentProviderOperator;
import com.google.android.calendar.api.event.Event;
import com.google.calendar.v2a.android.util.provider.Batch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.api.event.attendee:
//            Attendee, AttendeeDescriptor, Response, ContactId

public final class AttendeeStoreUtils
{

    public static void createInsertOperations(Event event, ContentProviderOperator contentprovideroperator, long l, boolean flag)
    {
        ImmutableList immutablelist;
        int j;
        int k;
        if (contentprovideroperator == null)
        {
            throw new NullPointerException();
        }
        if (event == null)
        {
            throw new NullPointerException();
        }
        immutablelist = (ImmutableList)event.getAttendees();
        k = immutablelist.size();
        j = 0;
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
_L9:
        if (j >= k) goto _L2; else goto _L1
_L1:
        int i;
        Attendee attendee = (Attendee)immutablelist.get(j);
        Object obj = attendee.attendeeDescriptor;
        if (!flag && ((AttendeeDescriptor) (obj)).contactId != null)
        {
            throw new IllegalStateException("Cannot insert attendees with a contactId.");
        }
        android.content.ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(android.provider.CalendarContract.Attendees.CONTENT_URI);
        Object obj1;
        int i1;
        if (flag)
        {
            builder.withValue("event_id", Long.valueOf(l));
        } else
        {
            builder.withValueBackReference("event_id", (int)l);
        }
        obj1 = ((AttendeeDescriptor) (obj)).email;
        if (event.getDescriptor() == null || event.getCalendar() == null)
        {
            obj = obj1;
        } else
        {
            obj = event.getCalendar().calendarId;
            if (obj != null && ((String) (obj)).equalsIgnoreCase(((String) (obj1))))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                obj = obj1;
            }
        }
        obj = builder.withValue("attendeeEmail", obj).withValue("attendeeName", attendee.displayName);
        i = attendee.type;
        i1 = attendee.role;
        if (i1 == 2)
        {
            if (i != 1)
            {
                throw new IllegalArgumentException("role=OPTIONAL requires type=INDIVIDUAL");
            }
            i = 2;
        } else
        if (i == 3)
        {
            if (i1 != 1)
            {
                throw new IllegalArgumentException("type=RESOURCE requires role=REQUIRED");
            }
            i = 3;
        } else
        {
            i = 1;
        }
        obj = ((android.content.ContentProviderOperation.Builder) (obj)).withValue("attendeeType", Integer.valueOf(i));
        attendee.response.status.ordinal();
        JVM INSTR tableswitch 1 3: default 324
    //                   1 441
    //                   2 447
    //                   3 453;
           goto _L3 _L4 _L5 _L6
_L3:
        i = 0;
_L7:
        ((android.content.ContentProviderOperation.Builder) (obj)).withValue("attendeeStatus", Integer.valueOf(i)).withValue("attendeeRelationship", Integer.valueOf(attendee.relationship)).withValue("attendeeIdentity", null).withValue("attendeeIdNamespace", null);
        obj = builder.build();
        obj1 = contentprovideroperator.batch;
        ((Batch) (obj1)).operations.add(obj);
        ((Batch) (obj1)).operations.size();
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 1;
        continue; /* Loop/switch isn't completed */
_L5:
        i = 4;
        continue; /* Loop/switch isn't completed */
_L6:
        i = 2;
        if (true) goto _L7; else goto _L2
_L2:
        return;
        if (true) goto _L9; else goto _L8
_L8:
    }

    public static boolean descriptorsEquivalent(AttendeeDescriptor attendeedescriptor, AttendeeDescriptor attendeedescriptor1)
    {
label0:
        {
            boolean flag1 = false;
            if (attendeedescriptor.contactId == null || !attendeedescriptor.contactId.equals(attendeedescriptor1.contactId))
            {
                attendeedescriptor = attendeedescriptor.email;
                attendeedescriptor1 = attendeedescriptor1.email;
                boolean flag;
                if (attendeedescriptor != null && attendeedescriptor.equalsIgnoreCase(attendeedescriptor1))
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
            flag1 = true;
        }
        return flag1;
    }

    public static AttendeeDescriptor toApiAttendeeDescriptor(String s, String s1, String s2)
    {
        if (!s1.isEmpty() && !s2.isEmpty())
        {
            return new AttendeeDescriptor(s, new ContactId(s1, s2));
        }
        if (s.endsWith("@profile.calendar.google.com"))
        {
            return new AttendeeDescriptor(s, new ContactId(s.substring(0, s.indexOf("@profile.calendar.google.com")), "com.google"));
        } else
        {
            return new AttendeeDescriptor(s);
        }
    }
}
