// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.task.alternate.TaskCacheInvalidator;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AllInOneActivityModule_OptionalTaskCacheInvalidatorFactory
    implements Factory
{

    public static Optional proxyOptionalTaskCacheInvalidator(Context context, Provider provider)
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
        }
        ((FeatureConfig)context).experimental_options();
        if (flag)
        {
            context = (TaskCacheInvalidator)provider.get();
            if (context == null)
            {
                throw new NullPointerException();
            }
            context = new Present(context);
        } else
        {
            context = Absent.INSTANCE;
        }
        if (context == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (Optional)context;
        }
    }

    public final volatile Object get()
    {
        throw new NoSuchMethodError();
    }
}
