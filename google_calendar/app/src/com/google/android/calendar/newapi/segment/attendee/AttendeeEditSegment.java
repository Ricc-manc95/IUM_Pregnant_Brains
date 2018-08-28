// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public class AttendeeEditSegment extends EditSegment
    implements android.view.View.OnClickListener
{
    public static interface Listener
    {

        public abstract void onAttendeeTileClicked();

        public abstract void onFindTimeTileClicked();
    }


    public TextTileView attendeeTile;
    public TextTileView findTimeTile;

    public AttendeeEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public void onClick(View view)
    {
        if (view.getId() == 0x7f10024b)
        {
            ((Listener)super.mListener).onAttendeeTileClicked();
        } else
        if (view.getId() == 0x7f10024c)
        {
            ((Listener)super.mListener).onFindTimeTileClicked();
            return;
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        attendeeTile = (TextTileView)findViewById(0x7f10024b);
        attendeeTile.setOnClickListener(this);
        findTimeTile = (TextTileView)findViewById(0x7f10024c);
        findTimeTile.setOnClickListener(this);
        TextView textview = findTimeTile.primaryLine;
        Typeface typeface;
        if (Material.robotoMedium != null)
        {
            typeface = Material.robotoMedium;
        } else
        {
            typeface = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = typeface;
        }
        textview.setTypeface(typeface);
    }
}
