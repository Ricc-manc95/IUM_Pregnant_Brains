// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;

public final class Rsvp extends Parameter
{

    public static final Rsvp FALSE = new Rsvp("FALSE");
    public static final Rsvp TRUE = new Rsvp("TRUE");
    public static final long serialVersionUID = 0xb550864e8323ae24L;
    private Boolean rsvp;

    private Rsvp(Boolean boolean1)
    {
        super("RSVP", ParameterFactoryImpl.instance);
        rsvp = boolean1;
    }

    public Rsvp(String s)
    {
        this(Boolean.valueOf(s));
    }

    public final String getValue()
    {
        if (rsvp.booleanValue())
        {
            return "TRUE";
        } else
        {
            return "FALSE";
        }
    }

}
