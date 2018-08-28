// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.content.ContentResolver;
import android.net.Uri;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.timebox.TimeBoxUtil;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.apps.common.ical4jutils.CalendarBuilderWrapper;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.EventFactory;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.AttendeeModifications;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocationModifications;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.api.event.time.RecurrenceParser;
import com.google.calendar.common.rtc.ConferenceDescriptionUtils;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.base.Present;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.data.UnfoldingReader;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Content;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.Time;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.CuType;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.DateProperty;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStamp;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Duration;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Status;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.util.CompatibilityHints;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventOperation, ICalUtils

class ICalEventReader
{
    static interface OperationLoader
    {

        public abstract ListenableFuture loadEvents(CalendarListEntry calendarlistentry);
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/ical/ICalEventReader);
    private boolean addAttendees;
    private boolean addRecurrences;
    public final EventClient eventClient;

    ICalEventReader()
    {
        this(CalendarApi.Events);
    }

    private ICalEventReader(EventClient eventclient)
    {
        addAttendees = true;
        addRecurrences = true;
        eventClient = eventclient;
        CompatibilityHints.HINTS.put("ical4j.unfolding.relaxed", Boolean.valueOf(true));
        CompatibilityHints.HINTS.put("ical4j.parsing.relaxed", Boolean.valueOf(true));
        CompatibilityHints.HINTS.put("ical4j.compatibility.outlook", Boolean.valueOf(true));
        CompatibilityHints.HINTS.put("ical4j.compatibility.notes", Boolean.valueOf(true));
    }

    static String getEmailWithoutScheme(String s)
    {
        String s1 = s;
        if (s != null)
        {
            s1 = s;
            if (s.toLowerCase(Locale.US).startsWith("mailto:"))
            {
                s1 = s.substring(7);
            }
        }
        return s1;
    }

    static final Boolean lambda$addEventDetails$7$ICalEventReader(DateProperty dateproperty)
    {
        return Boolean.valueOf(dateproperty.toString().contains("VALUE=DATE:"));
    }

    static final Recurrence lambda$addRecurrences$8$ICalEventReader(String s)
    {
        return RecurrenceParser.parseFromStoreStrings(s, null, null, null);
    }

    static final String lambda$createICalEventOperations$5$ICalEventReader(DtStamp dtstamp)
    {
        return String.valueOf(((DateProperty) (dtstamp)).date.getTime());
    }

    static final EventModifications lambda$createICalEventOperations$6$ICalEventReader(String s, int i, String s1, Event event)
    {
        return CalendarApi.EventFactory.modifyIcsImport(event, s, i, s1);
    }

