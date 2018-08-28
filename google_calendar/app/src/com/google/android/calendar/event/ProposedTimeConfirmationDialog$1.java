// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.view.View;

// Referenced classes of package com.google.android.calendar.event:
//            ProposedTimeConfirmationDialog

final class this._cls0
    implements android.view.ConfirmationDialog._cls1
{

    private final ProposedTimeConfirmationDialog this$0;

    public final void onClick(View view)
    {
        if (listener != null)
        {
            listener.onProposedTimeEditRequest();
        }
        getDialog().dismiss();
    }

    ProposedTimeConfirmListener()
    {
        this$0 = ProposedTimeConfirmationDialog.this;
        super();
    }
}
