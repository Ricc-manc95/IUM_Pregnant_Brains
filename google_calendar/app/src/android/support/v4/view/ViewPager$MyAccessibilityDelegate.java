// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.support.v4.view:
//            AccessibilityDelegateCompat, ViewPager, PagerAdapter

final class  extends AccessibilityDelegateCompat
{

    private final ViewPager this$0;

    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        boolean flag = true;
        super.onInitializeAccessibilityEvent(view, accessibilityevent);
        accessibilityevent.setClassName(android/support/v4/view/ViewPager.getName());
        if (mAdapter == null || mAdapter.getCount() <= 1)
        {
            flag = false;
        }
        accessibilityevent.setScrollable(flag);
        if (accessibilityevent.getEventType() == 4096 && mAdapter != null)
        {
            accessibilityevent.setItemCount(mAdapter.getCount());
            accessibilityevent.setFromIndex(mCurItem);
            accessibilityevent.setToIndex(mCurItem);
        }
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        view = android/support/v4/view/ViewPager.getName();
        accessibilitynodeinfocompat.mInfo.setClassName(view);
        boolean flag;
        if (mAdapter != null && mAdapter.getCount() > 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        accessibilitynodeinfocompat.mInfo.setScrollable(flag);
        if (canScrollHorizontally(1))
        {
            accessibilitynodeinfocompat.mInfo.addAction(4096);
        }
        if (canScrollHorizontally(-1))
        {
            accessibilitynodeinfocompat.mInfo.addAction(8192);
        }
    }

    public final boolean performAccessibilityAction(View view, int i, Bundle bundle)
    {
        if (super.performAccessibilityAction(view, i, bundle))
        {
            return true;
        }
        switch (i)
        {
        default:
            return false;

        case 4096: 
            if (canScrollHorizontally(1))
            {
                setCurrentItem(mCurItem + 1);
                return true;
            } else
            {
                return false;
            }

        case 8192: 
            break;
        }
        if (canScrollHorizontally(-1))
        {
            setCurrentItem(mCurItem - 1);
            return true;
        } else
        {
            return false;
        }
    }

    oCompat()
    {
        this$0 = ViewPager.this;
        super();
    }
}
