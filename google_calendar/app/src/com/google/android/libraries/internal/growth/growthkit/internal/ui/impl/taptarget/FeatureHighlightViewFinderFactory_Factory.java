// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget;

import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget:
//            FeatureHighlightViewFinderFactory

public final class FeatureHighlightViewFinderFactory_Factory
    implements Factory
{

    private final Provider targetElementFinderProvider;

    public FeatureHighlightViewFinderFactory_Factory(Provider provider)
    {
        targetElementFinderProvider = provider;
    }

    public final Object get()
    {
        return new FeatureHighlightViewFinderFactory(targetElementFinderProvider);
    }
}
