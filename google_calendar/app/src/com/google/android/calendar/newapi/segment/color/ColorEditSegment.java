// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.color;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.google.android.calendar.common.drawable.ColorCircle;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public class ColorEditSegment extends EditSegment
    implements android.view.View.OnClickListener
{
    public static interface Listener
    {

        public abstract void onColorClicked();
    }


    public ColorCircle colorCircle;
    public TextTileView tile;

    public ColorEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public void onClick(View view)
    {
        ((InputMethodManager)view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        ((Listener)mListener).onColorClicked();
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        tile = (TextTileView)findViewById(0x7f100252);
        tile.setOnClickListener(this);
        colorCircle = new ColorCircle(getResources(), 0x7f0e0137);
        tile.setIconDrawable(colorCircle);
    }
}
