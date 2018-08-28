// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineGroove

final class _cls9
    implements android.os.r
{

    public final Object createFromParcel(Parcel parcel)
    {
        return new TimelineGroove(parcel);
    }

    public final Object[] newArray(int i)
    {
        return new TimelineGroove[i];
    }

    _cls9()
    {
    }
}
