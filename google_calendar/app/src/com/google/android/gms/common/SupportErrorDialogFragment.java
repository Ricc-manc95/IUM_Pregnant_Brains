// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public final class SupportErrorDialogFragment extends DialogFragment
{

    public Dialog mDialog;
    public android.content.DialogInterface.OnCancelListener zzaIl;

    public SupportErrorDialogFragment()
    {
        mDialog = null;
        zzaIl = null;
    }

    public final void onCancel(DialogInterface dialoginterface)
    {
        if (zzaIl != null)
        {
            zzaIl.onCancel(dialoginterface);
        }
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        if (mDialog == null)
        {
            super.mShowsDialog = false;
        }
        return mDialog;
    }
}
