// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;

public class Priority extends Property
{

    public static final Priority HIGH = new ImmutablePriority(1);
    public static final Priority LOW = new ImmutablePriority(9);
    public static final Priority MEDIUM = new ImmutablePriority(5);
    public static final Priority UNDEFINED = new ImmutablePriority(0);
    public static final long serialVersionUID = 0xb187a6676fbda1bbL;
    private int level;

    public Priority()
    {
        super("PRIORITY", PropertyFactoryImpl.instance);
        level = UNDEFINED.level;
    }

    public Priority(ParameterList parameterlist, int i)
    {
        super("PRIORITY", parameterlist, PropertyFactoryImpl.instance);
        level = i;
    }

    public final String getValue()
    {
        return String.valueOf(level);
    }

    public void setValue(String s)
    {
        level = Integer.parseInt(s);
    }

    public final void validate()
        throws ValidationException
    {
    }


    private class ImmutablePriority extends Priority
    {

        public static final long serialVersionUID = 0x51aba069e0da7502L;

        public final void setValue(String s)
        {
            throw new UnsupportedOperationException("Cannot modify constant instances");
        }

        ImmutablePriority(int i)
        {
            super(new ParameterList(true), i);
        }
    }

}
