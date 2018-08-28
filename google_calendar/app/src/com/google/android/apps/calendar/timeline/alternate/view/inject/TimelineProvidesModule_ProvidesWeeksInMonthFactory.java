// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.inject;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.inject:
//            TimelineProvidesModule

public final class TimelineProvidesModule_ProvidesWeeksInMonthFactory
    implements Factory
{

    private final TimelineProvidesModule module;

    public TimelineProvidesModule_ProvidesWeeksInMonthFactory(TimelineProvidesModule timelineprovidesmodule)
    {
        module = timelineprovidesmodule;
    }

    public final Object get()
    {
        TimelineProvidesModule timelineprovidesmodule = module;
        return Integer.valueOf(6);
    }
}
