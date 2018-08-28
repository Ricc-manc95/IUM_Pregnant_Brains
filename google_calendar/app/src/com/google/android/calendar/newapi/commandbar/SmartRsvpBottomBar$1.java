// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package com.google.android.calendar.newapi.commandbar:
//            SmartRsvpBottomBar

final class this._cls0 extends AccessibilityDelegateCompat
{

    private final SmartRsvpBottomBar this$0;

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        if (currentMode.isExpanded)
        {
            view = android.support.v4.view.accessibility.Compat.AccessibilityActionCompat.ACTION_COLLAPSE;
        } else
        {
            view = android.support.v4.view.accessibility.Compat.AccessibilityActionCompat.ACTION_EXPAND;
        }
        accessibilitynodeinfocompat.mInfo.addAction((android.view.accessibility..AccessibilityAction)((android.support.v4.view.accessibility.Compat.AccessibilityActionCompat) (view)).mAction);
    }

    n()
    {
        this$0 = SmartRsvpBottomBar.this;
        super();
    }
}
