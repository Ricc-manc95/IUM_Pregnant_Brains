// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import java.util.regex.Pattern;

public final class PrimesDirStatsConfigurations
{

    public static final PrimesDirStatsConfigurations DEFAULT = new PrimesDirStatsConfigurations(false);
    public final boolean enabled;
    private final Pattern listFilesPatterns[];
    public final int maxFolderDepth;

    private PrimesDirStatsConfigurations(boolean flag)
    {
        this(false, 2, new Pattern[0]);
    }

    private transient PrimesDirStatsConfigurations(boolean flag, int i, Pattern apattern[])
    {
        enabled = flag;
        maxFolderDepth = 2;
        listFilesPatterns = apattern;
    }

    public final Pattern[] getListFilesPatterns()
    {
        return (Pattern[])listFilesPatterns.clone();
    }

}
