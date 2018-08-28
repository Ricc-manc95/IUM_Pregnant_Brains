// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import java.util.Random;

final class AdMobInfo
{

    public static final AdMobInfo INSTANCE = new AdMobInfo();
    public int mAdHitId;
    public Random mRandom;

    private AdMobInfo()
    {
        mRandom = new Random();
    }

}
