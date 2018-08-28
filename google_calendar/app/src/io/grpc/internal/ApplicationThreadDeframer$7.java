// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ApplicationThreadDeframer

final class val.cause
    implements Runnable
{

    private final ApplicationThreadDeframer this$0;
    private final Throwable val$cause;

    public final void run()
    {
        storedListener.rameFailed(val$cause);
    }

    ()
    {
        this$0 = final_applicationthreaddeframer;
        val$cause = Throwable.this;
        super();
    }
}
