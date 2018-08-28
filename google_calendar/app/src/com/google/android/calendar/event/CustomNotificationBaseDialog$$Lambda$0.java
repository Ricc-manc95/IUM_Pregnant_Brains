// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.app.AlertDialog;
import android.view.View;

// Referenced classes of package com.google.android.calendar.event:
//            CustomNotificationBaseDialog

final class arg._cls1
    implements android.view.BaseDialog..Lambda._cls0
{

    private final CustomNotificationBaseDialog arg$1;

    public final void onClick(View view)
    {
        view = arg$1;
        view.doneButtonPressed = true;
        if (((CustomNotificationBaseDialog) (view)).alertDialog != null)
        {
            ((CustomNotificationBaseDialog) (view)).alertDialog.hide();
            ((CustomNotificationBaseDialog) (view)).alertDialog.dismiss();
        }
    }

    (CustomNotificationBaseDialog customnotificationbasedialog)
    {
        arg$1 = customnotificationbasedialog;
    }
}
