// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.accounts.impl;

import android.content.Context;
import com.google.android.libraries.gcoreclient.auth.GcoreGoogleAuthUtil;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.accounts.impl:
//            AccountManagerImpl

public final class AccountManagerImpl_Factory
    implements Factory
{

    private final Provider authUtilProvider;
    private final Provider blockingExecutorProvider;
    private final Provider contextProvider;
    private final Provider syncGaiaAccountsProvider;

    public AccountManagerImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3)
    {
        contextProvider = provider;
        blockingExecutorProvider = provider1;
        authUtilProvider = provider2;
        syncGaiaAccountsProvider = provider3;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = blockingExecutorProvider;
        Provider provider2 = authUtilProvider;
        Provider provider3 = syncGaiaAccountsProvider;
        return new AccountManagerImpl((Context)provider.get(), (ListeningExecutorService)provider1.get(), (GcoreGoogleAuthUtil)provider2.get(), provider3);
    }
}
