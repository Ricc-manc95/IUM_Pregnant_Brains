// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.calendar.common.view.overlay.OverlayFragment;

// Referenced classes of package com.google.android.calendar.timely:
//            DayPopUpFragment

final class yFragment extends com.google.android.calendar.common.view.overlay.rlayDialog
{

    private final DayPopUpFragment this$0;

    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        Object obj = getOverlayView().findViewById(0x7f10012a);
        if (obj != null)
        {
            Object obj1 = DayPopUpFragment.this;
            String s = requireContext().getResources().getString(0x7f130155);
            obj1 = ((Fragment) (obj1)).requireContext().getResources().getString(0x7f130449, new Object[] {
                s
            });
            obj = String.valueOf(((View) (obj)).getContentDescription());
            accessibilityevent.setContentDescription((new StringBuilder(String.valueOf(obj1).length() + 2 + String.valueOf(obj).length())).append(((String) (obj1))).append(", ").append(((String) (obj))).toString());
        }
        return super.dispatchPopulateAccessibilityEvent(accessibilityevent);
    }

    yFragment(Context context)
    {
        this$0 = DayPopUpFragment.this;
        super(DayPopUpFragment.this, context);
    }
}
