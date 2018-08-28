// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactory;
import net.fortuna.ical4j.model.Time;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.ValidationException;

// Referenced classes of package net.fortuna.ical4j.model.property:
//            DateProperty

public class UtcProperty extends DateProperty
{

    public static final long serialVersionUID = 0x434ef1989d177c42L;

    public UtcProperty(String s, PropertyFactory propertyfactory)
    {
        super(s, propertyfactory);
        setDate(new DateTime(true));
    }

    public void setDateTime(DateTime datetime)
    {
        if (datetime != null)
        {
            datetime = new DateTime(datetime);
            datetime.setUtc(true);
            setDate(datetime);
            return;
        } else
        {
            setDate(datetime);
            return;
        }
    }

    public final void setTimeZone(TimeZone timezone)
    {
        throw new UnsupportedOperationException("Cannot set timezone for UTC properties");
    }

    public void validate()
        throws ValidationException
    {
        super.validate();
        if (super.date != null && !(super.date instanceof DateTime))
        {
            throw new ValidationException("Property must have a DATE-TIME value");
        }
        DateTime datetime = (DateTime)super.date;
        if (datetime != null && !datetime.time.utc)
        {
            throw new ValidationException(String.valueOf(super.name).concat(": DATE-TIME value must be specified in UTC time"));
        } else
        {
            return;
        }
    }
}
