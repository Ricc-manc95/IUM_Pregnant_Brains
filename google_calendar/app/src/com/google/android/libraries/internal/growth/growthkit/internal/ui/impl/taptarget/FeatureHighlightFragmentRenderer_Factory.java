// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget;

import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget:
//            FeatureHighlightFragmentRenderer, FeatureHighlightViewFinderFactory

public final class FeatureHighlightFragmentRenderer_Factory
    implements Factory
{

    private final Provider featureHighlightViewFinderFactoryProvider;

    public FeatureHighlightFragmentRenderer_Factory(Provider provider)
    {
        featureHighlightViewFinderFactoryProvider = provider;
    }

    public final Object get()
    {
        return new FeatureHighlightFragmentRenderer((FeatureHighlightViewFinderFactory)featureHighlightViewFinderFactoryProvider.get());
    }
}
