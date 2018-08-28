// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;

public final class PercentComplete extends Property
{

    public static final long serialVersionUID = 0x6c1506b052f995b0L;
    private int percentage;

    public PercentComplete()
    {
        super("PERCENT-COMPLETE", PropertyFactoryImpl.instance);
    }

    public final String getValue()
    {
        return String.valueOf(percentage);
    }

    public final void setValue(String s)
    {
        percentage = Integer.parseInt(s);
    }

    public final void validate()
        throws ValidationException
    {
        if (percentage < 0 || percentage > 100)
        {
            String s = super.name;
            int i = percentage;
            throw new ValidationException((new StringBuilder(String.valueOf(s).length() + 32)).append(s).append(" with invalid value: ").append(i).toString());
        } else
        {
            return;
        }
    }
}
