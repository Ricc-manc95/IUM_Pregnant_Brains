// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.experiments;

import android.content.Context;

// Referenced classes of package com.google.android.apps.calendar.config.experiments:
//            Experiment

public final class PercentageExperiment extends Experiment
{

    private final int lowerBoundary;
    private final int scale;
    private final int upperBoundary;

    PercentageExperiment(int i, String s, int j, int k, int l, Boolean boolean1, int i1)
    {
        super(i, s, j, boolean1);
        lowerBoundary = k;
        upperBoundary = l;
        scale = i1;
    }

    protected final boolean isActiveInternal(Context context)
    {
        int i = getBucket(context, salt, scale);
        return i >= lowerBoundary && i < upperBoundary;
    }

    public final String toString()
    {
        String s = name;
        return (new StringBuilder(String.valueOf(s).length() + 27)).append("PercentageExperiment{name=").append(s).append("}").toString();
    }
}
