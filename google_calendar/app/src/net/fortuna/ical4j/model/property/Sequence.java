// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;

public final class Sequence extends Property
{

    public static final long serialVersionUID = 0xe9b2e2aaf0d844bbL;
    public int sequenceNo;

    public Sequence()
    {
        super("SEQUENCE", PropertyFactoryImpl.instance);
        sequenceNo = 0;
    }

    public Sequence(int i)
    {
        super("SEQUENCE", PropertyFactoryImpl.instance);
        sequenceNo = i;
    }

    public final String getValue()
    {
        return String.valueOf(sequenceNo);
    }

    public final void setValue(String s)
    {
        sequenceNo = Integer.parseInt(s);
    }

    public final void validate()
        throws ValidationException
    {
    }
}
