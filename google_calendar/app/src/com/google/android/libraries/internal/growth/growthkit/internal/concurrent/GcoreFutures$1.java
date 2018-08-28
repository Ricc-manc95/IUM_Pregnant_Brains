// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;
import com.google.android.libraries.gcoreclient.common.api.GcoreReleasable;
import com.google.android.libraries.gcoreclient.common.api.GcoreResult;
import java.io.Closeable;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.concurrent:
//            AsyncCloseableFunction, AsyncCloseable

final class val.client
    implements AsyncCloseableFunction
{

    public final GcoreGoogleApiClient val$client;
    private final boolean val$keepOpen;

    private final AsyncCloseable apply(GcoreResult gcoreresult)
        throws Exception
    {
        if (val$keepOpen || (gcoreresult instanceof GcoreReleasable))
        {
            class _cls1
                implements Closeable
            {

                private final GcoreFutures._cls1 this$0;

                public final void close()
                    throws IOException
                {
                    client.disconnect();
                }

            _cls1()
            {
                this$0 = GcoreFutures._cls1.this;
                super();
            }
            }

            if (gcoreresult == null)
            {
                gcoreresult = com.google.common.util.concurrent..ImmediateSuccessfulFuture.NULL;
            } else
            {
                gcoreresult = new com.google.common.util.concurrent..ImmediateSuccessfulFuture(gcoreresult);
            }
            return AsyncCloseable.fromFutureWithCloseables(gcoreresult, new Closeable[] {
                new _cls1()
            });
        }
        val$client.disconnect();
        if (gcoreresult == null)
        {
            gcoreresult = com.google.common.util.concurrent..ImmediateSuccessfulFuture.NULL;
        } else
        {
            gcoreresult = new com.google.common.util.concurrent..ImmediateSuccessfulFuture(gcoreresult);
        }
        return AsyncCloseable.fromFuture(gcoreresult);
        Exception exception;
        exception;
        if (gcoreresult == null)
        {
            gcoreresult = com.google.common.util.concurrent..ImmediateSuccessfulFuture.NULL;
        } else
        {
            gcoreresult = new com.google.common.util.concurrent..ImmediateSuccessfulFuture(gcoreresult);
        }
        return AsyncCloseable.fromFuture(gcoreresult);
    }

    public final volatile AsyncCloseable apply(Object obj)
        throws Exception
    {
        return apply((GcoreResult)obj);
    }

    _cls1()
    {
        val$keepOpen = flag;
        val$client = gcoregoogleapiclient;
        super();
    }
}
