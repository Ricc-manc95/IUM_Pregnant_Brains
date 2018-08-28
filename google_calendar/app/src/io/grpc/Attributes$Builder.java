// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package io.grpc:
//            Attributes

public static final class base
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
                java.util.se se = (java.util.se)iterator.next();
                if (!newdata.containsKey(se.wdata()))
                {
                    newdata.put((newdata)se.wdata(), se.wdata());
                }
            } while (true);
            base = new Attributes(newdata);
            newdata = null;
        }
        return base;
    }

    public final base set(base base1, Object obj)
    {
        if (newdata == null)
        {
            newdata = new IdentityHashMap(1);
        }
        newdata.put(base1, obj);
        return this;
    }

    (Attributes attributes)
    {
        base = attributes;
    }
}
