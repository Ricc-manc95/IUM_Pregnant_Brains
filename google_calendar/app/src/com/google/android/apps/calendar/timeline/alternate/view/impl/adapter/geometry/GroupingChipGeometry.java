// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry:
//            HorizontalChipGeometry, GeometryUtils

public final class GroupingChipGeometry
    implements HorizontalChipGeometry
{

    private final HorizontalChipGeometry geometry;

    public GroupingChipGeometry(HorizontalChipGeometry horizontalchipgeometry)
    {
        geometry = horizontalchipgeometry;
    }

    public final void layoutChipsHorizontally(List list)
    {
        ArrayList arraylist = new ArrayList(list);
        list = new ArrayList();
        Collections.sort(arraylist, GeometryUtils.START_TIME_COMPARATOR);
        arraylist = (ArrayList)arraylist;
        int j = arraylist.size();
        long l = 0x8000000000000000L;
        for (int i = 0; i < j;)
        {
            Object obj = arraylist.get(i);
            i++;
            obj = (com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)obj;
            if (l <= ((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder) (obj)).getDisplayStartFp16() && !list.isEmpty())
            {
                geometry.layoutChipsHorizontally(list);
                list.clear();
            }
            list.add(obj);
            l = Math.max(l, ((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder) (obj)).getDisplayEndFp16());
        }

        geometry.layoutChipsHorizontally(list);
    }
}
