// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package io.grpc.internal:
//            RetriableStream, ClientStream

final class val.winningSubstream
    implements Runnable
{

    private final RetriableStream this$0;
    private final Collection val$savedDrainedSubstreams;
    private final CAUSE_COMMITTED val$winningSubstream;

    public final void run()
    {
        Iterator iterator = val$savedDrainedSubstreams.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            val.winningSubstream winningsubstream = (val.savedDrainedSubstreams)iterator.next();
            if (winningsubstream != val$winningSubstream)
            {
                winningsubstream.ream.cancel(RetriableStream.CANCELLED_BECAUSE_COMMITTED);
            }
        } while (true);
        postCommit();
    }

    ()
    {
        this$0 = final_retriablestream;
        val$savedDrainedSubstreams = collection;
        val$winningSubstream = val.winningSubstream.this;
        super();
    }
}
