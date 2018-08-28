// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;


// Referenced classes of package io.opencensus.trace:
//            MessageEvent

final class AutoValue_MessageEvent extends MessageEvent
{

    private final long compressedMessageSize;
    private final long messageId;
    private final MessageEvent.Type type;
    private final long uncompressedMessageSize;

    AutoValue_MessageEvent(MessageEvent.Type type1, long l, long l1, long l2)
    {
        type = type1;
        messageId = l;
        uncompressedMessageSize = l1;
        compressedMessageSize = l2;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof MessageEvent)
            {
                if (!type.equals(((MessageEvent) (obj = (MessageEvent)obj)).getType()) || messageId != ((MessageEvent) (obj)).getMessageId() || uncompressedMessageSize != ((MessageEvent) (obj)).getUncompressedMessageSize() || compressedMessageSize != ((MessageEvent) (obj)).getCompressedMessageSize())
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final long getCompressedMessageSize()
    {
        return compressedMessageSize;
    }

    public final long getMessageId()
    {
        return messageId;
    }

    public final MessageEvent.Type getType()
    {
        return type;
    }

    public final long getUncompressedMessageSize()
    {
        return uncompressedMessageSize;
    }

    public final int hashCode()
    {
        return (((type.hashCode() ^ 0xf4243) * 0xf4243 ^ (int)(messageId >>> 32 ^ messageId)) * 0xf4243 ^ (int)(uncompressedMessageSize >>> 32 ^ uncompressedMessageSize)) * 0xf4243 ^ (int)(compressedMessageSize >>> 32 ^ compressedMessageSize);
    }

    public final String toString()
    {
        String s = String.valueOf(type);
        long l = messageId;
        long l1 = uncompressedMessageSize;
        long l2 = compressedMessageSize;
        return (new StringBuilder(String.valueOf(s).length() + 141)).append("MessageEvent{type=").append(s).append(", messageId=").append(l).append(", uncompressedMessageSize=").append(l1).append(", compressedMessageSize=").append(l2).append("}").toString();
    }
}
