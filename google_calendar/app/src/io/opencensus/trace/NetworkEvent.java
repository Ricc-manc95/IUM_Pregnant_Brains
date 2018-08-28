// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;

import io.opencensus.common.Timestamp;

// Referenced classes of package io.opencensus.trace:
//            BaseMessageEvent

public abstract class NetworkEvent extends BaseMessageEvent
{

    NetworkEvent()
    {
    }

    public abstract long getCompressedMessageSize();

    public abstract Timestamp getKernelTimestamp();

    public abstract long getMessageId();

    public abstract Type getType();

    public abstract long getUncompressedMessageSize();
}
