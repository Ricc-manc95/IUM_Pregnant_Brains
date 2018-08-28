// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs;

import android.content.Context;
import com.google.android.libraries.internal.growth.growthkit.internal.media.ImageCache;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs:
//            MaterialDialogImageDownloadManager

public final class MaterialDialogImageDownloadManager_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider imageCacheProvider;

    public MaterialDialogImageDownloadManager_Factory(Provider provider, Provider provider1)
    {
        contextProvider = provider;
        imageCacheProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = imageCacheProvider;
        return new MaterialDialogImageDownloadManager((Context)provider.get(), (ImageCache)provider1.get());
    }
}
