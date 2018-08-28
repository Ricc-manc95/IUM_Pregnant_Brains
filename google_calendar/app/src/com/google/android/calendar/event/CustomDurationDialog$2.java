// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package com.google.android.calendar.event:
//            CustomDurationDialog

final class this._cls0 extends android.view.ate
{

    private final CustomDurationDialog this$0;

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfo);
        accessibilitynodeinfo.setText(requireContext().getResources().getQuantityString(0x7f120005, minutes, new Object[] {
            Integer.valueOf(minutes)
        }));
    }

    ()
    {
        this$0 = CustomDurationDialog.this;
        super();
    }
}
