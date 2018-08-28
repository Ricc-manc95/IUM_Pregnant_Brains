// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.timeline.alternate.view.api.CreationMode;
import dagger.internal.Factory;

public final class AllInOneActivityModule_ProvidesCreationModeFactory
    implements Factory
{

    public static final AllInOneActivityModule_ProvidesCreationModeFactory INSTANCE = new AllInOneActivityModule_ProvidesCreationModeFactory();

    public AllInOneActivityModule_ProvidesCreationModeFactory()
    {
    }

    public final Object get()
    {
        CreationMode creationmode = CreationMode.OLD;
        if (creationmode == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (CreationMode)creationmode;
        }
    }

}
