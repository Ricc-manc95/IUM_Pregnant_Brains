// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.calendar.timely.TaskBundleFragment;
import dagger.android.AndroidInjector;

final class this._cls1 extends this._cls1
{

    private TaskBundleFragment seedInstance;
    private final seedInstance this$1;

    public final AndroidInjector build()
    {
        if (seedInstance == null)
        {
            throw new IllegalStateException(String.valueOf(com/google/android/calendar/timely/TaskBundleFragment.getCanonicalName()).concat(" must be set"));
        } else
        {
            return new it>(this._cls1.this);
        }
    }

    public final void seedInstance(Object obj)
    {
        obj = (TaskBundleFragment)obj;
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            seedInstance = (TaskBundleFragment)obj;
            return;
        }
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
