// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AllDayClickGuardHolder

public final class AllDayClickGuardHolder_Factory
    implements Factory
{

    private final Provider contextProvider;

    public AllDayClickGuardHolder_Factory(Provider provider)
    {
        contextProvider = provider;
    }

    public final Object get()
    {
        return new AllDayClickGuardHolder((Context)contextProvider.get());
    }
}
