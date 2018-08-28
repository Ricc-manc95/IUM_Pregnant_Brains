// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.inject;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.inject:
//            GrowthKitInternalProdModule

public final class GrowthKitInternalProdModule_ProvideAppCertificateFingerprintFactory
    implements Factory
{

    private final Provider applicationPackageNameProvider;
    private final Provider contextProvider;

    public GrowthKitInternalProdModule_ProvideAppCertificateFingerprintFactory(Provider provider, Provider provider1)
    {
        contextProvider = provider;
        applicationPackageNameProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = applicationPackageNameProvider;
        return GrowthKitInternalProdModule.provideAppCertificateFingerprint((Context)provider.get(), (String)provider1.get());
    }
}
