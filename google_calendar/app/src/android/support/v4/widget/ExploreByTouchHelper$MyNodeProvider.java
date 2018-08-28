// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.support.v4.widget:
//            ExploreByTouchHelper

final class rCompat extends AccessibilityNodeProviderCompat
{

    private final ExploreByTouchHelper this$0;

    public final AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i)
    {
        return new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(obtainAccessibilityNodeInfo(i).mInfo));
    }

    public final AccessibilityNodeInfoCompat findFocus(int i)
    {
        if (i == 2)
        {
            i = mAccessibilityFocusedVirtualViewId;
        } else
        {
            i = mKeyboardFocusedVirtualViewId;
        }
        if (i == 0x80000000)
        {
            return null;
        } else
        {
            return createAccessibilityNodeInfo(i);
        }
    }

    public final boolean performAction(int i, int j, Bundle bundle)
    {
        ExploreByTouchHelper explorebytouchhelper = ExploreByTouchHelper.this;
        switch (i)
        {
        default:
            switch (j)
            {
            default:
                return explorebytouchhelper.onPerformActionForVirtualView(i, j, bundle);

            case 64: // '@'
                if (!explorebytouchhelper.mManager.isEnabled() || !explorebytouchhelper.mManager.isTouchExplorationEnabled())
                {
                    return false;
                }
                if (explorebytouchhelper.mAccessibilityFocusedVirtualViewId != i)
                {
                    if (explorebytouchhelper.mAccessibilityFocusedVirtualViewId != 0x80000000)
                    {
                        explorebytouchhelper.clearAccessibilityFocus(explorebytouchhelper.mAccessibilityFocusedVirtualViewId);
                    }
                    explorebytouchhelper.mAccessibilityFocusedVirtualViewId = i;
                    explorebytouchhelper.mHost.invalidate();
                    explorebytouchhelper.sendEventForVirtualView(i, 32768);
                    return true;
                } else
                {
                    return false;
                }

            case 128: 
                return explorebytouchhelper.clearAccessibilityFocus(i);

            case 1: // '\001'
                return explorebytouchhelper.requestKeyboardFocusForVirtualView(i);

            case 2: // '\002'
                break;
            }
            break;

        case -1: 
            return ViewCompat.performAccessibilityAction(explorebytouchhelper.mHost, j, bundle);
        }
        if (explorebytouchhelper.mKeyboardFocusedVirtualViewId != i)
        {
            return false;
        } else
        {
            explorebytouchhelper.mKeyboardFocusedVirtualViewId = 0x80000000;
            explorebytouchhelper.sendEventForVirtualView(i, 8);
            return true;
        }
    }

    pat()
    {
        this$0 = ExploreByTouchHelper.this;
        super();
    }
}
