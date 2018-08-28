// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            FlightPassenger

final class 
    implements android.os.
{

    public final Object createFromParcel(Parcel parcel)
    {
        return new FlightPassenger(parcel);
    }

    public final Object[] newArray(int i)
    {
        return new FlightPassenger[i];
    }

    ()
    {
    }
}
