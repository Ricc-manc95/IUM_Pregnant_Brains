// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ApplicationThreadDeframer

final class val.hasPartialMessage
    implements Runnable
{

    private final ApplicationThreadDeframer this$0;
    private final boolean val$hasPartialMessage;

    public final void run()
    {
        storedListener.ramerClosed(val$hasPartialMessage);
    }

    ()
    {
        this$0 = final_applicationthreaddeframer;
        val$hasPartialMessage = Z.this;
        super();
    }
}
