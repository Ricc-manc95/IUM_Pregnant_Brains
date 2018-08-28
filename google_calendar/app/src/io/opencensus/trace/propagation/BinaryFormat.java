// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace.propagation;

import io.opencensus.trace.SpanContext;
import java.text.ParseException;

// Referenced classes of package io.opencensus.trace.propagation:
//            SpanContextParseException

public class BinaryFormat
{

    public static final NoopBinaryFormat NOOP_BINARY_FORMAT = new NoopBinaryFormat();

    public BinaryFormat()
    {
    }

    private final SpanContext fromBinaryValue(byte abyte0[])
        throws ParseException
    {
        try
        {
            abyte0 = fromByteArray(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new ParseException(abyte0.toString(), 0);
        }
        return abyte0;
    }

    public SpanContext fromByteArray(byte abyte0[])
        throws SpanContextParseException
    {
        try
        {
            abyte0 = fromBinaryValue(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new SpanContextParseException("Error while parsing.", abyte0);
        }
        return abyte0;
    }

    public byte[] toByteArray(SpanContext spancontext)
    {
        return toByteArray(spancontext);
    }


    private class NoopBinaryFormat extends BinaryFormat
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

        NoopBinaryFormat()
        {
        }
    }

}
