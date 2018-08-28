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

public class GrooveFrequencySelectionView extends GrooveScheduleView
{
    public static interface Listener
    {

        public abstract void onFrequencyMoreOptionsClicked();

        public abstract void onFrequencySelectionComplete(int i, int j);
    }


    public GrooveFrequencySelectionView(Context context, int i)
    {
        boolean flag;
        flag = false;
        super(context);
        LayoutInflater.from(context).inflate(0x7f050083, this);
        setOrientation(1);
        setTransitionGroup(true);
        mTitleView = (TextView)findViewById(0x7f1001d7);
        findViewById(0x7f1001e2).setVisibility(0);
        switch (i)
        {
        default:
            i = 0;
            break;

        case 520: 
        case 522: 
        case 524: 
        case 769: 
        case 771: 
        case 772: 
        case 773: 
        case 774: 
        case 775: 
        case 776: 
        case 777: 
        case 778: 
        case 779: 
        case 1033: 
        case 1034: 
        case 1035: 
            break MISSING_BLOCK_LABEL_428;
        }
_L1:
        context = findViewById(0x7f1001d9);
        int j;
        if (i == 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (context != null)
        {
            class .Lambda._cls0
                implements android.view.View.OnClickListener
            {

                private final GrooveFrequencySelectionView arg$1;
                private final int arg$2;
                private final int arg$3;

                public final void onClick(View view)
                {
                    view = arg$1;
                    int k = arg$2;
                    int l = arg$3;
                    if (view.getContext() instanceof Listener)
                    {
                        ((Listener)view.getContext()).onFrequencySelectionComplete(k, l);
                    }
                }

            .Lambda._cls0(int i, int j)
            {
                arg$1 = GrooveFrequencySelectionView.this;
                arg$2 = i;
                arg$3 = j;
            }
            }

            class .Lambda._cls1
                implements android.view.View.OnClickListener
            {

                private final GrooveFrequencySelectionView arg$1;

                public final void onClick(View view)
                {
                    view = arg$1;
                    if (view.getContext() instanceof Listener)
                    {
                        ((Listener)view.getContext()).onFrequencyMoreOptionsClicked();
                    }
                }

            .Lambda._cls1()
            {
                arg$1 = GrooveFrequencySelectionView.this;
            }
            }

            if (j != 0)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            context.setVisibility(j);
        }
        context = findViewById(0x7f1001de);
        if (i == 1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (context != null)
        {
            if (i != 0)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 8;
            }
            context.setVisibility(i);
        }
        ((Button)findViewById(0x7f1001da)).setOnClickListener(new .Lambda._cls0(2, 1));
        ((Button)findViewById(0x7f1001db)).setOnClickListener(new .Lambda._cls0(2, 3));
        ((Button)findViewById(0x7f1001dc)).setOnClickListener(new .Lambda._cls0(2, 5));
        ((Button)findViewById(0x7f1001dd)).setOnClickListener(new .Lambda._cls0(2, 7));
        ((Button)findViewById(0x7f1001df)).setOnClickListener(new .Lambda._cls0(3, 1));
        ((Button)findViewById(0x7f1001e0)).setOnClickListener(new .Lambda._cls0(3, 2));
        ((Button)findViewById(0x7f1001e1)).setOnClickListener(new .Lambda._cls0(2, 1));
        ((Button)findViewById(0x7f1001e2)).setOnClickListener(new .Lambda._cls1());
        return;
        i = 1;
          goto _L1
    }
}
