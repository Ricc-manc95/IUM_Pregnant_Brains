// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.assist;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import java.util.ArrayList;
import java.util.List;

public class AssistActionDialog extends DialogFragment
    implements android.content.DialogInterface.OnClickListener
{
    public static interface Listener
    {

        public abstract void executeAssistAction();

        public abstract void removeAssist();
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/assist/AssistActionDialog);

    public AssistActionDialog()
    {
    }

    public static DialogFragment createDialog(String s, Fragment fragment)
    {
        Bundle bundle = new Bundle();
        bundle.putString("action", s);
        s = new AssistActionDialog();
        s.setArguments(bundle);
        s.setTargetFragment(fragment, 0);
        return s;
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface = (Listener)super.mTarget;
        boolean flag;
        if (!TextUtils.isEmpty(getArguments().getString("action")))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && i == 0)
        {
            dialoginterface.executeAssistAction();
            return;
        } else
        {
            dialoginterface.removeAssist();
            return;
        }
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        ArrayList arraylist = new ArrayList();
        boolean flag;
        if (!TextUtils.isEmpty(getArguments().getString("action")))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            arraylist.add(getArguments().getString("action"));
        }
        arraylist.add(requireContext().getResources().getString(0x7f1303e5));
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        return (new android.app.AlertDialog.Builder(bundle)).setItems((CharSequence[])arraylist.toArray(new CharSequence[0]), this).create();
    }

}
