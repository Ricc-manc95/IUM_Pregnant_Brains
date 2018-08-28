// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.Comparator;

final class val.priorityAccessor
    implements Comparator
{

    private final iorityAccessor val$priorityAccessor;

    public final int compare(Object obj, Object obj1)
    {
        return val$priorityAccessor.getPriority(obj) - val$priorityAccessor.getPriority(obj1);
    }

    iorityAccessor()
    {
        val$priorityAccessor = iorityaccessor;
        super();
    }
}
