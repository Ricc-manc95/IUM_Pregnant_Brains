// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.experiments;


// Referenced classes of package com.google.android.apps.calendar.config.experiments:
//            WeeklyExperiment, Experiment

public final class  extends 
{

    private final int weeks = 13;

    protected final Experiment buildInternal()
    {
        return new WeeklyExperiment(mId, mName, mSalt, mForceActive, weeks);
    }

    protected final void checkConstraints()
    {
        super.onstraints();
        if (weeks <= 0)
        {
            throw new IllegalStateException("Number of weeks for weekly experiment should be positive.");
        } else
        {
            return;
        }
    }

    public (int i, String s, int j, int k)
    {
        super(20, s, 0x26284187);
    }
}
