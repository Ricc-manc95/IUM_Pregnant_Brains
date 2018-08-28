// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import com.android.datetimepicker.DialogFragmentWithListener;

public final class per extends DialogFragmentWithListener
{

    public android.app.tTimePickerFragment.getArguments timeListener;

    static Dialog createDialog(Context context, Bundle bundle, android.app.tTimePickerFragment ttimepickerfragment)
    {
        class _cls1 extends TimePickerDialog
        {

            public final void onClick(DialogInterface dialoginterface, int i)
            {
                switch (i)
                {
                default:
                    super.onClick(dialoginterface, i);
                    return;

                case -2: 
                    cancel();
                    break;
                }
            }

            _cls1(Context context, android.app.TimePickerDialog.OnTimeSetListener ontimesetlistener, int i, int j, boolean flag)
            {
                super(context, ontimesetlistener, i, j, flag);
            }
        }

        return new _cls1(context, ttimepickerfragment, bundle.getInt("hour"), bundle.getInt("minute"), bundle.getBoolean("24hour"));
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        bundle = timeListener;
        android.app.Fragment fragment = getTargetFragment();
        if (fragment instanceof getTargetFragment)
        {
            bundle = new per((per)fragment);
        }
        return createDialog(getActivity(), getArguments(), bundle);
    }
}
