// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;

import com.google.android.libraries.internal.growth.growthkit.inject.FragmentInjector;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class TooltipModule_ProvideTooltipFragmentInjectorFactory
    implements Factory
{

    private final Provider builderProvider;

    public TooltipModule_ProvideTooltipFragmentInjectorFactory(Provider provider)
    {
        builderProvider = provider;
    }

    public final Object get()
    {
        TooltipFragment.TooltipFragmentSubcomponent tooltipfragmentsubcomponent = ((TooltipFragment.TooltipFragmentSubcomponent.Builder)builderProvider.get()).build();
        if (tooltipfragmentsubcomponent == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (FragmentInjector)tooltipfragmentsubcomponent;
        }
    }
}
