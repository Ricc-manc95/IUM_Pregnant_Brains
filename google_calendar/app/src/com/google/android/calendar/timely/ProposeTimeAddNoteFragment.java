// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.KeyboardManipulator;
import com.google.android.calendar.utils.ColorUtils;
import com.google.android.calendar.utils.statusbar.StatusbarAnimatorCompat;
import java.util.Set;

public final class ProposeTimeAddNoteFragment extends Fragment
{

    private Button doneButton;
    public long endMillis;
    private int eventColor;
    private boolean hasProposedTime;
    private KeyboardManipulator keyboardManipulator;
    public Listener listener;
    public EditText note;
    public long startMillis;
    private TextView subtitle;

    public ProposeTimeAddNoteFragment()
    {
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        viewgroup = layoutinflater.inflate(0x7f050135, viewgroup, false);
        eventColor = getArguments().getInt("event_color");
        boolean flag;
        if (getArguments().keySet().contains("start_millis") && getArguments().keySet().contains("end_millis"))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hasProposedTime = flag;
        if (hasProposedTime)
        {
            startMillis = getArguments().getLong("start_millis");
            endMillis = getArguments().getLong("end_millis");
        }
        layoutinflater = (Toolbar)viewgroup.findViewById(0x7f100113);
        layoutinflater.setBackgroundColor(eventColor);
        ((TextView)layoutinflater.findViewById(0x7f100047)).setText(0x7f130095);
        subtitle = (TextView)viewgroup.findViewById(0x7f10030e);
        bundle = DateTimeFormatHelper.instance;
        if (bundle == null)
        {
            throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
        }
        bundle = ((DateTimeFormatHelper)bundle).getDateRangeText(startMillis, endMillis, 19);
        subtitle.setText(bundle);
        bundle = new _cls1();
        layoutinflater.ensureNavButtonView();
        ((Toolbar) (layoutinflater)).mNavButtonView.setOnClickListener(bundle);
        doneButton = (Button)layoutinflater.findViewById(0x7f10030c);
        doneButton.setOnClickListener(new _cls2());
        note = (EditText)viewgroup.findViewById(0x7f100307);
        layoutinflater = getArguments().getString("note");
        if (!TextUtils.isEmpty(layoutinflater))
        {
            note.setText(layoutinflater);
        }
        if (!hasProposedTime)
        {
            ((TextView)viewgroup.findViewById(0x7f100047)).setText(requireContext().getResources().getText(0x7f130095));
            viewgroup.findViewById(0x7f10030d).setVisibility(8);
            updateDoneButtonVisibility();
            doneButton.setText(requireContext().getResources().getText(0x7f130093));
            note.setHint(0x7f130096);
            note.addTextChangedListener(new _cls3());
            subtitle.setVisibility(8);
        }
        int i;
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        keyboardManipulator = new KeyboardManipulator(layoutinflater, note);
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        layoutinflater = layoutinflater.getWindow();
        i = eventColor;
        layoutinflater = StatusbarAnimatorCompat.createInstance(layoutinflater);
        layoutinflater.setStatusbarColor(ColorUtils.blend(i, 0x33000000));
        layoutinflater.setLightStatusbar(false);
        return viewgroup;
    }

    public final void onStart()
    {
        super.onStart();
        note.requestFocus();
        KeyboardManipulator keyboardmanipulator = keyboardManipulator;
        keyboardmanipulator.showPendingSince = SystemClock.uptimeMillis();
        keyboardmanipulator.showIfNecessary();
    }

    public final void onStop()
    {
        super.onStop();
        keyboardManipulator.requestHide();
    }

    final void updateDoneButtonVisibility()
    {
        if (!hasProposedTime)
        {
            Button button = doneButton;
            byte byte0;
            if (TextUtils.isEmpty(note.getText()))
            {
                byte0 = 4;
            } else
            {
                byte0 = 0;
            }
            button.setVisibility(byte0);
        }
    }

    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final ProposeTimeAddNoteFragment this$0;

        public final void onClick(View view)
        {
            if (listener != null)
            {
                listener._mth51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL0SJFE1NN6PAKD5MMAGB4CH76UT358PP62PRDCLN78EP9AO______0();
            }
        }

        _cls1()
        {
            this$0 = ProposeTimeAddNoteFragment.this;
            super();
        }

        private class Listener
        {

            public abstract void onCancelled$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL0SJFE1NN6PAKD5MMAGB4CH76UT358PP62PRDCLN78EP9AO______0();

            public abstract void onNoteAdded$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL0SJFE1NN6PAKD5MMAGB4CH76UT358PP62PRDCLN78EQA9966KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0(String s);
        }

    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        private final ProposeTimeAddNoteFragment this$0;

        public final void onClick(View view)
        {
            if (listener != null)
            {
                view = listener;
                long l = startMillis;
                l = endMillis;
                view._mth51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL0SJFE1NN6PAKD5MMAGB4CH76UT358PP62PRDCLN78EQA9966KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0(note.getText().toString());
            }
        }

        _cls2()
        {
            this$0 = ProposeTimeAddNoteFragment.this;
            super();
        }
    }


    private class _cls3
        implements TextWatcher
    {

        private final ProposeTimeAddNoteFragment this$0;

        public final void afterTextChanged(Editable editable)
        {
            updateDoneButtonVisibility();
        }

        public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        _cls3()
        {
            this$0 = ProposeTimeAddNoteFragment.this;
            super();
        }
    }

}
