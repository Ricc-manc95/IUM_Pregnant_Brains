// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.noinject;

import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.noinject:
//            DaggerGrowthKitApplicationComponent

final class this._cls0
    implements Provider
{

    private final DaggerGrowthKitApplicationComponent this$0;

    public final Object get()
    {
        return new owthKitJobServiceSubcomponentBuilder(DaggerGrowthKitApplicationComponent.this);
    }

    owthKitJobServiceSubcomponentBuilder()
    {
        this$0 = DaggerGrowthKitApplicationComponent.this;
        super();
    }
}
