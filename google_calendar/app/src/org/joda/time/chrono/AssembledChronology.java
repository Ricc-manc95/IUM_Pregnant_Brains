// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import java.io.IOException;
import java.io.ObjectInputStream;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;

// Referenced classes of package org.joda.time.chrono:
//            BaseChronology

public abstract class AssembledChronology extends BaseChronology
{

    public static final long serialVersionUID = 0xa29fafd9a2b7b8f9L;
    public final Chronology iBase;
    private transient int iBaseFlags;
    private transient DurationField iCenturies;
    private transient DateTimeField iCenturyOfEra;
    private transient DateTimeField iClockhourOfDay;
    private transient DateTimeField iClockhourOfHalfday;
    private transient DateTimeField iDayOfMonth;
    private transient DateTimeField iDayOfWeek;
    private transient DateTimeField iDayOfYear;
    private transient DurationField iDays;
    private transient DateTimeField iEra;
    private transient DurationField iEras;
    private transient DateTimeField iHalfdayOfDay;
    private transient DurationField iHalfdays;
    private transient DateTimeField iHourOfDay;
    private transient DateTimeField iHourOfHalfday;
    private transient DurationField iHours;
    private transient DurationField iMillis;
    private transient DateTimeField iMillisOfDay;
    private transient DateTimeField iMillisOfSecond;
    private transient DateTimeField iMinuteOfDay;
    private transient DateTimeField iMinuteOfHour;
    private transient DurationField iMinutes;
    private transient DateTimeField iMonthOfYear;
    private transient DurationField iMonths;
    public final Object iParam;
    private transient DateTimeField iSecondOfDay;
    private transient DateTimeField iSecondOfMinute;
    private transient DurationField iSeconds;
    private transient DateTimeField iWeekOfWeekyear;
    private transient DurationField iWeeks;
    private transient DateTimeField iWeekyear;
    private transient DateTimeField iWeekyearOfCentury;
    private transient DurationField iWeekyears;
    private transient DateTimeField iYear;
    private transient DateTimeField iYearOfCentury;
    private transient DateTimeField iYearOfEra;
    private transient DurationField iYears;

    protected AssembledChronology(Chronology chronology, Object obj)
    {
        iBase = chronology;
        iParam = obj;
        setFields();
    }

