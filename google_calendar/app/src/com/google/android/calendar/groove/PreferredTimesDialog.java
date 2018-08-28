// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

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
import com.android.calendarcommon2.LogUtils;

public class PreferredTimesDialog extends DialogFragment
    implements android.content.DialogInterface.OnClickListener, android.content.DialogInterface.OnMultiChoiceClickListener
{
    public static interface OnPreferredTimesSelectedListener
    {

        public abstract void onPreferredTimesSelected(boolean aflag[]);
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/groove/PreferredTimesDialog);
    public boolean checkedItems[];
    public OnPreferredTimesSelectedListener listener;

    public PreferredTimesDialog()
    {
    }

    private final void disablePositiveButtonIfNoneChecked(AlertDialog alertdialog)
    {
        boolean flag1 = true;
        boolean flag;
        if (!checkedItems[0] && !checkedItems[1] && !checkedItems[2])
        {
            flag = true;
        } else
        {
            flag = false;
        }
        alertdialog = alertdialog.getButton(-1);
        if (flag)
        {
            flag1 = false;
        }
        alertdialog.setEnabled(flag1);
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (listener != null)
        {
            listener.onPreferredTimesSelected(checkedItems);
        }
    }

    public void onClick(DialogInterface dialoginterface, int i, boolean flag)
    {
        checkedItems[i] = flag;
        disablePositiveButtonIfNoneChecked((AlertDialog)dialoginterface);
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null)
        {
            checkedItems = bundle.getBooleanArray("preferred_times_checked");
        }
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
        return (new android.app.AlertDialog.Builder(bundle)).setTitle(requireContext().getResources().getString(0x7f1303b1)).setMultiChoiceItems(0x7f0b0047, checkedItems, this).setNegativeButton(requireContext().getResources().getString(0x1040000), null).setPositiveButton(requireContext().getResources().getString(0x104000a), this).create();
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putBooleanArray("preferred_times_checked", checkedItems);
        super.onSaveInstanceState(bundle);
    }

    public final void onStart()
    {
        super.onStart();
        disablePositiveButtonIfNoneChecked((AlertDialog)getDialog());
    }

}
