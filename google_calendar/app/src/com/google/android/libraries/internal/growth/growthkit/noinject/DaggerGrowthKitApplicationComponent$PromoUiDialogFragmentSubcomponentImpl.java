// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.noinject;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.google.android.libraries.internal.growth.growthkit.internal.clearcut.ClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.media.ImageCache;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.UserActionUtil;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs.PromoUiDialogFragment;
import com.google.common.base.Absent;
import com.google.common.collect.ImmutableMap;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.noinject:
//            DaggerGrowthKitApplicationComponent

final class this._cls0
    implements com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs.ject
{

    private final DaggerGrowthKitApplicationComponent this$0;

    public final void inject(Fragment fragment)
    {
        fragment = (PromoUiDialogFragment)fragment;
        DaggerGrowthKitApplicationComponent daggergrowthkitapplicationcomponent = DaggerGrowthKitApplicationComponent.this;
        fragment.dialogBuilderMap = ImmutableMap.of(com.google.identity.growth.proto.gBuilderMap, daggergrowthkitapplicationcomponent.provideRatingMaterialDialogBuilderProvider, com.google.identity.growth.proto.RatingMaterialDialogBuilderProvider, daggergrowthkitapplicationcomponent.provideRatingDefaultDialogBuilderProvider, com.google.identity.growth.proto.RatingDefaultDialogBuilderProvider, daggergrowthkitapplicationcomponent.provideRatingBottomSheetDialogBuilderProvider);
        fragment.imageCache = (ImageCache)provideImageCacheProvider.get();
        daggergrowthkitapplicationcomponent = DaggerGrowthKitApplicationComponent.this;
        fragment.userActionUtil = new UserActionUtil((Context)daggergrowthkitapplicationcomponent.provideContextProvider.get(), (PerAccountProvider)daggergrowthkitapplicationcomponent.providePresentedPromosStoreProvider.get(), (ClearcutLogger)daggergrowthkitapplicationcomponent.provideClearcutLoggerProvider.get(), Absent.INSTANCE);
    }

    ()
    {
        this$0 = DaggerGrowthKitApplicationComponent.this;
        super();
    }
}
