// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.view.View;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            DndEventHandler

final class this._cls0
    implements android.view.eListener
{

    private final DndEventHandler this$0;

    public final void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1)
    {
        _flddelegate.onTargetVisibleAreaChanged((dTarget)view);
    }

    legate()
    {
        this$0 = DndEventHandler.this;
        super();
    }
}
