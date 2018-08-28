// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Status;

// Referenced classes of package io.grpc.internal:
//            ConnectionClientTransport

final class this._cls0
    implements this._cls0
{

    private final ansport this$0;

    public final void onFailure$5166KOBMC4NMOOBECSNL8Q3IDTRM2OJCCKTIILG_0()
    {
        ansport.shutdownNow(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone"));
    }

    public final void onSuccess$5152ILG_0()
    {
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
