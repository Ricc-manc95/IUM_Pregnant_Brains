// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;

// Referenced classes of package com.google.android.calendar.event:
//            CustomDurationDialog

final class this._cls0
    implements android.content.Listener
{

    private final CustomDurationDialog this$0;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        onCancel(dialoginterface);
    }

    ()
    {
        this$0 = CustomDurationDialog.this;
        super();
    }
}
