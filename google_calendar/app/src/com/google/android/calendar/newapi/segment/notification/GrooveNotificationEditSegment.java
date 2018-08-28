// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.notification;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import com.google.android.calendar.common.view.NinjaSwitch;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public class GrooveNotificationEditSegment extends EditSegment
    implements android.view.View.OnClickListener, android.widget.CompoundButton.OnCheckedChangeListener
{
    public static interface Listener
    {

        public abstract void onNotificationClicked();

        public abstract void onRemoveNotificationClicked();

        public abstract void onSmartNotificationsToggled(boolean flag);
    }


    public TextTileView addNotificationTile;
    public TextTileView notificationTile;
    public NinjaSwitch smartNotificationSwitch;
    private TextTileView smartNotificationTile;

    public GrooveNotificationEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        ((Listener)mListener).onSmartNotificationsToggled(flag);
    }

    public void onClick(View view)
    {
        int i = view.getId();
        if (i == 0x7f10027f || i == 0x7f100270)
        {
            ((Listener)mListener).onNotificationClicked();
        } else
        {
            if (i == 0x7f1000d4)
            {
                ((Listener)mListener).onRemoveNotificationClicked();
                return;
            }
            if (i == 0x7f100280)
            {
                smartNotificationSwitch.toggle();
                return;
            }
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        addNotificationTile = (TextTileView)findViewById(0x7f100270);
        addNotificationTile.setOnClickListener(this);
        notificationTile = (TextTileView)findViewById(0x7f10027f);
        notificationTile.setOnClickListener(this);
        View view = LayoutInflater.from(getContext()).inflate(0x7f05013f, notificationTile, false);
        view.setContentDescription(getResources().getString(0x7f1303e8));
        Object obj = (ImageView)view.findViewById(0x7f1000d4);
        ((ImageView) (obj)).setBackgroundResource(0x7f0201d8);
        ((ImageView) (obj)).setOnClickListener(this);
        obj = notificationTile;
        obj.rightActionView = view;
        ((TileView) (obj)).addView(view);
        if (((TileView) (obj)).rightActionView != null && !((TileView) (obj)).hasOnClickListeners())
        {
            ((TileView) (obj)).treatAsButton(true);
            ((TileView) (obj)).setOnClickListener(new com.google.android.calendar.tiles.view.TileView..Lambda._cls1(((TileView) (obj))));
        }
        smartNotificationTile = (TextTileView)findViewById(0x7f100280);
        smartNotificationTile.setOnClickListener(this);
        view = ((TileView) (smartNotificationTile)).rightActionView;
        if (view == null)
        {
            throw new NullPointerException();
        } else
        {
            smartNotificationSwitch = (NinjaSwitch)view;
            smartNotificationSwitch.setOnCheckedChangeListener(this);
            return;
        }
    }
}
