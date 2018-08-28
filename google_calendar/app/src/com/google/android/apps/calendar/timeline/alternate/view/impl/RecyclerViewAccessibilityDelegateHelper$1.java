// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            RecyclerViewAccessibilityDelegateHelper

final class this._cls0 extends AccessibilityNodeProviderCompat
{

    private final RecyclerViewAccessibilityDelegateHelper this$0;

    public final AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i)
    {
        return new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(obtainAccessibilityNodeInfo(i).mInfo));
    }

    public final boolean performAction(int i, int j, Bundle bundle)
    {
        RecyclerViewAccessibilityDelegateHelper recyclerviewaccessibilitydelegatehelper = RecyclerViewAccessibilityDelegateHelper.this;
        i;
        JVM INSTR tableswitch -1 -1: default 24
    //                   -1 41;
           goto _L1 _L2
_L1:
        if (!recyclerviewaccessibilitydelegatehelper._flddelegate._mth514KIJ31DPI74RR9CGNMUSPF89QMSP3CCKTIIMG_0(i, j)) goto _L4; else goto _L3
_L3:
        return true;
_L2:
        if (!recyclerviewaccessibilitydelegatehelper._flddelegate._mth514KOOBECHP6UQB45TNN6BQ2ELN68R357CKLK___0(j) && !ViewCompat.performAccessibilityAction(recyclerviewaccessibilitydelegatehelper.hostView, j, bundle))
        {
            return false;
        }
        if (true) goto _L3; else goto _L4
_L4:
        switch (j)
        {
        default:
            return ViewCompat.performAccessibilityAction(recyclerviewaccessibilitydelegatehelper.hostView, j, bundle);

        case 64: // '@'
            return recyclerviewaccessibilitydelegatehelper.requestAccessibilityFocus(i);

        case 128: 
            return recyclerviewaccessibilitydelegatehelper.clearAccessibilityFocus(i);
        }
    }

    legate()
    {
        this$0 = RecyclerViewAccessibilityDelegateHelper.this;
        super();
    }
}
