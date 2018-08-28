// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.inject;

import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.CreationItemToEventAdapter;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.concurrent.SubscriptionManager;
import dagger.internal.Factory;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.inject:
//            TimelineProvidesModule

public final class TimelineProvidesModule_ProvidesCreateEventPhantomItemFactory
    implements Factory
{

    private final Provider adapterPhantomObservableProvider;
    private final Provider itemToEventAdapterProvider;
    private final TimelineProvidesModule module;

    public TimelineProvidesModule_ProvidesCreateEventPhantomItemFactory(TimelineProvidesModule timelineprovidesmodule, Provider provider, Provider provider1)
    {
        module = timelineprovidesmodule;
        adapterPhantomObservableProvider = provider;
        itemToEventAdapterProvider = provider1;
    }

    public final Object get()
    {
        Object obj = module;
        obj = adapterPhantomObservableProvider;
        Object obj1 = itemToEventAdapterProvider;
        obj = (ObservableReference)((Provider) (obj)).get();
        obj1 = (CreationItemToEventAdapter)((Provider) (obj1)).get();
        Object obj2 = TimelineProvidesModule..Lambda._cls0.$instance;
        Object obj3 = new TimelineProvidesModule..Lambda._cls1(((CreationItemToEventAdapter) (obj1)));
        obj1 = com.google.common.base.Equivalence.Equals.INSTANCE;
        obj2 = new com.google.android.apps.calendar.util.Lenses._cls1(((com.google.common.base.Function) (obj2)), ((com.google.android.apps.calendar.util.function.BiFunction) (obj3)));
        obj3 = new SubscriptionManager();
        com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls2 _lcls2 = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls2(((com.google.android.apps.calendar.util.Lens) (obj2)), ((ObservableReference) (obj)), ((SubscriptionManager) (obj3)));
        ((ObservableReference) (obj)).subscribe(new com.google.android.apps.calendar.util.concurrent.ObservableReferences..Lambda._cls0(((com.google.android.apps.calendar.util.Lens) (obj2)), ((com.google.common.base.Equivalence) (obj1)), new AtomicReference(), ((SubscriptionManager) (obj3))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE, false);
        if (_lcls2 == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ObservableReference)_lcls2;
        }
    }
}
