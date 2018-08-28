// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import dagger.android.AndroidInjector;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity, DaggerCalendarApplicationComponent

final class InOneCalendarActivitySubcomponent.Builder extends InOneCalendarActivitySubcomponent.Builder
{

    public com.google.android.apps.calendar.timeline.alternate.view.timebox.lder providesModule;
    public AllInOneCalendarActivity seedInstance;
    private final DaggerCalendarApplicationComponent this$0;

    public final AndroidInjector build()
    {
        if (providesModule == null)
        {
            providesModule = new com.google.android.apps.calendar.timeline.alternate.view.timebox.lder();
        }
        if (seedInstance == null)
        {
            throw new IllegalStateException(String.valueOf(com/google/android/calendar/AllInOneCalendarActivity.getCanonicalName()).concat(" must be set"));
        } else
        {
            return new it>(DaggerCalendarApplicationComponent.this, this);
        }
    }

    public final void seedInstance(Object obj)
    {
        obj = (AllInOneCalendarActivity)obj;
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            seedInstance = (AllInOneCalendarActivity)obj;
            return;
        }
    }

    InOneCalendarActivitySubcomponent.Builder()
    {
        this$0 = DaggerCalendarApplicationComponent.this;
        super();
    }
}
