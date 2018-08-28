// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.api.util.attendee;

import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.ContactId;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import java.util.Iterator;

public final class AttendeeUtils
{

    public static Attendee getCurrentAttendee(Event event)
    {
        Object obj;
        Iterator iterator;
        obj = event.getCalendar().calendarId;
        event = event.getAttendees();
        class .Lambda._cls1
            implements Predicate
        {

            private final String arg$1;

            public final boolean apply(Object obj1)
            {
                String s = arg$1;
                obj1 = ((Attendee)obj1).attendeeDescriptor.email;
                return obj1 != null && ((String) (obj1)).equalsIgnoreCase(s);
            }

            public .Lambda._cls1(String s)
            {
                arg$1 = s;
            }
        }

        obj = new .Lambda._cls1(((String) (obj)));
        iterator = event.iterator();
        if (iterator == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        }
_L4:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        event = ((Event) (iterator.next()));
        if (!((Predicate) (obj)).apply(event)) goto _L4; else goto _L3
_L3:
        return (Attendee)event;
_L2:
        event = null;
        if (true) goto _L3; else goto _L5
_L5:
    }

    public static boolean hasGuests(Event event)
    {
label0:
        {
            if (event.isHabitInstance())
            {
                break label0;
            }
            if (!event.isAttendeesOmitted())
            {
                com.google.common.collect.ImmutableList immutablelist = event.getAttendees();
                class .Lambda._cls0
                    implements Predicate
                {

                    private final Event arg$1;

                    public final boolean apply(Object obj)
                    {
                        Event event1 = arg$1;
                        obj = (Attendee)obj;
                        return AttendeeUtils.isPerson(((Attendee) (obj))) && !AttendeeUtils.isSameAttendee(event1.getOrganizer(), ((Attendee) (obj)).attendeeDescriptor);
                    }

            .Lambda._cls0(Event event)
            {
                arg$1 = event;
            }
                }

                event = new .Lambda._cls0(event);
                if (Iterators.indexOf(immutablelist.iterator(), event) == -1)
                {
                    break label0;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isOrganizerAndListed(Event event)
    {
        Object obj;
        Object obj1 = event.getCalendar().calendarId;
        obj = event.getAttendees();
        obj1 = new .Lambda._cls1(((String) (obj1)));
        Iterator iterator = ((Iterable) (obj)).iterator();
        if (iterator == null)
        {
            throw new NullPointerException();
        }
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_110;
            }
            obj = iterator.next();
        } while (!((Predicate) (obj1)).apply(obj));
_L1:
        obj = (Attendee)obj;
        return obj != null && isSameAttendee(event.getOrganizer(), ((Attendee) (obj)).attendeeDescriptor);
        obj = null;
          goto _L1
    }

    public static boolean isOrganizerCopy(Event event)
    {
        Object obj = event.getCalendar();
        if (obj != null)
        {
            obj = ((CalendarDescriptor) (obj)).calendarId;
            event = event.getOrganizer().email;
            boolean flag;
            if (obj != null && ((String) (obj)).equalsIgnoreCase(event))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isPerson(Attendee attendee)
    {
        if (attendee.type == 1)
        {
            attendee = attendee.attendeeDescriptor.email;
            boolean flag;
            if (attendee != null && attendee.endsWith("@resource.calendar.google.com"))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isRoom(Attendee attendee)
    {
label0:
        {
            boolean flag1 = false;
            if (attendee.type != 3)
            {
                attendee = attendee.attendeeDescriptor.email;
                boolean flag;
                if (attendee != null && attendee.endsWith("@resource.calendar.google.com"))
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

    public static boolean isSameAttendee(AttendeeDescriptor attendeedescriptor, AttendeeDescriptor attendeedescriptor1)
    {
        if (attendeedescriptor != null && attendeedescriptor1 != null)
        {
            if (attendeedescriptor.contactId != null && attendeedescriptor1.contactId != null)
            {
                return attendeedescriptor.contactId.equals(attendeedescriptor1.contactId);
            }
            if (attendeedescriptor.email != null && attendeedescriptor1.email != null)
            {
                attendeedescriptor = attendeedescriptor.email;
                attendeedescriptor1 = attendeedescriptor1.email;
                if (attendeedescriptor != null && attendeedescriptor.equalsIgnoreCase(attendeedescriptor1))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
