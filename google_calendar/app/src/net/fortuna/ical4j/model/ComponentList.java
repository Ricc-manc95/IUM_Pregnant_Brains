// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package net.fortuna.ical4j.model:
//            Component

public final class ComponentList extends ArrayList
    implements Serializable
{

    public static final long serialVersionUID = 0x656d366bb76a1959L;

    public ComponentList()
    {
    }

    public final boolean add(Object obj)
    {
        if (!(obj instanceof Component))
        {
            obj = String.valueOf(net/fortuna/ical4j/model/Component.getName());
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

    public final Component getComponent(String s)
    {
        for (Iterator iterator = iterator(); iterator.hasNext();)
        {
            Component component = (Component)iterator.next();
            if (component.name.equals(s))
            {
                return component;
            }
        }

        return null;
    }

    public final ComponentList getComponents(String s)
    {
        ComponentList componentlist = new ComponentList();
        Iterator iterator = iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Component component = (Component)iterator.next();
            if (component.name.equals(s))
            {
                componentlist.add(component);
            }
        } while (true);
        return componentlist;
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        for (Iterator iterator = iterator(); iterator.hasNext(); stringbuffer.append(iterator.next().toString())) { }
        return stringbuffer.toString();
    }
}
