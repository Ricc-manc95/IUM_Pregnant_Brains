// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.analytics.impl;

import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.social.analytics.impl:
//            AnalyticsLoggerImpl

public final class AnalyticsLoggerProvider
    implements Provider
{

    private final Provider eventHandlersProvider;

    public AnalyticsLoggerProvider(Provider provider)
    {
        eventHandlersProvider = provider;
    }

    public final Object get()
    {
        return new AnalyticsLoggerImpl(eventHandlersProvider);
    }
}
