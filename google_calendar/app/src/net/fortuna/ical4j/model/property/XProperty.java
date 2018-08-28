// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Escapable;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.util.CompatibilityHints;

public final class XProperty extends Property
    implements Escapable
{

    public static final long serialVersionUID = 0x205c16564b269ccdL;
    private String value;

    public XProperty(String s)
    {
        super(s, PropertyFactoryImpl.instance);
    }

    public final String getValue()
    {
        return value;
    }

    public final void setValue(String s)
    {
        value = s;
    }

    public final void validate()
        throws ValidationException
    {
        if (!CompatibilityHints.isHintEnabled("ical4j.validation.relaxed") && !super.name.startsWith("X-"))
        {
            String s = super.name;
            throw new ValidationException((new StringBuilder(String.valueOf(s).length() + 75)).append("Invalid name [").append(s).append("]. Experimental properties must have the following prefix: X-").toString());
        } else
        {
            return;
        }
    }
}
