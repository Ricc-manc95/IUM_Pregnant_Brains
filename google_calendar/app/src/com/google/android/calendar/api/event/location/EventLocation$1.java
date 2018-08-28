// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.location;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event.location:
//            EventLocation

final class 
    implements android.os.or
{

    public final Object createFromParcel(Parcel parcel)
    {
        return new EventLocation(parcel);
    }

    public final Object[] newArray(int i)
    {
        return new EventLocation[i];
    }

    ()
    {
    }
}
