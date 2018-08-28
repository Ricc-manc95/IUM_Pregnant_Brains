// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.view.api.CreationMode;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            CreatePhantomUpdater

public final class CreatePhantomUpdater_Factory
    implements Factory
{

    private final Provider creationModeProvider;
    private final Provider hostViewProvider;
    private final Provider phantomObservableProvider;

    public CreatePhantomUpdater_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        creationModeProvider = provider;
        hostViewProvider = provider1;
        phantomObservableProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = creationModeProvider;
        Provider provider1 = hostViewProvider;
        Provider provider2 = phantomObservableProvider;
        return new CreatePhantomUpdater((CreationMode)provider.get(), (TimelineHostView)provider1.get(), (ObservableReference)provider2.get());
    }
}
