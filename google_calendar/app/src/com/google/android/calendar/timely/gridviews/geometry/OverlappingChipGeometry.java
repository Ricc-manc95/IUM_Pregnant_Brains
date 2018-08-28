// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.geometry;

import android.view.View;
import com.google.android.calendar.utils.Holder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely.gridviews.geometry:
//            HorizontalChipGeometry, PositionOnGrid, GridGeometryUtils

public final class OverlappingChipGeometry
    implements HorizontalChipGeometry
{

    private static final Comparator CHIP_BY_START_FRACTION;
    private final HorizontalChipGeometry geometry;

    public OverlappingChipGeometry(HorizontalChipGeometry horizontalchipgeometry)
    {
        geometry = horizontalchipgeometry;
    }

    static final int lambda$static$0$OverlappingChipGeometry(View view, View view1)
    {
        return (int)Math.signum(((PositionOnGrid)((Holder)view.getLayoutParams()).get()).startFraction - ((PositionOnGrid)((Holder)view1.getLayoutParams()).get()).startFraction);
    }

    public final void layoutChipsHorizontally(ArrayList arraylist)
    {
        boolean flag = false;
        geometry.layoutChipsHorizontally(arraylist);
        Collections.sort(arraylist, CHIP_BY_START_FRACTION);
        for (int i = 0; i < arraylist.size(); i++)
        {
            View view = (View)arraylist.get(i);
            float f = 0.0F;
            int k = 0;
            while (k < i) 
            {
                View view2 = (View)arraylist.get(k);
                if (GridGeometryUtils.intersectsTime(view, view2))
                {
                    float f2;
                    if (((PositionOnGrid)((Holder)view.getLayoutParams()).get()).topMinutes - ((PositionOnGrid)((Holder)view2.getLayoutParams()).get()).topMinutes >= 45)
                    {
                        f2 = ((PositionOnGrid)((Holder)view2.getLayoutParams()).get()).startFraction + 0.07F;
                    } else
                    {
                        f2 = ((PositionOnGrid)((Holder)view2.getLayoutParams()).get()).endFraction;
                    }
                    f = Math.max(f, f2);
                }
                k++;
            }
            GridGeometryUtils.setX(view, Math.min(((PositionOnGrid)((Holder)view.getLayoutParams()).get()).startFraction, f), ((PositionOnGrid)((Holder)view.getLayoutParams()).get()).endFraction);
        }

        int j = arraylist.size() - 1;
        int l;
        do
        {
            l = ((flag) ? 1 : 0);
            if (j < 0)
            {
                break;
            }
            View view1 = (View)arraylist.get(j);
            l = j + 1;
            float f1 = 1.0F;
            for (; l < arraylist.size(); l++)
            {
                View view3 = (View)arraylist.get(l);
                if (GridGeometryUtils.intersectsTime(view1, view3) && ((PositionOnGrid)((Holder)view3.getLayoutParams()).get()).topMinutes - ((PositionOnGrid)((Holder)view1.getLayoutParams()).get()).topMinutes < 45)
                {
                    f1 = Math.min(f1, ((PositionOnGrid)((Holder)view3.getLayoutParams()).get()).startFraction);
                }
            }

            f1 = Math.max(((PositionOnGrid)((Holder)view1.getLayoutParams()).get()).endFraction, f1);
            GridGeometryUtils.setX(view1, ((PositionOnGrid)((Holder)view1.getLayoutParams()).get()).startFraction, f1);
            j--;
        } while (true);
        for (; l < arraylist.size(); l++)
        {
            ((PositionOnGrid)((Holder)((View)arraylist.get(l)).getLayoutParams()).get()).z = l;
        }

    }

    static 
    {
        class .Lambda._cls0
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls0();

            public final int compare(Object obj, Object obj1)
            {
                return OverlappingChipGeometry.lambda$static$0$OverlappingChipGeometry((View)obj, (View)obj1);
            }


            private .Lambda._cls0()
            {
            }
        }

        CHIP_BY_START_FRACTION = .Lambda._cls0..instance;
    }
}
