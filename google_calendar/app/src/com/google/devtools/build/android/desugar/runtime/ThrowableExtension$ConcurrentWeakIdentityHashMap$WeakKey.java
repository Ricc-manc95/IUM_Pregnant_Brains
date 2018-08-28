// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.devtools.build.android.desugar.runtime;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class hash extends WeakReference
{

    private final int hash;

    public final boolean equals(Object obj)
    {
        boolean flag1 = true;
        if (obj != null && obj.getClass() == getClass()) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        flag = flag1;
        if (this == obj) goto _L4; else goto _L3
_L3:
        obj = (hash)obj;
        if (hash != ((hash) (obj)).hash)
        {
            break; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (get() == ((get) (obj)).get()) goto _L4; else goto _L5
_L5:
        return false;
    }

    public final int hashCode()
    {
        return hash;
    }

    public (Throwable throwable, ReferenceQueue referencequeue)
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
