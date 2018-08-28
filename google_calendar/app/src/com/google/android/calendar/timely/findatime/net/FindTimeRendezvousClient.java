// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.net;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.ContactNameResolver;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.AttendeeExplanation;
import com.google.android.calendar.timely.FindTimeAttendee;
import com.google.android.calendar.timely.FindTimeTimeframe;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineSuggestion;
import com.google.android.calendar.timely.net.BaseClientFragment;
import com.google.android.calendar.timely.net.TimestampUtils;
import com.google.android.calendar.timely.net.findatime.FindTimeRequestExecutor;
import com.google.android.calendar.timely.net.findatime.utils.FindTimeRequestUtils;
import com.google.android.calendar.timely.net.grpc.environment.GrpcEnvironments;
import com.google.calendar.suggest.v2.Attendee;
import com.google.calendar.suggest.v2.AttendeeEvents;
import com.google.calendar.suggest.v2.CalendarEvent;
import com.google.calendar.suggest.v2.Event;
import com.google.calendar.suggest.v2.Explanation;
import com.google.calendar.suggest.v2.OmittedAttendee;
import com.google.calendar.suggest.v2.SingleEventTime;
import com.google.calendar.suggest.v2.SuggestTimeRequest;
import com.google.calendar.suggest.v2.SuggestTimeResponse;
import com.google.calendar.suggest.v2.TimeSettings;
import com.google.calendar.suggest.v2.TimeSuggestion;
import com.google.calendar.suggest.v2.UserContext;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.PrimitiveNonBoxingCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.timely.findatime.net:
//            FindTimeClient, FakeFindTimeRequestExecutor

