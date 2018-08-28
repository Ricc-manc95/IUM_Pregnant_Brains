// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.streamz;

import com.google.protobuf.AbstractMessageLite;

// Referenced classes of package com.google.android.libraries.streamz:
//            MetricFactory

public class StreamzMessageProducer
{

    public final com.google.clearcut.streamz.StreamzProto.IncrementRequest incrementRequest;

    StreamzMessageProducer(MetricFactory metricfactory)
    {
        incrementRequest = metricfactory.generateIncrementRequestProto();
    }

    public final byte[] toProtoBytes()
    {
        return incrementRequest.toByteArray();
    }
}
