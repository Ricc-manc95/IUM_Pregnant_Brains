// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.DialogInterface;

// Referenced classes of package com.google.android.calendar.event:
//            CustomDurationDialog

final class this._cls0
    implements android.content.Listener
{

    private final CustomDurationDialog this$0;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        if (listener != null)
        {
            dialoginterface = listener;
            CustomDurationDialog customdurationdialog = CustomDurationDialog.this;
            i = customdurationdialog.hours;
            dialoginterface.onCustomDurationSet(customdurationdialog.minutes + i * 60);
        }
    }

    CustomDurationSetListener()
    {
        this$0 = CustomDurationDialog.this;
        super();
    }
}
