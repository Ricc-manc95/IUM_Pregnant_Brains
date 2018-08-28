// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap;

import android.util.LruCache;

// Referenced classes of package com.android.bitmap:
//            UnrefedPooledCache, Poolable

final class this._cls0 extends LruCache
{

    private final UnrefedPooledCache this$0;

    protected final int sizeOf(Object obj, Object obj1)
    {
        obj = (Poolable)obj1;
        return UnrefedPooledCache.this.sizeOf(((Poolable) (obj)));
    }

    public (int i)
    {
        this$0 = UnrefedPooledCache.this;
        super(i);
    }
}
