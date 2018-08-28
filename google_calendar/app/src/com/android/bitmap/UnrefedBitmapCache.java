// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap;

import android.os.Trace;
import android.util.LruCache;

// Referenced classes of package com.android.bitmap:
//            UnrefedPooledCache, BitmapCache, ReusableBitmap, RequestKey, 
//            Poolable

public class UnrefedBitmapCache extends UnrefedPooledCache
    implements BitmapCache
{

    private boolean blocking;
    private final Object lock = new Object();
    private LruCache nullRequests;

    public UnrefedBitmapCache(int i, float f, int j)
    {
        super(i, 0.0F);
        blocking = false;
    }

    private final ReusableBitmap get(RequestKey requestkey, boolean flag)
    {
        if (nullRequests != null && nullRequests.get(requestkey) != null)
        {
            if (ReusableBitmap.NullReusableBitmap.instance == null)
            {
                ReusableBitmap.NullReusableBitmap.instance = new ReusableBitmap.NullReusableBitmap();
            }
            return ReusableBitmap.NullReusableBitmap.instance;
        } else
        {
            return (ReusableBitmap)super.get(requestkey, flag);
        }
    }

    private final ReusableBitmap poll()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
_L1:
        Object obj1 = (ReusableBitmap)super.poll();
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        if (!blocking)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        Trace.beginSection("sleep");
        try
        {
            lock.wait();
        }
        catch (InterruptedException interruptedexception) { }
        Trace.endSection();
          goto _L1
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        obj;
        JVM INSTR monitorexit ;
        return ((ReusableBitmap) (obj1));
    }

    private final ReusableBitmap put(RequestKey requestkey, ReusableBitmap reusablebitmap)
    {
label0:
        {
            if (nullRequests == null)
            {
                break label0;
            }
            if (reusablebitmap != null)
            {
                if (ReusableBitmap.NullReusableBitmap.instance == null)
                {
                    ReusableBitmap.NullReusableBitmap.instance = new ReusableBitmap.NullReusableBitmap();
                }
                if (reusablebitmap != ReusableBitmap.NullReusableBitmap.instance)
                {
                    break label0;
                }
            }
            reusablebitmap = nullRequests;
            if (ReusableBitmap.NullReusableBitmap.instance == null)
            {
                ReusableBitmap.NullReusableBitmap.instance = new ReusableBitmap.NullReusableBitmap();
            }
            reusablebitmap.put(requestkey, ReusableBitmap.NullReusableBitmap.instance);
            return null;
        }
        return (ReusableBitmap)super.put(requestkey, reusablebitmap);
    }

    public final volatile Poolable get(Object obj, boolean flag)
    {
        return get((RequestKey)obj, flag);
    }

    public final volatile Object get(Object obj, boolean flag)
    {
        return get((RequestKey)obj, true);
    }

    public final void offer(Poolable poolable)
    {
        ReusableBitmap reusablebitmap = (ReusableBitmap)poolable;
        synchronized (lock)
        {
            super.offer(reusablebitmap);
            lock.notify();
        }
        return;
        exception;
        poolable;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void offer(Object obj)
    {
        ReusableBitmap reusablebitmap = (ReusableBitmap)obj;
        synchronized (lock)
        {
            super.offer(reusablebitmap);
            lock.notify();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final volatile Poolable poll()
    {
        return (ReusableBitmap)poll();
    }

    public final volatile Object poll()
    {
        return poll();
    }

    public final volatile Poolable put(Object obj, Poolable poolable)
    {
        return put((RequestKey)obj, (ReusableBitmap)poolable);
    }

    public final volatile Object put(Object obj, Object obj1)
    {
        return put((RequestKey)obj, (ReusableBitmap)obj1);
    }

    public final void setBlocking(boolean flag)
    {
        synchronized (lock)
        {
            blocking = flag;
            if (!blocking)
            {
                lock.notifyAll();
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected final int sizeOf(Poolable poolable)
    {
        return ((ReusableBitmap)poolable).getByteCount();
    }

    static 
    {
        com/android/bitmap/UnrefedBitmapCache.getSimpleName();
    }
}
