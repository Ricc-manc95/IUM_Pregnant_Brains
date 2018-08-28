// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;


// Referenced classes of package io.opencensus.trace:
//            Span, SpanContext, MessageEvent, NetworkEvent, 
//            EndSpanOptions

public final class BlankSpan extends Span
{

    public static final BlankSpan INSTANCE = new BlankSpan();

    private BlankSpan()
    {
        super(SpanContext.INVALID, null);
    }

    public final void addMessageEvent(MessageEvent messageevent)
    {
        if (messageevent == null)
        {
            throw new NullPointerException("messageEvent");
        } else
        {
            return;
        }
    }

    public final void addNetworkEvent(NetworkEvent networkevent)
    {
    }

    public final void end(EndSpanOptions endspanoptions)
    {
        if (endspanoptions == null)
        {
            throw new NullPointerException("options");
        } else
        {
            return;
        }
    }

    public final String toString()
    {
        return "BlankSpan";
    }

}
