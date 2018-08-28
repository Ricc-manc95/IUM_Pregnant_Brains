// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event:
//            $AutoValue_V2AEventDescriptor, V2AEventDescriptor, V2AEventKey

final class AutoValue_V2AEventDescriptor extends $AutoValue_V2AEventDescriptor
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_V2AEventDescriptor(boolean flag, boolean flag1, long l, V2AEventKey v2aeventkey)
    {
        super(flag, flag1, l, v2aeventkey);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        int j;
        if (super.recurringException)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        if (super.recurringPhantom)
        {
            j = ((flag) ? 1 : 0);
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        parcel.writeLong(super.originalStart);
        parcel.writeParcelable((V2AEventKey)getKey(), i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            boolean flag1 = false;
            boolean flag;
            if (parcel.readInt() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (parcel.readInt() == 1)
            {
                flag1 = true;
            }
            return new AutoValue_V2AEventDescriptor(flag, flag1, parcel.readLong(), (V2AEventKey)parcel.readParcelable(com/google/android/calendar/api/event/V2AEventKey.getClassLoader()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_V2AEventDescriptor[i];
        }

        _cls1()
        {
        }
    }

}
