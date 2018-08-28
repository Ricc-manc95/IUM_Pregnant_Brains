// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.provider.sync.syncer;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.calendar.v2a.android.provider.sync.syncer:
//            ProviderSyncJobService

final class arg._cls1
    implements AsyncCallable
{

    private final ProviderSyncJobService arg$1;

    public final ListenableFuture call()
    {
        ProviderSyncJobService.requestSync(arg$1);
        if (true)
        {
            return com.google.common.util.concurrent.ulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.ulFuture(null);
        }
    }

    (ProviderSyncJobService providersyncjobservice)
    {
        arg$1 = providersyncjobservice;
    }
}
