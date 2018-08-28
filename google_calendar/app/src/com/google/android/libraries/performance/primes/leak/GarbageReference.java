// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.leak;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public final class GarbageReference extends PhantomReference
{

    public final String name;
    public GarbageReference next;
    public GarbageReference prev;

    public GarbageReference(Object obj, String s, ReferenceQueue referencequeue)
    {
        super(obj, referencequeue);
        name = s;
    }

    public final void insertAfter(GarbageReference garbagereference)
    {
        if (garbagereference == null)
        {
            throw new NullPointerException();
        }
        prev = garbagereference;
        next = garbagereference.next;
        if (next != null)
        {
            next.prev = this;
        }
        garbagereference.next = this;
    }

    public final GarbageReference removeSelf()
    {
        if (prev != null)
        {
            prev.next = next;
        }
        if (next != null)
        {
            next.prev = prev;
        }
        next = null;
        prev = null;
        return this;
    }
}
