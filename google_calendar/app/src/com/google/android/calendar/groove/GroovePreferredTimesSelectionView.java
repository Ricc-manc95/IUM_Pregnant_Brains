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

public final class GroovePreferredTimesSelectionView extends GrooveScheduleView
{

    public GroovePreferredTimesSelectionView(Context context)
    {
        super(context);
        LayoutInflater.from(context).inflate(0x7f050085, this);
        setOrientation(1);
        mTitleView = (TextView)findViewById(0x7f1001e6);
        ((Button)findViewById(0x7f1001ea)).setOnClickListener(new _cls1());
        ((Button)findViewById(0x7f1001e7)).setOnClickListener(new _cls1());
        ((Button)findViewById(0x7f1001e8)).setOnClickListener(new _cls1());
        ((Button)findViewById(0x7f1001e9)).setOnClickListener(new _cls1());
    }

    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final GroovePreferredTimesSelectionView this$0;
        private final int val$preferredTimes;

        public final void onClick(View view)
        {
            if (getContext() instanceof Listener)
            {
                ((Listener)getContext()).onPreferredTimesSelectionComplete(preferredTimes);
            }
        }

        _cls1()
        {
            this$0 = GroovePreferredTimesSelectionView.this;
            preferredTimes = i;
            super();
        }

        private class Listener
        {

            public abstract void onPreferredTimesSelectionComplete(int i);
        }

    }

}