public class FindTimeRendezvousClient extends BaseClientFragment
    implements FindTimeClient
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/findatime/net/FindTimeRendezvousClient);
    private Context context;
    private String locale;
    private FindTimeRequestExecutor requestExecutor;

    public FindTimeRendezvousClient()
    {
    }

    public final void onAttach(Activity activity)
    {
        super.onAttach(activity);
        context = activity.getApplicationContext();
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        locale = getArguments().getString("language", null);
        if (requestExecutor == null)
        {
            bundle = getArguments().getString("account_email", null);
            if (getArguments().getBoolean("is_test_environment", false))
            {
                bundle = new FakeFindTimeRequestExecutor(context, bundle);
            } else
            {
                bundle = new FindTimeRequestExecutor(context, bundle, GrpcEnvironments.DEFAULT_TARGET_ENVIRONMENT);
            }
            requestExecutor = bundle;
        }
    }

    protected final Object retrieveData(Object obj)
        throws Exception
    {
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        FindTimeClient.Request request = (FindTimeClient.Request)obj;
        long l3;
        long l4;
        if (Clock.mockedTimestamp > 0L)
        {
            l3 = Clock.mockedTimestamp;
        } else
        {
            l3 = System.currentTimeMillis();
        }
        l4 = Math.max(request.timeframe.startTimeMillis, l3);
        l3 = Math.max(request.timeframe.endTimeMillis, l3);
        if (l4 >= l3)
        {
            return new FindTimeClient.Result(ImmutableList.of(), ImmutableList.of(), ImmutableList.of(), 0, null);
        }
        obj = requestExecutor;
        obj7 = request.attendees;
        int i = request.timeframe.timeframeType;
        Object obj9 = request.timeframe.date;
        long l5 = request.timeframe.durationMillis;
        obj8 = request.timezone;
        boolean flag4 = request.considerExistingRooms;
        obj4 = request.calendarEventReference;
        obj5 = request.existingEventId;
        obj3 = request.existingEventCalendarId;
        obj6 = (com.google.calendar.suggest.v2.UserContext.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)UserContext.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        obj8 = ((TimeZone) (obj8)).getID();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj6)).copyOnWrite();
        Object obj11 = (UserContext)((com.google.calendar.suggest.v2.UserContext.Builder) (obj6)).instance;
        if (obj8 == null)
        {
            throw new NullPointerException();
        }
        obj11.timezone_ = ((String) (obj8));
        if (locale != null)
        {
            obj8 = locale;
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj6)).copyOnWrite();
            obj11 = (UserContext)((com.google.calendar.suggest.v2.UserContext.Builder) (obj6)).instance;
            if (obj8 == null)
            {
                throw new NullPointerException();
            }
            obj11.locale_ = ((String) (obj8));
        }
        obj8 = (com.google.calendar.suggest.v2.TimeSettings.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)TimeSettings.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        if (i == 4)
        {
            obj11 = com.google.calendar.suggest.v2.TimeSettings.TimeframeType.AROUND_DATE;
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
            TimeSettings timesettings = (TimeSettings)((com.google.calendar.suggest.v2.TimeSettings.Builder) (obj8)).instance;
            if (obj11 == null)
            {
                throw new NullPointerException();
            }
            if (obj11 == com.google.calendar.suggest.v2.TimeSettings.TimeframeType.UNRECOGNIZED)
            {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            timesettings.timeframeType_ = ((com.google.calendar.suggest.v2.TimeSettings.TimeframeType) (obj11)).value;
            obj9 = TimestampUtils.timestampFromMillis(((Long) (obj9)).longValue());
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
            obj11 = (TimeSettings)((com.google.calendar.suggest.v2.TimeSettings.Builder) (obj8)).instance;
            if (obj9 == null)
            {
                throw new NullPointerException();
            }
            obj11.windowStartTime_ = ((com.google.protobuf.Timestamp) (obj9));
        } else
        {
            obj9 = TimestampUtils.timestampFromMillis(l4);
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
            obj11 = (TimeSettings)((com.google.calendar.suggest.v2.TimeSettings.Builder) (obj8)).instance;
            if (obj9 == null)
            {
                throw new NullPointerException();
            }
            obj11.windowStartTime_ = ((com.google.protobuf.Timestamp) (obj9));
            obj9 = TimestampUtils.timestampFromMillis(l3);
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
            obj11 = (TimeSettings)((com.google.calendar.suggest.v2.TimeSettings.Builder) (obj8)).instance;
            if (obj9 == null)
            {
                throw new NullPointerException();
            }
            obj11.windowEndTime_ = ((com.google.protobuf.Timestamp) (obj9));
        }
        obj9 = TimestampUtils.durationFromMillis(l5);
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
        obj11 = (TimeSettings)((com.google.calendar.suggest.v2.TimeSettings.Builder) (obj8)).instance;
        if (obj9 == null)
        {
            throw new NullPointerException();
        }
        obj11.duration_ = ((com.google.protobuf.Duration) (obj9));
        obj9 = new ArrayList();
        i = 0;
        while (i < ((List) (obj7)).size()) 
        {
            Object obj12 = (FindTimeAttendee)((List) (obj7)).get(i);
            com.google.calendar.suggest.v2.Attendee.Builder builder = (com.google.calendar.suggest.v2.Attendee.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)Attendee.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            obj12 = ((FindTimeAttendee) (obj12)).email;
            builder.copyOnWrite();
            Attendee attendee = (Attendee)builder.instance;
            if (obj12 == null)
            {
                throw new NullPointerException();
            }
            attendee.email_ = ((String) (obj12));
            boolean flag1;
            if (i == 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            builder.copyOnWrite();
            ((Attendee)builder.instance).organizer_ = flag1;
            ((List) (obj9)).add((Attendee)(GeneratedMessageLite)builder.build());
            i++;
        }
        obj7 = (com.google.calendar.suggest.v2.SuggestTimeRequest.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)SuggestTimeRequest.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).copyOnWrite();
        ((SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj7)).instance).userContext_ = (UserContext)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj6)).build();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).copyOnWrite();
        ((SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj7)).instance).timeSettings_ = (TimeSettings)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).build();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).copyOnWrite();
        obj6 = (SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj7)).instance;
        if (!((SuggestTimeRequest) (obj6)).attendees_.isModifiable())
        {
            obj6.attendees_ = GeneratedMessageLite.mutableCopy(((SuggestTimeRequest) (obj6)).attendees_);
        }
        obj6 = ((SuggestTimeRequest) (obj6)).attendees_;
        Internal.checkNotNull(obj9);
        if (obj9 instanceof LazyStringList)
        {
            obj9 = ((LazyStringList)obj9).getUnderlyingElements();
            obj8 = (LazyStringList)obj6;
            int j2 = ((List) (obj6)).size();
            for (obj6 = ((List) (obj9)).iterator(); ((Iterator) (obj6)).hasNext();)
            {
                obj9 = ((Iterator) (obj6)).next();
                if (obj9 == null)
                {
                    int j = ((LazyStringList) (obj8)).size();
                    obj = (new StringBuilder(37)).append("Element at index ").append(j - j2).append(" is null.").toString();
                    for (int k = ((LazyStringList) (obj8)).size() - 1; k >= j2; k--)
                    {
                        ((LazyStringList) (obj8)).remove(k);
                    }

                    throw new NullPointerException(((String) (obj)));
                }
                if (obj9 instanceof ByteString)
                {
                    ((LazyStringList) (obj8)).add((ByteString)obj9);
                } else
                {
                    ((LazyStringList) (obj8)).add((String)obj9);
                }
            }

        } else
        if (obj9 instanceof PrimitiveNonBoxingCollection)
        {
            ((List) (obj6)).addAll((Collection)obj9);
        } else
        {
            if ((obj6 instanceof ArrayList) && (obj9 instanceof Collection))
            {
                obj8 = (ArrayList)obj6;
                int l = ((List) (obj6)).size();
                ((ArrayList) (obj8)).ensureCapacity(((Collection)obj9).size() + l);
            }
            int k2 = ((List) (obj6)).size();
            obj8 = ((Iterable) (obj9)).iterator();
            while (((Iterator) (obj8)).hasNext()) 
            {
                obj9 = ((Iterator) (obj8)).next();
                if (obj9 == null)
                {
                    int i1 = ((List) (obj6)).size();
                    obj = (new StringBuilder(37)).append("Element at index ").append(i1 - k2).append(" is null.").toString();
                    for (int j1 = ((List) (obj6)).size() - 1; j1 >= k2; j1--)
                    {
                        ((List) (obj6)).remove(j1);
                    }

                    throw new NullPointerException(((String) (obj)));
                }
                ((List) (obj6)).add(obj9);
            }
        }
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).copyOnWrite();
        ((SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj7)).instance).includeAttendeesEvents_ = true;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).copyOnWrite();
        ((SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj7)).instance).maxResults_ = 10;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).copyOnWrite();
        ((SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj7)).instance).considerRooms_ = flag4;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).copyOnWrite();
        ((SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj7)).instance).expandGroupAttendees_ = true;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).copyOnWrite();
        ((SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj7)).instance).useExplanationType_ = true;
        if (obj4 != null)
        {
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).copyOnWrite();
            obj6 = (SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj7)).instance;
            if (obj4 == null)
            {
                throw new NullPointerException();
            }
            obj6.eventReference_ = ((String) (obj4));
        }
        if (!TextUtils.isEmpty(((CharSequence) (obj5))))
        {
            obj4 = (com.google.calendar.suggest.v2.CalendarEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)CalendarEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
            obj5 = (CalendarEvent)((com.google.calendar.suggest.v2.CalendarEvent.Builder) (obj4)).instance;
            if (obj3 == null)
            {
                throw new NullPointerException();
            }
            obj5.calendarId_ = ((String) (obj3));
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
            obj5 = (CalendarEvent)((com.google.calendar.suggest.v2.CalendarEvent.Builder) (obj4)).instance;
            if (obj3 == null)
            {
                throw new NullPointerException();
            }
            obj5.eventId_ = ((String) (obj3));
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).copyOnWrite();
            ((SuggestTimeRequest)((com.google.calendar.suggest.v2.SuggestTimeRequest.Builder) (obj7)).instance).existingEvent_ = (CalendarEvent)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).build();
        }
        obj3 = ((FindTimeRequestExecutor) (obj)).suggestTime((SuggestTimeRequest)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj7)).build());
        if (obj3 == null)
        {
            return new FindTimeClient.Result(ImmutableList.of(), ImmutableList.of(), ImmutableList.of(), 0, null);
        }
        obj5 = request.attendees;
        obj6 = ((SuggestTimeResponse) (obj3)).consideredAttendees_;
        obj = ((SuggestTimeResponse) (obj3)).omittedAttendees_;
        obj4 = new HashMap();
        obj5 = ((List) (obj5)).iterator();
        do
        {
            if (!((Iterator) (obj5)).hasNext())
            {
                break;
            }
            obj7 = (FindTimeAttendee)((Iterator) (obj5)).next();
            if (!TextUtils.isEmpty(((FindTimeAttendee) (obj7)).email) && !TextUtils.isEmpty(((FindTimeAttendee) (obj7)).displayName))
            {
                ((HashMap) (obj4)).put(((FindTimeAttendee) (obj7)).email.toLowerCase(), ((FindTimeAttendee) (obj7)).displayName);
            }
        } while (true);
        obj5 = new ArrayList();
        obj6 = ((List) (obj6)).iterator();
        do
        {
            if (!((Iterator) (obj6)).hasNext())
            {
                break;
            }
            obj7 = (Attendee)((Iterator) (obj6)).next();
            if (!((HashMap) (obj4)).containsKey(((Attendee) (obj7)).email_))
            {
                ((List) (obj5)).add(((Attendee) (obj7)).email_);
            }
        } while (true);
        obj6 = ((List) (obj)).iterator();
        do
        {
            if (!((Iterator) (obj6)).hasNext())
            {
                break;
            }
            obj7 = (OmittedAttendee)((Iterator) (obj6)).next();
            if (((OmittedAttendee) (obj7)).attendee_ == null)
            {
                obj = Attendee.DEFAULT_INSTANCE;
            } else
            {
                obj = ((OmittedAttendee) (obj7)).attendee_;
            }
            if (!((HashMap) (obj4)).containsKey(((Attendee) (obj)).email_))
            {
                if (((OmittedAttendee) (obj7)).attendee_ == null)
                {
                    obj = Attendee.DEFAULT_INSTANCE;
                } else
                {
                    obj = ((OmittedAttendee) (obj7)).attendee_;
                }
                ((List) (obj5)).add(((Attendee) (obj)).email_);
            }
        } while (true);
        obj = ContactNameResolver.loadDisplayNames(context, ((List) (obj5)));
        for (int k1 = 0; k1 < ((List) (obj5)).size(); k1++)
        {
            obj6 = (String)((List) (obj5)).get(k1);
            obj7 = (String)((ArrayList) (obj)).get(k1);
            if (!TextUtils.isEmpty(((CharSequence) (obj6))) && !TextUtils.isEmpty(((CharSequence) (obj7))))
            {
                ((HashMap) (obj4)).put(obj6, obj7);
            }
        }

        obj5 = ImmutableMap.copyOf(((Map) (obj4)));
        obj4 = request.attendees;
        obj = new HashMap();
        obj4 = ((List) (obj4)).iterator();
        do
        {
            if (!((Iterator) (obj4)).hasNext())
            {
                break;
            }
            obj6 = (FindTimeAttendee)((Iterator) (obj4)).next();
            if (!TextUtils.isEmpty(((FindTimeAttendee) (obj6)).email) && !TextUtils.isEmpty(((FindTimeAttendee) (obj6)).sourceAccount))
            {
                ((HashMap) (obj)).put(((FindTimeAttendee) (obj6)).email.toLowerCase(), ((FindTimeAttendee) (obj6)).sourceAccount);
            }
        } while (true);
        obj6 = ImmutableMap.copyOf(((Map) (obj)));
        obj9 = ((SuggestTimeResponse) (obj3)).consideredAttendees_;
        obj = ((SuggestTimeResponse) (obj3)).omittedAttendees_;
        obj4 = request.timezone;
        obj8 = ((SuggestTimeResponse) (obj3)).attendeeEvents_;
        obj7 = new HashMap();
        String s;
        Object obj13;
        for (obj9 = ((List) (obj9)).iterator(); ((Iterator) (obj9)).hasNext(); ((Map) (obj7)).put(s.toLowerCase(), obj13))
        {
            obj13 = (Attendee)((Iterator) (obj9)).next();
            s = ((Attendee) (obj13)).email_;
            obj13 = new FindTimeAttendee((String)((Map) (obj6)).get(s.toLowerCase()), s, Boolean.TRUE.equals(Boolean.valueOf(((Attendee) (obj13)).organizer_)));
            obj13.displayName = (String)((Map) (obj5)).get(s.toLowerCase());
        }

        obj9 = ((List) (obj)).iterator();
        do
        {
            if (!((Iterator) (obj9)).hasNext())
            {
                break;
            }
            OmittedAttendee omittedattendee1 = (OmittedAttendee)((Iterator) (obj9)).next();
            boolean flag;
            if (omittedattendee1.attendee_ != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                String s1;
                String s5;
                Boolean boolean1;
                if (omittedattendee1.attendee_ == null)
                {
                    obj = Attendee.DEFAULT_INSTANCE;
                } else
                {
                    obj = omittedattendee1.attendee_;
                }
                s1 = ((Attendee) (obj)).email_;
                s5 = (String)((Map) (obj6)).get(s1.toLowerCase());
                boolean1 = Boolean.TRUE;
                if (omittedattendee1.attendee_ == null)
                {
                    obj = Attendee.DEFAULT_INSTANCE;
                } else
                {
                    obj = omittedattendee1.attendee_;
                }
                obj = new FindTimeAttendee(s5, s1, boolean1.equals(Boolean.valueOf(((Attendee) (obj)).organizer_)));
                obj.displayName = (String)((Map) (obj5)).get(s1.toLowerCase());
                ((Map) (obj7)).put(s1.toLowerCase(), obj);
            }
        } while (true);
        obj8 = ((List) (obj8)).iterator();
        while (((Iterator) (obj8)).hasNext()) 
        {
            AttendeeEvents attendeeevents = (AttendeeEvents)((Iterator) (obj8)).next();
            if (attendeeevents.attendee_ == null)
            {
                obj = Attendee.DEFAULT_INSTANCE;
            } else
            {
                obj = attendeeevents.attendee_;
            }
            ((FindTimeAttendee)((Map) (obj7)).get(((Attendee) (obj)).email_.toLowerCase())).setEvents(FindTimeRequestUtils.convertToTimelineAttendeeEventList(context, attendeeevents.events_, ((TimeZone) (obj4))));
        }
        obj4 = ImmutableMap.copyOf(((Map) (obj7)));
        obj = ImmutableList.builder();
        for (obj7 = ((SuggestTimeResponse) (obj3)).consideredAttendees_.iterator(); ((Iterator) (obj7)).hasNext();)
        {
            Object obj10 = (Attendee)((Iterator) (obj7)).next();
            String s2 = ((Attendee) (obj10)).email_;
            boolean flag2 = Boolean.TRUE.equals(Boolean.valueOf(((Attendee) (obj10)).organizer_));
            obj8 = (String)((Map) (obj5)).get(((Attendee) (obj10)).email_.toLowerCase());
            obj10 = new FindTimeAttendee((String)((Map) (obj6)).get(((Attendee) (obj10)).email_.toLowerCase()), s2, flag2);
            obj10.displayName = ((String) (obj8));
            obj8 = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj)).add(obj10);
        }

        obj.forceCopy = true;
        obj5 = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj)).size);
        obj6 = request.timezone;
        obj7 = ImmutableList.builder();
        obj8 = ((SuggestTimeResponse) (obj3)).suggestions_.iterator();
