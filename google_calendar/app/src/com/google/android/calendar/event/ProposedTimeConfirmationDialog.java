// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.Utils;

public final class ProposedTimeConfirmationDialog extends DialogFragment
{

    public OnProposedTimeConfirmListener listener;

    public ProposedTimeConfirmationDialog()
    {
    }

    public final void onCancel(DialogInterface dialoginterface)
    {
        if (listener != null)
        {
            listener.onProposedTimeCancel();
        }
        super.onCancel(dialoginterface);
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        Object obj = null;
        View view;
        TextView textview;
        TextView textview1;
        String s;
        String s1;
        String s2;
        long l;
        long l1;
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        view = LayoutInflater.from(bundle).inflate(0x7f050136, null);
        textview = (TextView)view.findViewById(0x7f10030f);
        textview1 = (TextView)view.findViewById(0x7f100310);
        textview.setOnClickListener(new _cls1());
        l = getArguments().getLong("proposed_start_time");
        l1 = getArguments().getLong("proposed_end_time");
        s = getArguments().getString("proposed_note");
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        s1 = Utils.tZUtils.formatDateRange(bundle, l, l1, 18);
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        s2 = Utils.tZUtils.formatDateRange(bundle, l, l1, 1);
        if (TextUtils.isEmpty(s))
        {
            bundle = requireContext().getResources().getString(0x7f1303c5);
        } else
        {
            bundle = requireContext().getResources().getString(0x7f1303c6);
        }
        textview1.setText(bundle);
        textview1.setOnClickListener(new _cls2());
        textview.setText(requireContext().getResources().getString(0x7f1303c4, new Object[] {
            s1, s2
        }));
        bundle = DateTimeFormatHelper.instance;
        if (bundle == null)
        {
            throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
        }
        textview.setText(((DateTimeFormatHelper)bundle).getDateRangeText(l, l1, 0x18013));
        if (super.mHost == null)
        {
            bundle = obj;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        return (new android.app.AlertDialog.Builder(bundle)).setTitle(0x7f1303c7).setView(view).setPositiveButton(0x104000a, new _cls4()).setNegativeButton(0x1040000, new _cls3()).create();
    }

    private class OnProposedTimeConfirmListener
    {

        public abstract void onProposedNoteEditRequest();

        public abstract void onProposedTimeCancel();

        public abstract void onProposedTimeConfirm();

        public abstract void onProposedTimeEditRequest();
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final ProposedTimeConfirmationDialog this$0;

        public final void onClick(View view)
        {
            if (listener != null)
            {
                listener.onProposedTimeEditRequest();
            }
            getDialog().dismiss();
        }

        _cls1()
        {
            this$0 = ProposedTimeConfirmationDialog.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        private final ProposedTimeConfirmationDialog this$0;

        public final void onClick(View view)
        {
            if (listener != null)
            {
                listener.onProposedNoteEditRequest();
            }
            getDialog().dismiss();
        }

        _cls2()
        {
            this$0 = ProposedTimeConfirmationDialog.this;
            super();
        }
    }


    private class _cls4
        implements android.content.DialogInterface.OnClickListener
    {

        private final ProposedTimeConfirmationDialog this$0;

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            if (listener != null)
            {
                listener.onProposedTimeConfirm();
            }
        }

        _cls4()
        {
            this$0 = ProposedTimeConfirmationDialog.this;
            super();
        }
    }


    private class _cls3
        implements android.content.DialogInterface.OnClickListener
    {

        private final ProposedTimeConfirmationDialog this$0;

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            onCancel(dialoginterface);
        }

        _cls3()
        {
            this$0 = ProposedTimeConfirmationDialog.this;
            super();
        }
    }

}
