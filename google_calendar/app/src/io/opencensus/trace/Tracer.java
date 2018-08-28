// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;


// Referenced classes of package io.opencensus.trace:
//            Span, SpanBuilder

public abstract class Tracer
{

    public static final NoopTracer noopTracer = new NoopTracer();

    protected Tracer()
    {
    }

    public abstract SpanBuilder spanBuilderWithExplicitParent(String s, Span span);


    private class NoopTracer extends Tracer
    {

        public final SpanBuilder spanBuilderWithExplicitParent(String s, Span span)
        {
            return new SpanBuilder.NoopSpanBuilder(s);
        }

        NoopTracer()
        {
        }
    }

}