_L9:
        if (!((Iterator) (obj8)).hasNext()) goto _L2; else goto _L1
_L1:
        TimeSuggestion timesuggestion;
        TimelineSuggestion timelinesuggestion;
        ArrayList arraylist;
        com.google.common.collect.ImmutableList.Builder builder1;
        Iterator iterator1;
        timesuggestion = (TimeSuggestion)((Iterator) (obj8)).next();
        timelinesuggestion = new TimelineSuggestion();
        if (timesuggestion.eventTime_ == null)
        {
            obj = SingleEventTime.DEFAULT_INSTANCE;
        } else
        {
            obj = timesuggestion.eventTime_;
        }
        timelinesuggestion.timeRange = FindTimeRequestUtils.toTimeRange(((TimeZone) (obj6)), ((SingleEventTime) (obj)));
        arraylist = new ArrayList();
        for (obj = ((List) (obj5)).iterator(); ((Iterator) (obj)).hasNext();)
        {
            FindTimeAttendee findtimeattendee = (FindTimeAttendee)((Iterator) (obj)).next();
            if (findtimeattendee.isOrganizer)
            {
                arraylist.add(0, (FindTimeAttendee)((Map) (obj4)).get(findtimeattendee.email.toLowerCase()));
            } else
            {
                arraylist.add((FindTimeAttendee)((Map) (obj4)).get(findtimeattendee.email.toLowerCase()));
            }
        }

        obj = timesuggestion.attendeeExplanations_;
        builder1 = ImmutableList.builder();
        iterator1 = ((List) (obj)).iterator();
