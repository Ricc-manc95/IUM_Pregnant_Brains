// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Metadata;
import io.grpc.Status;

// Referenced classes of package io.grpc.internal:
//            ClientStreamListener

abstract class ForwardingClientStreamListener
    implements ClientStreamListener
{

    ForwardingClientStreamListener()
    {
    }

    public void closed(Status status, Metadata metadata)
    {
        _mthdelegate().closed(status, metadata);
    }

    public void closed$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(Status status, int i, Metadata metadata)
    {
        _mthdelegate().closed$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(status, i, metadata);
    }

    protected abstract ClientStreamListener _mthdelegate();

    public final void headersRead(Metadata metadata)
    {
        _mthdelegate().headersRead(metadata);
    }

    public final void messagesAvailable(StreamListener.MessageProducer messageproducer)
    {
        _mthdelegate().messagesAvailable(messageproducer);
    }

    public final void onReady()
    {
        _mthdelegate().onReady();
    }

    public String toString()
    {
        return (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("delegate", _mthdelegate()).toString();
    }
}
