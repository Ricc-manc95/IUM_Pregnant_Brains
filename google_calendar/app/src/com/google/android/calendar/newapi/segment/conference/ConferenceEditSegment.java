// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.calendar.common.view.NinjaSwitch;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public class ConferenceEditSegment extends EditSegment
{
    public static interface Listener
    {

        public abstract void onConferenceToggled(boolean flag);
    }


    public NinjaSwitch conferenceToggle;
    public TextTileView tile;

    public ConferenceEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        tile = (TextTileView)findViewById(0x7f100258);
        conferenceToggle = new NinjaSwitch(getContext());
        class .Lambda._cls0
            implements android.widget.CompoundButton.OnCheckedChangeListener
        {

            private final ConferenceEditSegment arg$1;

            public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                ((Listener)((EditSegment) (arg$1)).mListener).onConferenceToggled(flag);
            }

            .Lambda._cls0()
            {
                arg$1 = ConferenceEditSegment.this;
            }
        }

        conferenceToggle.setOnCheckedChangeListener(new .Lambda._cls0());
        TextTileView texttileview = tile;
        NinjaSwitch ninjaswitch = conferenceToggle;
        texttileview.rightActionView = ninjaswitch;
        texttileview.addView(ninjaswitch);
        if (((TileView) (texttileview)).rightActionView == null || texttileview.hasOnClickListeners())
        {
            return;
        } else
        {
            texttileview.treatAsButton(true);
            texttileview.setOnClickListener(new com.google.android.calendar.tiles.view.TileView..Lambda._cls1(texttileview));
            return;
        }
    }
}
