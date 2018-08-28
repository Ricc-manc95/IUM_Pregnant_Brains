// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.inject;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Absent;
import dagger.internal.Factory;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.inject:
//            TimelineProvidesModule

public final class TimelineProvidesModule_ProvidesCreateEventPhantomAdapterEventFactory
    implements Factory
{

    private final TimelineProvidesModule module;

    public TimelineProvidesModule_ProvidesCreateEventPhantomAdapterEventFactory(TimelineProvidesModule timelineprovidesmodule)
    {
        module = timelineprovidesmodule;
    }

    public final Object get()
    {
        Object obj = module;
        obj = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Absent.INSTANCE);
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ObservableReference)obj;
        }
    }
}
