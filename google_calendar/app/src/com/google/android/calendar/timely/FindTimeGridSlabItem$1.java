// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridSlabItem

final class this._cls0
    implements android.view.alLayoutListener
{

    private final FindTimeGridSlabItem this$0;

    public final void onGlobalLayout()
    {
        if (listener != null)
        {
            int i;
            if (descriptionView.getVisibility() == 8)
            {
                i = 0;
            } else
            {
                i = descriptionView.getLineCount();
            }
            listener.onDescriptionNumLinesUpdated(i);
        }
        descriptionView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    SlabItemUpdatedListener()
    {
        this$0 = FindTimeGridSlabItem.this;
        super();
    }
}
