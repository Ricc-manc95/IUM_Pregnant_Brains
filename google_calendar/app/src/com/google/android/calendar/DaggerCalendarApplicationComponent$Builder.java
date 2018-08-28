// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Application;

// Referenced classes of package com.google.android.calendar:
//            DaggerCalendarApplicationComponent, CalendarApplicationComponent

final class 
    implements 
{

    public Application application;

    public final  application(Application application1)
    {
        if (application1 == null)
        {
            throw new NullPointerException();
        } else
        {
            application = (Application)application1;
            return this;
        }
    }

    public final CalendarApplicationComponent build()
    {
        if (application == null)
        {
            throw new IllegalStateException(String.valueOf(android/app/Application.getCanonicalName()).concat(" must be set"));
        } else
        {
            return new DaggerCalendarApplicationComponent(this);
        }
    }

    ()
    {
    }
}
