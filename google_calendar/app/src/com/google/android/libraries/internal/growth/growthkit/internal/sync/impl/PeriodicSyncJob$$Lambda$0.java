// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesNotAvailableException;
import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesRepairableException;
import com.google.android.libraries.gcoreclient.security.GcoreProviderInstaller;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl:
//            PeriodicSyncJob

final class arg._cls1
    implements Runnable
{

    private final PeriodicSyncJob arg$1;

    public final void run()
    {
        PeriodicSyncJob periodicsyncjob = arg$1;
        periodicsyncjob.gcoreProviderInstaller.installIfNeeded(periodicsyncjob.appContext);
        return;
        Object obj;
        obj;
_L2:
        PeriodicSyncJob.logger.e(((Throwable) (obj)), "Failed to install security provider, GrowthKit sync can't run.", new Object[0]);
        return;
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    i(PeriodicSyncJob periodicsyncjob)
    {
        arg$1 = periodicsyncjob;
    }
}
