// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.devtools.build.android.desugar.runtime;

import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

final class WeakKey
{

    private final ConcurrentHashMap map = new ConcurrentHashMap(16, 0.75F, 10);
    private final ReferenceQueue referenceQueue = new ReferenceQueue();

    public final List get(Throwable throwable, boolean flag)
    {
        for (java.lang.ref.Reference reference = referenceQueue.poll(); reference != null; reference = referenceQueue.poll())
        {
            map.remove(reference);
        }

        class WeakKey extends WeakReference
        {

            private final int hash;

            public final boolean equals(Object obj1)
            {
                boolean flag2 = true;
                if (obj1 != null && obj1.getClass() == getClass()) goto _L2; else goto _L1
_L1:
                boolean flag1 = false;
_L4:
                return flag1;
_L2:
                flag1 = flag2;
                if (this == obj1) goto _L4; else goto _L3
_L3:
                obj1 = (WeakKey)obj1;
                if (hash != ((WeakKey) (obj1)).hash)
                {
                    break; /* Loop/switch isn't completed */
                }
                flag1 = flag2;
                if (get() == ((WeakKey) (obj1)).get()) goto _L4; else goto _L5
_L5:
                return false;
            }

            public final int hashCode()
            {
                return hash;
            }

            public WeakKey(Throwable throwable, ReferenceQueue referencequeue)
            {
                super(throwable, referencequeue);
                if (throwable == null)
                {
                    throw new NullPointerException("The referent cannot be null");
                } else
                {
                    hash = System.identityHashCode(throwable);
                    return;
                }
            }
        }

        Object obj = new WeakKey(throwable, null);
        List list = (List)map.get(obj);
        if (!flag)
        {
            obj = list;
        } else
        {
            obj = list;
            if (list == null)
            {
                Vector vector = new Vector(2);
                throwable = (List)map.putIfAbsent(new WeakKey(throwable, referenceQueue), vector);
                obj = throwable;
                if (throwable == null)
                {
                    return vector;
                }
            }
        }
        return ((List) (obj));
    }

    WeakKey()
    {
    }
}
