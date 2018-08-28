// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.calendar:
//            DateTimeFormatHelper

public final class AllInOneActivityModule_ProvidesDateTimeFormatHelperFactory
    implements Factory
{

    public static final AllInOneActivityModule_ProvidesDateTimeFormatHelperFactory INSTANCE = new AllInOneActivityModule_ProvidesDateTimeFormatHelperFactory();

    public AllInOneActivityModule_ProvidesDateTimeFormatHelperFactory()
    {
    }

    public final Object get()
    {
        DateTimeFormatHelper datetimeformathelper = DateTimeFormatHelper.instance;
        if (datetimeformathelper == null)
        {
            throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
        }
        datetimeformathelper = (DateTimeFormatHelper)datetimeformathelper;
        if (datetimeformathelper == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (DateTimeFormatHelper)datetimeformathelper;
        }
    }

}
