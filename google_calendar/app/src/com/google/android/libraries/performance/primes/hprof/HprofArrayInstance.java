// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;

import com.google.android.libraries.stitch.util.Preconditions;
import java.nio.ByteBuffer;

// Referenced classes of package com.google.android.libraries.performance.primes.hprof:
//            HprofObject, ParseContext, HprofClass

public final class HprofArrayInstance extends HprofObject
{

    public final HprofClass clazz;

    public HprofArrayInstance(int i, HprofClass hprofclass)
    {
        super(i);
        clazz = hprofclass;
    }

    public final String buildLeakSegment(ParseContext parsecontext, int i)
    {
        int j = getChildCount(parsecontext);
        return (new StringBuilder(31)).append("Object[").append(i).append("/").append(j).append("]").toString();
    }

    public final int computeShallowSize(ParseContext parsecontext)
    {
        return parsecontext.idSize * getChildCount(parsecontext);
    }

    public final int getChildCount(ParseContext parsecontext)
    {
        return parsecontext.buffer.getInt(position + parsecontext.idSize + 4);
    }

    public final String getChildName(ParseContext parsecontext, int i)
    {
        int j = getChildCount(parsecontext);
        if (i < 0 || i >= j)
        {
            if (i < 0)
            {
                parsecontext = Preconditions.format("%s (%s) must not be negative", new Object[] {
                    "index", Integer.valueOf(i)
                });
            } else
            {
                if (j < 0)
                {
                    throw new IllegalArgumentException((new StringBuilder(26)).append("negative size: ").append(j).toString());
                }
                parsecontext = Preconditions.format("%s (%s) must be less than size (%s)", new Object[] {
                    "index", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
            throw new IndexOutOfBoundsException(parsecontext);
        } else
        {
            return (new StringBuilder(13)).append("[").append(i).append("]").toString();
        }
    }

    public final int getChildValue(ParseContext parsecontext, int i)
    {
        int j = getChildCount(parsecontext);
        if (i < 0 || i >= j)
        {
            if (i < 0)
            {
                parsecontext = Preconditions.format("%s (%s) must not be negative", new Object[] {
                    "index", Integer.valueOf(i)
                });
            } else
            {
                if (j < 0)
                {
                    throw new IllegalArgumentException((new StringBuilder(26)).append("negative size: ").append(j).toString());
                }
                parsecontext = Preconditions.format("%s (%s) must be less than size (%s)", new Object[] {
                    "index", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
            throw new IndexOutOfBoundsException(parsecontext);
        } else
        {
            return parsecontext.readId(position + parsecontext.idSize + 4 + 4 + parsecontext.idSize + parsecontext.idSize * i);
        }
    }
}
