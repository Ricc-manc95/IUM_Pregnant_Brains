// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee;

import android.app.Activity;
import android.text.TextUtils;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.AttendeeModifications;
import com.google.android.calendar.api.event.attendee.AttendeePermissions;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.avatar.ContactInfo;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class AttendeesUtils
{

    public static final Comparator DEFAULT_ATTENDEE_NAME_COMPARATOR;

    public static boolean canRespond(EventPermissions eventpermissions, Event event)
    {
        while (eventpermissions.isReadOnly() || !eventpermissions.getAttendeePermissions().canModifyResponseStatus() || !AttendeeUtils.hasGuests(event)) 
        {
            return false;
        }
        return true;
    }

    public static Attendee createPerson(String s, String s1)
    {
        s1 = new AttendeeDescriptor(s1);
        com.google.android.calendar.api.event.attendee.Response.Builder builder = new com.google.android.calendar.api.event.attendee.Response.Builder();
        builder.status = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION;
        return new Attendee(s1, s, 1, 1, new Response(builder));
    }

    public static String getAttendeeName(Attendee attendee)
    {
        if (TextUtils.isEmpty(attendee.displayName))
        {
            return attendee.attendeeDescriptor.email;
        } else
        {
            return attendee.displayName;
        }
    }

    public static ContactInfo getContactInfo(String s, Attendee attendee)
    {
        String s1 = attendee.displayName;
        attendee = attendee.attendeeDescriptor.email;
        com.google.android.calendar.avatar.ContactInfo.Builder builder = ContactInfo.newBuilder();
        builder.sourceAccountName = s;
        builder.name = s1;
        builder.primaryEmail = attendee;
        return new ContactInfo(builder);
    }

    static final AttendeeDescriptor lambda$setAttendeeList$1$AttendeesUtils(Attendee attendee)
    {
        return attendee.attendeeDescriptor;
    }

    static final int lambda$static$0$AttendeesUtils(Attendee attendee, Attendee attendee1)
    {
        if (TextUtils.isEmpty(attendee.displayName))
        {
            attendee = attendee.attendeeDescriptor.email;
        } else
        {
            attendee = attendee.displayName;
        }
        if (TextUtils.isEmpty(attendee1.displayName))
        {
            attendee1 = attendee1.attendeeDescriptor.email;
        } else
        {
            attendee1 = attendee1.displayName;
        }
        return attendee.compareToIgnoreCase(attendee1);
    }

    public static boolean requestContactsPermissionsIfMissing(Activity activity)
    {
        if (AndroidPermissionUtils.hasContactsPermissions(activity))
        {
            return true;
        } else
        {
            AndroidPermissionUtils.requestContactsPermissions(activity);
            return false;
        }
    }

    public static boolean setAttendeeList(EventModifications eventmodifications, Collection collection, Predicate predicate)
    {
        AttendeeModifications attendeemodifications = eventmodifications.getAttendeeModifications();
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj1)
            {
                return AttendeesUtils.lambda$setAttendeeList$1$AttendeesUtils((Attendee)obj1);
            }


            private .Lambda._cls0()
            {
            }
        }

        Object obj;
        if (predicate != null)
        {
            eventmodifications = eventmodifications.getAttendees();
            if (eventmodifications == null)
            {
                throw new NullPointerException();
            }
            if (predicate == null)
            {
                throw new NullPointerException();
            }
            predicate = new com.google.common.collect.Iterables._cls4(eventmodifications, predicate);
        } else
        {
            predicate = eventmodifications.getAttendees();
        }
        if (collection instanceof FluentIterable)
        {
            eventmodifications = (FluentIterable)collection;
        } else
        {
            eventmodifications = new com.google.common.collect.FluentIterable._cls1(collection, collection);
        }
        obj = .Lambda._cls0..instance;
        eventmodifications = (Iterable)((FluentIterable) (eventmodifications)).iterableDelegate.or(eventmodifications);
        if (eventmodifications == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        }
        eventmodifications = new com.google.common.collect.Iterables._cls5(eventmodifications, ((Function) (obj)));
        boolean flag;
        if (eventmodifications instanceof FluentIterable)
        {
            eventmodifications = (FluentIterable)eventmodifications;
        } else
        {
            eventmodifications = new com.google.common.collect.FluentIterable._cls1(eventmodifications, eventmodifications);
        }
        eventmodifications = (Iterable)((FluentIterable) (eventmodifications)).iterableDelegate.or(eventmodifications);
        if (eventmodifications instanceof Collection)
        {
            eventmodifications = ImmutableSet.copyOf((Collection)eventmodifications);
        } else
        {
            eventmodifications = eventmodifications.iterator();
            if (!eventmodifications.hasNext())
            {
                eventmodifications = RegularImmutableSet.EMPTY;
            } else
            {
                obj = eventmodifications.next();
                if (!eventmodifications.hasNext())
                {
                    eventmodifications = new SingletonImmutableSet(obj);
                } else
                {
                    eventmodifications = ((com.google.common.collect.ImmutableSet.Builder)((com.google.common.collect.ImmutableSet.Builder)(new com.google.common.collect.ImmutableSet.Builder()).add(obj)).addAll(eventmodifications)).build();
                }
            }
        }
        obj = new HashSet();
        predicate = predicate.iterator();
        flag = false;
        while (predicate.hasNext()) 
        {
            Attendee attendee = (Attendee)predicate.next();
            if (!eventmodifications.contains(attendee.attendeeDescriptor))
            {
                attendeemodifications.removeAttendee(attendee);
                flag = true;
            } else
            {
                ((Set) (obj)).add(attendee.attendeeDescriptor);
            }
        }
        eventmodifications = collection.iterator();
        do
        {
            if (!eventmodifications.hasNext())
            {
                break;
            }
            collection = (Attendee)eventmodifications.next();
            if (!((Set) (obj)).contains(((Attendee) (collection)).attendeeDescriptor))
            {
                attendeemodifications.addAttendee(collection);
                flag = true;
            }
        } while (true);
        return flag;
    }

    static 
    {
        class .Lambda._cls1
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls1();

            public final int compare(Object obj, Object obj1)
            {
                return AttendeesUtils.lambda$static$0$AttendeesUtils((Attendee)obj, (Attendee)obj1);
            }


            private .Lambda._cls1()
            {
            }
        }

        DEFAULT_ATTENDEE_NAME_COMPARATOR = .Lambda._cls1..instance;
    }
}
