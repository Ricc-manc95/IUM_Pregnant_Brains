// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.scope;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.ListView;
import java.util.List;

// Referenced classes of package com.google.android.calendar.event.scope:
//            ScopeSelectionDialog

final class arg._cls1
    implements android.content.
{

    private final ScopeSelectionDialog arg$1;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface = arg$1;
        arg._cls1 _lcls1 = (arg._cls1)dialoginterface.getArguments().getParcelable("ARGUMENT_CONFIG");
        i = ((AlertDialog)dialoginterface.getDialog()).getListView().getCheckedItemPosition();
        arg._cls1 _lcls1_1 = (arg._cls1)_lcls1.pes().get(i);
        ((ectedCallback)((Fragment) (dialoginterface)).mTarget).onScopeSelected(_lcls1_1.e(), _lcls1);
    }

    ectedCallback(ScopeSelectionDialog scopeselectiondialog)
    {
        arg$1 = scopeselectiondialog;
    }
}
