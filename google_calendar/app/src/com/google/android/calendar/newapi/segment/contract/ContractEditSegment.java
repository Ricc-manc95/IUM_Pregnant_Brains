// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.contract;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public class ContractEditSegment extends EditSegment
    implements android.view.View.OnClickListener
{
    public static interface Listener
    {

        public abstract void onDurationClicked();

        public abstract void onFrequencyClicked();

        public abstract void onPreferredTimeClicked();
    }


    public TextTileView buttonDuration;
    public TextTileView buttonFrequency;
    public TextTileView buttonPreferredTimes;

    public ContractEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public void onClick(View view)
    {
        ((InputMethodManager)view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        if (view.getId() == 0x7f10025f)
        {
            ((Listener)mListener).onFrequencyClicked();
        } else
        {
            if (view.getId() == 0x7f100260)
            {
                ((Listener)mListener).onDurationClicked();
                return;
            }
            if (view.getId() == 0x7f100261)
            {
                ((Listener)mListener).onPreferredTimeClicked();
                return;
            }
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        buttonFrequency = (TextTileView)findViewById(0x7f10025f);
        buttonFrequency.setOnClickListener(this);
        buttonDuration = (TextTileView)findViewById(0x7f100260);
        buttonDuration.setOnClickListener(this);
        buttonPreferredTimes = (TextTileView)findViewById(0x7f100261);
        buttonPreferredTimes.setOnClickListener(this);
    }
}
