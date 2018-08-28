// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity

public final class AllInOneActivityModule_ProvidesWeekBannerClickCallbackFactory
    implements Factory
{

    private final Provider activityProvider;

    public AllInOneActivityModule_ProvidesWeekBannerClickCallbackFactory(Provider provider)
    {
        activityProvider = provider;
    }

    public final Object get()
    {
        Object obj = (AllInOneCalendarActivity)activityProvider.get();
        obj.getClass();
        obj = new AllInOneActivityModule..Lambda._cls4(((AllInOneCalendarActivity) (obj)));
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (Runnable)obj;
        }
    }
}
