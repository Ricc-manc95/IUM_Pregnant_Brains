// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.dnd;


// Referenced classes of package com.google.android.calendar.timeline.dnd:
//            DragScrollPageController

final class this._cls0
    implements eaState.Callback
{

    private final DragScrollPageController this$0;

    public final void enterArea$514IILG_0()
    {
        _flddelegate.onPageModeStart();
    }

    public final void inArea(int i, float f, long l)
    {
        legate legate = _flddelegate;
        if (i == 0)
        {
            i = -1;
        } else
        {
            i = 1;
        }
        legate.onPage(i);
    }

    public final void leaveArea$514IILG_0()
    {
        _flddelegate.onPageModeEnd();
    }

    legate()
    {
        this$0 = DragScrollPageController.this;
        super();
    }
}
