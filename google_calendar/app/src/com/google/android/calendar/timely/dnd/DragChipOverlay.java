// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.dnd;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.utils.recycler.Recycler;

// Referenced classes of package com.google.android.calendar.timely.dnd:
//            DragChipFactory

public class DragChipOverlay extends ViewGroup
{
    static final class DragChipLayoutParams extends android.view.ViewGroup.LayoutParams
    {

        public int leftInset;
        public int rightInset;

        DragChipLayoutParams()
        {
            super(0, 0);
        }
    }

    public static final class EmptyDragShadowBuilder extends android.view.View.DragShadowBuilder
    {

        public final void onDrawShadow(Canvas canvas)
        {
        }

        public final void onProvideShadowMetrics(Point point, Point point1)
        {
            point.set(1, 1);
            point1.set(0, 0);
        }

        public EmptyDragShadowBuilder()
        {
        }
    }


    public static final Property DRAG_CHIP_LEFT = new _cls1(java/lang/Integer, "DragChipLeftOffset");
    public static final Property DRAG_CHIP_RIGHT = new _cls2(java/lang/Integer, "DragChipRightOffset");
    public DragChipFactory dragChipFactory;
    public final Rect frameRecycle;
    public int positionInWindowRecycle[];

    public DragChipOverlay(Context context)
    {
        super(context);
        frameRecycle = new Rect();
        positionInWindowRecycle = new int[2];
    }

    public DragChipOverlay(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        frameRecycle = new Rect();
        positionInWindowRecycle = new int[2];
    }

    public DragChipOverlay(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        frameRecycle = new Rect();
        positionInWindowRecycle = new int[2];
    }

    public DragChipOverlay(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        frameRecycle = new Rect();
        positionInWindowRecycle = new int[2];
    }

    public static DragChipOverlay getInstance(Activity activity)
    {
        return (DragChipOverlay)activity.findViewById(0x7f10016b);
    }

    public static void setSize(View view, int i, int j)
    {
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        if (layoutparams.width != i || layoutparams.height != j)
        {
            layoutparams.width = i;
            layoutparams.height = j;
            view.setLayoutParams(layoutparams);
        }
    }

    public final void addDragChip(int i, Rect rect)
    {
        frameRecycle.set(rect);
        Object obj = dragChipFactory;
        rect = (Chip)((DragChipFactory) (obj)).chipRecycler.getOrCreateObject();
        rect.setViewModel(((DragChipFactory) (obj)).chipViewModel);
        rect.setTextIconScale(((DragChipFactory) (obj)).chipTextIconScale);
        ViewCompat.setElevation(rect, ((DragChipFactory) (obj)).chipElevation);
        addView(rect, i, generateDefaultLayoutParams());
        setSize(rect, frameRecycle.width(), frameRecycle.height());
        obj = frameRecycle;
        getLocationInWindow(positionInWindowRecycle);
        ((Rect) (obj)).offset(-positionInWindowRecycle[0], -positionInWindowRecycle[1]);
        rect.setTranslationX(frameRecycle.left);
        rect.setTranslationY(frameRecycle.top);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return super.checkLayoutParams(layoutparams) && (layoutparams instanceof DragChipLayoutParams);
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new DragChipLayoutParams();
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (checkLayoutParams(layoutparams))
        {
            return layoutparams;
        } else
        {
            return generateDefaultLayoutParams();
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        for (i = 0; i < getChildCount(); i++)
        {
            View view = getChildAt(i);
            DragChipLayoutParams dragchiplayoutparams = (DragChipLayoutParams)view.getLayoutParams();
            view.layout(dragchiplayoutparams.leftInset, 0, dragchiplayoutparams.width - dragchiplayoutparams.rightInset, dragchiplayoutparams.height);
        }

    }

    public final void setDragChipArea(Rect rect)
    {
        getLocationInWindow(positionInWindowRecycle);
        rect.offset(-positionInWindowRecycle[0], -positionInWindowRecycle[1]);
        setClipBounds(rect);
    }


    private class _cls1 extends Property
    {

        public final Object get(Object obj)
        {
            return Integer.valueOf(((DragChipLayoutParams)((View)obj).getLayoutParams()).leftInset);
        }

        public final void set(Object obj, Object obj1)
        {
            obj = (View)obj;
            obj1 = (Integer)obj1;
            DragChipLayoutParams dragchiplayoutparams = (DragChipLayoutParams)((View) (obj)).getLayoutParams();
            dragchiplayoutparams.leftInset = ((Integer) (obj1)).intValue();
            ((View) (obj)).setLayoutParams(dragchiplayoutparams);
        }

        _cls1(Class class1, String s)
        {
            super(class1, s);
        }
    }


    private class _cls2 extends Property
    {

        public final Object get(Object obj)
        {
            obj = (DragChipLayoutParams)((View)obj).getLayoutParams();
            return Integer.valueOf(((DragChipLayoutParams) (obj)).width - ((DragChipLayoutParams) (obj)).rightInset);
        }

        public final void set(Object obj, Object obj1)
        {
            obj = (View)obj;
            obj1 = (Integer)obj1;
            DragChipLayoutParams dragchiplayoutparams = (DragChipLayoutParams)((View) (obj)).getLayoutParams();
            dragchiplayoutparams.rightInset = dragchiplayoutparams.width - ((Integer) (obj1)).intValue();
            ((View) (obj)).setLayoutParams(dragchiplayoutparams);
        }

        _cls2(Class class1, String s)
        {
            super(class1, s);
        }
    }

}
