// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.fortuna.ical4j.model.component.Daylight;
import net.fortuna.ical4j.model.component.Observance;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.property.TzId;
import net.fortuna.ical4j.model.property.TzOffsetTo;

// Referenced classes of package net.fortuna.ical4j.model:
//            Component, PropertyList, Content, ComponentList, 
//            DateTime, UtcOffset

public final class TimeZone extends java.util.TimeZone
{

    public static final long serialVersionUID = 0xb1fe4517b7810fdeL;
    private final int rawOffset;
    private final VTimeZone vTimeZone;

    protected TimeZone()
    {
        vTimeZone = null;
        rawOffset = 0;
    }

    public TimeZone(VTimeZone vtimezone)
    {
        Object obj;
        ComponentList componentlist;
        Object obj1;
        boolean flag;
        obj = null;
        flag = false;
        super();
        vTimeZone = vtimezone;
        setID(((TzId)((Component) (vtimezone)).properties.getProperty("TZID")).getValue());
        obj1 = vtimezone.observances.getComponents("STANDARD");
        componentlist = ((ComponentList) (obj1));
        if (!((List) (obj1)).isEmpty()) goto _L2; else goto _L1
_L1:
        int i;
        componentlist = vtimezone.observances.getComponents("DAYLIGHT");
        i = ((flag) ? 1 : 0);
        if (componentlist.isEmpty()) goto _L3; else goto _L2
_L2:
        if (componentlist.size() <= 1) goto _L5; else goto _L4
_L4:
        DateTime datetime;
        datetime = new DateTime();
        i = 0;
        vtimezone = null;
_L8:
        obj1 = vtimezone;
        if (i >= componentlist.size()) goto _L7; else goto _L6
_L6:
        Observance observance = (Observance)componentlist.get(i);
        obj1 = observance.getLatestOnset(datetime);
        if (obj1 != null && (obj == null || ((Date) (obj1)).after(((Date) (obj)))))
        {
            obj = observance;
            vtimezone = ((VTimeZone) (obj1));
        } else
        {
            VTimeZone vtimezone1 = vtimezone;
            vtimezone = ((VTimeZone) (obj));
            obj = vtimezone1;
        }
        i++;
        obj1 = obj;
        obj = vtimezone;
        vtimezone = ((VTimeZone) (obj1));
        if (true) goto _L8; else goto _L7
_L5:
        obj1 = (Observance)componentlist.get(0);
_L7:
        i = ((flag) ? 1 : 0);
        if (obj1 != null)
        {
            vtimezone = (TzOffsetTo)((Component) (obj1)).properties.getProperty("TZOFFSETTO");
            i = ((flag) ? 1 : 0);
            if (vtimezone != null)
            {
                i = (int)((TzOffsetTo) (vtimezone)).offset.offset;
            }
        }
_L3:
        rawOffset = i;
        return;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (TimeZone)obj;
            if (rawOffset != ((TimeZone) (obj)).rawOffset)
            {
                return false;
            }
            if (vTimeZone == null ? ((TimeZone) (obj)).vTimeZone != null : !vTimeZone.equals(((TimeZone) (obj)).vTimeZone))
            {
                return false;
            }
        }
        return true;
    }

    public final int getOffset(int i, int j, int k, int l, int i1, int j1)
    {
        boolean flag = false;
        Object obj = Calendar.getInstance();
        ((Calendar) (obj)).set(0, i);
        ((Calendar) (obj)).set(1, j);
        ((Calendar) (obj)).set(2, k);
        ((Calendar) (obj)).set(6, l);
        ((Calendar) (obj)).set(7, i1);
        ((Calendar) (obj)).set(14, j1);
        obj = vTimeZone.getApplicableObservance(new DateTime(((Calendar) (obj)).getTime()));
        i = ((flag) ? 1 : 0);
        if (obj != null)
        {
            i = (int)((TzOffsetTo)((Component) (obj)).properties.getProperty("TZOFFSETTO")).offset.offset;
        }
        return i;
    }

    public final int getOffset(long l)
    {
        Object obj = vTimeZone.getApplicableObservance(new DateTime(l));
        if (obj != null)
        {
            obj = (TzOffsetTo)((Component) (obj)).properties.getProperty("TZOFFSETTO");
            if (((TzOffsetTo) (obj)).offset.offset < (long)getRawOffset())
            {
                return getRawOffset();
            } else
            {
                return (int)((TzOffsetTo) (obj)).offset.offset;
            }
        } else
        {
            return 0;
        }
    }

    public final int getRawOffset()
    {
        return rawOffset;
    }

    public final int hashCode()
    {
        int i;
        if (vTimeZone != null)
        {
            i = vTimeZone.hashCode();
        } else
        {
            i = 0;
        }
        return i * 31 + rawOffset;
    }

    public final boolean inDaylightTime(Date date)
    {
        date = vTimeZone.getApplicableObservance(new DateTime(date));
        return date != null && (date instanceof Daylight);
    }

    public final void setRawOffset(int i)
    {
        throw new UnsupportedOperationException("Updates to the VTIMEZONE object must be performed directly");
    }

    public final boolean useDaylightTime()
    {
        return !vTimeZone.observances.getComponents("DAYLIGHT").isEmpty();
    }
}
