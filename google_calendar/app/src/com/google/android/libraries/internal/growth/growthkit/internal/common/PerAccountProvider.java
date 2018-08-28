// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.common;

import java.util.HashMap;
import java.util.Map;

public class PerAccountProvider
{

    private final Factory factory;
    private final Object lock = new Object();
    private final Map perAccountMap = new HashMap();

    public PerAccountProvider(Factory factory1)
    {
        factory = factory1;
    }

    public final Object forAccount(String s)
    {
        Object obj;
        obj = perAccountMap.get(s);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        Object obj2 = lock;
        obj2;
        JVM INSTR monitorenter ;
        Object obj1 = perAccountMap.get(s);
        obj = obj1;
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_64;
        }
        obj = factory.createForAccount(s);
        perAccountMap.put(s, obj);
        obj2;
        JVM INSTR monitorexit ;
        return obj;
        s;
        obj2;
        JVM INSTR monitorexit ;
        throw s;
        return obj;
    }

    private class Factory
    {

        public abstract Object createForAccount(String s);
    }

}
