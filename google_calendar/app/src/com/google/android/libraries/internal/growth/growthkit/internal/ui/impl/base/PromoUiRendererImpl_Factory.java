// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base;

import dagger.internal.Factory;
import java.util.Map;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base:
//            PromoUiRendererImpl

public final class PromoUiRendererImpl_Factory
    implements Factory
{

    private final Provider fragmentRendererMapProvider;

    public PromoUiRendererImpl_Factory(Provider provider)
    {
        fragmentRendererMapProvider = provider;
    }

    public final Object get()
    {
        return new PromoUiRendererImpl((Map)fragmentRendererMapProvider.get());
    }
}
