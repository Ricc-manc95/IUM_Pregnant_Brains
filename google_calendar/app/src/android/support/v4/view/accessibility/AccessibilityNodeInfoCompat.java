// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;

public final class AccessibilityNodeInfoCompat
{

    public final AccessibilityNodeInfo mInfo;
    public int mParentVirtualDescendantId;

    public AccessibilityNodeInfoCompat(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        mParentVirtualDescendantId = -1;
        mInfo = accessibilitynodeinfo;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        obj = (AccessibilityNodeInfoCompat)obj;
        if (mInfo != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (((AccessibilityNodeInfoCompat) (obj)).mInfo == null) goto _L1; else goto _L3
_L3:
        return false;
        if (mInfo.equals(((AccessibilityNodeInfoCompat) (obj)).mInfo)) goto _L1; else goto _L4
_L4:
        return false;
    }

    public final int hashCode()
    {
        if (mInfo == null)
        {
            return 0;
        } else
        {
            return mInfo.hashCode();
        }
    }

    public final String toString()
    {
        StringBuilder stringbuilder;
        int i;
        stringbuilder = new StringBuilder();
        stringbuilder.append(super.toString());
        Rect rect = new Rect();
        mInfo.getBoundsInParent(rect);
        stringbuilder.append((new StringBuilder("; boundsInParent: ")).append(rect).toString());
        mInfo.getBoundsInScreen(rect);
        stringbuilder.append((new StringBuilder("; boundsInScreen: ")).append(rect).toString());
        stringbuilder.append("; packageName: ").append(mInfo.getPackageName());
        stringbuilder.append("; className: ").append(mInfo.getClassName());
        stringbuilder.append("; text: ").append(mInfo.getText());
        stringbuilder.append("; contentDescription: ").append(mInfo.getContentDescription());
        stringbuilder.append("; viewId: ").append(mInfo.getViewIdResourceName());
        stringbuilder.append("; checkable: ").append(mInfo.isCheckable());
        stringbuilder.append("; checked: ").append(mInfo.isChecked());
        stringbuilder.append("; focusable: ").append(mInfo.isFocusable());
        stringbuilder.append("; focused: ").append(mInfo.isFocused());
        stringbuilder.append("; selected: ").append(mInfo.isSelected());
        stringbuilder.append("; clickable: ").append(mInfo.isClickable());
        stringbuilder.append("; longClickable: ").append(mInfo.isLongClickable());
        stringbuilder.append("; enabled: ").append(mInfo.isEnabled());
        stringbuilder.append("; password: ").append(mInfo.isPassword());
        stringbuilder.append((new StringBuilder("; scrollable: ")).append(mInfo.isScrollable()).toString());
        stringbuilder.append("; [");
        i = mInfo.getActions();
_L24:
        if (i == 0) goto _L2; else goto _L1
_L1:
        int j;
        j = 1 << Integer.numberOfTrailingZeros(i);
        i = ~j & i;
        j;
        JVM INSTR lookupswitch 18: default 540
    //                   1: 563
    //                   2: 569
    //                   4: 575
    //                   8: 581
    //                   16: 587
    //                   32: 593
    //                   64: 599
    //                   128: 605
    //                   256: 611
    //                   512: 617
    //                   1024: 623
    //                   2048: 629
    //                   4096: 635
    //                   8192: 641
    //                   16384: 653
    //                   32768: 659
    //                   65536: 647
    //                   131072: 665;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21
_L3:
        String s = "ACTION_UNKNOWN";
_L22:
        stringbuilder.append(s);
        if (i != 0)
        {
            stringbuilder.append(", ");
        }
        continue; /* Loop/switch isn't completed */
_L4:
        s = "ACTION_FOCUS";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "ACTION_CLEAR_FOCUS";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "ACTION_SELECT";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "ACTION_CLEAR_SELECTION";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "ACTION_CLICK";
        continue; /* Loop/switch isn't completed */
_L9:
        s = "ACTION_LONG_CLICK";
        continue; /* Loop/switch isn't completed */
_L10:
        s = "ACTION_ACCESSIBILITY_FOCUS";
        continue; /* Loop/switch isn't completed */
_L11:
        s = "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
        continue; /* Loop/switch isn't completed */
_L12:
        s = "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
        continue; /* Loop/switch isn't completed */
_L13:
        s = "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
        continue; /* Loop/switch isn't completed */
_L14:
        s = "ACTION_NEXT_HTML_ELEMENT";
        continue; /* Loop/switch isn't completed */
_L15:
        s = "ACTION_PREVIOUS_HTML_ELEMENT";
        continue; /* Loop/switch isn't completed */
_L16:
        s = "ACTION_SCROLL_FORWARD";
        continue; /* Loop/switch isn't completed */
_L17:
        s = "ACTION_SCROLL_BACKWARD";
        continue; /* Loop/switch isn't completed */
_L20:
        s = "ACTION_CUT";
        continue; /* Loop/switch isn't completed */
_L18:
        s = "ACTION_COPY";
        continue; /* Loop/switch isn't completed */
_L19:
        s = "ACTION_PASTE";
        continue; /* Loop/switch isn't completed */
_L21:
        s = "ACTION_SET_SELECTION";
        if (true) goto _L22; else goto _L2
_L2:
        stringbuilder.append("]");
        return stringbuilder.toString();
        if (true) goto _L24; else goto _L23
_L23:
    }
}
