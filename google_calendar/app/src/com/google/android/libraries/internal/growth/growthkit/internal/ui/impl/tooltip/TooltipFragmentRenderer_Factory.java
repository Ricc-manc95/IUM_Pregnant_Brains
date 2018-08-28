// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;

import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip:
//            TooltipFragmentRenderer, TooltipViewFinder

public final class TooltipFragmentRenderer_Factory
    implements Factory
{

    private final Provider tooltipViewFinderProvider;

    public TooltipFragmentRenderer_Factory(Provider provider)
    {
        tooltipViewFinderProvider = provider;
    }

    public final Object get()
    {
        return new TooltipFragmentRenderer((TooltipViewFinder)tooltipViewFinderProvider.get());
    }
}
