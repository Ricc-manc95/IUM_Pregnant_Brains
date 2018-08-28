// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry.PositionOnGrid;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterEvent, AutoValue_AdapterEvent

public final class displayEndFp16 extends displayEndFp16
{

    private Long displayEndFp16;
    private Long displayStartFp16;
    private Long endTimeMs;
    private Integer gridAllDaySlot;
    private PositionOnGrid gridTimedPosition;
    private Boolean isTimedEvent;
    private Object item;
    private Integer itemVersion;
    private Integer julianDay;
    private Integer monthSlot;
    private Integer position;
    private Long startTimeMs;

    public final AdapterEvent build()
    {
        String s1 = "";
        if (item == null)
        {
            s1 = String.valueOf("").concat(" item");
        }
        String s = s1;
        if (itemVersion == null)
        {
            s = String.valueOf(s1).concat(" itemVersion");
        }
        s1 = s;
        if (position == null)
        {
            s1 = String.valueOf(s).concat(" position");
        }
        s = s1;
        if (monthSlot == null)
        {
            s = String.valueOf(s1).concat(" monthSlot");
        }
        s1 = s;
        if (isTimedEvent == null)
        {
            s1 = String.valueOf(s).concat(" isTimedEvent");
        }
        s = s1;
        if (julianDay == null)
        {
            s = String.valueOf(s1).concat(" julianDay");
        }
        s1 = s;
        if (startTimeMs == null)
        {
            s1 = String.valueOf(s).concat(" startTimeMs");
        }
        s = s1;
        if (endTimeMs == null)
        {
            s = String.valueOf(s1).concat(" endTimeMs");
        }
        s1 = s;
        if (displayStartFp16 == null)
        {
            s1 = String.valueOf(s).concat(" displayStartFp16");
        }
        s = s1;
        if (displayEndFp16 == null)
        {
            s = String.valueOf(s1).concat(" displayEndFp16");
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
            return new AutoValue_AdapterEvent(item, itemVersion.intValue(), position.intValue(), monthSlot.intValue(), gridAllDaySlot, gridTimedPosition, isTimedEvent.booleanValue(), julianDay.intValue(), startTimeMs.longValue(), endTimeMs.longValue(), displayStartFp16.longValue(), displayEndFp16.longValue());
        }
    }

    public final long getDisplayEndFp16()
    {
        if (displayEndFp16 == null)
        {
            throw new IllegalStateException("Property \"displayEndFp16\" has not been set");
        } else
        {
            return displayEndFp16.longValue();
        }
    }

    public final long getDisplayStartFp16()
    {
        if (displayStartFp16 == null)
        {
            throw new IllegalStateException("Property \"displayStartFp16\" has not been set");
        } else
        {
            return displayStartFp16.longValue();
        }
    }

    public final PositionOnGrid getGridTimedPosition()
    {
        return gridTimedPosition;
    }

    public final boolean getIsTimedEvent()
    {
        if (isTimedEvent == null)
        {
            throw new IllegalStateException("Property \"isTimedEvent\" has not been set");
        } else
        {
            return isTimedEvent.booleanValue();
        }
    }

    public final Object getItem()
    {
        if (item == null)
        {
            throw new IllegalStateException("Property \"item\" has not been set");
        } else
        {
            return item;
        }
    }

    public final int getJulianDay()
    {
        if (julianDay == null)
        {
            throw new IllegalStateException("Property \"julianDay\" has not been set");
        } else
        {
            return julianDay.intValue();
        }
    }

    public final int getPosition()
    {
        if (position == null)
        {
            throw new IllegalStateException("Property \"position\" has not been set");
        } else
        {
            return position.intValue();
        }
    }

    public final position setDisplayEndFp16(long l)
    {
        displayEndFp16 = Long.valueOf(l);
        return this;
    }

    public final displayEndFp16 setDisplayStartFp16(long l)
    {
        displayStartFp16 = Long.valueOf(l);
        return this;
    }

    public final displayStartFp16 setEndTimeMs(long l)
    {
        endTimeMs = Long.valueOf(l);
        return this;
    }

    public final endTimeMs setGridAllDaySlot(Integer integer)
    {
        gridAllDaySlot = integer;
        return this;
    }

    public final gridAllDaySlot setGridTimedPosition(PositionOnGrid positionongrid)
    {
        gridTimedPosition = positionongrid;
        return this;
    }

    public final gridTimedPosition setIsTimedEvent(boolean flag)
    {
        isTimedEvent = Boolean.valueOf(flag);
        return this;
    }

    public final isTimedEvent setItem(Object obj)
    {
        if (obj == null)
        {
            throw new NullPointerException("Null item");
        } else
        {
            item = obj;
            return this;
        }
    }

    public final item setItemVersion(int i)
    {
        itemVersion = Integer.valueOf(i);
        return this;
    }

    public final itemVersion setJulianDay(int i)
    {
        julianDay = Integer.valueOf(i);
        return this;
    }

    public final julianDay setMonthSlot(int i)
    {
        monthSlot = Integer.valueOf(i);
        return this;
    }

    public final monthSlot setPosition(int i)
    {
        position = Integer.valueOf(i);
        return this;
    }

    public final position setStartTimeMs(long l)
    {
        startTimeMs = Long.valueOf(l);
        return this;
    }

    public ()
    {
    }

    (AdapterEvent adapterevent)
    {
        item = adapterevent.getItem();
        itemVersion = Integer.valueOf(adapterevent.getItemVersion());
        position = Integer.valueOf(adapterevent.getPosition());
        monthSlot = Integer.valueOf(adapterevent.getMonthSlot());
        gridAllDaySlot = adapterevent.getGridAllDaySlot();
        gridTimedPosition = adapterevent.getGridTimedPosition();
        isTimedEvent = Boolean.valueOf(adapterevent.getIsTimedEvent());
        julianDay = Integer.valueOf(adapterevent.getJulianDay());
        startTimeMs = Long.valueOf(adapterevent.getStartTimeMs());
        endTimeMs = Long.valueOf(adapterevent.getEndTimeMs());
        displayStartFp16 = Long.valueOf(adapterevent.getDisplayStartFp16());
        displayEndFp16 = Long.valueOf(adapterevent.getDisplayEndFp16());
    }
}
