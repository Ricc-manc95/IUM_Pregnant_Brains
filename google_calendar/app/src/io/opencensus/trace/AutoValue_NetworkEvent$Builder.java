// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;


// Referenced classes of package io.opencensus.trace:
//            AutoValue_NetworkEvent, NetworkEvent

public final class  extends 
{

    private Long compressedMessageSize;
    private Long messageId;
    private uncompressedMessageSize type;
    private Long uncompressedMessageSize;

    public final NetworkEvent build()
    {
        String s1 = "";
        if (type == null)
        {
            s1 = String.valueOf("").concat(" type");
        }
        String s = s1;
        if (messageId == null)
        {
            s = String.valueOf(s1).concat(" messageId");
        }
        s1 = s;
        if (uncompressedMessageSize == null)
        {
            s1 = String.valueOf(s).concat(" uncompressedMessageSize");
        }
        s = s1;
        if (compressedMessageSize == null)
        {
            s = String.valueOf(s1).concat(" compressedMessageSize");
        }
        if (!s.isEmpty())
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Missing required properties:".concat(s);
            } else
            {
                s = new String("Missing required properties:");
            }
            throw new IllegalStateException(s);
        } else
        {
            return new AutoValue_NetworkEvent(null, type, messageId.longValue(), uncompressedMessageSize.longValue(), compressedMessageSize.longValue());
        }
    }

    public final compressedMessageSize setCompressedMessageSize(long l)
    {
        compressedMessageSize = Long.valueOf(l);
        return this;
    }

    final compressedMessageSize setMessageId(long l)
    {
        messageId = Long.valueOf(l);
        return this;
    }

    final messageId setType(messageId messageid)
    {
        if (messageid == null)
        {
            throw new NullPointerException("Null type");
        } else
        {
            type = messageid;
            return this;
        }
    }

    public final type setUncompressedMessageSize(long l)
    {
        uncompressedMessageSize = Long.valueOf(l);
        return this;
    }

    public ()
    {
    }
}
