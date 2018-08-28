// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.view.ScaleGestureDetector;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.Layout;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutManager;

final class val.layoutManager
    implements android.view.or.OnScaleGestureListener
{

    private final LayoutManager val$layoutManager;

    public final boolean onScale(ScaleGestureDetector scalegesturedetector)
    {
        Layout layout = val$layoutManager.getLayout();
        float f = scalegesturedetector.getCurrentSpanX();
        float f1 = scalegesturedetector.getCurrentSpanY();
        scalegesturedetector.getPreviousSpanX();
        return layout.onScale$5134CHI655D0____0(f, f1, scalegesturedetector.getPreviousSpanY());
    }

    public final boolean onScaleBegin(ScaleGestureDetector scalegesturedetector)
    {
        return val$layoutManager.getLayout().onScaleBegin();
    }

    public final void onScaleEnd(ScaleGestureDetector scalegesturedetector)
    {
        val$layoutManager.getLayout().onScaleEnd();
    }

    er()
    {
        val$layoutManager = layoutmanager;
        super();
    }
}
