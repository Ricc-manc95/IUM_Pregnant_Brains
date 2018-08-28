// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time;

import java.io.Serializable;

// Referenced classes of package org.joda.time:
//            Chronology, DurationField

public abstract class DurationFieldType
    implements Serializable
{

    public static final DurationFieldType CENTURIES_TYPE = new StandardDurationFieldType("centuries", (byte)2);
    public static final DurationFieldType DAYS_TYPE = new StandardDurationFieldType("days", (byte)7);
    public static final DurationFieldType ERAS_TYPE = new StandardDurationFieldType("eras", (byte)1);
    public static final DurationFieldType HALFDAYS_TYPE = new StandardDurationFieldType("halfdays", (byte)8);
    public static final DurationFieldType HOURS_TYPE = new StandardDurationFieldType("hours", (byte)9);
    public static final DurationFieldType MILLIS_TYPE = new StandardDurationFieldType("millis", (byte)12);
    public static final DurationFieldType MINUTES_TYPE = new StandardDurationFieldType("minutes", (byte)10);
    public static final DurationFieldType MONTHS_TYPE = new StandardDurationFieldType("months", (byte)5);
    public static final DurationFieldType SECONDS_TYPE = new StandardDurationFieldType("seconds", (byte)11);
    public static final DurationFieldType WEEKS_TYPE = new StandardDurationFieldType("weeks", (byte)6);
    public static final DurationFieldType WEEKYEARS_TYPE = new StandardDurationFieldType("weekyears", (byte)3);
    public static final DurationFieldType YEARS_TYPE = new StandardDurationFieldType("years", (byte)4);
    public static final long serialVersionUID = 0x7f8cac4ed77L;
    public final String iName;

    protected DurationFieldType(String s)
    {
        iName = s;
    }

    public abstract DurationField getField(Chronology chronology);

    public String toString()
    {
        return iName;
    }


    private class StandardDurationFieldType extends DurationFieldType
    {

        public static final long serialVersionUID = 0x1c563f5ae6d3L;
        private final byte iOrdinal;

        private final Object readResolve()
        {
            switch (iOrdinal)
            {
            default:
                return this;

            case 1: // '\001'
                return ERAS_TYPE;

            case 2: // '\002'
                return CENTURIES_TYPE;

            case 3: // '\003'
                return WEEKYEARS_TYPE;

            case 4: // '\004'
                return YEARS_TYPE;

            case 5: // '\005'
                return MONTHS_TYPE;

            case 6: // '\006'
                return WEEKS_TYPE;

            case 7: // '\007'
                return DAYS_TYPE;

            case 8: // '\b'
                return HALFDAYS_TYPE;

            case 9: // '\t'
                return HOURS_TYPE;

            case 10: // '\n'
                return MINUTES_TYPE;

            case 11: // '\013'
                return SECONDS_TYPE;

            case 12: // '\f'
                return MILLIS_TYPE;
            }
        }

        public final boolean equals(Object obj)
        {
            if (this != obj)
            {
                if (obj instanceof StandardDurationFieldType)
                {
                    if (iOrdinal != ((StandardDurationFieldType)obj).iOrdinal)
                    {
                        return false;
                    }
                } else
                {
                    return false;
                }
            }
            return true;
        }

        public final DurationField getField(Chronology chronology)
        {
            chronology = DateTimeUtils.getChronology(chronology);
            switch (iOrdinal)
            {
            default:
                throw new InternalError();

            case 1: // '\001'
                return chronology.eras();

            case 2: // '\002'
                return chronology.centuries();

            case 3: // '\003'
                return chronology.weekyears();

            case 4: // '\004'
                return chronology.years();

            case 5: // '\005'
                return chronology.months();

            case 6: // '\006'
                return chronology.weeks();

            case 7: // '\007'
                return chronology.days();

            case 8: // '\b'
                return chronology.halfdays();

            case 9: // '\t'
                return chronology.hours();

            case 10: // '\n'
                return chronology.minutes();

            case 11: // '\013'
                return chronology.seconds();

            case 12: // '\f'
                return chronology.millis();
            }
        }

        public final int hashCode()
        {
            return 1 << iOrdinal;
        }

        StandardDurationFieldType(String s, byte byte0)
        {
            super(s);
            iOrdinal = byte0;
        }
    }

}
