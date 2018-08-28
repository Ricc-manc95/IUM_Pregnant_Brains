// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.net.URISyntaxException;
import net.fortuna.ical4j.model.parameter.Value;

// Referenced classes of package net.fortuna.ical4j.model:
//            ParameterFactory, Parameter

final class 
    implements ParameterFactory
{

    public static final long serialVersionUID = 1L;

    public final Parameter createParameter(String s, String s1)
        throws URISyntaxException
    {
        s1 = new Value(s1);
        if (Value.BINARY.equals(s1))
        {
            s = Value.BINARY;
        } else
        {
            if (Value.BOOLEAN.equals(s1))
            {
                return Value.BOOLEAN;
            }
            if (Value.CAL_ADDRESS.equals(s1))
            {
                return Value.CAL_ADDRESS;
            }
            if (Value.DATE.equals(s1))
            {
                return Value.DATE;
            }
            if (Value.DATE_TIME.equals(s1))
            {
                return Value.DATE_TIME;
            }
            if (Value.DURATION.equals(s1))
            {
                return Value.DURATION;
            }
            if (Value.FLOAT.equals(s1))
            {
                return Value.FLOAT;
            }
            if (Value.INTEGER.equals(s1))
            {
                return Value.INTEGER;
            }
            if (Value.PERIOD.equals(s1))
            {
                return Value.PERIOD;
            }
            if (Value.RECUR.equals(s1))
            {
                return Value.RECUR;
            }
            if (Value.TEXT.equals(s1))
            {
                return Value.TEXT;
            }
            if (Value.TIME.equals(s1))
            {
                return Value.TIME;
            }
            if (Value.URI.equals(s1))
            {
                return Value.URI;
            }
            s = s1;
            if (Value.UTC_OFFSET.equals(s1))
            {
                return Value.UTC_OFFSET;
            }
        }
        return s;
    }

    ()
    {
    }
}
