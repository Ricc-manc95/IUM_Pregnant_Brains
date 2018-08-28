// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.feature;


// Referenced classes of package com.google.android.apps.calendar.config.feature:
//            FeatureConfig

public final class Features
{

    public static FeatureConfig instance;

    public static FeatureConfig get()
    {
        FeatureConfig featureconfig = instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            return (FeatureConfig)featureconfig;
        }
    }
}
