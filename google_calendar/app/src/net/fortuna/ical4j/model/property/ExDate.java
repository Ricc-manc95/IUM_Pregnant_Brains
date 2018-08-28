// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.util.ParameterValidator;

// Referenced classes of package net.fortuna.ical4j.model.property:
//            DateListProperty

public final class ExDate extends DateListProperty
{

    public static final long serialVersionUID = 0x2493fea08061993fL;

    public ExDate()
    {
        super("EXDATE", PropertyFactoryImpl.instance);
    }

    public final void validate()
        throws ValidationException
    {
        ParameterValidator.assertOneOrLess("VALUE", super.parameters);
        Parameter parameter = super.parameters.getParameter("VALUE");
        if (parameter != null && !Value.DATE_TIME.equals(parameter) && !Value.DATE.equals(parameter))
        {
            throw new ValidationException("Parameter [VALUE] is invalid");
        } else
        {
            ParameterValidator.assertOneOrLess("TZID", super.parameters);
            return;
        }
    }
}
