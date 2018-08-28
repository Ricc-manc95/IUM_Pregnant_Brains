// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class Attributes
{
    public static final class Builder
    {

        private Attributes base;
        public Map newdata;

        public final Attributes build()
        {
            if (newdata != null)
            {
                Iterator iterator = base.data.entrySet().iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                    if (!newdata.containsKey(entry.getKey()))
                    {
                        newdata.put((Key)entry.getKey(), entry.getValue());
                    }
                } while (true);
                base = new Attributes(newdata);
                newdata = null;
            }
            return base;
        }

        public final Builder set(Key key, Object obj)
        {
            if (newdata == null)
            {
                newdata = new IdentityHashMap(1);
            }
            newdata.put(key, obj);
            return this;
        }

        Builder(Attributes attributes)
        {
            base = attributes;
        }
    }

    public static final class Key
    {

        private final String debugString;

        public final String toString()
        {
            return debugString;
        }

        public Key(String s)
        {
            debugString = s;
        }
    }


    public static final Attributes EMPTY = new Attributes(Collections.emptyMap());
    public final Map data;

    Attributes(Map map)
    {
        data = map;
    }

    public static Builder newBuilder()
    {
        return new Builder(EMPTY);
    }

    public final boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        obj = (Attributes)obj;
        if (data.size() != ((Attributes) (obj)).data.size())
        {
            return false;
        }
        for (Iterator iterator = data.entrySet().iterator(); iterator.hasNext();)
        {
            Object obj2 = (java.util.Map.Entry)iterator.next();
            if (!((Attributes) (obj)).data.containsKey(((java.util.Map.Entry) (obj2)).getKey()))
            {
                return false;
            }
            Object obj1 = ((java.util.Map.Entry) (obj2)).getValue();
            obj2 = ((Attributes) (obj)).data.get(((java.util.Map.Entry) (obj2)).getKey());
            boolean flag;
            if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return false;
            }
        }

        return true;
    }

    public final int hashCode()
    {
        Iterator iterator = data.entrySet().iterator();
        java.util.Map.Entry entry;
        int i;
        for (i = 0; iterator.hasNext(); i = Arrays.hashCode(new Object[] {
    entry.getKey(), entry.getValue()
}) + i)
        {
            entry = (java.util.Map.Entry)iterator.next();
        }

        return i;
    }

    public final String toString()
    {
        return data.toString();
    }

}
