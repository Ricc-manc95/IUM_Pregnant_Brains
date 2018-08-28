// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.util.Map;
import net.fortuna.ical4j.model.property.XProperty;
import net.fortuna.ical4j.util.CompatibilityHints;

// Referenced classes of package net.fortuna.ical4j.model:
//            AbstractContentFactory, PropertyFactory, Property

public class PropertyFactoryImpl extends AbstractContentFactory
    implements PropertyFactory
{

    public static PropertyFactoryImpl instance = new PropertyFactoryImpl();
    public static final long serialVersionUID = 0x9c700200ac509fc7L;

    protected PropertyFactoryImpl()
    {
        Object obj = new ActionFactory();
        super.defaultFactories.put("ACTION", obj);
        obj = new AttachFactory();
        super.defaultFactories.put("ATTACH", obj);
        obj = new AttendeeFactory();
        super.defaultFactories.put("ATTENDEE", obj);
        obj = new CalScaleFactory();
        super.defaultFactories.put("CALSCALE", obj);
        obj = new CategoriesFactory();
        super.defaultFactories.put("CATEGORIES", obj);
        obj = new ClazzFactory();
        super.defaultFactories.put("CLASS", obj);
        obj = new CommentFactory();
        super.defaultFactories.put("COMMENT", obj);
        obj = new CompletedFactory();
        super.defaultFactories.put("COMPLETED", obj);
        obj = new ContactFactory();
        super.defaultFactories.put("CONTACT", obj);
        obj = new CountryFactory();
        super.defaultFactories.put("COUNTRY", obj);
        obj = new CreatedFactory();
        super.defaultFactories.put("CREATED", obj);
        obj = new DescriptionFactory();
        super.defaultFactories.put("DESCRIPTION", obj);
        obj = new DtEndFactory();
        super.defaultFactories.put("DTEND", obj);
        obj = new DtStampFactory();
        super.defaultFactories.put("DTSTAMP", obj);
        obj = new DtStartFactory();
        super.defaultFactories.put("DTSTART", obj);
        obj = new DueFactory();
        super.defaultFactories.put("DUE", obj);
        obj = new DurationFactory();
        super.defaultFactories.put("DURATION", obj);
        obj = new ExDateFactory();
        super.defaultFactories.put("EXDATE", obj);
        obj = new ExRuleFactory();
        super.defaultFactories.put("EXRULE", obj);
        obj = new ExtendedAddressFactory();
        super.defaultFactories.put("EXTENDED-ADDRESS", obj);
        obj = new FreeBusyFactory();
        super.defaultFactories.put("FREEBUSY", obj);
        obj = new GeoFactory();
        super.defaultFactories.put("GEO", obj);
        obj = new LastModifiedFactory();
        super.defaultFactories.put("LAST-MODIFIED", obj);
        obj = new LocalityFactory();
        super.defaultFactories.put("LOCALITY", obj);
        obj = new LocationFactory();
        super.defaultFactories.put("LOCATION", obj);
        obj = new LocationTypeFactory();
        super.defaultFactories.put("LOCATION-TYPE", obj);
        obj = new MethodFactory();
        super.defaultFactories.put("METHOD", obj);
        obj = new NameFactory();
        super.defaultFactories.put("NAME", obj);
        obj = new OrganizerFactory();
        super.defaultFactories.put("ORGANIZER", obj);
        obj = new PercentCompleteFactory();
        super.defaultFactories.put("PERCENT-COMPLETE", obj);
        obj = new PostalcodeFactory();
        super.defaultFactories.put("POSTAL-CODE", obj);
        obj = new PriorityFactory();
        super.defaultFactories.put("PRIORITY", obj);
        obj = new ProdIdFactory();
        super.defaultFactories.put("PRODID", obj);
        obj = new RDateFactory();
        super.defaultFactories.put("RDATE", obj);
        obj = new RecurrenceIdFactory();
        super.defaultFactories.put("RECURRENCE-ID", obj);
        obj = new RegionFactory();
        super.defaultFactories.put("REGION", obj);
        obj = new RelatedToFactory();
        super.defaultFactories.put("RELATED-TO", obj);
        obj = new RepeatFactory();
        super.defaultFactories.put("REPEAT", obj);
        obj = new RequestStatusFactory();
        super.defaultFactories.put("REQUEST-STATUS", obj);
        obj = new ResourcesFactory();
        super.defaultFactories.put("RESOURCES", obj);
        obj = new RRuleFactory();
        super.defaultFactories.put("RRULE", obj);
        obj = new SequenceFactory();
        super.defaultFactories.put("SEQUENCE", obj);
        obj = new StatusFactory();
        super.defaultFactories.put("STATUS", obj);
        obj = new StreetAddressFactory();
        super.defaultFactories.put("STREET-ADDRESS", obj);
        obj = new SummaryFactory();
        super.defaultFactories.put("SUMMARY", obj);
        obj = new TelFactory();
        super.defaultFactories.put("TEL", obj);
        obj = new TranspFactory();
        super.defaultFactories.put("TRANSP", obj);
        obj = new TriggerFactory();
        super.defaultFactories.put("TRIGGER", obj);
        obj = new TzIdFactory();
        super.defaultFactories.put("TZID", obj);
        obj = new TzNameFactory();
        super.defaultFactories.put("TZNAME", obj);
        obj = new TzOffsetFromFactory();
        super.defaultFactories.put("TZOFFSETFROM", obj);
        obj = new TzOffsetToFactory();
        super.defaultFactories.put("TZOFFSETTO", obj);
        obj = new TzUrlFactory();
        super.defaultFactories.put("TZURL", obj);
        obj = new UidFactory();
        super.defaultFactories.put("UID", obj);
        obj = new UrlFactory();
        super.defaultFactories.put("URL", obj);
        obj = new VersionFactory();
        super.defaultFactories.put("VERSION", obj);
    }

    public final Property createProperty(String s)
    {
        Object obj1 = super.defaultFactories.get(s);
        Object obj = obj1;
        if (obj1 == null)
        {
            obj = super.extendedFactories.get(s);
        }
        obj = (PropertyFactory)obj;
        if (obj != null)
        {
            return ((PropertyFactory) (obj)).createProperty(s);
        }
        boolean flag;
        if (s.startsWith("X-") && s.length() > 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return new XProperty(s);
        }
        if (CompatibilityHints.isHintEnabled("ical4j.parsing.relaxed"))
        {
            return new XProperty(s);
        } else
        {
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 19)).append("Illegal property [").append(s).append("]").toString());
        }
    }


    private class ActionFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Action();
        }

        ActionFactory()
        {
        }
    }


    private class AttachFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Attach();
        }

        AttachFactory()
        {
        }
    }


    private class AttendeeFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Attendee();
        }

        AttendeeFactory()
        {
        }
    }


    private class CalScaleFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new CalScale();
        }

        CalScaleFactory()
        {
        }
    }


    private class CategoriesFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Categories();
        }

        CategoriesFactory()
        {
        }
    }


    private class ClazzFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Clazz();
        }

        ClazzFactory()
        {
        }
    }


    private class CommentFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Comment();
        }

        CommentFactory()
        {
        }
    }


    private class CompletedFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Completed();
        }

        CompletedFactory()
        {
        }
    }


    private class ContactFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Contact();
        }

        ContactFactory()
        {
        }
    }


    private class CountryFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Country();
        }

        CountryFactory()
        {
        }
    }


    private class CreatedFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Created();
        }

        CreatedFactory()
        {
        }
    }


    private class DescriptionFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Description();
        }

        DescriptionFactory()
        {
        }
    }


    private class DtEndFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new DtEnd();
        }

        DtEndFactory()
        {
        }
    }


    private class DtStampFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new DtStamp();
        }

        DtStampFactory()
        {
        }
    }


    private class DtStartFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new DtStart();
        }

        DtStartFactory()
        {
        }
    }


    private class DueFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Due();
        }

        DueFactory()
        {
        }
    }


    private class DurationFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Duration();
        }

        DurationFactory()
        {
        }
    }


    private class ExDateFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new ExDate();
        }

        ExDateFactory()
        {
        }
    }


    private class ExRuleFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new ExRule();
        }

        ExRuleFactory()
        {
        }
    }


    private class ExtendedAddressFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new ExtendedAddress();
        }

        ExtendedAddressFactory()
        {
        }
    }


    private class FreeBusyFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new FreeBusy();
        }

        FreeBusyFactory()
        {
        }
    }


    private class GeoFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Geo();
        }

        GeoFactory()
        {
        }
    }


    private class LastModifiedFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new LastModified();
        }

        LastModifiedFactory()
        {
        }
    }


    private class LocalityFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Locality();
        }

        LocalityFactory()
        {
        }
    }


    private class LocationFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Location();
        }

        LocationFactory()
        {
        }
    }


    private class LocationTypeFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new LocationType();
        }

        LocationTypeFactory()
        {
        }
    }


    private class MethodFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Method();
        }

        MethodFactory()
        {
        }
    }


    private class NameFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Name();
        }

        NameFactory()
        {
        }
    }


    private class OrganizerFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Organizer();
        }

        OrganizerFactory()
        {
        }
    }


    private class PercentCompleteFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new PercentComplete();
        }

        PercentCompleteFactory()
        {
        }
    }


    private class PostalcodeFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Postalcode();
        }

        PostalcodeFactory()
        {
        }
    }


    private class PriorityFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Priority();
        }

        PriorityFactory()
        {
        }
    }


    private class ProdIdFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new ProdId();
        }

        ProdIdFactory()
        {
        }
    }


    private class RDateFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new RDate();
        }

        RDateFactory()
        {
        }
    }


    private class RecurrenceIdFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new RecurrenceId();
        }

        RecurrenceIdFactory()
        {
        }
    }


    private class RegionFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Region();
        }

        RegionFactory()
        {
        }
    }


    private class RelatedToFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new RelatedTo();
        }

        RelatedToFactory()
        {
        }
    }


    private class RepeatFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Repeat();
        }

        RepeatFactory()
        {
        }
    }


    private class RequestStatusFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new RequestStatus();
        }

        RequestStatusFactory()
        {
        }
    }


    private class ResourcesFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Resources();
        }

        ResourcesFactory()
        {
        }
    }


    private class RRuleFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new RRule();
        }

        RRuleFactory()
        {
        }
    }


    private class SequenceFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Sequence();
        }

        SequenceFactory()
        {
        }
    }


    private class StatusFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Status();
        }

        StatusFactory()
        {
        }
    }


    private class StreetAddressFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new StreetAddress();
        }

        StreetAddressFactory()
        {
        }
    }


    private class SummaryFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Summary();
        }

        SummaryFactory()
        {
        }
    }


    private class TelFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Tel();
        }

        TelFactory()
        {
        }
    }


    private class TranspFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Transp();
        }

        TranspFactory()
        {
        }
    }


    private class TriggerFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Trigger();
        }

        TriggerFactory()
        {
        }
    }


    private class TzIdFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new TzId();
        }

        TzIdFactory()
        {
        }
    }


    private class TzNameFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new TzName();
        }

        TzNameFactory()
        {
        }
    }


    private class TzOffsetFromFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new TzOffsetFrom();
        }

        TzOffsetFromFactory()
        {
        }
    }


    private class TzOffsetToFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new TzOffsetTo();
        }

        TzOffsetToFactory()
        {
        }
    }


    private class TzUrlFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new TzUrl();
        }

        TzUrlFactory()
        {
        }
    }


    private class UidFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Uid();
        }

        UidFactory()
        {
        }
    }


    private class UrlFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Url();
        }

        UrlFactory()
        {
        }
    }


    private class VersionFactory
        implements PropertyFactory
    {

        public static final long serialVersionUID = 1L;

        public final Property createProperty(String s)
        {
            return new Version();
        }

        VersionFactory()
        {
        }
    }

}
