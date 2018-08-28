// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.common.base.Receiver;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            TestingToolsBroadcastReceiver

final class arg._cls1
    implements Receiver
{

    private final TestingToolsBroadcastReceiver arg$1;

    public final void accept(Object obj)
    {
        TestingToolsBroadcastReceiver testingtoolsbroadcastreceiver = arg$1;
        obj = (Throwable)obj;
        testingtoolsbroadcastreceiver.logger.e(((Throwable) (obj)), "Failed to clear event counts in BroadcastReceiver", new Object[0]);
    }

    (TestingToolsBroadcastReceiver testingtoolsbroadcastreceiver)
    {
        arg$1 = testingtoolsbroadcastreceiver;
    }
}
