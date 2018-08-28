// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.view.horizontalcarousel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Space;

public class HorizontalCarousel extends RecyclerView
{
    public final class LayoutManagerWithMaxItemWidth extends LinearLayoutManager
    {

        private final Rect insets = new Rect();
        private final HorizontalCarousel this$0;

        public final void measureChildWithMargins(View view, int i, int j)
        {
            boolean flag = false;
            android.support.v7.widget.RecyclerView.LayoutParams layoutparams = (android.support.v7.widget.RecyclerView.LayoutParams)view.getLayoutParams();
            if (layoutparams.width == -1)
            {
                Rect rect = insets;
                int k;
                int l;
                int i1;
                int j1;
                int k1;
                int l1;
                int i2;
                if (super.mRecyclerView == null)
                {
                    rect.set(0, 0, 0, 0);
                } else
                {
                    rect.set(super.mRecyclerView.getItemDecorInsetsForChild(view));
                }
                k1 = insets.left;
                l1 = insets.right;
                i1 = insets.top;
                j1 = insets.bottom;
                i2 = super.mWidth;
                if (super.mRecyclerView != null)
                {
                    k = super.mRecyclerView.getPaddingLeft();
                } else
                {
                    k = 0;
                }
                if (super.mRecyclerView != null)
                {
                    l = super.mRecyclerView.getPaddingRight();
                } else
                {
                    l = 0;
                }
                l = getChildMeasureSpec(i2, k + l + layoutparams.leftMargin + layoutparams.rightMargin + (i + (k1 + l1)), maxItemWidth, canScrollHorizontally());
                k1 = super.mHeight;
                if (super.mRecyclerView != null)
                {
                    i = super.mRecyclerView.getPaddingTop();
                } else
                {
                    i = 0;
                }
                k = ((flag) ? 1 : 0);
                if (super.mRecyclerView != null)
                {
                    k = super.mRecyclerView.getPaddingBottom();
                }
                view.measure(l, getChildMeasureSpec(k1, i + k + layoutparams.topMargin + layoutparams.bottomMargin + (j + (i1 + j1)), layoutparams.height, canScrollVertically()));
                return;
            } else
            {
                super.measureChildWithMargins(view, i, j);
                return;
            }
        }

        public LayoutManagerWithMaxItemWidth(Context context)
        {
            this$0 = HorizontalCarousel.this;
            super(0, false);
        }
    }

    public static final class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder
    {

        public final String toString()
        {
            return String.format("%s %s", new Object[] {
                super.toString(), itemView.getTag()
            });
        }

        public ViewHolder(View view)
        {
            super(view);
            view.setTag(0x7f10000d, this);
        }
    }


    private static final Rect RECT = new Rect();
    private static final RectF RECT_F = new RectF();
    private boolean forceOnSizeChanged;
    public int maxItemWidth;
    private int maxItemWidthHint;
    public int spaceBetween;
    private final android.support.v7.widget.RecyclerView.ItemDecoration spaceDecoration;
    public int spaceEnd;
    public int spaceStart;
    private final float touchSlop;

    public HorizontalCarousel(Context context)
    {
        this(context, null);
    }

    public HorizontalCarousel(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        spaceDecoration = new _cls1();
        super.mHasFixedSize = true;
        setLayoutManager(new LayoutManagerWithMaxItemWidth(context));
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        addItemDecoration(spaceDecoration);
        if (attributeset == null)
        {
            break MISSING_BLOCK_LABEL_126;
        }
        context = context.getTheme().obtainStyledAttributes(attributeset, R.styleable.HorizontalCarousel, 0, 0);
        spaceStart = context.getDimensionPixelSize(R.styleable.HorizontalCarousel_layout_spaceStart, 0);
        spaceEnd = context.getDimensionPixelSize(R.styleable.HorizontalCarousel_layout_spaceEnd, 0);
        spaceBetween = context.getDimensionPixelSize(R.styleable.HorizontalCarousel_layout_spaceBetween, 0);
        maxItemWidthHint = context.getDimensionPixelSize(R.styleable.HorizontalCarousel_layout_maxItemWidthHint, 0);
        context.recycle();
        return;
        attributeset;
        context.recycle();
        throw attributeset;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if (i != k)
        {
            maxItemWidth = Math.min(maxItemWidthHint, i - spaceStart - spaceEnd);
            forceOnSizeChanged = false;
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        float f;
        float f1;
        float f2;
        int i;
        int j;
        boolean flag;
        flag = super.onTouchEvent(motionevent);
        i = (int)motionevent.getX();
        j = (int)motionevent.getY();
        f = i;
        f1 = j;
        f2 = touchSlop;
        j = getChildCount();
        i = 0;
_L3:
        if (i >= j) goto _L2; else goto _L1
_L1:
        motionevent = getChildAt(i);
        if (motionevent instanceof Space)
        {
            continue; /* Loop/switch isn't completed */
        }
        motionevent.getHitRect(RECT);
        RECT_F.set(RECT);
        RECT_F.inset(-f2, -f2);
        if (!RECT_F.contains(f, f1))
        {
            continue; /* Loop/switch isn't completed */
        }
_L4:
        if (motionevent != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        return i != 0 && flag;
        i++;
          goto _L3
_L2:
        motionevent = null;
          goto _L4
    }

    public void setMaxItemWidthHint(int i)
    {
        maxItemWidthHint = i;
    }

    public void setSpaceBetween(int i)
    {
        spaceBetween = i;
    }

    public void setSpaceEnd(int i)
    {
        spaceEnd = i;
    }

    public void setSpaceStart(int i)
    {
        spaceStart = i;
    }


    private class _cls1 extends android.support.v7.widget.RecyclerView.ItemDecoration
    {

        private final HorizontalCarousel this$0;

        public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerview, android.support.v7.widget.RecyclerView.State state)
        {
            int j = HorizontalCarousel.getChildAdapterPosition(view);
            boolean flag;
            int i;
            if (ViewCompat.getLayoutDirection(HorizontalCarousel.this) == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (state.mInPreLayout)
            {
                i = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
            } else
            {
                i = state.mItemCount;
            }
            if (flag)
            {
                if (j == 0 && i == 1)
                {
                    rect.set(spaceStart, 0, spaceEnd, 0);
                    return;
                }
                if (j == 0)
                {
                    rect.set(spaceStart, 0, spaceBetween, 0);
                    return;
                }
                if (j == i - 1)
                {
                    rect.set(0, 0, spaceEnd, 0);
                    return;
                } else
                {
                    rect.set(0, 0, spaceBetween, 0);
                    return;
                }
            }
            if (j == 0 && i == 1)
            {
                rect.set(spaceEnd, 0, spaceStart, 0);
                return;
            }
            if (j == 0)
            {
                rect.set(spaceBetween, 0, spaceStart, 0);
                return;
            }
            if (j == i - 1)
            {
                rect.set(spaceEnd, 0, 0, 0);
                return;
            } else
            {
                rect.set(spaceBetween, 0, 0, 0);
                return;
            }
        }

        _cls1()
        {
            this$0 = HorizontalCarousel.this;
            super();
        }
    }

}
