// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.Context;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ViewScreenController

final class verlayDialog extends com.google.android.calendar.common.view.overlay.Dialog
{

    private final ViewScreenController this$0;

    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        if (accessibilityevent.getEventType() == 32)
        {
            List list = accessibilityevent.getText();
            Object obj = ViewScreenController.this;
            accessibilityevent = ((OverlayFragment) (obj)).getTitle();
            obj = ((ViewScreenController) (obj)).getModel().getTitle();
            if (!TextUtils.isEmpty(((CharSequence) (obj))))
            {
                accessibilityevent = (new StringBuilder(String.valueOf(accessibilityevent).length() + 1 + String.valueOf(obj).length())).append(accessibilityevent).append("\n").append(((String) (obj))).toString();
            }
            list.add(accessibilityevent);
            return true;
        } else
        {
            return super.dispatchPopulateAccessibilityEvent(accessibilityevent);
        }
    }

    verlayDialog(Context context)
    {
        this$0 = ViewScreenController.this;
        super(ViewScreenController.this, context);
    }
}
