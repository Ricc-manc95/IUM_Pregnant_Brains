// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Escapable;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;

public final class ProdId extends Property
    implements Escapable
{

    public static final long serialVersionUID = 0xde3c08e54f627b6aL;
    private String value;

    public ProdId()
    {
        super("PRODID", PropertyFactoryImpl.instance);
    }

    public ProdId(String s)
    {
        super("PRODID", PropertyFactoryImpl.instance);
        setValue(s);
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
