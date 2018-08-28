// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.os.Parcel;
import com.google.android.calendar.utils.datatypes.ImmutableListAdapter;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.ical:
//            $AutoValue_ICalEventOperation, ICalEventOperation

final class AutoValue_ICalEventOperation extends $AutoValue_ICalEventOperation
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public static final ImmutableListAdapter IMMUTABLE_LIST_ADAPTER = new ImmutableListAdapter();

    AutoValue_ICalEventOperation(ImmutableList immutablelist, boolean flag, int i)
    {
        super(immutablelist, flag, i);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeList(events());
        if (canceled())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        parcel.writeInt(getImportType());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
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

        _cls1()
        {
        }
    }

}
