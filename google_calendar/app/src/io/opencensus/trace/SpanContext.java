// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;

import java.util.Arrays;

// Referenced classes of package io.opencensus.trace:
//            TraceId, SpanId, TraceOptions

public final class SpanContext
{

    public static final SpanContext INVALID;
    public final SpanId spanId;
    private final TraceId traceId;
    public final TraceOptions traceOptions;

    private SpanContext(TraceId traceid, SpanId spanid, TraceOptions traceoptions)
    {
        traceId = traceid;
        spanId = spanid;
        traceOptions = traceoptions;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof SpanContext))
            {
                return false;
            }
            obj = (SpanContext)obj;
            if (!traceId.equals(((SpanContext) (obj)).traceId) || !spanId.equals(((SpanContext) (obj)).spanId) || !traceOptions.equals(((SpanContext) (obj)).traceOptions))
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            traceId, spanId, traceOptions
        });
    }

    public final String toString()
    {
        String s = String.valueOf(traceId);
        String s1 = String.valueOf(spanId);
        String s2 = String.valueOf(traceOptions);
        return (new StringBuilder(String.valueOf(s).length() + 45 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("SpanContext{traceId=").append(s).append(", spanId=").append(s1).append(", traceOptions=").append(s2).append("}").toString();
    }

    static 
    {
        INVALID = new SpanContext(TraceId.INVALID, SpanId.INVALID, TraceOptions.DEFAULT);
    }
}
