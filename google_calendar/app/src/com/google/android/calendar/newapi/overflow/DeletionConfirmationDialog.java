// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.overflow;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;

public final class DeletionConfirmationDialog extends DialogFragment
    implements android.content.DialogInterface.OnClickListener
{

    public DeletionConfirmationDialog()
    {
    }

    public static DeletionConfirmationDialog newInstance(Fragment fragment, int i)
    {
        DeletionConfirmationDialog deletionconfirmationdialog = new DeletionConfirmationDialog();
        deletionconfirmationdialog.setTargetFragment(fragment, 0);
        fragment = new Bundle();
        fragment.putInt("ARGUMENT_MESSAGE", i);
        deletionconfirmationdialog.setArguments(fragment);
        return deletionconfirmationdialog;
    }

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        ((OnDeleteConfirmedCallback)super.mTarget).onDeleteConfirmed();
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        return (new android.app.AlertDialog.Builder(bundle)).setMessage(getArguments().getInt("ARGUMENT_MESSAGE")).setNegativeButton(0x1040000, null).setPositiveButton(0x7f130160, this).create();
    }

    private class OnDeleteConfirmedCallback
    {

        public abstract void onDeleteConfirmed();
    }

}
