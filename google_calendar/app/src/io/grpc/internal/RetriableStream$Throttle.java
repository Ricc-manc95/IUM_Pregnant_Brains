// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

final class maxTokens
{

    public final int maxTokens;
    public final int threshold;
    public final AtomicInteger tokenCount = new AtomicInteger();
    public final int tokenRatio;

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof maxTokens))
            {
                return false;
            }
            obj = (maxTokens)obj;
            if (maxTokens != ((maxTokens) (obj)).maxTokens || tokenRatio != ((tokenRatio) (obj)).tokenRatio)
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(maxTokens), Integer.valueOf(tokenRatio)
        });
    }

    (float f, float f1)
    {
        tokenRatio = (int)(f1 * 1000F);
        maxTokens = (int)(f * 1000F);
        threshold = maxTokens / 2;
        tokenCount.set(maxTokens);
    }
}
