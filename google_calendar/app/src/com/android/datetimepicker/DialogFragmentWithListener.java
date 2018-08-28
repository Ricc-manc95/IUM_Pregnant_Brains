// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

// Referenced classes of package com.android.datetimepicker:
//            DialogFragmentListener

public class DialogFragmentWithListener extends DialogFragment
{

    public DialogFragmentListener dialogListener;

    public void onCancel(DialogInterface dialoginterface)
    {
        super.onCancel(dialoginterface);
        if (dialogListener != null)
        {
            dialogListener.onDialogCancelled(this);
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (dialogListener != null)
        {
            dialogListener.onDialogCreated(this);
        }
    }

    public void onDestroy()
    {
        super.onDestroy();
        if (dialogListener != null)
        {
            dialogListener.onDialogDestroyed(this);
        }
    }
}
