// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.support.design.internal:
//            NavigationMenuItemView

final class > extends AccessibilityDelegateCompat
{

    private final NavigationMenuItemView this$0;

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        boolean flag = checkable;
        accessibilitynodeinfocompat.mInfo.setCheckable(flag);
    }

    foCompat()
    {
        this$0 = NavigationMenuItemView.this;
        super();
    }
}
