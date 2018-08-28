// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;

public abstract class AbstractReadableInstantFieldProperty
    implements Serializable
{

    public static final long serialVersionUID = 0x1b5b33e6c8f3f47dL;

    public AbstractReadableInstantFieldProperty()
    {
    }

    public boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof AbstractReadableInstantFieldProperty))
        {
            return false;
        }
        Object obj1 = (AbstractReadableInstantFieldProperty)obj;
        if (getField().get(getMillis()) != ((AbstractReadableInstantFieldProperty) (obj1)).getField().get(((AbstractReadableInstantFieldProperty) (obj1)).getMillis()) || !getField().getType().equals(((AbstractReadableInstantFieldProperty) (obj1)).getField().getType()))
        {
            break; /* Loop/switch isn't completed */
        }
        obj = getChronology();
        obj1 = ((AbstractReadableInstantFieldProperty) (obj1)).getChronology();
        boolean flag;
        if (obj == obj1)
        {
            flag = true;
        } else
        if (obj == null || obj1 == null)
        {
            flag = false;
        } else
        {
            flag = obj.equals(obj1);
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public Chronology getChronology()
    {
        throw new UnsupportedOperationException("The method getChronology() was added in v1.4 and needs to be implemented by subclasses of AbstractReadableInstantFieldProperty");
    }

    public abstract DateTimeField getField();

    public abstract long getMillis();

    public int hashCode()
    {
        return getField().get(getMillis()) * 17 + getField().getType().hashCode() + getChronology().hashCode();
    }

    public String toString()
    {
        String s = getField().getName();
        return (new StringBuilder(String.valueOf(s).length() + 10)).append("Property[").append(s).append("]").toString();
    }
}
