// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.inject;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.inject:
//            TimelineProvidesModule

public final class TimelineProvidesModule_ProvidesGridMsPerVerticalPxFactory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider dimensConverterProvider;
    private final TimelineProvidesModule module;

    public TimelineProvidesModule_ProvidesGridMsPerVerticalPxFactory(TimelineProvidesModule timelineprovidesmodule, Provider provider, Provider provider1)
    {
        module = timelineprovidesmodule;
        contextProvider = provider;
        dimensConverterProvider = provider1;
    }

    public final Object get()
    {
        Object obj = module;
        obj = contextProvider;
        Object obj1 = dimensConverterProvider;
        obj = (Context)((Provider) (obj)).get();
        obj1 = (DimensConverter)((Provider) (obj1)).get();
        int i = (int)((float)TimeUnit.HOURS.toMillis(1L) / ((DimensConverter) (obj1)).dpToPx(48F));
        obj1 = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Integer.valueOf(((Context) (obj)).getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("GridMsPerVerticalPx", i)));
        ((ObservableReference) (obj1)).subscribe(new TimelineProvidesModule..Lambda._cls3(((Context) (obj))), CalendarExecutor.BACKGROUND, false);
        if (obj1 == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ObservableReference)obj1;
        }
    }
}
