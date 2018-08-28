// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags;

import io.opencensus.tags.propagation.TagContextBinarySerializer;

// Referenced classes of package io.opencensus.tags:
//            TagContext

final class  extends TagContextBinarySerializer
{

    private static final byte EMPTY_BYTE_ARRAY[] = new byte[0];
    public static final TagContextBinarySerializer INSTANCE = new <init>();

    public final TagContext fromByteArray(byte abyte0[])
    {
        if (abyte0 == null)
        {
            throw new NullPointerException("bytes");
        } else
        {
            return ;
        }
    }

    public final byte[] toByteArray(TagContext tagcontext)
    {
        if (tagcontext == null)
        {
            throw new NullPointerException("tags");
        } else
        {
            return EMPTY_BYTE_ARRAY;
        }
    }


    private ()
    {
    }
}
