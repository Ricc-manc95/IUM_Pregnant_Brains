// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.phenotype.impl;

import com.google.android.gms.phenotype.Configurations;
import com.google.android.libraries.gcoreclient.phenotype.GcoreConfigurations;
import java.util.HashMap;

public class BaseGcoreConfigurationsImpl
    implements GcoreConfigurations
{

    public final Configurations configurations;

    BaseGcoreConfigurationsImpl(Configurations configurations1)
    {
        configurations = configurations1;
        new HashMap();
    }

    public final String getServerToken()
    {
        return configurations.serverToken;
    }

    public final String getSnapshotToken()
    {
        return configurations.snapshotToken;
    }
}
