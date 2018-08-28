// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.widget.ListAdapter;

// Referenced classes of package com.google.android.calendar.common.dialog:
//            SingleChoiceDialogListener

public abstract class SingleChoiceDialog extends DialogFragment
    implements android.content.DialogInterface.OnClickListener
{

    public static final String TAG = com/google/android/calendar/common/dialog/SingleChoiceDialog.getSimpleName();
    public int selectedItem;
    public String title;

    public SingleChoiceDialog()
    {
        selectedItem = -1;
    }

    public abstract ListAdapter createListAdapter(int i);

    public abstract Object getValue(int i);

    public void onClick(DialogInterface dialoginterface, int i)
    {
        ((SingleChoiceDialogListener)super.mTarget).onDialogItemChosen(getValue(i), super.mTargetRequestCode);
        dismissAllowingStateLoss();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null)
        {
            selectedItem = bundle.getInt("single_choice_dialog_selected_item");
            title = bundle.getString("single_choice_dialog_title");
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
        return (new android.app.AlertDialog.Builder(bundle)).setSingleChoiceItems(createListAdapter(selectedItem), selectedItem, this).setTitle(title).create();
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        bundle.putInt("single_choice_dialog_selected_item", selectedItem);
        bundle.putString("single_choice_dialog_title", title);
        super.onSaveInstanceState(bundle);
    }

}
