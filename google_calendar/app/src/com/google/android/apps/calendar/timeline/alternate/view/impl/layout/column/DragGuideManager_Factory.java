// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            DragGuideManager

public final class DragGuideManager_Factory
    implements Factory
{

    public static final DragGuideManager_Factory INSTANCE = new DragGuideManager_Factory();

    public DragGuideManager_Factory()
    {
    }

    public final Object get()
    {
        return new DragGuideManager();
    }

}
