// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.support.v4.view.ViewCompat;
import android.util.Property;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.dnd.DragChipFactory;
import com.google.android.calendar.timely.dnd.DragChipOverlay;
import com.google.android.calendar.utils.animation.Properties;
import com.google.android.calendar.utils.recycler.Recycler;
import com.google.common.base.Function;

final class arg._cls1
    implements Function
{

    private final DragChipOverlay arg$1;

    public final Object apply(Object obj)
    {
        Object obj1 = arg$1;
        obj = (Chip)obj;
        Object obj2 = (Chip)((DragChipOverlay) (obj1)).getChildAt(0);
        int i = ((Integer)DragChipOverlay.DRAG_CHIP_LEFT.get(obj2)).intValue();
        int j = ((Integer)DragChipOverlay.DRAG_CHIP_RIGHT.get(obj2)).intValue();
        Object obj3 = ((DragChipOverlay) (obj1)).getChildAt(0);
        obj2 = ((DragChipOverlay) (obj1)).dragChipFactory;
        obj3 = (Chip)obj3;
        ((DragChipFactory) (obj2)).chipRecycler.recycle(obj3);
        ((DragChipOverlay) (obj1)).removeViewAt(0);
        obj1 = ((DragChipOverlay) (obj1)).dragChipFactory;
        obj2 = Properties.VIEW_LEFT;
        obj3 = Properties.VIEW_RIGHT;
        int k = ((Integer)((Property) (obj2)).get(obj)).intValue();
        int l = ((Integer)((Property) (obj3)).get(obj)).intValue();
        ((Property) (obj2)).set(obj, Integer.valueOf(i));
        ((Property) (obj3)).set(obj, Integer.valueOf(j));
        ViewCompat.setTranslationZ(((android.view.View) (obj)), ((DragChipFactory) (obj1)).chipElevation);
        return DragChipFactory.createPickUpDropAnimator(((Chip) (obj)), ((Property) (obj2)), ((Property) (obj3)), i, k, j, l, ((DragChipFactory) (obj1)).chipElevation, 0.0F);
    }

    (DragChipOverlay dragchipoverlay)
    {
        arg$1 = dragchipoverlay;
    }
}
