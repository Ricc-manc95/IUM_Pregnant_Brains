// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.edit;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;

public class InvalidDatesChosenDialog extends DialogFragment
{

    public static final String TAG = com/google/android/calendar/event/edit/InvalidDatesChosenDialog.getSimpleName();

    public InvalidDatesChosenDialog()
    {
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        Resources resources;
        String s;
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        resources = bundle.getResources();
        s = resources.getString(0x7f13030a);
        return (new android.app.AlertDialog.Builder(bundle)).setMessage(s).setNegativeButton(resources.getString(0x104000a), null).create();
    }

}
