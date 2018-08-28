// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.room;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public class RoomEditSegment extends EditSegment
    implements android.view.View.OnClickListener
{
    public static interface Listener
    {

        public abstract void onEditRoomsClicked();
    }


    public TextTileView addRoomTile;
    public TextTileView roomsTile;

    public RoomEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public void onClick(View view)
    {
        ((Listener)super.mListener).onEditRoomsClicked();
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        roomsTile = (TextTileView)findViewById(0x7f100290);
        roomsTile.setOnClickListener(this);
        addRoomTile = (TextTileView)findViewById(0x7f100291);
        addRoomTile.setOnClickListener(this);
    }

    public void setPaddingBottom(int i)
    {
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), i);
    }
}
