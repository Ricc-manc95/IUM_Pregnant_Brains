// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attendee;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event.attendee:
//            Attendee

final class 
    implements android.os.Creator
{

    public final Object createFromParcel(Parcel parcel)
    {
        return new Attendee(parcel);
    }

    public final Object[] newArray(int i)
    {
        return new Attendee[i];
    }

    ()
    {
    }
}