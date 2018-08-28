// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.recurrencepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

// Referenced classes of package com.android.recurrencepicker:
//            WeekButton

public class LinearLayoutWithMaxWidth extends LinearLayout
{

    public LinearLayoutWithMaxWidth(Context context)
    {
        super(context);
    }

    public LinearLayoutWithMaxWidth(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public LinearLayoutWithMaxWidth(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    protected void onMeasure(int i, int j)
    {
        WeekButton.setSuggestedWidth(android.view.View.MeasureSpec.getSize(i) / 7);
        super.onMeasure(i, j);
    }
}
