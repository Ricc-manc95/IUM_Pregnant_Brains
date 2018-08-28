// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnDragState

abstract class i
{

    abstract ColumnDragState build();

    abstract long dragTimeFp16();

    abstract i dragTimeFp16(long l);

    abstract i events(List list);

    abstract i pagingDirection(int i);

    abstract i phantoms(List list);

    abstract i scrolling(boolean flag);

    abstract boolean scrolling();

    abstract long startDragTimeFp16();

    abstract i startDragTimeFp16(long l);

    i()
    {
    }
}
