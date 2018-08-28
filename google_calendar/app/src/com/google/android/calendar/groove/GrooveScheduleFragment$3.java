// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.view.View;
import com.google.android.calendar.utils.AccessibilityUtils;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveScheduleFragment

final class val.position
    implements Runnable
{

    private final GrooveScheduleFragment this$0;
    private final int val$position;
    private final View val$view;

    public final void run()
    {
        View view1 = val$view;
        GrooveScheduleFragment grooveschedulefragment = GrooveScheduleFragment.this;
        view1 = view1.findViewById(GrooveScheduleFragment.getFocusViewId(val$position));
        if (view1 != null)
        {
            AccessibilityUtils.requestAccessibilityFocus(view1);
        }
    }

    A()
    {
        this$0 = final_grooveschedulefragment;
        val$view = view1;
        val$position = I.this;
        super();
    }
}
