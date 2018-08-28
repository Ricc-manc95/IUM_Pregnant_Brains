// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.experiments;


// Referenced classes of package com.google.android.apps.calendar.config.experiments:
//            PercentageExperiment, Experiment

public final class mScale extends mScale
{

    private final int mLowerBoundary;
    private final int mScale;
    private final int mUpperBoundary;

    protected final Experiment buildInternal()
    {
        return new PercentageExperiment(mId, mName, mSalt, mLowerBoundary, mUpperBoundary, mForceActive, mScale);
    }

    protected final void checkConstraints()
    {
        super.raints();
        if (mLowerBoundary < 0 || mUpperBoundary > mScale)
        {
            int i = mScale;
            throw new IllegalStateException((new StringBuilder(48)).append("Experiment interval must be in [0, ").append(i).append(").").toString());
        }
        if (mUpperBoundary <= mLowerBoundary)
        {
            throw new IllegalStateException("The upperBoundary must be strictly biggerthan the lowerBoundary.");
        } else
        {
            return;
        }
    }

    public (int i, String s, int j, int k, int l)
    {
        this(i, s, j, 0, l, 100);
    }

    public <init>(int i, String s, int j, int k, int l, int i1)
    {
        super(i, s, j);
        mLowerBoundary = k;
        mUpperBoundary = l;
        mScale = i1;
    }
}
