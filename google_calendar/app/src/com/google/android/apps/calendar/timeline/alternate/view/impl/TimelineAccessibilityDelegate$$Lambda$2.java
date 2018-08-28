// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            TimelineAccessibilityDelegate

final class arg._cls1
    implements Function
{

    private final TimelineAccessibilityDelegate arg$1;

    public final Object apply(Object obj)
    {
        TimelineAccessibilityDelegate timelineaccessibilitydelegate = arg$1;
        obj = (Integer)obj;
        if (((Integer) (obj)).intValue() != -1)
        {
            timelineaccessibilitydelegate.requestFocusOnNextUpdate = ((Integer) (obj)).intValue();
        }
        return null;
    }

    (TimelineAccessibilityDelegate timelineaccessibilitydelegate)
    {
        arg$1 = timelineaccessibilitydelegate;
    }
}
