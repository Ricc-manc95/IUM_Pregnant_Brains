// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.support.v7.widget.RecyclerView;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            TimelineApiImpl

final class arg._cls1
    implements Consumer
{

    private final TimelineApiImpl arg$1;

    public final void accept(Object obj)
    {
        TimelineApiImpl timelineapiimpl = arg$1;
        if (((Boolean)obj).booleanValue())
        {
            timelineapiimpl.recyclerView.requestLayout();
        }
    }

    (TimelineApiImpl timelineapiimpl)
    {
        arg$1 = timelineapiimpl;
    }
}
