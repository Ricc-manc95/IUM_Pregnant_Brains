// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;

import io.opencensus.common.Timestamp;

// Referenced classes of package io.opencensus.trace:
//            NetworkEvent

final class AutoValue_NetworkEvent extends NetworkEvent
{

    private final long compressedMessageSize;
    private final Timestamp kernelTimestamp;
    private final long messageId;
    private final NetworkEvent.Type type;
    private final long uncompressedMessageSize;

    AutoValue_NetworkEvent(Timestamp timestamp, NetworkEvent.Type type1, long l, long l1, long l2)
    {
        kernelTimestamp = timestamp;
        type = type1;
        messageId = l;
        uncompressedMessageSize = l1;
        compressedMessageSize = l2;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof NetworkEvent)
            {
                obj = (NetworkEvent)obj;
                if ((kernelTimestamp != null ? !kernelTimestamp.equals(((NetworkEvent) (obj)).getKernelTimestamp()) : ((NetworkEvent) (obj)).getKernelTimestamp() != null) || (!type.equals(((NetworkEvent) (obj)).getType()) || messageId != ((NetworkEvent) (obj)).getMessageId() || uncompressedMessageSize != ((NetworkEvent) (obj)).getUncompressedMessageSize() || compressedMessageSize != ((NetworkEvent) (obj)).getCompressedMessageSize()))
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

    public final Timestamp getKernelTimestamp()
    {
        return kernelTimestamp;
    }

    public final long getMessageId()
    {
        return messageId;
    }

    public final NetworkEvent.Type getType()
    {
        return type;
    }

    public final long getUncompressedMessageSize()
    {
        return uncompressedMessageSize;
    }

    public final int hashCode()
    {
        int i;
        if (kernelTimestamp == null)
        {
            i = 0;
        } else
        {
            i = kernelTimestamp.hashCode();
        }
        return ((((i ^ 0xf4243) * 0xf4243 ^ type.hashCode()) * 0xf4243 ^ (int)(messageId >>> 32 ^ messageId)) * 0xf4243 ^ (int)(uncompressedMessageSize >>> 32 ^ uncompressedMessageSize)) * 0xf4243 ^ (int)(compressedMessageSize >>> 32 ^ compressedMessageSize);
    }

    public final String toString()
    {
        String s = String.valueOf(kernelTimestamp);
        String s1 = String.valueOf(type);
        long l = messageId;
        long l1 = uncompressedMessageSize;
        long l2 = compressedMessageSize;
        return (new StringBuilder(String.valueOf(s).length() + 159 + String.valueOf(s1).length())).append("NetworkEvent{kernelTimestamp=").append(s).append(", type=").append(s1).append(", messageId=").append(l).append(", uncompressedMessageSize=").append(l1).append(", compressedMessageSize=").append(l2).append("}").toString();
    }
}
