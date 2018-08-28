// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;

public final class Duration extends Property
{

    public static final long serialVersionUID = 0x7ee97572b24083beL;
    public Dur duration;

    public Duration()
    {
        super("DURATION", PropertyFactoryImpl.instance);
    }

    public Duration(Dur dur)
    {
        super("DURATION", PropertyFactoryImpl.instance);
        duration = dur;
    }

    public final String getValue()
    {
        return duration.toString();
    }

    public final void setValue(String s)
    {
        duration = new Dur(s);
    }

    public final void validate()
        throws ValidationException
    {
    }
}
