// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesNotAvailableException;
import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesRepairableException;
import com.google.android.libraries.gcoreclient.security.GcoreProviderInstaller;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            TestingToolsBroadcastReceiver

final class arg._cls1
    implements Runnable
{

    private final TestingToolsBroadcastReceiver arg$1;

    public final void run()
    {
        TestingToolsBroadcastReceiver testingtoolsbroadcastreceiver = arg$1;
        testingtoolsbroadcastreceiver.gcoreProviderInstaller.installIfNeeded(testingtoolsbroadcastreceiver.context);
        return;
        Object obj;
        obj;
_L2:
        testingtoolsbroadcastreceiver.logger.e(((Throwable) (obj)), "Failed to install security provider, GrowthKit sync can't run.", new Object[0]);
        return;
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    (TestingToolsBroadcastReceiver testingtoolsbroadcastreceiver)
    {
        arg$1 = testingtoolsbroadcastreceiver;
    }
}
