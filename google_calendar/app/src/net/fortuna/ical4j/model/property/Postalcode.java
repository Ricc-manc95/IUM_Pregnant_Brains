// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Escapable;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;

public final class Postalcode extends Property
    implements Escapable
{

    public static final long serialVersionUID = 0x1b86a74dda700be4L;
    private String value;

    public Postalcode()
    {
        super("POSTAL-CODE", PropertyFactoryImpl.instance);
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
    }
}