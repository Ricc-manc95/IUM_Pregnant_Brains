// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.v2a;

import android.content.Context;

// Referenced classes of package com.google.android.calendar.v2a:
//            UnifiedSyncUtils

final class arg._cls1
    implements Runnable
{

    private final Context arg$1;

    public final void run()
    {
        UnifiedSyncUtils.lambda$cleanupUnifiedSyncFuture$11$UnifiedSyncUtils(arg$1);
    }

    (Context context)
    {
        arg$1 = context;
    }
}