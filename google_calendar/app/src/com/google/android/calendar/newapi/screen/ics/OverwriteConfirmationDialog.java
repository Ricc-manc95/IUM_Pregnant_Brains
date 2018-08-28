// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.ics;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;

public class OverwriteConfirmationDialog extends DialogFragment
    implements android.content.DialogInterface.OnClickListener
{
    public static interface Listener
    {

        public abstract void onOverwriteConfirmed();
    }


    public static final String TAG = com/google/android/calendar/newapi/screen/ics/OverwriteConfirmationDialog.getSimpleName();

    public OverwriteConfirmationDialog()
    {
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (super.mTarget instanceof Listener)
        {
            ((Listener)super.mTarget).onOverwriteConfirmed();
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
        return (new android.app.AlertDialog.Builder(bundle)).setTitle(0x7f130302).setMessage(0x7f130301).setNegativeButton(0x1040000, null).setPositiveButton(0x7f130300, this).create();
    }

}
