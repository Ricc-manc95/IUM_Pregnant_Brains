// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;

public final class Repeat extends Property
{

    public static final long serialVersionUID = 0xe77f9a8996e29af1L;
    private int count;

    public Repeat()
    {
        super("REPEAT", PropertyFactoryImpl.instance);
    }

    public final String getValue()
    {
        return String.valueOf(count);
    }

    public final void setValue(String s)
    {
        count = Integer.parseInt(s);
    }

    public final void validate()
        throws ValidationException
    {
    }
}
