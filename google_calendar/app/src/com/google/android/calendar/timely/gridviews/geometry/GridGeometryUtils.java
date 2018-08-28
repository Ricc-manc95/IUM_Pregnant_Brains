// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.geometry;

import android.view.View;
import com.google.android.calendar.utils.Holder;

// Referenced classes of package com.google.android.calendar.timely.gridviews.geometry:
//            PositionOnGrid

public final class GridGeometryUtils
{

    static boolean intersectsTime(View view, View view1)
    {
        view = (PositionOnGrid)((Holder)view.getLayoutParams()).get();
        view1 = (PositionOnGrid)((Holder)view1.getLayoutParams()).get();
        return ((PositionOnGrid) (view)).topMinutes < ((PositionOnGrid) (view1)).bottomMinutes && ((PositionOnGrid) (view)).bottomMinutes > ((PositionOnGrid) (view1)).topMinutes;
    }

    static void setX(View view, float f, float f1)
    {
        view = (PositionOnGrid)((Holder)view.getLayoutParams()).get();
        view.startFraction = f;
        view.endFraction = f1;
    }
}
