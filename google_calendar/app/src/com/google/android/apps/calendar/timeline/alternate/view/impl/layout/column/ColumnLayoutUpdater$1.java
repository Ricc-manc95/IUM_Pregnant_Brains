// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent;
import com.google.common.base.Absent;
import com.google.common.base.Optional;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnLayoutUpdater, ColumnViewportController

final class val.event
    implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.ew.ActionHandler
{

    private final ColumnLayoutUpdater this$0;
    private final AdapterEvent val$event;

    public final boolean focus()
    {
        return false;
    }

    public final Optional scroll(boolean flag, Integer integer)
    {
        return Absent.INSTANCE;
    }

    public final boolean showOnScreen()
    {
        return viewportController.animateShowRange(val$event.getJulianDay(), val$event.getDisplayStartFp16(), val$event.getDisplayEndFp16()).isPresent();
    }

    er()
    {
        this$0 = final_columnlayoutupdater;
        val$event = AdapterEvent.this;
        super();
    }
}
