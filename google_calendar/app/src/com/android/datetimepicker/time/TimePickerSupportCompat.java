// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import com.android.datetimepicker.SupportDialogFragmentListener;
import com.android.datetimepicker.SupportDialogFragmentWithListener;

public final class TimePickerSupportCompat
{

    public SupportDialogFragmentListener dialogFragmentListener;
    public DialogFragment fragment;
    public final TimePickerCompat.OnTimeSetListener onTimeSetListener;

    public TimePickerSupportCompat(TimePickerCompat.OnTimeSetListener ontimesetlistener)
    {
        onTimeSetListener = ontimesetlistener;
    }

    public final void initialize(int i, int j, boolean flag)
    {
        if (fragment != null)
        {
            fragment.dismiss();
        }
        DefaultTimePickerSupportFragment defaulttimepickersupportfragment = new DefaultTimePickerSupportFragment();
        Bundle bundle = new Bundle(3);
        bundle.putInt("hour", i);
        bundle.putInt("minute", j);
        bundle.putBoolean("24hour", flag);
        defaulttimepickersupportfragment.setArguments(bundle);
        defaulttimepickersupportfragment.timeListener = new TimePickerCompat.DefaultTimeSetListenerWrapper(onTimeSetListener);
        defaulttimepickersupportfragment.dialogListener = dialogFragmentListener;
        fragment = defaulttimepickersupportfragment;
    }

    private class DefaultTimePickerSupportFragment extends SupportDialogFragmentWithListener
    {

        public android.app.TimePickerDialog.OnTimeSetListener timeListener;

        public final Dialog onCreateDialog(Bundle bundle)
        {
            bundle = timeListener;
            Object obj = super.mTarget;
            if (obj instanceof TimePickerCompat.OnTimeSetListener)
            {
                bundle = new TimePickerCompat.DefaultTimeSetListenerWrapper((TimePickerCompat.OnTimeSetListener)obj);
            }
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            return TimePickerCompat.DefaultTimePickerFragment.createDialog(((android.content.Context) (obj)), getArguments(), bundle);
        }

        public DefaultTimePickerSupportFragment()
        {
        }
    }

}
