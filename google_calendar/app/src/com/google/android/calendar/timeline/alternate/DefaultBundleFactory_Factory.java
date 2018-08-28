// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            DefaultBundleFactory

public final class DefaultBundleFactory_Factory
    implements Factory
{

    private final Provider timeUtilsProvider;

    public DefaultBundleFactory_Factory(Provider provider)
    {
        timeUtilsProvider = provider;
    }

    public final Object get()
    {
        return new DefaultBundleFactory((TimeUtils)timeUtilsProvider.get());
    }
}
