// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.time;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public class GrooveTimeEditSegment extends EditSegment
    implements android.view.View.OnClickListener
{
    public static interface Listener
    {

        public abstract void onDateClicked();

        public abstract void onTimeClicked();
    }


    public TextTileView tile;

    public GrooveTimeEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public void onClick(View view)
    {
        ((InputMethodManager)view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        if (0x7f10029f == view.getId())
        {
            ((Listener)super.mListener).onTimeClicked();
            return;
        } else
        {
            ((Listener)super.mListener).onDateClicked();
            return;
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        tile = (TextTileView)findViewById(0x7f100175);
        tile.setOnClickListener(this);
        ((TileView) (tile)).rightActionView.setOnClickListener(this);
    }
}
