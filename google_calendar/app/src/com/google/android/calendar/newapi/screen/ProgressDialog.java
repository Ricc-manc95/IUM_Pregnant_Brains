// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public final class ProgressDialog extends DialogFragment
{

    public ProgressDialog()
    {
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        android.app.AlertDialog.Builder builder;
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        builder = new android.app.AlertDialog.Builder(bundle);
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        bundle = bundle.getLayoutInflater().inflate(0x7f0500db, null);
        ((TextView)bundle.findViewById(0x7f1000ff)).setText(requireContext().getResources().getText(0));
        builder.setView(bundle);
        return builder.create();
    }
}
