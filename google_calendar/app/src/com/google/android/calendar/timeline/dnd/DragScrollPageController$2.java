// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.dnd;


// Referenced classes of package com.google.android.calendar.timeline.dnd:
//            DragScrollPageController, ScrollRateInterpolator

final class this._cls0
    implements eaState.Callback
{

    private final DragScrollPageController this$0;

    public final void enterArea$514IILG_0()
    {
        _flddelegate.onScrollModeStart();
    }

    public final void inArea(int i, float f, long l)
    {
        legate legate = _flddelegate;
        ScrollRateInterpolator scrollrateinterpolator = scrollInterpolator;
        double d = f;
        double d1 = scrollrateinterpolator.maxScroll;
        int j = (int)Math.max(1.0D, (Math.pow(d - 1.0D, 5D) + 1.0D) * d1 * Math.min(1.0D, (double)l / 2000D));
        if (i == 2)
        {
            i = 1;
        } else
        {
            i = -1;
        }
        legate.onScroll(i * j);
    }

    public final void leaveArea$514IILG_0()
    {
        _flddelegate.onScrollModeEnd();
    }

    legate()
    {
        this$0 = DragScrollPageController.this;
        super();
    }
}
