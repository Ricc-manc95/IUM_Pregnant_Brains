// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base:
//            TargetElementFinder

public final class TargetElementFinder_Factory
    implements Factory
{

    private final Provider optVisualElementViewFinderProvider;

    public TargetElementFinder_Factory(Provider provider)
    {
        optVisualElementViewFinderProvider = provider;
    }

    public final Object get()
    {
        return new TargetElementFinder((Optional)optVisualElementViewFinderProvider.get());
    }
}
