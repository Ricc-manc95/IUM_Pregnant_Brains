// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

// Referenced classes of package com.android.datetimepicker:
//            SupportDialogFragmentListener

public class SupportDialogFragmentWithListener extends DialogFragment
{

    public SupportDialogFragmentListener dialogListener;

    public SupportDialogFragmentWithListener()
    {
    }

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

    public final void onDestroy()
    {
        super.onDestroy();
        if (dialogListener != null)
        {
            dialogListener.onDialogDestroyed(this);
        }
    }
}
