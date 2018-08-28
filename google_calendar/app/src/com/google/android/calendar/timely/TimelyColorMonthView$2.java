// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;
import com.google.android.calendar.utils.a11y.A11yHelper;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyColorMonthView

final class this._cls0
    implements Runnable
{

    private final TimelyColorMonthView this$0;

    public final void run()
    {
        A11yHelper.getInstance();
        TimelyColorMonthView timelycolormonthview = TimelyColorMonthView.this;
        if (A11yHelper.isAccessibilityEnabled(timelycolormonthview.getContext()))
        {
            AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(2048);
            accessibilityevent.setContentChangeTypes(1);
            timelycolormonthview.sendAccessibilityEventUnchecked(accessibilityevent);
        }
        getAccessibilityNodeProvider().performAction(mSelectedDay, 64, null);
    }

    ()
    {
        this$0 = TimelyColorMonthView.this;
        super();
    }
}
