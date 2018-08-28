// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.color;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.color:
//            AutoValue_CustomCalendarColor

final class 
    implements android.os.alue_CustomCalendarColor._cls1
{

    public final Object createFromParcel(Parcel parcel)
    {
        return new AutoValue_CustomCalendarColor(parcel.readInt(), parcel.readString());
    }

    public final Object[] newArray(int i)
    {
        return new AutoValue_CustomCalendarColor[i];
    }

    ()
    {
    }
}
