// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            ChipHeights

public final class ChipHeights_Factory
    implements Factory
{

    private final Provider contextProvider;

    public ChipHeights_Factory(Provider provider)
    {
        contextProvider = provider;
    }

    public final Object get()
    {
        return new ChipHeights((Context)contextProvider.get());
    }
}
