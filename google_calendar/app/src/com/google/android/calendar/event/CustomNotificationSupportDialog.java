// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.view.Window;
import com.android.datetimepicker.time.TimePickerSupportCompat;
import com.google.android.calendar.time.Time;
import java.util.List;

// Referenced classes of package com.google.android.calendar.event:
//            CustomNotificationBaseDialog

public final class CustomNotificationSupportDialog extends DialogFragment
    implements CustomNotificationBaseDialog.TimePickerShowingDialog
{

    public final CustomNotificationBaseDialog dialog = new CustomNotificationBaseDialog(this);

    public CustomNotificationSupportDialog()
    {
    }

    public static CustomNotificationSupportDialog newInstance(boolean flag, String s, boolean flag1)
    {
        CustomNotificationSupportDialog customnotificationsupportdialog = new CustomNotificationSupportDialog();
        customnotificationsupportdialog.mCancelable = true;
        if (((DialogFragment) (customnotificationsupportdialog)).mDialog != null)
        {
            ((DialogFragment) (customnotificationsupportdialog)).mDialog.setCancelable(true);
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("all_day", flag);
        bundle.putString("allowed_reminders", s);
        bundle.putBoolean("allow_notifications_after_event", flag1);
        customnotificationsupportdialog.setArguments(bundle);
        return customnotificationsupportdialog;
    }

    public final void onCancel(DialogInterface dialoginterface)
    {
        super.onCancel(dialoginterface);
        dialoginterface = dialog;
        if (((CustomNotificationBaseDialog) (dialoginterface)).listener != null)
        {
            ((CustomNotificationBaseDialog) (dialoginterface)).listener.onCancel();
        }
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        CustomNotificationBaseDialog customnotificationbasedialog = dialog;
        Object obj;
        Bundle bundle1;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        bundle1 = getArguments();
        getDialog();
        return customnotificationbasedialog.onCreateDialog$51662RJ4E9NMIP1FC5O70BQ1CDQ6ITJ9EHSJMJ31DPI74RR9CGNMUSPF89QMSP3CCKTKOOBECHP6UQB45TNN6BQ2ELN68R357D662RJ4E9NMIP1FC5O70BQ4D5GMORR77CKKOOBECHP6UQB45TGN0S1F8HKM2R3FCSTG____0(((Activity) (obj)), bundle1, bundle);
    }

    public final void onDismiss(DialogInterface dialoginterface)
    {
        super.onDismiss(dialoginterface);
        CustomNotificationBaseDialog customnotificationbasedialog = dialog;
        if (super.mHost == null)
        {
            dialoginterface = null;
        } else
        {
            dialoginterface = (FragmentActivity)super.mHost.mActivity;
        }
        if (dialoginterface != null)
        {
            dialoginterface.getWindow().setSoftInputMode(2);
        }
        if (customnotificationbasedialog.listener != null && customnotificationbasedialog.doneButtonPressed)
        {
            customnotificationbasedialog.listener.onCustomNotificationSet(customnotificationbasedialog.getMinutes(), ((Integer)customnotificationbasedialog.methodValues.get(customnotificationbasedialog.methodOptions.selectedIndex)).intValue());
        }
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        Object obj = dialog;
        bundle.putInt("selectedUnitsIndex", ((CustomNotificationBaseDialog) (obj)).unitOptions.selectedIndex);
        bundle.putInt("selectedMethodIndex", ((CustomNotificationBaseDialog) (obj)).methodOptions.selectedIndex);
        obj = ((CustomNotificationBaseDialog) (obj)).time;
        ((Time) (obj)).writeFieldsToImpl();
        bundle.putLong("atTime", ((Time) (obj)).impl.toMillis(false));
        super.onSaveInstanceState(bundle);
    }

    public final void showTimePicker(Time time)
    {
        Object obj;
        TimePickerSupportCompat timepickersupportcompat;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        timepickersupportcompat = new TimePickerSupportCompat(dialog);
        timepickersupportcompat.initialize(time.hour, time.minute, DateFormat.is24HourFormat(((android.content.Context) (obj))));
        time = ((FragmentActivity) (obj)).mFragments.mHost.mFragmentManager;
        time.executePendingTransactions();
        timepickersupportcompat.fragment.show(time, "TimePickerDialog");
    }
}
