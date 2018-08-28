// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineSuggestion

public final class FindTimeGridData
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public int index;
    public ImmutableList suggestions;

    public FindTimeGridData()
    {
    }

    public FindTimeGridData(Parcel parcel)
    {
        suggestions = ImmutableList.copyOf(parcel.createTypedArrayList(TimelineSuggestion.CREATOR));
        index = parcel.readInt();
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof FindTimeGridData))
        {
            return false;
        }
        Object obj1 = (FindTimeGridData)obj;
        if (index != ((FindTimeGridData) (obj1)).index)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = suggestions;
        obj1 = ((FindTimeGridData) (obj1)).suggestions;
        boolean flag;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(index), suggestions
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedList(suggestions);
        parcel.writeInt(index);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new FindTimeGridData(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new FindTimeGridData[i];
        }

        _cls1()
        {
        }
    }

}
