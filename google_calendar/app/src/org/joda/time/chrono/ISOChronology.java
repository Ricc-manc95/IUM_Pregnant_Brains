// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.field.RemainderDateTimeField;

// Referenced classes of package org.joda.time.chrono:
//            AssembledChronology, GregorianChronology, ZonedChronology, ISOYearOfEraDateTimeField

public final class ISOChronology extends AssembledChronology
{

    public static final ISOChronology INSTANCE_UTC;
    private static final ConcurrentHashMap cCache;
    public static final long serialVersionUID = 0xa9c8116671375027L;

    private ISOChronology(Chronology chronology)
    {
        super(chronology, null);
    }

    public static ISOChronology getInstance()
    {
        return getInstance(DateTimeZone.getDefault());
    }

    public static ISOChronology getInstance(DateTimeZone datetimezone)
    {
        ISOChronology isochronology;
label0:
        {
            DateTimeZone datetimezone1 = datetimezone;
            if (datetimezone == null)
            {
                datetimezone1 = DateTimeZone.getDefault();
            }
            isochronology = (ISOChronology)cCache.get(datetimezone1);
            datetimezone = isochronology;
            if (isochronology == null)
            {
                isochronology = new ISOChronology(ZonedChronology.getInstance(INSTANCE_UTC, datetimezone1));
                datetimezone = (ISOChronology)cCache.putIfAbsent(datetimezone1, isochronology);
                if (datetimezone == null)
                {
                    break label0;
                }
            }
            return datetimezone;
        }
        return isochronology;
    }

    private final Object writeReplace()
    {
        return new Stub(getZone());
    }

    protected final void assemble(AssembledChronology.Fields fields)
    {
        if (super.iBase.getZone() == DateTimeZone.UTC)
        {
            fields.centuryOfEra = new DividedDateTimeField(ISOYearOfEraDateTimeField.INSTANCE, DateTimeFieldType.CENTURY_OF_ERA_TYPE, 100);
            fields.yearOfCentury = new RemainderDateTimeField((DividedDateTimeField)fields.centuryOfEra, DateTimeFieldType.YEAR_OF_CENTURY_TYPE);
            fields.weekyearOfCentury = new RemainderDateTimeField((DividedDateTimeField)fields.centuryOfEra, DateTimeFieldType.WEEKYEAR_OF_CENTURY_TYPE);
            fields.centuries = fields.centuryOfEra.getDurationField();
        }
    }

    public final boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj instanceof ISOChronology)
        {
            obj = (ISOChronology)obj;
            return getZone().equals(((Chronology) (obj)).getZone());
        } else
        {
            return false;
        }
    }

    public final int hashCode()
    {
        return "ISO".hashCode() * 11 + getZone().hashCode();
    }

    public final String toString()
    {
        String s = "ISOChronology";
        DateTimeZone datetimezone = getZone();
        if (datetimezone != null)
        {
            s = datetimezone.iID;
            s = (new StringBuilder(String.valueOf("ISOChronology").length() + 2 + String.valueOf(s).length())).append("ISOChronology").append('[').append(s).append(']').toString();
        }
        return s;
    }

    public final Chronology withUTC()
    {
        return INSTANCE_UTC;
    }

    public final Chronology withZone(DateTimeZone datetimezone)
    {
        DateTimeZone datetimezone1 = datetimezone;
        if (datetimezone == null)
        {
            datetimezone1 = DateTimeZone.getDefault();
        }
        if (datetimezone1 == getZone())
        {
            return this;
        } else
        {
            return getInstance(datetimezone1);
        }
    }

    static 
    {
        cCache = new ConcurrentHashMap();
        INSTANCE_UTC = new ISOChronology(GregorianChronology.INSTANCE_UTC);
        cCache.put(DateTimeZone.UTC, INSTANCE_UTC);
    }

    private class Stub
        implements Serializable
    {

        public static final long serialVersionUID = 0xa9c8116671375027L;
        private transient DateTimeZone iZone;

        private final void readObject(ObjectInputStream objectinputstream)
            throws IOException, ClassNotFoundException
        {
            iZone = (DateTimeZone)objectinputstream.readObject();
        }

        private final Object readResolve()
        {
            return ISOChronology.getInstance(iZone);
        }

        private final void writeObject(ObjectOutputStream objectoutputstream)
            throws IOException
        {
            objectoutputstream.writeObject(iZone);
        }

        Stub(DateTimeZone datetimezone)
        {
            iZone = datetimezone;
        }
    }

}
