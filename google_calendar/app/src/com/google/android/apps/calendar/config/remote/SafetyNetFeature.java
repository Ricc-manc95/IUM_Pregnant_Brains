// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.remote;

import com.google.android.gms.phenotype.PhenotypeFlag;

// Referenced classes of package com.google.android.apps.calendar.config.remote:
//            RemoteFeature

public final class SafetyNetFeature extends RemoteFeature
{

    public Integer numberOfDeletions;
    public final PhenotypeFlag numberOfDeletionsFlag = buildFlag("number_of_deletions", 10);
    public Integer percentageOfDeletions;
    public final PhenotypeFlag percentageOfDeletionsFlag = buildFlag("percentage_of_deletions", 95);

    public SafetyNetFeature()
    {
        super("SafetyNetV3", "SFNT", false);
    }
}
