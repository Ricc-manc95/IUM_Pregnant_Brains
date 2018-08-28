// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Application;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CalendarApplicationPropertiesModule_ProvidesAlternateTimelineEnabledFactory
    implements Factory
{

    private final Provider applicationProvider;

    public CalendarApplicationPropertiesModule_ProvidesAlternateTimelineEnabledFactory(Provider provider)
    {
        applicationProvider = provider;
    }

    public final Object get()
    {
        Object obj = (Application)applicationProvider.get();
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).alternate_timeline();
        boolean flag = RemoteFeatureConfig.ALTERNATE_TIMELINE.enabled();
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)obj).experimental_options();
            return Boolean.valueOf(flag);
        }
    }
}
