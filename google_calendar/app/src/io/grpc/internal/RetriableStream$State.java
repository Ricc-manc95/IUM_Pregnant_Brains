// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.Collection;
import java.util.List;

final class eam.closed
{

    public final List buffer;
    public final boolean cancelled;
    public final Collection drainedSubstreams;
    public final boolean passThrough;
    public final eam winningSubstream;

    eam(List list, Collection collection, eam eam, boolean flag, boolean flag1)
    {
        boolean flag2;
label0:
        {
            boolean flag3 = false;
            super();
            buffer = list;
            if (collection == null)
            {
                throw new NullPointerException(String.valueOf("drainedSubstreams"));
            }
            drainedSubstreams = (Collection)collection;
            winningSubstream = eam;
            cancelled = flag;
            passThrough = flag1;
            if (!flag1 || list == null)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (!flag2)
            {
                throw new IllegalStateException(String.valueOf("passThrough should imply buffer is null"));
            }
            if (!flag1 || eam != null)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (!flag2)
            {
                throw new IllegalStateException(String.valueOf("passThrough should imply winningSubstream != null"));
            }
            if (!flag1 || collection.size() == 1 && collection.contains(eam) || collection.size() == 0 && eam.closed)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (!flag2)
            {
                throw new IllegalStateException(String.valueOf("passThrough should imply winningSubstream is drained"));
            }
            if (flag)
            {
                flag2 = flag3;
                if (eam == null)
                {
                    break label0;
                }
            }
            flag2 = true;
        }
        if (!flag2)
        {
            throw new IllegalStateException(String.valueOf("cancelled should imply committed"));
        } else
        {
            return;
        }
    }
}
