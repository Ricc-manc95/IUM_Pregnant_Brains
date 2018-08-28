// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import java.util.HashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;

// Referenced classes of package org.joda.time.chrono:
//            AssembledChronology

public final class ZonedChronology extends AssembledChronology
{

    public static final long serialVersionUID = 0xf105b3cbf0791080L;

    private ZonedChronology(Chronology chronology, DateTimeZone datetimezone)
    {
        super(chronology, datetimezone);
    }

    private final DateTimeField convertField(DateTimeField datetimefield, HashMap hashmap)
    {
        if (datetimefield == null || !datetimefield.isSupported())
        {
            return datetimefield;
        }
        if (hashmap.containsKey(datetimefield))
        {
            return (DateTimeField)hashmap.get(datetimefield);
        } else
        {
            ZonedDateTimeField zoneddatetimefield = new ZonedDateTimeField(datetimefield, getZone(), convertField(datetimefield.getDurationField(), hashmap), convertField(datetimefield.getRangeDurationField(), hashmap), convertField(datetimefield.getLeapDurationField(), hashmap));
            hashmap.put(datetimefield, zoneddatetimefield);
            return zoneddatetimefield;
        }
    }

    private final DurationField convertField(DurationField durationfield, HashMap hashmap)
    {
        if (durationfield == null || !durationfield.isSupported())
        {
            return durationfield;
        }
        if (hashmap.containsKey(durationfield))
        {
            return (DurationField)hashmap.get(durationfield);
        } else
        {
            ZonedDurationField zoneddurationfield = new ZonedDurationField(durationfield, getZone());
            hashmap.put(durationfield, zoneddurationfield);
            return zoneddurationfield;
        }
    }

    public static ZonedChronology getInstance(Chronology chronology, DateTimeZone datetimezone)
    {
        if (chronology == null)
        {
            throw new IllegalArgumentException("Must supply a chronology");
        }
        chronology = chronology.withUTC();
        if (chronology == null)
        {
            throw new IllegalArgumentException("UTC chronology must not be null");
        }
        if (datetimezone == null)
        {
            throw new IllegalArgumentException("DateTimeZone must not be null");
        } else
        {
            return new ZonedChronology(chronology, datetimezone);
        }
    }

