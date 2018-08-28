// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;

public class Status extends Property
{

    public static final Status VEVENT_CANCELLED = new ImmutableStatus("CANCELLED");
    public static final Status VEVENT_CONFIRMED = new ImmutableStatus("CONFIRMED");
    public static final Status VEVENT_TENTATIVE = new ImmutableStatus("TENTATIVE");
    public static final Status VJOURNAL_CANCELLED = new ImmutableStatus("CANCELLED");
    public static final Status VJOURNAL_DRAFT = new ImmutableStatus("DRAFT");
    public static final Status VJOURNAL_FINAL = new ImmutableStatus("FINAL");
    public static final Status VTODO_CANCELLED = new ImmutableStatus("CANCELLED");
    public static final Status VTODO_COMPLETED = new ImmutableStatus("COMPLETED");
    public static final Status VTODO_IN_PROCESS = new ImmutableStatus("IN-PROCESS");
    public static final Status VTODO_NEEDS_ACTION = new ImmutableStatus("NEEDS-ACTION");
    public static final long serialVersionUID = 0x66b5ff440757292aL;
    private String value;

    public Status()
    {
        super("STATUS", PropertyFactoryImpl.instance);
    }

    public Status(ParameterList parameterlist, String s)
    {
        super("STATUS", parameterlist, PropertyFactoryImpl.instance);
        value = s;
    }

    public final String getValue()
    {
        return value;
    }

    public void setValue(String s)
    {
        value = s;
    }

    public final void validate()
        throws ValidationException
    {
    }


    private class ImmutableStatus extends Status
    {

        public static final long serialVersionUID = 0x6bdb399114aa8d6cL;

        public final void setValue(String s)
        {
            throw new UnsupportedOperationException("Cannot modify constant instances");
        }

        ImmutableStatus(String s)
        {
            super(new ParameterList(true), s);
        }
    }

}
