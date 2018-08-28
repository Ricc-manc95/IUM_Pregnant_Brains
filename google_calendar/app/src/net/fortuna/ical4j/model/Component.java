// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;
import java.util.Iterator;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

// Referenced classes of package net.fortuna.ical4j.model:
//            PropertyList, ValidationException, Property

public abstract class Component
    implements Serializable
{

    public static final long serialVersionUID = 0x4499c04881ed51f9L;
    public String name;
    public PropertyList properties;

    public Component(String s)
    {
        this(s, new PropertyList());
    }

    public Component(String s, PropertyList propertylist)
    {
        name = s;
        properties = propertylist;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof Component)
        {
            obj = (Component)obj;
            return (new EqualsBuilder()).append(name, ((Component) (obj)).name).append(properties, ((Component) (obj)).properties).isEquals;
        } else
        {
            return super.equals(obj);
        }
    }

    public int hashCode()
    {
        return (new HashCodeBuilder()).append(name).append(properties).iTotal;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("BEGIN");
        stringbuffer.append(':');
        stringbuffer.append(name);
        stringbuffer.append("\r\n");
        stringbuffer.append(properties);
        stringbuffer.append("END");
        stringbuffer.append(':');
        stringbuffer.append(name);
        stringbuffer.append("\r\n");
        return stringbuffer.toString();
    }

    public abstract void validate(boolean flag)
        throws ValidationException;

    public final void validateProperties()
        throws ValidationException
    {
        for (Iterator iterator = properties.iterator(); iterator.hasNext(); ((Property)iterator.next()).validate()) { }
    }
}
