// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.contract;

import android.os.Parcel;

// Referenced classes of package com.google.android.syncadapters.calendar.timely.contract:
//            TimelyEventData

final class 
    implements android.os.
{

    public final Object createFromParcel(Parcel parcel)
    {
        return new TimelyEventData(parcel);
    }

    public final Object[] newArray(int i)
    {
        return new TimelyEventData[i];
    }

    ()
    {
    }
}
