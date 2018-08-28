// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.assist;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public class AssistInformationEditSegment extends EditSegment
{
    public static interface Listener
    {

        public abstract void onAssistTileClicked();
    }


    public TextTileView tile;

    public AssistInformationEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        tile = (TextTileView)findViewById(0x7f100175);
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final AssistInformationEditSegment arg$1;

            public final void onClick(View view)
            {
                ((Listener)((EditSegment) (arg$1)).mListener).onAssistTileClicked();
            }

            .Lambda._cls0()
            {
                arg$1 = AssistInformationEditSegment.this;
            }
        }

        tile.setOnClickListener(new .Lambda._cls0());
    }
}
