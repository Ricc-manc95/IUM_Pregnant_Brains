// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.net;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.google.android.apps.calendar.proposenewtime.state.Attendee;
import com.google.android.calendar.timely.net.BaseClientFragment;
import com.google.android.calendar.timely.net.TimestampUtils;
import com.google.android.calendar.timely.net.findatime.FindTimeRequestExecutor;
import com.google.android.calendar.timely.net.findatime.utils.FindTimeRequestUtils;
import com.google.android.calendar.timely.net.grpc.environment.GrpcEnvironments;
import com.google.calendar.suggest.v2.AttendeeEvents;
import com.google.calendar.suggest.v2.CalendarEvent;
import com.google.calendar.suggest.v2.Event;
import com.google.calendar.suggest.v2.OmittedAttendee;
import com.google.calendar.suggest.v2.SuggestTimeRequest;
import com.google.calendar.suggest.v2.SuggestTimeResponse;
import com.google.calendar.suggest.v2.TimeSettings;
import com.google.calendar.suggest.v2.UserContext;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableMap;
import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.PrimitiveNonBoxingCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.net:
//            Request

public final class ProposeNewTimeRendezvousClient extends BaseClientFragment
{

    private static final Duration RENDEZVOUS_EVENT_DURATION_PLACEHOLDER = TimestampUtils.durationFromMillis(1000L);
    private Context context;
    private String locale;
    private FindTimeRequestExecutor requestExecutor;

    public ProposeNewTimeRendezvousClient()
    {
    }

    static final boolean lambda$buildAttendeeMap$0$ProposeNewTimeRendezvousClient(String s, Event event)
    {
        return !s.equals(event.eventId_);
    }

    public final void onAttach(Context context1)
    {
        super.onAttach(context1);
        context = context1.getApplicationContext();
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        locale = getArguments().getString("language", null);
        if (requestExecutor == null)
        {
            bundle = getArguments().getString("account_email", null);
            requestExecutor = new FindTimeRequestExecutor(context, bundle, GrpcEnvironments.DEFAULT_TARGET_ENVIRONMENT);
        }
    }

