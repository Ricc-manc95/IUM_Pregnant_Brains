// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event:
//            AutoValue_V2AEventDescriptor, V2AEventKey

final class 
    implements android.os.Value_V2AEventDescriptor._cls1
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

    ()
    {
    }
}
