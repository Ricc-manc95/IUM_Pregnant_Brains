// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import java.text.ParseException;
import net.fortuna.ical4j.model.PeriodList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.util.ParameterValidator;

public final class FreeBusy extends Property
{

    public static final long serialVersionUID = 0xa6f5f30f2cb42eb9L;
    private PeriodList periods;

    public FreeBusy()
    {
        super("FREEBUSY", PropertyFactoryImpl.instance);
        periods = new PeriodList();
    }

    public final String getValue()
    {
        return periods.toString();
    }

    public final void setValue(String s)
        throws ParseException
    {
        periods = new PeriodList(s);
    }

    public final void validate()
        throws ValidationException
    {
        ParameterValidator.assertOneOrLess("FBTYPE", super.parameters);
        if (!periods.utc)
        {
            throw new ValidationException("Periods must be in UTC format");
        } else
        {
            return;
        }
    }
}
