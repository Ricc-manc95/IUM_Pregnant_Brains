// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.core.internal;

import java.util.HashMap;

// Referenced classes of package android.arch.core.internal:
//            SafeIterableMap

public final class FastSafeIterableMap extends SafeIterableMap
{

    public HashMap mHashMap;

    public FastSafeIterableMap()
    {
        mHashMap = new HashMap();
    }

    protected final SafeIterableMap.Entry get(Object obj)
    {
        return (SafeIterableMap.Entry)mHashMap.get(obj);
    }

    public final Object putIfAbsent(Object obj, Object obj1)
    {
        SafeIterableMap.Entry entry = get(obj);
        if (entry != null)
        {
            return entry.mValue;
        } else
        {
            mHashMap.put(obj, put(obj, obj1));
            return null;
        }
    }

    public final Object remove(Object obj)
    {
        Object obj1 = super.remove(obj);
        mHashMap.remove(obj);
        return obj1;
    }
}
