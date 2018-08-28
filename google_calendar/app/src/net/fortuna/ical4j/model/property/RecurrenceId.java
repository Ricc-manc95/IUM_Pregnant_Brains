// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.util.ParameterValidator;

// Referenced classes of package net.fortuna.ical4j.model.property:
//            DateProperty

public final class RecurrenceId extends DateProperty
{

    public static final long serialVersionUID = 0x3dda0837d4fba07eL;

    public RecurrenceId()
    {
        super("RECURRENCE-ID", PropertyFactoryImpl.instance);
        setDate(new DateTime());
    }

    public final void validate()
        throws ValidationException
    {
        super.validate();
        ParameterValidator.assertOneOrLess("RANGE", super.parameters);
    }
}
