// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.data;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.CalendarException;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentFactory;
import net.fortuna.ical4j.model.Content;
import net.fortuna.ical4j.model.ParameterFactoryRegistry;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.property.DateListProperty;
import net.fortuna.ical4j.model.property.DateProperty;

// Referenced classes of package net.fortuna.ical4j.data:
//            CalendarParserFactory, ParserException, CalendarParser, ContentHandler, 
//            UnfoldingReader

public class CalendarBuilder
{

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    public Calendar calendar;
    public Component component;
    private final ContentHandler contentHandler;
    private List datesMissingTimezones;
    private final CalendarParser parser;
    public Property property;
    public Component subComponent;
    public final TimeZoneRegistry tzRegistry;

    public CalendarBuilder()
    {
        this(CalendarParserFactory.instance.createParser(), new PropertyFactoryRegistry(), new ParameterFactoryRegistry(), TimeZoneRegistryFactory.instance.createRegistry());
    }

    private CalendarBuilder(CalendarParser calendarparser, PropertyFactoryRegistry propertyfactoryregistry, ParameterFactoryRegistry parameterfactoryregistry, TimeZoneRegistry timezoneregistry)
    {
        parser = calendarparser;
        tzRegistry = timezoneregistry;
        contentHandler = new ContentHandlerImpl(ComponentFactory.instance, propertyfactoryregistry, parameterfactoryregistry);
    }

    public CalendarBuilder(TimeZoneRegistry timezoneregistry)
    {
        this(CalendarParserFactory.instance.createParser(), new PropertyFactoryRegistry(), new ParameterFactoryRegistry(), timezoneregistry);
    }

    static void assertComponent(Component component1)
    {
        if (component1 == null)
        {
            throw new CalendarException("Expected component not initialised");
        } else
        {
            return;
        }
    }

    static void assertProperty(Property property1)
    {
        if (property1 == null)
        {
            throw new CalendarException("Expected property not initialised");
        } else
        {
            return;
        }
    }

    public Calendar build(UnfoldingReader unfoldingreader)
        throws IOException, ParserException
    {
        calendar = null;
        component = null;
        subComponent = null;
        property = null;
        datesMissingTimezones = new ArrayList();
        parser.parse(unfoldingreader, contentHandler);
        if (datesMissingTimezones.size() > 0 && tzRegistry != null)
        {
            for (unfoldingreader = datesMissingTimezones.iterator(); unfoldingreader.hasNext();)
            {
                Property property1 = (Property)unfoldingreader.next();
                Object obj = property1.parameters.getParameter("TZID");
                if (obj != null)
                {
                    obj = tzRegistry.getTimeZone(((Content) (obj)).getValue());
                    if (obj != null)
                    {
                        String s = property1.getValue();
                        if (property1 instanceof DateProperty)
                        {
                            ((DateProperty)property1).setTimeZone(((TimeZone) (obj)));
                        } else
                        if (property1 instanceof DateListProperty)
                        {
                            ((DateListProperty)property1).setTimeZone(((TimeZone) (obj)));
                        }
                        try
                        {
                            property1.setValue(s);
                        }
                        // Misplaced declaration of an exception variable
                        catch (UnfoldingReader unfoldingreader)
                        {
                            throw new CalendarException(unfoldingreader);
                        }
                        // Misplaced declaration of an exception variable
                        catch (UnfoldingReader unfoldingreader)
                        {
                            throw new CalendarException(unfoldingreader);
                        }
                    }
                }
            }

        }
        return calendar;
    }


