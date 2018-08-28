// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.support.v4.view.AccessibilityDelegateCompat;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            TimelineAccessibilityDelegate, RecyclerViewAccessibilityDelegateHelper

final class arg._cls1
    implements Consumer
{

    private final TimelineAccessibilityDelegate arg$1;

    public final void accept(Object obj)
    {
        TimelineAccessibilityDelegate timelineaccessibilitydelegate = arg$1;
        if (((Boolean)obj).booleanValue())
        {
            obj = timelineaccessibilitydelegate.helper.hostView;
            AccessibilityDelegateCompat.DEFAULT_DELEGATE.lityEvent(((android.view.View) (obj)), 2048);
        }
    }

    (TimelineAccessibilityDelegate timelineaccessibilitydelegate)
    {
        arg$1 = timelineaccessibilitydelegate;
    }
}
