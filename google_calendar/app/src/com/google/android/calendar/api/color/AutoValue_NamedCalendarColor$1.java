// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.color;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.color:
//            AutoValue_NamedCalendarColor

final class 
    implements android.os.Value_NamedCalendarColor._cls1
{

    public final Object createFromParcel(Parcel parcel)
    {
        return new AutoValue_NamedCalendarColor(parcel.readInt(), parcel.readInt(), parcel.readInt());
    }

    public final Object[] newArray(int i)
    {
        return new AutoValue_NamedCalendarColor[i];
    }

    ()
    {
    }
}