    private class ContentHandlerImpl
        implements ContentHandler
    {

        private final ComponentFactory componentFactory;
        private final ParameterFactory parameterFactory;
        private final PropertyFactory propertyFactory;
        private final CalendarBuilder this$0;

        public final void endCalendar()
        {
        }

        public final void endComponent$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0()
        {
            CalendarBuilder calendarbuilder = CalendarBuilder.this;
            CalendarBuilder.assertComponent(component);
            if (subComponent == null) goto _L2; else goto _L1
_L1:
            if (!(component instanceof VTimeZone)) goto _L4; else goto _L3
_L3:
            ((VTimeZone)component).observances.add(subComponent);
_L5:
            subComponent = null;
            return;
_L4:
            if (component instanceof VEvent)
            {
                ((VEvent)component).alarms.add(subComponent);
            } else
            if (component instanceof VToDo)
            {
                ((VToDo)component).alarms.add(subComponent);
            } else
            if (component instanceof VAvailability)
            {
                ((VAvailability)component).available.add(subComponent);
            }
            if (true) goto _L5; else goto _L2
_L2:
            calendar.components.add(component);
            if ((component instanceof VTimeZone) && tzRegistry != null)
            {
                tzRegistry.register(new TimeZone((VTimeZone)component));
            }
            component = null;
            return;
        }

        public final void endProperty$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0()
        {
            Object obj = CalendarBuilder.this;
            CalendarBuilder.assertProperty(property);
            CalendarBuilder calendarbuilder = CalendarBuilder.this;
            Property property1 = property;
            if (Action.AUDIO.equals(property1))
            {
                obj = Action.AUDIO;
            } else
            if (Action.DISPLAY.equals(property1))
            {
                obj = Action.DISPLAY;
            } else
            if (Action.EMAIL.equals(property1))
            {
                obj = Action.EMAIL;
            } else
            if (Action.PROCEDURE.equals(property1))
            {
                obj = Action.PROCEDURE;
            } else
            if (CalScale.GREGORIAN.equals(property1))
            {
                obj = CalScale.GREGORIAN;
            } else
            if (Clazz.CONFIDENTIAL.equals(property1))
            {
                obj = Clazz.CONFIDENTIAL;
            } else
            if (Clazz.PRIVATE.equals(property1))
            {
                obj = Clazz.PRIVATE;
            } else
            if (Clazz.PUBLIC.equals(property1))
            {
                obj = Clazz.PUBLIC;
            } else
            if (Method.ADD.equals(property1))
            {
                obj = Method.ADD;
            } else
            if (Method.CANCEL.equals(property1))
            {
                obj = Method.CANCEL;
            } else
            if (Method.COUNTER.equals(property1))
            {
                obj = Method.COUNTER;
            } else
            if (Method.DECLINE_COUNTER.equals(property1))
            {
                obj = Method.DECLINE_COUNTER;
            } else
            if (Method.PUBLISH.equals(property1))
            {
                obj = Method.PUBLISH;
            } else
            if (Method.REFRESH.equals(property1))
            {
                obj = Method.REFRESH;
            } else
            if (Method.REPLY.equals(property1))
            {
                obj = Method.REPLY;
            } else
            if (Method.REQUEST.equals(property1))
            {
                obj = Method.REQUEST;
            } else
            if (Priority.HIGH.equals(property1))
            {
                obj = Priority.HIGH;
            } else
            if (Priority.LOW.equals(property1))
            {
                obj = Priority.LOW;
            } else
            if (Priority.MEDIUM.equals(property1))
            {
                obj = Priority.MEDIUM;
            } else
            if (Priority.UNDEFINED.equals(property1))
            {
                obj = Priority.UNDEFINED;
            } else
            if (Status.VEVENT_CANCELLED.equals(property1))
            {
                obj = Status.VEVENT_CANCELLED;
            } else
            if (Status.VEVENT_CONFIRMED.equals(property1))
            {
                obj = Status.VEVENT_CONFIRMED;
            } else
            if (Status.VEVENT_TENTATIVE.equals(property1))
            {
                obj = Status.VEVENT_TENTATIVE;
            } else
            if (Status.VJOURNAL_CANCELLED.equals(property1))
            {
                obj = Status.VJOURNAL_CANCELLED;
            } else
            if (Status.VJOURNAL_DRAFT.equals(property1))
            {
                obj = Status.VJOURNAL_DRAFT;
            } else
            if (Status.VJOURNAL_FINAL.equals(property1))
            {
                obj = Status.VJOURNAL_FINAL;
            } else
            if (Status.VTODO_CANCELLED.equals(property1))
            {
                obj = Status.VTODO_CANCELLED;
            } else
            if (Status.VTODO_COMPLETED.equals(property1))
            {
                obj = Status.VTODO_COMPLETED;
            } else
            if (Status.VTODO_IN_PROCESS.equals(property1))
            {
                obj = Status.VTODO_IN_PROCESS;
            } else
            if (Status.VTODO_NEEDS_ACTION.equals(property1))
            {
                obj = Status.VTODO_NEEDS_ACTION;
            } else
            if (Transp.OPAQUE.equals(property1))
            {
                obj = Transp.OPAQUE;
            } else
            if (Transp.TRANSPARENT.equals(property1))
            {
                obj = Transp.TRANSPARENT;
            } else
            {
                obj = property1;
                if (Version.VERSION_2_0.equals(property1))
                {
                    obj = Version.VERSION_2_0;
                }
            }
            calendarbuilder.property = ((Property) (obj));
            if (component != null)
            {
                if (subComponent != null)
                {
                    subComponent.properties.add(property);
                } else
                {
                    component.properties.add(property);
                }
            } else
            if (calendar != null)
            {
                calendar.properties.add(property);
            }
            property = null;
        }

        public final void parameter(String s, String s1)
            throws URISyntaxException
        {
            CalendarBuilder calendarbuilder = CalendarBuilder.this;
            CalendarBuilder.assertProperty(property);
            s = parameterFactory.createParameter(s.toUpperCase(), Strings.escapeNewline(s1));
            s1 = property.parameters;
            if (s == null)
            {
                try
                {
                    throw new IllegalArgumentException("Trying to add null Parameter");
                }
                // Misplaced declaration of an exception variable
                catch (String s) { }
                if (!property.name.startsWith("X-"))
                {
                    throw s;
                }
                break MISSING_BLOCK_LABEL_92;
            }
            ((ParameterList) (s1)).parameters.add(s);
        }

        public final void propertyValue(String s)
            throws URISyntaxException, ParseException, IOException
        {
            CalendarBuilder calendarbuilder = CalendarBuilder.this;
            CalendarBuilder.assertProperty(property);
            if (property instanceof Escapable)
            {
                property.setValue(Strings.unescape(s));
                return;
            } else
            {
                property.setValue(s);
                return;
            }
        }

        public final void startCalendar()
        {
            calendar = new Calendar();
        }

        public final void startComponent(String s)
        {
            if (component != null)
            {
                CalendarBuilder calendarbuilder = CalendarBuilder.this;
                Object obj = componentFactory;
                obj = new PropertyList();
                if ("VALARM".equals(s))
                {
                    s = new VAlarm(((PropertyList) (obj)));
                } else
                if ("VEVENT".equals(s))
                {
                    s = new VEvent(((PropertyList) (obj)));
                } else
                if ("VFREEBUSY".equals(s))
                {
                    s = new VFreeBusy(((PropertyList) (obj)));
                } else
                if ("VJOURNAL".equals(s))
                {
                    s = new VJournal(((PropertyList) (obj)));
                } else
                if ("VTODO".equals(s))
                {
                    s = new VToDo(((PropertyList) (obj)));
                } else
                if ("STANDARD".equals(s))
                {
                    s = new Standard(((PropertyList) (obj)));
                } else
                if ("DAYLIGHT".equals(s))
                {
                    s = new Daylight(((PropertyList) (obj)));
                } else
                if ("VTIMEZONE".equals(s))
                {
                    s = new VTimeZone(((PropertyList) (obj)));
                } else
                if ("VVENUE".equals(s))
                {
                    s = new VVenue(((PropertyList) (obj)));
                } else
                if ("VAVAILABILITY".equals(s))
                {
                    s = new VAvailability(((PropertyList) (obj)));
                } else
                if ("AVAILABLE".equals(s))
                {
                    s = new Available(((PropertyList) (obj)));
                } else
                if (ComponentFactory.isExperimentalName(s))
                {
                    s = new XComponent(s, ((PropertyList) (obj)));
                } else
                if (ComponentFactory.allowIllegalNames())
                {
                    s = new XComponent(s, ((PropertyList) (obj)));
                } else
                {
                    throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 20)).append("Illegal component [").append(s).append("]").toString());
                }
                calendarbuilder.subComponent = s;
                return;
            }
            CalendarBuilder calendarbuilder1 = CalendarBuilder.this;
            Object obj1 = componentFactory;
            obj1 = new PropertyList();
            if ("VALARM".equals(s))
            {
                s = new VAlarm(((PropertyList) (obj1)));
            } else
            if ("VEVENT".equals(s))
            {
                s = new VEvent(((PropertyList) (obj1)));
            } else
            if ("VFREEBUSY".equals(s))
            {
                s = new VFreeBusy(((PropertyList) (obj1)));
            } else
            if ("VJOURNAL".equals(s))
            {
                s = new VJournal(((PropertyList) (obj1)));
            } else
            if ("VTODO".equals(s))
            {
                s = new VToDo(((PropertyList) (obj1)));
            } else
            if ("STANDARD".equals(s))
            {
                s = new Standard(((PropertyList) (obj1)));
            } else
            if ("DAYLIGHT".equals(s))
            {
                s = new Daylight(((PropertyList) (obj1)));
            } else
            if ("VTIMEZONE".equals(s))
            {
                s = new VTimeZone(((PropertyList) (obj1)));
            } else
            if ("VVENUE".equals(s))
            {
                s = new VVenue(((PropertyList) (obj1)));
            } else
            if ("VAVAILABILITY".equals(s))
            {
                s = new VAvailability(((PropertyList) (obj1)));
            } else
            if ("AVAILABLE".equals(s))
            {
                s = new Available(((PropertyList) (obj1)));
            } else
            if (ComponentFactory.isExperimentalName(s))
            {
                s = new XComponent(s, ((PropertyList) (obj1)));
            } else
            if (ComponentFactory.allowIllegalNames())
            {
                s = new XComponent(s, ((PropertyList) (obj1)));
            } else
            {
                throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 20)).append("Illegal component [").append(s).append("]").toString());
            }
            calendarbuilder1.component = s;
        }

        public final void startProperty(String s)
        {
            property = propertyFactory.createProperty(s.toUpperCase());
        }

        public ContentHandlerImpl(ComponentFactory componentfactory, PropertyFactory propertyfactory, ParameterFactory parameterfactory)
        {
            this$0 = CalendarBuilder.this;
            super();
            componentFactory = componentfactory;
            propertyFactory = propertyfactory;
            parameterFactory = parameterfactory;
        }
    }

}
