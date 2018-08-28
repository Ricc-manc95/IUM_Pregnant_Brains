// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.util;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.util:
//            IdleTracker

public final class IdleTracker_Factory
    implements Factory
{

    public static final IdleTracker_Factory INSTANCE = new IdleTracker_Factory();

    public IdleTracker_Factory()
    {
    }

    public final Object get()
    {
        return new IdleTracker();
    }

}
