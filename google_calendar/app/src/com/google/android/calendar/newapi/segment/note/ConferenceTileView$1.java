// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.note;

import android.view.MotionEvent;

// Referenced classes of package com.google.android.calendar.newapi.segment.note:
//            ConferenceTileView

final class this._cls0 extends android.view.eOnGestureListener
{

    private final ConferenceTileView this$0;

    public final boolean onSingleTapUp(MotionEvent motionevent)
    {
        performClick();
        return true;
    }

    ()
    {
        this$0 = ConferenceTileView.this;
        super();
    }
}
