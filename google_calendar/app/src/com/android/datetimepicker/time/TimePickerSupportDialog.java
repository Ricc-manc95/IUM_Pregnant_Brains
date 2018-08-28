// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.datetimepicker.HapticFeedbackController;
import com.android.datetimepicker.SupportDialogFragmentWithListener;

// Referenced classes of package com.android.datetimepicker.time:
//            TimePickerBaseDialog

public final class TimePickerSupportDialog extends SupportDialogFragmentWithListener
    implements TimePickerBaseDialog.DismissiblePicker
{

    public final TimePickerBaseDialog baseDialog = new TimePickerBaseDialog(this);

    public TimePickerSupportDialog()
    {
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        baseDialog.onCreate(bundle);
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        bundle = super.onCreateDialog(bundle);
        Fragment fragment = super.mTarget;
        if (fragment instanceof TimePickerCompat.OnTimeSetListener)
        {
            baseDialog.callback = new TimePickerCompat.LibraryTimeSetListenerWrapper((TimePickerCompat.OnTimeSetListener)fragment);
        }
        return bundle;
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        TimePickerBaseDialog timepickerbasedialog = baseDialog;
        if (super.mHost == null)
        {
            viewgroup = null;
        } else
        {
            viewgroup = (FragmentActivity)super.mHost.mActivity;
        }
        return timepickerbasedialog.onCreateView$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FEPKMATPF9HGNIRRLEH4MSPJCC5Q6ASHR9HGMSP3IDTKM8BRMD5INEBQMD5INEHRIDTQN0EQCC5N68SJFD5I2URRJ5T17ARJ4DHIJMAACC5N68SJFD5I2UTJ9CLRIULJ9CLRJM___0(viewgroup, layoutinflater, bundle);
    }

    public final void onPause()
    {
        super.onPause();
        HapticFeedbackController hapticfeedbackcontroller = baseDialog.hapticFeedbackController;
        hapticfeedbackcontroller.vibrator = null;
        hapticfeedbackcontroller.context.getContentResolver().unregisterContentObserver(hapticfeedbackcontroller.contentObserver);
    }

    public final void onResume()
    {
        super.onResume();
        baseDialog.hapticFeedbackController.start();
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        baseDialog.onSaveInstanceState(bundle);
    }
}
