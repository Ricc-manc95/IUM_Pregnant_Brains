// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ObjectPool, SharedResourceHolder

public final class SharedResourcePool
    implements ObjectPool
{

    private final SharedResourceHolder.Resource resource;

    SharedResourcePool(SharedResourceHolder.Resource resource1)
    {
        resource = resource1;
    }

    public final Object getObject()
    {
        SharedResourceHolder.Resource resource1 = resource;
        return SharedResourceHolder.holder.getInternal(resource1);
    }

    public final Object returnObject(Object obj)
    {
        SharedResourceHolder.Resource resource1 = resource;
        SharedResourceHolder.holder.releaseInternal(resource1, obj);
        return null;
    }
}
