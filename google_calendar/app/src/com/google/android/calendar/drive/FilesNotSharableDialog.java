// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.drive;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;

public final class FilesNotSharableDialog extends DialogFragment
{

    public FilesNotSharableDialog()
    {
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        int i = getArguments().getInt("numFiles");
        String s;
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        s = bundle.getResources().getQuantityString(0x7f120015, i);
        return (new android.app.AlertDialog.Builder(bundle)).setMessage(s).create();
    }
}
