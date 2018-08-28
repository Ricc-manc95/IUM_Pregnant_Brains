// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base;

import android.content.Context;
import com.google.android.libraries.internal.growth.growthkit.internal.clearcut.ClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base:
//            UserActionUtil

public final class UserActionUtil_Factory
    implements Factory
{

    private final Provider clearcutLoggerProvider;
    private final Provider contextProvider;
    private final Provider intentBuilderProvider;
    private final Provider presentedPromosStoreProvider;

    public UserActionUtil_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3)
    {
        contextProvider = provider;
        presentedPromosStoreProvider = provider1;
        clearcutLoggerProvider = provider2;
        intentBuilderProvider = provider3;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = presentedPromosStoreProvider;
        Provider provider2 = clearcutLoggerProvider;
        Provider provider3 = intentBuilderProvider;
        return new UserActionUtil((Context)provider.get(), (PerAccountProvider)provider1.get(), (ClearcutLogger)provider2.get(), (Optional)provider3.get());
    }
}
