// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.v2a;

import com.google.calendar.v2a.shared.android.AndroidSharedApi;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.v2a:
//            UnifiedSyncUtils

final class arg._cls1
    implements Function
{

    private final long arg$1;

    public final Object apply(Object obj)
    {
        return UnifiedSyncUtils.lambda$getSyncLogs$14$UnifiedSyncUtils(arg$1, (AndroidSharedApi)obj);
    }

    (long l)
    {
        arg$1 = l;
    }
}
