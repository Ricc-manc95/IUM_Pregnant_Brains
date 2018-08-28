// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.os.Bundle;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar:
//            NewViewScreenFactory

final class arg._cls2
    implements AsyncFunction
{

    private final EventInfoAnimationData arg$1;
    private final Bundle arg$2;

    public final ListenableFuture apply(Object obj)
    {
        EventInfoAnimationData eventinfoanimationdata = arg$1;
        Bundle bundle = arg$2;
        return NewViewScreenFactory.onTimelineItem((TimelineEvent)obj, eventinfoanimationdata, bundle);
    }

    ationData(EventInfoAnimationData eventinfoanimationdata, Bundle bundle)
    {
        arg$1 = eventinfoanimationdata;
        arg$2 = bundle;
    }
}
