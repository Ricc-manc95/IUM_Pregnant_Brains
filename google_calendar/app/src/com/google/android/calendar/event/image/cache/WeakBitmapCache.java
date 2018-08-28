// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image.cache;

import com.android.bitmap.BitmapCache;
import com.android.bitmap.RequestKey;
import com.android.bitmap.ReusableBitmap;
import com.android.calendarcommon2.LogUtils;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WeakBitmapCache
    implements BitmapCache
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/event/image/cache/WeakBitmapCache);
    private final boolean allowNull;
    private boolean blocking;
    private final Map cache = new HashMap();
    private final List pool = new ArrayList();

    public WeakBitmapCache(boolean flag)
    {
        blocking = false;
        allowNull = flag;
    }

    private final void clearDeadReferences()
    {
        int i = 0;
        Map map = cache;
        map;
        JVM INSTR monitorenter ;
        Object obj;
        obj = new ArrayList();
        Iterator iterator = cache.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            RequestKey requestkey = (RequestKey)iterator.next();
            if (((Reference)cache.get(requestkey)).get() == null)
            {
                ((List) (obj)).add(requestkey);
            }
        } while (true);
        break MISSING_BLOCK_LABEL_90;
        obj;
        map;
        JVM INSTR monitorexit ;
        throw obj;
        ArrayList arraylist;
        int j;
        arraylist = (ArrayList)obj;
        j = arraylist.size();