    private void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        objectinputstream.defaultReadObject();
        setFields();
    }

    private final void setFields()
    {
        boolean flag = false;
        int i = 0;
        Fields fields = new Fields();
        if (iBase != null)
        {
            fields.copyFieldsFrom(iBase);
        }
        assemble(fields);
        Object obj = fields.millis;
        if (obj == null)
        {
            obj = super.millis();
        }
        iMillis = ((DurationField) (obj));
        obj = fields.seconds;
        if (obj == null)
        {
            obj = super.seconds();
        }
        iSeconds = ((DurationField) (obj));
        obj = fields.minutes;
        if (obj == null)
        {
            obj = super.minutes();
        }
        iMinutes = ((DurationField) (obj));
        obj = fields.hours;
        if (obj == null)
        {
            obj = super.hours();
        }
        iHours = ((DurationField) (obj));
        obj = fields.halfdays;
        if (obj == null)
        {
            obj = super.halfdays();
        }
        iHalfdays = ((DurationField) (obj));
        obj = fields.days;
        if (obj == null)
        {
            obj = super.days();
        }
        iDays = ((DurationField) (obj));
        obj = fields.weeks;
        if (obj == null)
        {
            obj = super.weeks();
        }
        iWeeks = ((DurationField) (obj));
        obj = fields.weekyears;
        if (obj == null)
        {
            obj = super.weekyears();
        }
        iWeekyears = ((DurationField) (obj));
        obj = fields.months;
        if (obj == null)
        {
            obj = super.months();
        }
        iMonths = ((DurationField) (obj));
        obj = fields.years;
        if (obj == null)
        {
            obj = super.years();
        }
        iYears = ((DurationField) (obj));
        obj = fields.centuries;
        if (obj == null)
        {
            obj = super.centuries();
        }
        iCenturies = ((DurationField) (obj));
        obj = fields.eras;
        if (obj == null)
        {
            obj = super.eras();
        }
        iEras = ((DurationField) (obj));
        obj = fields.millisOfSecond;
        if (obj == null)
        {
            obj = super.millisOfSecond();
        }
        iMillisOfSecond = ((DateTimeField) (obj));
        obj = fields.millisOfDay;
        if (obj == null)
        {
            obj = super.millisOfDay();
        }
        iMillisOfDay = ((DateTimeField) (obj));
        obj = fields.secondOfMinute;
        if (obj == null)
        {
            obj = super.secondOfMinute();
        }
        iSecondOfMinute = ((DateTimeField) (obj));
        obj = fields.secondOfDay;
        if (obj == null)
        {
            obj = super.secondOfDay();
        }
        iSecondOfDay = ((DateTimeField) (obj));
        obj = fields.minuteOfHour;
        if (obj == null)
        {
            obj = super.minuteOfHour();
        }
        iMinuteOfHour = ((DateTimeField) (obj));
        obj = fields.minuteOfDay;
        if (obj == null)
        {
            obj = super.minuteOfDay();
        }
        iMinuteOfDay = ((DateTimeField) (obj));
        obj = fields.hourOfDay;
        if (obj == null)
        {
            obj = super.hourOfDay();
        }
        iHourOfDay = ((DateTimeField) (obj));
        obj = fields.clockhourOfDay;
        if (obj == null)
        {
            obj = super.clockhourOfDay();
        }
        iClockhourOfDay = ((DateTimeField) (obj));
        obj = fields.hourOfHalfday;
        if (obj == null)
        {
            obj = super.hourOfHalfday();
        }
        iHourOfHalfday = ((DateTimeField) (obj));
        obj = fields.clockhourOfHalfday;
        if (obj == null)
        {
            obj = super.clockhourOfHalfday();
        }
        iClockhourOfHalfday = ((DateTimeField) (obj));
        obj = fields.halfdayOfDay;
        if (obj == null)
        {
            obj = super.halfdayOfDay();
        }
        iHalfdayOfDay = ((DateTimeField) (obj));
        obj = fields.dayOfWeek;
        if (obj == null)
        {
            obj = super.dayOfWeek();
        }
        iDayOfWeek = ((DateTimeField) (obj));
        obj = fields.dayOfMonth;
        if (obj == null)
        {
            obj = super.dayOfMonth();
        }
        iDayOfMonth = ((DateTimeField) (obj));
        obj = fields.dayOfYear;
        if (obj == null)
        {
            obj = super.dayOfYear();
        }
        iDayOfYear = ((DateTimeField) (obj));
        obj = fields.weekOfWeekyear;
        if (obj == null)
        {
            obj = super.weekOfWeekyear();
        }
        iWeekOfWeekyear = ((DateTimeField) (obj));
        obj = fields.weekyear;
        if (obj == null)
        {
            obj = super.weekyear();
        }
        iWeekyear = ((DateTimeField) (obj));
        obj = fields.weekyearOfCentury;
        if (obj == null)
        {
            obj = super.weekyearOfCentury();
        }
        iWeekyearOfCentury = ((DateTimeField) (obj));
        obj = fields.monthOfYear;
        if (obj == null)
        {
            obj = super.monthOfYear();
        }
        iMonthOfYear = ((DateTimeField) (obj));
        obj = fields.year;
        if (obj == null)
        {
            obj = super.year();
        }
        iYear = ((DateTimeField) (obj));
        obj = fields.yearOfEra;
        if (obj == null)
        {
            obj = super.yearOfEra();
        }
        iYearOfEra = ((DateTimeField) (obj));
        obj = fields.yearOfCentury;
        if (obj == null)
        {
            obj = super.yearOfCentury();
        }
        iYearOfCentury = ((DateTimeField) (obj));
        obj = fields.centuryOfEra;
        if (obj == null)
        {
            obj = super.centuryOfEra();
        }
        iCenturyOfEra = ((DateTimeField) (obj));
        obj = fields.era;
        if (obj == null)
        {
            obj = super.era();
        }
        iEra = ((DateTimeField) (obj));
        if (iBase != null)
        {
            byte byte0;
            byte byte1;
            if (iHourOfDay == iBase.hourOfDay() && iMinuteOfHour == iBase.minuteOfHour() && iSecondOfMinute == iBase.secondOfMinute() && iMillisOfSecond == iBase.millisOfSecond())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (iMillisOfDay == iBase.millisOfDay())
            {
                byte0 = 2;
            } else
            {
                byte0 = 0;
            }
            byte1 = flag;
            if (iYear == iBase.year())
            {
                byte1 = flag;
                if (iMonthOfYear == iBase.monthOfYear())
                {
                    byte1 = flag;
                    if (iDayOfMonth == iBase.dayOfMonth())
                    {
                        byte1 = 4;
                    }
                }
            }
            i = byte1 | (i | byte0);
        }
        iBaseFlags = i;
    }

    protected abstract void assemble(Fields fields);

    public final DurationField centuries()
    {
        return iCenturies;
    }

    public final DateTimeField centuryOfEra()
    {
        return iCenturyOfEra;
    }

    public final DateTimeField clockhourOfDay()
    {
        return iClockhourOfDay;
    }

    public final DateTimeField clockhourOfHalfday()
    {
        return iClockhourOfHalfday;
    }

    public final DateTimeField dayOfMonth()
    {
        return iDayOfMonth;
    }

    public final DateTimeField dayOfWeek()
    {
        return iDayOfWeek;
    }

    public final DateTimeField dayOfYear()
    {
        return iDayOfYear;
    }

    public final DurationField days()
    {
        return iDays;
    }

    public final DateTimeField era()
    {
        return iEra;
    }

    public final DurationField eras()
    {
        return iEras;
    }

    public DateTimeZone getZone()
    {
        Chronology chronology = iBase;
        if (chronology != null)
        {
            return chronology.getZone();
        } else
        {
            return null;
        }
    }

    public final DateTimeField halfdayOfDay()
    {
        return iHalfdayOfDay;
    }

    public final DurationField halfdays()
    {
        return iHalfdays;
    }

    public final DateTimeField hourOfDay()
    {
        return iHourOfDay;
    }

    public final DateTimeField hourOfHalfday()
    {
        return iHourOfHalfday;
    }

    public final DurationField hours()
    {
        return iHours;
    }

    public final DurationField millis()
    {
        return iMillis;
    }

    public final DateTimeField millisOfDay()
    {
        return iMillisOfDay;
    }

    public final DateTimeField millisOfSecond()
    {
        return iMillisOfSecond;
    }

    public final DateTimeField minuteOfDay()
    {
        return iMinuteOfDay;
    }

    public final DateTimeField minuteOfHour()
    {
        return iMinuteOfHour;
    }

    public final DurationField minutes()
    {
        return iMinutes;
    }

    public final DateTimeField monthOfYear()
    {
        return iMonthOfYear;
    }

    public final DurationField months()
    {
        return iMonths;
    }

    public final DateTimeField secondOfDay()
    {
        return iSecondOfDay;
    }

    public final DateTimeField secondOfMinute()
    {
        return iSecondOfMinute;
    }

    public final DurationField seconds()
    {
        return iSeconds;
    }

    public final DateTimeField weekOfWeekyear()
    {
        return iWeekOfWeekyear;
    }

    public final DurationField weeks()
    {
        return iWeeks;
    }

    public final DateTimeField weekyear()
    {
        return iWeekyear;
    }

    public final DateTimeField weekyearOfCentury()
    {
        return iWeekyearOfCentury;
    }

    public final DurationField weekyears()
    {
        return iWeekyears;
    }

    public final DateTimeField year()
    {
        return iYear;
    }

    public final DateTimeField yearOfCentury()
    {
        return iYearOfCentury;
    }

    public final DateTimeField yearOfEra()
    {
        return iYearOfEra;
    }

    public final DurationField years()
    {
        return iYears;
    }

    private class Fields
    {

        public DurationField centuries;
        public DateTimeField centuryOfEra;
        public DateTimeField clockhourOfDay;
        public DateTimeField clockhourOfHalfday;
        public DateTimeField dayOfMonth;
        public DateTimeField dayOfWeek;
        public DateTimeField dayOfYear;
        public DurationField days;
        public DateTimeField era;
        public DurationField eras;
        public DateTimeField halfdayOfDay;
        public DurationField halfdays;
        public DateTimeField hourOfDay;
        public DateTimeField hourOfHalfday;
        public DurationField hours;
        public DurationField millis;
        public DateTimeField millisOfDay;
        public DateTimeField millisOfSecond;
        public DateTimeField minuteOfDay;
        public DateTimeField minuteOfHour;
        public DurationField minutes;
        public DateTimeField monthOfYear;
        public DurationField months;
        public DateTimeField secondOfDay;
        public DateTimeField secondOfMinute;
        public DurationField seconds;
        public DateTimeField weekOfWeekyear;
        public DurationField weeks;
        public DateTimeField weekyear;
        public DateTimeField weekyearOfCentury;
        public DurationField weekyears;
        public DateTimeField year;
        public DateTimeField yearOfCentury;
        public DateTimeField yearOfEra;
        public DurationField years;

        public final void copyFieldsFrom(Chronology chronology)
        {
            boolean flag1 = false;
            Object obj = chronology.millis();
            boolean flag;
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DurationField) (obj)).isSupported();
            }
            if (flag)
            {
                millis = ((DurationField) (obj));
            }
            obj = chronology.seconds();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DurationField) (obj)).isSupported();
            }
            if (flag)
            {
                seconds = ((DurationField) (obj));
            }
            obj = chronology.minutes();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DurationField) (obj)).isSupported();
            }
            if (flag)
            {
                minutes = ((DurationField) (obj));
            }
            obj = chronology.hours();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DurationField) (obj)).isSupported();
            }
            if (flag)
            {
                hours = ((DurationField) (obj));
            }
            obj = chronology.halfdays();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DurationField) (obj)).isSupported();
            }
            if (flag)
            {
                halfdays = ((DurationField) (obj));
            }
            obj = chronology.days();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DurationField) (obj)).isSupported();
            }
            if (flag)
            {
                days = ((DurationField) (obj));
            }
            obj = chronology.weeks();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DurationField) (obj)).isSupported();
            }
            if (flag)
            {
                weeks = ((DurationField) (obj));
            }
            obj = chronology.weekyears();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DurationField) (obj)).isSupported();
            }
            if (flag)
            {
                weekyears = ((DurationField) (obj));
            }
            obj = chronology.months();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DurationField) (obj)).isSupported();
            }
            if (flag)
            {
                months = ((DurationField) (obj));
            }
            obj = chronology.years();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DurationField) (obj)).isSupported();
            }
            if (flag)
            {
                years = ((DurationField) (obj));
            }
            obj = chronology.centuries();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DurationField) (obj)).isSupported();
            }
            if (flag)
            {
                centuries = ((DurationField) (obj));
            }
            obj = chronology.eras();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DurationField) (obj)).isSupported();
            }
            if (flag)
            {
                eras = ((DurationField) (obj));
            }
            obj = chronology.millisOfSecond();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                millisOfSecond = ((DateTimeField) (obj));
            }
            obj = chronology.millisOfDay();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                millisOfDay = ((DateTimeField) (obj));
            }
            obj = chronology.secondOfMinute();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                secondOfMinute = ((DateTimeField) (obj));
            }
            obj = chronology.secondOfDay();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                secondOfDay = ((DateTimeField) (obj));
            }
            obj = chronology.minuteOfHour();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                minuteOfHour = ((DateTimeField) (obj));
            }
            obj = chronology.minuteOfDay();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                minuteOfDay = ((DateTimeField) (obj));
            }
            obj = chronology.hourOfDay();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                hourOfDay = ((DateTimeField) (obj));
            }
            obj = chronology.clockhourOfDay();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                clockhourOfDay = ((DateTimeField) (obj));
            }
            obj = chronology.hourOfHalfday();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                hourOfHalfday = ((DateTimeField) (obj));
            }
            obj = chronology.clockhourOfHalfday();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                clockhourOfHalfday = ((DateTimeField) (obj));
            }
            obj = chronology.halfdayOfDay();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                halfdayOfDay = ((DateTimeField) (obj));
            }
            obj = chronology.dayOfWeek();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                dayOfWeek = ((DateTimeField) (obj));
            }
            obj = chronology.dayOfMonth();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                dayOfMonth = ((DateTimeField) (obj));
            }
            obj = chronology.dayOfYear();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                dayOfYear = ((DateTimeField) (obj));
            }
            obj = chronology.weekOfWeekyear();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                weekOfWeekyear = ((DateTimeField) (obj));
            }
            obj = chronology.weekyear();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                weekyear = ((DateTimeField) (obj));
            }
            obj = chronology.weekyearOfCentury();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                weekyearOfCentury = ((DateTimeField) (obj));
            }
            obj = chronology.monthOfYear();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                monthOfYear = ((DateTimeField) (obj));
            }
            obj = chronology.year();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                year = ((DateTimeField) (obj));
            }
            obj = chronology.yearOfEra();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                yearOfEra = ((DateTimeField) (obj));
            }
            obj = chronology.yearOfCentury();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                yearOfCentury = ((DateTimeField) (obj));
            }
            obj = chronology.centuryOfEra();
            if (obj == null)
            {
                flag = false;
            } else
            {
                flag = ((DateTimeField) (obj)).isSupported();
            }
            if (flag)
            {
                centuryOfEra = ((DateTimeField) (obj));
            }
            chronology = chronology.era();
            if (chronology == null)
            {
                flag = flag1;
            } else
            {
                flag = chronology.isSupported();
            }
            if (flag)
            {
                era = chronology;
            }
        }

        Fields()
        {
        }
    }

}
