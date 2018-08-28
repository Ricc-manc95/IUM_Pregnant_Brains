// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.Validator;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.util.CompatibilityHints;

// Referenced classes of package net.fortuna.ical4j.model.component:
//            CalendarComponent

public final class XComponent extends CalendarComponent
{

    public static final long serialVersionUID = 0xcdb9ac6b22c9f311L;

    public XComponent(String s, PropertyList propertylist)
    {
        super(s, propertylist);
    }

    protected final Validator getValidator(Method method)
    {
        return EMPTY_VALIDATOR;
    }

    public final void validate(boolean flag)
        throws ValidationException
    {
        if (!CompatibilityHints.isHintEnabled("ical4j.validation.relaxed") && !super.name.startsWith("X-"))
        {
            throw new ValidationException("Experimental components must have the following prefix: X-");
        }
        if (flag)
        {
            validateProperties();
        }
    }
}
