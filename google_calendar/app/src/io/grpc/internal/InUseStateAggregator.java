// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.HashSet;

abstract class InUseStateAggregator
{

    public final HashSet inUseObjects = new HashSet();

    InUseStateAggregator()
    {
    }

    abstract void handleInUse();

    abstract void handleNotInUse();

    final void updateObjectInUse(Object obj, boolean flag)
    {
        int i = inUseObjects.size();
        if (flag)
        {
            inUseObjects.add(obj);
            if (i == 0)
            {
                handleInUse();
            }
        } else
        if (inUseObjects.remove(obj) && i == 1)
        {
            handleNotInUse();
            return;
        }
    }
}
