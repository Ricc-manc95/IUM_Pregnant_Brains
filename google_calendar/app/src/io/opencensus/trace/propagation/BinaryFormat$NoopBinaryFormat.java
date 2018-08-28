// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace.propagation;

import io.opencensus.trace.SpanContext;

// Referenced classes of package io.opencensus.trace.propagation:
//            BinaryFormat

final class  extends BinaryFormat
{

    public final SpanContext fromByteArray(byte abyte0[])
    {
        if (abyte0 == null)
        {
            throw new NullPointerException("bytes");
        } else
        {
            return SpanContext.INVALID;
        }
    }

    public final byte[] toByteArray(SpanContext spancontext)
    {
        if (spancontext == null)
        {
            throw new NullPointerException("spanContext");
        } else
        {
            return new byte[0];
        }
    }

    ()
    {
    }
}