    static boolean originalStartMatches(Event event, Long long1)
    {
        event = event.getDescriptor();
        if (long1 != null) goto _L2; else goto _L1
_L1:
        if (event.isRecurring()) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (long1.longValue() != event.getOriginalStart())
        {
            return false;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    final ICalEventOperation createICalEventOperation(Collection collection, VEvent vevent, String s)
    {
        Iterator iterator1 = collection.iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            Object obj5 = (EventModifications)iterator1.next();
            Object obj4 = ((EventModifications) (obj5)).getSummary();
            Object obj = (Summary)((Component) (vevent)).properties.getProperty("SUMMARY");
            class .Lambda._cls6
                implements Function
            {

                public static final Function $instance = new .Lambda._cls6();

                public final Object apply(Object obj8)
                {
                    return ((Content)(Summary)obj8).getValue();
                }


            private .Lambda._cls6()
            {
            }
            }

            class .Lambda._cls7
                implements Consumer
            {

                private final EventModifications arg$1;

                public final void accept(Object obj8)
                {
                    arg$1.setSummary((String)obj8);
                }

            .Lambda._cls7(EventModifications eventmodifications)
            {
                arg$1 = eventmodifications;
            }
            }

            class .Lambda._cls8
                implements Function
            {

                public static final Function $instance = new .Lambda._cls8();

                public final Object apply(Object obj8)
                {
                    return ((Content)(Description)obj8).getValue();
                }


            private .Lambda._cls8()
            {
            }
            }

            class .Lambda._cls9
                implements Consumer
            {

                private final EventModifications arg$1;

                public final void accept(Object obj8)
                {
                    arg$1.setDescription((String)obj8);
                }

            .Lambda._cls9(EventModifications eventmodifications)
            {
                arg$1 = eventmodifications;
            }
            }

            Object obj7;
            boolean flag;
            long l;
            long l1;
            if (obj == null)
            {
                obj = Absent.INSTANCE;
            } else
            {
                obj = new Present(obj);
            }
            obj = (String)((Optional) (obj)).transform(.Lambda._cls6..instance).orNull();
            obj5.getClass();
            obj7 = new .Lambda._cls7(((EventModifications) (obj5)));
            if (obj4 == obj || obj4 != null && obj4.equals(obj))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                ((Consumer) (obj7)).accept(obj);
            }
            obj4 = ((EventModifications) (obj5)).getDescription();
            obj = (Description)((Component) (vevent)).properties.getProperty("DESCRIPTION");
            if (obj == null)
            {
                obj = Absent.INSTANCE;
            } else
            {
                obj = new Present(obj);
            }
            obj = (String)((Optional) (obj)).transform(.Lambda._cls8..instance).orNull();
            obj5.getClass();
            obj7 = new .Lambda._cls9(((EventModifications) (obj5)));
            if (obj4 == obj || obj4 != null && obj4.equals(obj))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                ((Consumer) (obj7)).accept(obj);
            }
            obj = ((EventModifications) (obj5)).getDescription();
            if (obj != null)
            {
                ((EventModifications) (obj5)).setDescription(ConferenceDescriptionUtils.removeAutoGeneratedDescription(((String) (obj))));
            }
            obj = (DtStart)((Component) (vevent)).properties.getProperty("DTSTART");
            if (obj == null)
            {
                obj4 = Absent.INSTANCE;
            } else
            {
                obj4 = new Present(obj);
            }
            obj = (DtEnd)((Component) (vevent)).properties.getProperty("DTEND");
            if (obj == null && (DtStart)((Component) (vevent)).properties.getProperty("DTSTART") != null)
            {
                obj7 = (DtStart)((Component) (vevent)).properties.getProperty("DTSTART");
                class .Lambda._cls10
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls10();

                    public final Object apply(Object obj8)
                    {
                        return ((DateProperty)obj8).date;
                    }


            private .Lambda._cls10()
            {
            }
                }

                class .Lambda._cls11
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls11();

                    public final Object apply(Object obj8)
                    {
                        return Long.valueOf(((java.util.Date)(Date)obj8).getTime());
                    }


            private .Lambda._cls11()
            {
            }
                }

                class .Lambda._cls12
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls12();

                    public final Object apply(Object obj8)
                    {
                        return ((DateProperty)obj8).date;
                    }


