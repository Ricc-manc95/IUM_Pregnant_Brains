// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget;

import com.google.android.libraries.internal.growth.growthkit.inject.FragmentInjector;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class TapTargetModule_ProvideFeatureHighlightFragmentInjectorFactory
    implements Factory
{

    private final Provider builderProvider;

    public TapTargetModule_ProvideFeatureHighlightFragmentInjectorFactory(Provider provider)
    {
        builderProvider = provider;
    }

    public final Object get()
    {
        FeatureHighlightFragment.FeatureHighlightFragmentSubcomponent featurehighlightfragmentsubcomponent = ((FeatureHighlightFragment.FeatureHighlightFragmentSubcomponent.Builder)builderProvider.get()).build();
        if (featurehighlightfragmentsubcomponent == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (FragmentInjector)featurehighlightfragmentsubcomponent;
        }
    }
}
