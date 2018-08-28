// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry.PositionOnGrid;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterEvent

final class AutoValue_AdapterEvent extends AdapterEvent
{

    private final long displayEndFp16;
    private final long displayStartFp16;
    private final long endTimeMs;
    private final Integer gridAllDaySlot;
    private final PositionOnGrid gridTimedPosition;
    private final boolean isTimedEvent;
    private final Object item;
    private final int itemVersion;
    private final int julianDay;
    private final int monthSlot;
    private final int position;
    private final long startTimeMs;

    AutoValue_AdapterEvent(Object obj, int i, int j, int k, Integer integer, PositionOnGrid positionongrid, boolean flag, 
            int l, long l1, long l2, long l3, 
            long l4)
    {
        item = obj;
        itemVersion = i;
        position = j;
        monthSlot = k;
        gridAllDaySlot = integer;
        gridTimedPosition = positionongrid;
        isTimedEvent = flag;
        julianDay = l;
        startTimeMs = l1;
        endTimeMs = l2;
        displayStartFp16 = l3;
        displayEndFp16 = l4;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof AdapterEvent)
            {
                if (!item.equals(((AdapterEvent) (obj = (AdapterEvent)obj)).getItem()) || itemVersion != ((AdapterEvent) (obj)).getItemVersion() || position != ((AdapterEvent) (obj)).getPosition() || monthSlot != ((AdapterEvent) (obj)).getMonthSlot() || (gridAllDaySlot != null ? !gridAllDaySlot.equals(((AdapterEvent) (obj)).getGridAllDaySlot()) : ((AdapterEvent) (obj)).getGridAllDaySlot() != null) || (gridTimedPosition != null ? !gridTimedPosition.equals(((AdapterEvent) (obj)).getGridTimedPosition()) : ((AdapterEvent) (obj)).getGridTimedPosition() != null) || (isTimedEvent != ((AdapterEvent) (obj)).getIsTimedEvent() || julianDay != ((AdapterEvent) (obj)).getJulianDay() || startTimeMs != ((AdapterEvent) (obj)).getStartTimeMs() || endTimeMs != ((AdapterEvent) (obj)).getEndTimeMs() || displayStartFp16 != ((AdapterEvent) (obj)).getDisplayStartFp16() || displayEndFp16 != ((AdapterEvent) (obj)).getDisplayEndFp16()))
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

    public final long getDisplayEndFp16()
    {
        return displayEndFp16;
    }

    public final long getDisplayStartFp16()
    {
        return displayStartFp16;
    }

    public final long getEndTimeMs()
    {
        return endTimeMs;
    }

    public final Integer getGridAllDaySlot()
    {
        return gridAllDaySlot;
    }

    public final PositionOnGrid getGridTimedPosition()
    {
        return gridTimedPosition;
    }

    public final boolean getIsTimedEvent()
    {
        return isTimedEvent;
    }

    public final Object getItem()
    {
        return item;
    }

    public final int getItemVersion()
    {
        return itemVersion;
    }

    public final int getJulianDay()
    {
        return julianDay;
    }

    public final int getMonthSlot()
    {
        return monthSlot;
    }

    public final int getPosition()
    {
        return position;
    }

    public final long getStartTimeMs()
    {
        return startTimeMs;
    }

    public final int hashCode()
    {
        int j = 0;
        int k = item.hashCode();
        int l = itemVersion;
        int i1 = position;
        int j1 = monthSlot;
        int i;
        char c;
        if (gridAllDaySlot == null)
        {
            i = 0;
        } else
        {
            i = gridAllDaySlot.hashCode();
        }
        if (gridTimedPosition != null)
        {
            j = gridTimedPosition.hashCode();
        }
        if (isTimedEvent)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return (((((c ^ ((i ^ ((((k ^ 0xf4243) * 0xf4243 ^ l) * 0xf4243 ^ i1) * 0xf4243 ^ j1) * 0xf4243) * 0xf4243 ^ j) * 0xf4243) * 0xf4243 ^ julianDay) * 0xf4243 ^ (int)(startTimeMs >>> 32 ^ startTimeMs)) * 0xf4243 ^ (int)(endTimeMs >>> 32 ^ endTimeMs)) * 0xf4243 ^ (int)(displayStartFp16 >>> 32 ^ displayStartFp16)) * 0xf4243 ^ (int)(displayEndFp16 >>> 32 ^ displayEndFp16);
    }

    public final AdapterEvent.Builder toBuilder()
    {
        return new Builder(this);
    }

    public final String toString()
    {
        String s = String.valueOf(item);
        int i = itemVersion;
        int j = position;
        int k = monthSlot;
        String s1 = String.valueOf(gridAllDaySlot);
        String s2 = String.valueOf(gridTimedPosition);
        boolean flag = isTimedEvent;
        int l = julianDay;
        long l1 = startTimeMs;
        long l2 = endTimeMs;
        long l3 = displayStartFp16;
        long l4 = displayEndFp16;
        return (new StringBuilder(String.valueOf(s).length() + 311 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("AdapterEvent{item=").append(s).append(", itemVersion=").append(i).append(", position=").append(j).append(", monthSlot=").append(k).append(", gridAllDaySlot=").append(s1).append(", gridTimedPosition=").append(s2).append(", isTimedEvent=").append(flag).append(", julianDay=").append(l).append(", startTimeMs=").append(l1).append(", endTimeMs=").append(l2).append(", displayStartFp16=").append(l3).append(", displayEndFp16=").append(l4).append("}").toString();
    }

    private class Builder extends AdapterEvent.Builder
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

        public final AdapterEvent.Builder setDisplayEndFp16(long l)
        {
            displayEndFp16 = Long.valueOf(l);
            return this;
        }

        public final AdapterEvent.Builder setDisplayStartFp16(long l)
        {
            displayStartFp16 = Long.valueOf(l);
            return this;
        }

        public final AdapterEvent.Builder setEndTimeMs(long l)
        {
            endTimeMs = Long.valueOf(l);
            return this;
        }

        public final AdapterEvent.Builder setGridAllDaySlot(Integer integer)
        {
            gridAllDaySlot = integer;
            return this;
        }

        public final AdapterEvent.Builder setGridTimedPosition(PositionOnGrid positionongrid)
        {
            gridTimedPosition = positionongrid;
            return this;
        }

        public final AdapterEvent.Builder setIsTimedEvent(boolean flag)
        {
            isTimedEvent = Boolean.valueOf(flag);
            return this;
        }

        public final AdapterEvent.Builder setItem(Object obj)
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

        public final AdapterEvent.Builder setItemVersion(int i)
        {
            itemVersion = Integer.valueOf(i);
            return this;
        }

        public final AdapterEvent.Builder setJulianDay(int i)
        {
            julianDay = Integer.valueOf(i);
            return this;
        }

        public final AdapterEvent.Builder setMonthSlot(int i)
        {
            monthSlot = Integer.valueOf(i);
            return this;
        }

        public final AdapterEvent.Builder setPosition(int i)
        {
            position = Integer.valueOf(i);
            return this;
        }

        public final AdapterEvent.Builder setStartTimeMs(long l)
        {
            startTimeMs = Long.valueOf(l);
            return this;
        }

        public Builder()
        {
        }

        Builder(AdapterEvent adapterevent)
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

}