_L8:
        Object obj14;
        if (!iterator1.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        obj14 = (Explanation)iterator1.next();
        com.google.calendar.suggest.v2.Explanation.ConflictType conflicttype = com.google.calendar.suggest.v2.Explanation.ConflictType.forNumber(((Explanation) (obj14)).conflictType_);
        obj = conflicttype;
        if (conflicttype == null)
        {
            obj = com.google.calendar.suggest.v2.Explanation.ConflictType.UNRECOGNIZED;
        }
        ((com.google.calendar.suggest.v2.Explanation.ConflictType) (obj)).ordinal();
        JVM INSTR tableswitch 0 1: default 2796
    //                   0 2917
    //                   1 2817;
           goto _L3 _L4 _L5
_L3:
        Object obj1;
        obj1 = TAG;
        if (obj == com.google.calendar.suggest.v2.Explanation.ConflictType.UNRECOGNIZED)
        {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        break; /* Loop/switch isn't completed */
_L5:
        int l1 = 1;
_L6:
        Object obj15;
        if (((Explanation) (obj14)).attendee_ == null)
        {
            obj = Attendee.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Explanation) (obj14)).attendee_;
        }
        obj = (FindTimeAttendee)((Map) (obj4)).get(((Attendee) (obj)).email_.toLowerCase());
        obj1 = context;
        obj15 = ((Explanation) (obj14)).conflictingEvents_;
        obj14 = new ArrayList();
        for (obj15 = ((List) (obj15)).iterator(); ((Iterator) (obj15)).hasNext(); ((List) (obj14)).add(FindTimeRequestUtils.convertToTimelineAttendeeEvent(((Context) (obj1)), (Event)((Iterator) (obj15)).next(), ((TimeZone) (obj6))))) { }
        break MISSING_BLOCK_LABEL_2960;
