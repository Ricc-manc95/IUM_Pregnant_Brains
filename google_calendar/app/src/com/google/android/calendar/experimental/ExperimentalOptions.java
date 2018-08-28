// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.experimental;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;

public final class ExperimentalOptions
{

    public static boolean isAlternateTimelineEnabled(Context context)
    {
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)context).alternate_timeline();
        boolean flag = RemoteFeatureConfig.ALTERNATE_TIMELINE.enabled();
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)context).experimental_options();
            return flag;
        }
    }

    public static boolean isBroadcastingEnabled(Context context)
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).broadcasting();
        return context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("PREF_BROADCASTING", true);
    }

    public static boolean isFlingingEnabled$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7CKLK___0()
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).flinging();
            return false;
        }
    }

    public static boolean isProposeNewTimeEnabled(Context context)
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).propose_new_time();
        featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).fishfood_build();
        return RemoteFeatureConfig.PROPOSE_NEW_TIME.enabled() && context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("PREF_PROPOSE_NEW_TIME", true);
    }

    public static boolean isRbEnabledForAllUsers(Context context)
    {
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)context).experimental_options();
            return false;
        }
    }

    public static boolean isTaskAssistStagingEnabled(Context context)
    {
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)context).experimental_options();
            return false;
        }
    }

    public static boolean shouldThorFetchGstatic(Context context)
    {
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)context).experimental_options();
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)context).conference_pstn_assets_only();
            return true;
        }
    }
}
