// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public class CalendarEditSegment extends EditSegment
    implements android.view.View.OnClickListener
{
    public static interface Listener
    {

        public abstract void onCalendarClicked();
    }


    public TextTileView textView;

    public CalendarEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public void onClick(View view)
    {
        ((Listener)mListener).onCalendarClicked();
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        textView = (TextTileView)findViewById(0x7f100251);
        textView.setOnClickListener(this);
    }
}
