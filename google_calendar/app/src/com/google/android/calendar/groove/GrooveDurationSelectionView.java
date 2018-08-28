// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveScheduleView

public final class GrooveDurationSelectionView extends GrooveScheduleView
{

    public GrooveDurationSelectionView(Context context)
    {
        super(context);
        LayoutInflater.from(context).inflate(0x7f050081, this);
        setOrientation(1);
        mTitleView = (TextView)findViewById(0x7f1001cb);
        ((Button)findViewById(0x7f1001cd)).setOnClickListener(new _cls1());
        ((Button)findViewById(0x7f1001ce)).setOnClickListener(new _cls1());
        ((Button)findViewById(0x7f1001cf)).setOnClickListener(new _cls1());
        ((Button)findViewById(0x7f1001d0)).setOnClickListener(new _cls1());
        ((Button)findViewById(0x7f1001d2)).setOnClickListener(new _cls1());
        ((Button)findViewById(0x7f1001d3)).setOnClickListener(new _cls1());
        ((Button)findViewById(0x7f1001d4)).setOnClickListener(new _cls1());
        ((Button)findViewById(0x7f1001d5)).setOnClickListener(new _cls1());
        ((Button)findViewById(0x7f1001d6)).setOnClickListener(new _cls1());
    }

    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final GrooveDurationSelectionView this$0;
        private final int val$duration;

        public final void onClick(View view)
        {
            if (getContext() instanceof Listener)
            {
                ((Listener)getContext()).onDurationSelectionComplete(duration);
            }
        }

        _cls1()
        {
            this$0 = GrooveDurationSelectionView.this;
            duration = i;
            super();
        }

        private class Listener
        {

            public abstract void onDurationSelectionComplete(int i);
        }

    }

}
