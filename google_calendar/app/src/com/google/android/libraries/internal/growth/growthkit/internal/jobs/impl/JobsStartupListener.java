// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.jobs.GrowthKitJobScheduler;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitStartupListener;

public final class JobsStartupListener
    implements GrowthKitStartupListener
{

    private final GrowthKitJobScheduler growthKitJobScheduler;

    public JobsStartupListener(GrowthKitJobScheduler growthkitjobscheduler)
    {
        growthKitJobScheduler = growthkitjobscheduler;
    }

    public final void onApplicationStartup$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7CKLC___0()
    {
        growthKitJobScheduler.autoScheduleJobs();
    }
}