    protected final void assemble(AssembledChronology.Fields fields)
    {
        HashMap hashmap = new HashMap();
        fields.eras = convertField(fields.eras, hashmap);
        fields.centuries = convertField(fields.centuries, hashmap);
        fields.years = convertField(fields.years, hashmap);
        fields.months = convertField(fields.months, hashmap);
        fields.weekyears = convertField(fields.weekyears, hashmap);
        fields.weeks = convertField(fields.weeks, hashmap);
        fields.days = convertField(fields.days, hashmap);
        fields.halfdays = convertField(fields.halfdays, hashmap);
        fields.hours = convertField(fields.hours, hashmap);
        fields.minutes = convertField(fields.minutes, hashmap);
        fields.seconds = convertField(fields.seconds, hashmap);
        fields.millis = convertField(fields.millis, hashmap);
        fields.year = convertField(fields.year, hashmap);
        fields.yearOfEra = convertField(fields.yearOfEra, hashmap);
        fields.yearOfCentury = convertField(fields.yearOfCentury, hashmap);
        fields.centuryOfEra = convertField(fields.centuryOfEra, hashmap);
        fields.era = convertField(fields.era, hashmap);
        fields.dayOfWeek = convertField(fields.dayOfWeek, hashmap);
        fields.dayOfMonth = convertField(fields.dayOfMonth, hashmap);
        fields.dayOfYear = convertField(fields.dayOfYear, hashmap);
        fields.monthOfYear = convertField(fields.monthOfYear, hashmap);
        fields.weekOfWeekyear = convertField(fields.weekOfWeekyear, hashmap);
        fields.weekyear = convertField(fields.weekyear, hashmap);
        fields.weekyearOfCentury = convertField(fields.weekyearOfCentury, hashmap);
        fields.millisOfSecond = convertField(fields.millisOfSecond, hashmap);
        fields.millisOfDay = convertField(fields.millisOfDay, hashmap);
        fields.secondOfMinute = convertField(fields.secondOfMinute, hashmap);
        fields.secondOfDay = convertField(fields.secondOfDay, hashmap);
        fields.minuteOfHour = convertField(fields.minuteOfHour, hashmap);
        fields.minuteOfDay = convertField(fields.minuteOfDay, hashmap);
        fields.hourOfDay = convertField(fields.hourOfDay, hashmap);
        fields.hourOfHalfday = convertField(fields.hourOfHalfday, hashmap);
        fields.clockhourOfDay = convertField(fields.clockhourOfDay, hashmap);
        fields.clockhourOfHalfday = convertField(fields.clockhourOfHalfday, hashmap);
        fields.halfdayOfDay = convertField(fields.halfdayOfDay, hashmap);
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof ZonedChronology))
            {
                return false;
            }
            obj = (ZonedChronology)obj;
            if (!super.iBase.equals(((AssembledChronology) (obj)).iBase) || !getZone().equals(((Chronology) (obj)).getZone()))
            {
                return false;
            }
        }
        return true;
    }

    public final DateTimeZone getZone()
    {
        return (DateTimeZone)super.iParam;
    }

    public final int hashCode()
    {
        return 0x4fba5 + getZone().hashCode() * 11 + super.iBase.hashCode() * 7;
    }

    public final String toString()
    {
        String s = String.valueOf(super.iBase);
        String s1 = getZone().iID;
        return (new StringBuilder(String.valueOf(s).length() + 19 + String.valueOf(s1).length())).append("ZonedChronology[").append(s).append(", ").append(s1).append(']').toString();
    }

    public final Chronology withUTC()
    {
        return super.iBase;
    }

    public final Chronology withZone(DateTimeZone datetimezone)
    {
        DateTimeZone datetimezone1 = datetimezone;
        if (datetimezone == null)
        {
            datetimezone1 = DateTimeZone.getDefault();
        }
        if (datetimezone1 == super.iParam)
        {
            return this;
        }
        if (datetimezone1 == DateTimeZone.UTC)
        {
            return super.iBase;
        } else
        {
            return new ZonedChronology(super.iBase, datetimezone1);
        }
    }

    private class ZonedDateTimeField extends BaseDateTimeField
    {

        private final DurationField iDurationField;
        private final DateTimeField iField;
        private final DurationField iLeapDurationField;
        private final DurationField iRangeDurationField;
        private final boolean iTimeField;
        private final DateTimeZone iZone;

        private final int getOffsetToAdd(long l)
        {
            int i = iZone.getOffset(l);
            if (((long)i + l ^ l) < 0L && ((long)i ^ l) >= 0L)
            {
                throw new ArithmeticException("Adding time zone offset caused overflow");
            } else
            {
                return i;
            }
        }

        public final long add(long l, int i)
        {
            if (iTimeField)
            {
                int j = getOffsetToAdd(l);
                return iField.add((long)j + l, i) - (long)j;
            } else
            {
                long l1 = iZone.convertUTCToLocal(l);
                l1 = iField.add(l1, i);
                return iZone.convertLocalToUTC(l1, false, l);
            }
        }

        public final long add(long l, long l1)
        {
            if (iTimeField)
            {
                int i = getOffsetToAdd(l);
                return iField.add((long)i + l, l1) - (long)i;
            } else
            {
                long l2 = iZone.convertUTCToLocal(l);
                l1 = iField.add(l2, l1);
                return iZone.convertLocalToUTC(l1, false, l);
            }
        }

        public final int get(long l)
        {
            l = iZone.convertUTCToLocal(l);
            return iField.get(l);
        }

        public final String getAsShortText(int i, Locale locale)
        {
            return iField.getAsShortText(i, locale);
        }

        public final String getAsShortText(long l, Locale locale)
        {
            l = iZone.convertUTCToLocal(l);
            return iField.getAsShortText(l, locale);
        }

        public final String getAsText(int i, Locale locale)
        {
            return iField.getAsText(i, locale);
        }

        public final String getAsText(long l, Locale locale)
        {
            l = iZone.convertUTCToLocal(l);
            return iField.getAsText(l, locale);
        }

        public final DurationField getDurationField()
        {
            return iDurationField;
        }

        public final DurationField getLeapDurationField()
        {
            return iLeapDurationField;
        }

        public final int getMaximumTextLength(Locale locale)
        {
            return iField.getMaximumTextLength(locale);
        }

        public final int getMaximumValue()
        {
            return iField.getMaximumValue();
        }

        public final int getMaximumValue(long l)
        {
            l = iZone.convertUTCToLocal(l);
            return iField.getMaximumValue(l);
        }

        public final int getMinimumValue()
        {
            return iField.getMinimumValue();
        }

        public final DurationField getRangeDurationField()
        {
            return iRangeDurationField;
        }

        public final boolean isLeap(long l)
        {
            l = iZone.convertUTCToLocal(l);
            return iField.isLeap(l);
        }

        public final long remainder(long l)
        {
            l = iZone.convertUTCToLocal(l);
            return iField.remainder(l);
        }

        public final long roundFloor(long l)
        {
            if (iTimeField)
            {
                int i = getOffsetToAdd(l);
                return iField.roundFloor((long)i + l) - (long)i;
            } else
            {
                long l1 = iZone.convertUTCToLocal(l);
                l1 = iField.roundFloor(l1);
                return iZone.convertLocalToUTC(l1, false, l);
            }
        }

        public final long set(long l, int i)
        {
            long l1 = iZone.convertUTCToLocal(l);
            l1 = iField.set(l1, i);
            l = iZone.convertLocalToUTC(l1, false, l);
            if (get(l) != i)
            {
                IllegalInstantException illegalinstantexception = new IllegalInstantException(l1, iZone.iID);
                IllegalFieldValueException illegalfieldvalueexception = new IllegalFieldValueException(iField.getType(), Integer.valueOf(i), illegalinstantexception.getMessage());
                illegalfieldvalueexception.initCause(illegalinstantexception);
                throw illegalfieldvalueexception;
            } else
            {
                return l;
            }
        }

        public final long set(long l, String s, Locale locale)
        {
            long l1 = iZone.convertUTCToLocal(l);
            l1 = iField.set(l1, s, locale);
            return iZone.convertLocalToUTC(l1, false, l);
        }

        ZonedDateTimeField(DateTimeField datetimefield, DateTimeZone datetimezone, DurationField durationfield, DurationField durationfield1, DurationField durationfield2)
        {
            super(datetimefield.getType());
            if (!datetimefield.isSupported())
            {
                throw new IllegalArgumentException();
            }
            iField = datetimefield;
            iZone = datetimezone;
            iDurationField = durationfield;
            boolean flag;
            if (durationfield != null && durationfield.getUnitMillis() < 0x2932e00L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            iTimeField = flag;
            iRangeDurationField = durationfield1;
            iLeapDurationField = durationfield2;
        }
    }


    private class ZonedDurationField extends BaseDurationField
    {

        public static final long serialVersionUID = 0xf943b502d87d9ea2L;
        private final DurationField iField;
        private final boolean iTimeField;
        private final DateTimeZone iZone;

        private final int getOffsetFromLocalToSubtract(long l)
        {
            int i = iZone.getOffsetFromLocal(l);
            if ((l - (long)i ^ l) < 0L && ((long)i ^ l) < 0L)
            {
                throw new ArithmeticException("Subtracting time zone offset caused overflow");
            } else
            {
                return i;
            }
        }

        private final int getOffsetToAdd(long l)
        {
            int i = iZone.getOffset(l);
            if (((long)i + l ^ l) < 0L && ((long)i ^ l) >= 0L)
            {
                throw new ArithmeticException("Adding time zone offset caused overflow");
            } else
            {
                return i;
            }
        }

        public final long add(long l, int i)
        {
            int j = getOffsetToAdd(l);
            l = iField.add((long)j + l, i);
            if (iTimeField)
            {
                i = j;
            } else
            {
                i = getOffsetFromLocalToSubtract(l);
            }
            return l - (long)i;
        }

        public final long add(long l, long l1)
        {
            int i = getOffsetToAdd(l);
            l = iField.add((long)i + l, l1);
            if (!iTimeField)
            {
                i = getOffsetFromLocalToSubtract(l);
            }
            return l - (long)i;
        }

        public final long getUnitMillis()
        {
            return iField.getUnitMillis();
        }

        public final boolean isPrecise()
        {
            if (iTimeField)
            {
                return iField.isPrecise();
            }
            return iField.isPrecise() && iZone.isFixed();
        }

        ZonedDurationField(DurationField durationfield, DateTimeZone datetimezone)
        {
            super(durationfield.getType());
            if (!durationfield.isSupported())
            {
                throw new IllegalArgumentException();
            }
            iField = durationfield;
            boolean flag;
            if (durationfield != null && durationfield.getUnitMillis() < 0x2932e00L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            iTimeField = flag;
            iZone = datetimezone;
        }
    }

}
