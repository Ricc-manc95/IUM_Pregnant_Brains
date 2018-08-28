// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.content.Context;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.common.ApiOperation;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.AttendeeModifications;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.common.base.Strings;
import com.google.common.base.VerifyException;
import com.google.common.collect.Iterators;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;
import java.util.Collection;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventClient, EventModifications, EventApiStoreImpl, EventDescriptor, 
//            Event, CpEventKey, EventKey

public final class EventClientFutureImpl
    implements EventClient
{

    public final EventApiStoreImpl api;

    public EventClientFutureImpl(EventApiStoreImpl eventapistoreimpl)
    {
        api = eventapistoreimpl;
    }

    private static EventModifications prepareIcsOperation(EventModifications eventmodifications)
    {
        if (eventmodifications.getCalendar() == null)
        {
            throw new NullPointerException();
        }
        if (eventmodifications.getCalendar().calendarId == null)
        {
            throw new NullPointerException();
        }
        String s = eventmodifications.getCalendar().calendarId;
        if (!s.equalsIgnoreCase(eventmodifications.getOrganizer().email))
        {
            com.google.common.collect.ImmutableList immutablelist = eventmodifications.getAttendees();
            class .Lambda._cls9
                implements Predicate
            {

                private final String arg$1;

                public final boolean apply(Object obj)
                {
                    return arg$1.equalsIgnoreCase(((Attendee)obj).attendeeDescriptor.email);
                }

            .Lambda._cls9(String s)
            {
                arg$1 = s;
            }
            }

            .Lambda._cls9 _lcls9 = new .Lambda._cls9(s);
            boolean flag;
            if (Iterators.indexOf(immutablelist.iterator(), _lcls9) != -1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                eventmodifications.getAttendeeModifications().addAttendee(new Attendee(new AttendeeDescriptor(s), eventmodifications.getCalendarListEntry().getDisplayName(), 1, 1, new Response(new com.google.android.calendar.api.event.attendee.Response.Builder())));
            }
        }
        return eventmodifications;
    }

    public final ListenableFuture create(EventModifications eventmodifications, GooglePrivateData.GuestNotification guestnotification)
    {
        class .Lambda._cls0
            implements Callable
        {

            private final EventClientFutureImpl arg$1;
            private final EventModifications arg$2;
            private final GooglePrivateData.GuestNotification arg$3;

            public final Object call()
            {
                boolean flag1 = true;
                Object obj1 = arg$1;
                Object obj = arg$2;
                GooglePrivateData.GuestNotification guestnotification1 = arg$3;
                obj1 = ((EventClientFutureImpl) (obj1)).api;
                if (!((EventModifications) (obj)).isNewEvent())
                {
                    throw new IllegalArgumentException();
                }
                boolean flag;
                if (!(((EventModifications) (obj)).getDescriptor().getKey() instanceof EventKey.Persisted))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalArgumentException();
                }
                obj = (Event)EventApiStoreImpl.callWithMetrics(new EventApiStoreImpl..Lambda._cls0(((EventApiStoreImpl) (obj1)), ((EventModifications) (obj)), guestnotification1), ApiOperation.EVENT_CREATE);
                if (obj != null)
                {
                    flag = flag1;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
                } else
                {
                    return (Event)obj;
                }
            }

            .Lambda._cls0(EventModifications eventmodifications, GooglePrivateData.GuestNotification guestnotification)
            {
                arg$1 = EventClientFutureImpl.this;
                arg$2 = eventmodifications;
                arg$3 = guestnotification;
            }
        }

        return (FluentFuture)CalendarExecutor.API.submit(new .Lambda._cls0(eventmodifications, guestnotification));
    }

    public final ListenableFuture delete(EventDescriptor eventdescriptor, int i, GooglePrivateData.GuestNotification guestnotification)
    {
        class .Lambda._cls5
            implements Callable
        {

            private final EventClientFutureImpl arg$1;
            private final EventDescriptor arg$2;
            private final int arg$3;
            private final GooglePrivateData.GuestNotification arg$4;

            public final Object call()
            {
                EventClientFutureImpl eventclientfutureimpl = arg$1;
                EventDescriptor eventdescriptor1 = arg$2;
                int j = arg$3;
                GooglePrivateData.GuestNotification guestnotification1 = arg$4;
                ((Boolean)EventApiStoreImpl.callWithMetrics(new EventApiStoreImpl..Lambda._cls5(eventclientfutureimpl.api, (CpEventDescriptor)eventdescriptor1, j, guestnotification1), ApiOperation.EVENT_DELETE)).booleanValue();
                return null;
            }

            .Lambda._cls5(EventDescriptor eventdescriptor, int i, GooglePrivateData.GuestNotification guestnotification)
            {
                arg$1 = EventClientFutureImpl.this;
                arg$2 = eventdescriptor;
                arg$3 = i;
                arg$4 = guestnotification;
            }
        }

        return (FluentFuture)CalendarExecutor.API.submit(new .Lambda._cls5(eventdescriptor, i, guestnotification));
    }

    public final ListenableFuture icsImport(EventModifications eventmodifications)
    {
        if (!eventmodifications.isNewEvent())
        {
            throw new IllegalStateException(String.valueOf("Please use icsUpdate method for updates."));
        } else
        {
            eventmodifications = prepareIcsOperation(eventmodifications);
            GooglePrivateData.GuestNotification guestnotification = GooglePrivateData.GuestNotification.UNDECIDED;
            return (FluentFuture)CalendarExecutor.API.submit(new .Lambda._cls0(eventmodifications, guestnotification));
        }
    }

    public final ListenableFuture icsList(Collection collection)
    {
        class .Lambda._cls6
            implements Callable
        {

            private final EventClientFutureImpl arg$1;
            private final Collection arg$2;

            public final Object call()
            {
                boolean flag = false;
                EventClientFutureImpl eventclientfutureimpl = arg$1;
                Collection collection1 = arg$2;
                Event aevent[] = (Event[])EventApiStoreImpl.callWithMetrics(new EventApiStoreImpl..Lambda._cls6(eventclientfutureimpl.api, collection1), ApiOperation.EVENT_ICS_LIST);
                if (aevent != null)
                {
                    flag = true;
                }
                if (!flag)
                {
                    throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
                } else
                {
                    return (Event[])aevent;
                }
            }

            .Lambda._cls6(Collection collection)
            {
                arg$1 = EventClientFutureImpl.this;
                arg$2 = collection;
            }
        }

        return (FluentFuture)CalendarExecutor.API.submit(new .Lambda._cls6(collection));
    }

    public final ListenableFuture icsUpdate(EventModifications eventmodifications, int i)
    {
        boolean flag;
        if (!eventmodifications.isNewEvent())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Please use icsImport method for new events."));
        } else
        {
            eventmodifications = prepareIcsOperation(eventmodifications);
            GooglePrivateData.GuestNotification guestnotification = GooglePrivateData.GuestNotification.UNDECIDED;
            class .Lambda._cls4
                implements Callable
            {

                private final EventClientFutureImpl arg$1;
                private final EventModifications arg$2;
                private final int arg$3;
                private final GooglePrivateData.GuestNotification arg$4;

                public final Object call()
                {
                    EventClientFutureImpl eventclientfutureimpl = arg$1;
                    EventModifications eventmodifications1 = arg$2;
                    int j = arg$3;
                    GooglePrivateData.GuestNotification guestnotification1 = arg$4;
                    if (eventmodifications1.getOriginal() == null)
                    {
                        throw new NullPointerException();
                    } else
                    {
                        return (Event)EventApiStoreImpl.callWithMetrics(new EventApiStoreImpl..Lambda._cls3(eventclientfutureimpl.api, eventmodifications1, j, guestnotification1), ApiOperation.EVENT_UPDATE);
                    }
                }

            .Lambda._cls4(EventModifications eventmodifications, int i, GooglePrivateData.GuestNotification guestnotification)
            {
                arg$1 = EventClientFutureImpl.this;
                arg$2 = eventmodifications;
                arg$3 = i;
                arg$4 = guestnotification;
            }
            }

            return (FluentFuture)CalendarExecutor.API.submit(new .Lambda._cls4(eventmodifications, i, guestnotification));
        }
    }

    public final void initialize(Context context)
    {
    }

    final Event lambda$read$3$EventClientFutureImpl(CpEventKey cpeventkey)
        throws Exception
    {
        boolean flag2;
        flag2 = true;
        boolean flag;
        try
        {
            cpeventkey = (EventDescriptor)EventApiStoreImpl.callWithMetrics(new EventApiStoreImpl..Lambda._cls4(api, cpeventkey), ApiOperation.EVENT_CREATE_DESCRIPTOR);
        }
        // Misplaced declaration of an exception variable
        catch (CpEventKey cpeventkey)
        {
            throw new RuntimeException(cpeventkey);
        }
        if (cpeventkey != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_66;
        }
        throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        cpeventkey = (EventDescriptor)cpeventkey;
        cpeventkey = (Event)EventApiStoreImpl.callWithMetrics(new EventApiStoreImpl..Lambda._cls1(api, cpeventkey), ApiOperation.EVENT_READ);
        boolean flag1;
        if (cpeventkey != null)
        {
            flag1 = flag2;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_120;
        }
        throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        cpeventkey = (Event)cpeventkey;
        return cpeventkey;
    }

    public final ListenableFuture read(EventDescriptor eventdescriptor)
    {
        class .Lambda._cls1
            implements Callable
        {

            private final EventClientFutureImpl arg$1;
            private final EventDescriptor arg$2;

            public final Object call()
            {
                boolean flag = false;
                Object obj1 = arg$1;
                Object obj = arg$2;
                obj1 = ((EventClientFutureImpl) (obj1)).api;
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                obj = (Event)EventApiStoreImpl.callWithMetrics(new EventApiStoreImpl..Lambda._cls1(((EventApiStoreImpl) (obj1)), (EventDescriptor)obj), ApiOperation.EVENT_READ);
                if (obj != null)
                {
                    flag = true;
                }
                if (!flag)
                {
                    throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
                } else
                {
                    return (Event)obj;
                }
            }

            .Lambda._cls1(EventDescriptor eventdescriptor)
            {
                arg$1 = EventClientFutureImpl.this;
                arg$2 = eventdescriptor;
            }
        }

        return (FluentFuture)CalendarExecutor.API.submit(new .Lambda._cls1(eventdescriptor));
    }

    public final ListenableFuture read(EventKey eventkey)
    {
        if (!(eventkey instanceof CpEventKey))
        {
            throw new IllegalArgumentException();
        } else
        {
            eventkey = (CpEventKey)eventkey;
            class .Lambda._cls3
                implements Callable
            {

                private final EventClientFutureImpl arg$1;
                private final CpEventKey arg$2;

                public final Object call()
                {
                    return arg$1.lambda$read$3$EventClientFutureImpl(arg$2);
                }

            .Lambda._cls3(CpEventKey cpeventkey)
            {
                arg$1 = EventClientFutureImpl.this;
                arg$2 = cpeventkey;
            }
            }

            return (FluentFuture)CalendarExecutor.API.submit(new .Lambda._cls3(eventkey));
        }
    }

    public final ListenableFuture readDescriptor(EventKey eventkey)
    {
        if (!(eventkey instanceof CpEventKey))
        {
            throw new IllegalArgumentException();
        } else
        {
            eventkey = (CpEventKey)eventkey;
            class .Lambda._cls7
                implements Callable
            {

                private final EventClientFutureImpl arg$1;
                private final CpEventKey arg$2;

                public final Object call()
                {
                    boolean flag = false;
                    Object obj = arg$1;
                    CpEventKey cpeventkey = arg$2;
                    obj = (EventDescriptor)EventApiStoreImpl.callWithMetrics(new EventApiStoreImpl..Lambda._cls4(((EventClientFutureImpl) (obj)).api, cpeventkey), ApiOperation.EVENT_CREATE_DESCRIPTOR);
                    if (obj != null)
                    {
                        flag = true;
                    }
                    if (!flag)
                    {
                        throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
                    } else
                    {
                        return (EventDescriptor)obj;
                    }
                }

            .Lambda._cls7(CpEventKey cpeventkey)
            {
                arg$1 = EventClientFutureImpl.this;
                arg$2 = cpeventkey;
            }
            }

            return (FluentFuture)CalendarExecutor.API.submit(new .Lambda._cls7(eventkey));
        }
    }

    public final ListenableFuture readGadgetPreferences(EventKey eventkey, CalendarKey calendarkey)
    {
        eventkey = (CpEventKey)eventkey;
        class .Lambda._cls8
            implements Callable
        {

            private final CpEventKey arg$1;
            private final CalendarKey arg$2;

            public final Object call()
            {
                Object obj = arg$1;
                CalendarKey calendarkey1 = arg$2;
                obj = TimelyStore.acquire(CalendarApi.getApiAppContext()).getTimelyEventData(((CpEventKey) (obj)).localId(), calendarkey1);
                if (obj == null)
                {
                    return RegularImmutableMap.EMPTY;
                }
                obj = ((TimelyEventData) (obj)).eventGadget;
                if (obj == null)
                {
                    return RegularImmutableMap.EMPTY;
                } else
                {
                    return ImmutableMap.copyOf(((com.google.api.services.calendar.model.Event.Gadget) (obj)).preferences);
                }
            }

            .Lambda._cls8(CpEventKey cpeventkey, CalendarKey calendarkey)
            {
                arg$1 = cpeventkey;
                arg$2 = calendarkey;
            }
        }

        return (FluentFuture)CalendarExecutor.API.submit(new .Lambda._cls8(eventkey, calendarkey));
    }

    public final ListenableFuture update(EventModifications eventmodifications, int i, GooglePrivateData.GuestNotification guestnotification)
    {
        return (FluentFuture)CalendarExecutor.API.submit(new .Lambda._cls4(eventmodifications, i, guestnotification));
    }
}
