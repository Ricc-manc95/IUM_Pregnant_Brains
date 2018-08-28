// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.accessibility.AccessibilityEvent;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineTaskBundle, TimelineItemUtil, TaskBundleFragment

final class ragment extends com.google.android.calendar.common.view.overlay.ayDialog
{

    private final TaskBundleFragment this$0;

    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        accessibilityevent.setContentDescription(TimelineItemUtil.createContentDescription(getContext(), (TimelineTaskBundle)getArguments().getParcelable("task_bundle"), false, ((TimelineTaskBundle)getArguments().getParcelable("task_bundle")).title));
        return super.dispatchPopulateAccessibilityEvent(accessibilityevent);
    }

    ragment(Context context)
    {
        this$0 = TaskBundleFragment.this;
        super(TaskBundleFragment.this, context);
    }
}
