// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.api.event.time:
//            RecurRulePart

public final class ByDayRecurrence
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final Integer offset;
    public final int weekday;

    public ByDayRecurrence(int i, Integer integer)
    {
        weekday = i;
        offset = integer;
    }

    ByDayRecurrence(Parcel parcel)
    {
        this(RecurRulePart.checkWeekday(parcel.readInt()), (Integer)parcel.readValue(java/lang/Integer.getClassLoader()));
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        Object obj1;
        if (obj instanceof ByDayRecurrence)
        {
            if (weekday == ((ByDayRecurrence) (obj1 = (ByDayRecurrence)obj)).weekday)
            {
                obj = offset;
                obj1 = ((ByDayRecurrence) (obj1)).offset;
                boolean flag;
                if (obj == obj1 || obj != null && obj.equals(obj1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(weekday), offset
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(weekday);
        parcel.writeValue(offset);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new ByDayRecurrence(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new ByDayRecurrence[i];
        }

        _cls1()
        {
        }
    }

}
