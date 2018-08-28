// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.collect;

import com.google.common.base.FinalizableReferenceQueue;
import com.google.common.base.FinalizableSoftReference;
import java.util.Map;

// Referenced classes of package com.google.android.apps.calendar.util.collect:
//            ReferenceCache

final class val.key extends FinalizableSoftReference
{

    private final ReferenceCache val$cache;
    private final Object val$key;

    public final void finalizeReferent()
    {
        val$cache.keyToValueReferenceMap.remove(val$key);
    }

    (ReferenceCache referencecache, Object obj1)
    {
        val$cache = referencecache;
        val$key = obj1;
        super(final_obj, final_finalizablereferencequeue);
    }
}
