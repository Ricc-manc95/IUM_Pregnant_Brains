// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AllInOneActivityModule_ProvidesScreenTypeFactory
    implements Factory
{

    private final Provider contextProvider;

    public AllInOneActivityModule_ProvidesScreenTypeFactory(Provider provider)
    {
        contextProvider = provider;
    }

    public final Object get()
    {
        Object obj = (Context)contextProvider.get();
        if (((Context) (obj)).getResources().getBoolean(0x7f0c0016))
        {
            boolean flag;
            if (((Context) (obj)).getResources().getBoolean(0x7f0c0016) && ((Context) (obj)).getResources().getConfiguration().smallestScreenWidthDp >= 720)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                obj = ScreenType.LARGE_TABLET;
            } else
            {
                obj = ScreenType.SMALL_TABLET;
            }
        } else
        {
            obj = ScreenType.PHONE;
        }
        obj = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(obj);
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ObservableReference)obj;
        }
    }
}
