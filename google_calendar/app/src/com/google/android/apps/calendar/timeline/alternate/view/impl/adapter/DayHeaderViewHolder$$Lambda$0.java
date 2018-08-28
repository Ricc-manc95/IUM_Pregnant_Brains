// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.view.View;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            DayHeaderViewHolder

final class arg._cls1
    implements Consumer
{

    private final DayHeaderViewHolder arg$1;

    public final void accept(Object obj)
    {
        obj = arg$1;
        ((DayHeaderViewHolder) (obj)).bind(((DayHeaderViewHolder) (obj)).position);
        ((DayHeaderViewHolder) (obj)).itemView.invalidate();
    }

    i(DayHeaderViewHolder dayheaderviewholder)
    {
        arg$1 = dayheaderviewholder;
    }
}
