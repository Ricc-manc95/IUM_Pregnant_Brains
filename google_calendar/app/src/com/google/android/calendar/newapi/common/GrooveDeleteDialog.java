// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public final class GrooveDeleteDialog extends DialogFragment
    implements android.content.DialogInterface.OnClickListener
{

    private int deleteValues[];
    private AlertDialog dialog;
    private int selectedDeleteValue;

    public GrooveDeleteDialog()
    {
    }

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        if (i != -1)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        flag1 = getArguments().getBoolean("ARG_GROOVE_SIMPLIFIED_OPTIONS");
        dialoginterface = (Callback)super.mTarget;
        break MISSING_BLOCK_LABEL_26;
        if (!flag1 && selectedDeleteValue != 0)
        {
            flag = false;
        }
        dialoginterface.onDeleteConfirmed(flag);
        return;
        selectedDeleteValue = deleteValues[i];
        dialog.getButton(-1).setEnabled(true);
        return;
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        if (getArguments().getBoolean("ARG_GROOVE_SIMPLIFIED_OPTIONS"))
        {
            if (super.mHost == null)
            {
                bundle = null;
            } else
            {
                bundle = (FragmentActivity)super.mHost.mActivity;
            }
            dialog = (new android.app.AlertDialog.Builder(bundle)).setMessage(0x7f13015d).setPositiveButton(0x104000a, this).setNegativeButton(0x1040000, null).show();
            return dialog;
        }
        bundle = getArguments().getString("ARG_GROOVE_TITLE");
        String as[] = requireContext().getResources().getStringArray(0x7f0b001e);
        deleteValues = requireContext().getResources().getIntArray(0x7f0b0021);
        Object obj = new ArrayList();
        for (int i = 0; i < deleteValues.length - 1; i++)
        {
            ((List) (obj)).add(Integer.valueOf(deleteValues[i]));
        }

        obj = requireContext().getResources().getString(0x7f13015c, new Object[] {
            bundle
        });
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        dialog = (new android.app.AlertDialog.Builder(bundle)).setTitle(((CharSequence) (obj))).setSingleChoiceItems(as, -1, this).setPositiveButton(0x104000a, this).setNegativeButton(0x1040000, null).show();
        dialog.getButton(-1).setEnabled(false);
        return dialog;
    }

    private class Callback
    {

        public abstract void onDeleteConfirmed(boolean flag);
    }

}
