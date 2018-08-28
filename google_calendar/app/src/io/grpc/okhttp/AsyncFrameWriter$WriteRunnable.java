// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.Status;
import io.grpc.okhttp.internal.framed.ErrorCode;
import java.io.IOException;

// Referenced classes of package io.grpc.okhttp:
//            AsyncFrameWriter, OkHttpClientTransport

abstract class <init>
    implements Runnable
{

    private final AsyncFrameWriter this$0;

    public abstract void doRun()
        throws IOException;

    public final void run()
    {
        try
        {
            if (frameWriter == null)
            {
                throw new IOException("Unable to perform write due to unavailable frameWriter.");
            }
        }
        catch (RuntimeException runtimeexception)
        {
            OkHttpClientTransport okhttpclienttransport = transport;
            if (runtimeexception == null)
            {
                throw new NullPointerException(String.valueOf("failureCause"));
            } else
            {
                Status status = Status.UNAVAILABLE.withCause(runtimeexception);
                okhttpclienttransport.startGoAway(0, ErrorCode.INTERNAL_ERROR, status);
                return;
            }
        }
        catch (Exception exception)
        {
            OkHttpClientTransport okhttpclienttransport1 = transport;
            if (exception == null)
            {
                throw new NullPointerException(String.valueOf("failureCause"));
            } else
            {
                Status status1 = Status.UNAVAILABLE.withCause(exception);
                okhttpclienttransport1.startGoAway(0, ErrorCode.INTERNAL_ERROR, status1);
                return;
            }
        }
        doRun();
        return;
    }

    private ()
    {
        this$0 = AsyncFrameWriter.this;
        super();
    }

    this._cls0(byte byte0)
    {
        this();
    }
}
