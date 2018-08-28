// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;


// Referenced classes of package io.opencensus.trace:
//            SpanBuilder, BlankSpan, Span

final class  extends SpanBuilder
{

    public final Span startSpan()
    {
        return BlankSpan.INSTANCE;
    }

    (String s)
    {
        if (s == null)
        {
            throw new NullPointerException("name");
        } else
        {
            return;
        }
    }
}
