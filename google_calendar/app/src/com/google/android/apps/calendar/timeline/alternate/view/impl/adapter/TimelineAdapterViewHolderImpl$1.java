// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.Rect;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapterViewHolderImpl

final class this._cls0 extends ViewOutlineProvider
{

    private final TimelineAdapterViewHolderImpl this$0;

    public final void getOutline(View view, Outline outline)
    {
        int i = Math.max(0, clipRect.left - view.getLeft());
        int j = Math.max(0, clipRect.top - view.getTop());
        int k = Math.max(0, view.getRight() - clipRect.right);
        int l = Math.max(0, view.getBottom() - clipRect.bottom);
        outline.setRect(i, j, view.getWidth() - k, view.getHeight() - l);
    }

    ()
    {
        this$0 = TimelineAdapterViewHolderImpl.this;
        super();
    }
}
