// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            TestingToolsBroadcastReceiver

final class arg._cls1
    implements Callable
{

    private final TestingToolsBroadcastReceiver arg$1;

    public final Object call()
    {
        com.google.android.libraries.internal.growth.growthkit.internal.common.Logger logger = arg$1.logger;
        Object aobj[] = new Object[0];
        if (aobj != null && aobj.length > 0)
        {
            String.format("Cleared all counters", aobj);
        }
        return null;
    }

    (TestingToolsBroadcastReceiver testingtoolsbroadcastreceiver)
    {
        arg$1 = testingtoolsbroadcastreceiver;
    }
}
