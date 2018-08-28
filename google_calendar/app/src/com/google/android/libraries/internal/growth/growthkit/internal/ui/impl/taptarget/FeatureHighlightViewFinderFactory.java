// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget;

import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.TargetElementFinder;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget:
//            FeatureHighlightViewFinder

public final class FeatureHighlightViewFinderFactory
{

    private final Provider targetElementFinderProvider;

    public FeatureHighlightViewFinderFactory(Provider provider)
    {
        targetElementFinderProvider = (Provider)checkNotNull(provider, 1);
    }

    private static Object checkNotNull(Object obj, int i)
    {
        if (obj == null)
        {
            throw new NullPointerException((new StringBuilder(93)).append("@AutoFactory method argument is null but is not marked @Nullable. Argument index: ").append(i).toString());
        } else
        {
            return obj;
        }
    }

    public final FeatureHighlightViewFinder create(com.google.identity.growth.proto.Promotion.TapTargetUi taptargetui)
    {
        return new FeatureHighlightViewFinder((TargetElementFinder)checkNotNull((TargetElementFinder)targetElementFinderProvider.get(), 1), (com.google.identity.growth.proto.Promotion.TapTargetUi)checkNotNull(taptargetui, 2));
    }
}
