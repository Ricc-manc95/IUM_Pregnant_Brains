// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.time;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.google.android.calendar.common.view.NinjaSwitch;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public class ReminderTimeEditSegment extends EditSegment
    implements android.view.View.OnClickListener
{
    public static interface Listener
    {

        public abstract void onAllDayToggled(boolean flag);

        public abstract void onStartDateClicked();

        public abstract void onStartTimeClicked();
    }


    public NinjaSwitch allDaySwitch;
    public TextTileView startDateTile;
    public TextView startTimeTextView;

    public ReminderTimeEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public void onClick(View view)
    {
        if (view.equals(startTimeTextView))
        {
            ((InputMethodManager)view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
            ((Listener)super.mListener).onStartTimeClicked();
        } else
        {
            if (0x7f100299 == view.getId())
            {
                ((InputMethodManager)view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                ((Listener)super.mListener).onStartDateClicked();
                return;
            }
            if (0x7f100298 == view.getId())
            {
                allDaySwitch.toggle();
                return;
            }
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        startDateTile = (TextTileView)findViewById(0x7f100299);
        startDateTile.setOnClickListener(this);
        startTimeTextView = (TextView)((TileView) (startDateTile)).rightActionView;
        startTimeTextView.setOnClickListener(this);
        TextTileView texttileview = (TextTileView)findViewById(0x7f100298);
        texttileview.setOnClickListener(this);
        allDaySwitch = (NinjaSwitch)((TileView) (texttileview)).rightActionView;
        class .Lambda._cls0
            implements android.widget.CompoundButton.OnCheckedChangeListener
        {

            private final ReminderTimeEditSegment arg$1;

            public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                ((Listener)((EditSegment) (arg$1)).mListener).onAllDayToggled(flag);
            }

            .Lambda._cls0()
            {
                arg$1 = ReminderTimeEditSegment.this;
            }
        }

        allDaySwitch.setOnCheckedChangeListener(new .Lambda._cls0());
    }
}
