// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.belong;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import com.google.android.calendar.common.view.NinjaSwitch;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public class BelongIntegrationEditSegment extends EditSegment
    implements android.view.View.OnClickListener, android.widget.CompoundButton.OnCheckedChangeListener
{
    public static interface Listener
    {

        public abstract void onIntegrationToggled(boolean flag);

        public abstract void onLearnMoreClicked();
    }


    public NinjaSwitch mSwitch;

    public BelongIntegrationEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        ((Listener)mListener).onIntegrationToggled(flag);
    }

    public void onClick(View view)
    {
        ((Listener)mListener).onLearnMoreClicked();
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mSwitch = new NinjaSwitch(getContext());
        mSwitch.setOnCheckedChangeListener(this);
        TextTileView texttileview = (TextTileView)findViewById(0x7f100250);
        NinjaSwitch ninjaswitch = mSwitch;
        texttileview.rightActionView = ninjaswitch;
        texttileview.addView(ninjaswitch);
        if (((TileView) (texttileview)).rightActionView != null && !texttileview.hasOnClickListeners())
        {
            texttileview.treatAsButton(true);
            texttileview.setOnClickListener(new com.google.android.calendar.tiles.view.TileView..Lambda._cls1(texttileview));
        }
        findViewById(0x7f1001c3).setOnClickListener(this);
    }
}
