// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.graphics.Rect;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

// Referenced classes of package android.support.v4.widget:
//            DrawerLayout

final class this._cls0 extends AccessibilityDelegateCompat
{

    private final Rect mTmpRect = new Rect();
    private final DrawerLayout this$0;

    public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        if (accessibilityevent.getEventType() == 32)
        {
            view = accessibilityevent.getText();
            accessibilityevent = findVisibleDrawer();
            if (accessibilityevent != null)
            {
                DrawerLayout drawerlayout = DrawerLayout.this;
                if (Gravity.getAbsoluteGravity(Gravity.getAbsoluteGravity(((this._cls0)accessibilityevent.getLayoutParams())._fld0, ViewCompat.getLayoutDirection(drawerlayout)), ViewCompat.getLayoutDirection(DrawerLayout.this)) == 3);
                if (false)
                {
                    view.add(null);
                }
            }
            return true;
        } else
        {
            return super.dispatchPopulateAccessibilityEvent(view, accessibilityevent);
        }
    }

    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(view, accessibilityevent);
        accessibilityevent.setClassName(android/support/v4/widget/DrawerLayout.getName());
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        if (DrawerLayout.CAN_HIDE_DESCENDANTS)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        } else
        {
            AccessibilityNodeInfoCompat accessibilitynodeinfocompat1 = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(accessibilitynodeinfocompat.mInfo));
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat1);
            accessibilitynodeinfocompat.mInfo.setSource(view);
            Object obj = ViewCompat.getParentForAccessibility(view);
            if (obj instanceof View)
            {
                obj = (View)obj;
                accessibilitynodeinfocompat.mInfo.setParent(((View) (obj)));
            }
            obj = mTmpRect;
            accessibilitynodeinfocompat1.mInfo.getBoundsInParent(((Rect) (obj)));
            accessibilitynodeinfocompat.mInfo.setBoundsInParent(((Rect) (obj)));
            accessibilitynodeinfocompat1.mInfo.getBoundsInScreen(((Rect) (obj)));
            accessibilitynodeinfocompat.mInfo.setBoundsInScreen(((Rect) (obj)));
            boolean flag = accessibilitynodeinfocompat1.mInfo.isVisibleToUser();
            accessibilitynodeinfocompat.mInfo.setVisibleToUser(flag);
            obj = accessibilitynodeinfocompat1.mInfo.getPackageName();
            accessibilitynodeinfocompat.mInfo.setPackageName(((CharSequence) (obj)));
            obj = accessibilitynodeinfocompat1.mInfo.getClassName();
            accessibilitynodeinfocompat.mInfo.setClassName(((CharSequence) (obj)));
            obj = accessibilitynodeinfocompat1.mInfo.getContentDescription();
            accessibilitynodeinfocompat.mInfo.setContentDescription(((CharSequence) (obj)));
            flag = accessibilitynodeinfocompat1.mInfo.isEnabled();
            accessibilitynodeinfocompat.mInfo.setEnabled(flag);
            flag = accessibilitynodeinfocompat1.mInfo.isClickable();
            accessibilitynodeinfocompat.mInfo.setClickable(flag);
            flag = accessibilitynodeinfocompat1.mInfo.isFocusable();
            accessibilitynodeinfocompat.mInfo.setFocusable(flag);
            flag = accessibilitynodeinfocompat1.mInfo.isFocused();
            accessibilitynodeinfocompat.mInfo.setFocused(flag);
            flag = accessibilitynodeinfocompat1.mInfo.isAccessibilityFocused();
            accessibilitynodeinfocompat.mInfo.setAccessibilityFocused(flag);
            flag = accessibilitynodeinfocompat1.mInfo.isSelected();
            accessibilitynodeinfocompat.mInfo.setSelected(flag);
            flag = accessibilitynodeinfocompat1.mInfo.isLongClickable();
            accessibilitynodeinfocompat.mInfo.setLongClickable(flag);
            int i = accessibilitynodeinfocompat1.mInfo.getActions();
            accessibilitynodeinfocompat.mInfo.addAction(i);
            accessibilitynodeinfocompat1.mInfo.recycle();
            view = (ViewGroup)view;
            int j = view.getChildCount();
            i = 0;
            while (i < j) 
            {
                View view1 = view.getChildAt(i);
                if (DrawerLayout.includeChildForAccessibility(view1))
                {
                    accessibilitynodeinfocompat.mInfo.addChild(view1);
                }
                i++;
            }
        }
        view = android/support/v4/widget/DrawerLayout.getName();
        accessibilitynodeinfocompat.mInfo.setClassName(view);
        accessibilitynodeinfocompat.mInfo.setFocusable(false);
        accessibilitynodeinfocompat.mInfo.setFocused(false);
        view = android.support.v4.view.accessibility.bilityActionCompat.ACTION_FOCUS;
        accessibilitynodeinfocompat.mInfo.removeAction((android.view.accessibility.Action)((android.support.v4.view.accessibility.bilityActionCompat) (view)).mAction);
        view = android.support.v4.view.accessibility.bilityActionCompat.ACTION_CLEAR_FOCUS;
        accessibilitynodeinfocompat.mInfo.removeAction((android.view.accessibility.Action)((android.support.v4.view.accessibility.bilityActionCompat) (view)).mAction);
    }

    public final boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
    {
        if (DrawerLayout.CAN_HIDE_DESCENDANTS || DrawerLayout.includeChildForAccessibility(view))
        {
            return super.onRequestSendAccessibilityEvent(viewgroup, view, accessibilityevent);
        } else
        {
            return false;
        }
    }

    mpat()
    {
        this$0 = DrawerLayout.this;
        super();
    }
}
