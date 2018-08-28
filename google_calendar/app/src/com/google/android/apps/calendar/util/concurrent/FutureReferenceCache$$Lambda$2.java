// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.android.apps.calendar.util.collect.ReferenceCache;
import com.google.android.apps.calendar.util.function.Consumer;
import java.util.Map;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            FutureReferenceCache

final class arg._cls2
    implements Consumer
{

    private final FutureReferenceCache arg$1;
    private final Object arg$2;

    public final void accept(Object obj)
    {
        Object obj2 = arg$1;
        Object obj1 = arg$2;
        obj2 = ((FutureReferenceCache) (obj2)).valueReferenceCache;
        ((ReferenceCache) (obj2)).keyToValueReferenceMap.put(obj1, ((ReferenceCache) (obj2)).type.ence(((ReferenceCache) (obj2)), obj1, obj, ((ReferenceCache) (obj2)).finalizableReferenceQueue));
    }

    (FutureReferenceCache futurereferencecache, Object obj)
    {
        arg$1 = futurereferencecache;
        arg$2 = obj;
    }
}
