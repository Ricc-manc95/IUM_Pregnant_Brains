// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.timebox;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.timebox:
//            TimeBoxItemAdapter

public final class TimeBoxItemAdapter_Factory
    implements Factory
{

    public static final TimeBoxItemAdapter_Factory INSTANCE = new TimeBoxItemAdapter_Factory();

    public TimeBoxItemAdapter_Factory()
    {
    }

    public final Object get()
    {
        return new TimeBoxItemAdapter();
    }

}