_L4:
        l1 = 0;
          goto _L6
        LogUtils.wtf(((String) (obj1)), "Unexpected conflict type %d", new Object[] {
            Integer.valueOf(((com.google.calendar.suggest.v2.Explanation.ConflictType) (obj)).value)
        });
        l1 = 0;
          goto _L6
        obj = (com.google.common.collect.ImmutableList.Builder)builder1.add(new AttendeeExplanation(l1, ((FindTimeAttendee) (obj)), ((List) (obj14))));
        if (true) goto _L8; else goto _L7
_L7:
        builder1.forceCopy = true;
        timelinesuggestion.attendeeExplanations = ImmutableList.asImmutableList(builder1.contents, builder1.size);
        timelinesuggestion.attendees = ImmutableList.copyOf(arraylist);
        timelinesuggestion.suggestionId = timesuggestion.suggestionId_;
        timelinesuggestion.explanationMessage = timesuggestion.explanationMessage_;
        obj = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj7)).add(timelinesuggestion);
          goto _L9
_L2:
        obj7.forceCopy = true;
        obj6 = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj7)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj7)).size);
        obj = ((SuggestTimeResponse) (obj3)).omittedAttendees_;
        obj7 = new com.google.common.collect.ImmutableList.Builder();
        obj8 = ((List) (obj)).iterator();
