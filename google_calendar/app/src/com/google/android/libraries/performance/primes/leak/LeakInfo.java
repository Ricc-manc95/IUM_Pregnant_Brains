// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.leak;

import java.util.Objects;

public final class LeakInfo
{

    public final String path;
    public final int retainedHeapSizeBytes;

    public LeakInfo(String s, int i)
    {
        path = s;
        retainedHeapSizeBytes = i;
    }

    public final boolean equals(Object obj)
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (obj instanceof LeakInfo)
        {
            obj = (LeakInfo)obj;
            flag = flag1;
            if (path.equals(((LeakInfo) (obj)).path))
            {
                flag = flag1;
                if (retainedHeapSizeBytes == ((LeakInfo) (obj)).retainedHeapSizeBytes)
                {
                    flag = true;
                }
            }
        }
        return flag;
    }

    public final int hashCode()
    {
        return Objects.hash(new Object[] {
            path, Integer.valueOf(retainedHeapSizeBytes)
        });
    }

    public final String toString()
    {
        return String.format("{path: %s, retainedHeapSizeBytes: %d}", new Object[] {
            path, Integer.valueOf(retainedHeapSizeBytes)
        });
    }
}
