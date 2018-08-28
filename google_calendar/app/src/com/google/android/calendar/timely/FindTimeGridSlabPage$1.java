// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridSlabPage

final class this._cls0
    implements SlabItemUpdatedListener
{

    public final FindTimeGridSlabPage this$0;

    public final void onDescriptionNumLinesUpdated(int i)
    {
        class _cls1
            implements android.view.ViewTreeObserver.OnGlobalLayoutListener
        {

            private final FindTimeGridSlabPage._cls1 this$1;

            public final void onGlobalLayout()
            {
                slabBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (listener != null)
                {
                    listener.onSlabBarHeightUpdated(this$0);
                }
            }

            _cls1()
            {
                this$1 = FindTimeGridSlabPage._cls1.this;
                super();
            }
        }

        slabBar.getViewTreeObserver().addOnGlobalLayoutListener(new _cls1());
        FrameLayout framelayout = slabBar;
        FindTimeGridSlabPage findtimegridslabpage = FindTimeGridSlabPage.this;
        int j = findtimegridslabpage.baseHeight;
        framelayout.setMinimumHeight(findtimegridslabpage.extraLineHeight * i + j);
    }

    _cls1()
    {
        this$0 = FindTimeGridSlabPage.this;
        super();
    }
}
