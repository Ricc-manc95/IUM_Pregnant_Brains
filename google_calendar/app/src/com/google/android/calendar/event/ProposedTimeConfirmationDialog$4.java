// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.DialogInterface;

// Referenced classes of package com.google.android.calendar.event:
//            ProposedTimeConfirmationDialog

final class this._cls0
    implements android.content.firmationDialog._cls4
{

    private final ProposedTimeConfirmationDialog this$0;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        if (listener != null)
        {
            listener.onProposedTimeConfirm();
        }
    }

    ProposedTimeConfirmListener()
    {
        this$0 = ProposedTimeConfirmationDialog.this;
        super();
    }
}
