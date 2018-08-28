// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;

import java.nio.ByteBuffer;

// Referenced classes of package com.google.android.libraries.performance.primes.hprof:
//            HprofObject, ParseContext

public final class HprofPrimitiveArrayInstance extends HprofObject
{

    public HprofPrimitiveArrayInstance(int i)
    {
        super(i);
    }

    public final String buildLeakSegment(ParseContext parsecontext, int i)
    {
        return "";
    }

    public final int computeShallowSize(ParseContext parsecontext)
    {
        int i = position;
        int j = parsecontext.idSize;
        i = parsecontext.buffer.get(i + j + 4 + 4);
        j = parsecontext.typeSizes[i];
        boolean flag;
        if (j > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            return getChildCount(parsecontext) * j;
        }
    }

    public final int getChildCount(ParseContext parsecontext)
    {
        int i = position;
        int j = parsecontext.idSize;
        return parsecontext.buffer.getInt(i + j + 4);
    }

    public final String getChildName(ParseContext parsecontext, int i)
    {
        return "";
    }

    public final int getChildValue(ParseContext parsecontext, int i)
    {
        return 0;
    }
}
