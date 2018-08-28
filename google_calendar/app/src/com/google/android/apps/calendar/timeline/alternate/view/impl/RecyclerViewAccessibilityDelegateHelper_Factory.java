// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.support.v7.widget.RecyclerView;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            RecyclerViewAccessibilityDelegateHelper

public final class RecyclerViewAccessibilityDelegateHelper_Factory
    implements Factory
{

    private final Provider delegateProvider;
    private final Provider hostViewProvider;

    public RecyclerViewAccessibilityDelegateHelper_Factory(Provider provider, Provider provider1)
    {
        hostViewProvider = provider;
        delegateProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = hostViewProvider;
        Provider provider1 = delegateProvider;
        return new RecyclerViewAccessibilityDelegateHelper((RecyclerView)provider.get(), (RecyclerViewAccessibilityDelegateHelper.Delegate)provider1.get());
    }
}
