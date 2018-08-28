// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.graphics.Canvas;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.Rect;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            ViewLayoutParams

public interface TimelineAdapterViewHolder
{

    public abstract boolean draw(ViewDrawer viewdrawer, Canvas canvas, long l);

    public abstract void onLayoutUpdate(ViewLayoutParams viewlayoutparams);

    public abstract void onRecycled();

    public abstract void setClipRect(Rect rect);
}
