// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.lang.ref.WeakReference;

// Referenced classes of package com.google.common.base:
//            FinalizableReference, FinalizableReferenceQueue

public abstract class FinalizableWeakReference extends WeakReference
    implements FinalizableReference
{

    public FinalizableWeakReference(Object obj, FinalizableReferenceQueue finalizablereferencequeue)
    {
        super(obj, finalizablereferencequeue.queue);
        finalizablereferencequeue.cleanUp();
    }
}
