// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;


// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            RoomBookingFilterParams

abstract class $AutoValue_RoomBookingFilterParams extends RoomBookingFilterParams
{

    private final Integer getRecurrenceOption;
    private final boolean showOnlyAvailable;

    $AutoValue_RoomBookingFilterParams(boolean flag, Integer integer)
    {
        showOnlyAvailable = flag;
        getRecurrenceOption = integer;
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof RoomBookingFilterParams))
        {
            break MISSING_BLOCK_LABEL_62;
        }
        obj = (RoomBookingFilterParams)obj;
        if (showOnlyAvailable != ((RoomBookingFilterParams) (obj)).showOnlyAvailable())
        {
            break; /* Loop/switch isn't completed */
        }
        if (getRecurrenceOption != null) goto _L4; else goto _L3
_L3:
        if (((RoomBookingFilterParams) (obj)).getRecurrenceOption() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!getRecurrenceOption.equals(((RoomBookingFilterParams) (obj)).getRecurrenceOption())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final Integer getRecurrenceOption()
    {
        return getRecurrenceOption;
    }

    public int hashCode()
    {
        char c;
        int i;
        if (showOnlyAvailable)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        if (getRecurrenceOption == null)
        {
            i = 0;
        } else
        {
            i = getRecurrenceOption.hashCode();
        }
        return i ^ 0xf4243 * (c ^ 0xf4243);
    }

    public final boolean showOnlyAvailable()
    {
        return showOnlyAvailable;
    }

    public String toString()
    {
        boolean flag = showOnlyAvailable;
        String s = String.valueOf(getRecurrenceOption);
        return (new StringBuilder(String.valueOf(s).length() + 70)).append("RoomBookingFilterParams{showOnlyAvailable=").append(flag).append(", getRecurrenceOption=").append(s).append("}").toString();
    }
}
