// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.streamz;

import com.google.android.libraries.streamz.Counter3;
import com.google.android.libraries.streamz.Field;
import com.google.android.libraries.streamz.MetricFactory;
import dagger.internal.Factory;

public final class StreamzCommonModule_ProvideLoggingCounterFactory
    implements Factory
{

    public static final StreamzCommonModule_ProvideLoggingCounterFactory INSTANCE = new StreamzCommonModule_ProvideLoggingCounterFactory();

    public StreamzCommonModule_ProvideLoggingCounterFactory()
    {
    }

    public final Object get()
    {
        Object obj = MetricFactory.defaultFactory;
        obj = (Counter3)((MetricFactory) (obj)).getOrCreate(new Counter3("/client_streamz/android_growthkit/logging_count", ((com.google.android.libraries.streamz.MetricConfigProvider) (obj)), new Field("package_name", java/lang/String), new Field("which_log", java/lang/String), new Field("status", java/lang/String)));
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (Counter3)obj;
        }
    }

}
