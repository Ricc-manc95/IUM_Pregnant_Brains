// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.experiments.PhenotypeManager;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitStartupListener;

final class ExperimentsStartupListener
    implements GrowthKitStartupListener
{

    private final PhenotypeManager phenotypeManager;

    public ExperimentsStartupListener(PhenotypeManager phenotypemanager)
    {
        phenotypeManager = phenotypemanager;
    }

    public final void onApplicationStartup$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7CKLC___0()
    {
        phenotypeManager.register();
    }
}
