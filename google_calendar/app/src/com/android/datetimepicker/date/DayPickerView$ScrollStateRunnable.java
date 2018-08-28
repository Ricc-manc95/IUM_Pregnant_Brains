// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.view.View;

// Referenced classes of package com.android.datetimepicker.date:
//            DayPickerView

public final class this._cls0
    implements Runnable
{

    public int newState;
    public final DayPickerView this$0;

    public final void run()
    {
        Object obj;
        int k;
        k = 1;
        mCurrentScrollState = newState;
        if (newState != 0 || mPreviousScrollState == 0 || mPreviousScrollState == 1)
        {
            break MISSING_BLOCK_LABEL_204;
        }
        mPreviousScrollState = newState;
        obj = getChildAt(0);
        int i = 0;
        for (; obj != null && ((View) (obj)).getBottom() <= 0; obj = ((DayPickerView) (obj)).getChildAt(i))
        {
            obj = DayPickerView.this;
            i++;
        }

        if (obj != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int l;
        int j = getFirstVisiblePosition();
        l = getLastVisiblePosition();
        int i1;
        if (j != 0 && l != getCount() - 1)
        {
            j = k;
        } else
        {
            j = 0;
        }
        k = ((View) (obj)).getTop();
        l = ((View) (obj)).getBottom();
        i1 = getHeight() / 2;
        if (!j || k >= DayPickerView.LIST_TOP_OFFSET) goto _L1; else goto _L3
_L3:
        if (l > i1)
        {
            smoothScrollBy(k, 250);
            return;
        } else
        {
            smoothScrollBy(l, 250);
            return;
        }
        mPreviousScrollState = newState;
        return;
    }

    protected ()
    {
        this$0 = DayPickerView.this;
        super();
    }
}
