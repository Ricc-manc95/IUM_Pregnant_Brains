// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.Rect;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapterViewHolder, ViewLayoutParams

public class TimelineAdapterViewHolderImpl extends android.support.v7.widget.RecyclerView.ViewHolder
    implements TimelineAdapterViewHolder
{

    public boolean clip;
    public final Rect clipRect = new Rect();
    private ViewOutlineProvider outlineProvider;
    public boolean useOutlineProvider;

    public TimelineAdapterViewHolderImpl(View view)
    {
        super(view);
        clip = false;
        itemView.setTag(getClass().getSimpleName());
        outlineProvider = new _cls1();
    }

    public final boolean draw(TimelineAdapterViewHolder.ViewDrawer viewdrawer, Canvas canvas, long l)
    {
        if (!clip || useOutlineProvider || ViewCompat.getZ(itemView) > 0.0F)
        {
            return viewdrawer.draw(canvas, itemView, l);
        } else
        {
            canvas.save();
            canvas.clipRect(clipRect.left, clipRect.top, clipRect.right, clipRect.bottom);
            boolean flag = viewdrawer.draw(canvas, itemView, l);
            canvas.restore();
            return flag;
        }
    }

    public void onLayoutUpdate(ViewLayoutParams viewlayoutparams)
    {
    }

    public void onRecycled()
    {
    }

    public final void setClipRect(Rect rect)
    {
        if (rect != null)
        {
            clipRect.set(rect);
            clip = true;
        } else
        {
            clip = false;
        }
        updateOutlineProvider(clip, useOutlineProvider);
    }

    final void updateOutlineProvider(boolean flag, boolean flag1)
    {
        if (flag && flag1)
        {
            itemView.setClipToOutline(true);
            itemView.setOutlineProvider(outlineProvider);
            itemView.invalidateOutline();
        } else
        if (itemView.getOutlineProvider() != ViewOutlineProvider.BACKGROUND && ViewCompat.getZ(itemView) == 0.0F)
        {
            itemView.setClipToOutline(false);
            itemView.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
            itemView.invalidateOutline();
            return;
        }
    }

    private class _cls1 extends ViewOutlineProvider
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

        _cls1()
        {
            this$0 = TimelineAdapterViewHolderImpl.this;
            super();
        }
    }

}
