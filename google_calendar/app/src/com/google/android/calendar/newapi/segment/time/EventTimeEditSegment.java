// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.time;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.calendar.common.view.NinjaSwitch;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public class EventTimeEditSegment extends EditSegment
    implements android.view.View.OnClickListener
{
    public static interface Listener
    {

        public abstract void onAddEndTimeClicked();

        public abstract void onAllDayToggled(boolean flag);

        public abstract void onEndDateClicked();

        public abstract void onEndTimeClicked();

        public abstract void onMoreOptionsClicked();

        public abstract void onStartDateClicked();

        public abstract void onStartTimeClicked();
    }


    public NinjaSwitch allDaySwitch;
    public TextTileView endDateTile;
    public TextView endTimeTextView;
    public TextTileView endTimeUnspecifiedTile;
    public TextTileView moreOptionsTile;
    public TextTileView startDateTile;
    public TextView startTimeTextView;
    public ImageView timeIcon;

    public EventTimeEditSegment(Context context, AttributeSet attributeset)
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
            if (view.equals(endTimeTextView))
            {
                ((InputMethodManager)view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                ((Listener)super.mListener).onEndTimeClicked();
                return;
            }
            if (0x7f100272 == view.getId())
            {
                ((InputMethodManager)view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                ((Listener)super.mListener).onEndDateClicked();
                return;
            }
            if (0x7f100298 == view.getId())
            {
                allDaySwitch.toggle();
                return;
            }
            if (0x7f100273 == view.getId())
            {
                ((InputMethodManager)view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                ((Listener)super.mListener).onAddEndTimeClicked();
                return;
            }
            if (0x7f100274 == view.getId())
            {
                ((InputMethodManager)view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                ((Listener)super.mListener).onMoreOptionsClicked();
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
        endDateTile = (TextTileView)findViewById(0x7f100272);
        endDateTile.setOnClickListener(this);
        endTimeTextView = (TextView)((TileView) (endDateTile)).rightActionView;
        endTimeTextView.setOnClickListener(this);
        int i = ContextCompat.getColor(getContext(), 0x7f0d00ab);
        endTimeUnspecifiedTile = (TextTileView)findViewById(0x7f100273);
        endTimeUnspecifiedTile.setOnClickListener(this);
        endTimeUnspecifiedTile.setPrimaryTextColor(i);
        moreOptionsTile = (TextTileView)findViewById(0x7f100274);
        moreOptionsTile.setOnClickListener(this);
        moreOptionsTile.setPrimaryTextColor(i);
        TextTileView texttileview = (TextTileView)findViewById(0x7f100298);
        texttileview.setOnClickListener(this);
        timeIcon = texttileview.getIcon();
        allDaySwitch = (NinjaSwitch)((TileView) (texttileview)).rightActionView;
        class .Lambda._cls0
            implements android.widget.CompoundButton.OnCheckedChangeListener
        {

            private final EventTimeEditSegment arg$1;

            public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                ((Listener)((EditSegment) (arg$1)).mListener).onAllDayToggled(flag);
            }

            .Lambda._cls0()
            {
                arg$1 = EventTimeEditSegment.this;
            }
        }

        allDaySwitch.setOnCheckedChangeListener(new .Lambda._cls0());
    }
}
