// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.UtcOffset;
import net.fortuna.ical4j.model.ValidationException;

public final class TzOffsetTo extends Property
{

    public static final long serialVersionUID = 0x71fd8b683389bf04L;
    public UtcOffset offset;

    public TzOffsetTo()
    {
        super("TZOFFSETTO", PropertyFactoryImpl.instance);
    }

    public final String getValue()
    {
        if (offset != null)
        {
            return offset.toString();
        } else
        {
            return "";
        }
    }

    public final void setValue(String s)
    {
        offset = new UtcOffset(s);
    }

    public final void validate()
        throws ValidationException
    {
    }
}
