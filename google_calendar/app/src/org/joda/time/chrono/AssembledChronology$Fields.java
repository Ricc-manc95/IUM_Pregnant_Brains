// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DurationField;

public final class 
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

    ()
    {
    }
}