_L1:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_144;
        }
        Object obj1 = arraylist.get(i);
        i++;
        obj1 = (RequestKey)obj1;
        cache.remove(obj1);
          goto _L1
        LogUtils.d(TAG, "clearDeadReferences(): removed %d entries", new Object[] {
            Integer.valueOf(((List) (obj)).size())
        });
        map;
        JVM INSTR monitorexit ;
    }

    private final ReusableBitmap get(RequestKey requestkey, boolean flag)
    {
        Map map = cache;
        map;
        JVM INSTR monitorenter ;
        Reference reference = (Reference)cache.get(requestkey);
        if (reference != null) goto _L2; else goto _L1
_L1:
        requestkey = null;
_L7:
        if (reference == null || requestkey != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        clearDeadReferences();
        if (requestkey == null) goto _L4; else goto _L3
_L3:
        LogUtils.d(TAG, "get(): hit", new Object[0]);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        requestkey.acquireReference();
_L5:
        map;
        JVM INSTR monitorexit ;
        return requestkey;
_L2:
        requestkey = (ReusableBitmap)reference.get();
        continue; /* Loop/switch isn't completed */
_L4:
        LogUtils.d(TAG, "get(): miss", new Object[0]);
          goto _L5
        requestkey;
        map;
        JVM INSTR monitorexit ;
        throw requestkey;
        if (true) goto _L7; else goto _L6
_L6:
    }

    private final ReusableBitmap maybeFindPoolBitmap()
    {
        List list = pool;
        list;
        JVM INSTR monitorenter ;
        ReusableBitmap reusablebitmap;
        do
        {
            if (pool.isEmpty())
            {
                break MISSING_BLOCK_LABEL_70;
            }
            reusablebitmap = (ReusableBitmap)((Reference)pool.remove(pool.size() - 1)).get();
        } while (reusablebitmap == null);
        LogUtils.d(TAG, "poll(): found in pool", new Object[0]);
        list;
        JVM INSTR monitorexit ;
        return reusablebitmap;
        list;
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private final ReusableBitmap maybeFindUnusedCacheBitmap()
    {
        boolean flag2 = false;
        Map map = cache;
        map;
        JVM INSTR monitorenter ;
        Iterator iterator = cache.keySet().iterator();
        boolean flag = false;
_L4:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        RequestKey requestkey;
        Reference reference;
        Object obj;
        requestkey = (RequestKey)iterator.next();
        reference = (Reference)cache.get(requestkey);
        obj = reference.get();
        Exception exception;
        boolean flag1;
        if (obj == null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        flag = flag1 | flag;
        obj = (ReusableBitmap)reference.get();
        if (obj == null) goto _L4; else goto _L3
_L3:
        if (((ReusableBitmap) (obj)).refCount != 0 || !((ReusableBitmap) (obj)).reusable) goto _L4; else goto _L5
_L5:
        LogUtils.d(TAG, "poll(): found in store", new Object[0]);
        flag1 = flag2;
        if (cache.remove(requestkey) == reference)
        {
            flag1 = true;
        }
        if (flag1) goto _L7; else goto _L6
_L6:
        throw new IllegalStateException();
        exception;
_L9:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_173;
        }
        clearDeadReferences();
        throw exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
_L7:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_195;
        }
        clearDeadReferences();
        map;
        JVM INSTR monitorexit ;
        return ((ReusableBitmap) (obj));
_L2:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_209;
        }
        clearDeadReferences();
        map;
        JVM INSTR monitorexit ;
        return null;
        exception;
        flag = false;
        continue; /* Loop/switch isn't completed */
        exception;
        if (true) goto _L9; else goto _L8
_L8:
    }

    private final ReusableBitmap poll()
    {
_L5:
        Object obj = maybeFindPoolBitmap();
        if (obj == null) goto _L2; else goto _L1
_L1:
        return ((ReusableBitmap) (obj));
_L2:
        ReusableBitmap reusablebitmap;
        reusablebitmap = maybeFindUnusedCacheBitmap();
        obj = reusablebitmap;
        if (reusablebitmap != null) goto _L1; else goto _L3
_L3:
        obj = pool;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        if (!blocking)
        {
            break; /* Loop/switch isn't completed */
        }
        flag = pool.isEmpty();
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        pool.wait();
        obj;
        JVM INSTR monitorexit ;
        if (true) goto _L5; else goto _L4
        Object obj1;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        return null;
_L4:
        obj;
        JVM INSTR monitorexit ;
        return null;
    }

    public final volatile Object get(Object obj, boolean flag)
    {
        return get((RequestKey)obj, true);
    }

    public final void offer(Object obj)
    {
        ReusableBitmap reusablebitmap = (ReusableBitmap)obj;
        if (com.android.bitmap.ReusableBitmap.NullReusableBitmap.instance == null)
        {
            com.android.bitmap.ReusableBitmap.NullReusableBitmap.instance = new com.android.bitmap.ReusableBitmap.NullReusableBitmap();
        }
        if (reusablebitmap != com.android.bitmap.ReusableBitmap.NullReusableBitmap.instance)
        {
            boolean flag;
            if (reusablebitmap.refCount == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            }
            if (!reusablebitmap.reusable)
            {
                throw new IllegalStateException();
            }
            synchronized (pool)
            {
                pool.add(new SoftReference(reusablebitmap));
                pool.notifyAll();
            }
            return;
        } else
        {
            return;
        }
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final volatile Object poll()
    {
        return poll();
    }

    public ReusableBitmap put(RequestKey requestkey, ReusableBitmap reusablebitmap)
    {
        Map map = cache;
        map;
        JVM INSTR monitorenter ;
        if (reusablebitmap == null) goto _L2; else goto _L1
_L1:
        if (com.android.bitmap.ReusableBitmap.NullReusableBitmap.instance == null)
        {
            com.android.bitmap.ReusableBitmap.NullReusableBitmap.instance = new com.android.bitmap.ReusableBitmap.NullReusableBitmap();
        }
        if (reusablebitmap != com.android.bitmap.ReusableBitmap.NullReusableBitmap.instance) goto _L3; else goto _L2
_L2:
        if (!allowNull)
        {
            break MISSING_BLOCK_LABEL_140;
        }
        reusablebitmap = cache;
        if (com.android.bitmap.ReusableBitmap.NullReusableBitmap.instance == null)
        {
            com.android.bitmap.ReusableBitmap.NullReusableBitmap.instance = new com.android.bitmap.ReusableBitmap.NullReusableBitmap();
        }
        requestkey = (Reference)reusablebitmap.put(requestkey, new SoftReference(com.android.bitmap.ReusableBitmap.NullReusableBitmap.instance));
          goto _L4
_L5:
        map;
        JVM INSTR monitorexit ;
        return requestkey;
_L3:
        requestkey = (Reference)cache.put(requestkey, new SoftReference(reusablebitmap));
          goto _L4
_L7:
        requestkey = (ReusableBitmap)requestkey.get();
          goto _L5
        requestkey;
        map;
        JVM INSTR monitorexit ;
        throw requestkey;
_L4:
        if (requestkey != null) goto _L7; else goto _L6
_L6:
        requestkey = null;
          goto _L5
        requestkey = null;
          goto _L4
    }

    public volatile Object put(Object obj, Object obj1)
    {
        return put((RequestKey)obj, (ReusableBitmap)obj1);
    }

    public final void setBlocking(boolean flag)
    {
        synchronized (pool)
        {
            blocking = flag;
            pool.notifyAll();
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

}
