// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.XProperty;
import net.fortuna.ical4j.util.Strings;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

// Referenced classes of package net.fortuna.ical4j.model:
//            Content, ParameterList, Parameter, Escapable, 
//            ValidationException, PropertyFactory

public abstract class Property extends Content
{

    public static final long serialVersionUID = 0x61d2511e8c75386fL;
    public String name;
    public ParameterList parameters;

    public Property(String s, ParameterList parameterlist, PropertyFactory propertyfactory)
    {
        name = s;
        parameters = parameterlist;
    }

    public Property(String s, PropertyFactory propertyfactory)
    {
        this(s, new ParameterList(), propertyfactory);
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof Property)
        {
            obj = (Property)obj;
            if (name.equals(((Property) (obj)).name))
            {
                return (new EqualsBuilder()).append(getValue(), ((Content) (obj)).getValue()).append(parameters, ((Property) (obj)).parameters).isEquals;
            } else
            {
                return false;
            }
        } else
        {
            return super.equals(obj);
        }
    }

    public int hashCode()
    {
        return (new HashCodeBuilder()).append(name.toUpperCase()).append(getValue()).append(parameters).iTotal;
    }

    public abstract void setValue(String s)
        throws IOException, URISyntaxException, ParseException;

    public final String toString()
    {
        StringBuffer stringbuffer;
        stringbuffer = new StringBuffer();
        stringbuffer.append(name);
        if (parameters != null)
        {
            stringbuffer.append(parameters);
        }
        stringbuffer.append(':');
        if (!(this instanceof XProperty)) goto _L2; else goto _L1
_L1:
        Value value = (Value)parameters.getParameter("VALUE");
        if (value != null && !value.equals(Value.TEXT)) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        if (flag)
        {
            stringbuffer.append(Strings.escape(Strings.valueOf(getValue())));
        } else
        {
            stringbuffer.append(Strings.valueOf(getValue()));
        }
        stringbuffer.append("\r\n");
        return stringbuffer.toString();
_L2:
        if (this instanceof Escapable)
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L4:
        flag = false;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public abstract void validate()
        throws ValidationException;
}
