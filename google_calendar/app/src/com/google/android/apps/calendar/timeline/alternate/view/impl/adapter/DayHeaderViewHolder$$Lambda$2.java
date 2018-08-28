// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            DayHeaderViewHolder

final class arg._cls2
    implements Consumer
{

    private final DayHeaderViewHolder arg$1;
    private final StringBuilder arg$2;

    public final void accept(Object obj)
    {
        DayHeaderViewHolder dayheaderviewholder = arg$1;
        StringBuilder stringbuilder = arg$2;
        obj = (ViewMode)obj;
        if (obj == ViewMode.SCHEDULE)
        {
            stringbuilder.append(", ").append(dayheaderviewholder.context.getResources().getString(0x7f130071));
        } else
        if (obj == ViewMode.ONE_DAY_GRID)
        {
            stringbuilder.append(", ").append(dayheaderviewholder.context.getResources().getString(0x7f130073));
            return;
        }
    }

    i(DayHeaderViewHolder dayheaderviewholder, StringBuilder stringbuilder)
    {
        arg$1 = dayheaderviewholder;
        arg$2 = stringbuilder;
    }
}
