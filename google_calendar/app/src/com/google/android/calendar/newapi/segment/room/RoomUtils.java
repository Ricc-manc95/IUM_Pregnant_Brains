// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.room;

import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.util.SparseArray;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.newapi.segment.attendee.AttendeesUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class RoomUtils
{

    private static final com.google.android.calendar.api.event.attendee.Response.ResponseStatus RESPONSE_SORT_ORDER[];
    private static final String ROOMS_SEPARATOR = Pattern.quote(", ");

    public static String addRoomsToLocation(String s, List list)
    {
        class .Lambda._cls1
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls1();

            public final boolean apply(Object obj1)
            {
                return RoomUtils.lambda$addRoomsToLocation$0$RoomUtils((Attendee)obj1);
            }


            private .Lambda._cls1()
            {
            }
        }

        Object obj;
        if (list instanceof FluentIterable)
        {
            list = (FluentIterable)list;
        } else
        {
            list = new com.google.common.collect.FluentIterable._cls1(list, list);
        }
        obj = .Lambda._cls1..instance;
        list = (Iterable)((FluentIterable) (list)).iterableDelegate.or(list);
        if (list == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        }
        list = new com.google.common.collect.Iterables._cls4(list, ((Predicate) (obj)));
        class .Lambda._cls2
            implements Function
        {

            public static final Function $instance = new .Lambda._cls2();

            public final Object apply(Object obj1)
            {
                return RoomUtils.lambda$addRoomsToLocation$1$RoomUtils((Attendee)obj1);
            }


            private .Lambda._cls2()
            {
            }
        }

        if (list instanceof FluentIterable)
        {
            list = (FluentIterable)list;
        } else
        {
            list = new com.google.common.collect.FluentIterable._cls1(list, list);
        }
        obj = .Lambda._cls2..instance;
        list = (Iterable)((FluentIterable) (list)).iterableDelegate.or(list);
        if (list == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        }
        list = new com.google.common.collect.Iterables._cls5(list, ((Function) (obj)));
        if (list instanceof FluentIterable)
        {
            list = (FluentIterable)list;
        } else
        {
            list = new com.google.common.collect.FluentIterable._cls1(list, list);
        }
        s = Platform.emptyToNull(s);
        list = FluentIterable.concatNoDefensiveCopy(new Iterable[] {
            (Iterable)((FluentIterable) (list)).iterableDelegate.or(list), Arrays.asList(new String[] {
                s
            })
        });
        s = (new Joiner(", ")).skipNulls();
        list = list.iterator();
        return s.appendTo(new StringBuilder(), list).toString();
    }

    public static List createRoomLabels(List list)
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) 
        {
            Attendee attendee = (Attendee)iterator.next();
            if (!TextUtils.isEmpty(attendee.displayName))
            {
                list = attendee.displayName;
            } else
            {
                list = attendee.attendeeDescriptor.email;
            }
            list = new SpannableString(list);
            if (attendee.response.status == com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED)
            {
                list.setSpan(new StrikethroughSpan(), 0, list.length(), 17);
            }
            arraylist.add(list);
        }
        return arraylist;
    }

    public static String createRoomsLink(Iterable iterable)
    {
        Joiner joiner = new Joiner(" OR ");
        class .Lambda._cls4
            implements Function
        {

            public static final Function $instance = new .Lambda._cls4();

            public final Object apply(Object obj)
            {
                return RoomUtils.lambda$createRoomsLink$2$RoomUtils((Attendee)obj);
            }


            private .Lambda._cls4()
            {
            }
        }

        Function function = .Lambda._cls4..instance;
        if (iterable == null)
        {
            throw new NullPointerException();
        }
        if (function == null)
        {
            throw new NullPointerException();
        } else
        {
            iterable = (new com.google.common.collect.Iterables._cls5(iterable, function)).iterator();
            iterable = joiner.appendTo(new StringBuilder(), iterable).toString();
            return (new android.net.Uri.Builder()).scheme("https").authority("campusmaps.googleplex.com").appendQueryParameter("q", iterable).build().toString();
        }
    }

    public static List getOriginalRooms(EventModifications eventmodifications)
    {
        if (eventmodifications.getOriginal() == null)
        {
            return Collections.emptyList();
        } else
        {
            return getRooms(eventmodifications.getOriginal());
        }
    }

    public static List getRooms(Event event)
    {
        event = event.getAttendees();
        class .Lambda._cls0
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls0();

            public final boolean apply(Object obj1)
            {
                return AttendeeUtils.isRoom((Attendee)obj1);
            }


            private .Lambda._cls0()
            {
            }
        }

        Object obj = .Lambda._cls0..instance;
        if (event == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        }
        event = new com.google.android.calendar.newapi.segment.attendee.AttendeesUtils.AttendeeMap(new com.google.common.collect.Iterables._cls4(event, ((Predicate) (obj))), AttendeesUtils.DEFAULT_ATTENDEE_NAME_COMPARATOR);
        obj = new ArrayList();
        com.google.android.calendar.api.event.attendee.Response.ResponseStatus aresponsestatus[] = RESPONSE_SORT_ORDER;
        int j = aresponsestatus.length;
        for (int i = 0; i < j; i++)
        {
            com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus = aresponsestatus[i];
            ((List) (obj)).addAll((Collection)((com.google.android.calendar.newapi.segment.attendee.AttendeesUtils.AttendeeMap) (event)).attendees.get(responsestatus.ordinal(), Collections.emptyList()));
        }

        return ((List) (obj));
    }

    static final boolean lambda$addRoomsToLocation$0$RoomUtils(Attendee attendee)
    {
        return attendee.response.status != com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED && !TextUtils.isEmpty(attendee.displayName);
    }

    static final String lambda$addRoomsToLocation$1$RoomUtils(Attendee attendee)
    {
        return attendee.displayName;
    }

    static final String lambda$createRoomsLink$2$RoomUtils(Attendee attendee)
    {
        attendee = attendee.displayName;
        return (new StringBuilder(String.valueOf(attendee).length() + 2)).append("(").append(attendee).append(")").toString();
    }

    public static String removeRoomsFromLocation(String s, List list)
    {
        if (TextUtils.isEmpty(s))
        {
            s = null;
        } else
        {
            list = list.iterator();
            do
            {
                if (!list.hasNext())
                {
                    break;
                }
                Object obj = (Attendee)list.next();
                if (!TextUtils.isEmpty(((Attendee) (obj)).displayName))
                {
                    obj = Pattern.quote(((Attendee) (obj)).displayName);
                    String s1 = ROOMS_SEPARATOR;
                    String s2 = ROOMS_SEPARATOR;
                    String s3 = ROOMS_SEPARATOR;
                    s = s.replaceAll((new StringBuilder(String.valueOf(s1).length() + 17 + String.valueOf(obj).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(obj).length())).append("((^|(?<=").append(s1).append("))").append(((String) (obj))).append(s2).append("|(^|").append(s3).append(")").append(((String) (obj))).append("$)").toString(), "");
                }
            } while (true);
            list = s.trim();
            s = list;
            if (TextUtils.isEmpty(list))
            {
                return null;
            }
        }
        return s;
    }

    public static boolean updateEventModificationsFromRoomBookingActivityResult(EventModifications eventmodifications, Intent intent)
    {
        ArrayList arraylist = intent.getStringArrayListExtra("intent_key_room_emails");
        intent = intent.getStringArrayListExtra("intent_key_room_names");
        ArrayList arraylist1 = new ArrayList();
        int i = 0;
        while (i < arraylist.size()) 
        {
            Object obj = (String)arraylist.get(i);
            String s = (String)intent.get(i);
            obj = new AttendeeDescriptor(((String) (obj)));
            byte byte0;
            if (RemoteFeatureConfig.THIRD_PARTY_RESOURCE_SUPPORT.enabled())
            {
                byte0 = 3;
            } else
            {
                byte0 = 1;
            }
            arraylist1.add(new Attendee(((AttendeeDescriptor) (obj)), s, 1, byte0, new Response(new com.google.android.calendar.api.event.attendee.Response.Builder())));
            i++;
        }
        class .Lambda._cls3
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls3();

            public final boolean apply(Object obj1)
            {
                return AttendeeUtils.isRoom((Attendee)obj1);
            }


            private .Lambda._cls3()
            {
            }
        }

        return AttendeesUtils.setAttendeeList(eventmodifications, arraylist1, .Lambda._cls3..instance);
    }

    static 
    {
        RESPONSE_SORT_ORDER = (new com.google.android.calendar.api.event.attendee.Response.ResponseStatus[] {
            com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED, com.google.android.calendar.api.event.attendee.Response.ResponseStatus.TENTATIVE, com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION, com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED
        });
    }
}
