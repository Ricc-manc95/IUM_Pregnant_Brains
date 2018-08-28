// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityRecord;
import android.widget.ScrollView;

// Referenced classes of package android.support.v4.widget:
//            NestedScrollView

static final class  extends AccessibilityDelegateCompat
{

    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(view, accessibilityevent);
        view = (NestedScrollView)view;
        accessibilityevent.setClassName(android/widget/ScrollView.getName());
        boolean flag;
        if (view.getScrollRange() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        accessibilityevent.setScrollable(flag);
        accessibilityevent.setScrollX(view.getScrollX());
        accessibilityevent.setScrollY(view.getScrollY());
        accessibilityevent.setMaxScrollX(view.getScrollX());
        accessibilityevent.setMaxScrollY(view.getScrollRange());
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        view = (NestedScrollView)view;
        String s = android/widget/ScrollView.getName();
        accessibilitynodeinfocompat.mInfo.setClassName(s);
        if (view.isEnabled())
        {
            int i = view.getScrollRange();
            if (i > 0)
            {
                accessibilitynodeinfocompat.mInfo.setScrollable(true);
                if (view.getScrollY() > 0)
                {
                    accessibilitynodeinfocompat.mInfo.addAction(8192);
                }
                if (view.getScrollY() < i)
                {
                    accessibilitynodeinfocompat.mInfo.addAction(4096);
                }
            }
        }
    }

    public final boolean performAccessibilityAction(View view, int i, Bundle bundle)
    {
        if (super.performAccessibilityAction(view, i, bundle))
        {
            return true;
        }
        view = (NestedScrollView)view;
        if (!view.isEnabled())
        {
            return false;
        }
        switch (i)
        {
        default:
            return false;

        case 4096: 
            i = Math.min((view.getHeight() - view.getPaddingBottom() - view.getPaddingTop()) + view.getScrollY(), view.getScrollRange());
            if (i != view.getScrollY())
            {
                view.smoothScrollBy(0 - view.getScrollX(), i - view.getScrollY());
                return true;
            } else
            {
                return false;
            }

        case 8192: 
            i = view.getHeight();
            int j = view.getPaddingBottom();
            int k = view.getPaddingTop();
            i = Math.max(view.getScrollY() - (i - j - k), 0);
            break;
        }
        if (i != view.getScrollY())
        {
            view.smoothScrollBy(0 - view.getScrollX(), i - view.getScrollY());
            return true;
        } else
        {
            return false;
        }
    }

    ()
    {
    }
}