_L14:
        if (!((Iterator) (obj8)).hasNext())
        {
            break MISSING_BLOCK_LABEL_3345;
        }
        OmittedAttendee omittedattendee = (OmittedAttendee)((Iterator) (obj8)).next();
        FindTimeAttendee findtimeattendee1;
        if (omittedattendee.attendee_ == null)
        {
            obj = Attendee.DEFAULT_INSTANCE;
        } else
        {
            obj = omittedattendee.attendee_;
        }
        findtimeattendee1 = (FindTimeAttendee)((Map) (obj4)).get(((Attendee) (obj)).email_.toLowerCase());
        obj = findtimeattendee1;
        if (findtimeattendee1 == null)
        {
            com.google.calendar.suggest.v2.OmittedAttendee.Reason reason;
            String s3;
            String s4;
            boolean flag3;
            if (omittedattendee.attendee_ == null)
            {
                obj = Attendee.DEFAULT_INSTANCE;
            } else
            {
                obj = omittedattendee.attendee_;
            }
            flag3 = ((Attendee) (obj)).organizer_;
            if (omittedattendee.attendee_ == null)
            {
                obj = Attendee.DEFAULT_INSTANCE;
            } else
            {
                obj = omittedattendee.attendee_;
            }
            obj = new FindTimeAttendee(null, ((Attendee) (obj)).email_, flag3);
            LogUtils.wtf(TAG, "Could not find attendee in suggest response!", new Object[0]);
        }
        s3 = ((FindTimeAttendee) (obj)).email;
        s4 = ((FindTimeAttendee) (obj)).displayName;
        reason = com.google.calendar.suggest.v2.OmittedAttendee.Reason.forNumber(omittedattendee.reason_);
        obj = reason;
        if (reason == null)
        {
            obj = com.google.calendar.suggest.v2.OmittedAttendee.Reason.UNRECOGNIZED;
        }
        ((com.google.calendar.suggest.v2.OmittedAttendee.Reason) (obj)).ordinal();
        JVM INSTR tableswitch 0 2: default 3272
    //                   0 3327
    //                   1 3333
    //                   2 3339;
           goto _L10 _L11 _L12 _L13
_L13:
        break MISSING_BLOCK_LABEL_3339;
_L10:
        l1 = 0;
_L15:
        obj = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj7)).add(new com.google.android.calendar.timely.OmittedAttendee(s3, s4, l1));
          goto _L14
_L11:
        l1 = 0;
          goto _L15
_L12:
        l1 = 1;
          goto _L15
        l1 = 2;
          goto _L15
        obj7.forceCopy = true;
        obj = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj7)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj7)).size);
        Object obj2 = ImmutableList.builder();
        for (Iterator iterator = ((List) (obj5)).iterator(); iterator.hasNext();)
        {
            obj5 = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj2)).add(((FindTimeAttendee)iterator.next()).email);
        }

        obj2.forceCopy = true;
        obj2 = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj2)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj2)).size);
        int l2 = ((SuggestTimeResponse) (obj3)).acceptableSuggestions_;
        int i2 = l2;
        if (l2 == -1)
        {
            i2 = ((List) (obj6)).size();
        }
        return new FindTimeClient.Result(((ImmutableList) (obj6)), ((ImmutableList) (obj2)), ((ImmutableList) (obj)), i2, ((SuggestTimeResponse) (obj3)).responseId_);
    }

}
