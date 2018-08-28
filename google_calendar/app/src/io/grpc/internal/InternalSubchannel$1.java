// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            InUseStateAggregator, InternalSubchannel

final class <init> extends InUseStateAggregator
{

    private final InternalSubchannel this$0;

    final void handleInUse()
    {
        callback.onInUse(InternalSubchannel.this);
    }

    final void handleNotInUse()
    {
        callback.onNotInUse(InternalSubchannel.this);
    }

    llback()
    {
        this$0 = InternalSubchannel.this;
        super();
    }
}
