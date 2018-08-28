// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.bottomsheet;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.support.design.bottomsheet:
//            BottomSheetDialog

final class it> extends AccessibilityDelegateCompat
{

    private final BottomSheetDialog this$0;

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        if (cancelable)
        {
            accessibilitynodeinfocompat.mInfo.addAction(0x100000);
            accessibilitynodeinfocompat.mInfo.setDismissable(true);
            return;
        } else
        {
            accessibilitynodeinfocompat.mInfo.setDismissable(false);
            return;
        }
    }

    public final boolean performAccessibilityAction(View view, int i, Bundle bundle)
    {
        if (i == 0x100000 && cancelable)
        {
            cancel();
            return true;
        } else
        {
            return super.performAccessibilityAction(view, i, bundle);
        }
    }

    InfoCompat()
    {
        this$0 = BottomSheetDialog.this;
        super();
    }
}
