// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.event.conference.AccessCode;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.event:
//            ConferenceCallUtils

public class AccessCodePickerDialog extends DialogFragment
{
    final class AccessCodeAdapter extends BaseAdapter
    {

        public int selectedItemIndex;
        private final AccessCodePickerDialog this$0;

        public final int getCount()
        {
            return accessCodes.size() + 1;
        }

        public final Object getItem(int i)
        {
            if (i < accessCodes.size())
            {
                return (AccessCode)accessCodes.get(i);
            } else
            {
                return null;
            }
        }

        public final long getItemId(int i)
        {
            return (long)i;
        }

        public final View getView(int i, View view, ViewGroup viewgroup)
        {
            CheckedTextView checkedtextview;
            viewgroup = view;
            if (view == null)
            {
                viewgroup = inflater.inflate(0x7f050060, null);
            }
            checkedtextview = (CheckedTextView)viewgroup.findViewById(0x7f100042);
            if (i >= accessCodes.size())
            {
                break MISSING_BLOCK_LABEL_219;
            }
            view = (AccessCode)accessCodes.get(i);
            ((AccessCode) (view)).type;
            JVM INSTR tableswitch 2 3: default 88
        //                       2 165
        //                       3 192;
               goto _L1 _L2 _L3
_L1:
            view = ((AccessCode) (view)).number;
_L4:
            SpannableString spannablestring = new SpannableString(view);
            spannablestring.setSpan((new android.text.style.TtsSpan.DigitsBuilder(view)).build(), 0, spannablestring.length(), 33);
            checkedtextview.setText(spannablestring);
            checkedtextview.setContentDescription(spannablestring);
_L5:
            boolean flag;
            if (selectedItemIndex == i)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            checkedtextview.setChecked(flag);
            return viewgroup;
_L2:
            view = resources.getString(0x7f130049, new Object[] {
                ((AccessCode) (view)).number
            });
              goto _L4
_L3:
            view = resources.getString(0x7f13004b, new Object[] {
                ((AccessCode) (view)).number
            });
              goto _L4
            view = resources.getString(0x7f13004a);
            checkedtextview.setText(view);
            checkedtextview.setContentDescription(view);
              goto _L5
        }

        AccessCodeAdapter()
        {
            this$0 = AccessCodePickerDialog.this;
            super();
            selectedItemIndex = 0;
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/event/AccessCodePickerDialog);
    public ArrayList accessCodes;
    public AccessCodeAdapter adapter;
    public String conferenceNumber;
    public LayoutInflater inflater;
    public Resources resources;

    public AccessCodePickerDialog()
    {
    }

    public void onCancel(DialogInterface dialoginterface)
    {
        super.onCancel(dialoginterface);
        if (super.mHost == null)
        {
            dialoginterface = null;
        } else
        {
            dialoginterface = (FragmentActivity)super.mHost.mActivity;
        }
        ConferenceCallUtils.logAction(dialoginterface, "join_conference", "cancel");
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        Object obj = null;
        conferenceNumber = getArguments().getString("key_conference_number");
        accessCodes = getArguments().getParcelableArrayList("key_access_codes");
        adapter = new AccessCodeAdapter();
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        inflater = LayoutInflater.from(bundle);
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        resources = bundle.getResources();
        if (super.mHost == null)
        {
            bundle = obj;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        bundle = new android.app.AlertDialog.Builder(bundle);
        bundle.setTitle(resources.getString(0x7f13004d)).setSingleChoiceItems(adapter, adapter.selectedItemIndex, new _cls3()).setPositiveButton(0x7f13004c, new _cls2()).setNegativeButton(0x1040000, new _cls1());
        return bundle.create();
    }

    public void onDismiss(DialogInterface dialoginterface)
    {
        super.onDismiss(dialoginterface);
        if (super.mHost == null)
        {
            dialoginterface = null;
        } else
        {
            dialoginterface = (FragmentActivity)super.mHost.mActivity;
        }
        ConferenceCallUtils.logAction(dialoginterface, "join_conference", "cancel");
    }


    private class _cls3
        implements android.content.DialogInterface.OnClickListener
    {

        private final AccessCodePickerDialog this$0;

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            dialoginterface = adapter;
            dialoginterface.selectedItemIndex = i;
            dialoginterface.notifyDataSetChanged();
        }

        _cls3()
        {
            this$0 = AccessCodePickerDialog.this;
            super();
        }
    }


    private class _cls2
        implements android.content.DialogInterface.OnClickListener
    {

        private final AccessCodePickerDialog this$0;

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            String s = null;
            dialoginterface = AccessCodePickerDialog.this;
            PhoneUtil phoneutil;
            String s1;
            AccessCodePickerDialog accesscodepickerdialog;
            if (((Fragment) (dialoginterface)).mHost == null)
            {
                dialoginterface = null;
            } else
            {
                dialoginterface = (FragmentActivity)((Fragment) (dialoginterface)).mHost.mActivity;
            }
            phoneutil = (PhoneUtil)dialoginterface;
            s1 = conferenceNumber;
            accesscodepickerdialog = AccessCodePickerDialog.this;
            if (accesscodepickerdialog.adapter.selectedItemIndex < accesscodepickerdialog.accessCodes.size())
            {
                s = ((AccessCode)accesscodepickerdialog.accessCodes.get(accesscodepickerdialog.adapter.selectedItemIndex)).number;
            }
            phoneutil.makeCall(ConferenceCallUtils.buildUri(s1, s));
            if (adapter.selectedItemIndex == adapter.getCount() - 1)
            {
                ConferenceCallUtils.logAction(dialoginterface, "join_conference", "without_passcode");
                return;
            } else
            {
                ConferenceCallUtils.logAction(dialoginterface, "join_conference", "with_passcode");
                return;
            }
        }

        _cls2()
        {
            this$0 = AccessCodePickerDialog.this;
            super();
        }
    }


    private class _cls1
        implements android.content.DialogInterface.OnClickListener
    {

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            dialoginterface.cancel();
        }

        _cls1()
        {
        }
    }

}
