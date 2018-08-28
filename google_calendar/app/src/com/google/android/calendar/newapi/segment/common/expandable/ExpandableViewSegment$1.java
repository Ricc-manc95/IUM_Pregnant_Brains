// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.common.expandable;

import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package com.google.android.calendar.newapi.segment.common.expandable:
//            ExpandableViewSegment

final class this._cls0 extends AccessibilityDelegateCompat
{

    private final ExpandableViewSegment this$0;

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        if (expanded)
        {
            view = android.support.v4.view.accessibility.pat.AccessibilityActionCompat.ACTION_COLLAPSE;
        } else
        {
            view = android.support.v4.view.accessibility.pat.AccessibilityActionCompat.ACTION_EXPAND;
        }
        accessibilitynodeinfocompat.mInfo.addAction((android.view.accessibility.cessibilityAction)((android.support.v4.view.accessibility.pat.AccessibilityActionCompat) (view)).mAction);
    }

    Compat()
    {
        this$0 = ExpandableViewSegment.this;
        super();
    }
}
