// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar.timely:
//            MonthBackgrounds

public final class MonthBackgrounds_Factory
    implements Factory
{

    private final Provider contextProvider;

    public MonthBackgrounds_Factory(Provider provider)
    {
        contextProvider = provider;
    }

    public final Object get()
    {
        return new MonthBackgrounds((Context)contextProvider.get());
    }
}
