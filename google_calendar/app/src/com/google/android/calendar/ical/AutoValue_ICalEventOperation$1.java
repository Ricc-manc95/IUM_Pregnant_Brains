// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.os.Parcel;
import com.google.android.calendar.utils.datatypes.ImmutableListAdapter;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.ical:
//            AutoValue_ICalEventOperation

final class 
    implements android.os._ICalEventOperation._cls1
{

    public final Object createFromParcel(Parcel parcel)
    {
        boolean flag = true;
        ImmutableList immutablelist = ImmutableList.copyOf(parcel.readArrayList(com/google/android/calendar/utils/datatypes/ImmutableListAdapter.getClassLoader()));
        if (parcel.readInt() != 1)
        {
            flag = false;
        }
        return new AutoValue_ICalEventOperation(immutablelist, flag, parcel.readInt());
    }

    public final Object[] newArray(int i)
    {
        return new AutoValue_ICalEventOperation[i];
    }

    ()
    {
    }
}
