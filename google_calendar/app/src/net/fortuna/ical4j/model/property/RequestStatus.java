// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import java.util.StringTokenizer;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.util.ParameterValidator;

public final class RequestStatus extends Property
{

    public static final long serialVersionUID = 0xd2909d3fc4ffbe6fL;
    private String description;
    private String exData;
    private String statusCode;

    public RequestStatus()
    {
        super("REQUEST-STATUS", PropertyFactoryImpl.instance);
    }

    public final String getValue()
    {
        StringBuffer stringbuffer = new StringBuffer();
        if (statusCode != null)
        {
            stringbuffer.append(statusCode);
        }
        if (description != null)
        {
            stringbuffer.append(';');
            stringbuffer.append(description);
        }
        if (exData != null)
        {
            stringbuffer.append(';');
            stringbuffer.append(exData);
        }
        return stringbuffer.toString();
    }

    public final void setValue(String s)
    {
        s = new StringTokenizer(s, ";");
        if (s.hasMoreTokens())
        {
            statusCode = s.nextToken();
        }
        if (s.hasMoreTokens())
        {
            description = s.nextToken();
        }
        if (s.hasMoreTokens())
        {
            exData = s.nextToken();
        }
    }

    public final void validate()
        throws ValidationException
    {
        ParameterValidator.assertOneOrLess("LANGUAGE", super.parameters);
    }
}
