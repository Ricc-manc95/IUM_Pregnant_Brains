// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.util;

import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.ValidationException;

public final class PropertyValidator
{

    public static PropertyValidator instance = new PropertyValidator();

    private PropertyValidator()
    {
    }

    public static void assertNone(String s, PropertyList propertylist)
        throws ValidationException
    {
        if (propertylist.getProperty(s) != null)
        {
            throw new ValidationException("Property [{0}] is not applicable", new Object[] {
                s
            });
        } else
        {
            return;
        }
    }

    public static void assertOne(String s, PropertyList propertylist)
        throws ValidationException
    {
        if (propertylist.getProperties(s).size() != 1)
        {
            throw new ValidationException("Property [{0}] must be specified once", new Object[] {
                s
            });
        } else
        {
            return;
        }
    }

    public static void assertOneOrLess(String s, PropertyList propertylist)
        throws ValidationException
    {
        if (propertylist.getProperties(s).size() > 1)
        {
            throw new ValidationException("Property [{0}] must only be specified once", new Object[] {
                s
            });
        } else
        {
            return;
        }
    }

    public static void assertOneOrMore(String s, PropertyList propertylist)
        throws ValidationException
    {
        if (propertylist.getProperties(s).size() <= 0)
        {
            throw new ValidationException("Property [{0}] must be specified at least once", new Object[] {
                s
            });
        } else
        {
            return;
        }
    }

}
