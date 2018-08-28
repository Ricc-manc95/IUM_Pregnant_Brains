// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.Validator;
import net.fortuna.ical4j.model.property.Method;

public abstract class CalendarComponent extends Component
{

    public static final Validator EMPTY_VALIDATOR = new EmptyValidator();
    public static final long serialVersionUID = 0xaf0d1e54e6bc90f0L;

    public CalendarComponent(String s)
    {
        super(s);
    }

    public CalendarComponent(String s, PropertyList propertylist)
    {
        super(s, propertylist);
    }

    protected abstract Validator getValidator(Method method);

    public final void validate(Method method)
        throws ValidationException
    {
        Validator validator = getValidator(method);
        if (validator != null)
        {
            validator.validate();
            return;
        } else
        {
            method = String.valueOf(method);
            throw new ValidationException((new StringBuilder(String.valueOf(method).length() + 20)).append("Unsupported method: ").append(method).toString());
        }
    }


    private class EmptyValidator
        implements Validator
    {

        public static final long serialVersionUID = 1L;

        public final void validate()
            throws ValidationException
        {
        }

        EmptyValidator()
        {
        }
    }

}
