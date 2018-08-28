// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

// Referenced classes of package net.fortuna.ical4j.model:
//            PropertyList, ComponentList

public final class Calendar
    implements Serializable
{

    public static final long serialVersionUID = 0xe90b6441b9a0514cL;
    public ComponentList components;
    public PropertyList properties;

    public Calendar()
    {
        this(new PropertyList(), new ComponentList());
    }

    public Calendar(PropertyList propertylist, ComponentList componentlist)
    {
        properties = propertylist;
        components = componentlist;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof Calendar)
        {
            obj = (Calendar)obj;
            return (new EqualsBuilder()).append(properties, ((Calendar) (obj)).properties).append(components, ((Calendar) (obj)).components).isEquals;
        } else
        {
            return super.equals(obj);
        }
    }

    public final int hashCode()
    {
        return (new HashCodeBuilder()).append(properties).append(components).iTotal;
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("BEGIN");
        stringbuffer.append(':');
        stringbuffer.append("VCALENDAR");
        stringbuffer.append("\r\n");
        stringbuffer.append(properties);
        stringbuffer.append(components);
        stringbuffer.append("END");
        stringbuffer.append(':');
        stringbuffer.append("VCALENDAR");
        stringbuffer.append("\r\n");
        return stringbuffer.toString();
    }
}
