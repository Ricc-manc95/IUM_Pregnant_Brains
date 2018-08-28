// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.graphics.Point;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            TentativeChipGestureListener

final class arg._cls2
    implements Consumer
{

    private final TentativeChipGestureListener arg$1;
    private final Point arg$2;

    public final void accept(Object obj)
    {
        obj = arg$1;
        Point point = arg$2;
        ((TentativeChipGestureListener) (obj)).longPressListener.ess(((TentativeChipGestureListener) (obj)).chip, point);
    }

    Y(TentativeChipGestureListener tentativechipgesturelistener, Point point)
    {
        arg$1 = tentativechipgesturelistener;
        arg$2 = point;
    }
}
