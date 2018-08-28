// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap;

import android.os.Trace;
import android.util.LruCache;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

// Referenced classes of package com.android.bitmap:
//            PooledCache, Poolable

public class UnrefedPooledCache
    implements PooledCache
{
    final class NonPooledCache extends LruCache
    {

        private final UnrefedPooledCache this$0;

        protected final int sizeOf(Object obj, Object obj1)
        {
            obj = (Poolable)obj1;
            return UnrefedPooledCache.this.sizeOf(((Poolable) (obj)));
        }

        public NonPooledCache(int i)
        {
            this$0 = UnrefedPooledCache.this;
            super(i);
        }
    }


    private final LinkedHashMap cache = new LinkedHashMap(0, 0.75F, true);
    private final LruCache nonPooledCache;
    private final LinkedBlockingQueue pool = new LinkedBlockingQueue();
    private final int targetSize;

    public UnrefedPooledCache(int i, float f)
    {
        int j = Math.round((float)i * f);
        if (j > 0)
        {
            nonPooledCache = new NonPooledCache(j);
        } else
        {
            nonPooledCache = null;
        }
        targetSize = i - j;
    }

    public Poolable get(Object obj, boolean flag)
    {
        Trace.beginSection("cache get");
        LinkedHashMap linkedhashmap = cache;
        linkedhashmap;
        JVM INSTR monitorenter ;
        Poolable poolable1 = (Poolable)cache.get(obj);
        Poolable poolable;
        poolable = poolable1;
        if (poolable1 != null)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        poolable = poolable1;
        if (nonPooledCache != null)
        {
            poolable = (Poolable)nonPooledCache.get(obj);
        }
        if (!flag || poolable == null)
        {
            break MISSING_BLOCK_LABEL_71;
        }
        poolable.acquireReference();
        Trace.endSection();
        linkedhashmap;
        JVM INSTR monitorexit ;
        return poolable;
        obj;
        linkedhashmap;
        JVM INSTR monitorexit ;
        throw obj;
    }

    public volatile Object get(Object obj, boolean flag)
    {
        return get(obj, true);
    }

    public void offer(Poolable poolable)
    {
        Trace.beginSection("pool offer");
        if (poolable.getRefCount() != 0 || !poolable.isEligibleForPooling())
        {
            Trace.endSection();
            poolable = String.valueOf(poolable);
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(poolable).length() + 39)).append("unexpected offer of an invalid object: ").append(poolable).toString());
        } else
        {
            pool.offer(poolable);
            Trace.endSection();
            return;
        }
    }

    public volatile void offer(Object obj)
    {
        offer((Poolable)obj);
    }

    public Poolable poll()
    {
        Trace.beginSection("pool poll");
        Poolable poolable = (Poolable)pool.poll();
        if (poolable != null)
        {
            Trace.endSection();
            return poolable;
        }
        LinkedHashMap linkedhashmap = cache;
        linkedhashmap;
        JVM INSTR monitorenter ;
        int i = 0;
        Iterator iterator = cache.entrySet().iterator();
        Object obj = null;
_L4:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        Object obj1;
        Poolable poolable1;
        obj1 = (java.util.Map.Entry)iterator.next();
        poolable1 = (Poolable)((java.util.Map.Entry) (obj1)).getValue();
        if (poolable1.getRefCount() > 0 || !poolable1.isEligibleForPooling()) goto _L4; else goto _L3
_L3:
        if (obj == null)
        {
            obj = obj1;
        }
        i = sizeOf(poolable1) + i;
        int j;
        obj1 = obj;
        j = i;
        if (i > targetSize) goto _L5; else goto _L4
_L5:
        if (j > targetSize)
        {
            break MISSING_BLOCK_LABEL_154;
        }
        Trace.endSection();
        linkedhashmap;
        JVM INSTR monitorexit ;
        return null;
        cache.remove(((java.util.Map.Entry) (obj1)).getKey());
        Trace.endSection();
        obj = (Poolable)((java.util.Map.Entry) (obj1)).getValue();
        linkedhashmap;
        JVM INSTR monitorexit ;
        return ((Poolable) (obj));
        obj;
        linkedhashmap;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        obj1 = obj;
        j = i;
        if (true) goto _L5; else goto _L6
_L6:
    }

    public volatile Object poll()
    {
        return poll();
    }

    public Poolable put(Object obj, Poolable poolable)
    {
        Poolable poolable1;
        poolable1 = null;
        Trace.beginSection("cache put");
        if (poolable == null)
        {
            Trace.endSection();
            return null;
        }
        LinkedHashMap linkedhashmap = cache;
        linkedhashmap;
        JVM INSTR monitorenter ;
        if (!poolable.isEligibleForPooling()) goto _L2; else goto _L1
_L1:
        poolable1 = (Poolable)cache.put(obj, poolable);
_L4:
        Trace.endSection();
        return poolable1;
        obj;
        linkedhashmap;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        if (nonPooledCache == null) goto _L4; else goto _L3
_L3:
        poolable1 = (Poolable)nonPooledCache.put(obj, poolable);
          goto _L4
    }

    public volatile Object put(Object obj, Object obj1)
    {
        return put(obj, (Poolable)obj1);
    }

    protected int sizeOf(Poolable poolable)
    {
        return 1;
    }

    static 
    {
        com/android/bitmap/UnrefedPooledCache.getSimpleName();
    }
}
