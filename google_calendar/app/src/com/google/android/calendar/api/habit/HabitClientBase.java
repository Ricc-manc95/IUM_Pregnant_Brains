// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.content.Context;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitClient, HabitApiStoreImpl

abstract class HabitClientBase
    implements HabitClient
{

    public final HabitApiStoreImpl api;

    HabitClientBase(HabitApiStoreImpl habitapistoreimpl)
    {
        if (habitapistoreimpl == null)
        {
            throw new NullPointerException();
        } else
        {
            api = (HabitApiStoreImpl)habitapistoreimpl;
            return;
        }
    }

    public final void initialize(Context context)
    {
    }
}