    protected final Object retrieveData(Object obj)
        throws Exception
    {
        obj = (Request)obj;
        Object obj1 = requestExecutor;
        Object obj4 = ((Request) (obj)).getEventId();
        Object obj3 = ((Request) (obj)).getCalendarId();
        Object obj5 = ((Request) (obj)).getAttendees();
        long l1 = ((Request) (obj)).getStartTimeMillis();
        long l2 = ((Request) (obj)).getEndTimeMillis();
        Object obj7 = ((Request) (obj)).getTimeZone();
        Object obj2 = (com.google.calendar.suggest.v2.SuggestTimeRequest.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)SuggestTimeRequest.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        ((SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj2)).instance).maxResults_ = 1;
        Object obj6 = (com.google.calendar.suggest.v2.UserContext.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)UserContext.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        obj7 = ((TimeZone) (obj7)).getID();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj6)).copyOnWrite();
        Object obj11 = (UserContext)((com.google.calendar.suggest.v2.UserContext.Builder) (obj6)).instance;
        if (obj7 == null)
        {
            throw new NullPointerException();
        }
        obj11.timezone_ = ((String) (obj7));
        if (locale != null)
        {
            obj7 = locale;
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj6)).copyOnWrite();
            obj11 = (UserContext)((com.google.calendar.suggest.v2.UserContext.Builder) (obj6)).instance;
            if (obj7 == null)
            {
                throw new NullPointerException();
            }
            obj11.locale_ = ((String) (obj7));
        }
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        ((SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj2)).instance).userContext_ = (UserContext)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj6)).build();
        obj6 = (com.google.calendar.suggest.v2.TimeSettings.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)TimeSettings.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        obj7 = TimestampUtils.timestampFromMillis(l1);
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj6)).copyOnWrite();
        obj11 = (TimeSettings)((com.google.calendar.suggest.v2.TimeSettings.Builder) (obj6)).instance;
        if (obj7 == null)
        {
            throw new NullPointerException();
        }
        obj11.windowStartTime_ = ((com.google.protobuf.Timestamp) (obj7));
        obj7 = TimestampUtils.timestampFromMillis(l2);
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj6)).copyOnWrite();
        obj11 = (TimeSettings)((com.google.calendar.suggest.v2.TimeSettings.Builder) (obj6)).instance;
        if (obj7 == null)
        {
            throw new NullPointerException();
        }
        obj11.windowEndTime_ = ((com.google.protobuf.Timestamp) (obj7));
        obj7 = RENDEZVOUS_EVENT_DURATION_PLACEHOLDER;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj6)).copyOnWrite();
        obj11 = (TimeSettings)((com.google.calendar.suggest.v2.TimeSettings.Builder) (obj6)).instance;
        if (obj7 == null)
        {
            throw new NullPointerException();
        }
        obj11.duration_ = ((Duration) (obj7));
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        ((SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj2)).instance).timeSettings_ = (TimeSettings)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj6)).build();
        obj6 = new ArrayList();
        com.google.calendar.suggest.v2.Attendee.Builder builder;
        for (obj5 = ((List) (obj5)).iterator(); ((Iterator) (obj5)).hasNext(); ((List) (obj6)).add((com.google.calendar.suggest.v2.Attendee)(GeneratedMessageLite)builder.build()))
        {
            Object obj12 = (Attendee)((Iterator) (obj5)).next();
            builder = (com.google.calendar.suggest.v2.Attendee.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.calendar.suggest.v2.Attendee.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            obj12 = ((Attendee) (obj12)).getEmail();
            builder.copyOnWrite();
            com.google.calendar.suggest.v2.Attendee attendee = (com.google.calendar.suggest.v2.Attendee)builder.instance;
            if (obj12 == null)
            {
                throw new NullPointerException();
            }
            attendee.email_ = ((String) (obj12));
        }

        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        obj5 = (SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj2)).instance;
        if (!((SuggestTimeRequest) (obj5)).attendees_.isModifiable())
        {
            obj5.attendees_ = GeneratedMessageLite.mutableCopy(((SuggestTimeRequest) (obj5)).attendees_);
        }
        obj5 = ((SuggestTimeRequest) (obj5)).attendees_;
        Internal.checkNotNull(obj6);
        if (obj6 instanceof LazyStringList)
        {
            List list = ((LazyStringList)obj6).getUnderlyingElements();
            obj6 = (LazyStringList)obj5;
            int j1 = ((List) (obj5)).size();
            for (obj5 = list.iterator(); ((Iterator) (obj5)).hasNext();)
            {
                Object obj8 = ((Iterator) (obj5)).next();
                if (obj8 == null)
                {
                    int i = ((LazyStringList) (obj6)).size();
                    obj = (new StringBuilder(37)).append("Element at index ").append(i - j1).append(" is null.").toString();
                    for (int j = ((LazyStringList) (obj6)).size() - 1; j >= j1; j--)
                    {
                        ((LazyStringList) (obj6)).remove(j);
                    }

                    throw new NullPointerException(((String) (obj)));
                }
                if (obj8 instanceof ByteString)
                {
                    ((LazyStringList) (obj6)).add((ByteString)obj8);
                } else
                {
                    ((LazyStringList) (obj6)).add((String)obj8);
                }
            }

        } else
        if (obj6 instanceof PrimitiveNonBoxingCollection)
        {
            ((List) (obj5)).addAll((Collection)obj6);
        } else
        {
            if ((obj5 instanceof ArrayList) && (obj6 instanceof Collection))
            {
                ArrayList arraylist = (ArrayList)obj5;
                int k = ((List) (obj5)).size();
                arraylist.ensureCapacity(((Collection)obj6).size() + k);
            }
            int k1 = ((List) (obj5)).size();
            obj6 = ((Iterable) (obj6)).iterator();
            while (((Iterator) (obj6)).hasNext()) 
            {
                Object obj9 = ((Iterator) (obj6)).next();
                if (obj9 == null)
                {
                    int l = ((List) (obj5)).size();
                    obj = (new StringBuilder(37)).append("Element at index ").append(l - k1).append(" is null.").toString();
                    for (int i1 = ((List) (obj5)).size() - 1; i1 >= k1; i1--)
                    {
                        ((List) (obj5)).remove(i1);
                    }

                    throw new NullPointerException(((String) (obj)));
                }
                ((List) (obj5)).add(obj9);
            }
        }
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        ((SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj2)).instance).includeAttendeesEvents_ = true;
        if (obj4 != null && obj3 != null)
        {
            obj5 = (com.google.calendar.suggest.v2.CalendarEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)CalendarEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj5)).copyOnWrite();
            obj6 = (CalendarEvent)((com.google.calendar.suggest.v2.CalendarEvent.Builder) (obj5)).instance;
            if (obj4 == null)
            {
                throw new NullPointerException();
            }
            obj6.eventId_ = ((String) (obj4));
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj5)).copyOnWrite();
            obj4 = (CalendarEvent)((com.google.calendar.suggest.v2.CalendarEvent.Builder) (obj5)).instance;
            if (obj3 == null)
            {
                throw new NullPointerException();
            }
            obj4.calendarId_ = ((String) (obj3));
            obj3 = (CalendarEvent)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj5)).build();
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
            obj4 = (SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj2)).instance;
            if (obj3 == null)
            {
                throw new NullPointerException();
            }
            obj4.existingEvent_ = ((CalendarEvent) (obj3));
        }
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        ((SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj2)).instance).expandGroupAttendees_ = true;
        obj2 = ((FindTimeRequestExecutor) (obj1)).suggestTime((SuggestTimeRequest)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).build());
        if (obj2 == null)
        {
            return RegularImmutableMap.EMPTY;
        }
        obj3 = ((Request) (obj)).getTimeZone();
        obj1 = ((SuggestTimeResponse) (obj2)).attendeeEvents_;
        obj4 = ((SuggestTimeResponse) (obj2)).omittedAttendees_;
        obj5 = ((Request) (obj)).getEventId();
        obj2 = new com.google.common.collect.ImmutableMap.Builder();
        Object obj10;
        for (Iterator iterator = ((List) (obj1)).iterator(); iterator.hasNext(); ((com.google.common.collect.ImmutableMap.Builder) (obj2)).put(obj1, new Present(obj)))
        {
            obj10 = (AttendeeEvents)iterator.next();
            obj1 = ((AttendeeEvents) (obj10)).events_;
            obj = obj1;
            if (obj5 != null)
            {
                class .Lambda._cls0
                    implements Predicate
                {

                    private final String arg$1;

                    public final boolean apply(Object obj13)
                    {
                        return ProposeNewTimeRendezvousClient.lambda$buildAttendeeMap$0$ProposeNewTimeRendezvousClient(arg$1, (Event)obj13);
                    }

            .Lambda._cls0(String s)
            {
                arg$1 = s;
            }
                }

                if (obj1 instanceof FluentIterable)
                {
                    obj = (FluentIterable)obj1;
                } else
                {
                    obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj1)), ((Iterable) (obj1)));
                }
                obj1 = new .Lambda._cls0(((String) (obj5)));
                obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                obj = new com.google.common.collect.Iterables._cls4(((Iterable) (obj)), ((Predicate) (obj1)));
                if (obj instanceof FluentIterable)
                {
                    obj = (FluentIterable)obj;
                } else
                {
                    obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
                }
                obj = ImmutableList.copyOf((Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj));
            }
            if (((AttendeeEvents) (obj10)).attendee_ == null)
            {
                obj1 = com.google.calendar.suggest.v2.Attendee.DEFAULT_INSTANCE;
            } else
            {
                obj1 = ((AttendeeEvents) (obj10)).attendee_;
            }
            obj10 = context;
            obj1 = ((com.google.calendar.suggest.v2.Attendee) (obj1)).email_.toLowerCase(((Context) (obj10)).getResources().getConfiguration().locale);
            obj = FindTimeRequestUtils.convertToTimelineAttendeeEventList(context, ((List) (obj)), ((TimeZone) (obj3)));
            if (obj == null)
            {
                throw new NullPointerException();
            }
        }

        obj3 = ((List) (obj4)).iterator();
        do
        {
            if (!((Iterator) (obj3)).hasNext())
            {
                break;
            }
            OmittedAttendee omittedattendee = (OmittedAttendee)((Iterator) (obj3)).next();
            com.google.calendar.suggest.v2.OmittedAttendee.Reason reason = com.google.calendar.suggest.v2.OmittedAttendee.Reason.forNumber(omittedattendee.reason_);
            obj = reason;
            if (reason == null)
            {
                obj = com.google.calendar.suggest.v2.OmittedAttendee.Reason.UNRECOGNIZED;
            }
            if (obj != com.google.calendar.suggest.v2.OmittedAttendee.Reason.IGNORED)
            {
                Context context1;
                if (omittedattendee.attendee_ == null)
                {
                    obj = com.google.calendar.suggest.v2.Attendee.DEFAULT_INSTANCE;
                } else
                {
                    obj = omittedattendee.attendee_;
                }
                context1 = context;
                ((com.google.common.collect.ImmutableMap.Builder) (obj2)).put(((com.google.calendar.suggest.v2.Attendee) (obj)).email_.toLowerCase(context1.getResources().getConfiguration().locale), Absent.INSTANCE);
            }
        } while (true);
        return ((com.google.common.collect.ImmutableMap.Builder) (obj2)).build();
    }

}
