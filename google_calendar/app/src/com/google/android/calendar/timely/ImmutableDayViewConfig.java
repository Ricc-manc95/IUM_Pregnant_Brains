// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.timely:
//            DayViewConfig, AgendaScrollCallback

public final class ImmutableDayViewConfig
    implements Parcelable, DayViewConfig
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final boolean canDrawBackgroundImage;
    private final boolean inGridMode;
    private final boolean isChipClickable;
    private final boolean neverDrawMonthHeader;
    private final boolean neverDrawNowLine;
    private final boolean shouldDrawDayHeader;
    private final boolean shouldDrawExtraHeaders;
    private final boolean shouldDrawMonthInDayHeader;
    private final boolean shouldDrawNoEventsView;
    private final boolean shouldDrawYearHeader;
    private final boolean supportsSwipe;

    ImmutableDayViewConfig(Parcel parcel)
    {
        boolean flag1 = true;
        super();
        boolean flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        inGridMode = flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        shouldDrawExtraHeaders = flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        shouldDrawDayHeader = flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        shouldDrawYearHeader = flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        neverDrawNowLine = flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        shouldDrawNoEventsView = flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        shouldDrawMonthInDayHeader = flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        canDrawBackgroundImage = flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isChipClickable = flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        supportsSwipe = flag;
        if (parcel.readInt() == 1)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        neverDrawMonthHeader = flag;
    }

    ImmutableDayViewConfig(boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, boolean flag5, boolean flag6, 
            boolean flag7, boolean flag8, boolean flag9, boolean flag10)
    {
        inGridMode = flag;
        shouldDrawExtraHeaders = flag1;
        shouldDrawDayHeader = flag2;
        shouldDrawYearHeader = flag3;
        neverDrawNowLine = flag4;
        shouldDrawNoEventsView = flag5;
        shouldDrawMonthInDayHeader = flag6;
        canDrawBackgroundImage = flag7;
        isChipClickable = flag8;
        supportsSwipe = flag9;
        neverDrawMonthHeader = flag10;
    }

    public final boolean canDrawBackgroundImage()
    {
        return canDrawBackgroundImage;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final AgendaScrollCallback getAgendaScrollCallback()
    {
        return null;
    }

    public final boolean inGridMode()
    {
        return inGridMode;
    }

    public final boolean inListView()
    {
        return !inGridMode;
    }

    public final boolean isChipClickable()
    {
        return isChipClickable;
    }

    public final boolean shouldDrawDayHeader()
    {
        return shouldDrawDayHeader;
    }

    public final boolean shouldDrawExtraHeaders()
    {
        return shouldDrawExtraHeaders;
    }

    public final boolean shouldDrawMonthInDayHeader()
    {
        return shouldDrawMonthInDayHeader;
    }

    public final boolean shouldDrawNoEventsView()
    {
        return shouldDrawNoEventsView;
    }

    public final boolean shouldDrawYearHeader()
    {
        return shouldDrawYearHeader;
    }

    public final boolean shouldNeverDrawMonthHeader()
    {
        return neverDrawMonthHeader;
    }

    public final boolean shouldNeverDrawNowLine()
    {
        return neverDrawNowLine;
    }

    public final boolean supportsSwipe()
    {
        return supportsSwipe;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if (inGridMode)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (shouldDrawExtraHeaders)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (shouldDrawDayHeader)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (shouldDrawYearHeader)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (neverDrawNowLine)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (shouldDrawNoEventsView)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (shouldDrawMonthInDayHeader)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (canDrawBackgroundImage)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (isChipClickable)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (supportsSwipe)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (neverDrawMonthHeader)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new ImmutableDayViewConfig(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new ImmutableDayViewConfig[i];
        }

        _cls1()
        {
        }
    }

}
