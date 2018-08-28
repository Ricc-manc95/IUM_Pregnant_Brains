// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.stub;

import io.grpc.ClientCall;
import io.grpc.Metadata;

final class this._cls0 extends io.grpc.Call
{

    private final this._cls0 this$0;

    public final void start(io.grpc.Call call, Metadata metadata)
    {
        metadata.merge(_fld0);
        super.Call(call, metadata);
    }

    (ClientCall clientcall)
    {
        this$0 = this._cls0.this;
        super(clientcall);
    }
}
