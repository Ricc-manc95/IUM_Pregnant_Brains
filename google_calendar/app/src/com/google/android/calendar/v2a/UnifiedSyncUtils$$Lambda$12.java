// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.v2a;

import com.google.calendar.v2a.shared.android.AndroidSharedApi;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.v2a:
//            UnifiedSyncUtils

final class arg._cls1
    implements Callable
{

    private final AndroidSharedApi arg$1;

    public final Object call()
    {
        return UnifiedSyncUtils.lambda$getDatabaseDump$12$UnifiedSyncUtils(arg$1);
    }

    A(AndroidSharedApi androidsharedapi)
    {
        arg$1 = androidsharedapi;
    }
}
