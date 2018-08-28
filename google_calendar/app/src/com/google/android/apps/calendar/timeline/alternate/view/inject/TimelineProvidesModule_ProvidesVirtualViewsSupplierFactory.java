// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.inject;

import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutUpdaterImpl;
import com.google.common.base.Supplier;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.inject:
//            TimelineProvidesModule

public final class TimelineProvidesModule_ProvidesVirtualViewsSupplierFactory
    implements Factory
{

    private final Provider layoutUpdaterProvider;
    private final TimelineProvidesModule module;

    public TimelineProvidesModule_ProvidesVirtualViewsSupplierFactory(TimelineProvidesModule timelineprovidesmodule, Provider provider)
    {
        module = timelineprovidesmodule;
        layoutUpdaterProvider = provider;
    }

    public final Object get()
    {
        Object obj = module;
        obj = (LayoutUpdaterImpl)layoutUpdaterProvider.get();
        obj.getClass();
        obj = new TimelineProvidesModule..Lambda._cls2(((LayoutUpdaterImpl) (obj)));
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (Supplier)obj;
        }
    }
}
