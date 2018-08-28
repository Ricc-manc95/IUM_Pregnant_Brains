// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnDragState, AutoValue_ColumnDragState

final class events extends events
{

    private Long dragTimeFp16;
    private List events;
    private Integer pagingDirection;
    private List phantoms;
    private Boolean scrolling;
    private Long startDragTimeFp16;

    final ColumnDragState build()
    {
        String s1 = "";
        if (dragTimeFp16 == null)
        {
            s1 = String.valueOf("").concat(" dragTimeFp16");
        }
        String s = s1;
        if (startDragTimeFp16 == null)
        {
            s = String.valueOf(s1).concat(" startDragTimeFp16");
        }
        s1 = s;
        if (pagingDirection == null)
        {
            s1 = String.valueOf(s).concat(" pagingDirection");
        }
        s = s1;
        if (scrolling == null)
        {
            s = String.valueOf(s1).concat(" scrolling");
        }
        s1 = s;
        if (phantoms == null)
        {
            s1 = String.valueOf(s).concat(" phantoms");
        }
        s = s1;
        if (events == null)
        {
            s = String.valueOf(s1).concat(" events");
        }
        if (!s.isEmpty())
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Missing required properties:".concat(s);
            } else
            {
                s = new String("Missing required properties:");
            }
            throw new IllegalStateException(s);
        } else
        {
            return new AutoValue_ColumnDragState(dragTimeFp16.longValue(), startDragTimeFp16.longValue(), pagingDirection.intValue(), scrolling.booleanValue(), phantoms, events);
        }
    }

    final long dragTimeFp16()
    {
        if (dragTimeFp16 == null)
        {
            throw new IllegalStateException("Property \"dragTimeFp16\" has not been set");
        } else
        {
            return dragTimeFp16.longValue();
        }
    }

    final dragTimeFp16 dragTimeFp16(long l)
    {
        dragTimeFp16 = Long.valueOf(l);
        return this;
    }

    final dragTimeFp16 events(List list)
    {
        events = list;
        return this;
    }

    final events pagingDirection(int i)
    {
        pagingDirection = Integer.valueOf(i);
        return this;
    }

    final pagingDirection phantoms(List list)
    {
        if (list == null)
        {
            throw new NullPointerException("Null phantoms");
        } else
        {
            phantoms = list;
            return this;
        }
    }

    final phantoms scrolling(boolean flag)
    {
        scrolling = Boolean.valueOf(flag);
        return this;
    }

    final boolean scrolling()
    {
        if (scrolling == null)
        {
            throw new IllegalStateException("Property \"scrolling\" has not been set");
        } else
        {
            return scrolling.booleanValue();
        }
    }

    final long startDragTimeFp16()
    {
        if (startDragTimeFp16 == null)
        {
            throw new IllegalStateException("Property \"startDragTimeFp16\" has not been set");
        } else
        {
            return startDragTimeFp16.longValue();
        }
    }

    final startDragTimeFp16 startDragTimeFp16(long l)
    {
        startDragTimeFp16 = Long.valueOf(l);
        return this;
    }

    ()
    {
    }

    (ColumnDragState columndragstate)
    {
        dragTimeFp16 = Long.valueOf(columndragstate.dragTimeFp16());
        startDragTimeFp16 = Long.valueOf(columndragstate.startDragTimeFp16());
        pagingDirection = Integer.valueOf(columndragstate.pagingDirection());
        scrolling = Boolean.valueOf(columndragstate.scrolling());
        phantoms = columndragstate.phantoms();
        events = columndragstate.events();
    }
}
