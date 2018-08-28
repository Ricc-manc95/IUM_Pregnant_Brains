// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            DelayedStream, ClientStream

final class val.authority
    implements Runnable
{

    private final DelayedStream this$0;
    private final String val$authority;

    public final void run()
    {
        realStream.setAuthority(val$authority);
    }

    ()
    {
        this$0 = final_delayedstream;
        val$authority = String.this;
        super();
    }
}
