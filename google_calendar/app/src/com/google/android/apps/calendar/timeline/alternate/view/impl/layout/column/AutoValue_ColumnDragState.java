// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnDragState

final class AutoValue_ColumnDragState extends ColumnDragState
{

    private final long dragTimeFp16;
    private final List events;
    private final int pagingDirection;
    private final List phantoms;
    private final boolean scrolling;
    private final long startDragTimeFp16;

    AutoValue_ColumnDragState(long l, long l1, int i, boolean flag, List list, 
            List list1)
    {
        dragTimeFp16 = l;
        startDragTimeFp16 = l1;
        pagingDirection = i;
        scrolling = flag;
        phantoms = list;
        events = list1;
    }

    public final long dragTimeFp16()
    {
        return dragTimeFp16;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof ColumnDragState)
            {
                if (dragTimeFp16 != ((ColumnDragState) (obj = (ColumnDragState)obj)).dragTimeFp16() || startDragTimeFp16 != ((ColumnDragState) (obj)).startDragTimeFp16() || pagingDirection != ((ColumnDragState) (obj)).pagingDirection() || scrolling != ((ColumnDragState) (obj)).scrolling() || !phantoms.equals(((ColumnDragState) (obj)).phantoms()) || !events.equals(((ColumnDragState) (obj)).events()))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final List events()
    {
        return events;
    }

    public final int hashCode()
    {
        int i = (int)(dragTimeFp16 >>> 32 ^ dragTimeFp16);
        int j = (int)(startDragTimeFp16 >>> 32 ^ startDragTimeFp16);
        int k = pagingDirection;
        char c;
        if (scrolling)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return ((c ^ (((i ^ 0xf4243) * 0xf4243 ^ j) * 0xf4243 ^ k) * 0xf4243) * 0xf4243 ^ phantoms.hashCode()) * 0xf4243 ^ events.hashCode();
    }

    public final int pagingDirection()
    {
        return pagingDirection;
    }

    public final List phantoms()
    {
        return phantoms;
    }

    public final boolean scrolling()
    {
        return scrolling;
    }

    public final long startDragTimeFp16()
    {
        return startDragTimeFp16;
    }

    final ColumnDragState.Builder toBuilder()
    {
        return new Builder(this);
    }

    public final String toString()
    {
        long l = dragTimeFp16;
        long l1 = startDragTimeFp16;
        int i = pagingDirection;
        boolean flag = scrolling;
        String s = String.valueOf(phantoms);
        String s1 = String.valueOf(events);
        return (new StringBuilder(String.valueOf(s).length() + 156 + String.valueOf(s1).length())).append("ColumnDragState{dragTimeFp16=").append(l).append(", startDragTimeFp16=").append(l1).append(", pagingDirection=").append(i).append(", scrolling=").append(flag).append(", phantoms=").append(s).append(", events=").append(s1).append("}").toString();
    }

    private class Builder extends ColumnDragState.Builder
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

        final ColumnDragState.Builder dragTimeFp16(long l)
        {
            dragTimeFp16 = Long.valueOf(l);
            return this;
        }

        final ColumnDragState.Builder events(List list)
        {
            events = list;
            return this;
        }

        final ColumnDragState.Builder pagingDirection(int i)
        {
            pagingDirection = Integer.valueOf(i);
            return this;
        }

        final ColumnDragState.Builder phantoms(List list)
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

        final ColumnDragState.Builder scrolling(boolean flag)
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

        final ColumnDragState.Builder startDragTimeFp16(long l)
        {
            startDragTimeFp16 = Long.valueOf(l);
            return this;
        }

        Builder()
        {
        }

        Builder(ColumnDragState columndragstate)
        {
            dragTimeFp16 = Long.valueOf(columndragstate.dragTimeFp16());
            startDragTimeFp16 = Long.valueOf(columndragstate.startDragTimeFp16());
            pagingDirection = Integer.valueOf(columndragstate.pagingDirection());
            scrolling = Boolean.valueOf(columndragstate.scrolling());
            phantoms = columndragstate.phantoms();
            events = columndragstate.events();
        }
    }

}
