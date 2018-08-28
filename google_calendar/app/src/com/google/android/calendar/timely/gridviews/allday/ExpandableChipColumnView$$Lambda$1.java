// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.allday;

import android.graphics.Point;
import com.google.android.calendar.analytics.dnd.DndAnalytics;
import com.google.android.calendar.timeline.chip.Chip;

// Referenced classes of package com.google.android.calendar.timely.gridviews.allday:
//            ExpandableChipColumnView

final class arg._cls1
    implements com.google.android.calendar.timeline.chip..Lambda._cls1
{

    private final ExpandableChipColumnView arg$1;

    public final boolean onChipLongPress(Chip chip, Point point)
    {
        DndAnalytics.logDndFailedStartWrongView(arg$1.getContext(), "long_press_timeline_chip_grid_allday");
        return false;
    }

    (ExpandableChipColumnView expandablechipcolumnview)
    {
        arg$1 = expandablechipcolumnview;
    }
}
