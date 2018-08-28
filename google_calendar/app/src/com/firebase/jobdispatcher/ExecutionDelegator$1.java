// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.util.Log;

// Referenced classes of package com.firebase.jobdispatcher:
//            GooglePlayReceiver, JobCoder, ExecutionDelegator

final class it> extends it>
{

    private final ExecutionDelegator this$0;

    public final void jobFinished(Bundle bundle, int i)
    {
        bundle = GooglePlayReceiver.prefixedCoder.decode(bundle);
        if (bundle == null)
        {
            Log.wtf("FJD.ExternalReceiver", "jobFinished: unknown invocation provided");
            return;
        } else
        {
            onJobFinishedMessage(bundle.build(), i);
            return;
        }
    }

    ()
    {
        this$0 = ExecutionDelegator.this;
        super();
    }
}