            private .Lambda._cls12()
            {
            }
                }

                class .Lambda._cls13
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls13();

                    public final Object apply(Object obj8)
                    {
                        return Long.valueOf(((java.util.Date)(Date)obj8).getTime());
                    }


            private .Lambda._cls13()
            {
            }
                }

                class .Lambda._cls14
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls14();

                    public final Object apply(Object obj8)
                    {
                        return ICalEventReader.lambda$addEventDetails$7$ICalEventReader((DateProperty)obj8);
                    }


            private .Lambda._cls14()
            {
            }
                }

                class .Lambda._cls17
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls17();

                    public final Object apply(Object obj8)
                    {
                        return ((Content)(Location)obj8).getValue();
                    }


            private .Lambda._cls17()
            {
            }
                }

                Value value;
                boolean flag3;
                if ((Duration)((Component) (vevent)).properties.getProperty("DURATION") != null)
                {
                    obj = (Duration)((Component) (vevent)).properties.getProperty("DURATION");
                } else
                if (((DateProperty) (obj7)).date instanceof DateTime)
                {
                    obj = new Duration(new Dur(0, 0, 0, 0));
                } else
                {
                    obj = new Duration(new Dur(1, 0, 0, 0));
                }
                obj = ((Duration) (obj)).duration.getTime(((DateProperty) (obj7)).date);
                value = (Value)((Property) (obj7)).parameters.getParameter("VALUE");
                if (Value.DATE.equals(value))
                {
                    obj = new Date(((java.util.Date) (obj)));
                } else
                {
                    obj = new DateTime(((java.util.Date) (obj)));
                }
                obj = new DtEnd(((Date) (obj)));
                if (((DateProperty) (obj7)).date instanceof DateTime)
                {
                    flag3 = ((DateTime)((DateProperty) (obj7)).date).time.utc;
                } else
                {
                    flag3 = false;
                }
                if (flag3)
                {
                    ((DateProperty) (obj)).setUtc(true);
                }
            }
            if (obj == null)
            {
                obj = Absent.INSTANCE;
            } else
            {
                obj = new Present(obj);
            }
            l1 = ((Long)((Optional) (obj4)).transform(.Lambda._cls10..instance).transform(.Lambda._cls11..instance).get()).longValue();
            l = Math.max(l1, ((Long)((Optional) (obj)).transform(.Lambda._cls12..instance).transform(.Lambda._cls13..instance).or(Long.valueOf(l1))).longValue());
            if (((Boolean)((Optional) (obj4)).transform(.Lambda._cls14..instance).or(Boolean.valueOf(false))).booleanValue())
            {
                l1 = TimeBoxUtil.utcJulianDayToMs(TimeBoxUtil.msToUtcJulianDay(l1));
                l = TimeBoxUtil.utcJulianDayToMs(TimeBoxUtil.msToUtcJulianDay(l));
                if (!((EventModifications) (obj5)).isAllDayEvent() || ((EventModifications) (obj5)).getStartMillis() != l1 || ((EventModifications) (obj5)).getEndMillis() != l)
                {
                    ((EventModifications) (obj5)).setToAllDayWithDates(l1, l);
                }
            } else
            {
                if (((EventModifications) (obj5)).isAllDayEvent() || ((EventModifications) (obj5)).getStartMillis() != l1 || ((EventModifications) (obj5)).getEndMillis() != l)
                {
                    ((EventModifications) (obj5)).setToTimedWithTimes(l1, l);
                }
                obj = TimeZone.getDefault().getID();
                obj4 = ((EventModifications) (obj5)).getTimeZoneId();
                obj5.getClass();
                class .Lambda._cls15
                    implements Consumer
                {

                    private final EventModifications arg$1;

                    public final void accept(Object obj8)
                    {
                        arg$1.setTimeZoneId((String)obj8);
                    }

            .Lambda._cls15(EventModifications eventmodifications)
            {
                arg$1 = eventmodifications;
            }
                }

                obj7 = new .Lambda._cls15(((EventModifications) (obj5)));
                boolean flag1;
                if (obj4 == obj || obj4 != null && obj4.equals(obj))
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (!flag1)
                {
                    ((Consumer) (obj7)).accept(obj);
                }
                if (!addRecurrences || ((Component) (vevent)).properties.getProperty("RRULE") == null)
                {
                    obj4 = ((EventModifications) (obj5)).getEndTimeZoneId();
                    obj5.getClass();
                    class .Lambda._cls16
                        implements Consumer
                    {

                        private final EventModifications arg$1;

                        public final void accept(Object obj8)
                        {
                            arg$1.setEndTimeZoneId((String)obj8);
                        }

            .Lambda._cls16(EventModifications eventmodifications)
            {
                arg$1 = eventmodifications;
            }
                    }

                    obj7 = new .Lambda._cls16(((EventModifications) (obj5)));
                    if (obj4 == obj || obj4 != null && obj4.equals(obj))
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (!flag1)
                    {
                        ((Consumer) (obj7)).accept(obj);
                    }
                }
            }
            obj = (Location)((Component) (vevent)).properties.getProperty("LOCATION");
            if (obj == null)
            {
                obj = Absent.INSTANCE;
            } else
            {
                obj = new Present(obj);
            }
            obj7 = ((Optional) (obj)).transform(.Lambda._cls17..instance);
            if (((Optional) (obj7)).isPresent() && !Platform.stringIsNullOrEmpty((String)((Optional) (obj7)).get()))
            {
                obj = ((EventModifications) (obj5)).getLocationModification();
                obj4 = new com.google.android.calendar.api.event.location.EventLocation.Builder();
                obj7 = (String)((Optional) (obj7)).get();
                if (obj7 == null)
                {
                    throw new NullPointerException();
                }
                obj4.name = (String)obj7;
                ((StructuredLocationModifications) (obj)).addEventLocation(new EventLocation(((com.google.android.calendar.api.event.location.EventLocation.Builder) (obj4))));
            }
            if (addRecurrences)
            {
                Object obj1 = ((Component) (vevent)).properties.getProperty("RRULE");
                class .Lambda._cls18
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls18();

                    public final Object apply(Object obj8)
                    {
                        return ((Content)(Property)obj8).getValue();
                    }


            private .Lambda._cls18()
            {
            }
                }

                class .Lambda._cls19
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls19();

                    public final Object apply(Object obj8)
                    {
                        return ICalEventReader.lambda$addRecurrences$8$ICalEventReader((String)obj8);
                    }


            private .Lambda._cls19()
            {
            }
                }

                class .Lambda._cls20
                    implements Consumer
                {

                    private final EventModifications arg$1;

                    public final void accept(Object obj8)
                    {
                        arg$1.setRecurrence((Recurrence)obj8);
                    }

            .Lambda._cls20(EventModifications eventmodifications)
            {
                arg$1 = eventmodifications;
            }
                }

                Recurrence recurrence;
                boolean flag2;
                if (obj1 == null)
                {
                    obj1 = Absent.INSTANCE;
                } else
                {
                    obj1 = new Present(obj1);
                }
                obj1 = (Recurrence)((Optional) (obj1)).transform(.Lambda._cls18..instance).transform(.Lambda._cls19..instance).orNull();
                recurrence = ((EventModifications) (obj5)).getRecurrence();
                obj5.getClass();
                obj5 = new .Lambda._cls20(((EventModifications) (obj5)));
                if (recurrence == obj1 || recurrence != null && recurrence.equals(obj1))
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                if (!flag2)
                {
                    ((Consumer) (obj5)).accept(obj1);
                }
            }
        } while (true);
        if ("CANCEL".equals(s) || Status.VEVENT_CANCELLED.equals((Status)((Component) (vevent)).properties.getProperty("STATUS")))
        {
            return ICalEventOperation.cancel(Lists.newArrayList(collection));
        }
        Iterator iterator = collection.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            EventModifications eventmodifications = (EventModifications)iterator.next();
            if (addAttendees && !"PUBLISH".equals(s))
            {
                Object obj2 = ((Component) (vevent)).properties.getProperties("ATTENDEE");
                net/fortuna/ical4j/model/property/Attendee.getClass();
                class .Lambda._cls24
                    implements Predicate
                {

                    private final Class arg$1;

                    public final boolean apply(Object obj8)
                    {
                        return arg$1.isInstance(obj8);
                    }

            .Lambda._cls24(Class class1)
            {
                arg$1 = class1;
            }
                }

                Object obj6 = new .Lambda._cls24(net/fortuna/ical4j/model/property/Attendee);
                if (obj2 == null)
                {
                    throw new NullPointerException();
                }
                if (obj6 == null)
                {
                    throw new NullPointerException();
                }
                obj2 = new com.google.common.collect.Iterables._cls4(((Iterable) (obj2)), ((Predicate) (obj6)));
                net/fortuna/ical4j/model/property/Attendee.getClass();
                class .Lambda._cls25
                    implements Function
                {

                    private final Class arg$1;

                    public final Object apply(Object obj8)
                    {
                        return arg$1.cast(obj8);
                    }

            .Lambda._cls25(Class class1)
            {
                arg$1 = class1;
            }
                }

                obj6 = new .Lambda._cls25(net/fortuna/ical4j/model/property/Attendee);
                if (obj2 == null)
                {
                    throw new NullPointerException();
                }
                if (obj6 == null)
                {
                    throw new NullPointerException();
                }
                obj6 = (new com.google.common.collect.Iterables._cls5(((Iterable) (obj2)), ((Function) (obj6)))).iterator();
                while (((Iterator) (obj6)).hasNext()) 
                {
                    Attendee attendee = (Attendee)((Iterator) (obj6)).next();
                    String s1 = getEmailWithoutScheme(attendee.getValue());
                    Object obj3 = ((Property) (attendee)).parameters.getParameter("CN");
                    class .Lambda._cls21
                        implements Function
                    {

                        public static final Function $instance = new .Lambda._cls21();

                        public final Object apply(Object obj8)
                        {
                            return ((Content)(Parameter)obj8).getValue();
                        }


            private .Lambda._cls21()
            {
            }
                    }

                    class .Lambda._cls22
                        implements Function
                    {

                        public static final Function $instance = new .Lambda._cls22();

                        public final Object apply(Object obj8)
                        {
                            return ((Content)(Parameter)obj8).getValue();
                        }


            private .Lambda._cls22()
            {
            }
                    }

                    class .Lambda._cls23
                        implements Function
                    {

                        public static final Function $instance = new .Lambda._cls23();

                        public final Object apply(Object obj8)
                        {
                            return ((Content)(Parameter)obj8).getValue();
                        }


            private .Lambda._cls23()
            {
            }
                    }

                    String s2;
                    byte byte0;
                    byte byte1;
                    if (obj3 == null)
                    {
                        obj3 = Absent.INSTANCE;
                    } else
                    {
                        obj3 = new Present(obj3);
                    }
                    s2 = (String)((Optional) (obj3)).transform(.Lambda._cls21..instance).orNull();
                    obj3 = ((Property) (attendee)).parameters.getParameter("CUTYPE");
                    if (obj3 == null)
                    {
                        obj3 = Absent.INSTANCE;
                    } else
                    {
                        obj3 = new Present(obj3);
                    }
                    obj3 = (String)((Optional) (obj3)).transform(.Lambda._cls22..instance).orNull();
                    if (CuType.ROOM.getValue().equals(obj3) || CuType.RESOURCE.getValue().equals(obj3))
                    {
                        byte0 = 3;
                    } else
                    if (CuType.GROUP.getValue().equals(obj3))
                    {
                        byte0 = 2;
                    } else
                    {
                        byte0 = 1;
                    }
                    obj3 = ((Property) (attendee)).parameters.getParameter("ROLE");
                    if (obj3 == null)
                    {
                        obj3 = Absent.INSTANCE;
                    } else
                    {
                        obj3 = new Present(obj3);
                    }
                    obj3 = (String)((Optional) (obj3)).transform(.Lambda._cls23..instance).orNull();
                    if (Role.OPT_PARTICIPANT.getValue().equals(obj3) && byte0 != 3)
                    {
                        byte1 = 2;
                    } else
                    {
                        byte1 = 1;
                    }
                    obj3 = new AttendeeDescriptor(s1);
                    if (!RemoteFeatureConfig.THIRD_PARTY_RESOURCE_SUPPORT.enabled())
                    {
                        byte1 = 1;
                    }
                    if (!RemoteFeatureConfig.THIRD_PARTY_RESOURCE_SUPPORT.enabled())
                    {
                        byte0 = 1;
                    }
                    obj3 = new com.google.android.calendar.api.event.attendee.Attendee(((AttendeeDescriptor) (obj3)), s2, byte1, byte0, ICalUtils.getAttendeeResponse(attendee));
                    eventmodifications.getAttendeeModifications().addAttendee(((com.google.android.calendar.api.event.attendee.Attendee) (obj3)));
                }
            }
        } while (true);
        return ICalEventOperation.create(collection);
    }

    final OperationLoader lambda$parseUri$0$ICalEventReader(ContentResolver contentresolver, Uri uri)
        throws Exception
    {
        Object obj;
        class .Lambda._cls1
            implements OperationLoader
        {

            private final ICalEventReader arg$1;
            private final Calendar arg$2;

            public final ListenableFuture loadEvents(CalendarListEntry calendarlistentry)
            {
                ICalEventReader icaleventreader = arg$1;
                Calendar calendar = arg$2;
                Object obj1 = calendar.components.getComponents("VEVENT");
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                if (net/fortuna/ical4j/model/component/VEvent == null)
                {
                    throw new NullPointerException();
                }
                com.google.common.base.Predicates.InstanceOfPredicate instanceofpredicate = new com.google.common.base.Predicates.InstanceOfPredicate(net/fortuna/ical4j/model/component/VEvent);
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                if (instanceofpredicate == null)
                {
                    throw new NullPointerException();
                }
                obj1 = new com.google.common.collect.Iterables._cls4(((Iterable) (obj1)), instanceofpredicate);
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                obj1 = Lists.newArrayList(new com.google.common.collect.Iterables._cls7(((Iterable) (obj1)), 200));
                class .Lambda._cls2
                    implements Function
                {

                    private final ICalEventReader arg$1;
                    private final Calendar arg$2;
                    private final CalendarListEntry arg$3;

                    public final Object apply(Object obj2)
                    {
                        ICalEventReader icaleventreader1 = arg$1;
                        Object obj3 = arg$2;
                        CalendarListEntry calendarlistentry1 = arg$3;
                        VEvent vevent = (VEvent)obj2;
                        obj2 = (Method)((Calendar) (obj3)).properties.getProperty("METHOD");
                        class .Lambda._cls26
                            implements Function
                        {

                            public static final Function $instance = new .Lambda._cls26();

                            public final Object apply(Object obj4)
                            {
                                return ((Content)(Method)obj4).getValue();
                            }


                                private .Lambda._cls26()
                                {
                                }
                        }

                        class .Lambda._cls27
                            implements Function
                        {

                            public static final Function $instance = new .Lambda._cls27();

                            public final Object apply(Object obj4)
                            {
                                return ((Content)(Uid)obj4).getValue();
                            }


                                private .Lambda._cls27()
                                {
                                }
                        }

                        class .Lambda._cls28
                            implements Function
                        {

                            public static final Function $instance = new .Lambda._cls28();

                            public final Object apply(Object obj4)
                            {
                                return ((DateProperty)(RecurrenceId)obj4).date;
                            }


                                private .Lambda._cls28()
                                {
                                }
                        }

                        class .Lambda._cls29
                            implements Function
                        {

                            public static final Function $instance = new .Lambda._cls29();

                            public final Object apply(Object obj4)
                            {
                                return Long.valueOf(((java.util.Date)(Date)obj4).getTime());
                            }


                                private .Lambda._cls29()
                                {
                                }
                        }

                        String s;
                        if (obj2 == null)
                        {
                            obj2 = Absent.INSTANCE;
                        } else
                        {
                            obj2 = new Present(obj2);
                        }
                        obj3 = (String)((Optional) (obj2)).transform(.Lambda._cls26..instance).or("PUBLISH");
                        obj2 = (Uid)((Component) (vevent)).properties.getProperty("UID");
                        if (obj2 == null)
                        {
                            obj2 = Absent.INSTANCE;
                        } else
                        {
                            obj2 = new Present(obj2);
                        }
                        s = (String)((Optional) (obj2)).transform(.Lambda._cls27..instance).orNull();
                        obj2 = (RecurrenceId)((Component) (vevent)).properties.getProperty("RECURRENCE-ID");
                        if (obj2 == null)
                        {
                            obj2 = Absent.INSTANCE;
                        } else
                        {
                            obj2 = new Present(obj2);
                        }
                        obj2 = ((Optional) (obj2)).transform(.Lambda._cls28..instance).transform(.Lambda._cls29..instance);
                        class .Lambda._cls30
                            implements Function
                        {

                            private final ICalEventReader arg$1;
                            private final Optional arg$2;

                            public final Object apply(Object obj4)
                            {
                                Object obj5 = arg$1;
                                obj5 = arg$2;
                                obj4 = (Event[])obj4;
                                obj5 = (Long)((Optional) (obj5)).orNull();
                                HashMap hashmap = new HashMap(obj4.length);
                                int j = obj4.length;
                                for (int i = 0; i < j; i++)
                                {
                                    Event event = obj4[i];
                                    if (event.getDescriptor().isRecurringException() && !ICalEventReader.originalStartMatches(event, ((Long) (obj5))))
                                    {
                                        continue;
                                    }
                                    com.google.android.calendar.api.calendarlist.CalendarDescriptor calendardescriptor = event.getCalendarListEntry().getDescriptor();
                                    if (!hashmap.containsKey(calendardescriptor) || ICalEventReader.originalStartMatches(event, ((Long) (obj5))))
                                    {
                                        hashmap.put(calendardescriptor, event);
                                    }
                                }

                                return hashmap.values();
                            }

                                .Lambda._cls30(Optional optional)
                                {
                                    arg$1 = ICalEventReader.this;
                                    arg$2 = optional;
                                }
                        }

                        if (s == null)
                        {
                            obj2 = Collections.emptyList();
                            class .Lambda._cls31
                                implements Function
                            {

                                private final ICalEventReader arg$1;
                                private final CalendarListEntry arg$2;
                                private final VEvent arg$3;
                                private final String arg$4;
                                private final String arg$5;

                                public final Object apply(Object obj4)
                                {
                                    ICalEventReader icaleventreader2 = arg$1;
                                    CalendarListEntry calendarlistentry2 = arg$2;
                                    VEvent vevent1 = arg$3;
                                    String s2 = arg$4;
                                    String s1 = arg$5;
                                    Collection collection = (Collection)obj4;
                                    obj4 = (DtStamp)((Component) (vevent1)).properties.getProperty("DTSTAMP");
                                    class .Lambda._cls3
                                        implements Function
                                    {

                                        public static final Function $instance = new .Lambda._cls3();

                                        public final Object apply(Object obj5)
                                        {
                                            return ICalEventReader.lambda$createICalEventOperations$5$ICalEventReader((DtStamp)obj5);
                                        }


                                            private .Lambda._cls3()
                                            {
                                            }
                                    }

                                    class .Lambda._cls4
                                        implements Function
                                    {

                                        public static final Function $instance = new .Lambda._cls4();

                                        public final Object apply(Object obj5)
                                        {
                                            return Integer.valueOf(((Sequence)obj5).sequenceNo);
                                        }


                                            private .Lambda._cls4()
                                            {
                                            }
                                    }

                                    String s3;
                                    int i;
                                    if (obj4 == null)
                                    {
                                        obj4 = Absent.INSTANCE;
                                    } else
                                    {
                                        obj4 = new Present(obj4);
                                    }
                                    s3 = (String)((Optional) (obj4)).transform(.Lambda._cls3..instance).orNull();
                                    obj4 = (Sequence)((Component) (vevent1)).properties.getProperty("SEQUENCE");
                                    if (obj4 == null)
                                    {
                                        obj4 = Absent.INSTANCE;
                                    } else
                                    {
                                        obj4 = new Present(obj4);
                                    }
                                    i = ((Integer)((Optional) (obj4)).transform(.Lambda._cls4..instance).or(Integer.valueOf(0))).intValue();
                                    if ((Organizer)((Component) (vevent1)).properties.getProperty("ORGANIZER") != null && !"PUBLISH".equals(s1))
                                    {
                                        obj4 = ICalEventReader.getEmailWithoutScheme(((Organizer)((Component) (vevent1)).properties.getProperty("ORGANIZER")).getValue());
                                    } else
                                    {
                                        obj4 = null;
                                    }
                                    class .Lambda._cls5
                                        implements Function
                                    {

                                        private final String arg$1;
                                        private final int arg$2;
                                        private final String arg$3;

                                        public final Object apply(Object obj5)
                                        {
                                            return ICalEventReader.lambda$createICalEventOperations$6$ICalEventReader(arg$1, arg$2, arg$3, (Event)obj5);
                                        }

                                            .Lambda._cls5(String s, int i, String s1)
                                            {
                                                arg$1 = s;
                                                arg$2 = i;
                                                arg$3 = s1;
                                            }
                                    }

                                    if (collection.isEmpty())
                                    {
                                        if (s2 == null)
                                        {
                                            obj4 = CalendarApi.EventFactory.newEvent(calendarlistentry2);
                                        } else
                                        {
                                            obj4 = CalendarApi.EventFactory.newIcsImport(calendarlistentry2, ((String) (obj4)), s2, i, s3);
                                        }
                                        return icaleventreader2.createICalEventOperation(Collections.singletonList(obj4), vevent1, s1);
                                    } else
                                    {
                                        return icaleventreader2.createICalEventOperation(Lists.newArrayList(new com.google.common.collect.Collections2.TransformedCollection(collection, new .Lambda._cls5(((String) (obj4)), i, s3))), vevent1, s1);
                                    }
                                }

                                .Lambda._cls31(CalendarListEntry calendarlistentry, VEvent vevent, String s, String s1)
                                {
                                    arg$1 = ICalEventReader.this;
                                    arg$2 = calendarlistentry;
                                    arg$3 = vevent;
                                    arg$4 = s;
                                    arg$5 = s1;
                                }
                            }

                            if (obj2 == null)
                            {
                                obj2 = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                            } else
                            {
                                obj2 = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj2);
                            }
                        } else
                        {
                            obj2 = AbstractTransformFuture.create(icaleventreader1.eventClient.icsList(Collections.singleton(s)), icaleventreader1. new .Lambda._cls30(((Optional) (obj2))), CalendarExecutor.BACKGROUND);
                        }
                        return AbstractTransformFuture.create(((ListenableFuture) (obj2)), icaleventreader1. new .Lambda._cls31(calendarlistentry1, vevent, s, ((String) (obj3))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                    }

                        .Lambda._cls2(Calendar calendar, CalendarListEntry calendarlistentry)
                        {
                            arg$1 = ICalEventReader.this;
                            arg$2 = calendar;
                            arg$3 = calendarlistentry;
                        }
                }

                calendarlistentry = icaleventreader. new .Lambda._cls2(calendar, calendarlistentry);
                if (obj1 instanceof RandomAccess)
                {
                    calendarlistentry = new com.google.common.collect.Lists.TransformingRandomAccessList(((java.util.List) (obj1)), calendarlistentry);
                } else
                {
                    calendarlistentry = new com.google.common.collect.Lists.TransformingSequentialList(((java.util.List) (obj1)), calendarlistentry);
                }
                return new com.google.common.util.concurrent.CollectionFuture.ListFuture(ImmutableList.copyOf(calendarlistentry), true);
            }

            .Lambda._cls1(Calendar calendar)
            {
                arg$1 = ICalEventReader.this;
                arg$2 = calendar;
            }
        }

        try
        {
            obj = contentresolver.openInputStream(uri);
        }
        // Misplaced declaration of an exception variable
        catch (ContentResolver contentresolver)
        {
            LogUtils.e(TAG, contentresolver, "Reading iCal file", new Object[0]);
            throw contentresolver;
        }
        // Misplaced declaration of an exception variable
        catch (ContentResolver contentresolver)
        {
            LogUtils.e(TAG, contentresolver, "Parsing iCal file", new Object[0]);
            throw contentresolver;
        }
        uri = null;
        contentresolver = new .Lambda._cls1((new CalendarBuilderWrapper(com.google.android.apps.common.ical4jutils.CalendarBuilderWrapper.DateUtils.timeZoneRegistryFactory.createRegistry())).build(new UnfoldingReader(new InputStreamReader(((InputStream) (obj)), CalendarBuilder.DEFAULT_CHARSET))));
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        ((InputStream) (obj)).close();
        return contentresolver;
        uri;
        throw uri;
        contentresolver;
_L3:
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        if (uri == null)
        {
            break MISSING_BLOCK_LABEL_127;
        }
        ((InputStream) (obj)).close();
_L1:
        throw contentresolver;
        obj;
        ThrowableExtension.STRATEGY.addSuppressed(uri, ((Throwable) (obj)));
          goto _L1
        ((InputStream) (obj)).close();
          goto _L1
        contentresolver;
        if (true) goto _L3; else goto _L2
_L2:
    }

}
