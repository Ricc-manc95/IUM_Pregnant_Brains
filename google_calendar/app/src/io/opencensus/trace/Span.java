// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

// Referenced classes of package io.opencensus.trace:
//            SpanContext, TraceOptions, NetworkEvent, MessageEvent, 
//            EndSpanOptions

public abstract class Span
{
    public static final class Options extends Enum
    {

        private static final Options $VALUES[];
        public static final Options RECORD_EVENTS;

        public static Options[] values()
        {
            return (Options[])$VALUES.clone();
        }

        static 
        {
            RECORD_EVENTS = new Options("RECORD_EVENTS", 0);
            $VALUES = (new Options[] {
                RECORD_EVENTS
            });
        }

        private Options(String s, int i)
        {
            super(s, 0);
        }
    }


    private static final Set DEFAULT_OPTIONS = Collections.unmodifiableSet(EnumSet.noneOf(io/opencensus/trace/Span$Options));
    public final SpanContext context;
    private final Set options;

    protected Span(SpanContext spancontext, EnumSet enumset)
    {
        if (spancontext == null)
        {
            throw new NullPointerException("context");
        }
        context = (SpanContext)spancontext;
        options = DEFAULT_OPTIONS;
        boolean flag;
        if ((spancontext.traceOptions.options & 1) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || options.contains(Options.RECORD_EVENTS))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException("Span is sampled, but does not have RECORD_EVENTS set.");
        } else
        {
            return;
        }
    }

    public void addMessageEvent(MessageEvent messageevent)
    {
        if (messageevent == null)
        {
            throw new NullPointerException("messageEvent");
        }
        if (messageevent == null)
        {
            throw new NullPointerException("event");
        }
        if (messageevent instanceof NetworkEvent)
        {
            messageevent = (NetworkEvent)messageevent;
        } else
        {
            MessageEvent messageevent1 = (MessageEvent)messageevent;
            AutoValue_NetworkEvent.Builder builder;
            long l;
            if (messageevent1.getType() == MessageEvent.Type.RECEIVED)
            {
                messageevent = NetworkEvent.Type.RECV;
            } else
            {
                messageevent = NetworkEvent.Type.SENT;
            }
            l = messageevent1.getMessageId();
            builder = new AutoValue_NetworkEvent.Builder();
            if (messageevent == null)
            {
                throw new NullPointerException("type");
            }
            messageevent = builder.setType((NetworkEvent.Type)messageevent).setMessageId(l).setUncompressedMessageSize(0L).setCompressedMessageSize(0L).setUncompressedMessageSize(messageevent1.getUncompressedMessageSize()).setCompressedMessageSize(messageevent1.getCompressedMessageSize()).build();
        }
        addNetworkEvent(messageevent);
    }

    public void addNetworkEvent(NetworkEvent networkevent)
    {
        if (networkevent == null)
        {
            throw new NullPointerException("event");
        }
        if (networkevent instanceof MessageEvent)
        {
            networkevent = (MessageEvent)networkevent;
        } else
        {
            NetworkEvent networkevent1 = (NetworkEvent)networkevent;
            if (networkevent1.getType() == NetworkEvent.Type.RECV)
            {
                networkevent = MessageEvent.Type.RECEIVED;
            } else
            {
                networkevent = MessageEvent.Type.SENT;
            }
            networkevent = MessageEvent.builder(networkevent, networkevent1.getMessageId()).setUncompressedMessageSize(networkevent1.getUncompressedMessageSize()).setCompressedMessageSize(networkevent1.getCompressedMessageSize()).build();
        }
        addMessageEvent(networkevent);
    }

    public abstract void end(EndSpanOptions endspanoptions);

    static 
    {
        Collections.emptyMap();
    }
}
