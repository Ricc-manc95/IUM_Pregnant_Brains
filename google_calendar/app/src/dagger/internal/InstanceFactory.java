// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package dagger.internal;

import dagger.Lazy;

// Referenced classes of package dagger.internal:
//            Factory

public final class InstanceFactory
    implements Lazy, Factory
{

    private final Object instance;

    public InstanceFactory(Object obj)
    {
        instance = obj;
    }

    public final Object get()
    {
        return instance;
    }

    static 
    {
        new InstanceFactory(null);
    }
}
