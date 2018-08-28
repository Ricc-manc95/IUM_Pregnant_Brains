// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.commonsync.analytics.factory;

import android.os.Build;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.common.util.isemulator.IsEmulator;

public final class AnalyticsLoggerFactory
{

    public static final boolean BUGFOOD_OR_EMULATOR_OR_TEST;
    private static final boolean IS_ROBOELECTRIC;

    static 
    {
        IS_ROBOELECTRIC = "robolectric".equals(Build.FINGERPRINT);
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).bugfood_build();
        boolean flag;
        if (IsEmulator.isProbablyEmulator() || IS_ROBOELECTRIC)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        BUGFOOD_OR_EMULATOR_OR_TEST = flag;
    }
}
