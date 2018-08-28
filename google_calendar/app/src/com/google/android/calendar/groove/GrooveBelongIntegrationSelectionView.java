// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveScheduleView

public final class GrooveBelongIntegrationSelectionView extends GrooveScheduleView
{

    public GrooveBelongIntegrationSelectionView(Context context)
    {
        super(context);
        LayoutInflater.from(context).inflate(0x7f05007e, this);
        setOrientation(1);
        mTitleView = (TextView)findViewById(0x7f1001c2);
        findViewById(0x7f1001c3).setOnClickListener(new _cls1());
        ((Button)findViewById(0x7f1001c5)).setOnClickListener(new _cls2());
        ((Button)findViewById(0x7f1001c4)).setOnClickListener(new _cls2());
    }

    public final boolean shouldChangeColor(View view)
    {
        if (view.getId() == 0x7f1001c5)
        {
            return false;
        } else
        {
            return super.shouldChangeColor(view);
        }
    }

    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final GrooveBelongIntegrationSelectionView this$0;

        public final void onClick(View view)
        {
            BelongUtils.onLearnMoreLinkClicked(getContext(), "creation_wizard");
        }

        _cls1()
        {
            this$0 = GrooveBelongIntegrationSelectionView.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        private final GrooveBelongIntegrationSelectionView this$0;
        private final int val$status;

        public final void onClick(View view)
        {
            if (getContext() instanceof Listener)
            {
                ((Listener)getContext()).onBelongIntegrationSelectionComplete(status);
            }
        }

        _cls2()
        {
            this$0 = GrooveBelongIntegrationSelectionView.this;
            status = i;
            super();
        }

        private class Listener
        {

            public abstract void onBelongIntegrationSelectionComplete(int i);
        }

    }

}
