// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.utils.AccessibilityUtils;

// Referenced classes of package com.google.android.calendar.event:
//            ReminderUtils

public class CustomDurationDialog extends DialogFragment
    implements TextWatcher
{
    public static final class Builder
    {

        public Bundle args;

        public Builder(int i)
        {
            args = new Bundle();
            args.putInt("duration_in_minutes", i);
        }
    }

    public static interface OnCustomDurationSetListener
    {

        public abstract void onCustomDurationDialogCancel();

        public abstract void onCustomDurationSet(int i);
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/event/CustomDurationDialog);
    private Button doneButton;
    private TextView errorText;
    private EditText hourText;
    public int hours;
    public OnCustomDurationSetListener listener;
    private int maxDurationInMinutes;
    private int maxErrorMsgId;
    private int minDurationInMinutes;
    private int minErrorMsgId;
    private EditText minuteText;
    public int minutes;

    public CustomDurationDialog()
    {
    }

    private final void updateErrorView()
    {
        int i;
        if (maxDurationInMinutes != 0x7fffffff && hours * 60 + minutes > maxDurationInMinutes)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L2; else goto _L1
_L1:
        Object obj = minuteText;
        i = ((EditText) (obj)).getPaddingTop();
        int l = ((EditText) (obj)).getPaddingBottom();
        ((EditText) (obj)).setBackgroundResource(0x7f020103);
        ((EditText) (obj)).setPadding(0, i, 0, l);
        obj = errorText;
        i = maxErrorMsgId;
        String s = ReminderUtils.constructTimeIntervalString(requireContext().getResources(), maxDurationInMinutes);
        ((TextView) (obj)).setText(requireContext().getResources().getString(i, new Object[] {
            s
        }));
        errorText.setVisibility(0);
        if (doneButton != null)
        {
            doneButton.setEnabled(false);
        }
_L4:
        return;
_L2:
        Object obj1;
        String s1;
        int j;
        int i1;
        if (minDurationInMinutes != 0x7fffffff && hours * 60 + minutes < minDurationInMinutes)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = hourText;
        j = ((EditText) (obj1)).getPaddingTop();
        i1 = ((EditText) (obj1)).getPaddingBottom();
        ((EditText) (obj1)).setBackgroundResource(0x7f020103);
        ((EditText) (obj1)).setPadding(0, j, 0, i1);
        obj1 = errorText;
        j = minErrorMsgId;
        s1 = ReminderUtils.constructTimeIntervalString(requireContext().getResources(), minDurationInMinutes);
        ((TextView) (obj1)).setText(requireContext().getResources().getString(j, new Object[] {
            s1
        }));
        errorText.setVisibility(0);
        if (doneButton != null)
        {
            doneButton.setEnabled(false);
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        EditText edittext = hourText;
        int k = edittext.getPaddingTop();
        int j1 = edittext.getPaddingBottom();
        edittext.setBackgroundResource(0x7f020103);
        edittext.setPadding(0, k, 0, j1);
        edittext = minuteText;
        k = edittext.getPaddingTop();
        j1 = edittext.getPaddingBottom();
        edittext.setBackgroundResource(0x7f020103);
        edittext.setPadding(0, k, 0, j1);
        errorText.setVisibility(4);
        if (doneButton != null)
        {
            doneButton.setEnabled(true);
            return;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public void afterTextChanged(Editable editable)
    {
        int i;
        boolean flag;
        flag = false;
        i = 0;
        if (editable.hashCode() != hourText.getEditableText().hashCode()) goto _L2; else goto _L1
_L1:
        editable = hourText.getText().toString();
        if (!TextUtils.isEmpty(editable))
        {
            i = Integer.parseInt(editable);
        }
        hours = i;
_L6:
        updateErrorView();
_L4:
        return;
_L2:
        if (editable.hashCode() != minuteText.getEditableText().hashCode()) goto _L4; else goto _L3
_L3:
        editable = minuteText.getText().toString();
        int j;
        if (TextUtils.isEmpty(editable))
        {
            j = ((flag) ? 1 : 0);
        } else
        {
            j = Integer.parseInt(editable);
        }
        minutes = j;
        if (minutes > 60)
        {
            j = hours * 60 + minutes;
            hours = j / 60;
            minutes = j % 60;
            hourText.setText(Integer.toString(hours));
            minuteText.setText(Integer.toString(minutes));
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public void onCancel(DialogInterface dialoginterface)
    {
        if (listener != null)
        {
            listener.onCustomDurationDialogCancel();
        }
        super.onCancel(dialoginterface);
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        Object obj = null;
        View view;
        int i;
        int j;
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        view = LayoutInflater.from(bundle).inflate(0x7f050035, null);
        i = getArguments().getInt("duration_in_minutes");
        hours = i / 60;
        minutes = i % 60;
        hourText = (EditText)view.findViewById(0x7f100140);
        hourText.addTextChangedListener(this);
        hourText.setAccessibilityDelegate(new _cls1());
        minuteText = (EditText)view.findViewById(0x7f100141);
        minuteText.addTextChangedListener(this);
        minuteText.setAccessibilityDelegate(new _cls2());
        maxDurationInMinutes = getArguments().getInt("max_duration_in_minutes", 0x7fffffff);
        maxErrorMsgId = getArguments().getInt("max_duration_error_msg", 0x7f130146);
        minDurationInMinutes = getArguments().getInt("min_duration_in_minutes", 0x80000000);
        minErrorMsgId = getArguments().getInt("min_duration_error_msg", 0x7f130146);
        bundle = minuteText;
        i = bundle.getPaddingTop();
        j = bundle.getPaddingBottom();
        bundle.setBackgroundResource(0x7f020103);
        bundle.setPadding(0, i, 0, j);
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        if (!AccessibilityUtils.isAccessibilityEnabled(bundle))
        {
            minuteText.requestFocus();
        }
        errorText = (TextView)view.findViewById(0x7f100142);
        if (super.mHost == null)
        {
            bundle = obj;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        bundle = (new android.app.AlertDialog.Builder(bundle)).setTitle(0x7f1301fb).setView(view).setPositiveButton(0x104000a, new _cls4()).setNegativeButton(0x1040000, new _cls3()).create();
        bundle.getWindow().setSoftInputMode(4);
        if (super.mTarget instanceof OnCustomDurationSetListener)
        {
            listener = (OnCustomDurationSetListener)super.mTarget;
        }
        return bundle;
    }

    public void onDismiss(DialogInterface dialoginterface)
    {
        if (getDialog() != null)
        {
            getDialog().getWindow().setSoftInputMode(2);
        }
        super.onDismiss(dialoginterface);
    }

    public final void onStart()
    {
        super.onStart();
        AlertDialog alertdialog = (AlertDialog)getDialog();
        if (alertdialog != null)
        {
            doneButton = alertdialog.getButton(-1);
        }
        hourText.setText(Integer.toString(hours));
        minuteText.setText(Integer.toString(minutes));
        minuteText.setSelection(minuteText.getText().toString().length(), minuteText.getText().toString().length());
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }


    private class _cls1 extends android.view.View.AccessibilityDelegate
    {

        private final CustomDurationDialog this$0;

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfo);
            accessibilitynodeinfo.setText(requireContext().getResources().getQuantityString(0x7f120004, hours, new Object[] {
                Integer.valueOf(hours)
            }));
        }

        _cls1()
        {
            this$0 = CustomDurationDialog.this;
            super();
        }
    }


    private class _cls2 extends android.view.View.AccessibilityDelegate
    {

        private final CustomDurationDialog this$0;

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfo);
            accessibilitynodeinfo.setText(requireContext().getResources().getQuantityString(0x7f120005, minutes, new Object[] {
                Integer.valueOf(minutes)
            }));
        }

        _cls2()
        {
            this$0 = CustomDurationDialog.this;
            super();
        }
    }


    private class _cls4
        implements android.content.DialogInterface.OnClickListener
    {

        private final CustomDurationDialog this$0;

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            if (listener != null)
            {
                dialoginterface = listener;
                CustomDurationDialog customdurationdialog = CustomDurationDialog.this;
                i = customdurationdialog.hours;
                dialoginterface.onCustomDurationSet(customdurationdialog.minutes + i * 60);
            }
        }

        _cls4()
        {
            this$0 = CustomDurationDialog.this;
            super();
        }
    }


    private class _cls3
        implements android.content.DialogInterface.OnClickListener
    {

        private final CustomDurationDialog this$0;

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            onCancel(dialoginterface);
        }

        _cls3()
        {
            this$0 = CustomDurationDialog.this;
            super();
        }
    }

}
