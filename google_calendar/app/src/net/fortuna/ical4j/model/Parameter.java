// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.fortuna.ical4j.util.Strings;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

// Referenced classes of package net.fortuna.ical4j.model:
//            Content, ParameterFactory

public abstract class Parameter extends Content
{

    public static final long serialVersionUID = 0xe36ebf0fdf50ba88L;
    public String name;

    public Parameter(String s, ParameterFactory parameterfactory)
    {
        name = s;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof Parameter)
        {
            obj = (Parameter)obj;
            return (new EqualsBuilder()).append(name, ((Parameter) (obj)).name).append(getValue(), ((Content) (obj)).getValue()).isEquals;
        } else
        {
            return super.equals(obj);
        }
    }

    public final int hashCode()
    {
        return (new HashCodeBuilder()).append(name.toUpperCase()).append(getValue()).iTotal;
    }

    public boolean isQuotable()
    {
        return Strings.PARAM_QUOTE_PATTERN.matcher(Strings.valueOf(getValue())).find();
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(name);
        stringbuffer.append('=');
        if (isQuotable())
        {
            stringbuffer.append(Strings.quote(Strings.valueOf(getValue())));
        } else
        {
            stringbuffer.append(Strings.valueOf(getValue()));
        }
        return stringbuffer.toString();
    }
}
