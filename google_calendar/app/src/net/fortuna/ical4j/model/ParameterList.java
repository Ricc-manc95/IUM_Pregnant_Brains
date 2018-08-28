// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

// Referenced classes of package net.fortuna.ical4j.model:
//            Parameter

public final class ParameterList
    implements Serializable
{

    public static final long serialVersionUID = 0xe57372386dcce187L;
    public final List parameters;

    public ParameterList()
    {
        this(false);
    }

    public ParameterList(boolean flag)
    {
        if (flag)
        {
            parameters = Collections.unmodifiableList(new ArrayList());
            return;
        } else
        {
            parameters = new CopyOnWriteArrayList();
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof ParameterList)
        {
            obj = (ParameterList)obj;
            return ObjectUtils.equals(parameters, ((ParameterList) (obj)).parameters);
        } else
        {
            return super.equals(obj);
        }
    }

    public final Parameter getParameter(String s)
    {
        for (Iterator iterator = parameters.iterator(); iterator.hasNext();)
        {
            Parameter parameter = (Parameter)iterator.next();
            if (s.equalsIgnoreCase(parameter.name))
            {
                return parameter;
            }
        }

        return null;
    }

    public final ParameterList getParameters(String s)
    {
        ParameterList parameterlist = new ParameterList();
        Iterator iterator = parameters.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Parameter parameter = (Parameter)iterator.next();
            if (parameter.name.equalsIgnoreCase(s))
            {
                if (parameter == null)
                {
                    throw new IllegalArgumentException("Trying to add null Parameter");
                }
                parameterlist.parameters.add(parameter);
            }
        } while (true);
        return parameterlist;
    }

    public final int hashCode()
    {
        return (new HashCodeBuilder()).append(parameters).iTotal;
    }

    public final boolean replace(Parameter parameter)
    {
        Parameter parameter1;
        for (Iterator iterator = getParameters(parameter.name).parameters.iterator(); iterator.hasNext(); parameters.remove(parameter1))
        {
            parameter1 = (Parameter)iterator.next();
        }

        if (parameter == null)
        {
            throw new IllegalArgumentException("Trying to add null Parameter");
        } else
        {
            return parameters.add(parameter);
        }
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        for (Iterator iterator = parameters.iterator(); iterator.hasNext(); stringbuffer.append(iterator.next().toString()))
        {
            stringbuffer.append(';');
        }

        return stringbuffer.toString();
    }
}
