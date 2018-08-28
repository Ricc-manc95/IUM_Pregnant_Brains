// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package net.fortuna.ical4j.model:
//            Property

public final class PropertyList extends ArrayList
    implements Serializable
{

    public static final long serialVersionUID = 0x84d2625c7cb75e39L;

    public PropertyList()
    {
    }

    public final boolean add(Object obj)
    {
        if (!(obj instanceof Property))
        {
            obj = String.valueOf(net/fortuna/ical4j/model/Property.getName());
            if (((String) (obj)).length() != 0)
            {
                obj = "Argument not a ".concat(((String) (obj)));
            } else
            {
                obj = new String("Argument not a ");
            }
            throw new IllegalArgumentException(((String) (obj)));
        } else
        {
            return super.add(obj);
        }
    }

    public final PropertyList getProperties(String s)
    {
        PropertyList propertylist = new PropertyList();
        Iterator iterator = iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Property property = (Property)iterator.next();
            if (property.name.equalsIgnoreCase(s))
            {
                propertylist.add(property);
            }
        } while (true);
        return propertylist;
    }

    public final Property getProperty(String s)
    {
        for (Iterator iterator = iterator(); iterator.hasNext();)
        {
            Property property = (Property)iterator.next();
            if (property.name.equalsIgnoreCase(s))
            {
                return property;
            }
        }

        return null;
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        for (Iterator iterator = iterator(); iterator.hasNext(); stringbuffer.append(iterator.next().toString())) { }
        return stringbuffer.toString();
    }
}
