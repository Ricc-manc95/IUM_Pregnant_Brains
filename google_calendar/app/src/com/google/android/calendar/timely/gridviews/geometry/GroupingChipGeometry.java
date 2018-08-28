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
//            HorizontalChipGeometry, PositionOnGrid

public final class GroupingChipGeometry
    implements HorizontalChipGeometry
{

    private static final Comparator CHIP_BY_TOP_MINUTES;
    private final HorizontalChipGeometry geometry;

    public GroupingChipGeometry(HorizontalChipGeometry horizontalchipgeometry)
    {
        geometry = horizontalchipgeometry;
    }

    static final int lambda$static$0$GroupingChipGeometry(View view, View view1)
    {
        return ((PositionOnGrid)((Holder)view.getLayoutParams()).get()).topMinutes - ((PositionOnGrid)((Holder)view1.getLayoutParams()).get()).topMinutes;
    }

    public final void layoutChipsHorizontally(ArrayList arraylist)
    {
        Collections.sort(arraylist, CHIP_BY_TOP_MINUTES);
        ArrayList arraylist1 = new ArrayList();
        int i = 0;
        int j = 0;
        for (; i < arraylist.size(); i++)
        {
            View view = (View)arraylist.get(i);
            if (j <= ((PositionOnGrid)((Holder)view.getLayoutParams()).get()).topMinutes)
            {
                geometry.layoutChipsHorizontally(arraylist1);
                arraylist1.clear();
            }
            arraylist1.add(view);
            j = Math.max(j, ((PositionOnGrid)((Holder)view.getLayoutParams()).get()).bottomMinutes);
        }

        geometry.layoutChipsHorizontally(arraylist1);
    }

    static 
    {
        class .Lambda._cls0
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls0();

            public final int compare(Object obj, Object obj1)
            {
                return GroupingChipGeometry.lambda$static$0$GroupingChipGeometry((View)obj, (View)obj1);
            }


            private .Lambda._cls0()
            {
            }
        }

        CHIP_BY_TOP_MINUTES = .Lambda._cls0..instance;
    }
}
