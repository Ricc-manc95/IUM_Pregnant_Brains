// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.view.View;

// Referenced classes of package com.google.android.calendar.event:
//            CustomNotificationBaseDialog

final class arg._cls1
    implements android.view.BaseDialog..Lambda._cls1
{

    private final CustomNotificationBaseDialog arg$1;

    public final void onClick(View view)
    {
        view = arg$1;
        ((CustomNotificationBaseDialog) (view)).dialog.showTimePicker(((CustomNotificationBaseDialog) (view)).time);
    }

    ShowingDialog(CustomNotificationBaseDialog customnotificationbasedialog)
    {
        arg$1 = customnotificationbasedialog;
    }
}
