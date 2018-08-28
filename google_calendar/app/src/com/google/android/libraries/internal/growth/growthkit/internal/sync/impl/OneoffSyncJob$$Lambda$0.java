// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import android.content.SharedPreferences;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl:
//            OneoffSyncJob

final class arg._cls1
    implements AsyncFunction
{

    private final OneoffSyncJob arg$1;

    public final ListenableFuture apply(Object obj)
    {
        return arg$1.lambda$executeJob$0$OneoffSyncJob((SharedPreferences)obj);
    }

    (OneoffSyncJob oneoffsyncjob)
    {
        arg$1 = oneoffsyncjob;
    }
}
