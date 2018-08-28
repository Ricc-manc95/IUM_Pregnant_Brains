// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.noinject;

import android.app.Service;
import android.content.Context;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Trace;
import com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl.GrowthKitJobService;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.android.libraries.streamz.Counter3;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.noinject:
//            DaggerGrowthKitApplicationComponent

final class this._cls0
    implements com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl.
{

    private final DaggerGrowthKitApplicationComponent this$0;

    public final void inject(Service service)
    {
        service = (GrowthKitJobService)service;
        service.enableFlag = provideGeneralEnableFlagProvider;
        service.jobs = DoubleCheck.lazy(mapOfStringAndProviderOfGrowthKitJobOfProvider);
        Object obj = new GooglePlayDriver((Context)provideContextProvider.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        }
        obj = new FirebaseJobDispatcher((Driver)obj);
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            service.firebaseJobDispatcher = (FirebaseJobDispatcher)obj;
            service.trace = (Trace)bindsNoOpTraceProvider.get();
            service.jobCounter = (Counter3)provideJobCounterProvider.get();
            service.streamzIncrements = (StreamzIncrements)provideStreamzIncrementsProvider.get();
            service.packageName = (String)provideApplicationPackageNameProvider.get();
            return;
        }
    }

    ()
    {
        this$0 = DaggerGrowthKitApplicationComponent.this;
        super();
    }
}
